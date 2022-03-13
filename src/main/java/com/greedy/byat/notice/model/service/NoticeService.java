package com.greedy.byat.notice.model.service;

import java.util.List;

import com.greedy.byat.common.exception.notice.NoticeConfirmRemoveException;
import com.greedy.byat.common.exception.notice.NoticeModifySettingException;
import com.greedy.byat.common.exception.notice.NoticeRemoveException;
import com.greedy.byat.common.exception.notice.NoticeUpdateStatusExcepction;
import com.greedy.byat.common.exception.notice.NoticeUpdateStatusExcepction;
import com.greedy.byat.notice.model.dto.NoticeDTO;
import com.greedy.byat.notice.model.dto.NoticeSettingDTO;

/**
 * <pre>
 * Class : NoticeService
 * Comment : NoticeController의 요청을 처리하는 Interface 입니다.
 * History
 * 2022/03/05 (박인근) 처음 작성, 알림 생성, 알림 목록 조회, 알림 조회, 알림 삭제 관련 코드 추가
 * 2022/03/06 (박인근) 알림 전체 목록 조회, 알림 삭제, 알림 설정 관련 코드 추가
 * 2022/03/07 (박인근) 알림 삭제 관련 코드 추가
 * </pre>
 * @version 3
 * @author 박인근
 * see NoticeController, NoticeDTO, MemberDTO, NoticeServiceImpl, NoticeMapper.java, NoticeMapper.xml
 * */
public interface NoticeService {

	List<NoticeDTO> selectNoticeList(int no);

	void updateNoticeStatus(int no, int code) throws NoticeUpdateStatusExcepction;

	void deleteConfirmNotice(int no) throws NoticeConfirmRemoveException;

	NoticeSettingDTO selectNoticeSetting(int no);

	void updateNoticeSetting(NoticeSettingDTO noticeSetting) throws NoticeModifySettingException;

	void chagenNoticeStatus(NoticeDTO notice) throws NoticeUpdateStatusExcepction;

	void deleteNotice(NoticeDTO notice) throws NoticeRemoveException;

}
