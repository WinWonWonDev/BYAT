package com.greedy.byat.calendar.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.byat.calendar.model.dto.ScheduleDTO;
import com.greedy.byat.calendar.model.service.CalendarService;

/* 
* <pre>
* Class : CalendarController
* Comment : Calendar관련 메소드들을 모아놓기 위한 Controller입니다.
* History
* 2022/02/25 이소현  처음 작성
* </pre>
* @version 1.1.0
* @author 이소현
* @see CalendarService, CalendarServiceImpl, ScheduleDTO, CalendarMapper  
*
*/
@Controller
@RequestMapping("/calendar")
public class CalendarController {

	private final CalendarService calendarService;
	
	
	@Autowired
	public CalendarController(CalendarService calendarService) {
		
		this.calendarService = calendarService;
	}
	
	@GetMapping("list")
	public String selectCalendarList() {
		
		return "/calendar/calendar";
	}
	
	@PostMapping("regist")
	public String registSchedule(HttpServletRequest request, RedirectAttributes rttr, @ModelAttribute ScheduleDTO schedule) {
			
		System.out.println("스케쥴 : " + schedule);
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		int permitCode = Integer.parseInt(request.getParameter("permitCode"));
		String memberName = request.getParameter("memberName");
		
		System.out.println("나오냐 :; " + memberNo + ", " + permitCode + "," + memberName);
		
		Map<String, Object> map = new HashMap<>();
		map.put("memberNo", memberNo);
		map.put("memberName", memberName);
		map.put("permitCode", permitCode);
		map.put("title", schedule.getTitle());
		map.put("startDate", schedule.getStartDate());
		map.put("endDate", schedule.getEndDate());
		map.put("body", schedule.getBody());
		
		System.out.println("map : " + map);
		
		int result = calendarService.registSchedule(map, memberNo, memberName, permitCode);

		if(result > 0) {
			rttr.addFlashAttribute("message", "일정 등록 완료!");
		} else {
			rttr.addFlashAttribute("message", "일정 등록 실패! 다시 시도해 주세요!");
		}
		
		return "redirect:/calendar/list";
	}
	
	
}
