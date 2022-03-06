package com.greedy.byat.notice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.byat.common.exception.notice.NoticeConfirmRemoveException;
import com.greedy.byat.common.exception.notice.NoticeUpdateStatus;
import com.greedy.byat.notice.model.dto.NoticeDTO;
import com.greedy.byat.notice.model.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	private final NoticeService noticeService;
	
	@Autowired
	public NoticeController(NoticeService noticeService) {
		
		this.noticeService = noticeService;
	}
	
	@PostMapping("list")
	public ModelAndView selectNoticeList(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		List<NoticeDTO> noticeList = noticeService.selectNoticeList(no);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		mv.addObject("noticeList", objectMapper.writeValueAsString(noticeList));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	@PostMapping("select")
	public ModelAndView modifyNoticeStatus(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws NoticeUpdateStatus {
		
		response.setContentType("application/json; charset=UTF-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		int code = Integer.parseInt(request.getParameter("code"));
		
		noticeService.updateNoticeStatus(no, code);
		
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	@PostMapping("removeconfirmnotice")
	public ModelAndView RemoveConfirmNotice(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws NoticeConfirmRemoveException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		noticeService.deleteConfirmNotice(no);
		
		mv.setViewName("jsonView");
		
		return mv;
	}
	
}
