package com.greedy.byat.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.byat.common.exception.member.LoginFailedException;
import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.member.model.service.MemberService;

@Controller
@RequestMapping("/member")
@SessionAttributes("loginMember")
public class MemberController {

	private final MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		
		this.memberService = memberService;
	}
	
	@GetMapping("/login")
	public void goLogin() {
		
	}
	
	@PostMapping("/login")
	public String loginMember(@ModelAttribute MemberDTO member, Model model
			, RedirectAttributes rttr) throws LoginFailedException {
		
		/* 초기 계정인 경우 */
		if(memberService.selectMember(member).equals("Y")) {
			
			model.addAttribute("loginMember", memberService.initLogin(member));
			
			rttr.addFlashAttribute("message","로그인 성공!");

			return "redirect:/home";
			
		} else {
		
			/* 초기 계정이 아닌 경우 */
			model.addAttribute("loginMember", memberService.login(member));
			
        	rttr.addFlashAttribute("message","로그인 성공!");
        	
        	return "redirect:/home";
	        
		}
			
			
	
			
		}
	}
