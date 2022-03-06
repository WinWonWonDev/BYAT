package com.greedy.byat.notice.model.service;

import java.util.List;

import com.greedy.byat.common.exception.notice.NoticeConfirmRemoveException;
import com.greedy.byat.common.exception.notice.NoticeUpdateStatus;
import com.greedy.byat.notice.model.dto.NoticeDTO;

public interface NoticeService {

	List<NoticeDTO> selectNoticeList(int no);

	void updateNoticeStatus(int no, int code) throws NoticeUpdateStatus;

	void deleteConfirmNotice(int no) throws NoticeConfirmRemoveException;

}
