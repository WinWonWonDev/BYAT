<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/menubar.jsp" %>
<%@ include file="../common/subMenu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
	html {
		width: 100%;
      	height: 98%;
      	background:#D7E3FA;
      	background-size: cover;
      	position:relative;
   	}
   
   	body {
		width: 100%;
     	height: 95%;
   	}
   
	#whiteBoard {
		position:absolute;
    	background:white;
      	border:1px solid black;
      	top:15%;
      	left:3%;
      	width:95%;
      	height:78%;
	}
	
	.historyListHead {
		height:15%;
	}

	.historyListName {
		margin-left:3%;
		margin-top:1%;
		font-size:35px;
		float:left;
		display:inline;
	}
	
	.searchHistory {
		float:right;
		margin-right:3%;
		margin-top:2%;
	}
	
	.search-area {
		margin-left:auto;
		margin-right:auto;
	}
	
	.historyListBox {
		border:1px solid black;
		height:77%;
		margin-left:3%;
		margin-right:3%;
	}
	
	.historyTable {
		margin-left:3%;
		margin-top:3%;
	}
	
	#codeTd {
		width:200px;
	}
	
	#titleTd {
		width:600px;
	}
	
	#kindsTd {
		width:200px;	
	}
	
	#createdDateTd {
		width:250px;	
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<div id="whiteBoard">
	      <div class="historyListHead">
				<div class="historyListName">히스토리목록</div>
				<div class="searchHistory">
					<div class="search-area">
						<select id="searchCondition" name="searchCondition">
							<option value="title">제목</option>
							<option value="content">내용</option>
							<option value="kinds">종류</option>
						</select>
						<input type="search">
						<button type="submit">검색</button>
					</div>
				</div>
	      </div>
	      <div class="historyListBox">
	      		<table border="1" class="historyTable">
					<tr>
						<th id="codeTd">코드</th>
						<th id="titleTd">제목</th>
						<th id="kindsTd">종류</th>
						<th id="createdDateTd">작성일자</th>
					</tr>
					<script>
						
					</script>				
	      		</table>
	      </div>
	</div>

</body>
</html>