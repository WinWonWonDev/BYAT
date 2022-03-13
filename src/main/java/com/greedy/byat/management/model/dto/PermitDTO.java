package com.greedy.byat.management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : PermitDTO
 * Comment : Permit에 관련된 setter, getter, toString 등을 모아두는 DTO
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
public class PermitDTO {
	
	private int code;
	private String name;
	private String description;
	
}
