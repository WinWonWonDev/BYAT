package com.greedy.byat.task.model.dto;

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
public class TaskMembersDTO {
	
	private int taskCode;
	private int memberNo;
	private int projectCode;
	private String roleName;
	private String permitName;
	private String memberName;
	private String memberId;
}
