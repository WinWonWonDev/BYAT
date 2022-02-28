package com.greedy.byat.calendar.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.calendar.model.dao.CalendarMapper;
import com.greedy.byat.calendar.model.dto.ScheduleDTO;

@Service
public class CalendarServiceImpl implements CalendarService {
 
	private final CalendarMapper mapper;

	@Autowired
	public CalendarServiceImpl(CalendarMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public int registSchedule(Map<String, Object> map, int memberNo, String memberName, int permitCode) {
		
		List<Integer> memberNumbers = mapper.selectAllMemberNo();
		int result = 0;

		map.put("memberNo", memberNo);
		map.put("memberName", memberName);
		
		if(permitCode == 1) {
			for(int i = 0; i < memberNumbers.size(); i++) {
				map.put("memberNo", memberNumbers.get(i));
				result = mapper.insertScheduleByAdmin(map);
			}
			
		} else if(permitCode == 3) {
			result = mapper.insertScheduleByMember(map);
		}

		return result;
	}



	







}


