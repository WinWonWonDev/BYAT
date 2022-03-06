package com.greedy.byat.profile.model.service;

import com.greedy.byat.member.model.dto.MemberDTO;

public interface ProfileService {
	
	String modifyProfile(MemberDTO member);

	void modifyPassword(MemberDTO member);
}
