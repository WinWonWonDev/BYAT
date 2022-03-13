package com.greedy.byat.member.model.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.byat.common.exception.member.NotexistEmailException;
import com.greedy.byat.member.model.dao.MemberMapper;
import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.profile.model.dto.AttachmentDTO;

/** 
* <pre>
* Class : MemberServiceImpl
* Comment : MemberController에 필요한 메소드들의 로직을 작성하는 Service
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
* @see MemberDTO, MemberService, MemberController, MemberMapper.java 
*
*/
@Service
public class MemberServiceImpl implements MemberService {

	private final MemberMapper mapper;
	private final BCryptPasswordEncoder passwordEncoder;
	private final JavaMailSender mailSender;
	
	@Autowired
	public MemberServiceImpl(MemberMapper mapper, BCryptPasswordEncoder passwordEncoder, JavaMailSender mailSender) {
		this.mapper = mapper;
		this.passwordEncoder = passwordEncoder;
		this.mailSender = mailSender;
	}

   /**
    * 메소드 selectMember에 관한 문서화 주석
    * @param MemberDTO member MemberDTO 자체를 파라미터로 사용하기 위한 파라미터
    * @param RedirectAttributes rttr addFlashAttribute로 메시지를 1회 출력하기 위한 파라미터
    * @param Model model addAttributes로 값을 담아 jsp로 전달하기 위한 파라미터
    * @param HttpServletRequest request  attachment를 세션에 담기 위한 파라미터.
    * @return if문으로 나눠진 결과에 따라 /member/login 또는 /home으로 redirect
    */
	@Override
	public String selectMember(MemberDTO member, RedirectAttributes rttr, Model model, HttpServletRequest request) {
		
		if(mapper.selectInitPasswordYN(member).equals("Y")) {
			//초기계정인경우
				if(mapper.initLogin(member) != null) {
					
					if(!passwordEncoder.matches(member.getPwd(), mapper.selectEncryptedPwd(member))) {
						rttr.addFlashAttribute("message", "로그인 실패! 아이디나 비밀번호를 확인해주세요!");
						return "redirect:/member/login";
					
					} else {
						model.addAttribute("loginMember", mapper.initLogin(member));
						rttr.addFlashAttribute("message", "로그인 성공!");
						return "redirect:/home";
					}
					
				} else {
					rttr.addFlashAttribute("message", "로그인 실패! 아이디나 비밀번호를 확인해주세요!");
					return "redirect:/member/login";
				}
				
			} else {
			
			/* 초기 계정이 아닌 경우 */
			
			if(mapper.login(member) != null) {
				
					if(!passwordEncoder.matches(member.getPwd(), mapper.selectEncryptedPwd(member))) {
						rttr.addFlashAttribute("message", "로그인 실패! 아이디나 비밀번호를 확인해주세요!");
						return "redirect:/member/login";
						
					} else {
						model.addAttribute("loginMember", mapper.login(member));
						rttr.addFlashAttribute("message","로그인 성공!");

						int memberNo = ((MemberDTO) model.getAttribute("loginMember")).getNo();
						System.out.println(memberNo);
						AttachmentDTO attachment = mapper.selectAttachment(memberNo);
						System.out.println("attachment : " + attachment);
						
						request.getSession().setAttribute("attachment", attachment);
						
						return "redirect:/home";
					}
					
				} else {
					rttr.addFlashAttribute("message", "로그인 실패! 아이디나 비밀번호를 확인해주세요!");
					return "redirect:/member/login";
				}
		
			}
	}

	
   /**
    * 메소드 selectEmailById에 관한 문서화 주석 
    * @param String id 해당 id를 가진 멤버의 Email과 Number를 가져오기 위한 inputId
    * @return 해당 이메일에 인증번호 전송결과 여부에 따른 결과를 int result 담아 return
    * @exception NotexistEmailException(입력한 아이디가 존재하지 않는 경우에 대한 익셉션)
    */
	@Override
	public int selectEmailById(String id) throws NotexistEmailException {
		
		int randomVerificationNum = (int)((Math.random() * 100000) + 10000);
		
		String email = mapper.selectEmailById(id);
		
		int no = mapper.selectMemberNo(id); 
		
		HashMap<String, Integer> map = new HashMap<>();
		map.put("no", no);
		map.put("verification", randomVerificationNum);
		
		int result = mapper.insertverification(map);
		
		if(email != null && result > 0) {
			String subject = "BYAT 비밀번호 찾기용 인증번호 이메일입니다.";
			String content = "BYAT 비밀번호 찾기용 인증번호는 " + randomVerificationNum + "입니다.";
			String from = "byatproject@gmail.com";
			String to = email;
			
			try {
		            MimeMessage mail = mailSender.createMimeMessage();
		            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
		            
		            mailHelper.setFrom(from);
		            mailHelper.setTo(to);
		            mailHelper.setSubject(subject);
		            mailHelper.setText(content, true);
		            mailSender.send(mail);
		            
		        } catch(Exception e) {
		            e.printStackTrace();
		        }
				
		} else {
			throw new NotexistEmailException("입력하신 아이디가 존재하지 않습니다.");
		}
		
		return result;
	}

