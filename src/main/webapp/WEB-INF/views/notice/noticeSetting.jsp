<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/menubar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
	
		.switch {
		position: relative;
		display: inline-block;
		width: 60px;
		height: 34px;
		vertical-align:middle;
	}
	
	.switch input {
		display:none;
	}
	
	.slider {
		position: absolute;
		cursor: pointer;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		background-color: #ccc;
		-webkit-transition: .4s;
		transition: .4s;
	}
	
	.slider:before {
		position: absolute;
		content: "";
		height: 26px;
		width: 26px;
		left: 4px;
		bottom: 4px;
		background-color: white;
		-webkit-transition: .4s;
		transition: .4s;
	}
	
	input:checked + .slider {
		background-color: #2196F3;
	}

	input:focus + .slider {
		box-shadow: 0 0 1px #2196F3;
	}

	input:checked + .slider:before {
		-webkit-transform: translateX(26px);
		-ms-transform: translateX(26px);
		transform: translateX(26px);
	}

	.slider.round {
	  	border-radius: 34px;
	}

	.slider.round:before {
	  	border-radius: 50%;
	}

	p {
		margin:0px;
		display:inline-block;
		font-size:25px;
		font-weight:bold;
		color:rgb(25,25,122);
	}
	
	.switchArea {
		float:right;
		margin-right:5%;
	}	
	
	#settingTitle {
		float:left;
		margin-left:5%;
	}
	
	.leftNoticeSetting {
		float:left;
		font-size:50px;
		text-align:center;
		position:absolute;
		width:100%;
	}
	
	.rightNoticeSetting {
		float:left;
		font-size:50px;
		text-align:center;
		position:absolute;
		width:100%;
	}
	
	.projectNoticeSettingLine {
		height:15%;
		width:30%;
		position:absolute;
		top:15%;
		left:13%;
		color:rgb(25,25,122);
		border-bottom:1px solid black;
	}
	
	.sprintNoticeSettingLine {
		height:15%;
		width:30%;
		position:absolute;
		top:15%;
		right:17%;
		color:rgb(25,25,122);
		border-bottom:1px solid black;
	}
	
	.backlogNoticeSettingLine {
		height:15%;
		width:30%;
		position:absolute;
		top:45%;
		left:13%;
		color:rgb(25,25,122);
		border-bottom:1px solid black;
	}
	
	.meetinglogNoticeSettingLine {
		height:15%;
		width:40%;
		position:absolute;
		top:45%;
		right:7%;
		color:rgb(25,25,122);
		border-bottom:1px solid black;
	}
	
	.issueNoticeSettingLine {
		height:15%;
		width:30%;
		position:absolute;
		top:75%;
		left:13%;
		color:rgb(25,25,122);
		border-bottom:1px solid black;
	}
	
	.calendarNoticeSettingLine {
		height:15%;
		width:30%;
		position:absolute;
		top:75%;
		right:17%;
		color:rgb(25,25,122);
		border-bottom:1px solid black;
	}
	
</style>
<title>Insert title here</title>
</head>
<body style="overflow:hidden;">
	<div id="whiteBoard">
		<div class="projectNoticeSettingLine">
			<div class="leftNoticeSetting">
				<span id="settingTitle">Project</span>
				<div class="switchArea">
					<p>OFF</p>
					<label class="switch">
						<input type="checkbox" id="projectCheck">
						<span class="slider round"></span>
					</label>
					<p>ON</p>
				</div>
			</div>
		</div>
		<div class="sprintNoticeSettingLine">
			<div class="rightNoticeSetting">
				<span id="settingTitle">Sprint</span>
				<div class="switchArea">
					<p>OFF</p>
					<label class="switch">
						<input type="checkbox" id="sprintCheck">
						<span class="slider round"></span>
					</label>
					<p>ON</p>				
				</div>
			</div>		
		</div>
		<div class="backlogNoticeSettingLine">
			<div class="leftNoticeSetting">
				<span id="settingTitle">Backlog</span>
				<div class="switchArea">
					<p>OFF</p>
					<label class="switch">
						<input type="checkbox" id="backlogCheck">
						<span class="slider round"></span>
					</label>
					<p>ON</p>				
				</div>
			</div>	
		</div>
		<div class="meetinglogNoticeSettingLine">
			<div class="rightNoticeSetting">
				<span id="settingTitle">Meeting Log</span>
				<div class="switchArea">
					<p>OFF</p>
					<label class="switch">
						<input type="checkbox" id="mettingLogCheck">
						<span class="slider round"></span>
					</label>
					<p>ON</p>				
				</div>
			</div>	
		</div>
		<div class="issueNoticeSettingLine">
			<div class="rightNoticeSetting">
				<span id="settingTitle">Issue</span>
				<div class="switchArea">
					<p>OFF</p>
					<label class="switch">
						<input type="checkbox" id="issueCheck">
						<span class="slider round"></span>
					</label>
					<p>ON</p>				
				</div>
			</div>	
		</div>
		<div class="calendarNoticeSettingLine">
			<div class="rightNoticeSetting">
				<span id="settingTitle">Calendar</span>
				<div class="switchArea">
					<p>OFF</p>
					<label class="switch">
						<input type="checkbox" id="calendarCheck">
						<span class="slider round"></span>
					</label>
					<p>ON</p>				
				</div>
			</div>	
		</div>
	</div>
	<script>
		var check = $("#projectCheck");
		check.click(function(){
			console.log(check);
		});
	</script>
</body>
</html>