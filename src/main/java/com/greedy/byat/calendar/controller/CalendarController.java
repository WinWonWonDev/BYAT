package com.greedy.byat.calendar.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.byat.calendar.model.dto.ScheduleDTO;
import com.greedy.byat.calendar.model.service.CalendarService;
import com.greedy.byat.member.model.dto.MemberDTO;

import net.sf.json.JSONArray;

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
	
	
	@RequestMapping(value="/list", produces="application/json; charset=UTF-8", method=RequestMethod.GET)
	public String selectCalendarList(Model model, HttpServletRequest request) {
		
		MemberDTO loginMember = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		
		List<ScheduleDTO> calendarList = calendarService.selectCalendarList(loginMember);
		
		request.setAttribute("calendarList", calendarList);
		
		return "/calendar/calendar";
		
	}
	
	@PostMapping(value="regist", produces="application/json; charset=UTF-8")
	@ResponseBody
	public int registSchedule(HttpServletRequest request, RedirectAttributes rttr, @ModelAttribute ScheduleDTO schedule, MemberDTO member) throws ParseException {
		
		String allData = request.getParameter("alldata"); //json으로 받았으니까 그거를 
		
		MemberDTO loginMember = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		System.out.println("ㄴ옴?" + loginMember);
		
		
		List<Map<String, Object>> calendarList = new ArrayList<>();
		List<ScheduleDTO> calednarListByDTO = null;
		
		calendarList = JSONArray.fromObject(allData);
		
		int result = calendarService.registSchedule(calendarList, loginMember);
		
		System.out.println("result: " + result); //저장되씅면 result에 1 , 저장안됬으면 result 2
		
		
		if(result > 0) {
			rttr.addFlashAttribute("message", "일정 등록 성공!");
			return 1;
		} else {
			rttr.addFlashAttribute("message", "일정 등록 실패! 다시 시도해주세요!");
			return 0;
		}
		
	}

	@GetMapping("/selectallmember")
	@ResponseBody
	public List<MemberDTO> selectAllMemberForSelectBox() {
		
		List<MemberDTO> selectAllMemberList = calendarService.selectAllMemberList();
		
		System.out.println(selectAllMemberList);
		
		return selectAllMemberList;
	}
	
	@GetMapping(value="/movecalendarbymember", produces="application/json; charset=UTF-8")
	public String moveCalendarByMember(Model model, HttpServletRequest request, int memberNoForMove) {

		System.out.println("오냐 ? : " + memberNoForMove);
		List<ScheduleDTO> calendarList = calendarService.selectCalendarListForSelectBox(memberNoForMove);

		System.out.println("컨트롤러 그거 가따온거 되냐?");
		
		model.addAttribute("calendarList", calendarList);
		
		System.out.println("calendarList : " + calendarList);
		
		return "/calendar/calendar";
		
		
	}
	
}
