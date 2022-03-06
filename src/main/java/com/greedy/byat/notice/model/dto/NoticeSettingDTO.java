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
public class NoticeSettingDTO {

	private int no;
	private String projectSetting;
	private String sprintSetting;
	private String backlogSetting;
	private String issueSetting;
	private String meetinglogSetting;
	private String calendarSetting;
	
}
