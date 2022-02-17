package com.greedy.byat.sprint.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.byat.sprint.model.dto.SprintDTO;
import com.greedy.byat.sprint.model.service.SprintService;

@Controller
@RequestMapping("/sprint")
public class SprintController {

	private final SprintService sprintService;
	
	@Autowired
	public SprintController(SprintService sprintService) {
		this.sprintService = sprintService;
	}
	
	@GetMapping("/list")
	public ModelAndView selectSprintList(HttpServletRequest request, ModelAndView mv) {
		
		int projectNo = Integer.parseInt(request.getParameter("projectNo"));
		
		List<SprintDTO> sprintList = sprintService.selectSprintList(projectNo);
		
		mv.addObject("sprintList", sprintList);
		mv.setViewName("/sprint/list");
		
		return mv;
	}
}
