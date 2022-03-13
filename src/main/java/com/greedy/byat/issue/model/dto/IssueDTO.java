package com.greedy.byat.issue.model.dto;

import java.util.List;

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
public class IssueDTO {

	private int code;
	private String title;
	private String body;
	private java.sql.Date writingTime;
	private String progress;
	private int version;
	private String deleteStatus;
	private int writer;
	private int sprintCode;
	private int projectCode;
	private String name;
	private List<IssueMembersDTO> issueMemberList; 
	
}
