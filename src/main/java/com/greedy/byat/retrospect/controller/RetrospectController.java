package com.greedy.byat.retrospect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greedy.byat.retrospect.model.service.RetrospectService;

@Controller
@RequestMapping("/retrospect")
public class RetrospectController {

	private final RetrospectService retrospectService;
	
	@Autowired
	public RetrospectController(RetrospectService retrospectService) {
		
		this.retrospectService = retrospectService;
	}
	
	
}
