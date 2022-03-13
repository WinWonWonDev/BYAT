package com.greedy.byat.member.model.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.byat.common.exception.member.LoginFailedException;
import com.greedy.byat.common.exception.member.NotexistEmailException;
import com.greedy.byat.member.model.dto.MemberDTO;
import com.greedy.byat.profile.model.dto.AttachmentDTO;

/**
 * <pre>
 * Class : MemberService
 * Comment : MemberController의 요청을 처리하는 Interface
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
 * @see MemberController, MemberServiceImpl, MemberMapper, MemberDTO
 * */
public interface MemberService {

	String selectMember(MemberDTO member, RedirectAttributes rttr, Model model, HttpServletRequest request);

	int selectEmailById(String id) throws NotexistEmailException;

	int matchVerificationNumber(String inputVerificationNum);

	int modifyMemberPwd(String inputPassword, String inputId);

	Object emailDuplicationCheck(String emailAddress);

	int registVerificationNumber(String emailAddress, String inputId) throws NotexistEmailException;

	int selectResubmitVerificationNum(String inputId, String emailAddress) throws NotexistEmailException;

	int matchVerificationNumberForInit(String inputVerificationNum, int inputNo);

	int initialInputInfo(String emailAddress, String phoneNumber, String newPassword, int inputNo);
	

}
