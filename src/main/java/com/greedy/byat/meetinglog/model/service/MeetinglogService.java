package com.greedy.byat.meetinglog.model.service;

import java.util.List;

import com.greedy.byat.meetinglog.model.dto.MeetinglogDTO;

/**
 * <pre>
 * Class : MeetinglogService
 * Comment : MeetinglogController의 요청을 처리하는 Interface
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
 * @see MeetinglogController, MeetinglogServiceImpl, MeetinglogMapper
 * */
public interface MeetinglogService {

	List<MeetinglogDTO> selectMeetinglogList(int projectCode);

	String registMeetinglog(MeetinglogDTO meetinglog);

	MeetinglogDTO selectMeetinglogDetail(int meetinglogCode);

	String modifyMeetinglog(MeetinglogDTO meetinglog);

	String selectProjectName(int projectCode);

	String removeMeetinglog(int meetingCode);

}
