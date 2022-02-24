package com.greedy.byat.backlog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.byat.backlog.model.dto.BacklogDTO;
import com.greedy.byat.backlog.model.service.BacklogService;

@Controller
@RequestMapping("/sprint/list")
public class BacklogController {

	private final BacklogService backlogService;
	
	@Autowired
	public BacklogController(BacklogService backlogService) {
		
		this.backlogService = backlogService;
	}
	
	@RequestMapping("/")
	public String selectAllBacklogList(HttpServletRequest request, Model model) {
		
		int projectCode = Integer.parseInt(request.getParameter("projectCode"));
		
		List<BacklogDTO> backlogList = backlogService.selectBacklogList(projectCode);
		
		System.out.println("backlogList : " + backlogList);
		
		model.addAttribute("backlogList", backlogList);
		
		return "/sprint/list";
	}
	
	@RequestMapping("")
	public void registBacklog() {}
	
}
