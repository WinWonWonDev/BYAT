package com.greedy.byat.member.model.dao;

import java.util.HashMap;
import java.util.Map;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.profile.model.dto.AttachmentDTO;

public interface MemberMapper {

	String selectEncryptedPwd(MemberDTO member);

	MemberDTO login(MemberDTO member);

	MemberDTO initLogin(MemberDTO member);

	String selectInitPasswordYN(MemberDTO member);

	String emailduplicationCheck(int id);

	String selectEmailById(String id);

	String mathchVerificationNumber(String inputVerificationNum);

	int selectMemberNo(String id);

	int insertverification(HashMap<String, Integer> map);

	int updateVerficiation(String inputVerificationNum);

	int updateMemberPwd(Map<String, String> map);

	String emailDuplicationCheck(String emailAddress);

	int insertverificationforInit(HashMap<String, Integer> map);

	String mathchVerificationNumberForInit(HashMap<String, Object> map);

	int updateVerficiationForInit(String inputVerificationNum);

	int initialInputInfo(HashMap<String, Object> map);
	
	int updateMemberProfile(MemberDTO member);

	AttachmentDTO selectAttachment(int memberNo);
	
	int updateAttachmentStatus(int memberNo);

	int insertAttachment(AttachmentDTO attachment);

}
