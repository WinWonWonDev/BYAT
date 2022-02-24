package com.greedy.byat.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//(필드 넣어준다음에 주석 풀어주시기 바랍니다)
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
