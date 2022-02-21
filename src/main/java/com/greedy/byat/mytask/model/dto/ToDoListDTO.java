package com.greedy.byat.mytask.model.dto;

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
public class ToDoListDTO {

	private java.sql.Date writingTime;
	private String title;
	private String checkStatus;
	private String deleteStatus;
	private int no;
}
