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
      	overflow-y:hidden;
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
	
	.meetinglogListHead {
		height:15%;
	}

	.meetinglogListName {
		margin-left:3%;
		margin-top:1%;
		font-size:35px;
		float:left;
		display:inline;
	}
	
	.searchMeetinglog {
		float:right;
		margin-right:3%;
		margin-top:2%;
	}
	
	.search-area {
		margin-left:auto;
		margin-right:auto;
	}
	
	.meetinglogBox {
		border:2px solid black;
		width:250px;
		height:100px;
		margin-left:3%;
	}
	
	.plusBox {
		border:1px solid black;
		width:240px;
		height:20px;
		margin-left:2%;
		margin-top:3%;
	}
	
	.plusBlackImage {
		width:15px;
		height:15px;
		background:url("/byat/resources/images/plusBlackButton.png") no-repeat;
		background-size:cover;
		border:0px;
		cursor:pointer;
		margin-left:45%;
	}
	
	#meetinglogCreateModal {
		display: none;
		position:relative;
		width:100%;
		height:100%;
		z-index:1;
	}
	
	#meetinglogCreateModal button {
		display:inline-block;
		width:100px;
		margin-left:calc(100% - 100px - 10px);
	}
	
	#meetinglogCreateModal .modal_content {
		width:500px;
		height:600px;
		margin:20px auto;
		background:#29428C;
		border:1px solid black;
		border-radius:25px;
	}
	
	#meetinglogCreateModal .modal_layer {
		position:fixed;
		top:0;
		left:0;
		width:100%;
		height:100%;
		background:rgba(0, 0, 0, 0.5);
		z-index:-1;
	}
	.modal_head {
	
		height:35px;
		color:white;
		text-align:left;
		font-size:20px;
		margin-top:20px;
	}
	
	#meetingLogCloseBtn {
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:80px;
		height:30px;
		position:absolute;
		right:30%;
		top:5%;
	}
	
	#meetingLogCreateBtn {
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:80px;
		height:30px;
		position:absolute;
		right:55%;
		top:5%;
	}
	
	.modal_content-box {
		width:90%;
		height:70%;
		font-size:40px;
		text-align:center;
		background: white;
		border-radius: 25px;
		margin-left: 28px;
		margin-top:3%;
	}
	
	.modal_button {
		width:100%;
		height:30%;
		float:right;
		position:relative;
		margin-top:5%;
	}
	
	h3 {
		margin-left: 20px;
	}
	
	h5 {
		font-size: 20px;
		float: left;
		margin-left: 30px;
		margin-right: 105px;
		margin-bottom: 0px;
		height: 20px;
	}
	
	form {
		width:100%;
		height:100%;
	}
	
	.meetingLogTitle {
		font-size:30px;	
	}
	
	.meetingLogDescription {
		margin-top: 10%;
	}
	
	
</style>
<title>Insert title here</title>
</head>
<body>
	<div id="whiteBoard">
	      <div class="meetinglogListHead">
				<div class="meetinglogListName">회의록</div>
				<div class="searchMeetinglog">
					<div class="search-area">
						<select id="searchCondition" name="searchCondition">
							<option value="title">제목</option>
							<option value="content">내용</option>
							<option value="writer">작성자</option>
						</select>
						<input type="search">
						<button type="submit">검색</button>
					</div>
				</div>
	      </div>
	      <div class="meetinglogBox">
	      		<div class="plusBox">
	      			<button type="button" class="plusBlackImage" id="plusBlackImage"></button>
	      		</div>
	      </div>
	</div>
	<div id="meetinglogCreateModal">
  
  		<div class="modal_content">
  		<form action="" method="post">
			<div class="modal_head">
			<h3>회의록 생성</h3>
	    	</div>
      		<div class="modal_content-box">
      			<input type="text" class="meetingLogTitle" name="meetingLogTitle" placeholder="MeetingLog-001">
      			<textarea class="meetingLogDescription" id="meetingLogDescription" rows="20" cols="49" placeholder="상세내용을 입력해주세요"></textarea>
      		</div>
      		<div class="modal_button">
	        	<button type="button" id="meetingLogCreateBtn">Ok</button>
	        	<button type="button" id="meetingLogCloseBtn">Cancel</button>
      		</div>
		</form>
   		</div>
  		<div class="modal_layer"></div>
	</div>
	
	<script>
		document.getElementById("plusBlackImage").onclick = function() {
	        document.getElementById("meetinglogCreateModal").style.display="block";
	    }

		document.getElementById("meetingLogCloseBtn").onclick = function() {
	        document.getElementById("meetinglogCreateModal").style.display="none";
	    }
		
		
	</script>

</body>
</html>