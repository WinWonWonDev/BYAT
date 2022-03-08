package com.greedy.byat.meetinglog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.byat.meetinglog.model.dto.MeetinglogDTO;
import com.greedy.byat.meetinglog.model.service.MeetinglogService;
import com.greedy.byat.member.model.dto.MemberDTO;

@Controller
@RequestMapping("/meetinglog")
public class MeetinglogController {

	private final MeetinglogService meetinglogService;
	
	@Autowired
	public MeetinglogController(MeetinglogService meetinglogService) {
		
		this.meetinglogService = meetinglogService;
	}
	
	
	  @GetMapping("/list") 
	  public String selectMytask(HttpServletRequest request, Model model) {
		  System.out.println("list 확인");
		  
		  int projectCode = Integer.parseInt(request.getParameter("code"));

		  String projectName = meetinglogService.selectProjectName(projectCode);
		  
		  List<MeetinglogDTO> meetinglogList = meetinglogService.selectMeetinglogList(projectCode);
				  
		  model.addAttribute("code", projectCode);
		  model.addAttribute("projectName", projectName);
		  model.addAttribute("meetinglogList", meetinglogList);
		  
		  System.out.println(meetinglogList+" : meetinglogList selectMytask");
		  
		  return "/meetinglog/list";
	  }


		
	  @PostMapping(value="/detail", produces="application/json; charset=UTF-8" )
	  @ResponseBody
	  public String selectMeetinglogDetail(HttpServletRequest request, Model model ) {
		System.out.println("selectMeetinglogDetail : 디데일 조회");

		int meetinglogCode = Integer.parseInt(request.getParameter("meetinglogCode"));
		System.out.println("[selectMeetinglogDetail] meetinglogCode : " + meetinglogCode);
		
		MeetinglogDTO meetinglog = meetinglogService.selectMeetinglogDetail(meetinglogCode);
		  
		System.out.println(meetinglog);
	  
		model.addAttribute("meetinglog", meetinglog);
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
			
		return gson.toJson(meetinglog);
	  }
	  
	  @PostMapping("/regist") 
	  public String registMeetinglog(HttpServletRequest request, Model model) {
		
		  java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
		  
		  MeetinglogDTO meetinglog = new MeetinglogDTO();
		  MemberDTO member = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		  int projectCode = Integer.parseInt(request.getParameter("code"));

		  String title = request.getParameter("meetingLogTitle");
		  String body = request.getParameter("meetingLogDescription");
		  
		  meetinglog.setTitle(title);
		  meetinglog.setBody(body);
		  meetinglog.setWritingDate(sqlDate);
		  meetinglog.setProjectCode(projectCode);
		  meetinglog.setMemberNo(member.getNo());
		  meetinglog.setMemberName(member.getName());
		  meetinglog.setDeleteStatus("N");
		  
		  int result = meetinglogService.registMeetinglog(meetinglog);

		  System.out.println("gg : " + projectCode );
		  
		  model.addAttribute("code", projectCode);
		  
		  return "redirect:/meetinglog/list?code="+projectCode; 
		  
	  }
	  
	  @PostMapping("/modify")
	  public String modifyMeetinglog(HttpServletRequest request, Model model) {
		
		  MeetinglogDTO meetinglog = new MeetinglogDTO();
		  
		  int projectCode = Integer.parseInt(request.getParameter("code"));
		  
		  String title = request.getParameter("meetingLogDetailTitle");
		  String body = request.getParameter("meetingLogDetailBody");
		  int code = Integer.parseInt(request.getParameter("meetingLogDetailCode"));
		  String memberName = request.getParameter("memberName");
		  
		  meetinglog.setBody(body);
		  meetinglog.setCode(code);
		  meetinglog.setTitle(title);
		  meetinglog.setMemberName(memberName);
		  System.out.println("modify meetinglog : " + meetinglog);
		  
		  int result = meetinglogService.modifyMeetinglog(meetinglog);
		  
		  model.addAttribute("code", projectCode);
		  model.addAttribute("meetinglog", meetinglog);
		  
		  return "redirect:/meetinglog/list?code=" + projectCode; 
		  
	  }
	  
	  @GetMapping("/remove")
	  public String removeMeetinglog(HttpServletRequest request, Model model) {
		 
		  int projectCode = Integer.parseInt(request.getParameter("code"));
		  
		  int code = Integer.parseInt(request.getParameter("meetingLogDetailCode"));

		  
		 // int result  = meetinglogService.removeMeetinglog(meetingLogDetailCode);
		  model.addAttribute("code", projectCode);
		  
		  return "redirect:/meetinglog/list?code=" + projectCode; 
		  
	  }
		  
}
