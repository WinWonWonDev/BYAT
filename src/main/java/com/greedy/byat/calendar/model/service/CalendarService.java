package com.greedy.byat.calendar.model.service;

/**
 * <pre>
 * Class : CalendarService
 * Comment : CalendarController의 요청을 처리하는 Interface
 * History
 * 2021/02/17 (이소현) 처음 작성
 * </pre>
 * @version 1.0.0
 * @author 이소현
 * @see CalendarController, CalendarServiceImpl, CalendarMapper, ScheduleDTO
 * */
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.greedy.byat.calendar.model.dto.ScheduleDTO;
import com.greedy.byat.member.model.dto.MemberDTO;


public interface CalendarService {

	List<ScheduleDTO> selectCalendarList(MemberDTO loginMember);

	int registSchedule(List<Map<String, Object>> calendarList, MemberDTO loginMember);

	List<MemberDTO> selectAllMemberList();

}
