package com.greedy.byat.notice.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.common.exception.notice.NoticeConfirmRemoveException;
import com.greedy.byat.common.exception.notice.NoticeUpdateStatus;
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
		
		return noticeFinalList;
	}

	@Override
	public void updateNoticeStatus(int no, int code) throws NoticeUpdateStatus {
		
		NoticeDTO notice = new NoticeDTO();
		notice.setCode(code);
		notice.setNo(no);
		
		int result = mapper.updateNoticeStatus(notice);
		
		if(!(result > 0)) {
			throw new NoticeUpdateStatus("알림 읽음 상태 변경!");
		}
		
	}

	@Override
	public void deleteConfirmNotice(int no) throws NoticeConfirmRemoveException {
		
		int result = mapper.deleteConfirmNotice(no);
		
		if(!(result > 0)) {
			throw new NoticeConfirmRemoveException("조회한 알림 삭제 실패!");
		}
		
	}

}


