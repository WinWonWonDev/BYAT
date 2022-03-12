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

   /**
    * 메소드 selectCalendarList에 관한 문서화 주석
    * @ param MemberDTO loginMember : 현재 로그인 한 멤버의 세션을 MemberDTO형식으로 변환하여 값을 전달하는 파라미터입니다. 
    * @ return : permitCode로 인해 나뉘어진 경우에 수에 따라 List<ScheduleDTO> totalCalendarList를 반환합니다. 
    */
	@Override
	public List<ScheduleDTO> selectCalendarList(MemberDTO loginMember) {
		
		List<ScheduleDTO> totalCalendarList = null;
		List<ScheduleDTO> calendarList = null;
		
		int memberNo = loginMember.getNo();
		int permitCode = loginMember.getPermitCode();
		
		if(permitCode == 1) { 
			totalCalendarList = mapper.selectCalendarListByAdmin(memberNo);
			
		} else { 
			totalCalendarList = mapper.selectProjectListByMemberOne(memberNo);
			calendarList = mapper.selectCalendarListByMemberOne(memberNo);

			totalCalendarList.addAll(calendarList);
		}
		
		return totalCalendarList;
	}

   /**
    * 메소드 registSchedule에 관한 문서화 주석
    * @ param List<Map<String, Object>> calendarList : 캘린더의 제목, 시작/종료일, 작성자 등의 값을 mapper로 한번에 전달하기 위한 파라미터입니다. 
    * @ param MemberDTO loginMember : 현재 접속한(세션) 멤버의 넘버, 이름 등이 MemberDTO형식으로 담긴 파라미터입니다.  
    * @ return : 캘린더 일정 등록 성공 여부를 담은 결과 값을 int result에 담아 return합니다. 
    */
	@Override
	public int registSchedule(List<Map<String, Object>> calendarList, MemberDTO loginMember) {
		
		int memberNo = loginMember.getNo();
		int deleteAllCalendar = 0;
		int result = 0;
		String memberName = loginMember.getName();
			
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

				String realCalendarStartDate = calendarStartDate.split("T")[0].toString();
				String realCalendarEndDate = calendarEndDate.split("T")[0].toString();
				
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


   /**
    * 메소드 selectAllMemberList에 관한 문서화 주석
    * @ return : selectBox에 모든 멤버를 담아주기 위해 모든 멤버의 정보를 조회하는 selectAllMemberList의 값을 반환합니다.
    */
	@Override
	public List<MemberDTO> selectAllMemberList() {

		List<MemberDTO> selectAllMemberList = mapper.selectAllMemberList();
		
		return selectAllMemberList;
	}
}


