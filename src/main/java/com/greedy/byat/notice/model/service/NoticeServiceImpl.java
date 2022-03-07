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

@Service
public class NoticeServiceImpl implements NoticeService {
 
	private final NoticeMapper mapper;

	@Autowired
	public NoticeServiceImpl(NoticeMapper mapper) {
		this.mapper = mapper;
	}

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

	@Override
	public void deleteConfirmNotice(int no) throws NoticeConfirmRemoveException {
		
		int result = mapper.deleteConfirmNotice(no);
		
		if(!(result > 0)) {
			throw new NoticeConfirmRemoveException("조회한 알림 삭제 실패!");
		}
		
	}

	@Override
	public NoticeSettingDTO selectNoticeSetting(int no) {

		NoticeSettingDTO noticeSetting = mapper.selectNoticeSetting(no);
		
		return noticeSetting;
	}

	@Override
	public void updateNoticeSetting(NoticeSettingDTO noticeSetting) throws NoticeModifySettingException {
		
		int noticeSettingResult = mapper.updateNoticeSetting(noticeSetting);
		
		if(!(noticeSettingResult > 0)) {
			
			throw new NoticeModifySettingException("멤버 알림 설정 변경 실패!");
		}
		
	}

	@Override
	public void chagenNoticeStatus(NoticeDTO notice) throws NoticeUpdateStatusExcepction {

		int noticeStatusUpdateResult = mapper.updateNoticeStatusChange(notice);
		
		if(!(noticeStatusUpdateResult > 0)) {
			
			throw new NoticeUpdateStatusExcepction("알림 상태 변경 실패!");
		}
		
	}

	@Override
	public void deleteNotice(NoticeDTO notice) throws NoticeRemoveException {
		
		int noticeDeleteResult = mapper.deleteNotice(notice);
		
		if(!(noticeDeleteResult > 0)) {
			
			throw new NoticeRemoveException("알림 삭제 실패!");
		}
		
	}

}


