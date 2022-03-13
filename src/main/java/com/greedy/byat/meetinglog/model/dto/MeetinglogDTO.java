package com.greedy.byat.meetinglog.model.dto;

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
public class MeetinglogDTO {

	private int code;
	private String title;
	private String body;
	private java.sql.Date writingDate;
	private int version;
	private String deleteStatus;
	private int projectCode;
	private int memberNo; 
	private String memberName;
}
