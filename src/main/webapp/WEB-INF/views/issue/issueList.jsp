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
	
	.issueListHead {
		height:15%;
		border-bottom:1px solid  black;
	}

	.issueListName {
		margin-left:3%;
		margin-top:1%;
		font-size:35px;
		float:left;
		display:inline;
	}
	
	.searchIssue {
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
	
	.issueBeforeSolution {
		border-right:1px solid black;
		float:left;
		width:33.3%;
		height:85%;
	}
	
	.issueResolving {
		border-right:1px solid black;
		float:left;
		width:33.3%;
		height:85%;
	}
	
	.issueSolved {
		float:left;
		width:33%;
		height:85%;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<div id="whiteBoard">
	      <div class="issueListHead">
				<div class="issueListName">""의 Issue</div>
				<div class="searchIssue">
					<div class="search-area">
						<select id="searchCondition" name="searchCondition">
							<option value="title">제목</option>
							<option value="content">내용</option>
							<option value="issuePresenter">제기자</option>
							<option value="issueManager">담당자</option>
						</select>
						<input type="search">
						<button type="submit">검색</button>
					</div>
				</div>
	      </div>
	      <div class="issueBeforeSolution">
	      		야호
	      </div>
	      <div class="issueResolving">
	      		신난다
	      </div>
	      <div class="issueSolved">
	      		ㅎ
	      </div>
	</div>

</body>
</html>