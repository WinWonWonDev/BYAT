package com.greedy.byat.profile.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.profile.model.service.ProfileService;

@Controller
@RequestMapping("/member/profile")
public class ProfileController {

	private final ProfileService profileService;
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public ProfileController(ProfileService profileService, BCryptPasswordEncoder passwordEncoder) {
		
		this.profileService = profileService;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/modify")
	public String modifyProfile(@ModelAttribute MemberDTO member, HttpServletRequest request, RedirectAttributes rttr) {
		
		String name = request.getParameter("textName");
		String email = request.getParameter("textEmail");
		String phone = request.getParameter("textPhone");
		
		member.setId(((MemberDTO) request.getSession().getAttribute("loginMember")).getId());
		member.setName(name);
		member.setEmail(email);
		member.setPhone(phone);
		
		String message = profileService.modifyProfile(member);
		
		request.getSession().setAttribute("loginMember", member);
		
		rttr.addFlashAttribute("message", message);
		
		return "redirect:/member/profile";
	}
	
	@PostMapping("/modifyPwd")
	public String modifyPassword(@ModelAttribute MemberDTO member, HttpServletRequest request, RedirectAttributes rttr) {
		
		member.setId(((MemberDTO) request.getSession().getAttribute("loginMember")).getId());
		member.setPwd(passwordEncoder.encode(member.getPwd()));
		
		profileService.modifyPassword(member);
		
		return "redirect:/member/profile";
	}
}
