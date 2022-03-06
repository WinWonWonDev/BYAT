package com.greedy.byat.calendar.model.dto;

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
public class CalendarDTO {

	private int code; 
	private String title; 
	private String writer; 
	private String body; 
	private String startDate; 
	private String endDate;
	private int memberNo;
	private int typeCode;
	private String deleteStatus;
	

	//	private int id; 
//	private String groupId; 
//	private String title; 
//	private String writer; 
//	private String content; 
//	private String start; 
//	private String end; 
//	private boolean allday; 
//	private String backgroundColor; 
//	private String borderColor;

	
	
}
