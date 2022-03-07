package com.greedy.byat.calendar.model.dao;

import java.util.List;
import java.util.Map;

import com.greedy.byat.calendar.model.dto.ScheduleDTO;
import com.greedy.byat.member.model.dto.MemberDTO;

public interface CalendarMapper {

	int insertScheduleByMember(Map<String, Object> map);

	int insertScheduleByAdmin(Map<String, Object> map);

//	List<Integer> selectAllMemberNo();

//	List<ScheduleDTO> selectCalendarList(int memberNo);

	List<ScheduleDTO> selectCalendarListByAdmin(int memberNo);

	int selectProjectCodes(MemberDTO loginMember);

	List<ScheduleDTO> selectProjectListByMemberOne(int memberNo);

	List<ScheduleDTO> selectCalendarListByMemberOne(int memberNo);

	int insertSchedule(Map<String, Object> map);

	int deleteAllCalendar(int memberNo);


}
