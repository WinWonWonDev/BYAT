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

/**
* <pre>
* Class : MemberController
* Comment : Member관련 메소드를 모아놓은 Controller
* History
* 2022/02/17 (이소현) 처음 작성
* 2022/02/18 (이소현) 멤버 로그인(최초 로그인 시), 비밀번호 찾기 구현 시작
* 2022/02/19 (이소현) 비밀번호 찾기 관련 모달창 작성, 이메일 인증번호 전송 
* 2022/02/20 (이소현) 인증번호 재전송 시 타이머 리셋, 인증번호 일치, 비밀번호 변경 
* 2022/02/21 (이소현) 타이머 기능 수정, 비밀번호 변경, 초기 계정 정보 설정
* 2022/02/22 (이소현) 인증번호 전송
* </pre>
* @version 6
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

   /**
    * 메소드 goLogin에 관한 문서화 주석
    * @return /login로 이동
    */
	@GetMapping("/login")
	public void goLogin() {
		
	}

   /**
    * 메소드 loginMember에 관한 문서화 주석
    * @param MemberDTO member MemberDTO 자체를 파라미터로 사용하기  위한 파라미터
    * @param Model model addAttributes로 값을 담아 jsp로 전달하기  위한 파라미터
    * @param RedirectAttributes rttr addFlashAttribute로 메시지를 1회 출력하기  위한 파라미터
    * @param HttpServletRequest request attachment를 세션에 담기  위한 파라미터
    * @return 로그인 하려는 멤버의 정보를 String result에 담아 return
    * @exception LoginFailedException(로그인 실패 시 발생하는 예외) 
    */
	@PostMapping("/login")
	public String loginMember(@ModelAttribute MemberDTO member, Model model
			, RedirectAttributes rttr, HttpServletRequest request) throws LoginFailedException {

		String result = memberService.selectMember(member, rttr, model, request);
		
		return result; 
		
	}
	
   /**
    * 메소드 emailduplicationCheck에 관한 문서화 주석
    * @param String emailAddress email중복체크를 위한  파라미터
    * @param RedirectAttributes rttr addFlashAttribute로 메시지를 1회 출력하기 위한 파라미터
    * @param HttpServletResponse response response 시 json 설정을 하기 위한 파라미터
    * @return 초기계정의 이메일 중복 체크 결과를 String test에 담아 return 
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
	
   /**
    * 메소드 registVerificationNumber에 관한 문서화 주석
    * @param String emailAddress DB에 저장된 email과의 비교(중복체크)를 위한 emailAddress 
    * @param String inputId Member Number를 가져오기 위한 Member id가 담긴 inputId
    * @param RedirectAttributes rttr addFlashAttribute로 메시지를 1회 출력하기 위한 파리미터
    * @param HttpServletResponse response response 시 json 설정을 하기 위한 파라미터
    * @return 초기계정의 이메일 중복 체크 결과를 String test에 담아 return
    * @exception NotexistEmailException(입력한 아이디가 존재하지 않는 경우에 대한 익셉션)
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
	

   /**
    * 메소드 selectemail에 관한 문서화 주석
    * @param String inputId 해당 id를 가진 멤버의 Email과 Number를 가져오기 위한 inputId
    * @return Service에서 반환 받은 이메일에 인증번호 전송한 결과가 담긴 값을 비동기방식으로 이용하기 위해 가공된 값을 return 
    * @exception NotexistEmailException(입력한 아이디가 존재하지 않는 경우에 대한 익셉션)
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

   /**
    * 메소드 resubmitVerificationNum에 관한 문서화 주석
    * @param String inputId 해당 id를 가진 멤버의 Email과 Number를 가져오기 위한 inputId
    * @param String emailAddress 해당 emailAddress를 가진 멤버의 Email과 Number를 가져오기 위한 inputId
    * @return Service에서 반환 받은 이메일에 인증번호  재전송한 결과가 담긴 값을 비동기방식으로 이용하기 위해 가공된 값을 return
    * @exception NotexistEmailException(입력한 아이디가 존재하지 않는 경우에 대한 익셉션)
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
	
   /**
    * 메소드 matchVerificationNumber에 관한 문서화 주석
    * @param String inputVerificationNum DB에 저장된 인증번호와 비교하기 위한 인증번호 넘버가 담긴 파라미터
    * @return 인증번호 일치 성공 여부를 int result에 담아 return 
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
	
   /**
    * 
    * 메소드 matchVerificationNumberForInit에 관한 문서화 주석
    * @param String inputVerificationNum DB에 저장된 인증번호와 비교하기 위해 입력한 인증번호 넘버가 담긴 파라미터
    * @param int inputNo DB에 저장된 인증번호를 찾기위한 멤버 넘버가 저장된 파라미터
    * @return 최초 로그인 시 DB에 저장된 인증번호가 일치하는 여부에 따른 결과가 int result에 담겨 return 
    */
	@GetMapping(value="checkverificationforinit", produces="application/json; charset=UTF-8")
	@ResponseBody
	public int matchVerificationNumberForInit(String inputVerificationNum, int inputNo) {
		
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
	
   /**
    * 메소드 modifyMemberPwd에 관한 문서화 주석
    * @param String inputPassword 비밀번호 변경을 위해 새로 입력한 비밀번호가 담긴 파라미터
    * @param String confirmPassword 비밀번호 변경을 위해 새로 입력한 비밀번호 확인값이 담긴 파라미터
    * @param String inpuId inputId에 담긴 멤버ID로 멤버 비밀번호를 업데이트 해주기 위해 멤버ID 값을 담은 파라미터
    * @param RedirectAttributes rttr addFlashAttribute로 메시지를 1회 출력하기 위한 파라미터
    * @return 비밀번호 변경 성공 여부에 따른 결과가 int result에 담겨 return. 
    */
	@PostMapping(value="modifypassword", produces="application/json; charset=UTF-8")
	@ResponseBody
	public int modifyMemberPwd(String inputPassword, String confirmPassword, String inputId, RedirectAttributes rttr) {
		
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
	
	
   /**
    * 메소드 initialInputInfo에 관한 문서화 주석
    * @param String newPassword 최초 로그인 시 정보 입력을 위해 새로 입력한 비밀번호가 담긴 파라미터
    * @param String newPasswordConfirm 최초 로그인 시 정보 입력을 위해 새로 입력한 비밀번호 확인값이 담긴 파라미터
    * @param String emailAddress 최초 로그인 시 정보 입력을 위해 새로 입력한 이메일이 담긴 파라미터
    * @param String phoneNumber 최초 로그인 시 정보 입력을 위해 새로 입력한 전화번호가 담긴 파라미터
    * @param RedirectAttributes rttr addFlashAttribute로 메시지를 1회 출력하기 위한 파라미터
    * @return 최초 로그인 시 정보 저장 성공 여부에 따른 결과가 int result에 담겨 return
    */
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
	
	/**
    * 메소드 logout에 관한 문서화 주석
    * @param SessionStatus status loginMember의 세션만료를 위한 파라미터
    * @param RedirectAttributes rttr addFlashAttribute로 메시지를 1회 출력하기 위한 파라미터
    * @return /member/login으로 재요청을 하기 위한 return
    */
	@GetMapping("/logout")
	public String logout(SessionStatus status, RedirectAttributes rttr) {
		status.setComplete();
		
		rttr.addFlashAttribute("message", "로그아웃 성공!");
		
		return "redirect:/member/login";
	}
	
	
	
}
