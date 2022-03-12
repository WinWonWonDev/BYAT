<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/menubar.jsp" %>
<%@ include file="../common/subMenu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
   const message = '${ requestScope.message }';
   if(message != null && message != '') {
      alert(message);
   }   
</script>
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
     	overflow-y:hidden;
     	overflow-x:hidden;
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
	form {
		width: 100%;
		height: 100%;
	}
	.backlog-area {
		position: absolute;
		top: -10px;
		left: 50px;
	}
	
	.backlog-box {
		position: absolute;
		top: 100px;
		border: 1px solid black;
		width: 250px;
		height: 400px;
		overflow-y:scroll;
		padding: none;
	}
	
	.backlog-add {
		background: url("/byat/resources/images/plusBlackButton.png") no-repeat;
		position: absolute;
		top: 65px;
		left: 220px;
		width: 25px;
		height: 25px;
		border: 0px;
		cursor: pointer;
	}
	#backlog-create-modal {
		display: none;
		position:relative;
		width:100%;
		height:100%;
		z-index:1;
	}
	#backlog-update-modal {
		display: none;
		position:relative;
		width:100%;
		height:100%;
		z-index:1;
	}

	#backlog-delete-modal {
		display: none;
		position:relative;
		width:100%;
		height:100%;
		z-index:1;
		position:absolute;
      	top:9%;
      	left:25%;
      	width:700px;
     	height:300px;
      	margin:100px auto;
      	background:#fff;
		border:2px solid #666;
	}
	#sprint-delete-modal {
		display: none;
		position:relative;
		width:100%;
		height:100%;
		z-index:1;
		position:absolute;
      	top:9%;
      	left:25%;
      	width:700px;
     	height:300px;
      	margin:100px auto;
      	background:#fff;
		border:2px solid #666;
	}
	#task-delete-modal {
		display: none;
		position:relative;
		width:100%;
		height:100%;
		z-index:1;
		position:absolute;
      	top:9%;
      	left:25%;
      	width:700px;
     	height:300px;
      	margin:100px auto;
      	background:#fff;
		border:2px solid #666;
	}
	#task-give-up-modal {
		display: none;
		position:relative;
		width:100%;
		height:100%;
		z-index:1;
		position:absolute;
      	top:9%;
      	left:25%;
      	width:700px;
     	height:300px;
      	margin:100px auto;
      	background:#fff;
		border:2px solid #666;
	}
	.system-message {
		width:100%;
		height:50%;
		float:right;
		font-size:30px;
		text-align:center;
	}
	.system-title {
		width:100.1%;
		height:35px;
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		font-size:20px;
 		float:right;
	}
	#backlog-delete {
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:80px;
		height:50px;
		position:absolute;
		right:55%;
		top: 65%;
	}
	#sprint-delete {
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:80px;
		height:50px;
		position:absolute;
		right:55%;
		top: 65%;
	}
	#task-delete {
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:80px;
		height:50px;
		position:absolute;
		right:55%;
		top: 65%;
	}
	#task-give-up {
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:80px;
		height:50px;
		position:absolute;
		right:55%;
		top: 65%;
	}
	#backlog-cloes-btn3 {
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:80px;
 		height:50px;
		position:absolute;
		right:30%;
 		top:65%;
	}
	#sprint-cloes-btn3 {
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:80px;
 		height:50px;
		position:absolute;
		right:30%;
 		top:65%;
	}
	#task-cloes-btn3 {
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:80px;
 		height:50px;
		position:absolute;
		right:30%;
 		top:65%;
	}
	#task-cloes-btn4 {
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:80px;
 		height:50px;
		position:absolute;
		right:30%;
 		top:65%;
	}
	#task-create-modal {
		display: none;
		position:relative;
		width:100%;
		height:100%;
		z-index:1;
	}
	#task-update-modal {
		display: none;
		position:relative;
		width:100%;
		height:100%;
		z-index:1;
	}
	#sprint-create-modal {
		display: none;
		position:relative;
		width:100%;
		height:100%;
		z-index:1;
	}
	#sprint-update-modal {
		display: none;
		position:relative;
		width:100%;
		height:100%;
		z-index:1;
	}
	#backlog-create-modal button {
		display:inline-block;
		width:100px;
		margin-left:calc(100% - 100px - 10px);
	}
	#task-create-modal button {
		display:inline-block;
		width:100px;
		margin-left:calc(100% - 100px - 10px);
	}
	.modal_content {
		width:500px;
		height:600px;
		margin:20px auto;
		background:#29428C;
		border:1px solid black;
		border-radius:25px;
	}
		
	#backlog-create-modal .modal_layer {
		position:fixed;
		top:0;
		left:0;
		width:100%;
		height:100%;
		background:rgba(0, 0, 0, 0.5);
		z-index:-1;
	}
	#backlog-update-modal .modal_layer {
		position:fixed;
		top:0;
		left:0;
		width:100%;
		height:100%;
		background:rgba(0, 0, 0, 0.5);
		z-index:-1;
	}
	#task-create-modal .modal_layer {
		position:fixed;
		top:0;
		left:0;
		width:100%;
		height:100%;
		background:rgba(0, 0, 0, 0.5);
		z-index:-1;
	}
	#task-update-modal .modal_layer {
		position:fixed;
		top:0;
		left:0;
		width:100%;
		height:100%;
		background:rgba(0, 0, 0, 0.5);
		z-index:-1;
	}
	#modal_layer2 {
		position:fixed;
		top:0;
		left:0;
		width:100%;
		height:100%;
		background:rgba(0, 0, 0, 0.5);
		z-index: 2;
		display: none;
	}
	.modal_head {
		margin-top: 10px;
		height:35px;
		color:white;
		text-align:left;
		font-size:20px;
	}
		
	#backlog-close-btn1 {
		right:30%;
		top:10%;
	}
	#backlog-close-btn2 {
		right:30%;
		top:10%;
	}
	#task-close-btn1 {
		right:30%;
		top:10%;
	}
	#sprint-close-btn1 {
		right:30%;
		top:10%;
	}
	#sprint-close-btn2 {
		right:30%;
		top:10%;
	}
	#task-close-btn2 {
		right:16%;
		top:10%;
	}
		
	#backlog-create {
		right:55%;
		top:10%;
	}
	#backlog-update {
		right:55%;
		top:10%;
	}
	#task-create {
		right:55%;
		top:10%;
	}
	#sprint-create {
		right:55%;
		top:10%;
	}
	#sprint-update {
		right:55%;
		top:10%;
	}
	#task-update {
		right:67%;
		top:10%;
	}
	#task-delete-open-btn {
		right: 41%;
		top: 10%;
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
		height:10%;
		float:right;
		position:relative;
	}
	.modal_button button {
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:80px;
		height:30px;
		position:absolute;
		margin-top: 20px;
	}
	h3 {
		margin-left: 20px;
	}
	.title {
		width: 400px;
		height: 40px;
		font-size: 20px;
		margin-top: 30px;
		margin-bottom: 5px;
	}
	.manager {
		width: 400px;
		height: 40px;
		font-size: 20px;
		float: none;
		margin-top: 15px;
		margin-bottom: 20px; 
	}
	.sprint-code {
		width: 400px;
		height: 40px;
		font-size: 20px;
		float: none;
		margin-top: 15px;
		border: 1px black solid;
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
	.description {
		margin-top: 20px;
		width: 400px;
		resize: none;
	}
	.task-participation-box {
		border-radius: 20px;
		border: 1px black solid;
		width: 40px;
		height: 40px;
		margin: 0px;
		float: left;
		font-size: 11px;
		text-align: center;
		line-height: 20px;
		word-spacing: 20px;
		font-weight: bold;
		cursor: pointer;
	}
	.sprint-area {
		position: absolute;
		top: -10px;
		left: 370px;
	}
	.backlog-head {
		font-size: 1.1cm;
		margin-bottom: 2px;
		margin-top: 40px;
	}
	.sprint-head {
		font-size: 1.1cm;
		margin-bottom: 2px;
		margin-top: 40px;
	}
	.sprint-box {
		position: absolute;
		top: 100px;
		width: 250px;
		height: 400px;
		border: 1px solid black;
		overflow-y:scroll;
	}
	.task-area {
		position: absolute;
		top: -10px;
		left: 680px;
		width: 750px;
	}
	.siren {
		position: absolute;
		top: 10px;
		left: -14px;
	}
	.task-add {
		position: absolute;
		top: 50px;
	}
	.sprint-start {
		position: absolute;
		top: 50px;
		left: 350px;
	}
	.sprint-end {
		position: absolute;
		top: 50px;
		left: 480px;
	}
	.sprint-add {
		position: absolute;
		top: 50px;
		left: 600px;
	}
	.task-box {
		position: absolute;
		top: 100px;
		width: 750px;
		height: 400px;
		border: 1px solid black;
		overflow-y:scroll;
	}
	.task-area button {
		width: 100px;
		height: 30px;
		color: white;
		background-color: #191970;
		cursor: pointer;
	}
	.backlog-item {
		background: #BDF6FE;
		width: 95%;
		height: 85px;
		margin-left: 7px;
		margin-top: 7px;
		margin-bottom: 7px;
		border-radius: 10px; 
		position: relative;
	}
	.sprint-item {
		background: #ACBAEE;
		width: 95%;
		height: 85px;
		margin-left: 7px;
		margin-top: 7px;
		margin-bottom: 7px;
		border-radius: 10px; 
		position: relative;
		cursor: pointer;
	}
	.task-participation{
		color: white;
		line-height: 5px;
		margin-top: 15px;
		font-weight: bold;
	}
	.backlog-item-title {
		position: absolute;
		top: 5px;
		left: 7px;
		background: none;
		width: 90%;
		margin-top: 2px;
		text-align: left;
		border: 0px;
	}
	.sprint-item-title {
		position: absolute;
		top: 5px;
		left: 7px;
		background: none;
		width: 70%;
		height: 70px;
		margin-top: 2px;
		text-align: left;
		border: 0px;
	}
	.backlog-status {
		position: absolute;
		top: 50px;
		left: 20px;
		width: 90px;
		height: 23px;
		text-align: center;
		border: 0px;
		font-size: 15px;
		border-radius: 4px;
		
	}
	#backlog-menu {
		position: absolute;
		top:50px;
		left: 180px;
		background: none;
		border: 0px;
		cursor: pointer;
	}
	.backlog-update-modal-open {
		position: absolute;
		top: 47%;
		left: 70%;
		font-size: 5px;
		background: #E0F2FA;
		border: 1px;
		border-radius: 2px;
		cursor: pointer;
	}
	.sprint-update-modal-open {
		position: absolute;
		top: 47%;
		left: 73%;
		font-size: 5px;
		background: #BD98ED;
		border: 1px;
		border-radius: 2px;
		cursor: pointer;
	}
	.backlog-delete-modal-open {
		position: absolute;
		top: 72%;
		left: 80%;
		font-size: 5px;
		background: #E0F2FA;
		border: 1px;
		border-radius: 2px; 
		cursor: pointer;
	}
	.sprint-delete-modal-open {
		position: absolute;
		top: 72%;
		left: 80%;
		font-size: 5px;
		background: #BD98ED;
		border: 1px;
		border-radius: 2px; 
		cursor: pointer;
	}
	.task-item {
		background: #BDF6FE;
		margin: 1.5%; 
		width: 30%;
		height: 25%;
		padding-top: 5px;
		border-radius: 10px;
		float: left;
		cursor: pointer;
	}
	.task-title {
		margin-top: 5px;;
		margin-left: 5px;
		margin-bottom: 5px;
		margin-right: 2px;
		width: 75%;
		height: 47%;
		float: left;
		font-size: 15px;
	}
	.task-item h5 {
		font-size: 10px;
		margin: 10px 5px 0px 5px;
		text-align: center;
		float: left;
	}
	.task-date-box {
		width: 50%;
		height: 35%;
		float: left;
		text-align: center;
	}
	.task-date-box div {
		width: 40%;
		height: 40%;
	}
	.task-status {
		top: 65px;
		left: 150px;
		margin-left: 5px;
		margin-top: 13px;
		border-radius: 5px;
		text-align: center;
		float: left;
		border: 1px solid black;
		width: 64px;
		height: 19px;
		font-size: 12px;
	}
	.modal_content-box h3 {
		margin-top: 0px;
		margin-bottom: 10px;
		font-size: 15px;
		color: #4FEA5E;
		text-align: left;
	}
	#task-add-members-modal {
		background:#29428C;
		width: 400px;
		height: 400px;
		position: absolute;
		top: 10%;
		left: 37%;
		display: block;
		border: 1px solid black;
		border-radius: 20px;
		z-index: 1050;
		display: none;
	}
	.modal_content_box2  {
		width:90%;
		height:72%;
		font-size:40px;
		text-align:center;
		background: white;
		border-radius: 25px;
		margin-left: 20px;
	}
	#task-members-list {
		margin-left: 300px;
		margin-bottom: 20px;
		background: #191970;
		height: 35px;
		width:100px;
		color: white;
	}
	#task-update {
		background: #191970;
		position: relative;
		top: 4%;
		left: 13%;
		width: 100px;
		height: 35px;
		color: white;
	}
	#task-delete-open-btn {
		background: #191970;
		position: relative;
		top: -10%;
		left: 40%;
		width: 100px;
		height: 35px;
		color: white;
	}
	#task-close-btn2 {
		background: #191970;
		position: relative;
		top: -10%;
		left: 47%;
		width: 100px;
		height: 35px;
		color: white;
	}
	#projectMembers {
		border: 2px solid black;
		width: 320px;
		height: 220px;
		float: left;
		margin-top:20px;
		margin-left: 10px;
		border-radius: 20px;
		overflow: auto;
	}
	.rightPointer {
		background: url("/byat/resources/images/rightPointer.png") no-repeat;
		width: 40px;
		height: 40px;
		float: left;
		margin-top: 100px;
		margin-left: 10px;
		margin-right: 10px;
	}
	#taskMembers {
		border: 2px solid black;
		width: 320px;
		float: left;
		border-radius: 10px;
		margin-top: 20px;
		overflow: auto;
		margin-left: 19px;
		font-size: 15px;
		border: 1px solid black;
		background: pink;
	}
	#taskMembers th {
		border-bottom: 2px solid black;
		background: pink;		
	}
	#taskMembers td {
		height: 30px;
		background: white;
		border: 1px solid white;
	}
	#taskMembersName {
		border-left: 2px solid black;
		border-right: 2px solid black;
	}
	#task-members-cloes {
		width: 100px;
		height: 35px;
		color: white;
		background: #191970;
		position: relative;
		left: 37%;
		top: 5%;
	}
	.switch-button {
		background: #CCCCCC;
		width: 70px;
		height: 30px;
		border: 1px solid #CCCCCC;
		border-radius: 15px;
	}
	.switch-btn {
		background: #FFFFFF;
		width: 30px;
		height: 30px;
		border: none;
		border-radius: 15px;
		position: relative; 
		left: 0px;
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
		.backlog-tasklize-button {
		position: absolute;
		top: 20%;
		left: 80%;
		font-size: 5px;
		background: #E0F2FA;
		border: 1px;
		border-radius: 2px;
		cursor: pointer;
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
	
	.taskAndIssueSlidebar:checked + .slider {
		background-color: #2196F3;
	}

	.taskAndIssueSlidebar:focus + .slider {
		box-shadow: 0 0 1px #2196F3;
	}

	.taskAndIssueSlidebar:checked + .slider:before {
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

	.sliderType {
		margin:0px;
		display:inline-block;
		font-size:20px;
		font-weight:bold;
		color:white;
	}
	
	.switchArea {
		position: absolute;
		margin-left:820px;
		top:8px;
	}
	
	#issue-modal-content {
		display: none;
	}
	
	.issueCreateModalTitle {
		width:82%;
		font-size:20px;
		padding-top:7px;
		padding-bottom:7px;
		margin-top:30px;
		border:1px solid black;
	}
	
	.issueMemberCreateArea {
		width:100%;
		height:32%;
		margin-top:15px;
	}
	
	.issueMemberCreateSelectArea {
		width:100%;
		height:18%;
	}
	
	.createMemberSelectBox {
		position:relative;
		width:185px;
		border:1px solid black;
		margin-left:183px;
		bottom:25px;
	}
	
	.issueCreateMemberList {
		background-color:white;
		position:relative;
		width:82%;
		height:140px;
		border:1px solid black;
		margin-left:37px;
		overflow-y:scroll;
	}
	
	.issueCreateMemberList::-webkit-scrollbar {
	
		border-radius:15px;
	}
	
	.issueCreateMemberList::-webkit-scrollbar-thumb {
		background-color: skyblue;
		border-radius:15px;
	}
	
	.issueCreateDescription {
		position:relative;
		border:1px solid black;
		resize: none;
		margin-top:10px;
		margin-right:3px;
	}
	
	.issueMember {
		position:relative;
		width:90%;
		height:30px;
		border:none;
		border-radius:15px;
		background-color:rgb(242,242,242);
		margin-top:15px;
		margin-left:25px;
	}
	
	.issueMemberInfo {
		font-size:13px;
		width:150px;
		position:relative;
		top:5px;
		left:15px;
	}
	
	.removeIssueMember {
		position:absolute;
		background-color:orangered;
		font-size:13px;
		color:white;
		border-radius:15px;
		width:70px;
		bottom:5px;
		margin-left:30px;
		cursor:pointer;
	}
	
	.selectBoxMessage {
		position:absolute;
		border:none;
		font-size:12px;
		color:yellowgreen;
		margin-left:35px;
	}
	
	.issueCreateBtn {
		margin-right:50px;
	}
	
	.modal_issue_create_button {
		width:100%;
		height:50px;
		float:right;
		position:relative;
		margin-top:5%;
	}
	
	.issueCreateBtn {
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:100px;
		height:30px;
		position:absolute;
		right:45%;
	}
	
	.issueCreateClose {
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:100px;
		height:30px;
		position:absolute;
		right:30%;
	}
	
</style>
</head>
<body>
	<div id="whiteBoard">
		<div class="backlog-area">
			<h2 class="backlog-head">BackLog</h2>
			<button class="backlog-add" id="backlog-create-open-btn"></button>
			<div class="backlog-box">

				<c:forEach items="${ backlogList }" var="backlog">
					<div class="backlog-item" align="center">
						<h4 class="backlog-item-title">${ backlog.title }</h4>
						<input type="hidden" name="projectCode" id="projectCode" value="${ requestScope.code }">
						<input type="hidden" name="code"  id="backlogCode" value="${ backlog.code }">
						<div class="backlog-status" id="backlog-status">${ backlog.progress }</div>
						<input type="button" class="backlog-tasklize-button" id="backlog-tasklize-button" value="담기" style="display: none;">
						<input type="button" class="backlog-update-modal-open" id="backlog-update-open-btn" value="조회  / 수정">
						<button type="button" class="backlog-delete-modal-open" id="backlog-delete-open-btn" onclick="location.href='${ pageContext.servletContext.contextPath }/backlog/remove?projectCode=${ requestScope.code }&code=${ backlog.code }'">삭제</button>
					</div>
				</c:forEach>

			</div>
		</div>
		<div class="sprint-area">
			<h2 class="sprint-head">Sprint</h2>
			<div class="sprint-box">
			
				<c:forEach items="${ sprintList }" var="sprint">
					<div class="sprint-item" align="center" id="sprintItem">
						<h4 class="sprint-item-title">${ sprint.title }</h4>
						<input type="hidden" name="code" id="projectCode" value="${ requestScope.code }">
						<input type="hidden" name="sprintCode" id="sprintCode" value="${ sprint.code }">
						<input type="hidden" id="sprintProgress" value="${ sprint.progress }">
						<input type="hidden" id="sprintBody" value="${ sprint.body }">
						<input type="button" class="sprint-update-modal-open" id="sprint-update-open-btn" value="조회  / 수정">
						<button type="button" class="sprint-delete-modal-open" id="sprint-delete-open-btn">삭제</button>
					</div>
				</c:forEach>
				
			</div>
		</div>
		<div class="task-area">
			<img class="siren" src="/byat/resources/images/siren.png">
			<input type="hidden" id="projectProgress" value="${ requestScope.projectProgress }">
			<button type="button" class="task-add" id="task-create-open-btn">Task 생성</button>
			<c:if test="${ requestScope.roleName ne '일반 멤버' }">
				<button type="button" class="sprint-start" id="sprint-start" value="${ requestScope.code }">Sprint 시작</button>
				<button type="button" class="sprint-end" id="sprint-end" value="${ requestScope.code }">Sprint 종료</button>
				<button type="button" class="sprint-add" id="sprint-create-open-btn">Sprint 생성</button>
			</c:if>
			<div class="task-box" id="task-box">
				
			</div>
		</div>
	</div>
	<!-- -------------------------------------------------------------모달창--------------------------------------------------------------------- -->
	<!-- 백로그 생성 모달창 -->
	<div id="backlog-create-modal">
   
   		<div class="modal_content">
   			<form action="${ pageContext.servletContext.contextPath }/backlog/regist" method="post">
			<div class="modal_head">
				<h3>백로그 생성</h3>
	    	</div>
       		<div class="modal_content-box">
       			<input type="text" class="title" name="title" placeholder="BacklogTitle" required="required">
       			<input type="hidden" id="projectCode" name="projectCode" value="${ requestScope.code }">
       			
       			<br clear="both">
       			<textarea class="description" id="backlogDescription" name="body" rows="13" cols="51" placeholder="BackLog Detail Description"></textarea>
       		</div>
       		<div class="modal_button">
	        	<button type="submit" id="backlog-create">Ok</button>
	        	<button type="button" id="backlog-close-btn1">Cancel</button>
       		</div>
   			</form>
    	</div>
    	<div class="modal_layer"></div>
	</div>
	
	<!-- 백로그 수정 모달창 -->
	<div id="backlog-update-modal">
   
   		<div class="modal_content">
   			<form action="${ pageContext.servletContext.contextPath }/backlog/modify" method="post">
				<div class="modal_head">
					<h3>백로그 수정</h3>
		    	</div>
       			<div class="modal_content-box">
       				<input type="hidden" id="backlogCode2" name="code">
       				<input type="text" class="title" name="title" id="backlogTitle" placeholder="BacklogTitle" required="required">
       				<input type="hidden" id="projectCode" name="projectCode" value="${ requestScope.code }">

       				<br clear="both">
       				<textarea class="description" name="body" id="backlogDescription2" rows="13" cols="51" placeholder="BackLog Detail Description"></textarea>
       			</div>
       			<div class="modal_button">
	        		<button type="submit" id="backlog-update">Ok</button>
	        		<button type="button" id="backlog-close-btn2">Cancel</button>
       			</div>
   			</form>
    	</div>
    	<div class="modal_layer"></div>
	</div>
	
	<!-- 긴급 태스크 생성 모달창 -->
	<div id="task-create-modal">
       
		<div class="switchArea">
			<p class="sliderType">Task</p>
			<label class="switch">
				<input type="checkbox" id="taskAndIssueSlidebar" class="taskAndIssueSlidebar">
				<span class="slider round"></span>
			</label>
			<p class="sliderType">Issue</p>
		</div>
       
        <div id="task-modal-content" class="modal_content">
            <form action="${ pageContext.servletContext.contextPath }/task/regist" method="post">
				<input type="hidden" id="projectCode" name="projectCode" value="${ requestScope.code }">
               	<input type="hidden" id="writer" name="memberNo" value="${ sessionScope.loginMember.no }">
           		<div class="modal_head">
               		<h3>태스크 생성</h3>
	            </div>
	            <div class="modal_content-box">
	              	<input type="text" class="title" name="title"  placeholder="Task Title" required="required">
	               
					<h5>시작일</h5>
					<h5>종료일</h5>
					<br clear="both">
					<input type='date' class="start-day" name='startDate' max="9999-12-31" required="required"/>
					<input type='date' class="end-day" name='endDate' max="9999-12-31" required="required"/>
					<textarea class="description" id="taskDescription" name="body" rows="13" cols="51" placeholder="Task Detail Description"></textarea>
	            </div>
	            <div class="modal_button">
					<button type="submit" id="task-create">Ok</button>
	             	<button type="button" id="task-close-btn1">Cancel</button>
	            </div>
			</form>
       	</div>
       	<div class="modal_content" id="issue-modal-content">
			<div class="modal_head">
				<h3>이슈 생성</h3>
			</div>
			<div class="modal_content-box">
				<form action="${ pageContext.servletContext.contextPath }/issue/regist" id="issueCreateForm" method="post">
					<input type="text" class="issueCreateModalTitle" id="issueCreateModalTitle" name="issueCreateTitle" placeholder="IssueTitle" required>
					<div class="issueMemberCreateArea">
						<div class="issueMemberCreateSelectArea">
							<div id="selectBoxMessage" class="selectBoxMessage">* [사번 이름 고유번호]</div>
							<select class="createMemberSelectBox" id="createMemberSelectBox">
								<option style="text-align:center">----------</option>
							</select>
						</div>
						<div class="issueCreateMemberList" id="issueCreateMemberList"></div>
						<textarea class="issueCreateDescription" id="issueCreateDescription" name="issueCreateBody" rows="13" cols="49" placeholder="상세내용을 입력해주세요" required></textarea>
					</div>
				</form>
			</div>
			<div class="modal_issue_create_button"> 
	        	<button type="button" id="issueCreateBtn" class="issueCreateBtn">Ok</button>
	        	<button type="button" id="issueCreateClose" class="issueCreateClose">Cancel</button>
			</div>
		</div>
    	<div class="modal_layer"></div>
	</div>
	
	<!-- 태스크 수정 모달창 -->
	<div id="task-update-modal">
   
   		<div class="modal_content">
   			<form action="${ pageContext.servletContext.contextPath }/task/modify" method="post">
				<div class="modal_head">
					<h3>태스크 수정</h3>
		    	</div>
	       		<div class="modal_content-box">
		   			<input type="hidden" name="projectCode" id="projectCode3">
		   			<input type="hidden" name="code" id="taskCode3">
	       			<input type="text" class="title" name="title" id="taskTitle3" placeholder="TaskTitle" required="required">
	       			
	       			<button type="button" id="task-members-list">구성원 목록</button>
					<h5>시작일</h5>
					<h5>종료일</h5>
	       			<br clear="both">
	       			<input type='date' class="start-day" name='startDate' id="taskStartDate3" max="9999-12-31"/>
	       			<input type='date' class="end-day" name='endDate' id="taskEndDate3" max="9999-12-31"/>
	       			<textarea class="description" id="taskDescription3" name="body" rows="13" cols="51" placeholder="Task Detail Description"></textarea>
	       		</div>
	        	<button type="submit" id="task-update">Ok</button>
   			</form>
	        <button type="button" id="task-delete-open-btn">Delete</button>
	        <button type="button" id="task-close-btn2">Cancel</button>
    	</div>
    	<div class="modal_layer"></div>
    	
		<!-- 태스크 구성원 목록 모달창 -->
		<div id="task-members-modal">
			<div id="task-add-members-modal">
				<div class="modal_head" style="margin-left: 10px;">
					<h3>태스크 구성원 목록</h3>
				</div>
				<div class="modal_content_box2">
					<table id="taskMembers">
						<thead>
							<tr>
								<th id="taskMembersId">회원 ID</th>
								<th id="taskMembersName">회원 이름</th>
								<th id="taskMembersPermit">회원 역할</th>
							</tr>
						</thead>
						<tbody>
						
						</tbody>
					</table>
				</div>
				<button type="button" id="task-members-cloes">Close</button>
			</div>
			<div id="modal_layer2"></div>
		</div>
	</div>
	

	<!-- 스프린트 생성 모달창 -->
	<div id="sprint-create-modal">
   
   		<div class="modal_content">
   			<form action="${ pageContext.servletContext.contextPath }/sprint/regist" method="post">
			<div class="modal_head">
				<h3>스프린트 생성</h3>
	    	</div>
       		<div class="modal_content-box" id="sprintCreate">
       			<input type="hidden" name="code" id="projectCode2" value="${ requestScope.code }"> 
       			<input type="text" class="title" name="title" placeholder="Sprint Title" required="required">
				<h5>시작일</h5>
				<h5>종료일</h5>
       			<br clear="both" style="height: 5px;">
       			<input type='date' class="start-day" id="sprint-startday" name='startDate'/>
       			<input type='date' class="end-day" id="sprint-endday" name='endDate'/>
       			<textarea class="description" id="sprintDescription" name='body' rows="13" cols="51" placeholder="sprint Detail Description"></textarea>
       		</div>
       		<div class="modal_button">
	        	<button type="submit" id="sprint-create">Ok</button>
	        	<button type="button" id="sprint-close-btn1">Cancel</button>
       		</div>
   			</form>
    	</div>
    	<div class="modal_layer"></div>
	</div>
	
	<!-- 스프린트 수정 모달창 -->
	<div id="sprint-update-modal">
   
   		<div class="modal_content">
   			<form action="${ pageContext.servletContext.contextPath }/sprint/modify" method="post">
				<div class="modal_head">
					<h3 id="sprintTitle1"></h3>
	    		</div>
       			<div class="modal_content-box" id="sprintUpdate">
       				<input type="hidden" name="writer" value="${ sessionScope.loginMember.name }">
       				<input type="hidden" name="projectCode" value="${ requestScope.code }">
       				<input type="text" class="title" name="title" id="sprintTitle2" placeholder="Sprint Title" required="required">
       				<input type="text" class="sprint-code" name="code" id="sprintCode2" readonly="readonly">
					<h3>스프린트 코드는 자동으로 생성됩니다.</h3>       		
					<h5>시작일</h5>
					<h5>종료일</h5>
       				<br clear="both" style="height: 5px;">
       				<input type='date' class="start-day" id="sprint-startday2" name='startDate'/>
       				<input type='date' class="end-day" id="sprint-endday2" name='endDate'/>
       				<textarea class="description" id="sprintDescription2" rows="13" cols="51" name="body" placeholder="sprint Detail Description"></textarea>
       			</div>
       			<div class="modal_button">
       				<c:if test="${ requestScope.roleName ne '일반 멤버' }">
		   		     	<button type="submit" id="sprint-update">Ok</button>
       				</c:if>
	   		     	<button type="button" id="sprint-close-btn2">Cancel</button>
       			</div>
   			</form>
    	</div>
    	<div class="modal_layer"></div>
	</div>
	
	<!-- ------------------------------------------------------시스템 메시지-------------------------------------------------- -->
	<!-- 백로그 삭제 동작을 확인하는 시스템 메시지창 -->
	<div id="backlog-delete-modal">
		<div class="system-title">
			System Message
		</div>
		<div class="system-message">
			<br>정말로 삭제하시겠습니까?
			<br>삭제한 백로그는 <font color="red">복구</font>할 수 없습니다.
		</div>
		<button type="button" id="backlog-delete">Ok</button>
		<button type="button" id="backlog-cloes-btn3">Cancel</button>
    </div>
    <!-- 스프린트 삭제 동작을 확인하는 시스템 메시지창 -->
	<div id="sprint-delete-modal">
		<div class="system-title">
			System Message
		</div>
		<div class="system-message">
			<br>정말로 삭제하시겠습니까?
			<br>삭제한 스프린트는 <font color="red">복구</font>할 수 없습니다.
		</div>
		<button type="button" id="sprint-delete">Ok</button>
		<button type="button" id="sprint-cloes-btn3">Cancel</button>
    </div>
    <!-- 태스크 삭제 동작을 확인하는 시스템 메시지창 -->
	<div id="task-delete-modal">
		<div class="system-title">
			System Message
		</div>
		<div class="system-message">
			<br>정말로 삭제하시겠습니까?
			<br>삭제한 태스크는 <font color="red">복구</font>할 수 없습니다.
		</div>    
		<button type="button" id="task-delete">Ok</button>
		<button type="button" id="task-cloes-btn3">Cancel</button>
    </div>
    <!-- 태스크 참가 포기 동작을 확인하는 시스템 메시지창 -->
	<div id="task-give-up-modal">
		<div class="system-title">
			System Message
		</div>
		<div class="system-message">
			<br>정말로 포기하시겠습니까?
		</div>    
		<button type="button" id="task-give-up">Ok</button>
		<button type="button" id="task-cloes-btn4">Cancel</button>
    </div>
<script>

	document.getElementById("selectIssueList").href = document.getElementById("selectIssueList").href + ${ requestScope.code };
	document.getElementById("selectMeetingLogList").href = document.getElementById("selectMeetingLogList").href + ${ requestScope.code };
	document.getElementById("selectRetrospectList").href = document.getElementById("selectRetrospectList").href + ${ requestScope.code };
	document.getElementById("selectSprintList").href = document.getElementById("selectSprintList").href + ${ requestScope.code };
	
	/* 이슈 부분 */
	let taskAndIssueSlidebar = $("#taskAndIssueSlidebar");
	
	taskAndIssueSlidebar.click(function(){
		//false면 task, true면 issue
		
		let taskModalContent = document.getElementById("task-modal-content");
		let issueModalContent = document.getElementById("issue-modal-content");
		
		if(taskAndIssueSlidebar[0].checked == true) {
			
			$.ajax({
				url : "/byat/issue/selectsprintmemberlist",
				type : "post",
				data : {
					projectCode : ${ requestScope.code }
				},
				success : function(data, status, xhr) {
					
					const selectBoxSprintMemberList = JSON.parse(data.selectsprintMemberList);
					
					issueModalSprintCode = document.createElement("input");
					issueModalSprintCode.setAttribute('type', 'hidden');
					issueModalSprintCode.setAttribute('id', 'issueModalSprintCode');
					issueModalSprintCode.setAttribute('value', selectBoxSprintMemberList[0].sprintCode);
					
					issueModalContent.appendChild(issueModalSprintCode);
					
					createMemberSelectBox = document.getElementById("createMemberSelectBox");
					
					for(let i in selectBoxSprintMemberList) {
						
						selectBoxSprintMember = document.createElement('option');
						selectBoxSprintMember.setAttribute('style', 'text-align:center');
						selectBoxSprintMember.innerText = selectBoxSprintMemberList[i].id + " " + selectBoxSprintMemberList[i].name + " " + selectBoxSprintMemberList[i].no;
						
						sprintMemberHiddenNo = document.createElement('input');
						sprintMemberHiddenNo.setAttribute('type', 'hidden');
						sprintMemberHiddenNo.setAttribute('id', 'sprintMemberHiddenNo');
						sprintMemberHiddenNo.setAttribute('value', selectBoxSprintMemberList[i].no);
						
						issueModalContent.appendChild(sprintMemberHiddenNo);
						createMemberSelectBox.appendChild(selectBoxSprintMember);
						
					}
					
				},
				error : function(xhr, status, error) {
					console.log(xhr);
				}
			});
			
			taskModalContent.style.display = 'none';
			issueModalContent.style.display = 'block';
		} else {
			taskModalContent.style.display = 'block';
			issueModalContent.style.display = 'none';
		}
	});
	
	document.getElementById("issueCreateClose").onclick = function() {

		taskAndIssueSlidebar[0].checked = false;
		document.getElementById("task-modal-content").style.display = 'block';
		document.getElementById("issue-modal-content").style.display = 'none';
		
		document.getElementById("task-create-modal").style.display = 'none';
		document.getElementById("issueCreateModalTitle").value = "";
		document.getElementById("issueCreateDescription").value = "";
		createMemberSelectBoxChildNodes = document.getElementById("createMemberSelectBox");
		
		issueCreateMemberListChildNodes = document.getElementById("issueCreateMemberList");
		
		while(issueCreateMemberListChildNodes.hasChildNodes()) {
            
			issueCreateMemberListChildNodes.removeChild(issueCreateMemberListChildNodes.firstChild);
            
         }
		
		while(createMemberSelectBoxChildNodes.hasChildNodes()) {
			
			createMemberSelectBoxChildNodes.removeChild(createMemberSelectBoxChildNodes.firstChild);
		}
		
		createMemberSelectBox = document.getElementById("createMemberSelectBox");
		
		boxFirstElement = document.createElement('option');
		boxFirstElement.setAttribute('style', 'text-align:center');
		boxFirstElement.innerText = "----------";
		
		createMemberSelectBox.appendChild(boxFirstElement);
	}
	
	$(document).ready(function() {
		
		$("#createMemberSelectBox").on('change', function() {

    		let issueModal = document.getElementById("issue-modal-content");
    		
			const $alreadyissueMember = document.querySelectorAll("#issueMemberInfo");
			
			let checkAlreadyIssueMember = false;
			
			if(this.value != "----------") {
				
				for(let a = 0; a < $alreadyissueMember.length; a++) {
					
					if($alreadyissueMember[a].innerText == this.value) {
						
						checkAlreadyIssueMember = true;
					}
				}
				
				if(checkAlreadyIssueMember) {
					
					alert("이미 선택된 멤버입니다.");
					
				} else {
					
					issueCreateMemberList = document.getElementById("issueCreateMemberList");
				
					issueMemberDiv = document.createElement('div');
    				issueMemberDiv.setAttribute('id', 'issueMember');
    				issueMemberDiv.setAttribute('class', 'issueMember');
    				
    				issueMemberInfoDiv = document.createElement('div');
    				issueMemberInfoDiv.setAttribute('id', 'issueMemberInfo');
    				issueMemberInfoDiv.setAttribute('class', 'issueMemberInfo');
    				issueMemberInfoDiv.setAttribute('name', 'issueMemberInfo');
    				issueMemberInfoDiv.innerText = this.value;
    				
    				issueMemberBody = document.createElement('input');
    				issueMemberBody.setAttribute('type', 'hidden');
    				issueMemberBody.setAttribute('id', 'issueMemberBody');
    				issueMemberBody.setAttribute('class', 'issueMemberBody');
    				issueMemberBody.setAttribute('name', 'issueMemberBody');
    				issueMemberBody.setAttribute('value', this.value);
    				
    				thisSprintProjectCode = document.createElement('input');
    				thisSprintProjectCode.setAttribute('type', 'hidden');
    				thisSprintProjectCode.setAttribute('name', 'projectCode');
    				thisSprintProjectCode.setAttribute('value', ${ requestScope.code });
    				
    				thisSprintCode = document.createElement('input');
    				thisSprintCode.setAttribute('type', 'hidden');
    				thisSprintCode.setAttribute('name', 'sprintCode');
    				thisSprintCode.setAttribute('value', issueModal.children[3].value);
    				
    				removeIssueMemberBtn = document.createElement('input');
    				removeIssueMemberBtn.setAttribute('type', 'button');
    				removeIssueMemberBtn.setAttribute('id', 'removeIssueMember');
    				removeIssueMemberBtn.setAttribute('class', 'removeIssueMember');
    				removeIssueMemberBtn.setAttribute('value', '제외하기');
    				
    				issueMemberDiv.appendChild(issueMemberInfoDiv);
    				issueMemberDiv.appendChild(issueMemberBody);
    				issueMemberDiv.appendChild(thisSprintProjectCode);
    				issueMemberDiv.appendChild(thisSprintCode);
    				issueMemberDiv.appendChild(removeIssueMemberBtn);
    				
    				issueCreateMemberList.appendChild(issueMemberDiv);
					
				}
			}
			
		});
    	
    	$(document).on("click", "#issueCreateBtn", function(event) { 
    		
    		createMemberSelectBox = document.getElementById("issueCreateMemberList");
			
    		const $sprintMemberHiddenNoList = document.querySelectorAll("#sprintMemberHiddenNo");
    		
    		let checkSprintMember = false;
    		
    		for(let i = 0; i < $sprintMemberHiddenNoList.length; i++) {
    			
    			if($sprintMemberHiddenNoList[i].value == "${ sessionScope.loginMember.no }") {
    				checkSprintMember = true;
    				break;
    			}
    			
    		}
    		
			if(createMemberSelectBox.children.length == 0) {
				
				alert("이슈 담당자를 지정해야합니다.");
				
			} else {
				
				if(checkSprintMember) {
					document.getElementById("issueCreateForm").submit();
				} else {
					alert("참여한 스프린트에서만 이슈를 생성하실 수 있습니다.");
				}
				
			}
    		
    	});
    	
    	$(document).on("click", "#removeIssueMember", function(event) { 
    		
    		alert("담당자 제외에 성공하셨습니다!");
			$(this).parent('div').remove();
    		
    	});
    	
    });
		
	/* 스프린트 시작 */
	if(document.getElementById("sprint-start")){
		
		document.getElementById("sprint-start").onclick = function() {
	
			const $projectCode = document.getElementById("sprint-start");
			location.href = "${ pageContext.servletContext.contextPath }/sprint/start?projectCode=" + $projectCode.value;
		}
	}
	

	/* 스프린트 종료 */
	if(document.getElementById("sprint-end")) {
			
		document.getElementById("sprint-end").onclick = function() {
	
			const $projectCode = document.getElementById("sprint-end");
	
			location.href = "${ pageContext.servletContext.contextPath }/sprint/end?projectCode=" + $projectCode.value;
		}
	}
	
	
	if(document.querySelectorAll("#sprintProgress")) {
		
		const $sprintProgress = document.querySelectorAll("#sprintProgress");
		const $sprintBody = document.querySelectorAll("#sprintBody");
		const $sprintItem = document.querySelectorAll("#sprintItem");
		const $sprintUpdateBtn = document.querySelectorAll("#sprint-update-open-btn");
		const $sprintDeleteBtn = document.querySelectorAll("#sprint-delete-open-btn");
		
		console.log($sprintProgress);
		
		for(let i = 0; i < $sprintProgress.length; i++) {
			
			if($sprintProgress[i].value == '완료'){
				
				console.log($sprintItem[i]);
				$sprintItem[i].style.background = "#C4C4C4";
				$sprintUpdateBtn[i].style.background = "#959a8f";
				$sprintDeleteBtn[i].style.background = "#959a8f";
			}
			
			if($sprintBody[i].value == null) {
				
				$sprintItem[i].style.border = "2px solid red";
			}
			
		}
	}
		
    document.getElementById("backlog-create-open-btn").onclick = function() {
		
    	if(document.getElementById("projectProgress").value == "완료"){
    		
    		alert("완료된 프로젝트는 백로그를 생성할 수 없습니다.");
    		
    	} else {
    		
	        document.getElementById("backlog-create-modal").style.display="block";
    	}
    }
    
    document.getElementById("backlog-close-btn1").onclick = function() {
        document.getElementById("backlog-create-modal").style.display = "none";
    }
    
    document.getElementById("task-create-open-btn").onclick = function() {
    	
    	if(document.getElementById("projectProgress").value == "완료"){
    		
    		alert("완료된 프로젝트는 태스크를 생성할 수 없습니다.");
    		
    	} else {
    		
	        document.getElementById("task-create-modal").style.display = "block";
    	}
    }
    
    document.getElementById("task-close-btn1").onclick = function() {
		document.getElementById("task-create-modal").style.display = "none";
    }
    
    document.getElementById("task-close-btn2").onclick = function() {
		document.getElementById("task-update-modal").style.display = "none";
    }
    
    document.getElementById("task-cloes-btn4").onclick = function() {
    	document.getElementById("task-give-up-modal").style.display = "none";
    }
    
    if(document.getElementById("backlog-update-open-btn")) {    	
		document.getElementById("backlog-update-open-btn").onclick = function() {
			document.getElementById("backlog-update-modal").style.display = "block";
		}
    }
    
    document.getElementById("backlog-close-btn2").onclick = function() {
        document.getElementById("backlog-update-modal").style.display = "none";
    }
    
    if(document.getElementById("backlog-delete-open-btn")) {
		document.getElementById("backlog-delete-open-btn").onclick = function() {
			document.getElementById("backlog-delete-modal").style.display = "block";
    	}    	
    }
    
    document.getElementById("backlog-cloes-btn3").onclick = function() {
    	document.getElementById("backlog-delete-modal").style.display = "none";
    }
    
    document.getElementById("task-delete-open-btn").onclick = function() {
        document.getElementById("task-delete-modal").style.display = "block";
    }
    
    document.getElementById("task-cloes-btn3").onclick = function() {
    	document.getElementById("task-delete-modal").style.display = "none";
    }
    
    if(document.getElementById("sprint-create-open-btn")) {
    	
	    document.getElementById("sprint-create-open-btn").onclick = function() {
	    	
	    	if(document.getElementById("projectProgress").value == "완료"){
	    		
	    		alert("완료된 프로젝트는 스프린트를 생성할 수 없습니다.");
	    		
	    	} else {
	    		
	        	document.getElementById("sprint-create-modal").style.display = "block";
	    	}
	    }
    }
    
    document.getElementById("sprint-close-btn1").onclick = function() {
    	document.getElementById("sprint-create-modal").style.display = "none";
    }
    
	if(document.querySelectorAll("#sprintUpdate input")) {
    	
    	const $sprintUpdateButtons = document.querySelectorAll("#sprint-update-open-btn");
    	const $sprintCodes = document.querySelectorAll("#sprintCode");
    	
    	console.log($sprintCodes);
    
    	for(let i = 0; i < $sprintUpdateButtons.length; i++){
 
    		$sprintUpdateButtons[i].onclick = function() {
    			
    			if("${ requestScope.roleName }" != '일반 멤버') {
    				
		    		document.getElementById("sprint-update-modal").style.display = "block";
	    			
		    		/* 스프린트 상세보기 */
		    		$.ajax({
		    			url: "/byat/sprint/detail",
		    			type: "get",
		    			data: { "sprintCode": $sprintCodes[i].value },
		    			dataType: "json",
		    			success: function(data, status, xhr){
		    			
		    				console.table(data);
		    				console.log(data.title);
							
		    				const $sprintCode1 = $("#sprintCode1");
		    				const $sprintTitle = $("#sprintTitle2");
		    				const $sprintCode2 = $("#sprintCode2");
		    				const $sprintStartDate = $("#sprint-startday2");
		    				const $sprintEndDate = $("#sprint-endday2");
		    				const $sprintBody = $("#sprintDescription2");
		    				
		    				$sprintCode1.val(data.code);
		    				$sprintTitle.val(data.title);
		    				$sprintCode2.val(data.code);
		    				$sprintStartDate.val(data.startDate);
		    				$sprintEndDate.val(data.endDate);
		    				$sprintBody.val(data.body);
		    				
		    			},
		    			error: function(xhr, status, error){
							console.log(xhr);
						}
		    		});
    			} else {
    				
    				alert("권한이 없습니다.");
    			}
    		};
    	
    	}
    }
    
	if(document.querySelectorAll("#sprint-delete-open-btn")) {
		
		const $sprintDeleteBtn = document.querySelectorAll("#sprint-delete-open-btn");
		const $sprintCode = document.querySelectorAll("#sprintCode");
		
		for(let i = 0; i < $sprintDeleteBtn.length; i++) {
			
			$sprintDeleteBtn[i].onclick = function() {
				
				if("${ requestScope.roleName }" != '일반 멤버') {
					
					document.getElementById("sprint-delete-modal").style.display = "block";
					
					document.getElementById("sprint-delete").value = $sprintCode[i].value;
					
				} else {
					
					alert("권한이 없습니다.");
				}
			}
		}
	}
	
	const $sprintDeleteButton = document.getElementById("sprint-delete");
	
	$sprintDeleteButton.onclick = function() {
		
		location.href ="${ pageContext.servletContext.contextPath }/sprint/remove?sprintCode=" + $sprintDeleteButton.value + "&projectCode=${ requestScope.code }"
	}
	
    document.getElementById("sprint-close-btn2").onclick = function() {
    	document.getElementById("sprint-update-modal").style.display = "none";
    }

    document.getElementById("sprint-cloes-btn3").onclick = function() {
    	document.getElementById("sprint-delete-modal").style.display = "none";
    }
    
    document.getElementById("task-members-cloes").onclick = function() {
    	document.getElementById("task-add-members-modal").style.display = "none";
    	document.getElementById("modal_layer2").style.display = "none";
    }
    
    if(document.querySelectorAll("#sprint-box div")){
    	
    	const $sprintItems = document.querySelectorAll("#sprintItem");
   		const $backlogTaskBtns = document.querySelectorAll("#backlog-tasklize-button");
		const $backCodes = document.querySelectorAll("#backlogCode");
    
   		console.log($sprintItems);
    
		for(let i = 0; i < $sprintItems.length; i++){	
    	
    		$sprintItems[i].onclick = (function(e) {
    		
				if(!$(e.target).hasClass("sprint-update-modal-open") && !$(e.target).hasClass("sprint-delete-modal-open")) {
				
					const $sprintCodes = document.querySelectorAll("#sprintCode");
					const $sprintProgress2 = document.querySelectorAll("#sprintProgress");
					
					console.log($sprintCodes[i].value);
					console.log('으응으으으으으으'+$sprintProgress2[i].value);
					
					for(let j = 0; j < $backlogTaskBtns.length; j++) {
	    				$backlogTaskBtns[j].style.display="block";
	    				
	    				/* 백로그 태스크화 */
						$backlogTaskBtns[j].onclick = function() {
	    					location.href="/byat/backlog/tasklize?projectCode=" + ${ requestScope.code } + "&sprintCode=" + $sprintCodes[i].value + "&backlogCode=" + $backCodes[j].value;
	    				};
	    			}
					
					/* 태스크 목록 조회*/
					$.ajax({
						url: "/byat/task/list",
						type: "get",
						data: { "sprintCode": $sprintCodes[i].value },
						success: function(data, status, xhr){
							console.table(data);
							
							const $taskBox = $("#task-box");
							$taskBox.html("");
							
							for(let k = 0; k < data.length; k++){
							
								const $taskDiv = $("<div class='task-div' id='task-div'>");
								let $taskItem = $("<div class='task-item' id='task-item'>");
								
								if(data[k].startDate == null || data[k].endDate == null || data[k].body == null) {
									
									$taskItem.css('border', '2px solid red');
								}
								const $sprintProgress = $("<input type='hidden' id='sprintProgress2'>").val($sprintProgress2[i].value);
								const $memberNo = $("<input type='hidden' name='memberNo' id='memberNo'>").val(data[k].memberNo);
								const $taskCode = $("<input type='hidden' name='code' id='taskCode'>").val(data[k].code);
								const $taskTitle = $("<div class='task-title' id='taskTitle' style='font-weight: bold;'>").text(data[k].title);
								
								let $taskParticipation = $("<div class='task-participation-box'>").val(data[k].code);
								
								const $br = $("<br clear='both'>");
								const $h5 = $("<h5 style='margin-bottom: 8px;'>").html("기간:");
								const $taskDateBox = $("<div class='task-date-box'>");
								
								if(data[k].startDate == null && data[k].endDate == null) {
									
									const $taskNullDate = $("<h5 id='task' style='font-size: 10px; width: 70px;'>").html('미정');	
									
									$taskDateBox.append($taskNullDate);
								} else {
									
									const $taskStartDate = $("<div id='taskStartDate' style='font-size: 10px; width: 70px;'>").text(data[k].startDate);
									const $taskEndDate = $("<div id='taskEndDate' style='font-size: 10px; width: 70px; margin: 5px;'>").text("~ " + data[k].endDate);
									
									$taskDateBox.append($taskStartDate);
									$taskDateBox.append($taskEndDate);
								}
								
								let $taskStatus = null;
									
								let $option1 = null;
								let $option2 = null;
								
								if(data[k].progress == '진행전'){
									
									$taskStatus = $("<div class='task-status' name='taskProgress'>").text("진행전").css('background', '#C4C4C4');
									
								} else if(data[k].progress == '진행중') {

									$taskStatus = $("<select class='task-status' id='task-status' name='taskProgress'>");
									$option1 = $("<option id='proceeding' value='진행중' selected='selected'>").text("진행중").css('background', '#F67B21');
									$option2 = $("<option id='finish' value='완료'>").text("완료").css('background', '#3988FF');
									
									$taskStatus.append($option1);
									$taskStatus.append($option2);
									
								} else if(data[k].progress == '완료') {

									$taskStatus = $("<select class='task-status' id='task-status' name='taskProgress'>");
									$option1 = $("<option id='proceeding' value='진행중'>").text("진행중").css('background', '#F67B21');
									$option2 = $("<option id='finish' value='완료' selected='selected'>").text("완료").css('background', '#3988FF');
									
									$taskStatus.append($option1);
									$taskStatus.append($option2);
									
								} else if(data[k].progress == '미완료') {
									
									$taskStatus = $("<div class='task-status' name='taskProgress'>").text("미완료").css('background', '#ffb6c1');
								}
								
								$taskDiv.append($taskItem);
								$taskItem.append($taskCode);
								$taskItem.append($taskTitle);
								$taskItem.append($sprintProgress);
								
								console.log(data[k].code);
								
								/* 태스크 참가 여부 조회 */
								$.ajax({
									url: "/byat/task/selectparticipation",
									type: "get",
									data: { "taskCode": data[k].code },
									success: function(data, status, xhr){
										console.table(data);
										
										if(data == 'Y'){
											
											$taskParticipation.css('background', 'red').css('color', 'white').text("참가 포기");
										} else if(data == 'N'){
											
											$taskParticipation.css('background', 'yellowgreen').css('color', 'white').css('line-height', '40px').text("참가");
										}
										
										$taskItem.append($taskParticipation);
										$taskItem.append($br);
										
										$taskItem.append($h5);
										
										$taskItem.append($taskDateBox);
										
										$taskItem.append($taskStatus);
										
										$taskBox.append($taskDiv);
										
										const $tasks = document.querySelectorAll("#task-div");
										console.log($tasks);
										
										for(let j = 0; j < $tasks.length; j++) {
											
											$tasks[j].onclick =  function(e){
												
												if(!$(e.target).hasClass("task-status") && !$(e.target).hasClass("task-participation-box") && !$(e.target).hasClass("task-participation")) {
													
													const $taskCodes = document.querySelectorAll("#taskCode");
													
													console.log($taskCodes[j].value);
													
													/* 태스크 상세 조회 */
													$.ajax({
														url: "/byat/task/detail",
														type: "get",
														data: { "taskCode": $taskCodes[j].value },
														success: function(data, status, xhr){
															console.table(data);
															
															document.getElementById("task-update-modal").style.display = "block";
															
															console.log(data.projectCode);
															console.log(data.title);
															const projectCode = $("#projectCode3");
															const taskCode = $("#taskCode3");
															const taskTitle = $("#taskTitle3");
															const taskMembersButton = $("#task-members-list");
															const taskStartDate = $("#taskStartDate3");
															const taskEndDate = $("#taskEndDate3");
															const taskBody = $("#taskDescription3");
															const taskRemoveButton = $("#task-delete");
															
															projectCode.val(data.projectCode);
															taskCode.val(data.code);
															taskTitle.val(data.title);
															taskMembersButton.val(data.code);
															taskStartDate.val(data.startDate);
															taskEndDate.val(data.endDate);
															taskBody.val(data.body);
															taskRemoveButton.val(data.code);
															
														},
														error: function(xhr, status, error){
															console.log(xhr);
														}
													});
												}
											}
										}
										
									},
									error: function(xhr, status, error){
										console.log(xhr);
									}
								});
							}
							
							
						},
						error: function(xhr, status, error){
							console.log(xhr);
						}
					});
				
				} 
			});
	    }
    }
    
    /* 태스크 상태 변경 */
    $(document).ready(function() {
    	
    	$(document).on("change", "#task-status", function(event) {
    		
    		const $taskCode = $("#taskCode").val();
    		console.log($(this).val());
			console.log($taskCode); 
			
			$.ajax({
				url: "/byat/task/progress",
				type: "get",
				data: { "taskCode": $taskCode,
						"taskProgress": $(this).val() },
				success: function(data, status, xhr){
					console.table(data);
					
				},
				error: function(xhr, status, error){
					console.log(xhr);
				}
			});
    	});
    });
	
    /* 태스크 참가 */
    $(document).ready(function() {
	    
		$(document).on("click", ".task-participation-box", function(event) {
		    
			const $sprintProgress2 = document.getElementById("sprintProgress2");
			
			console.log($sprintProgress2);
			
			if($sprintProgress2.value != '완료') {
				
				const $projectCode = $("#projectCode").val();
			    console.log($(this).text());		
	
			    if($(this).text() == '참가') {
			    	
			    	location.href = "${ pageContext.servletContext.contextPath }/task/participation?taskCode=" + $(this).val() + "&projectCode=" + $projectCode;
		    		
			    } else {
			    	
			    	document.getElementById("task-give-up-modal").style.display = "block";
			    	
			    	document.getElementById("task-give-up").value = $(this).val();
			    }
			} else {
				
				alert("종료된 스프린트 입니다.");				
			}
		}); 	
    });
    
    /* 태스크 참가 포기*/
    const $taskGiveUpBtn = document.getElementById("task-give-up");
    
    $taskGiveUpBtn.onclick = function() {
    	
    	const $projectCode = $("#projectCode").val();
	   	console.log($taskGiveUpBtn.value);
	   	
	   	location.href = "${ pageContext.servletContext.contextPath }/task/giveup?taskCode=" + $taskGiveUpBtn.value + "&projectCode=" + $projectCode;
    }
    
	/* 태스크 구성원 조회 */
	const $taskMembersList = document.getElementById("task-members-list");
	
	$taskMembersList.onclick = function() {
    	
    	document.getElementById("task-add-members-modal").style.display = "block";
    	document.getElementById("modal_layer2").style.display = "block";
    	
		console.log($taskMembersList.value);
		
		$.ajax({
			url: "/byat/task/members",
			type: "get",
			data: { "taskCode": $taskMembersList.value },
			success: function(data, status, xhr){
				console.table(data);
				
				const $table = $("#taskMembers tbody");
				$table.html("");
				
				for(let i in data){
					const $tr = $("<tr>");
					const $memberId = $("<td id='taskMembersId'>").text(data[i].memberId);
					const $memberName = $("<td style='border-left: 2px solid black; border-right:2px solid black;'>").text(data[i].memberName);
					const $permitName = $("<td id='taskMembersPermit'>").text(data[i].permitName);
					
					$tr.append($memberId);
					$tr.append($memberName);
					$tr.append($permitName);
					
					$table.append($tr);
				}
				
			},
			error: function(xhr, status, error){
				console.log(xhr);
			}
		});
			
    }
    
	/* 태스크 삭제 */
	const $taskDeleteBtn = document.getElementById("task-delete");
	
	$taskDeleteBtn.onclick = function(){
		
		const $projectCode = $("#projectCode").val();
		console.log($taskDeleteBtn.value);
		console.log("여기오냐");
		
		location.href = "${ pageContext.servletContext.contextPath }/task/remove?taskCode=" + $taskDeleteBtn.value + "&projectCode=" + $projectCode;
	}
	
	/* 백로그 관련 JS */
	if(document.querySelectorAll(".backlog-item")) {
		
		const $backlogUpdateBtns = document.querySelectorAll("#backlog-update-open-btn");
		const $backlogCodes = document.querySelectorAll("#backlogCode");

		console.log($backlogCodes);
		
		/* 백로그 수정 & 조회 */
		for(let i = 0; i < $backlogUpdateBtns.length; i++) {
			
			$backlogUpdateBtns[i].onclick = function() {
				
				document.getElementById("backlog-update-modal").style.display = "block";
				
				$.ajax({
					url: "/byat/backlog/detail",
					type: "get",
					data: { "backlogCode": $backlogCodes[i].value, "projectCode":  ${requestScope.code}  },
					dataType: "json",
					success: function(data, status, xhr) {
						
						console.table(data);
						
						const projectCode = $("#projectCode");
						const backlogTitle = $("#backlogTitle");
						const backlogBody = $("#backlogDescription2");
						const backlogCode = $("#backlogCode2");
						
						projectCode.val(data.projectCode);
						backlogTitle.val(data.title);
						backlogBody.val(data.body);
						backlogCode.val(data.code);
					},
					error: function(xhr, status, error) {
						console.log(xhr);
					}
				});
			}
		}
		
		const $backlogDeleteBtns = document.querySelectorAll("#backlog-delete-open-btn");
		console.log($backlogDeleteBtns);
		
		for(let i = 0; i < $backlogDeleteBtns.length; i++) {
			
			$backlogDeleteBtns[i].onclick = function() {
				
				document.getElementById("backlog-delete-modal").style.display = "block";
				const deleteBtn = $("#backlog-delete");
				deleteBtn.val($backlogCodes[i].value);
			};
		}
		
		const $backlogDeleteBtn = document.getElementById("backlog-delete");
		const $projectCode = $("#projectCode").val();
		console.log($backlogDeleteBtn);
		console.log($projectCode);
		
		$backlogDeleteBtn.onclick = function() {
			location.href = "${ pageContext.servletContext.contextPath }/backlog/remove?backlogCode=" + $backlogDeleteBtn.value + "&projectCode=" + $projectCode;
		}
		
	}
	
</script>
</body>
</html>