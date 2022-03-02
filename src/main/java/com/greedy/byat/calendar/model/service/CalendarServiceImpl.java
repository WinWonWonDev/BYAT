package com.greedy.byat.calendar.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.calendar.model.dao.CalendarMapper;
import com.greedy.byat.calendar.model.dto.ScheduleDTO;
import com.greedy.byat.member.model.dto.MemberDTO;

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
			
		} else if(permitCode == 3 || permitCode == 2) {
			result = mapper.insertScheduleByMember(map);
		}

		return result;
	}

	@Override
	public List<ScheduleDTO> selectCalendarList(MemberDTO loginMember) {
		
		//캘린더 조회할때 -> 나와야하는 게 
		//1) 관리자의 경우 -> 프로젝트는 x, 회사일정, 대신 관리자만 다른 애들 검색 가능 !
		//2) 일반 멤버의 경우 -> 관련된 프로젝트 , 본인 개인 일정
		//3) PM의 경우 -> 관련된 프로젝트, 본인 개인 일정
		// -> 2,3 의 경우 : 프로젝트에 참여했어도 현재 참여여부따져서 나오게 안나오게 해야함
		List<ScheduleDTO> totalCalendarList = null;
		List<ScheduleDTO> calendarList = null;
		
		int memberNo = loginMember.getNo();
		int permitCode = loginMember.getPermitCode();
		
		if(permitCode == 1) { //관리자의 경우
			totalCalendarList = mapper.selectCalendarListByAdmin(memberNo);
			
		} else { //일반멤버,PM의 경우(나머지 2,3인 경우)
			totalCalendarList = mapper.selectProjectListByMemberOne(memberNo);
			calendarList = mapper.selectCalendarListByMemberOne(memberNo);
			totalCalendarList.addAll(calendarList);
		}
		
		return totalCalendarList;
	}



	







}


