package com.greedy.byat.notice.model.dao;

import java.util.List;

import com.greedy.byat.notice.model.dto.NoticeDTO;
import com.greedy.byat.notice.model.dto.NoticeSettingDTO;

/**
 * <pre>
 * Class : NoticeMapper
 * Comment : mapper.xml의 id들이 담긴 class
 * History
 * 2022/03/05 (박인근) 처음 작성, 알림 생성, 알림 목록 조회, 알림 조회, 알림 삭제 관련 코드 추가
 * 2022/03/06 (박인근) 알림 전체 목록 조회, 알림 삭제, 알림 설정 관련 코드 추가
 * 2022/03/07 (박인근) 알림 삭제 관련 코드 추가
 * </pre>
 * @version 3
 * @author 박인근
 * @see NoticeController, NoticeService, NoticeServiceImpl, MemberDTO, NoticeDTO, NoticeSettingDTO
 * */
public interface NoticeMapper {

	List<NoticeDTO> selectNoticeList(int no);

	NoticeSettingDTO selectNoticeSetting(int no);

	int updateNoticeStatus(NoticeDTO notice);

	int deleteConfirmNotice(int no);

	int updateNoticeSetting(NoticeSettingDTO noticeSetting);

	int updateNoticeStatusChange(NoticeDTO notice);

	int deleteNotice(NoticeDTO notice);

}
