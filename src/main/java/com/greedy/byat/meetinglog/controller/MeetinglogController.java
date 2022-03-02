package com.greedy.byat.meetinglog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greedy.byat.meetinglog.model.dto.MeetinglogDTO;
import com.greedy.byat.meetinglog.model.service.MeetinglogService;

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
		 
		  int projectCode = Integer.parseInt(request.getParameter("code"));
	  
		  //List<MeetinglogDTO> meetinglogList = meetinglogService.selectMeetinglogList(projectCode);
		  
		  model.addAttribute("meetinglogList", meetinglogList);
		  
		  return "/meetinglog/list";
	  }


		/*
		 * private void selectMeetinglogList(int projectCode) { // TODO Auto-generated
		 * method stub
		 * 
		 * }
		 */
	 

}
