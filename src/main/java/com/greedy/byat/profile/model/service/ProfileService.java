package com.greedy.byat.profile.model.service;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.profile.model.dto.AttachmentDTO;

public interface ProfileService {
	
	String modifyProfile(MemberDTO member);
	
	boolean isPwdMatch(MemberDTO member, String requestOriginPwd);

	String modifyPassword(String newPwd, String id);
	
	AttachmentDTO selectAttachment(int memberNo);
	
	String registAttachment(AttachmentDTO attachment);
}
