package com.greedy.byat.management.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.byat.management.model.dto.ManagementDTO;
import com.greedy.byat.management.model.service.ManagementService;
import com.greedy.byat.member.model.dto.MemberDTO;

@Controller
@RequestMapping("/management")
public class ManagementController {

	private final ManagementService managementService;
	
	@Autowired
	public ManagementController(ManagementService managementService) {
		
		this.managementService = managementService;
	}
	
	@GetMapping("list")
	public ModelAndView selectManagementList(HttpServletRequest request, ModelAndView mv) {
		
		int totalCount = managementService.selectTotalCount();
		
		List<ManagementDTO> managementList = managementService.selectManagementList();

		mv.addObject("managementList", managementList);
		mv.setViewName("/management/management");
		
		System.out.println("값 : " + managementList);
		
		return mv;
	}
	
	@PostMapping("regist")
	public String registManagement(HttpServletRequest request, Model model, MemberDTO member, RedirectAttributes rttr, String managementRoleforCreate) {
			
		int result = managementService.registManagement(member, managementRoleforCreate);
		
		if(result > 0) {
			rttr.addFlashAttribute("message", "회원 계정 등록 성공!");
		} else {
			rttr.addFlashAttribute("message", "회원 계정 등록 실패! 다시 시도해주세요!");
		}
		
		return "redirect:/management/list";
		
	}
}
