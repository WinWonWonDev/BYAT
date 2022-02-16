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
		
		#modal button {
			display:inline-block;
			width:100px;
			margin-left:calc(100% - 100px - 10px);
		}
		
		#modal .modal_content {
			width:500px;
			height:600px;
			margin:20px auto;
			background:#29428C;
			border:1px solid black;
			border-radius:25px;
		}
		
		#modal .modal_layer {
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
		}
		
		#modal_close_btn {
			background-color:rgb(25,25,112);
			color:white;
			text-align:center;
			cursor:pointer;
			width:80px;
			height:30px;
			position:absolute;
			right:30%;
			top:10%;
		}
		
		#modal_ok_btn {
			background-color:rgb(25,25,112);
			color:white;
			text-align:center;
			cursor:pointer;
			width:80px;
			height:30px;
			position:absolute;
			right:55%;
			top:10%;
		}
		
		.modal_content-box {
			width:90%;
			height:80%;
			font-size:40px;
			text-align:center;
			background: white;
			border-radius: 25px;
			margin-left: 28px;
		}
		
		.modal_button {
			width:100%;
			height:30%;
			float:right;
			position:relative;
		}
		h3 {
			margin-left: 20px;
		}
		.backlog-title {
			width: 400px;
			height: 40px;
			font-size: 20px;
		}
		.backlog-manager {
			width: 400px;
			height: 40px;
			font-size: 20px;
			float: none;
		}
		.glass{
			background:url("/byat/resources/images/glass.png") no-repeat;
			width: 30px;
			height: 30px;
			border: 0px;
			position: relative;
			top: -50px;
			left: -3%;
			cursor: pointer;
		}
		h5 {
			font-size: 20px;
			float: left;
			margin-left: 30px;
			margin-right: 105px;
			margin-bottom: 0px;
			height: 20px;
			
		}
		.start-day {
			margin-right: 50px;
		}
		.end-day {
			margin-right: 50px;
		}
		.backlog-description {
			margin-top: 20px;
		}
</style>

<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body style="overflow:hidden;">
<div id="root">
   
    <button type="button" id="modal_open_btn">모달 창 열기</button>
       
</div>

<div id="modal">
   
    <div class="modal_content">
	    <div class="modal_head">
		    <h3>백로그 생성</h3>
	    </div>
       	<div class="modal_content-box">
       		<input type="text" class="backlog-title" placeholder="BacklogTitle">
       		
       		<input type="text" class="backlog-manager" placeholder="Manager">
       		
       		<input type="button" class="glass" id="searchMember">
			<h5>시작일</h5>
			<h5>종료일</h5>
       		<br clear="both">
       		<input type='date' class="start-day" name='backlogStartday'/>
       		<input type='date' class="end-day" name='backlogEndday'/>
       		<textarea class="backlog-description" id="backlogDescription" rows="13" cols="51"></textarea>
       	</div>
       	<div class="modal_button">
	        <button type="button" id="modal_ok_btn">Ok</button>
	        <button type="button" id="modal_close_btn">Cancel</button>
       	</div>
    </div>
    <div class="modal_layer"></div>
</div>
<script>

    document.getElementById("modal_open_btn").onclick = function() {
        document.getElementById("modal").style.display="block";
    }
   
    document.getElementById("modal_close_btn").onclick = function() {
        document.getElementById("modal").style.display="none";
    }
    
</script>
</body>
</html>