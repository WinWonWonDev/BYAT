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
 * 2021/02/17 (이소현) 처음 작성
 * </pre>
 * @version 1.0.0
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
