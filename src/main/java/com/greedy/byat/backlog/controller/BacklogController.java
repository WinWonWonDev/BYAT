package com.greedy.byat.backlog.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.byat.backlog.model.dto.BacklogDTO;
import com.greedy.byat.backlog.model.service.BacklogService;
import com.greedy.byat.member.model.dto.MemberDTO;

/**
 * <pre>
 * Class : BacklogController
 * Comment : "/backlog/*" 요청을 처리하는 Controller
 * History
 * 2021/02/18 (황인수) 처음 작성함
 * 2021/02/21 (황인수) 프론트 연결 시 오류 부분 수정
 * 2021/03/04 (황인수) 백로그 태스크화 구현 메서드 작성
 * </pre>
 * @author 황인수
 * @version 3
 * @see BacklogService, BacklogServiceImpl, BacklogMapper, BacklogDTO
 * */
@Controller
@RequestMapping("/backlog")
public class BacklogController {

	private final BacklogService backlogService;
	
	@Autowired
	public BacklogController(BacklogService backlogService) {
		
		this.backlogService = backlogService;
	}
	
	/**
	 * 메소드 registBacklog에 관한 문서화 주석
	 * @param backlog 삽입할 백로그의 정보 (DTO)
	 * @param request 요청 정보
	 * @param rttr redirect 객체를 전달하기 위한  RedirectAttributes 객체
	 * @return url redirect 해 줄 경로
	 * */
	@RequestMapping("/regist")
	public String registBacklog(@ModelAttribute BacklogDTO backlog, HttpServletRequest request, RedirectAttributes rttr) {
		
		MemberDTO writer = (MemberDTO) request.getSession().getAttribute("loginMember");
		int projectCode = Integer.parseInt(request.getParameter("projectCode"));
		
		backlog.setWriter(writer.getName());
		backlog.setProjectCode(projectCode);
		
		String message = backlogService.registBacklog(backlog);
		
		rttr.addFlashAttribute("message", message);
		
		return "redirect:/sprint/list?code=" + projectCode;
	}
	
	/**
	 * 메소드 modifyBacklog에 관한 문서화 주석
	 * @param backlog 수정하려는 backlog의 정보(DTO)
	 * @param request 요청 정보
	 * @param rttr redirect 객체를 전달하기 위한  RedirectAttributes 객체
	 * @return String redirect 해 줄 경로
	 * */
	@PostMapping("/modify")
	public String modifyBacklog(@ModelAttribute BacklogDTO backlog, HttpServletRequest request, RedirectAttributes rttr) {

		String message = backlogService.modifyBacklog(backlog);
		
		rttr.addFlashAttribute("message", message);
		
		return "redirect:/sprint/list?code=" + backlog.getProjectCode();
	}
	
	/** 
	 * 메소드 removeBacklog에 관한 문서화 주석
	 * @param request 요청 정보
	 * @param rttr redirect 객체를 전달하기 위한  RedirectAttributes 객체
	 * @return String redirect 해 줄 경로
	 * */
	@GetMapping("/remove")
	public String removeBacklog(HttpServletRequest request, RedirectAttributes rttr) {
		
		int backlogCode = Integer.parseInt(request.getParameter("backlogCode"));
		int projectCode = Integer.parseInt(request.getParameter("projectCode"));
		
		String message = backlogService.removeBacklog(backlogCode);
		
		rttr.addFlashAttribute("message", message);
		
		return "redirect:/sprint/list?code=" + projectCode;
	}
	
	/**
	 * 메소드 showBacklogDetail에 관한 문서화 주석
	 * @param request 요청 정보
	 * @return backlog 상세 조회할 백로그의 정보(DTO)
	 * */
	@GetMapping(value="/detail", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String showBacklogDetail(HttpServletRequest request) {
		
		int backlogCode = Integer.parseInt(request.getParameter("backlogCode"));
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		BacklogDTO backlog = backlogService.selectBacklogDetail(backlogCode);
		
		return gson.toJson(backlog);
	}
	
	/**
	 * 메소드 registBacklogTasklize에 관한 주석
	 * @param request 요청 정보
	 * @param rttr redirect 객체를 전달하기 위한  RedirectAttributes 객체
	 * @param String redirect 해 줄 경로
	 * */
	@GetMapping("/tasklize")
	public String registBacklogTasklize(HttpServletRequest request, RedirectAttributes rttr) {
		
		int memberNo = ((MemberDTO) request.getSession().getAttribute("loginMember")).getNo();
		
		int backlogCode = Integer.parseInt(request.getParameter("backlogCode"));
		int sprintCode = Integer.parseInt(request.getParameter("sprintCode"));
		int projectCode = Integer.parseInt(request.getParameter("projectCode"));
		

		HashMap<String, Integer> infoMap = new HashMap<>();
		infoMap.put("backlogCode", backlogCode);
		infoMap.put("sprintCode", sprintCode);
		infoMap.put("projectCode", projectCode);
		infoMap.put("memberNo", memberNo);
		
		String message = backlogService.registBacklogTasklize(infoMap);
		
		rttr.addFlashAttribute("message", message);
		
		return "redirect:/sprint/list?code=" + projectCode;
	}
}
