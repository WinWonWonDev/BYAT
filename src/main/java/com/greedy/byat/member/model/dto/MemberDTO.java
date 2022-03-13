package com.greedy.byat.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : MemberDTO
 * Comment : Member에 관련된 setter, getter, toString 등을 모아두는 DTO
 * History
 * 2021/02/17 (이소현)  처음 작성
 * </pre>
 * @version 1.0.0
 * @author 이소현
 * @see MemberController, MemberServiceImpl, MemberMapper
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString 
public class MemberDTO implements java.io.Serializable {

	private int no;
	private int permitCode;
	private String id;
	private String pwd;
	private String name;
	private String phone;
	private String email;
	private String absenceYN;
	private String initPwdYN;
	
	
}
