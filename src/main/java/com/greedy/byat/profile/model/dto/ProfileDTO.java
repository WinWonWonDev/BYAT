package com.greedy.byat.profile.model.dto;

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
public class ProfileDTO implements java.io.Serializable  {
	
	/* Profile TBL이 따로 있는 게 아닌, TBL_MEMBER에서 해당 회원과 일치하는 value 추출하기 */
	private int no;
	private String id;
	private String pwd;
	private String name;
	private String phone;
	private String email;
}
