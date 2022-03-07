package com.greedy.byat.notice.model.dao;

import java.util.List;

import com.greedy.byat.notice.model.dto.NoticeDTO;
import com.greedy.byat.notice.model.dto.NoticeSettingDTO;

public interface NoticeMapper {

	List<NoticeDTO> selectNoticeList(int no);

	NoticeSettingDTO selectNoticeSetting(int no);

	int updateNoticeStatus(NoticeDTO notice);

	int deleteConfirmNotice(int no);

	int updateNoticeSetting(NoticeSettingDTO noticeSetting);

	int updateNoticeStatusChange(NoticeDTO notice);

	int deleteNotice(NoticeDTO notice);

}
