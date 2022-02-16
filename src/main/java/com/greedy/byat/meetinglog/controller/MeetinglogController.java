package com.greedy.byat.meetinglog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greedy.byat.meetinglog.model.service.MeetinglogService;

@Controller
@RequestMapping("/meetinglog")
public class MeetinglogController {

	private final MeetinglogService meetinglogService;
	
	@Autowired
	public MeetinglogController(MeetinglogService meetinglogService) {
		
		this.meetinglogService = meetinglogService;
	}
	
	
}
