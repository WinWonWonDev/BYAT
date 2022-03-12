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
* Comment : Member관련 메소드를 모아놓은 Controller입니다.
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
    * @ param MemberDTO member : MemberDTO 자체를 파라미터로 사용하기 위함입니다.
    * @ param Model model : addAttributes로 값을 담아 jsp로 전달하기 위함입니다.
    * @ param RedirectAttributes rttr : addFlashAttribute로 메시지를 1회 출력하기 위함입니다.
    * @ param HttpServletRequest request : attachment를 세션에 담기 위함입니다.
    * @ return : 로그인 하려는 멤버의 정보를 String result에 담아 return 합니다. 
    * @ exception : LoginFailedException(로그인 실패 시 발생하는 예외입니다.) 
    */
	@PostMapping("/login")
	public String loginMember(@ModelAttribute MemberDTO member, Model model
			, RedirectAttributes rttr, HttpServletRequest request) throws LoginFailedException {

		String result = memberService.selectMember(member, rttr, model, request);
		
		return result; 
		
	}
	
   /*
    * 메소드 emailduplicationCheck에 관한 문서화 주석
    * @ param String emailAddress : email중복체크를 위한  
    * @ param RedirectAttributes rttr : addFlashAttribute로 메시지를 1회 출력하기 위함입니다.
    * @ param HttpServletResponse response : response 시 json 설정을 하기 위함입니다.
    * @ return : 초기계정의 이메일 중복 체크 결과를 String test에 담아 return 합니다. 
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
	
   /*
    * 메소드 registVerificationNumber에 관한 문서화 주석
    * @ param String emailAddress : DB에 저장된 email과의 비교(중복체크)를 위한 emailAddress입니다. 
    * @ param String inputId : Member Number를 가져오기 위한 Member id가 담긴 inputId입니다.
    * @ param RedirectAttributes rttr : addFlashAttribute로 메시지를 1회 출력하기 위함입니다.
    * @ param HttpServletResponse response : response 시 json 설정을 하기 위함입니다.
    * @ return : 초기계정의 이메일 중복 체크 결과를 String test에 담아 return 합니다. 
    * @ exception : NotexistEmailException(입력한 아이디가 존재하지 않는 경우에 대한 익셉션입니다.)
    */
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
	

   /*
    * 메소드 selectemail에 관한 문서화 주석
    * @ param String inputId : 해당 id를 가진 멤버의 Email과 Number를 가져오기 위한 inputId 입니다.
    * @ return : 해당 이메일에 인증번호 전송결과를 json으로 변환해 String으로 return 합니다. 
    * @ exception : NotexistEmailException(입력한 아이디가 존재하지 않는 경우에 대한 익셉션입니다.)
    */
	@PostMapping(value="selectemail", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String selectemail(String inputId) throws NotexistEmailException {
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		
		return gson.toJson(memberService.selectEmailById(inputId));
		
	}

   /*
    * 메소드 resubmitVerificationNum에 관한 문서화 주석
    * @ param String inputId : 해당 id를 가진 멤버의 Email과 Number를 가져오기 위한 inputId 입니다.
    * @ param String emailAddress : 해당 emailAddress를 가진 멤버의 Email과 Number를 가져오기 위한 inputId 입니다.
    * @ return : 해당 이메일에 인증번호 재전송결과를 json으로 변환해 String으로 return 합니다. 
    * @ exception : NotexistEmailException(입력한 아이디가 존재하지 않는 경우에 대한 익셉션입니다.)
    */
	@GetMapping(value="resubmitverificationnum", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String resubmitVerificationNum(String inputId, String emailAddress) throws NotexistEmailException {
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		return gson.toJson(memberService.selectResubmitVerificationNum(inputId, emailAddress));
		
	}
	
   /*
    * 메소드 matchVerificationNumber에 관한 문서화 주석
    * @ param String inputId : 해당 id를 가진 멤버의 Email과 Number를 가져오기 위한 inputId 입니다.
    * @ param String emailAddress : 해당 emailAddress를 가진 멤버의 Email과 Number를 가져오기 위한 inputId 입니다.
    * @ return : 해당 이메일에 인증번호 재전송결과를 json으로 변환해 String으로 return 합니다. 
    * @ exception : NotexistEmailException(입력한 아이디가 존재하지 않는 경우에 대한 익셉션입니다.)
    */
	@PostMapping(value="checkverification", produces="application/json; charset=UTF-8")
	@ResponseBody
	public int matchVerificationNumber(String inputVerificationNum) {
		
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
