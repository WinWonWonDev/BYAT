package com.greedy.byat.management.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
		
		List<ManagementDTO> managementList = managementService.selectManagementList();

		mv.addObject("managementList", managementList);
		mv.setViewName("/management/management");
		
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
	
	@PostMapping("modify")
	public String modifyManagement(RedirectAttributes rttr, ManagementDTO management) {

		int result = managementService.modifyManagement(management);
		
		if(result > 0) {
			rttr.addFlashAttribute("message", "회원 계정 수정 성공!");
		} else {
			rttr.addFlashAttribute("message", "회원 계정 수정 실패! 다시 시도해주세요!");
		}
		
		return "redirect:/management/list";
	}

	@GetMapping("remove") 
	public String removeManagement(RedirectAttributes rttr, HttpServletRequest request) {
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		int result = managementService.removeManagement(no);
		
		if(result > 0) {
			rttr.addFlashAttribute("message", "회원 계정 삭제 성공!");
		} else {
			rttr.addFlashAttribute("message", "회원 계정 삭제 실패! 담당중인 프로젝트가 있는 것 같습니다. 조치 후 다시 시도해주세요!");
		}
		
		return "redirect:/management/list";
		
	}
	
	@GetMapping("removedList")
	public ModelAndView selectManagementRemovedList(ModelAndView mv) {
		
		List<ManagementDTO> deletedManagementList = managementService.selectManagementRemovedList();
		
		mv.addObject("deletedManagementList", deletedManagementList );
		mv.setViewName("/management/deletedManagementList");
		
		return mv; 
	}
	
	@GetMapping("restore")
	public String restoreManagement(RedirectAttributes rttr, HttpServletRequest request) {
	
		int no = Integer.parseInt(request.getParameter("no"));
		
		int result = managementService.restoreManagement(no);
		
		if(result > 0) {
			rttr.addFlashAttribute("message", "회원 계정 복구 성공!");
		} else {
			rttr.addFlashAttribute("message", "회원계정 복구 실패! 다시 시도해주세요!");
		}
		
		return "redirect:/management/removedList";
	}

}
