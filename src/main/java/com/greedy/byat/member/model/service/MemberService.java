package com.greedy.byat.member.model.service;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.byat.common.exception.member.LoginFailedException;
import com.greedy.byat.common.exception.member.NotexistEmailException;
import com.greedy.byat.member.model.dto.MemberDTO;

public interface MemberService {

	String selectMember(MemberDTO member, RedirectAttributes rttr, Model model);

	int selectEmailById(String id) throws NotexistEmailException;

	int matchVerificationNumber(String inputVerificationNum);

	int modifyMemberPwd(String inputPassword, String inputId);

	Object emailDuplicationCheck(String emailAddress);

	int registVerificationNumber(String emailAddress, String inputId) throws NotexistEmailException;

	int selectResubmitVerificationNum(String inputId, String emailAddress) throws NotexistEmailException;

	int matchVerificationNumberForInit(String inputVerificationNum, int inputNo);

	int initialInputInfo(String emailAddress, String phoneNumber, String newPassword, int inputNo);
	

}
