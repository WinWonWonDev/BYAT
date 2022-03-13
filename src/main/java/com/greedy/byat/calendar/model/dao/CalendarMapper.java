package com.greedy.byat.calendar.model.dao;

import java.util.List;
import java.util.Map;

import com.greedy.byat.calendar.model.dto.ScheduleDTO;
import com.greedy.byat.member.model.dto.MemberDTO;


/**
* <pre>
* Class : CalendarMapper
* Comment : mapper.xml의 id들이 담긴 Class
* History
* 2022/02/27 (이소현) 처음 작성, 캘린더 목록 조회
* 2022/02/28 (이소현) 캘린더 목록 조회, 일정 생성(일반 멤버/관리자의 경우)
* 2022/03/01 (이소현) 캘린더 목록 조회, 일정 생성(시작날짜가 종료날짜보다 느린 경우 수정)
* 2022/03/02 (이소현) 캘린더 목록 조회(관리자/PM/일반 멤버의 경우), 일정 수정, 삭제 
* 2022/03/03 (이소현) 캘린더 목록 조회(jsp와 연동), 일정 삭제  
* 2022/03/04 (이소현) 캘린더 api 파악  
* 2022/03/05 (이소현) fullcalendar에 google캘린더 api 적용 및 연동 
* 2022/03/06 (이소현) fullcalendar 조회, 생성 수정
* 2022/03/07 (이소현) fullcalendar 목록 조회(json문자열로 넘어노는 에러 수정) 
* 2022/03/08 (이소현) fullcalendar 관리자의 경우 멤버 검색 및 캘린더 조회  
* </pre>
* @version 10
* @author 이소현
* @see CalendarController, CalendarService, CalendarServiceImpl, ScheduleDTO
* */
public interface CalendarMapper {

	int insertScheduleByMember(Map<String, Object> map);

	int insertScheduleByAdmin(Map<String, Object> map);

	List<Integer> selectAllMemberNo();

	List<ScheduleDTO> selectCalendarListByAdmin(int memberNo);

	int selectProjectCodes(MemberDTO loginMember);

	List<ScheduleDTO> selectProjectListByMemberOne(int memberNo);

	List<ScheduleDTO> selectCalendarListByMemberOne(int memberNo);

	int insertSchedule(Map<String, Object> map);

	int deleteAllCalendar(int memberNo);

	List<MemberDTO> selectAllMemberList();

	int deleteAllCalendarByAdmin();


}
