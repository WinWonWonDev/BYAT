package com.greedy.byat.project.model.dto;

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
	private String id;
	private String name;
	private String roleName;
	
}
