package com.greedy.byat.project.model.dto;

import java.util.List;

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
public class ProjectMembersDTO {

	private int code;
	private int no;
	private String participationYn;
	private String name;
	private String roleName;
	
}
