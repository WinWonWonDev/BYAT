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

@Controller
@RequestMapping("/backlog")
public class BacklogController {

	private final BacklogService backlogService;
	
	@Autowired
	public BacklogController(BacklogService backlogService) {
		
		this.backlogService = backlogService;
	}
	
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
	
	@PostMapping("/modify")
	public String modifyBacklog(@ModelAttribute BacklogDTO backlog, HttpServletRequest request, RedirectAttributes rttr) {

		String message = backlogService.modifyBacklog(backlog);
		
		rttr.addFlashAttribute("message", message);
		
		return "redirect:/sprint/list?code=" + backlog.getProjectCode();
	}
	
	@GetMapping("/remove")
	public String removeBacklog(HttpServletRequest request, RedirectAttributes rttr) {
		
		int backlogCode = Integer.parseInt(request.getParameter("backlogCode"));
		int projectCode = Integer.parseInt(request.getParameter("projectCode"));
		
		System.out.println("backlogCode : " + backlogCode + ", projectCode : " + projectCode);
		
		String message = backlogService.removeBacklog(backlogCode);
		
		rttr.addFlashAttribute("message", message);
		
		return "redirect:/sprint/list?code=" + projectCode;
	}
	
	@GetMapping(value="/detail", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String showBacklogDetail(HttpServletRequest request) {
		
		int projectCode = Integer.parseInt(request.getParameter("projectCode"));
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
