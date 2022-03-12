package com.greedy.byat.profile.model.service;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.profile.model.dto.AttachmentDTO;

/**
 * <pre>
 * Class : ProfileService
 * Comment : ProfileController의 요청을 처리하는 Interface
 * History
 * 2021/02/17 (황인수) 처음 작성
 * </pre>
 * @version 1.0.0
 * @author 황인수
 * @see ProfileController, ProfileServiceImpl, MemberMapper, MemberDTO, AttachmentDTO
 * */
public interface ProfileService {
	
	String modifyProfile(MemberDTO member);
	
	boolean isPwdMatch(MemberDTO member, String requestOriginPwd);

	String modifyPassword(String newPwd, String id);
	
	AttachmentDTO selectAttachment(int memberNo);
	
	String registAttachment(AttachmentDTO attachment);
}
