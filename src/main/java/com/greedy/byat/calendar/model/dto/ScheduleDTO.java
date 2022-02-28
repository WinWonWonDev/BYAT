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
public class ScheduleDTO {
	
	private int code;
	private String title;
	private java.sql.Date startDate;
	private java.sql.Date endDate;
	private String body; //string이려나
	private String writer;
	private int typeCode;
	private int projectCode;
	
}
