<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/manubar.jsp" %>
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
	
	.historyDetailHead {
		height:10%;
		border-bottom:1px solid  black;
	}
	
	h3 {
		margin-top:15px;
		text-align:center;
	}
	
	.historyDetailCodeArea {
		float:left;
		border-right:1px solid black;
		width:10%;
		height:100%;
	}

	.historyDetailCodeReal {
		float:left;
		border-right:1px solid black;
		width:20%;
		height:100%;
	}

	.historyDetailKindsArea {
		float:left;
		border-right:1px solid black;
		width:10%;
		height:100%;
	}
	
	.historyDetailKindsReal {
		float:left;
		border-right:1px solid black;
		width:20%;
		height:100%;
	}
	
	.historyDetailcreatedDateArea {
		float:left;
		border-right:1px solid black;
		width:15%;
		height:100%;
	} 
	
	.historyDetailcreatedDateReal {
		float:left;
		width:24.5%;
		height:100%;
	}
	
	.historyDetailTitleArea {
		float:left;
		border-right:1px solid black;
		width:15%;
		height:100%;
	}
	
	.historyDetailTitleReal {
		float:left;
		width:54%;
		height:100%;
	}

	.historyDetailSecondHead {
		height:10%;
		border-bottom:1px solid  black;
	}
	
	.historyOriginalCodeArea {
		float:left;
		border-right:1px solid black;
		width:15%;
		height:100%;
	}
	
	.historyOriginalCodeReal {
		float:left;
		border-right:1px solid black;
		width:15%;
		height:100%;
	}

</style>
<title>Insert title here</title>
</head>
<body style="overflow:hidden;">
	<div id="whiteBoard">
	      <div class="historyDetailHead">
	      		<div class="historyDetailCodeArea"><h3>코드</h3></div>
				<div class="historyDetailCodeReal"><h3>History-001</h3></div>
				<div class="historyDetailTitleArea"><h3>히스토리 제목</h3></div>
				<div class="historyDetailTitleReal"><h3>'키오스크 결제 관련 회의록' 회의록 생성 (롯데리아 키오스크)</h3></div>
	      </div>
	      <div class="historyDetailSecondHead">
 	      		<div class="historyDetailKindsArea"><h3>종류</h3></div>
	      		<div class="historyDetailKindsReal"><h3>스프린트</h3></div>
	      		<div class="historyOriginalCodeArea"><h3>원본 코드</h3></div>
	      		<div class="historyOriginalCodeReal"><h3>Sprint-999</h3></div>
	      		<div class="historyDetailcreatedDateArea"><h3>저장일자</h3></div>
	      		<div class="historyDetailcreatedDateReal"><h3>2022.01.12 PM 01:52</h3></div>
	      </div>
	</div>

</body>
</html>