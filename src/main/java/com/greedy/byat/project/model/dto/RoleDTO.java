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
public class RoleDTO {

	private int code;
	private String roleName;
	private int memberNo;
	private int projectCode;
	
}
