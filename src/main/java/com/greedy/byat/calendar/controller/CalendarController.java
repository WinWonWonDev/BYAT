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

/** 
* <pre>
* Class : CalendarController
* Comment : Calendar관련 메소드들을 모아놓기 위한 Controller
* History
* 2022/02/27 (이소현) 처음 작성, 캘린더 목록 조회
* 2022/02/28 (이소현) 캘린더 목록 조회, 일정 생성(일반 멤버/관리자의 경우)
* 2022/03/01 (이소현) 캘린더 목록 조회, 일정 생성(시작날짜가 종료날짜보다 느린 경우 수정)
* 2022/03/02 (이소현) 캘린더 목록 조회(관리자/PM/일반 멤버의 경우), 일정 수정, 삭제 
* 2022/03/03 (이소현) 캘린더 목록 조회(jsp와 연동), 일정 삭제  
* 2022/03/04 (이소현) 캘린더 api 파악  
* 2022/03/05 (이소현) fullcalendar에 google캘린더 api 적용 및 연동 
* 2022/03/06 (이소현) fullcalendar 조회, 생성 수정
* 2022/03/07 (이소현) fullcalendar 목록 조회(json문자열로 넘어노는 에러 수정) 
* 2022/03/08 (이소현) fullcalendar 관리자의 경우 멤버 검색 및 캘린더 조회  
* </pre>
* @version 10
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
	
   /**
    * 메소드 selectCalendarList에 관한 문서화 주석
    * @param HttpServletRequest request 현재 로그인 한 멤버의 세션을 가져오고, selectBox로 선택된 멤버의 no를 getParameter로 가져오기 위한 파라미터 
    * @return calendar폴더 내의 calendar.jsp로 return 
    */
	@RequestMapping(value="/list", produces="application/json; charset=UTF-8", method=RequestMethod.GET)
	public String selectCalendarList(HttpServletRequest request) {
		
		List<ScheduleDTO> calendarList = null;
		MemberDTO loginMember = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		String memberNoForSelectorBox = request.getParameter("no");
		
		if(memberNoForSelectorBox != null) { 
			MemberDTO selectedMember = new MemberDTO();
			selectedMember.setNo(Integer.parseInt(memberNoForSelectorBox));
			calendarList = calendarService.selectCalendarList(selectedMember);
			request.setAttribute("memberNos", selectedMember.getNo());
			
		} else {
			calendarList = calendarService.selectCalendarList(loginMember);
			request.setAttribute("memberNos", loginMember.getNo());
			
		}
		request.setAttribute("calendarList", calendarList);
		request.setAttribute("loginMemberForDelete", loginMember); 
		
		return "/calendar/calendar";
	}
	
   /**
    * 메소드 registSchedule에 관한 문서화 주석
    * @param HttpServletRequest request 현재 로그인 한 멤버의 세션을 가져오고, getParamenter를 통해 'alldate'의 값을 가져오기 위한 파라미터
    * @param RedirectAttributes rttr addFlashAttribute로 메시지를 1회 출력하기 위한 파라미터
    * @return 캘린더 일정 등록 성공 여부를 담은 결과 값을 int result에 담아 return
    */
	@PostMapping(value="regist", produces="application/json; charset=UTF-8")
	@ResponseBody
	public int registSchedule(HttpServletRequest request, RedirectAttributes rttr) {
		
		String allData = request.getParameter("alldata"); 
		
		MemberDTO loginMember = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		
		List<Map<String, Object>> calendarList = new ArrayList<>();
		List<ScheduleDTO> calednarListByDTO = null;
		
		calendarList = JSONArray.fromObject(allData);
		
		int result = calendarService.registSchedule(calendarList, loginMember);
		
		if(result > 0) {
			rttr.addFlashAttribute("message", "일정 등록 성공!");
			return 1;
		} else {
			rttr.addFlashAttribute("message", "일정 등록 실패! 다시 시도해주세요!");
			return 0;
		}
	}

   /**
    * 메소드 selectAllMemberForSelectBox에 관한 문서화 주석
    * @return selectBox에 모든 멤버를 담아주기 위해 모든 멤버의 정보를 조회하는 selectAllMemberList의 값을 return
    */
	@GetMapping("/selectallmember")
	@ResponseBody
	public List<MemberDTO> selectAllMemberForSelectBox() {
		
		List<MemberDTO> selectAllMemberList = calendarService.selectAllMemberList();
		
		return selectAllMemberList;
	}
	
}
