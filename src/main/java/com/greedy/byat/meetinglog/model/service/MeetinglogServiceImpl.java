package com.greedy.byat.meetinglog.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.greedy.byat.meetinglog.model.dao.MeetinglogMapper;
import com.greedy.byat.meetinglog.model.dto.MeetinglogDTO;
import com.greedy.byat.notice.model.dto.NoticeDTO;

@Service
public class MeetinglogServiceImpl implements MeetinglogService {
 
	private final MeetinglogMapper mapper;

	@Autowired
	public MeetinglogServiceImpl(MeetinglogMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<MeetinglogDTO> selectMeetinglogList(int projectCode) {

		List<MeetinglogDTO> meetinglog = mapper.selectMeetinglogList(projectCode);
		
		System.out.println("selectMeetinglogList 히스토리 값 넣기후 : " + meetinglog);

		return meetinglog;
	}

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

	@Override
	public MeetinglogDTO selectMeetinglogDetail(int meetinglogCode) {
		System.out.println("selectMeetinglogDetail 히스토리 값 넣기전 : " );
		
		MeetinglogDTO meetinglog = mapper.selectMeetinglogDetail(meetinglogCode);

		System.out.println("selectMeetinglogDetail 히스토리 값 넣기전 : " + meetinglog);
		
		return meetinglog;
	}

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

	@Override
	public String selectProjectName(int projectCode) {
		
		String project = mapper.selectProjectName(projectCode);
		
		return project;
	}

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


