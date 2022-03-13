package com.greedy.byat.member.model.dao;

import java.util.HashMap;
import java.util.Map;

import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.profile.model.dto.AttachmentDTO;

/**
* <pre>
* Class : MemberMapper
* Comment : mapper.xml의 id들이 담긴 Class
* History
* 2022/02/17 (이소현) 처음 작성
* 2022/02/18 (이소현) 멤버 로그인(최초 로그인 시), 비밀번호 찾기 구현 시작
* 2022/02/19 (이소현) 비밀번호 찾기 관련 모달창 작성, 이메일 인증번호 전송 
* 2022/02/20 (이소현) 인증번호 재전송 시 타이머 리셋, 인증번호 일치, 비밀번호 변경 
* 2022/02/21 (이소현) 타이머 기능 수정, 비밀번호 변경, 초기 계정 정보 설정
* 2022/02/22 (이소현) 인증번호 전송
* </pre>
* @version 6
* @author 이소현
* @see MemberController, MemberService, MemberServiceImpl, MemberDTO
* */
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
