package com.greedy.byat.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greedy.byat.notice.model.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	private final NoticeService noticeService;
	
	@Autowired
	public NoticeController(NoticeService noticeService) {
		
		this.noticeService = noticeService;
	}
	
	
}
