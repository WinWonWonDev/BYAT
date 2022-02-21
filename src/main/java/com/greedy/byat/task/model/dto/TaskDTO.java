package com.greedy.byat.task.model.dto;

import java.util.Date;

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
	private int backLogCode;
	private String title;
	private java.util.Date startDate;
	private java.util.Date endDate;
	private String manager;
	private String progress;
	private String body;
	private int projectCode;
	private int version;
	private String deleteStatus;
	private int memberNo;
	private int sprintCode;
}
