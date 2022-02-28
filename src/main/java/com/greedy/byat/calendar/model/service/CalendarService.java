package com.greedy.byat.calendar.model.service;


import java.util.Map;


public interface CalendarService {

	int registSchedule(Map<String, Object> map, int memberNo, String memberName, int permitCode);

}
