package com.greedy.byat.notice.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.common.exception.notice.NoticeConfirmRemoveException;
import com.greedy.byat.common.exception.notice.NoticeModifySettingException;
import com.greedy.byat.common.exception.notice.NoticeRemoveException;
import com.greedy.byat.common.exception.notice.NoticeUpdateStatusExcepction;
import com.greedy.byat.notice.model.dao.NoticeMapper;
import com.greedy.byat.notice.model.dto.NoticeDTO;
import com.greedy.byat.notice.model.dto.NoticeSettingDTO;

/**
 * <pre>
 * Class : NoticeServiceImpl
 * Comment : NoticeService의 메소드들을 실제로 처리하는 class 입니다.
 * History
 * 2022/03/05 (박인근) 처음 작성, 알림 생성, 알림 목록 조회, 알림 조회, 알림 삭제 관련 코드 추가
 * 2022/03/06 (박인근) 알림 전체 목록 조회, 알림 삭제, 알림 설정 관련 코드 추가
 * 2022/03/07 (박인근) 알림 삭제 관련 코드 추가
 * </pre>
 * @version 3
 * @author 박인근
 * see NoticeController, NoticeDTO, MemberDTO, NoticeServiceImpl, NoticeMapper.java, NoticeMapper.xml
 * */
@Service
public class NoticeServiceImpl implements NoticeService {
 
	/** NoticeMapper의 메소드들을 호출하기 위해 사용하는 전역변수로 변경 불가능하도록 final로 선언 */
	private final NoticeMapper mapper;

