package com.greedy.byat.member.model.dao;

import com.greedy.byat.member.model.dto.MemberDTO;

public interface MemberMapper {

	String selectEncryptedPwd(MemberDTO member);

	MemberDTO login(MemberDTO member);

	MemberDTO initLogin(MemberDTO member);

	String selectInitPasswordYN(MemberDTO member);

	String emailduplicationCheck(int id);

}
