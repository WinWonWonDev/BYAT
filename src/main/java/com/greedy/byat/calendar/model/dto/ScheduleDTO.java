package com.greedy.byat.calendar.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : ScheduleDTO
 * Comment : Calendar에 관련된 setter, getter, toString 등을 모아두는 DTO
 * History
 * 2021/02/17 (이소현)  처음 작성
 * </pre>
 * @version 1.0.0
 * @author 이소현
 * @see CalendarController, CalendarServiceImpl, CalendarMapper
 * */
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
	private String body; 
	private String writer;
	private int memberNo;
	private int typeCode;
	private int projectCode;
	private String deleteStatus;
	private String projectTitle;
}
