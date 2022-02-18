package com.greedy.byat.sprint.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.byat.common.exception.sprint.RegistSprintException;
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
		
		System.out.println(projectCode);
		
		List<SprintDTO> sprintList = sprintService.selectSprintList(projectCode);
		
		System.out.println(sprintList);
		
		mv.addObject("sprintList", sprintList);
		mv.setViewName("/sprint/list");
		
		return mv;
	}
	
//	@GetMapping("/searchmanager")
//	public String searchSprintManager() {
//		
//	}
//	
	@PostMapping("/regist")
	public String registSprint(@ModelAttribute SprintDTO sprint, HttpServletRequest request, RedirectAttributes rttr)throws RegistSprintException {
		
		int projectCode = Integer.parseInt(request.getParameter("code"));
		
		sprint.setProjectCode(projectCode);
		
		int result = sprintService.registSprint(projectCode, sprint);
		
		if(result > 0) {
			
			rttr.addFlashAttribute("message", "스픤트 생성 성ㄱㅇ");
			
			return "redirect:/sprint/list";

		} else {
			
			rttr.addFlashAttribute("message", "스픤트 생성 ㅣ패");

			return "redirect:/sprint/list";
		}
		
	}
}
