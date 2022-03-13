package com.greedy.byat.backlog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//(필드 넣어준다음에 주석 풀어주시기 바랍니다)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString 
public class BacklogDTO {
	
	private int code;
	private String title;
	private String progress;
	private String body;
	private int projectCode;
	private int version;
	private String deleteStatus;
	private String writer;
}
