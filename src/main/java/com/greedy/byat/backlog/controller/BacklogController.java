package com.greedy.byat.backlog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.byat.backlog.model.dto.BacklogDTO;
import com.greedy.byat.backlog.model.service.BacklogService;
import com.greedy.byat.member.model.dto.MemberDTO;

@Controller
@RequestMapping("/backlog")
public class BacklogController {

	private final BacklogService backlogService;
	
	@Autowired
	public BacklogController(BacklogService backlogService) {
		
		this.backlogService = backlogService;
	}
	
//	@RequestMapping("/")
//	public String selectAllBacklogList(HttpServletRequest request, Model model) {
//		
//		int projectCode = Integer.parseInt(request.getParameter("projectCode"));
//		
//		List<BacklogDTO> backlogList = backlogService.selectBacklogList(projectCode);
//		
//		System.out.println("backlogList : " + backlogList);
//		
//		model.addAttribute("backlogList", backlogList);
//		
//		return "/sprint/list";
//	}
	
	@RequestMapping("/regist")
	public String registBacklog(@ModelAttribute BacklogDTO backlog, HttpServletRequest request, RedirectAttributes rttr) {
		
		MemberDTO writer = (MemberDTO) request.getSession().getAttribute("loginMember");
		int projectCode = Integer.parseInt(request.getParameter("projectCode"));
		
		System.out.println("writer : " + writer);
		System.out.println("projectCode : " + projectCode);
		
		backlog.setWriter(writer.getName());
		backlog.setProjectCode(projectCode);
		
		String message = backlogService.registBacklog(backlog);
		
		rttr.addFlashAttribute("message", message);
		
		return "redirect:/sprint/list?code=" + projectCode;
	}
	
	@PostMapping("/modify")
	public String modifyBacklog(@ModelAttribute BacklogDTO backlog, HttpServletRequest request, RedirectAttributes rttr) {
		
		String message = backlogService.modifyBacklog(backlog);
		
		rttr.addFlashAttribute("message", message);
		
		return "redirect:/sprint/list?code=" + backlog.getProjectCode();
	}
	
	@GetMapping("/remove")
	public String removeBacklog(HttpServletRequest request, RedirectAttributes rttr) {
		
		int code = Integer.parseInt(request.getParameter("code"));
		int projectCode = Integer.parseInt(request.getParameter("projectCode"));
		
		String message = backlogService.removeBacklog(code);
		
		rttr.addFlashAttribute("message", message);
		
		return "redirect:/sprint/list?code=" + projectCode;
	}
}
