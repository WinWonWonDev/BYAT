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

/**
 * <pre>
 * Class : NoticeController
 * Comment : Notice 관련 메소드를 모아놓은 Controller입니다.
 * History
 * 2022/03/05 (박인근) 처음 작성, 알림 생성, 알림 목록 조회, 알림 조회, 알림 삭제 관련 코드 추가
 * 2022/03/06 (박인근) 알림 전체 목록 조회, 알림 삭제, 알림 설정 관련 코드 추가
 * 2022/03/07 (박인근) 알림 삭제 관련 코드 추가
 * </pre>
 * @version 3
 * @author 박인근
 * see NoticeDTO, NoticeSetting, NoticeService, NoticeServiceImpl, NoticeMapper.java, NoticeMapper.xml, NoticeDTO, MemberDTO
 * */
@Controller
@RequestMapping("/notice")
public class NoticeController {

	/** NoticeService의 메소드를 사용하기 위한 전역 변수로 변경 불가능 하도록 final로 선언 */
	private final NoticeService noticeService;
	
	@Autowired
	public NoticeController(NoticeService noticeService) {
		
		this.noticeService = noticeService;
	}
	
	/**
	 * 메소드 selectNoticeList에 관한 문서화 주석
	 * @ param mv 요청한 view에서 사용할 값들을 담아서 전달하기 위해 사용하는 파라미터
	 * @ param request 요청한 view에서 전달한 값을 사용하기 위한 파라미터
	 * @ param response 응답에 대한 설정을 변경하기 위해 사용하는 파라미터
	 * @ return view에서 사용해야 하는 값들을 담아 반환
	 * @ exception ModelAndView에 담아 보내줄 값의 자료형 변경 시 처리하는 예외
	 * */
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
	
	/**
	 * 메소드 modifyNoticeStatus에 관한 문서화 주석
	 * @ param mv 요청한 view에서 사용할 값들을 담아서 전달하기 위해 사용하는 파라미터
	 * @ param request 요청한 view에서 전달한 값을 사용하기 위한 파라미터
	 * @ param response 응답에 대한 설정을 변경하기 위해 사용하는 파라미터
	 * @ return 요청한 view페이지로 비동기 방식의 jsonView 반환
	 * @ exception 알림의 상태 변경 실패 예외에 대한 처리
	 * */
	@PostMapping("select")
	public ModelAndView modifyNoticeStatus(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws NoticeUpdateStatusExcepction {
		
		response.setContentType("application/json; charset=UTF-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		int code = Integer.parseInt(request.getParameter("code"));
		
		noticeService.updateNoticeStatus(no, code);
		
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	/**
	 * 메소드 RemoveConfirmNotice에 관한 문서화 주석
	 * @ param mv 요청한 view에서 사용할 값들을 담아서 전달하기 위해 사용하는 파라미터
	 * @ param request 요청한 view에서 전달한 값을 사용하기 위한 파라미터
	 * @ param response 응답에 대한 설정을 변경하기 위해 사용하는 파라미터
	 * @ return 요청한 view페이지로 비동기 방식의 jsonView 반환
	 * @ exception 조회한 알림 삭제 실패에 대한 예외 처리
	 * */
	@PostMapping("removeconfirmnotice")
	public ModelAndView RemoveConfirmNotice(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws NoticeConfirmRemoveException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		noticeService.deleteConfirmNotice(no);
		
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	/**
	 * 메소드 selectNoticeSetting에 관한 문서화 주석
	 * @ param mv 요청한 view에서 사용할 값들을 담아서 전달하기 위해 사용하는 파라미터
	 * @ param request 요청한 view에서 전달한 값을 사용하기 위한 파라미터
	 * @ return view에서 사용해야 하는 값들을 담아 반환
	 * */
	@GetMapping("noticesetting")
	public ModelAndView selectNoticeSetting(ModelAndView mv, HttpServletRequest request) {
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		NoticeSettingDTO noticeSetting = noticeService.selectNoticeSetting(no);
		
		mv.addObject("noticeSetting", noticeSetting);
		mv.setViewName("/notice/noticeSetting");
		
		return mv;
	}
	
	/**
	 * 메소드 modifyNoticeSetting에 관한 문서화 주석
	 * @ param mv 요청한 view에서 사용할 값들을 담아서 전달하기 위해 사용하는 파라미터
	 * @ param request 요청한 view에서 전달한 값을 사용하기 위한 파라미터
	 * @ param response 응답에 대한 설정을 변경하기 위해 사용하는 파라미터
	 * @ return 요청한 view페이지로 비동기 방식의 jsonView 반환
	 * @ exception 멤버의 알림 설정 변경 살패에 대한 예외 처리
	 * */
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
	
	/**
	 * 메소드 selectNoticeListAll에 관한 문서화 주석
	 * @ param model 요청한 view에서 사용할 값들을 담아서 전달하기 위해 사용하는 파라미터
	 * @ param request 요청한 view에서 전달한 값을 사용하기 위한 파라미터
	 * @ return 이동할 페이지의 url 반환
	 * */
	@RequestMapping("listall")
	public String selectNoticeListAll(Model model, HttpServletRequest request) {
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		List<NoticeDTO> noticeList = noticeService.selectNoticeList(no);
		
		model.addAttribute("noticeList", noticeList);
		
		return "/notice/listAll";
	}
	
	/**
	 * 메소드 changeNoticeStatus에 관한 문서화 주석
	 * @ param mv 요청한 view에서 사용할 값들을 담아서 전달하기 위해 사용하는 파라미터
	 * @ param request 요청한 view에서 전달한 값을 사용하기 위한 파라미터
	 * @ param response 응답에 대한 설정을 변경하기 위해 사용하는 파라미터
	 * @ return 요청한 view페이지로 비동기 방식의 jsonView 반환
	 * @ exception 멤버의 각각의 알림의 읽음 상태를 반대로 변경시 실패 예외 처리
	 * */
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
	
	/**
	 * 메소드 removeNotice에 관한 문서화 주석
	 * @ param mv 요청한 view에서 사용할 값들을 담아서 전달하기 위해 사용하는 파라미터
	 * @ param request 요청한 view에서 전달한 값을 사용하기 위한 파라미터
	 * @ param response 응답에 대한 설정을 변경하기 위해 사용하는 파라미터
	 * @ return 요청한 view페이지로 비동기 방식의 jsonView 반환
	 * @ exception 삭제한 알림의 상태 변병 실패 예외, 알림 삭제 실패 예외 처리
	 * */
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
