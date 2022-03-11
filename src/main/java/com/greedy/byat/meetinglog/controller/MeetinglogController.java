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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	  public String selectMeetinglogDetail(HttpServletRequest request) {
		System.out.println("selectMeetinglogDetail : 디데일 조회");

		int meetinglogCode = Integer.parseInt(request.getParameter("meetinglogCode"));
		System.out.println("[selectMeetinglogDetail] meetinglogCode : " + meetinglogCode);
		
		MeetinglogDTO meetinglog = meetinglogService.selectMeetinglogDetail(meetinglogCode);
		  
		System.out.println(meetinglog+ "dgdgdg");
	  
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
	  public String registMeetinglog(HttpServletRequest request, Model model, RedirectAttributes rttr) {
		
		  System.out.println("regist");
		  
		  java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
 
		  MeetinglogDTO meetinglog = new MeetinglogDTO();
		  MemberDTO member = ((MemberDTO) request.getSession().getAttribute("loginMember"));
		  
		  int projectCode = Integer.parseInt(request.getParameter("code"));
		  String title = request.getParameter("meetingLogTitle");
		  String body = request.getParameter("meetingLogDescription");
		  
		  meetinglog.setWritingDate(sqlDate);
		  meetinglog.setDeleteStatus("N");
		  meetinglog.setVersion('1');
		  meetinglog.setTitle(title);
		  meetinglog.setBody(body);
		  meetinglog.setProjectCode(projectCode);
		  meetinglog.setMemberNo(member.getNo());
		  meetinglog.setMemberName(member.getName());
		  
		  System.out.println("regist:meetinglog"  +meetinglog);
		  String message = meetinglogService.registMeetinglog(meetinglog);

		  
		  model.addAttribute("code", projectCode);
		  
		  rttr.addFlashAttribute("message",message);
		  
		  return "redirect:/meetinglog/list?code="+projectCode; 
		  
	  }
	  
	  @PostMapping("/modify")
	  public String modifyMeetinglog(HttpServletRequest request, Model model, RedirectAttributes rttr) {
		
		  MeetinglogDTO meetinglog = new MeetinglogDTO();
		  MemberDTO member =  ((MemberDTO) request.getSession().getAttribute("loginMember"));

		  
		  int projectCode = Integer.parseInt(request.getParameter("code"));
		  
		  String title = request.getParameter("meetingLogDetailTitle");
		  String body = request.getParameter("meetingLogDetailBody");
		  int code = Integer.parseInt(request.getParameter("meetingLogDetailCode"));
		  
		  meetinglog.setBody(body);
		  meetinglog.setCode(code);
		  meetinglog.setTitle(title);
		  meetinglog.setProjectCode(projectCode);
		  meetinglog.setMemberNo(member.getNo());
		  meetinglog.setMemberName(member.getName());
		  System.out.println("modify meetinglog : " + meetinglog);
		  
		  String message= meetinglogService.modifyMeetinglog(meetinglog);
		  
		  System.out.println("modify meetinglog :" + meetinglog);
		  
		  model.addAttribute("code", projectCode);
		  
		  rttr.addFlashAttribute("message",message);
		  
		  return "redirect:/meetinglog/list?code=" + projectCode; 
		  
	  }
	  
	  @PostMapping("/remove")
	  public String removeMeetinglog(HttpServletRequest request, Model model, RedirectAttributes rttr) {
		 
		  int projectCode = Integer.parseInt(request.getParameter("code"));
		  
		  int meetingCode = Integer.parseInt(request.getParameter("meetingCode"));

		  String message  = meetinglogService.removeMeetinglog(meetingCode);
		 
		  
		  
		  model.addAttribute("code", projectCode);
		  
		  rttr.addFlashAttribute("message",message);
		  
		  return "redirect:/meetinglog/list?code=" + projectCode; 
		  
	  }
		  
}
