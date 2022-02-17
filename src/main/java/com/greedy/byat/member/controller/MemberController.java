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
			, HttpServletRequest request, RedirectAttributes rttr, HttpSession session) throws LoginFailedException {
		
		System.out.println("넘버들오냐 ? : "  + member);
		/* 초기 계정인 경우 */
		//넘어온 멤버의 phone && 이메일이 null이면 초기계정일 거니까 
		if(("Y").equals(member.getInitPwdYN())) {
			
			model.addAttribute("loginMember", memberService.initLogin(member));
			
			return "redirect:/member/firstLoginSettingModal";
			
		} else {
		
			/* 초기 계정이 아닌 경우 */
			model.addAttribute("loginMember", memberService.login(member));
			
//		     HttpSession session2 =  request.getSession();
//	        MemberDTO memberSession = (MemberDTO) session.getAttribute("loginMember");
		        
	        if(model.getAttribute("loginMemeber") != null) {
	        	
	        	rttr.addFlashAttribute("message","로그인 성공!");
	        	return "redirect:/home";
	        } else {
	        	rttr.addFlashAttribute("message", "세션엄슴;");
	        	return "redirect:/member/login";
	        }
			
			
	
			
		}
	}
}
