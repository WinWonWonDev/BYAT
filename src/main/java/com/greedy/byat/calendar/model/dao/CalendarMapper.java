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
* 2021/02/17 (이소현) 처음 작성
* </pre>
* @version 1.0.0
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
