package com.greedy.byat.task.model.dto;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private java.sql.Date startDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private java.sql.Date endDate;
	private String progress;
	private String body;
	private int version;
	private String deleteStatus;
	private int backlogCode;
	private int sprintCode;
	private int projectCode;
	private int memberNo;
	private int updateMemberNo;
	private RoleDTO managerRole;
	private List<TaskMembersDTO> taskMemberList;
	private List<RoleDTO> membersRole;
	
}
