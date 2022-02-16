package com.greedy.sohyeon.common.paging;

public class Pagenation {
	

	// 검색어가 없는 경우 페이징 처리만을 위한 용도 (페이징만)
	public static SelectCriteria getSelectCriteria(int pageNo, int totalCount, int limit, int buttonAmount) {
		
		return getSelectCriteria(pageNo, totalCount, limit, buttonAmount, null, null);
	}
	
	//검색어가 존재하는 경우 검색 조건으로 select 한 후 페이징 처리를 하기 위한 용도 (페이징 + 검색)
	public static SelectCriteria getSelectCriteria(int pageNo, int totalCount, int limit, int buttonAmount, String searchCondition, String searchValue) {
		
		//pageNo과 totalConnt 가 넘어왓으니까 페이징 처리에 필요한 나머지 변수만 선언
		int maxPage;
		int startPage;
		int endPage;
		int startRow;
		int endRow;
		
		//총 페이지 수 계산
		// 목록수가 123일떄 페이지 수는 13페이지 
		// 자투리 목록이 최소 1개 일때 , 1page로 처리하기 위해서 0.9를 더하기 함 
		maxPage = (int) Math.ceil((double) totalCount /limit);
		//흠
		
		
		//현재 페이지에 보여줄 시작 페이지 수 7개씩 보여주게 할 경우 
		// 아래쪽에 페이지 수가 7개씩 보여지게 한다면 1, 8, 15, 22 ...  
		//음...왤캐 이해가 안되지 
		startPage = (int)(Math.ceil((double) pageNo / buttonAmount) - 1) * buttonAmount + 1;
		
		// 목록 아래에 보여질 마지막 페이지 수 7, 14, 21 .. .
		endPage = startPage + buttonAmount - 1; //이건 ㅇㅋ 
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		if(maxPage == 0 && endPage == 0) {
			maxPage = startPage;
			endPage = startPage;
		}
		
		//이거 이해는 됫는데 그냥 pageNo * limit 하면 되지 않나  음...
		startRow = (pageNo - 1) * limit + 1;
		endRow = startRow + limit - 1;
		//아 ㅇㅋ
		
		SelectCriteria selectCriteria = new SelectCriteria(pageNo, totalCount, limit, buttonAmount, maxPage, startPage, endPage, startRow, endRow, searchCondition, searchValue); 
		
		return selectCriteria;
		
		
	
	}

	

}
