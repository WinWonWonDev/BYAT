package com.greedy.byat.calendar.model.dao;

import java.util.List;
import java.util.Map;

public interface CalendarMapper {

	int insertScheduleByMember(Map<String, Object> map);

	int insertScheduleByAdmin(Map<String, Object> map);

	List<Integer> selectAllMemberNo();


}
