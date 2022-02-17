package com.greedy.byat.project.model.dto;

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
public class ProjectDTO {

	private int code;
	private String title;
	private java.sql.Date startDate;
	private java.sql.Date endDate;
	private String progress;
	private String body;
	private int version;
	private String deleteStatus;
	private String writer;
	private MemberDTO writerMember;
	
}