   /**
    * 메소드 matchVerificationNumber에 관한 문서화 주석
    * @param String inputVerificationNum DB에 저장된 인증번호와 비교하기 위한 인증번호 넘버가 담긴 파라미터
    * @return 인증번호 일치 성공 여부를 int result에 담아 return
    */
	@Override
	public int matchVerificationNumber(String inputVerificationNum) {
		
		int result = 0;
		
		String mathchVerification = mapper.mathchVerificationNumber(inputVerificationNum);
		
		if(mathchVerification != null) { 
			
			result = mapper.updateVerficiation(inputVerificationNum);
		}
		
		return result;
		
	}

   /**
    * 메소드 modifyMemberPwd에 관한 문서화 주석
    * @param String inputPassword 비밀번호 변경을 위해 새로 입력한 비밀번호가 담긴 파라미터
    * @param String inpuId inputId에 담긴 멤버ID로 멤버 비밀번호를 업데이트 해주기 위해 멤버ID 값을 담은 파라미터
    * @return 비밀번호 변경 성공 여부에 따른 결과가 int result에 담겨 return 
    */
	@Override
	public int modifyMemberPwd(String inputPassword, String inputId) {

		String encodedFirstPwd = passwordEncoder.encode(inputPassword);
		
		Map<String, String> map = new HashMap<>();
		map.put("encodedFirstPwd", encodedFirstPwd);
		map.put("id", inputId);
		
		int result = mapper.updateMemberPwd(map);
		
		return result;
	}

   /**
    * 메소드 emailDuplicationCheck에 관한 문서화 주석
    * @param String emailAddress DB에 존재하는 이메일과 비교할 emailAddress
    * @return existEmail에 값이 담김 여부에 따라 발생하는 result를 return 
    */
	@Override
	public Object emailDuplicationCheck(String emailAddress) {
		
		Object result = null;
		
		Object existEmail  = mapper.emailDuplicationCheck(emailAddress);
		
		if(existEmail != null) { 
			result = 1;
		} else {
			result = emailAddress;  
		}
		
		return result;
	}

   /**
    * 메소드 registVerificationNumber에 관한 문서화 주석
    * @param String emailAddress DB에 존재하는 이메일과 비교할 emailAddress
    * @param String inputId Member Number를 가져오기 위한 Member id가 담긴 inputId
    * @return 이메일 인증 성공 시 결과를 int result에 담아 return
    * @exception NotexistEmailException(입력한 아이디가 존재하지 않는 경우에 대한 익셉션)
    */
	@Override
	public int registVerificationNumber(String emailAddress, String inputId) throws NotexistEmailException {
		
		int result = 0;
		int randomVerificationNum = (int)((Math.random() * 100000) + 10000);
		
		int no = mapper.selectMemberNo(inputId); 
		
		HashMap<String, Integer> map = new HashMap<>();
		map.put("no", no);
		map.put("verification", randomVerificationNum);
		
		result = mapper.insertverification(map);
		
		if(emailAddress != null && result > 0) {
			String subject = "BYAT 비밀번호 찾기용 인증번호 이메일입니다.";
			String content = "BYAT 비밀번호 찾기용 인증번호는 " + randomVerificationNum + "입니다.";
			String from = "byatproject@gmail.com";
			String to = emailAddress;
			
			try {
		            MimeMessage mail = mailSender.createMimeMessage();
		            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
		            
		            mailHelper.setFrom(from);
		            mailHelper.setTo(to);
		            mailHelper.setSubject(subject);
		            mailHelper.setText(content, true);
		            mailSender.send(mail);
		            
		        } catch(Exception e) {
		            e.printStackTrace();
		        }
				
		} else {
			throw new NotexistEmailException("입력하신 아이디가 존재하지 않습니다.");
		}
		
		return result;
	}

