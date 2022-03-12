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

/* 
* <pre>
* Class : MemberController
* Comment : Member관련 메소드를 모아놓기 위한 Controller입니다.
* History
* 2022/02/17 이소현  처음 작성
* </pre>
* @version 1.0.0
* @author 이소현
* @see MemberDTO, MemberService, MemberServiceImpl, MemberMapper.java 
*
*/
@Controller
@RequestMapping("/member")
@SessionAttributes("loginMember")
public class MemberController {

	private final MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {

		this.memberService = memberService;
	}

   /*
    * 메소드 goLogin에 관한 문서화 주석
    * @ return : /login로 이동
    */
	@GetMapping("/login")
	public void goLogin() {
		
	}

   /*
    * 메소드 loginMember에 관한 문서화 주석
    * @ param first : MemberDTO 자체를 파라미터로 사용하기 위함입니다.
    * @ param second : addAttributes로 값을 담아 jsp로 전달하기 위함입니다.
    * @ param third : addFlashAttribute로 메시지를 1회 출력하기 위함입니다.
    * @ return result : 로그인 하려는 멤버의 정보를 String 담아 return 합니다. 
    * @ exception LoginFailedException : 로그인 실패 시 발생하는 예외입니다. 
    */
	@PostMapping("/login")
	public String loginMember(@ModelAttribute MemberDTO member, Model model
			, RedirectAttributes rttr, HttpServletRequest request) throws LoginFailedException {

		String result = memberService.selectMember(member, rttr, model, request);
		
		return result; 
		
	}
	
   /*
    * 메소드 emailduplicationCheck에 관한 문서화 주석
    * @ param first : email중복체크를 위한  
    * @ param second : addFlashAttribute로 메시지를 1회 출력하기 위함입니다.
    * @ param third : response 시 json 설정을 하기 위함입니다.
    * @ return return값에 대한 설명
    * @ exception 예외 이유에 대한 설명
    */
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
		
		if(!(newPassword.equals(newPasswordConfirm))) {
			rttr.addFlashAttribute("message", "비밀번호가 일치하지 않습니다! 확인해주세요!");
		} else {
			result = memberService.initialInputInfo(emailAddress, phoneNumber, newPassword, inputNo);
		}
		
		return result;
	}
	
	@GetMapping("/logout")
	public String logout(SessionStatus status, RedirectAttributes rttr) {
		status.setComplete();
		
		rttr.addFlashAttribute("message", "로그아웃 성공!");
		
		return "redirect:/member/login";
	}
	
	
	
}
