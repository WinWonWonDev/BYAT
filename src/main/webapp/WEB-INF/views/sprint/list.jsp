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
	}
	.task-participation-box {
		border-radius: 20px;
		border: 1px red solid;
		width: 40px;
		height: 40px;
		margin: 0px;
		float: left;
		font-size: 11px;
		text-align: center;
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
						<input type="button" class="backlog-update-modal-open" id="backlog-update-open-btn" value="조회  / 수정">
						<input type="button" class="backlog-delete-modal-open" id="backlog-delete-open-btn" value="삭제">
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
						<input type="button" class="sprint-update-modal-open" id="sprint-update-open-btn" value="조회  / 수정">
						<button type="button" class="sprint-delete-modal-open" id="sprint-delete-open-btn" onclick="location.href='${ pageContext.servletContext.contextPath }/sprint/remove?sprintCode=${ sprint.code }&projectCode=${ requestScope.code }'">삭제</button>
					</div>
					
				</c:forEach>
				
			</div>
		</div>
		<div class="task-area">
			<img class="siren" src="/byat/resources/images/siren.png">
			<button type="button" class="task-add" id="task-create-open-btn">Task 생성</button>
			<button type="button" class="sprint-start" id="sprint-start" value="${ requestScope.code }">Sprint 시작</button>
			<button type="button" class="sprint-end" id="sprint-end" value="${ requestScope.code }">Sprint 종료</button>
			<input type="hidden" id="projectProgress" value="${ requestScope.projectProgress }">
			<button type="button" class="sprint-add" id="sprint-create-open-btn">Sprint 생성</button>
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
       			<input type="text" class="title" name="title" placeholder="BacklogTitle">
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
       				<input type="text" class="title" name="backlogTitle" placeholder="BacklogTitle">
       				<input type="hidden" id="projectCode" name="projectCode" value="${ requestScope.code }">

       				<br clear="both">
       				<textarea class="description" id="backlogDescription" rows="13" cols="51" placeholder="BackLog Detail Description"></textarea>
       			</div>
       			<div class="modal_button">
	        		<button type="button" id="backlog-update">Ok</button>
	        		<button type="button" id="backlog-close-btn2">Cancel</button>
       			</div>
   			</form>
    	</div>
    	<div class="modal_layer"></div>
	</div>
	
	<!-- 긴급 태스크 생성 모달창 -->
	<div id="task-create-modal">
 		
 		
   		<div id="task-modal-content" class="modal_content">
   			<form action="${ pageContext.servletContext.contextPath }/task/regist" method="post">
   				<input type="hidden" id="projectCode" name="projectCode" value="${ requestScope.code }">
   				<input type="hidden" id="writer" name="memberNo" value="${ sessionScope.loginMember.no }">
				<div class="modal_head">
					<h3>태스크 생성</h3>
	   	 		</div>
       			<div class="modal_content-box">
       				<input type="text" class="title" name="title"  placeholder="Task Title">
       				
					<h5>시작일</h5>
					<h5>종료일</h5>
       				<br clear="both">
       				<input type='date' class="start-day" name='startDate'/>
       				<input type='date' class="end-day" name='endDate'/>
       				<textarea class="description" id="taskDescription" name="body" rows="13" cols="51" placeholder="Task Detail Description"></textarea>
       			</div>
       			<div class="modal_button">
	        		<button type="submit" id="task-create">Ok</button>
	        		<button type="button" id="task-close-btn1">Cancel</button>
       			</div>
   			</form>
    	</div>
    	<div id="issue-modal-content" class="modal_content" style="display: none;">
    		<form action="${ pageContext.servletContext.contextPath }/issue/regist" method="post">
    			<input type="hidden" id="projectCode" name="projectCode" value="${ requestScope.code }">
    			<div class="modal-head">
    				<h3>이슈 생성</h3>
    			</div>
    			<div class="modal_content_box">
    				<input type="text" class="title" name="title" placeholder="Issue Title">
    				
    				<!-- 담당자 지정 넣어주는곳  -->
    				
    				<textarea class="description" id="issueDescription" name="body" rows="13" cols="51" placeholder="Issue Detail Description"></textarea>
    			</div>
    		</form>
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
	       			<input type="text" class="title" name="title" id="taskTitle3" placeholder="TaskTitle">
	       			
	       			<button type="button" id="task-members-list">구성원 목록</button>
					<h5>시작일</h5>
					<h5>종료일</h5>
	       			<br clear="both">
	       			<input type='date' class="start-day" name='startDate' id="taskStartDate3"/>
	       			<input type='date' class="end-day" name='endDate' id="taskEndDate3"/>
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
       			<input type="text" class="title" name="title" placeholder="Sprint Title">
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
       				<input type="text" class="title" name="title" id="sprintTitle2" placeholder="Sprint Title">
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
	   		     	<button type="submit" id="sprint-update">Ok</button>
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
		</div>
		<button type="button" id="backlog-delete">Ok</button>
		<button type="button" id="backlog-cloes-btn3">Cancel</button>
    </div>
    <!-- 태스크 삭제 동작을 확인하는 시스템 메시지창 -->
	<div id="task-delete-modal">
		<div class="system-title">
			System Message
		</div>
		<div class="system-message">
			<br>정말로 삭제하시겠습니까?
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
		<button type="button" id="task-cloes-btn3">Cancel</button>
    </div>
<script>

	/* 인근이형이 추가하라고 한거 */
	/* document.getElementById("selectIssueList").href = document.getElementById("selectIssueList").href + ${ requestScope.code };*/
	
	/* 수빈이형이 추가하라고 한거*/
	/* document.getElementById("selectMeetingLogList").href = document.getElementById("selectMeetingLogList").href + ${ requestScope.code }; */
	
	/* 스프린트 시작 */
	document.getElementById("sprint-start").onclick = function() {
		
		if(document.getElementById("projectProgress").value == "완료") {
			
			alert("완료된 프로젝트입니다.");
		} else if (document.getElementById("projectProgress").value == "미진행") {
			
			alert("해당 프로젝트가 진행중이 아닙니다.");
		} else {
			
			let result = 0;
			
			const $projectCode = document.getElementById("sprint-start");
			
			console.log($projectCode.value);
			/* 태스크들중에  미기입된 항목이 있는지 체크 */
			$.ajax({
				url: "/byat/task/check",
				type: "get",
				data: { "projectCode": $projectCode.value },
				async : false,
				success: function(data, status, xhr){
					console.table(data);
					
					for(let i = 0; i < data.size; i++) {
					
						console.log(data[i].title);
						console.log(data[i].startDate);
						console.log(data[i].endDate);
						console.log(data[i].body);
						
						if(data[i].title == null || data[i].startDate == null || data[i].endDate == null || data[i].body == null) {
							
							result = 1;
						}
					}
				},
				error: function(xhr, status, error){
					console.log(xhr);
				}
			});
			
			if(result > 0) {

				alert("아직 작성완료하지못한 태스크가 있습니다.")
			} else {
				
				location.href = "${ pageContext.servletContext.contextPath }/sprint/start?projectCode=" + $projectCode.value;
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
    
    document.getElementById("task-cloes-btn3").onclick = function() {
    	document.getElementById("task-give-up-modal").style.display = "none";
    }
    
    document.getElementById("backlog-update-open-btn").onclick = function() {
        document.getElementById("backlog-update-modal").style.display = "block";
    }
    
    document.getElementById("backlog-close-btn2").onclick = function() {
        document.getElementById("backlog-update-modal").style.display = "none";
    }
    
    document.getElementById("backlog-delete-open-btn").onclick = function() {
        document.getElementById("backlog-delete-modal").style.display = "block";
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
    
    document.getElementById("sprint-create-open-btn").onclick = function() {
    	
    	if(document.getElementById("projectProgress").value == "완료"){
    		
    		alert("완료된 프로젝트는 스프린트를 생성할 수 없습니다.");
    		
    	} else {
    		
        	document.getElementById("sprint-create-modal").style.display = "block";
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
    		
    		};
    	
    	}
    }
    
    document.getElementById("sprint-close-btn2").onclick = function() {
    	document.getElementById("sprint-update-modal").style.display = "none";
    }

    document.getElementById("task-members-cloes").onclick = function() {
    	document.getElementById("task-add-members-modal").style.display = "none";
    	document.getElementById("modal_layer2").style.display = "none";
    }
    
    if(document.querySelectorAll("#sprint-box div")){
    	
    	const $sprintItems = document.querySelectorAll("#sprintItem");
    
   		console.log($sprintItems);
    
		for(let i = 0; i < $sprintItems.length; i++){	
    	
    		$sprintItems[i].onclick = (function(e) {
    		
				if(!$(e.target).hasClass("sprint-update-modal-open") && !$(e.target).hasClass("sprint-delete-modal-open")) {
				
					const $sprintCodes = document.querySelectorAll("#sprintCode");
			
					console.log($sprintCodes[i].value);
					
					/* 태스크 목록 조회*/
					$.ajax({
						url: "/byat/sprint/selecttasks",
						type: "get",
						data: { "sprintCode": $sprintCodes[i].value },
						success: function(data, status, xhr){
							console.table(data);
							
							const $taskBox = $("#task-box");
							$taskBox.html("");
							
							for(let i = 0; i < data.length; i++){
							
								const $taskDiv = $("<div class='task-div' id='task-div'>");
								const $taskItem = $("<div class='task-item' id='task-item'>");
								const $memberNo = $("<input type='hidden' name='memberNo' id='memberNo'>").val(data[i].memberNo);
								const $taskCode = $("<input type='hidden' name='code' id='taskCode'>").val(data[i].code);
								const $taskTitle = $("<div class='task-title' id='taskTitle' style='font-weight: normal;'>").text(data[i].title);
								
								let $taskParticipation = $("<div class='task-participation-box'>").val(data[i].code);
								
								const $br = $("<br clear='both'>");
								const $h5 = $("<h5 style='margin-bottom: 8px;'>").html("기간:");
								const $taskDateBox = $("<div class='task-date-box'>");
								
								const $taskStartDate = $("<div id='taskStartDate' style='font-size: 10px; width: 70px;'>").text(data[i].startDate);
								const $taskEndDate = $("<div id='taskEndDate' style='font-size: 10px; width: 70px;'>").text("~" + data[i].endDate);
								
								const $taskStatus = $("<select class='task-status' id='task-status' name='taskProgress'>");
								
								let $option1 = null;
								let $option2 = null;
								let $option3 = null;
								
								if(data[i].progress == '진행전'){
									
									$option1 = $("<option id='before' value='Before' selected='selected'>").text("진행전").css('backgorund', '#C4C4C4');
									$option2 = $("<option id='proceeding' value='Proceeding'>").text("진행중").css('background', '#F67B21');
									$option3 = $("<option id='finish' value='Finish'>").text("완료").css('background', '#3988FF');
									
								} else if(data[i].progress == '진행중') {

									$option1 = $("<option id='before' value='Before'>").text("진행전").css('backgorund', '#C4C4C4');
									$option2 = $("<option id='proceeding' value='Proceeding' selected='selected'>").text("진행중").css('background', '#F67B21');
									$option3 = $("<option id='finish' value='Finish'>").text("완료").css('background', '#3988FF');
									
								} else if(data[i].progress == '완료') {

									$option1 = $("<option id='before' value='Before'>").text("진행전").css('backgorund', '#C4C4C4');
									$option2 = $("<option id='proceeding' value='Proceeding'>").text("진행중").css('background', '#F67B21');
									$option3 = $("<option id='finish' value='Finish' selected='selected'>").text("완료").css('background', '#3988FF');
									
								}						
								$taskDiv.append($taskItem);
								$taskItem.append($taskCode);
								$taskItem.append($taskTitle);
								
								console.log(data[i].code);
								
								/* 태스크 참가 여부 조회 */
								$.ajax({
									url: "/byat/task/selectparticipation",
									type: "get",
									data: { "taskCode": data[i].code },
									success: function(data, status, xhr){
										console.table(data);
										
										if(data == 'Y'){
											
											$taskParticipation.css('background', 'red').css('color', 'white').text("참가 포기");
										} else if(data == 'N'){
											
											$taskParticipation.css('background', 'yellowgreen').css('color', 'white').text("참가");
										}
										
										$taskItem.append($taskParticipation);
										$taskItem.append($br);
										
										$taskItem.append($h5);
										
										$taskDateBox.append($taskStartDate);
										$taskDateBox.append($taskEndDate);
										$taskItem.append($taskDateBox);
										
										$taskStatus.append($option1);
										$taskStatus.append($option2);
										$taskStatus.append($option3);
										$taskItem.append($taskStatus);
										
										$taskBox.append($taskDiv);
										
										const $tasks = document.querySelectorAll("#task-div");
										console.log($tasks);
										
										for(let j = 0; j < $tasks.length; j++) {
											
											$tasks[i].onclick =  function(e){
												
												if(!$(e.target).hasClass("task-status") && !$(e.target).hasClass("task-participation-box") && !$(e.target).hasClass("task-participation")) {
													
													const $taskCodes = document.querySelectorAll("#taskCode");
													
													console.log($taskCodes[i].value);
													
													/* 태스크 상세 조회 */
													$.ajax({
														url: "/byat/task/detail",
														type: "get",
														data: { "taskCode": $taskCodes[i].value },
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
															taskGiveUp.val(data.code);
															
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
    
    /* 태스크 참가 */
    $(document).ready(function() {
	    
		$(document).on("click", ".task-participation-box", function(event) {
		    
			const $projectCode = $("#projectCode").val();
		    console.log($(this).text());		

		    if($(this).text() == '참가') {
		    	
		    	location.href = "${ pageContext.servletContext.contextPath }/task/participation?taskCode=" + $(this).val() + "&projectCode=" + $projectCode;
	    		
		    } else {
		    	
		    	document.getElementById("task-give-up-modal").style.display = "block";
		    	
		    	document.getElementById("task-give-up").value = $(this).val();
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
    
	/* 태스크 상태 변경 */
	$(document).ready(function() {
		
		$(document).on("onchange", ".task-status", function(event) {
			
			console.log("오셈");
			
			const $taskCode = $("#taskCode").val();
			
			console.log($taskCode);
		});
		
	});
	    
	/* 태스크 삭제 */
	const $taskDeleteBtn = document.getElementById("task-delete");
	
	$taskDeleteBtn.onclick = function(){
		
		const $projectCode = $("#projectCode").val();
		console.log($taskDeleteBtn.value);
		console.log("여기오냐");
		
		location.href = "${ pageContext.servletContext.contextPath }/task/remove?taskCode=" + $taskDeleteBtn.value + "&projectCode=" + $projectCode;
	}
	
	
</script>
</body>
</html>