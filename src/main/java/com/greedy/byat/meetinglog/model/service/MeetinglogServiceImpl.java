package com.greedy.byat.meetinglog.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.greedy.byat.meetinglog.model.dao.MeetinglogMapper;
import com.greedy.byat.meetinglog.model.dto.MeetinglogDTO;
import com.greedy.byat.notice.model.dto.NoticeDTO;

/** 
* <pre>
* Class : MeetinglogServiceImpl
* Comment : 회의록  서비스
* History
* 2022/02/17  (박수빈) 초기 생성 세팅
* 2022/03/02  (박수빈) selectMeetinglogList() 회의록 목록 조회 , 회의록 상세조회, 회의록 수정
* 2022/03/03  (박수빈) 회의록 상세조회, 회의록 수정
* 2022/03/04  (박수빈) 회의록 상세 조회, 회의록 수정
* 2022/03/05  (박수빈) 회의록 상세 조회, 회의록 수정
* 2022/03/06  (박수빈) 회의록 수정,삭제
* 2022/03/07  (박수빈) 회의록 수정,삭제
* 2022/03/08  (박수빈) 회의록 수정 ,삭제
* 2022/03/09  (박수빈) 회의록 수정,삭제
* 2022/03/10  (박수빈) 알림 생성 수정 삭제
* </pre>
* @version 1.0.0
* @author 박수빈
* @see MeetinglogMapper, MeetinglogService
*
*/
@Service
public class MeetinglogServiceImpl implements MeetinglogService {
 
	private final MeetinglogMapper mapper;

