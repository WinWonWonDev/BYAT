package com.greedy.byat.profile.model.dto;

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
public class AttachmentDTO {
	
	private int code;
	private String orgName;
	private String savedName;
	private String path;
	private String thumbnailPath;
	private String status;
	private int memberNo;
}
