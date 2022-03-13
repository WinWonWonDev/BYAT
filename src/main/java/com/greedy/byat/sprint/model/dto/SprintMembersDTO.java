package com.greedy.byat.sprint.model.dto;

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
public class SprintMembersDTO {

	private int no;
	private int projectCode;
	private int sprintCode;
	private String participationYn;
	private String id;
	private String name;
	
}
