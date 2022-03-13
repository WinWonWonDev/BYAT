package com.greedy.byat.meetinglog.model.dao;

import java.util.List;

import com.greedy.byat.meetinglog.model.dto.MeetinglogDTO;
import com.greedy.byat.notice.model.dto.NoticeDTO;

/** 
* <pre>
* Class : MeetinglogMapper
* Comment : 회의록  mapper.xml의 id들이 담긴 Class
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
* @see MeetinglogServiceImpl, MeetinglogService
*
*/
public interface MeetinglogMapper {

	List<MeetinglogDTO> selectMeetinglogList(int projectCode);

	int registMeetinglog(MeetinglogDTO meetinglog);

	MeetinglogDTO selectMeetinglogDetail(int meetinglog);

	int modifyMeetinglog(MeetinglogDTO meetinglog);

	void insertVersion(MeetinglogDTO meetinglog);

	int registMeetinglogHistory(MeetinglogDTO meetinglog);

	String selectProjectName(int projectCode);

	int removeMeetinglog(int meetingCode);

	int registMeetinglogNotice(NoticeDTO notice);

	List<Integer> selectProjectMembers(int projectCode);

	

}