   /**
    * 메소드 selectResubmitVerificationNum에 관한 문서화 주석
    * @param String inputId 해당 id를 가진 멤버의 Email과 Number를 가져오기 위한 inputId 
    * @param String emailAddress 해당 emailAddress를 가진 멤버의 Email과 Number를 가져오기 위한 inputId 
    * @return 해당 이메일에 인증번호 재전송결과를 int result에 담아 return
    * @exception NotexistEmailException(입력한 아이디가 존재하지 않는 경우에 대한 익셉션)
    */
	@Override
	public int selectResubmitVerificationNum(String inputId, String emailAddress) throws NotexistEmailException {
		
		int randomVerificationNum = (int)((Math.random() * 100000) + 10000);

		int no = mapper.selectMemberNo(inputId); 
		
		HashMap<String, Integer> map = new HashMap<>();
		map.put("no", no);
		map.put("verification", randomVerificationNum);
		
		int result = mapper.insertverificationforInit(map);
		
		if(emailAddress != null && result > 0) {
			String subject = "BYAT 비밀번호 찾기용 인증번호 이메일입니다.";
			String content = "BYAT 비밀번호 찾기용 인증번호는 " + randomVerificationNum + "입니다.";
			String from = "byatproject@gmail.com";
			String to = emailAddress;
			
			try {
		            MimeMessage mail = mailSender.createMimeMessage();
		            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
		            
		            mailHelper.setFrom(from);
		            mailHelper.setTo(to);
		            mailHelper.setSubject(subject);
		            mailHelper.setText(content, true);
		            mailSender.send(mail);
		            
		        } catch(Exception e) {
		            e.printStackTrace();
		        }
				
		} else {
			throw new NotexistEmailException("입력하신 아이디가 존재하지 않습니다.");
		}
		
		return result;
		
	}

   /**
    * 메소드 matchVerificationNumberForInit에 관한 문서화 주석
    * @param String inputVerificationNum DB에 저장된 인증번호와 비교하기 위해 입력한 인증번호 넘버가 담긴 파라미터
    * @param int inputNo DB에 저장된 인증번호를 찾기위한 멤버 넘버가 저장된 파라미터
    * @return 최초 로그인 시 DB에 저장된 인증번호가 일치하는 여부에 따른 결과가 int result에 담겨 return 
    */
	@Override
	public int matchVerificationNumberForInit(String inputVerificationNum, int inputNo) {

		int result = 0;
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("inputNo", inputNo);
		map.put("inputVerificationNum", inputVerificationNum);
		
		String mathchVerificationForInit = mapper.mathchVerificationNumberForInit(map);
		
		if(mathchVerificationForInit != null) { 
			
			result = mapper.updateVerficiationForInit(inputVerificationNum);
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
    * @return 최초 로그인 시 정보 저장 성공 여부에 따른 결과가 int result에 담겨 return. 
    */
	@Override
	public int initialInputInfo(String emailAddress, String phoneNumber, String newPassword, int inputNo) {

		String encodedFirstPwd = passwordEncoder.encode(newPassword);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("email", emailAddress);
		map.put("phone", phoneNumber);
		map.put("password", encodedFirstPwd);
		map.put("no", inputNo);
		
		int result = mapper.initialInputInfo(map);
		
		return result;
	}
}


