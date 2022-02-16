package com.greedy.byat.history.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greedy.byat.history.model.service.HistoryService;

@Controller
@RequestMapping("/history")
public class HistoryController {

	private final HistoryService historyService;
	
	@Autowired
	public HistoryController(HistoryService historyService) {
		
		this.historyService = historyService;
	}
	
	
}
