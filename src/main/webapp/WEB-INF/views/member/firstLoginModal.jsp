<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
		#modal {
			display: none;
			position:relative;
			width:100%;
			height:100%;
			z-index:1;
		}
		
		#modal h2 {
			margin:0;
		}
		#modal button {
			display:inline-block;
			width:100px;
			margin-left:calc(100% - 100px - 10px);
		}
		
		#modal .modalContent {
			width:700px;
			height:300px;
			margin:100px auto;
			/* padding:20px 10px; */
			background:#fff;
			border:2px solid #666;
		}
		
		#modal .modalLayer {
			position:fixed;
			top:0;
			left:0;
			width:100%;
			height:100%;
			background:rgba(0, 0, 0, 0.5);
			z-index:-1;
		}
		.modalHead {
			width:100.1%;
			height:35px;
			background-color:rgb(25,25,112);
			color:white;
			text-align:center;
			font-size:20px;
			float:right;
		}
		
		#modal_close_btn {
			background-color:rgb(25,25,112);
			color:white;
			text-align:center;
			cursor:pointer;
			width:80px;
			height:50px;
			position:absolute;
			right:30%;
			top:20%;
		}
		
		#modalOkBtn {
			background-color:rgb(25,25,112);
			color:white;
			text-align:center;
			cursor:pointer;
			width:80px;
			height:50px;
			position:absolute;
			right:44%;
			top:20%;
		}
		
		.modalContentMessage {
			width:100%;
			height:50%;
			float:right;
			padding:0px;
		}
		
		.modalButton {
			width:100%;
			height:30%;
			float:right;
			position:relative;
		}
		
		#contentHead {
			font-size:40px;
			text-align:center;
			font-weight: bold;
			margin-top:-10px;
		}
		
		#contentBody {
			font-size:25px;
			text-align:center;
			margin-top:-20px;
		}
</style>
<title>Insert title here</title>
</head>
<body>
<div id="root">
   
    <button type="button" id="modal_open_btn">모달 창 열기</button>
       
</div>

<div id="modal">
   
    <div class="modalContent">
	    <div class="modalHead">
	    	Alert Message
	    </div>
       	<div class="modalContentMessage">
  	   		<br><p id="contentHead">환영합니다!</p>
  	   		<br><p id="contentBody">처음 이용하기 전 <font color="red">프로필</font>을 설정해주세요!</p>
       	</div>
       	<div class="modalButton">
	        <button type="button" id="modalOkBtn">Ok</button>
       	</div>
       
    </div>
   
    <div class="modalLayer"></div>
</div>
<script>
	document.getElementById("modal").style.display="block";
   
    document.getElementById("modalOkBtn").onclick = function() {
        document.getElementById("modal").style.display="none";
        location.href = "firstLoginSettingModal.jsp";
    }
</script>
</body>
</html>