package com.greedy.byat.notice.model.service;

import java.util.List;

import com.greedy.byat.common.exception.notice.NoticeConfirmRemoveException;
import com.greedy.byat.common.exception.notice.NoticeModifySettingException;
import com.greedy.byat.common.exception.notice.NoticeRemoveException;
import com.greedy.byat.common.exception.notice.NoticeUpdateStatusExcepction;
import com.greedy.byat.common.exception.notice.NoticeUpdateStatusExcepction;
import com.greedy.byat.notice.model.dto.NoticeDTO;
import com.greedy.byat.notice.model.dto.NoticeSettingDTO;

public interface NoticeService {

	List<NoticeDTO> selectNoticeList(int no);

	void updateNoticeStatus(int no, int code) throws NoticeUpdateStatusExcepction;

	void deleteConfirmNotice(int no) throws NoticeConfirmRemoveException;

	NoticeSettingDTO selectNoticeSetting(int no);

	void updateNoticeSetting(NoticeSettingDTO noticeSetting) throws NoticeModifySettingException;

	void chagenNoticeStatus(NoticeDTO notice) throws NoticeUpdateStatusExcepction;

	void deleteNotice(NoticeDTO notice) throws NoticeRemoveException;

}
