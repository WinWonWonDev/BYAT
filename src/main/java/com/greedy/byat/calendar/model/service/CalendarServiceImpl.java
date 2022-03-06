package com.greedy.byat.calendar.model.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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

	@Override
	public int registSchedule(List<Map<String, Object>> calendarList, MemberDTO loginMember) throws ParseException {
		
		System.out.println("서비스 impt 옴?");
		
		int result = 0;
		
		if(loginMember.getPermitCode() == 1) {
			
			// string형으로 들어가니까 각자 
			for(Map<String, Object> calendarListInfo : calendarList) { 
				String calendarStartDate = (String) calendarListInfo.get("startDate"); 
				String calendarEndDate = (String) calendarListInfo.get("endDate");
				String calendarTitle = (String) calendarListInfo.get("title");
				
				
				Map<String, Object> map = new HashMap<>();
				map.put("title", calendarTitle);
				map.put("startDate", calendarStartDate);
				map.put("endDate", calendarEndDate);
				map.put("memberNo", loginMember.getNo());
				map.put("memberName", loginMember.getName());
				
				result = mapper.insertScheduleByAdmin(map);
				
			}
			
		} else if(loginMember.getPermitCode() == 3 || loginMember.getPermitCode() == 2) {
			
			System.out.println("금 여기 들어와야되잔항?");
			// string형으로 들어가니까 각자 
			for(Map<String, Object> calendarListInfo : calendarList) { 
				
				String calendarStartDate = (String) calendarListInfo.get("startDate"); 
				String calendarEndDate = (String) calendarListInfo.get("endDate");
				String calendarTitle = (String) calendarListInfo.get("title");

				System.out.println("calendarStartDate : " + calendarStartDate);
				System.out.println("calendarEndDate : " + calendarEndDate);

//				System.out.println(calendarStartDate.split("T")[calendarListCnt].toString()); //이렇게 하면 나온단 말이지? 
				
				String realCalendarStartDate = calendarStartDate.split("T")[0].toString();
				String realCalendarEndDate = calendarEndDate.split("T")[0].toString();
				
				System.out.println("startDate : " + realCalendarStartDate);
				System.out.println("EndDate : " + realCalendarEndDate);
				
				
				
				Map<String, Object> map = new HashMap<>();
				map.put("title", calendarTitle);
				map.put("startDate", realCalendarStartDate);
				map.put("endDate", realCalendarEndDate);
				map.put("memberNo", loginMember.getNo());
				map.put("memberName", loginMember.getName());
				
				System.out.println("map: " + map);
				
				result = mapper.insertScheduleByMember(map);
				
				System.out.println("됫으면 나와라! : " + result);
				
			}
		}
		
		
			
		if(result > 0) {
			return 1;
		} else {
			return 0;
		}
		
	}



	







}


