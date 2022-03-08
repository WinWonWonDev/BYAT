package com.greedy.byat.calendar.model.service;


import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.greedy.byat.calendar.model.dto.ScheduleDTO;
import com.greedy.byat.member.model.dto.MemberDTO;


public interface CalendarService {

	List<ScheduleDTO> selectCalendarList(MemberDTO loginMember);

	int registSchedule(List<Map<String, Object>> calendarList, MemberDTO loginMember) throws ParseException;

	List<MemberDTO> selectAllMemberList();

	List<ScheduleDTO> selectCalendarListForSelectBox(int memberNoForMove);

}
