package com.greedy.byat.profile.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.greedy.byat.member.model.dao.MemberMapper;
import com.greedy.byat.member.model.dto.MemberDTO;

@Service
public class ProfileServiceImpl implements ProfileService {
 
	private final MemberMapper mapper;
	private final BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public ProfileServiceImpl(MemberMapper mapper, BCryptPasswordEncoder passwordEncoder) {
		this.mapper = mapper;
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	public String modifyProfile(MemberDTO member) {
		
		int result = mapper.updateMemberProfile(member);
		String message = null;
		
		if(!(result > 0)) {
			message = "프로필 수정 실패 ...";
		} else {
			message = "프로필 수정 성공 !!!";
		}
		
		return message;
	}

	@Override
	public void modifyPassword(MemberDTO member) {
		
		String message = null;
		
		Map<String, String> pwdMap = new HashMap<>();
		
		if(!passwordEncoder.matches(member.getPwd(), mapper.selectEncryptedPwd(member))) {
			/* 일치하지 않는 경우 메세지 출력 */
			message = "기존 비밀번호가 일치하지 않습니다.";
		} else {
			mapper.updateMemberPwd(pwdMap);
		}
		
	}
}


