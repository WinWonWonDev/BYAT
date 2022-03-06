package com.greedy.byat.profile.model.dao;

import com.greedy.byat.member.model.dto.MemberDTO;

public interface ProfileMapper {
	
	int updateMemberProfile(MemberDTO member);
	
	int updatePassword(MemberDTO member);
}
