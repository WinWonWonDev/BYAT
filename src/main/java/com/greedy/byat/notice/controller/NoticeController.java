package com.greedy.byat.notice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.byat.common.exception.notice.NoticeConfirmRemoveException;
import com.greedy.byat.common.exception.notice.NoticeModifySettingException;
import com.greedy.byat.common.exception.notice.NoticeRemoveException;
import com.greedy.byat.common.exception.notice.NoticeUpdateStatusExcepction;
import com.greedy.byat.notice.model.dto.NoticeDTO;
import com.greedy.byat.notice.model.dto.NoticeSettingDTO;
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
	public ModelAndView modifyNoticeStatus(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws NoticeUpdateStatusExcepction {
		
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
	
	@GetMapping("noticesetting")
	public ModelAndView selectNoticeSetting(ModelAndView mv, HttpServletRequest request) {
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		NoticeSettingDTO noticeSetting = noticeService.selectNoticeSetting(no);
		
		mv.addObject("noticeSetting", noticeSetting);
		mv.setViewName("/notice/noticeSetting");
		
		return mv;
	}
	
	@PostMapping("modifyNoticeSetting")
	public ModelAndView modifyNoticeSetting(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws NoticeModifySettingException {

		response.setContentType("application/json; charset=UTF-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		NoticeSettingDTO noticeSetting = new NoticeSettingDTO();
		noticeSetting.setNo(no);
		
		String projectSetting = request.getParameter("projectSetting");
		String sprintSetting = request.getParameter("sprintSetting");
		String backlogSetting = request.getParameter("backlogSetting");
		String issueSetting = request.getParameter("issueSetting");
		String meetinglogSetting = request.getParameter("meetinglogSetting");
		String calendarSetting = request.getParameter("calendarSetting");
		
		if(projectSetting != null) {
			if("true".equals(projectSetting)) {
				noticeSetting.setProjectSetting("Y");
			} else {
				noticeSetting.setProjectSetting("N");
			}
		}
		
		if(sprintSetting != null) {
			if("true".equals(sprintSetting)) {
				noticeSetting.setSprintSetting("Y");
			} else {
				noticeSetting.setSprintSetting("N");
			}
		}
		
		if(backlogSetting != null) {
			if("true".equals(backlogSetting)) {
				noticeSetting.setBacklogSetting("Y");
			} else {
				noticeSetting.setBacklogSetting("N");
			}
		}
		
		if(issueSetting != null) {
			if("true".equals(issueSetting)) {
				noticeSetting.setIssueSetting("Y");
			} else {
				noticeSetting.setIssueSetting("N");
			}
		}
		
		if(meetinglogSetting != null) {
			if("true".equals(meetinglogSetting)) {
				noticeSetting.setMeetinglogSetting("Y");
			} else {
				noticeSetting.setMeetinglogSetting("N");
			}
		}
		
		if(calendarSetting != null) {
			if("true".equals(calendarSetting)) {
				noticeSetting.setCalendarSetting("Y");
			} else {
				noticeSetting.setCalendarSetting("N");
			}
		}
		
		noticeService.updateNoticeSetting(noticeSetting);
		
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	@RequestMapping("listall")
	public String selectNoticeListAll(Model model, HttpServletRequest request) throws JsonProcessingException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		List<NoticeDTO> noticeList = noticeService.selectNoticeList(no);
		
		model.addAttribute("noticeList", noticeList);
		
		return "/notice/listAll";
	}
	
	@PostMapping("changestatus")
	public ModelAndView changeNoticeStatus(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws NoticeUpdateStatusExcepction {
		
		response.setContentType("application/json; charset=UTF-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		int code = Integer.parseInt(request.getParameter("code"));
		String status = request.getParameter("noticeStatus");
		
		NoticeDTO notice = new NoticeDTO(); 
		notice.setNo(no); 
		notice.setCode(code);
		notice.setStatus(status);

		noticeService.chagenNoticeStatus(notice);
		
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	@PostMapping("removenotice")
	public ModelAndView removeNotice(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws NoticeModifySettingException, NoticeRemoveException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		int code = Integer.parseInt(request.getParameter("code"));
		
		NoticeDTO notice = new NoticeDTO(); 
		notice.setNo(no); 
		notice.setCode(code);

		noticeService.deleteNotice(notice);
		
		mv.setViewName("jsonView");
		
		return mv;
	}
	
}
