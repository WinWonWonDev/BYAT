package com.greedy.byat.management.model.dto;

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
public class ManagementDTO {
	
	private int memberNo; 
	private int permitCode; 
	private String permitName; 
	private String memberName; 
	private String memberId; 
	private String absenceYN; 
	private String initPwdYN; 
	
	
}
