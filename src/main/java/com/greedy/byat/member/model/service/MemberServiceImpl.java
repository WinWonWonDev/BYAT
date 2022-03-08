package com.greedy.byat.member.model.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.byat.common.exception.member.LoginFailedException;
import com.greedy.byat.common.exception.member.NotexistEmailException;
import com.greedy.byat.member.model.dao.MemberMapper;
import com.greedy.byat.member.model.dto.MemberDTO;

/* 
* <pre>
* Class : MemberServiceImpl
* Comment : MemberController에 필요한 메소드들의 로직을 작성하는 Service입니다.
* History
* 2022/02/17 이소현  처음 작성
* </pre>
* @version 1.0.0
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

	@Override
	public String selectMember(MemberDTO member, RedirectAttributes rttr, Model model) {
		
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
						return "redirect:/home";
					}
					
				} else {
					rttr.addFlashAttribute("message", "로그인 실패! 아이디나 비밀번호를 확인해주세요!");
					return "redirect:/member/login";
				}
		
			}
	}

//혹시 모르니 남겨두기	
//	@Override
//	public MemberDTO login(MemberDTO member) {
//		
//		if(!passwordEncoder.matches(member.getPwd(), mapper.selectEncryptedPwd(member))) {
//			return null;
//			
//		} else {
//			
//			return mapper.login(member);
//		}
//		
//	}
//	
//	@Override
//	public MemberDTO initLogin(MemberDTO member) {
//		
//		
//		if(!passwordEncoder.matches(member.getPwd(), mapper.selectEncryptedPwd(member))) {
//			return null;
//			
//		} else {
//			
//			return mapper.initLogin(member);
//		}
//	}
//	
//	@Override
//	public String selectMember(MemberDTO member) {
//		
//		return mapper.selectInitPasswordYN(member);
//	}

	

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

	@Override
	public int matchVerificationNumber(String inputVerificationNum) {
		
		int result = 0;
		
		String mathchVerification = mapper.mathchVerificationNumber(inputVerificationNum);
		
		if(mathchVerification != null) { 
			
			result = mapper.updateVerficiation(inputVerificationNum);
		}
		
		return result;
		
	}

	@Override
	public int modifyMemberPwd(String inputPassword, String inputId) {

		String encodedFirstPwd = passwordEncoder.encode(inputPassword);
		
		Map<String, String> map = new HashMap<>();
		map.put("encodedFirstPwd", encodedFirstPwd);
		map.put("id", inputId);
		
		int result = mapper.updateMemberPwd(map);
		
		return result;
	}

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

	@Override
	public int initialInputInfo(String emailAddress, String phoneNumber, String newPassword, int inputNo) {

		String encodedFirstPwd = passwordEncoder.encode(newPassword);
		
		System.out.println("암호화된 비밀번호 ㅇㅇ : " + encodedFirstPwd);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("email", emailAddress);
		map.put("phone", phoneNumber);
		map.put("password", encodedFirstPwd);
		map.put("no", inputNo);
		
		System.out.println("map : " + map);
		
		int result = mapper.initialInputInfo(map);
		
		System.out.println("서비스에서 sesult : " + result);
		
		return result;
	}


}