	@Autowired
	public MeetinglogServiceImpl(MeetinglogMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * 메소드 selectMeetinglogList에 관한 문서화 주석
	 * mapper에 회의록 조회 요청한다.
	 * @param projectCode : 프로젝트 코드
	 * @return meetinglog : 프로젝트 코드로 조회한 회의록 배열
	 * 
	 * @author 박수빈
	 */
	@Override
	public List<MeetinglogDTO> selectMeetinglogList(int projectCode) {

		List<MeetinglogDTO> meetinglog = mapper.selectMeetinglogList(projectCode);
		
		System.out.println("selectMeetinglogList 히스토리 값 넣기후 : " + meetinglog);

		return meetinglog;
	}

	/**
	 * 메소드 registMeetinglog에 관한 문서화 주석
	 * 회의록 생성 하는 매소드입니다. 생성 성공시 알림 생성
	 * @param meetinglog : 생성할 회의록 MeetinglogDTO 정보
	 * @return message : controller에 결과 메세지 전달
	 * 
	 * @author 박수빈
	 */
	@Override
	public String registMeetinglog(MeetinglogDTO meetinglog) {

		String message = "";
		int registMeetinglogNoticeResult = 0;
	
		int result = mapper.registMeetinglog(meetinglog);
		int projectCode = meetinglog.getProjectCode();
	
		System.out.println("projectCode : " +projectCode);

		NoticeDTO notice = new NoticeDTO(); 
		List<Integer> projectMembers = mapper.selectProjectMembers(projectCode);
	
		notice.setBody("\'" + meetinglog.getTitle() + "\'회의록이 생성되었습니다."); 
		notice.setUrl("/meetinglog/list?code="+ meetinglog.getProjectCode());
		notice.setStatus("N");
		notice.setCategory(5);
		notice.setMeetinglogCode(meetinglog.getCode()); 
		System.out.println("registMeetinglogNotice noticeDTO : " + notice);

		
		for(int i = 0; i < projectMembers.size(); i++ ) {
			
			notice.setNo(projectMembers.get(i));
		  
		  registMeetinglogNoticeResult += mapper.registMeetinglogNotice(notice);
		}
		
		
		if(result > 0 && registMeetinglogNoticeResult == projectMembers.size()) {
			message="회의록 생성 성공";
			
		}else {
			message="회의록 생성 실패";
		}
		
		return message;
	}

	/**
	 * 메소드 selectMeetinglogDetail에 관한 문서화 주석
	 * 회의록 상세 조회
	 * @param projectCode : 프로젝트 코드
	 * @return meetinglog : 프로젝트 코드로 조회한 회의록 배열
	 * 
	 * @author 박수빈
	 */
	@Override
	public MeetinglogDTO selectMeetinglogDetail(int meetinglogCode) {
		System.out.println("selectMeetinglogDetail 히스토리 값 넣기전 : " );
		
		MeetinglogDTO meetinglog = mapper.selectMeetinglogDetail(meetinglogCode);

		System.out.println("selectMeetinglogDetail 히스토리 값 넣기전 : " + meetinglog);
		
		return meetinglog;
	}

	/**
	 * 메소드 selectMeetinglogList에 관한 문서화 주석
	 * 회의록 수정 
	 * @param projectCode : 프로젝트 코드
	 * @return meetinglog : 프로젝트 코드로 조회한 회의록 배열
	 * 
	 * @author 박수빈
	 */
	@Override
	public String modifyMeetinglog(MeetinglogDTO meetinglog) {
		System.out.println("//modifyMeetinglog//meetinglog 전: " + meetinglog );
		
		String message = "";
		int projectCode= meetinglog.getProjectCode();
		int modifyMeetinglogNoticeResult = 0;
		NoticeDTO notice = new NoticeDTO(); 
		java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
		
		List<Integer> projectMembers = mapper.selectProjectMembers(projectCode);
		
		meetinglog.setWritingDate(sqlDate);
		meetinglog.setDeleteStatus("N");
		
		int result = mapper.modifyMeetinglog(meetinglog);
		System.out.println("modifyMeetinglog meetinglog  후 : " + meetinglog );
		
		mapper.insertVersion(meetinglog);
		
		notice.setBody("\'" + meetinglog.getTitle() + "\'회의록이 수정되었습니다."); 
		notice.setUrl("/meetinglog/list?code="+meetinglog.getProjectCode());
		notice.setStatus("N");
		notice.setCategory(5);
		notice.setMeetinglogCode(meetinglog.getCode()); 
		
		for(int i = 0; i < projectMembers.size(); i++ ) {
		    notice.setNo(projectMembers.get(i));
		    modifyMeetinglogNoticeResult += mapper.registMeetinglogNotice(notice);
		}
		
		if(result > 0 && modifyMeetinglogNoticeResult == projectMembers.size()) {
			message="회의록 수정 성공";
		}else {
			message="회의록 수정ㄴ 실패";
		}
		return message ;
	}

	/**
	 * 메소드 selectProjectName에 관한 문서화 주석
	 * 프로젝트 제목 조회
	 * @param projectCode :  해당 프로젝트 코드
	 * @return project : 조회한 프로젝트 제목 
	 * 
	 * @author 박수빈
	 */
	@Override
	public String selectProjectName(int projectCode) {
		
		String project = mapper.selectProjectName(projectCode);
		
		return project;
	}

	/**
	 * 메소드 removeMeetinglog에 관한 문서화 주석
	 * 회의록 삭제  성공시 알람 생성
	 * @param meetinglogCode :  삭제할 회의록 고유 번호
	 * @return message : 회의록 삭제 결과 메세지  
	 * 
	 * @author 박수빈
	 */
	@Override
	public String removeMeetinglog(int meetinglogCode) {
		System.out.println("remove meetinglog : " + meetinglogCode);
		
		
		String message = "";
		int removeMeetinglogNoticeResult = 0;
		
		NoticeDTO notice = new NoticeDTO(); 
		MeetinglogDTO meetinglog = mapper.selectMeetinglogDetail(meetinglogCode);
		
		System.out.println("remove meetinglog : " + meetinglog);
		int projectCode = meetinglog.getProjectCode();
		System.out.println("remove projectCode : " + projectCode);

		List<Integer> projectMembers = mapper.selectProjectMembers(projectCode);
		System.out.println(projectMembers);

		int removeResult = mapper.removeMeetinglog(meetinglogCode);

		notice.setBody("\'" + meetinglog.getTitle() + "\'회의록이 삭제되었습니다."); 
		notice.setUrl("/meetinglog/list?code="+meetinglog.getProjectCode());
		notice.setStatus("N");
		notice.setCategory(5);
		notice.setMeetinglogCode(meetinglog.getCode()); 
		
		for(int i = 0; i < projectMembers.size(); i++ ) {
		    notice.setNo(projectMembers.get(i));
		    removeMeetinglogNoticeResult += mapper.registMeetinglogNotice(notice);
		}
		
		System.out.println("removeResult");

		if(removeResult > 0 && removeMeetinglogNoticeResult == projectMembers.size() && meetinglog != null)  {
			message="회의록 삭제 성공";
		}else {
			message="회의록 삭제 실패";
		}
		
		return message ;
		
	}
	 
}


