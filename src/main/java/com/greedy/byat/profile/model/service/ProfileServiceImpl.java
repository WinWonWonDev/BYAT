package com.greedy.byat.profile.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.greedy.byat.member.model.dao.MemberMapper;
import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.profile.model.dto.AttachmentDTO;

/**
 * <pre>
 * Class : ProfileServiceImpl
 * Comment : ProfileService를 상속받아 method들을 재정의한 Class
 * History
 * 2021/03/03 (황인수) 처음 작성함
 * 2021/03/04 (황인수) MemberMapper 와 겹치는 부분 수정
 * </pre>
 * @version 2
 * @author 황인수
 * @see ProfileController, ProfileService, MemberMapper, BCryptPasswordEncoder, MemberDTO, AttachmentDTO
 * */
@Service
public class ProfileServiceImpl implements ProfileService {
 
	private final MemberMapper mapper;
	private final BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public ProfileServiceImpl(MemberMapper mapper, BCryptPasswordEncoder passwordEncoder) {
		this.mapper = mapper;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * 메소드 modifyProfile에 관한 문서화 주석
	 * @param member 수정하려는 멤버의 정보
	 * @return message mapper 수행 결과에 따른 message
	 * */
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
	
	/**
	 * 메소드 isPwdMatch에 관한 문서화 주석
	 * @param member 현재 비밀번호를 담고 있는 멤버의 정보
	 * @param requestOriginPwd 입력한 비밀번호
	 * @return boolean mapper 수행 결과에 따른 boolean값
	 * */
	@Override
	public boolean isPwdMatch(MemberDTO member, String requestOriginPwd) {
		
		return passwordEncoder.matches(requestOriginPwd, mapper.selectEncryptedPwd(member));

	}

	/**
	 * 메소드 modifyPassword에 관한 문서화 주석
	 * @param newPwd 변경하고자 하는 비밀번호
	 * @param id 변경할 멤버의 id
	 * @return message mapper 수행 결과에 따른 message
	 * */
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

	/**
	 * 메소드 selectAttachment에 관한 문서화 주석
	 * @param memberNo 조회하고자 하는 멤버의 번호
	 * @return Attachment 조회한 Attachment 정보
	 * */
	@Override
	public AttachmentDTO selectAttachment(int memberNo) {
		
		return mapper.selectAttachment(memberNo);
	}

	/**
	 * 메소드 registAttachment에 관한 문서화 주석
	 * @param attachment 업로드하려는 attachment의 정보
	 * @return message mapper의 수행 결과에 따른 message
	 * */
	@Override
	public String registAttachment(AttachmentDTO attachment) {

		String message = null;
		int updateStatusResult = mapper.updateAttachmentStatus(attachment.getMemberNo());
		
		if(!(updateStatusResult > 0) && (mapper.selectAttachment(attachment.getMemberNo()) != null)) {
			message = "프로필 사진 수정 실패 ...";
		} else {
			
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


