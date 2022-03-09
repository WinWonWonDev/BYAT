package com.greedy.byat.calendar.model.service;

import java.text.ParseException;
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
	public List<ScheduleDTO> selectCalendarList(MemberDTO loginMember) {
		
		List<ScheduleDTO> totalCalendarList = null;
		List<ScheduleDTO> calendarList = null;
		
		int memberNo = loginMember.getNo();
		int permitCode = loginMember.getPermitCode();
		
		if(permitCode == 1) { //관리자의 경우
			totalCalendarList = mapper.selectCalendarListByAdmin(memberNo);
			
		} else { //일반멤버,PM의 경우(나머지 2,3인 경우) -> 관리자일정, 프로젝트일정, 개인일정 다 보여야 되잖아? 
			totalCalendarList = mapper.selectProjectListByMemberOne(memberNo);
			calendarList = mapper.selectCalendarListByMemberOne(memberNo);

			totalCalendarList.addAll(calendarList);
		}
		
		return totalCalendarList;
	}

	@Override
	public int registSchedule(List<Map<String, Object>> calendarList, MemberDTO loginMember) throws ParseException {
		
		int memberNo = loginMember.getNo();
		String memberName = loginMember.getName();
		int deleteAllCalendar = 0;
		int result = 0;
			
		List<Integer> memberNumbers = mapper.selectAllMemberNo();
		  
		
		if(loginMember.getPermitCode() == 1) {
			
			deleteAllCalendar = mapper.deleteAllCalendarByAdmin();

			for(Map<String, Object> calendarListInfo : calendarList) { 
					String calendarStartDate = (String) calendarListInfo.get("startDate"); 
					String calendarEndDate = (String) calendarListInfo.get("endDate");
					String calendarTitle = (String) calendarListInfo.get("title");
					
					String realCalendarStartDate = calendarStartDate.split("T")[0].toString();
					String realCalendarEndDate = calendarEndDate.split("T")[0].toString();
					
					
					Map<String, Object> map = new HashMap<>();
					map.put("title", calendarTitle);
					map.put("startDate", realCalendarStartDate);
					map.put("endDate", realCalendarEndDate);
					map.put("memberName", memberName);
					
					for(int i = 0; i < memberNumbers.size(); i++) {

						map.put("memberNo", memberNumbers.get(i));
						result = mapper.insertScheduleByAdmin(map);
					}
					
				}
			
		} else if (loginMember.getPermitCode() == 3 || loginMember.getPermitCode() == 2) {

			deleteAllCalendar = mapper.deleteAllCalendar(memberNo);
			
			for(Map<String, Object> calendarListInfo : calendarList) { 
				
				String calendarStartDate = (String) calendarListInfo.get("startDate"); 
				String calendarEndDate = (String) calendarListInfo.get("endDate");
				String calendarTitle = (String) calendarListInfo.get("title");

				System.out.println("calendarStartDate : " + calendarStartDate);
				System.out.println("calendarEndDate : " + calendarEndDate);

				String realCalendarStartDate = calendarStartDate.split("T")[0].toString();
				String realCalendarEndDate = calendarEndDate.split("T")[0].toString();
				
				System.out.println("startDate : " + realCalendarStartDate);
				System.out.println("EndDate : " + realCalendarEndDate);
				
				Map<String, Object> map = new HashMap<>();
				map.put("title", calendarTitle);
				map.put("startDate", realCalendarStartDate);
				map.put("endDate", realCalendarEndDate);
				map.put("memberNo", memberNo);
				map.put("memberName", memberName);
				
				result = mapper.insertScheduleByMember(map);
				
			}
		}
			
		if(result > 0) {
			return 1;
		} else {
			return 0;
		}
		
	}

	@Override
	public List<MemberDTO> selectAllMemberList() {

		List<MemberDTO> selectAllMemberList = mapper.selectAllMemberList();
		
		return selectAllMemberList;
	}

//	@Override
//	public List<ScheduleDTO> selectCalendarListForSelectBox(int memberNoForMove) {
//		
//		List<ScheduleDTO> totalCalendarList = null;
//		List<ScheduleDTO> calendarList = null;
//		
//		System.out.println("서비스는 올는데");
//		totalCalendarList = mapper.selectProjectCalendarListForSelectBox(memberNoForMove);
//		System.out.println("토탈조차 안되는건가");
//		calendarList = mapper.selectCalendarListForSelectBox(memberNoForMove);
//		System.out.println("여기가 안나온느건가?");
//		totalCalendarList.addAll(calendarList);
//		
//		System.out.println("여기 잘 이거 나와야함!!! 나옴? : " + totalCalendarList);
//		
//		return totalCalendarList;
//		
//		
//	}



	







}