	@Autowired
	public NoticeServiceImpl(NoticeMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * 메소드 selectNoticeList에 관한 문서화 주석
	 * @ param no 로그인 한 멤버의 알림을 조회하기 위한 멤버의 식별 번호 파라미터
	 * @ return 로그인 한 멤버의 알림 목록 정보인 NoticeDTO List 반환
	 * */
	@Override
	public List<NoticeDTO> selectNoticeList(int no) {
		
		List<NoticeDTO> noticeList = mapper.selectNoticeList(no);
		
		NoticeSettingDTO noticeSetting = mapper.selectNoticeSetting(no);
		
		List<NoticeDTO> noticeFinalList = new ArrayList<>();
		
		int noticeCount = 0;
		
		for(int i = 0; i < noticeList.size(); i++) {
			
			switch(noticeList.get(i).getCategory()) {
				case 1:
					if("Y".equals(noticeSetting.getProjectSetting())) {
						noticeFinalList.add(noticeCount, noticeList.get(i));
						noticeCount++;
					}
					break;
				case 2:
					if("Y".equals(noticeSetting.getSprintSetting())) {
						noticeFinalList.add(noticeCount, noticeList.get(i));
						noticeCount++;
					}
					break;
				case 3:
					if("Y".equals(noticeSetting.getBacklogSetting())) {
						noticeFinalList.add(noticeCount, noticeList.get(i));
						noticeCount++;
					}
					break;
				case 4:
					if("Y".equals(noticeSetting.getIssueSetting())) {
						noticeFinalList.add(noticeCount, noticeList.get(i));
						noticeCount++;
					}
					break;
				case 5:
					if("Y".equals(noticeSetting.getMeetinglogSetting())) {
						noticeFinalList.add(noticeCount, noticeList.get(i));
						noticeCount++;
					}
					break;
				case 6:
					if("Y".equals(noticeSetting.getCalendarSetting())) {
						noticeFinalList.add(noticeCount, noticeList.get(i));
						noticeCount++;
					}
					break;
			}
			
		}
		
		for(int i = 0; i < noticeFinalList.size(); i++) {
			
			if(noticeFinalList.get(i).getBody().length() >= 50) {
				noticeFinalList.get(i).setBody(noticeFinalList.get(i).getBody().substring(0, 50) + "...");
			}
			
		}
		
		return noticeFinalList;
	}

	/**
	 * 메소드 updateNoticeStatus에 관한 문서화 주석
	 * @ param no 로그인 한 멤버의 알림인지 확인하기 위한 멤버의 식별 번호인 파라미터
	 * @ param code 멤버가 변경하려는 알림의 식별 번호인 파라미터
	 * @ exception 알림의 상태 변경 실패 예외에 대한 처리
	 * */
	@Override
	public void updateNoticeStatus(int no, int code) throws NoticeUpdateStatusExcepction {
		
		NoticeDTO notice = new NoticeDTO();
		notice.setCode(code);
		notice.setNo(no);
		
		int result = mapper.updateNoticeStatus(notice);
		
		if(!(result > 0)) {
			throw new NoticeUpdateStatusExcepction("알림 읽음 상태 변경!");
		}
		
	}

	/**
	 * 메소드 deleteConfirmNotice에 관한 문서화 주석
	 * @ param no 로그인 한 멤버의 조회한 알림을 삭제하기 위한 멤버의 식별 번호인 파라미터
	 * @ exception 조회한 알림 삭제 실패에 대한 예외 처리
	 * */
	@Override
	public void deleteConfirmNotice(int no) throws NoticeConfirmRemoveException {
		
		int result = mapper.deleteConfirmNotice(no);
		
		if(!(result > 0)) {
			throw new NoticeConfirmRemoveException("조회한 알림 삭제 실패!");
		}
		
	}

	/**
	 * 메소드 selectNoticeSetting에 관한 문서화 주석
	 * @ param no 로그인 한 멤버의 알림 설정 정보를 조회하기 위한 멤버의 식별 번호인 파라미터
	 * @ return 로그인 한 멤버의 알림 설정 정보인 NoticeSettingDTO를 반환
	 * */
	@Override
	public NoticeSettingDTO selectNoticeSetting(int no) {

		NoticeSettingDTO noticeSetting = mapper.selectNoticeSetting(no);
		
		return noticeSetting;
	}

	/**
	 * 메소드 updateNoticeSetting에 관한 문서화 주석
	 * @ param noticeSetting 로그인한 멤버가 변경한 알림 설정 정보를 담고 있는 파라미터
	 * @ exception 멤버의 알림 설정 변경 살패에 대한 예외 처리
	 * */
	@Override
	public void updateNoticeSetting(NoticeSettingDTO noticeSetting) throws NoticeModifySettingException {
		
		int noticeSettingResult = mapper.updateNoticeSetting(noticeSetting);
		
		if(!(noticeSettingResult > 0)) {
			
			throw new NoticeModifySettingException("멤버 알림 설정 변경 실패!");
		}
		
	}

	/**
	 * 메소드 changeNoticeStatus에 관한 문서화 주석
	 * @ param notice 로그인 한 멤버의 식별 번호와 알림의 읽음 상태를 변경한 값을 담고 있는 파라미터
	 * @ exception 멤버의 각각의 알림의 읽음 상태를 반대로 변경시 실패 예외 처리
	 * */
	@Override
	public void chagenNoticeStatus(NoticeDTO notice) throws NoticeUpdateStatusExcepction {

		int noticeStatusUpdateResult = mapper.updateNoticeStatusChange(notice);
		
		if(!(noticeStatusUpdateResult > 0)) {
			
			throw new NoticeUpdateStatusExcepction("알림 상태 변경 실패!");
		}
		
	}

	/**
	 * 메소드 deleteNotice에 관한 문서화 주석
	 * @ param notice 로그인 한 멤버의 식별 번호와 삭제하려는 알림의 식별 번호를 담고 있는 파라미터
	 * @ exception 삭제한 알림의 상태 변병 실패 예외, 알림 삭제 실패 예외 처리
	 * */
	@Override
	public void deleteNotice(NoticeDTO notice) throws NoticeRemoveException {
		
		int noticeDeleteResult = mapper.deleteNotice(notice);
		
		if(!(noticeDeleteResult > 0)) {
			
			throw new NoticeRemoveException("알림 삭제 실패!");
		}
		
	}

}


