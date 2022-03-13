package com.greedy.byat.management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : ManagementrDTO
 * Comment : Management에 관련된 setter, getter, toString 등을 모아두는 DTO입니다.
 * History
 * 2021/02/17 (이소현)  처음 작성
 * </pre>
 * @version 1.0.0
 * @author 이소현
 * @see ManagementController, ManagementServiceImpl, ManagementMapper
 * */
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
