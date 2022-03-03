package com.greedy.byat.backlog.model.dto;

import com.greedy.byat.member.model.dto.MemberDTO;

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
	private java.sql.Date startDate;
	private java.sql.Date endDate;
	private String progress;
	private String body;
	private int projectCode;
	private int version;
	private String deleteStatus;
	private String writer;
	private MemberDTO writerMember;
}
