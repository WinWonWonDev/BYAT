package com.greedy.byat.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greedy.byat.calendar.model.service.CalendarService;

@Controller
@RequestMapping("/calendar")
public class CalendarController {

	private final CalendarService calendarService;
	
	@Autowired
	public CalendarController(CalendarService calendarService) {
		
		this.calendarService = calendarService;
	}
	
	
}
