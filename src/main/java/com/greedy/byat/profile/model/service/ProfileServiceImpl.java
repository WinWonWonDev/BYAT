package com.greedy.byat.profile.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.greedy.byat.member.model.dao.MemberMapper;
import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.profile.model.dto.AttachmentDTO;

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
	public boolean isPwdMatch(MemberDTO member, String requestOriginPwd) {
		
		System.out.println("encryptedPwd : " + mapper.selectEncryptedPwd(member));
		
		return passwordEncoder.matches(requestOriginPwd, mapper.selectEncryptedPwd(member));

	}

	@Override
	public String modifyPassword(String newPwd, String id) {
		
		String message = null;
		
		Map<String, String> newPwdMap = new HashMap<>();
		newPwdMap.put("encodedFirstPwd", newPwd);
		newPwdMap.put("id", id);
		
		int result = mapper.updateMemberPwd(newPwdMap);
		
		if(!(result > 0)) {
			message = "비밀번호 변경 실패 ...";
		} else {
			message = "비밀번호 변경 성공 !!!";
		}
		
		return message;
	}


	@Override
	public AttachmentDTO selectAttachment(int memberNo) {
		
		return mapper.selectAttachment(memberNo);
	}


	@Override
	public String registAttachment(AttachmentDTO attachment) {

		String message = null;
		int updateStatusResult = mapper.updateAttachmentStatus(attachment.getMemberNo());
		
		if(!(updateStatusResult > 0) && (mapper.selectAttachment(attachment.getMemberNo()) != null)) {
			message = "프로필 사진 수정 실패 ...";
		} else {
			System.out.println("상태 변경 성공 !!!");
			
			int registResult = mapper.insertAttachment(attachment);
			
			if(!(registResult > 0)) {
				message = "프로필 사진 수정 실패 ...";
			} else {
				message = "프로필 사진 수정 성공 !!!";
			}
		}
		
		return message;
	}
}


