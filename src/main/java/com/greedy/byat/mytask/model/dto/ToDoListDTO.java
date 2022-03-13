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

	private int no;								//고유 번호
	private java.sql.Date writingTime;			//작성 시간
	private String title;						//제목
	private String checkStatus;					//체크박스 상태 (y/n)
	private String deleteStatus;				//삭제 상태 (y/n)
	private int memberNo;						//로그인된 멤버의 고유번호
}
