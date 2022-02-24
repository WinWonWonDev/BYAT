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
				return "redirect:/home";
				
			} else {
				rttr.addFlashAttribute("message", "로그인 실패! 아이디나 비밀번호를 확인해주세요!");
				return "redirect:/member/login";
			}
			

		} else {

			/* 초기 계정이 아닌 경우 */
			model.addAttribute("loginMember", memberService.login(member));
			
			if(memberService.login(member) != null) {
				rttr.addFlashAttribute("message","로그인 성공!");
				return "redirect:/home";
				
			} else {
				rttr.addFlashAttribute("message", "로그인 실패! 아이디나 비밀번호를 확인해주세요!");
				return "redirect:/member/login";
			}


		}
	}
	
	@GetMapping("emailduplicationcheckforinit")
	@ResponseBody
	public Object emailduplicationCheck(String emailAddress, RedirectAttributes rttr, HttpServletResponse response) {
		
		response.setContentType("application/json; charset=UTF-8");
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		String test = gson.toJson(memberService.emailDuplicationCheck(emailAddress));
		
		return test;
	}
	
	@GetMapping("/registverification")
	@ResponseBody
	public int registVerificationNumber(String emailAddress, String inputId, RedirectAttributes rttr, HttpServletResponse response) throws NotexistEmailException {
		
		response.setContentType("application/json; charset=UTF-8");
		
		int result = 0;
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		result = memberService.registVerificationNumber(emailAddress, inputId);
		
		return result;
		
	}
	

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

	@GetMapping(value="resubmitverificationnum", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String resubmitVerificationNum(String inputId, String emailAddress, HttpServletResponse response, Model model, HttpServletRequest request, RedirectAttributes rttr) throws NotexistEmailException, IOException {
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		return gson.toJson(memberService.selectResubmitVerificationNum(inputId, emailAddress));
		
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
	
	@GetMapping(value="checkverificationforinit", produces="application/json; charset=UTF-8")
	@ResponseBody
	public int matchVerificationNumberForInit(String inputVerificationNum, int inputNo, RedirectAttributes rttr) {
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		int result = memberService.matchVerificationNumberForInit(inputVerificationNum, inputNo);
		
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
	
	@GetMapping(value="initialinputinfo", produces="application/json; charset=UTF-8")
	@ResponseBody
	public int initialInputInfo(String emailAddress, String phoneNumber, String newPassword, String newPasswordConfirm, int inputNo, RedirectAttributes rttr) {

		int result = 0;
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		System.out.println("이메일 : " + emailAddress + "폰 넘버 : " + phoneNumber + " 새 패스워드 : " + newPassword + "새 패스워드 확인 : " + newPasswordConfirm);
		
		if(!(newPassword.equals(newPasswordConfirm))) {
			rttr.addFlashAttribute("message", "비밀번호가 일치하지 않습니다! 확인해주세요!");
		} else {
			result = memberService.initialInputInfo(emailAddress, phoneNumber, newPassword, inputNo);
		}
		
		System.out.println("결과 뭐? : " + result );
		
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
