package com.greedy.byat.mytask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greedy.byat.mytask.model.service.MyTaskService;

@Controller
@RequestMapping("/mytask")
public class MyTaskController {

	private final MyTaskService mytaskService;
	
	@Autowired
	public MyTaskController(MyTaskService mytaskService) {
		
		this.mytaskService = mytaskService;
	}
	
	
}
