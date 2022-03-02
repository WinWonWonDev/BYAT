package com.greedy.byat.profile.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.profile.model.dto.ProfileDTO;
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
	
	// Profile 페이지 접속용 메서드
	@GetMapping("/")
	public void profile() {}

	@PostMapping("/modify")
	public String modifyProfile(@ModelAttribute ProfileDTO profile, HttpServletRequest request, RedirectAttributes rttr) {
		
		String name = request.getParameter("textName");
		String email = request.getParameter("textEmail");
		String phone = request.getParameter("textPhone");
		
		rttr.addFlashAttribute("message", "정보 수정 성공 !!!");
		
		return "redirect:/profile";
	}
}
