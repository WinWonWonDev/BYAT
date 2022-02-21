package com.greedy.byat.sprint.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.byat.common.exception.sprint.RegistSprintException;
import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.sprint.model.dto.SprintDTO;
import com.greedy.byat.sprint.model.service.SprintService;

@Controller
@RequestMapping("/sprint")
public class SprintController {

	private final SprintService sprintService;
	
	@Autowired
	public SprintController(SprintService sprintService) {
		this.sprintService = sprintService;
	}
	
	@GetMapping("/list")
	public ModelAndView selectSprintList(HttpServletRequest request, ModelAndView mv) {

		int projectCode = Integer.parseInt(request.getParameter("code"));
		
		System.out.println("list 프로젝트 코드 : " +projectCode);
		
		List<SprintDTO> sprintList = sprintService.selectSprintList(projectCode);
		
		System.out.println(sprintList);
		
		mv.addObject("sprintList", sprintList);
		mv.addObject("code", projectCode);
		mv.setViewName("/sprint/list");
		
		return mv;
	}

	@PostMapping("/regist")
	public String registSprint(@ModelAttribute SprintDTO sprint, HttpServletRequest request, RedirectAttributes rttr) {
		
		MemberDTO writer = (MemberDTO) request.getSession().getAttribute("loginMember");
		int projectCode = Integer.parseInt(request.getParameter("code"));

		System.out.println("writer : " + writer);
		System.out.println("projectCode : " + projectCode);
		
		sprint.setProjectCode(projectCode);
		sprint.setWriter(writer.getName());
		
		sprintService.registSprint(sprint);
		
		rttr.addFlashAttribute("message", "스프린트 생성 성공!");
		
		return "redirect:/sprint/list?code="+projectCode;
	}
	
	@GetMapping(value = "/modify", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String selectSprint(HttpServletRequest request) throws JsonProcessingException {
		
		int sprintCode = Integer.parseInt(request.getParameter("sprintCode"));
		
		System.out.println("내가 바로 스프린트 코드 : " + sprintCode);
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		SprintDTO sprint = sprintService.selectSprint(sprintCode);
		
		return gson.toJson(sprint);
	}
	
	@GetMapping("/remove")
	public String removeSprint(HttpServletRequest request, RedirectAttributes rttr) {
		
		int projectCode = Integer.parseInt(request.getParameter("projectCode"));
		int sprintCode = Integer.parseInt(request.getParameter("sprintCode"));
		
		System.out.println("프로젝토 꼬드 : " + projectCode);
		System.out.println("스뿌린뜨 꼬드 : " + sprintCode);
		
		sprintService.deleteSprint(sprintCode);
		
		rttr.addFlashAttribute("message", "스프린트 삭제 완료");
		
		return "redirect:/sprint/list?code=" + projectCode;
	}
}
