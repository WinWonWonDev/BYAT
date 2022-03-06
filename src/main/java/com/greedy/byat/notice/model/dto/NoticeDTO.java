package com.greedy.byat.notice.model.dto;

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
public class NoticeDTO {

	private int code;
	private String body;
	private String url;
	private String status;
	private int category;
	private int no;
	private int projectCode;
	private int issueCode;
	private int scheduleCode;
	private int backlogCode;
	private int meetinglogCode;
	private int sprintCode;
	private java.sql.Date createdTime;
	
}
