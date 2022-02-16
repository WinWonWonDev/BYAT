package com.greedy.byat.common.paging;

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

	private int pageNo; 
	private int totalCount; 
	private int limit; 
	private int buttonAmount; 
	private int maxPage; 
	private int startPage; 
	private int endPage; 
	private int startRow; 
	private int endRow; 
	private String searchCondition; 
	private String searchValue; 
	
	
	
	
	
	
	
}
