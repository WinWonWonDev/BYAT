package com.greedy.byat.calendar.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.calendar.model.dao.CalendarMapper;

/* 
* <pre>
* Class : CalendarServiceImpl
* Comment : CalendarController에 필요한 메소드들의 로직을 작성하는 Service입니다.
* History
* 2022/02/25 이소현  처음 작성
* </pre>
* @version 1.0.0
* @author 이소현
* @see CalendarService, CalendarController, ScheduleDTO, CalendarMapper  
*
*/
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


