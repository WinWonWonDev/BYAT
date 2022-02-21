package com.greedy.byat.backlog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.byat.backlog.model.dto.BacklogDTO;
import com.greedy.byat.backlog.model.service.BacklogService;

@Controller
@RequestMapping("/sprint/backlog")
public class BacklogController {

	private final BacklogService backlogService;
	
	@Autowired
	public BacklogController(BacklogService backlogService) {
		
		this.backlogService = backlogService;
	}
	
	@RequestMapping("/list")
	public ModelAndView backlogSelectList(HttpServletRequest request, ModelAndView mv) {
		
		int projectCode = Integer.parseInt(request.getParameter("projectCode"));
		
		List<BacklogDTO> backlogList = backlogService.selectBacklogList(projectCode);
		
		System.out.println("backlogList : " + backlogList);
		
		return mv;
	}
	
}
