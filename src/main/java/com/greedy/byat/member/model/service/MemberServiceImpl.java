package com.greedy.byat.member.model.service;

import java.util.HashMap;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.greedy.byat.common.exception.member.LoginFailedException;
import com.greedy.byat.common.exception.member.NotexistEmailException;
import com.greedy.byat.member.model.dao.MemberMapper;
import com.greedy.byat.member.model.dto.MemberDTO;

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
	public MemberDTO login(MemberDTO member) throws LoginFailedException {
		
		if(!passwordEncoder.matches(member.getPwd(),
				mapper.selectEncryptedPwd(member))) {

			throw new  LoginFailedException("로그인 실패.. alert으로 띄우고 싶다");
			
		}

		return mapper.login(member);
	}

	@Override
	public MemberDTO initLogin(MemberDTO member) throws LoginFailedException {


		if(!passwordEncoder.matches(member.getPwd(),
				mapper.selectEncryptedPwd(member))) {

			throw new LoginFailedException("로그인 실패.. alert으로 띄우고 싶다");

		}

		return mapper.initLogin(member);
	}

	@Override
	public String selectMember(MemberDTO member) {
		
		return mapper.selectInitPasswordYN(member);
	}

	@Override
	public boolean emailduplicationCheck(int id) {
		
		String result = mapper.emailduplicationCheck(id);
		
		return result != null? true: false;
	}

	@Override
	public int selectEmailById(String id) throws NotexistEmailException {
		
		int randomVerificationNum = (int)((Math.random() * 100000) + 1000);
		
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
	public int modifyMemberPwd(String firstPwd, String confirmPwd) {

		String encodedFirstPwd = passwordEncoder.encode(firstPwd);
		
		int result = mapper.updateMemberPwd(encodedFirstPwd);
		
		return result;
	}




}


