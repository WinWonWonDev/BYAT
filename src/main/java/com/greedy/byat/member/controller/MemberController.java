package com.greedy.byat.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

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

			rttr.addFlashAttribute("message","로그인 성공!");

			return "redirect:/home";

		} else {

			/* 초기 계정이 아닌 경우 */
			model.addAttribute("loginMember", memberService.login(member));

			rttr.addFlashAttribute("message","로그인 성공!");

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
		
		System.out.println("나오냐 id : " + inputId);
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		return gson.toJson(memberService.selectEmailById(inputId));
		
	}
	
	
	
}
