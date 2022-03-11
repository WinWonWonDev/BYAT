package com.greedy.byat.retrospect.model.dto;

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
public class RetrospectCommentDTO {
	
	private int no;
	private int code;
	private int memberNo;
	private String memberName;
	private int sprintCode;
	private String body;
	private String deleteStatus;
	private java.sql.Date writingTime;
}
