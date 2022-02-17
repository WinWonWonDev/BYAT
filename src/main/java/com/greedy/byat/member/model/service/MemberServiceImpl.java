package com.greedy.byat.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.greedy.byat.common.exception.member.LoginFailedException;
import com.greedy.byat.member.model.dao.MemberMapper;
import com.greedy.byat.member.model.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {

	private final MemberMapper mapper;
	private final BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public MemberServiceImpl(MemberMapper mapper, BCryptPasswordEncoder passwordEncoder) {
		this.mapper = mapper;
		this.passwordEncoder = passwordEncoder;
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











}


