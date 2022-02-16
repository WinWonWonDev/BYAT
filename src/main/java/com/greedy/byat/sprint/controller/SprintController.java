package com.greedy.byat.sprint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greedy.byat.sprint.model.service.SprintService;

@Controller
@RequestMapping("/sprint")
public class SprintController {

	private final SprintService sprintService;
	
	@Autowired
	public SprintController(SprintService sprintService) {
		
		this.sprintService = sprintService;
	}
	
	
}
