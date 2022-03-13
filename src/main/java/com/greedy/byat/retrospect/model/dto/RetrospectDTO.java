package com.greedy.byat.retrospect.model.dto;

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
public class RetrospectDTO {

	private int code;
	private int sprintCode;
	private String title;
	private List<RetrospectCommentDTO> commentList;
}
