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
			System.out.println("맴버쪽만? 프로젝트 말ㄱ : " + calendarList);
			System.out.println("프로젝트만 : " + totalCalendarList);
			
			
			totalCalendarList.addAll(calendarList);
		}
		
		System.out.println("음 많이 잘나오냐? : " + totalCalendarList);
		return totalCalendarList;
	}

	@Override
	public int registSchedule(List<Map<String, Object>> calendarList, MemberDTO loginMember) throws ParseException {
		
		System.out.println("서비스 impt 옴?");
		
		int memberNo = loginMember.getNo();
		String memberName = loginMember.getName();
		int deleteAllCalendar = 0;
		int result = 0;
			
		// 만약에 수정하고 regist 할 때 select에 wrtier가 본인꺼가 아니거나  수정하고 하면 걔한테는 바뀌고(일단 ㄱㄷ)
		
		List<Integer> memberNumbers = mapper.selectAllMemberNo();
		
		
		if(loginMember.getPermitCode() == 1) {
			
			deleteAllCalendar = mapper.deleteAllCalendar(memberNo);
			// string형으로 들어가니까 각자 
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
						//ㄱㄷㄱㄷ
						map.put("memberNo", memberNumbers.get(i));
						result = mapper.insertScheduleByAdmin(map);
					}
					
				}
			
		} else if(loginMember.getPermitCode() == 3 || loginMember.getPermitCode() == 2) {

			deleteAllCalendar = mapper.deleteAllCalendar(memberNo);
			
			System.out.println("금 여기 들어와야되잔항?");
			// string형으로 들어가니까 각자 
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



	







}


