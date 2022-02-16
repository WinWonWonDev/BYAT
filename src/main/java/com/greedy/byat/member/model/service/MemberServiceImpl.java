package com.greedy.byat.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.byat.member.model.dao.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
 
	private final MemberMapper mapper;

	@Autowired
	public MemberServiceImpl(MemberMapper mapper) {
		this.mapper = mapper;
	}

	







}


