package com.greedy.byat.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greedy.byat.member.model.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		
		this.memberService = memberService;
	}
	
	
}
