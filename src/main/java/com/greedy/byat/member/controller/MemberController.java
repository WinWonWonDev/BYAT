package com.greedy.byat.member.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.byat.common.exception.member.LoginFailedException;
import com.greedy.byat.common.exception.member.NotexistEmailException;
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
			
			if(memberService.initLogin(member) != null) {
				rttr.addFlashAttribute("message","로그인 성공!");
				
			} else {
				rttr.addFlashAttribute("message", "로그인 실패! 아이디나 비밀번호를 확인해주세요!");
			}
			
			return "redirect:/home";

		} else {

			/* 초기 계정이 아닌 경우 */
			model.addAttribute("loginMember", memberService.login(member));
			
			if(memberService.login(member) != null) {
				rttr.addFlashAttribute("message","로그인 성공!");
				
			} else {
				rttr.addFlashAttribute("message", "로그인 실패! 아이디나 비밀번호를 확인해주세요!");
			}

			return "redirect:/home";

		}
	}
	
//	@PostMapping("/emailduplicationCheck")
//	@ResponseBody
//	public String emailduplicationCheck(@RequestParam(required = false)String emailAddress, HttpServletRequest request, Model model) {
//		
//		String result = "사용 가능한 이메일입니다!";
//		
//		int id = Integer.parseInt(request.getParameter("id"));
//		
//		if("".equals(emailAddress)) {
//			result = "이메일을 입력해주세요!";
//		} else if(memberService.emailduplicationCheck(id)){
//			result = "중복된 이메일입니다! 다른 이메일을 입력해주세요!";
//		}
//		
//		model.addAttribute(result);
//		
//		return result;
//	}
//
	@PostMapping(value="selectemail", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String selectemail(String inputId, @ModelAttribute MemberDTO member, HttpServletResponse response, Model model, HttpServletRequest request, RedirectAttributes rttr) throws NotexistEmailException, IOException {
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		
		return gson.toJson(memberService.selectEmailById(inputId));
		
	}
	
	@PostMapping(value="checkverification", produces="application/json; charset=UTF-8")
	@ResponseBody
	public int matchVerificationNumber(String inputVerificationNum, RedirectAttributes rttr) {
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();

		int result = memberService.matchVerificationNumber(inputVerificationNum);
		
		return result;
		
	}
	
	@PostMapping(value="modifypassword", produces="application/json; charset=UTF-8")
	@ResponseBody
	public int modifyMemberPwd(String inputPassword, String confirmPassword, String inputId, HttpServletRequest request, RedirectAttributes rttr) {
		
		int result = 0;
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		
		if(inputPassword.equals(confirmPassword)) {
			result = memberService.modifyMemberPwd(inputPassword, inputId);
			
		} else {
			rttr.addFlashAttribute("message", "비밀번호가 서로 일치하지 않습니다.");
			
		}
		
		return result;
	}
	
	@GetMapping("/moveprofile")
	public String moveProfile() {
		
		
		return "/member/profile";
	}
	
	@GetMapping("/logout")
	public String logout(SessionStatus status, RedirectAttributes rttr) {
		status.setComplete();
		
		rttr.addFlashAttribute("message", "로그아웃 성공!");
		
		return "redirect:/member/login";
	}
	
	
	
}
