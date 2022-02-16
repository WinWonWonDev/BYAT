package com.greedy.byat.issue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greedy.byat.issue.model.service.IssueService;

@Controller
@RequestMapping("/issue")
public class IssueController {

	private final IssueService issueService;
	
	@Autowired
	public IssueController(IssueService issueService) {
		
		this.issueService = issueService;
	}
	
	
}
