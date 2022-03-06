package com.greedy.byat.calendar.model.service;


import java.util.List;
import java.util.Map;

import com.greedy.byat.calendar.model.dto.ScheduleDTO;
import com.greedy.byat.member.model.dto.MemberDTO;


public interface CalendarService {

	int registSchedule(Map<String, Object> map, int memberNo, String memberName, int permitCode);

	List<ScheduleDTO> selectCalendarList(MemberDTO loginMember);

	int registSchedule(List<Map<String, Object>> calendarList);

}
