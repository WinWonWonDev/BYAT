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
	.task-manager-box {
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
	.task-manager{
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
</style>
</head>
<body>
	<div id="whiteBoard">
		<div class="backlog-area">
			<h2 class="backlog-head">BackLog</h2>
			<button class="backlog-add" id="backlog-create-open-btn"></button>
			<div class="backlog-box">
			
			
				<!-- c:foreach아니면 jsp로 -->
				<div class="backlog-item" align="center">
					<h4 class="backlog-item-title">크큭</h4>
					<div class="backlog-status" id="backlog-status" name="backlogStatus" onchange="chageLangSelect()"></div>
					<input type="button" class="backlog-update-modal-open" id="backlog-update-open-btn" value="조회  / 수정">
					<input type="button" class="backlog-delete-modal-open" id="backlog-delete-open-btn" value="삭제">
				</div>
				
			
			
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
			<button type="button" class="sprint-start">Sprint 시작</button>
			<button type="button" class="sprint-end">Sprint 종료</button>
			<input type="hidden" id="projectProgress" value="${ requestScope.projectProgress }">
			<button type="button" class="sprint-add" id="sprint-create-open-btn">Sprint 생성</button>
			<div class="task-box" id="task-box">
				
				<%-- <c:forEach items="${ taskList }" var="task">
				
					<div class="task-div">
						<div class="task-item" id="task-item">
							<input type="hidden" name="code" id="taskCode">
							<div class="task-title" id="taskTitle"></div>
							<div class="task-managements"></div>
							<br clear="both">
							<h5>기간:</h5>
							<div class="task-date-box">
								<div id="taskStartDate">0000-00-00</div>
								<div id="taskEndDate">~0000-00-00</div>
							</div>
						</div>
						<select class="task-status" id="task-status" name="taskProgress" onchange="chageLangSelect()">
							<option id="before" value="Before" selected="selected">진행전</option>
							<option id="proceeding" value="Proceeding">진행중</option>
							<option id="finish" value="Finish">완료</option>
						</select>
					</div>
					
				</c:forEach> --%>
			</div>
		</div>
	</div>
	<!-- -------------------------------------------------------------모달창--------------------------------------------------------------------- -->
	<!-- 백로그 생성 모달창 -->
	<div id="backlog-create-modal">
   
   		<div class="modal_content">
   			<form action="" method="post">
			<div class="modal_head">
				<h3>백로그 생성</h3>
	    	</div>
       		<div class="modal_content-box">
       			<input type="text" class="title" name="backlogTitle" placeholder="BacklogTitle">
       		
       			<select class="manager" id="backlogManager">
					<option>Manager</option>
					<!-- c:foreach -->
					
					
					
       			</select>
				<h5>시작일</h5>
				<h5>종료일</h5>
       			<br clear="both">
       			<input type='date' class="start-day" name='backlogStartday'/>
       			<input type='date' class="end-day" name='backlogEndday'/>
       			<textarea class="description" id="backlogDescription" rows="13" cols="51" placeholder="BackLog Detail Description"></textarea>
       		</div>
       		<div class="modal_button">
	        	<button type="button" id="backlog-create">Ok</button>
	        	<button type="button" id="backlog-close-btn1">Cancel</button>
       		</div>
   			</form>
    	</div>
    	<div class="modal_layer"></div>
	</div>
	
	<!-- 백로그 수정 모달창 -->
	<div id="backlog-update-modal">
   
   		<div class="modal_content">
   			<form action="" method="post">
			<div class="modal_head">
				<h3>백로그 수정</h3>
	    	</div>
       		<div class="modal_content-box">
       			<input type="text" class="title" name="backlogTitle" placeholder="BacklogTitle">
       		
       			<select class="manager" id=" backlogManager">
					<option>Manager</option>
					<!-- c:foreach -->
					
					
					
       			</select>
       		
				<h5>시작일</h5>
				<h5>종료일</h5>
       			<br clear="both">
       			<input type='date' class="start-day" name='backlogStartday'/>
       			<input type='date' class="end-day" name='backlogEndday'/>
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
   
   		<div class="modal_content">
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
    	<div class="modal_layer"></div>
	</div>
	
	<!-- 태스크 수정 모달창 -->
	<div id="task-update-modal">
   
   		<div class="modal_content">
   			<form action="" method="post">
			<div class="modal_head">
				<h3>태스크 수정</h3>
	    	</div>
       		<div class="modal_content-box">
       			<input type="text" class="title" name="taskTitle" placeholder="TaskTitle">
       		
       			<select class="manager" id="taskManager">
					<option>Manager</option>
					<!-- c:foreach -->
					
					
					
       			</select>
				<h5>시작일</h5>
				<h5>종료일</h5>
       			<br clear="both">
       			<input type='date' class="start-day" name='taskStartday'/>
       			<input type='date' class="end-day" name='taskEndday'/>
       			<textarea class="description" id="taskDescription" rows="13" cols="51" placeholder="Task Detail Description"></textarea>
       		</div>
       		<div class="modal_button">
	        	<button type="button" id="task-update">Ok</button>
	        	<button type="button" id="task-delete-open-btn">delete</button>
	        	<button type="button" id="task-close-btn2">Cancel</button>
       		</div>
   			</form>
    	</div>
    	<div class="modal_layer"></div>
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
	
<script>

	/*모달 키고 끄는 버튼*/
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
    
    /* document.getElementById("task-item").onclick = function() {
        document.getElementById("task-update-modal").style.display = "block";
    } */
    
    document.getElementById("task-close-btn2").onclick = function() {
		document.getElementById("task-update-modal").style.display = "none";
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

    
    if(document.querySelectorAll("#sprint-box div")){
    	
    	const $sprintItems = document.querySelectorAll("#sprintItem");
    
   		console.log($sprintItems);
    
		for(let i = 0; i < $sprintItems.length; i++){	
    	
    		$sprintItems[i].onclick = (function(e) {
    		
				if(!$(e.target).hasClass("sprint-update-modal-open") && !$(e.target).hasClass("sprint-delete-modal-open")) {
				
					const $sprintCodes = document.querySelectorAll("#sprintCode");
			
					console.log($sprintCodes[i].value);
					
					$.ajax({
						url: "/byat/sprint/selecttasks",
						type: "get",
						data: { "sprintCode": $sprintCodes[i].value },
						success: function(data, status, xhr){
							console.table(data);
							
								const $taskBox = $("#task-box");
								$taskBox.html("");
								
								for(let i = 0; i < data.length; i++){
								
									const $taskDiv = $("<div class='task-div'>");
									const $taskItem = $("<div class='task-item' id='task-item'>");
									const $memberNo = $("<input type='hidden' name='memberNo' id='memberNo'>").val(data[i].memberNo);
									const $taskCode = $("<input type='hidden' name='code' id='taskCode'>").val(data[i].code);
									const $taskTitle = $("<div class='task-title' id='taskTitle'>").text(data[i].title);
									
									const $taskManager = $("<div class='task-manager'>").text(data[i].manager);
									let $taskManagerBox = null;
									
									if(data[i].managerRole.roleName == 'PM'){
										
										$taskManagerBox = $("<div class='task-manager-box' style='background-color:red;'>");

									} else if(data[i].managerRole.roleName == '부PM'){
										
										$taskManagerBox = $("<div class='task-manager-box' style='background-color:yellowgreen;'>");
										
									} else if(data[i].managerRole.roleName == '일반 멤버'){
										
										$taskManagerBox = $("<div class='task-manager-box' style='background-color:#FFDFBA;'>");
										
									}
									
									const $br = $("<br clear='both'>");
									const $h5 = $("<h5 style='margin-bottom:5px;'>").html("기간:");
									const $taskDateBox = $("<div class='task-date-box'>");
									
									const $taskStartDate = $("<div id='taskStartDate' style='font-size:10px; width:70px;'>").text(data[i].startDate);
									const $taskEndDate = $("<div id='taskEndDate' style='font-size:10px; width:70px;'>").text("~" + data[i].endDate);
									
									const $taskStatus = $("<select class='task-status' id='task-status' name='taskProgress' onchange='changeLangSelect()'>");
									const $option1 = $("<option id='before' value='Before'>").text("진행전");
									const $option2 = $("<option id='proceeding' value='Proceeding'>").text("진행중");
									const $option3 = $("<option id='finish' value='Finish'>").text("완료");
									
									$taskDiv.append($taskItem);
									$taskItem.append($taskCode);
									$taskItem.append($taskTitle);

									$taskManagerBox.append($taskManager);
									$taskItem.append($taskManagerBox);
									
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
    
    /*태스크 상태 변경*/
    window.onload = function(){
    	const status = document.getElementById("task-status").value; 
    	const before = document.getElementById("before").value;
    	const proceeding = document.getElementById("proceeding").value;
    	const finish = document.getElementById("finish").value;
    	
    	if(status == before) {
    		document.getElementById("task-status").style.background="#C4C4C4";
    	} else if(status == proceeding) {
    		document.getElementById("task-status").style.background="#F67B21";
    	} else{
    		document.getElementById("task-status").style.background="#3988FF";
    	}
    	
    }
    
    function chageLangSelect() {
    	const status = document.getElementById("task-status").value; 
    	const before = document.getElementById("before").value;
    	const proceeding = document.getElementById("proceeding").value;
    	const finish = document.getElementById("finish").value;
    	
    	if(status == before) {
    		document.getElementById("task-status").style.background="#C4C4C4";
    	} else if(status == proceeding) {
    		document.getElementById("task-status").style.background="#F67B21";
    	} else{
    		document.getElementById("task-status").style.background="#3988FF";
    	}
    }
   
</script>
</body>
</html>