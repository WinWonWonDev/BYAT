package com.greedy.byat.management.model.dto;

import com.greedy.byat.member.model.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString 
public class ManagementDTO {
	
	private int memberNo; //멤버 번호
	private int permitCode; //권한 넘버
	private String permitName; //권한 이름
	private String memberName; //멤버 이름
	private String memberId; //멤버 id
	private String absenceYN; // 멤버탈퇴여부
	
}
