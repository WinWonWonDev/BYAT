package com.greedy.sohyeon.common.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SelectCriteria implements java.io.Serializable {

	private int pageNo; //요청한 페이지 번호 ! ㅇㅋ
	private int totalCount; //전체 게시물 수 ! ㅇㅋ
	private int limit; //한 페이지에 보여줄 게시물 수 ㅇㅋ
	private int buttonAmount; //한 페이지에 보여줄 페이징 버튼의 갯수 = 그 10 되있던거
	private int maxPage; // 가장 마지막 페이지
	private int startPage; // 한번에 보여줄 페이징 번튼의 시작하는 페이지 수 
	private int endPage; //한번에 보여줄 페이징 버튼의 마지막 페이지 수 
	private int startRow; //시작 조회 줄?
	private int endRow; // 그 마지막 조회 줄 row니까 ㅇㅇ
	private String searchCondition; //검색 조건!
	private String searchValue; //검색어 
	

	
	
	
	
	
	
}
