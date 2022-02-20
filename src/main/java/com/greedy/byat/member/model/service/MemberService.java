package com.greedy.byat.member.model.service;

import com.greedy.byat.common.exception.member.LoginFailedException;
import com.greedy.byat.common.exception.member.NotexistEmailException;
import com.greedy.byat.member.model.dto.MemberDTO;

public interface MemberService {

	MemberDTO initLogin(MemberDTO member) throws LoginFailedException;

	MemberDTO login(MemberDTO member) throws LoginFailedException;

	String selectMember(MemberDTO member);

	boolean emailduplicationCheck(int id);

	int selectEmailById(String id) throws NotexistEmailException;


}
