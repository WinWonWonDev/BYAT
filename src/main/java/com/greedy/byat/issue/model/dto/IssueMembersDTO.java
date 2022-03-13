package com.greedy.byat.issue.model.dto;

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
public class IssueMembersDTO {
	
	private int code;
	private int no;
	private int sprintCode;
	private String participationYn;
	private String name;
	private String id;
	private int changeMemberNo;
	
}
