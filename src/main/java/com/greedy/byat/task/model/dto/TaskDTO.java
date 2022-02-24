package com.greedy.byat.task.model.dto;


import java.util.List;

import com.greedy.byat.project.model.dto.RoleDTO;

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
public class TaskDTO {
	
	private int code;
	private String title;
	private String manager;
	private java.sql.Date startDate;
	private java.sql.Date endDate;
	private String progress;
	private String body;
	private int version;
	private String deleteStatus;
	private int backLogCode;
	private int sprintCode;
	private int projectCode;
	private int memberNo;
	private RoleDTO managerRole;
	private List<TaskMembersDTO> taskMemberList;
	private List<RoleDTO> membersRole;
	
}
