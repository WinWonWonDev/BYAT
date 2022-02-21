<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/menubar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
	
	.projectListHeadName {
		margin-left:5%;
		margin-top:2%;
		font-size:35px;
		float:left;
		display:inline;
	}
	
	#createProject {
		background-color:rgb(25,25,112);
		color:white;
		font-size:25px;
		float:right;
		margin-right:6%;
		margin-top:3%;
		cursor:pointer;
	}
	
	.projectListBox {
		border:1px solid black;
		height:75%;
		margin-left:3%;
		margin-right:3%;
		margin-top:6%;
	}
	
	#manageProjectTd {
		width:300px;
	}
	
	#projectParticipantsTd {
		width:350px;
	}
	
	#memberPlus {
		width:100px;
	}
	
	#projectDate {
		width:150px;
	}
	
	#projectProgress {
		width:100px;
	}
	
	#projectManager {
		width:100px;
	}
	
	#projectSetting {
		width:100px;
	}
	
	table {
    	width: 100%;
    	border-collapse: collapse;
    }
	
	th {
		background-color:rgb(229,229,229);
		height:50px;
	}
	
	#projectCreateModal {
		display: none;
		position:absolute;
		width:100%;
		height:100%;
		z-index:1;
		bottom:1%;
	}
	
	#projectCreateModal button {
		display:inline-block;
		width:100px;
		margin-left:calc(100% - 100px - 10px);
	}
	
	#projectCreateModal .modal_content {
		width:1000px;
		height:680px;
		margin:20px auto;
		background:#29428C;
		border:1px solid black;
		border-radius:25px;
	}
	
	#projectCreateModal .modal_layer {
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
		padding-top:20px;
	}
	
	#projectCreateModalCloseBtn {
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:110px;
		height:50px;
		position:absolute;
		right:30%;
	}
	
	#projectCreateBtn {
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:110px;
		height:50px;
		position:absolute;
		right:65%;
	}
	
	.modal_content-box {
		width:90%;
		height:75%;
		font-size:40px;
		text-align:center;
		background: white;
		border-radius: 25px;
		margin-left: 53px;
		margin-top:3%;
	}
	
	.modal_button {
		width:100%;
		height:30%;
		float:right;
		position:relative;
		margin-top:2%;
	}
	
	h3 {
		text-align:center;
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
	
	.projectModalTitle {
		width:82%;
		font-size:20px;
	}
	
	.projectDescription {
		margin-top:2%;
	}
	
	.projectTitleTag {
		font-size:20px;
		text-align:left;
		margin-left:9%;
		color:rgba(25,25,112,54);
	}
	
	.start-day {
		margin-top:10px;
		margin-left:80px;
		width:250px;
		font-size:20px;
		float:left;
		
	}
	.end-day {
		margin-top:10px;
		margin-left:230px;
		width:250px;
		font-size:20px;
		float:left;
	}
	
	.projectStartDayTag {
		font-size:20px;
		float:left;
		margin-top:3%;
		margin-left:9%;
		color:rgba(25,25,112,54);
	}
	
	.projectEndDayTag {
		font-size:20px;
		float:left;
		margin-top:3%;
		margin-left:35.5%;
		color:rgba(25,25,112,54);
	}
	
	.projectDescriptionTag {
		font-size:20px;
		float:left;
		margin-top:3%;
		margin-left:9%;
		color:rgba(25,25,112,54);
	}
	
	.prjectCodeMessage {
		font-size:18px;
		float:left;
		color:rgb(54,231,71);
		margin-left:9%;
	}

	/* .projectTable:nth-child(2n+3) {
		margin-top:10px;
	}
	
	.projectTable:nth-child(2n) {
		margin-top:10px;
	} */
	
	td {
		font-size:18px;
		text-align:center;
		height:70px;
	}
	
	.projectAddMemberBtn {
		background:url("/byat/resources/images/projectAddMember.png") no-repeat;
        border:none;
        width:50px;
        height:50px;
        cursor:pointer;
	}
	
	.projectRealProgress {
		background-color:rgb(78,115,223);
		color:white;
		height:40px;
		width:80px;
		text-align:center;
		border-radius:10px;
		display:table-cell;
		vertical-align:middle;
	}
	
	.projectRealManager {
		background-color:red;
		color:white;
		height:40px;
		width:40px;
		text-align:center;
		border-radius:20px;
		display:table-cell;
		vertical-align:middle;
		left:50px;
	}
	
	.projectEditBtn {
		background:url("/byat/resources/images/projectEditBtn.png") no-repeat;
        border:none;
        width:50px;
        height:50px;
        cursor:pointer;
	}
	
	#projectRealMember2 {
		background-color:yellowgreen;
	}
	#projectRealMember3 {
		background-color:rgb(255,223,186);
	}
	#projectRealMember1 {
		background-color:red;
	}
	
	#projectRealMemberList>div {
		color:white;
		height:40px;
		width:40px;
		text-align:center;
		border-radius:20px;
		display:table-cell;
		vertical-align:middle;
		transform: translateY(35%);
	}
	
	#projectRealMemberList {
		float:right;
	}
	
 	#projectMenuBox {
 		position:absolute;
 		width:80px;
 		height:50px;
 	}
 	
 	#projectMenuTitles div {
		background-color:white;
		border:1px solid black;
		text-align:center;
		font-weight:bold;
		cursor:pointer;
	}
	
	#projectUpdateModal {
		display: none;
		position:absolute;
		width:100%;
		height:100%;
		z-index:1;
		bottom:1%;
	}
	
	#projectUpdateModal button {
		display:inline-block;
		width:100px;
		margin-left:calc(100% - 100px - 10px);
	}
	
	#projectUpdateModal .modal_content {
		width:1000px;
		height:680px;
		margin:20px auto;
		background:#29428C;
		border:1px solid black;
		border-radius:25px;
	}
	
	#projectUpdateModal .modal_layer {
		position:fixed;
		top:0;
		left:0;
		width:100%;
		height:100%;
		background:rgba(0, 0, 0, 0.5);
		z-index:-1;
	}
	
	#projectUpdateModalCloseBtn {
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:110px;
		height:50px;
		position:absolute;
		right:30%;
	}
	
	#projectUpdateBtn {
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:110px;
		height:50px;
		position:absolute;
		right:65%;
	}
	
	#delete_modal {
		display: none;
		position:relative;
		width:100%;
		height:100%;
		z-index:1;
	}
	
	#delete_modal h2 {
		margin:0;
	}
	#delete_modal button {
		display:inline-block;
		width:100px;
		margin-left:calc(100% - 100px - 10px);
	}
	
	#delete_modal .delete_modal_content {
		width:700px;
		height:300px;
		margin:100px auto;
		/* padding:20px 10px; */
		background:#fff;
		border:2px solid #666;
	}
	
	.delete_modal_head {
		width:100.1%;
		height:35px;
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		font-size:20px;
		float:right;
	}
	
	#delete_modal_close_btn {
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
	
	#delete_modal_ok_btn {
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:80px;
		height:50px;
		position:absolute;
		right:55%;
		top:20%;
	}
	
	.delete_modal_content_message {
		width:100%;
		height:50%;
		float:right;
		font-size:30px;
		text-align:center;
	}
	
	.delete_modal_button {
		width:100%;
		height:30%;
		float:right;
		position:relative;
	}
	
	#regist_project_members_modal {
		display: none;
		position:relative;
		width:100%;
		height:100%;
		z-index:1;
	}
	
	#regist_project_members_modal .regist_project_members_modal_content {
		width:550px;
		height:450px;
		margin:20px auto;
		background:#3B60D0;
		border:1px solid black;
		border-radius:40px;
		margin-top:7%;
	}
	
	.regist_project_members_modal_head {
		height:35px;
		color:white;
		text-align:center;
		font-size:30px;
		padding-top:20px;
	}
	
	.regist_project_members_modal_content-box {
		width:90%;
		height:75%;
		font-size:40px;
		text-align:center;
		background: white;
		border-radius: 25px;
		margin-left: 32px;
		margin-top:3%;
	}
	
	.regist_project_members_modal_content button {
		
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:110px;
		height:30px;
		position:absolute;
		margin-top:5px;
	}
	
	.selectedMemberArea {
		
		width:88%;
		height:20%;
		border:2px solid black;
		margin-top:3%;
		margin-left:6%;
		position:relative;
		border-radius:15px;
		background-color:white;
	}
	
	.selectedMember {
		
		width:35%;
		font-size:15px;
		float:left;
		margin-top:5px;
		margin-left:40px;
		position:relative;
	}
	
	.selectedMemberProjectRole {
		
		position:absolute;
		margin-top:5px;
		right:70px;		
	}
	
	.addMembersBox {
		background-color:rgb(242,242,242); 
		height:140px; 
		border-radius:10px; 
		width:94%; 
		border:1px solid black; 
		margin-left:16px; 
		margin-top:15px; 
		overflow-y:scroll;
	}
	
	.addMembersBox::-webkit-scrollbar {
	
		background-color: rgb(242,242,242);		
		border-radius:15px;
	}
	
	.addMembersBox::-webkit-scrollbar-thumb {
		background-color: gray;
		border-radius:15px;
	}
	
</style>
<script>
   const message = '${ requestScope.message }';
   if(message != null && message != '') {
      alert(message);
   }   
</script>
<title>Insert title here</title>
</head>
<body style="overflow:hidden;">
	<div id="whiteBoard">
		<div class="projectListHeadName" style="font-weight:bold">전체 프로젝트 목록</div>
		<c:if test="${ (sessionScope.loginMember.permit eq 'PM') or (sessionScope.loginMember.permit eq 'ADMIN')}">
			<button id="createProject">새 프로젝트 생성</button>					
		</c:if>
			<button id="createProject" style="display:none;"></button>
		<div class="projectListBox">
	      		<table class="projectTable" id="projectTable">
					<tr>
						<th id="manageProjectTd">프로젝트 관리</th>
						<th id="projectParticipantsTd">참여자</th>
						<th id="memberPlus">팀원 추가</th>
						<th id="projectDate">시작날짜</th>
						<th id="projectDate">종료날짜</th>
						<th id="projectProgress">진행 상태</th>
						<th id="projectManager">담당자</th>
						<th id="projectSetting">설정</th>
					</tr>
					<c:forEach items="${ projectList }" var="project" varStatus="status">
					<tr>
						<td id="projectTitle" style="cursor:pointer;"><c:out value="${ project.title }" />
							<input type="hidden" value="${ project.code }" name="projectCode" id="projectCode">
						</td>
						<td id="projectRealMemberList">
							<c:forEach items="${ project.projectMembers }" var="member">
								<c:if test="${ member.roleName eq 'PM' }">
									<div id="projectRealMember1">
										<c:out value="${ member.name }" />															
									</div>									
								</c:if>
								<c:if test="${ member.roleName eq '부PM' }">
									<div id="projectRealMember2">
										<c:out value="${ member.name }" />															
									</div>
								</c:if>
								<c:if test="${ member.roleName eq '일반 멤버' }">
									<div id="projectRealMember3">
										<c:out value="${ member.name }" />															
									</div>
								</c:if>
							</c:forEach>
						</td>
						<td>
							
							<input type="button" class="projectAddMemberBtn">
						</td>
						<td><c:out value="${ project.startDate }" /></td>
						<td><c:out value="${ project.endDate }" /></td>
						<td style="padding-left:15px;">
							<c:if test="${ project.progress eq '진행중' }">
								<div class="projectRealProgress">
									<c:out value="${ project.progress }" />
								</div>								
							</c:if>
							<c:if test="${ project.progress eq '완료' }">
								<div class="projectRealProgress" style="background-color:rgb(41, 60, 117)">
									<c:out value="${ project.progress }" />
								</div>		
							</c:if>
							<c:if test="${ project.progress eq '미진행' }">
								<div class="projectRealProgress" style="background-color:rgb(196, 196, 196)">
									<c:out value="${ project.progress }" />
								</div>		
							</c:if>
						</td>
						<td style="padding-left:30px;">
							<div class="projectRealManager">
								<c:out value="${ project.writer }" />							
							</div>
						</td>
						<td>
							<input type="button" class="projectEditBtn" id="projectEditBtn">
						</td>
					</tr>
					<div id="projectMenuBox" class="projectMenuBox" style="display:none"> 
						<div id="projectMenuTitles">
							<div id="updateAndSelectProject">조회/수정</div>
							<c:if test="${ (sessionScope.loginMember.no eq PmMemberNumber[status.index]) or (sessionScope.loginMember.permit eq 'ADMIN')}">
								<div id="deleteProject">삭제하기</div>			
							</c:if>
							<div id="deleteProject" style="display:none;"></div>
						</div>
					</div>
				</c:forEach>
	      		</table>
		</div>
	</div>
	<div id="projectCreateModal">
  
  		<div class="modal_content">
	  		<form action="${ pageContext.servletContext.contextPath }/project/regist" method="post">
				<div class="modal_head">
					<h3>프로젝트 생성</h3>
		    	</div>
	      		<div class="modal_content-box">
	      			<div style="height:30px;"></div>
	      			<div class="projectTitleTag">프로젝트 명</div>
	      			<input type="text" class="projectModalTitle" name="title" placeholder="ProjectTitle" required>
	      			<div class="projectStartDayTag">프로젝트 시작일자</div>
	      			<div class="projectEndDayTag">프로젝트 종료일자</div>
	       			<br clear="both">
	       			<input type='date' class="start-day" name='startDate' required/>
	       			<input type='date' class="end-day" name='endDate' required/>
	       			<div class="projectDescriptionTag">프로젝트 상세 설명</div>
	      			<textarea class="projectDescription" id="projectDescription" name="body" rows="13" cols="100" placeholder="상세내용을 입력해주세요" required></textarea>
	      			<div class="prjectCodeMessage">프로젝트  코드는 자동으로 생성됩니다.</div>
	      		</div>
	      		<div class="modal_button">
		        	<button type="submit" id="projectCreateBtn">Ok</button>
		        	<button type="button" id="projectCreateModalCloseBtn">Cancel</button>
	      		</div>
			</form>
   		</div>
   		<div class="modal_layer"></div>
	</div>
	
	<div id="projectUpdateModal">
  
  		<div class="modal_content">
	  		<form action="${ pageContext.servletContext.contextPath }/project/modify" method="post">
				<div class="modal_head">
					<h3>프로젝트 상세</h3>
		    	</div>
	      		<div class="modal_content-box">
		  			<input type="hidden" name="code" id="projectUpdateCode">
	      			<div style="height:30px;"></div>
	      			<div class="projectTitleTag">프로젝트 명</div>
   					<input type="text" class="projectModalTitle" id="projectUpdateModalTitle" name="title" required>
	      			<div class="projectStartDayTag">프로젝트 시작일자</div>
	      			<div class="projectEndDayTag">프로젝트 종료일자</div>
	       			<br clear="both">
	       			<input type='date' class="start-day" name='startDate' id="updateStartDate" required/>
	       			<input type='date' class="end-day" name='endDate' id="updateEndDate" required/>
	       			<div class="projectDescriptionTag">프로젝트 상세 설명</div>
	      			<textarea class="projectDescription" id="projectUpdateDescription" name="body" rows="13" cols="100" required></textarea>
	      			<div class="prjectCodeMessage">프로젝트  코드는 자동으로 생성됩니다.</div>
	      		</div>
	      		<div class="modal_button">
		        	<button type="submit" id="projectUpdateBtn">Ok</button>
		        	<button type="button" id="projectUpdateModalCloseBtn">Cancel</button>
	      		</div>
			</form>
   		</div>
   		<div class="modal_layer"></div>
	</div>
	
	<div id="delete_modal">
   
	    <div class="delete_modal_content">
		    <div class="delete_modal_head">
		    	Alert Message
		    </div>
	       	<div class="delete_modal_content_message">
	  	   		<br>삭제한 프로젝트는 <font style="color:red;">복구</font>하실 수 없습니다. <br>정말 삭제하시겠습니까?
	       	</div>
	       	<div class="delete_modal_button">
		        <button type="button" id="delete_modal_ok_btn">Ok</button>
		        <button type="button" id="delete_modal_close_btn">Cancel</button>
	       	</div>
	       
	    </div>
	   
	    <div class="modal_layer"></div>
	</div>
	
	<div id="regist_project_members_modal">
		
		<div class="regist_project_members_modal_content">
			<div class="regist_project_members_modal_head">
				프로젝트 팀원 추가
			</div>
			<div class="regist_project_members_modal_content-box">
				<div class="searchMembersTitle" style="font-size:23px; text-align:left; margin-left:20px; padding-top:15px;">
					팀원 검색
				</div>
				<div class="searchMembersBox">
					<input type="text" id="searchMembers" maxlength="20" style="background-color:rgb(242,242,242); height:30px; border-radius:10px;" size="65">
				</div>
				<div class="addMembers" style="font-size:23px; text-align:left; margin-left:20px; padding-top:30px;">
					팀원 추가
				</div>
				<div class="addMembersBox" id="addMembersBox">
					<form action="${ pageContext.servletContext.contextPath }/project/registmember" id="addMembersForm" class="addMembersForm"></form>
				</div>
			</div>
				<button type="button" id="registMembersOkBtn" style="margin-left:100px;">OK</button>
				<button type="button" id="registMembersCancelBtn" style="margin-left:350px;">Cancel</button>
		</div>
		
		
	</div>
	
	<script>
		
		document.getElementById("createProject").onclick = function() {
	        document.getElementById("projectCreateModal").style.display="block";
	    }

		document.getElementById("projectCreateModalCloseBtn").onclick = function() {
	        document.getElementById("projectCreateModal").style.display="none";
	    }

	    document.getElementById("registMembersCancelBtn").onclick = function() {
	    	document.getElementById("regist_project_members_modal").style.display = "none";
	    	
	    	const $selectedMemberArea = document.querySelectorAll("#selectedMemberArea");
	    	
	    	for(let i = 0; i < $selectedMemberArea.length; i++) {
	    		
	    		$selectedMemberArea[i].remove();
	    		
	    	}
	    	
	    	document.getElementById("searchMembers").value = "";
	    }
	    
		document.getElementById("projectUpdateModalCloseBtn").onclick = function() {
	        document.getElementById("projectUpdateModal").style.display="none";
			
			document.getElementById("projectUpdateModalTitle").value = "";
			document.getElementById("updateStartDate").value = "";
			document.getElementById("updateEndDate").value = "";
			document.getElementById("projectUpdateDescription").value = "";
			document.getElementById("projectUpdateCode").value = "";
			
	    }
		
		let projectCode;
		
		let selectedMemberList = [];
		let projectMemberList = [];
		
		if(document.querySelectorAll("#projectTable td")) {
			const $tds = document.querySelectorAll("#projectTitle");
			const $code = document.querySelectorAll("#projectCode");
			const $projectEditBtn = document.querySelectorAll("#projectEditBtn");
			const $projectAddMemberBtn = document.querySelectorAll(".projectAddMemberBtn");
			const $proBox = document.querySelectorAll("#projectMenuBox");
			const $updateAndSelectProject = document.querySelectorAll("#updateAndSelectProject");
			const $deleteProject = document.querySelectorAll("#deleteProject");
			console.log($updateAndSelectProject);
			
			for(let i = 0; i < $tds.length; i++) {
				
				$tds[i].onclick = function() {
					
					location.href = "${ pageContext.servletContext.contextPath }/sprint/list?code=" + $code[i].value;
				}
				
			}
			
			for(let i = 0; i < $proBox.length; i++) {
				
				$proBox[i].style.left = '1300px';
			}
			
			for(let i = 0; i < $projectAddMemberBtn.length; i++) {
		          
				$projectAddMemberBtn[i].onclick = function() {

					document.getElementById("regist_project_members_modal").style.display="block";
			             
					projectCode = $code[i].value;
					
					console.log(projectCode);
					
					<c:forEach items="${projectList}" var="project" varStatus="status">
						<c:forEach items="${project.projectMembers}" var="member">
							if(i == ${status.index}) {
								selectedMemberList.push(${member.no});					
							}
						</c:forEach>
						projectMembersList = selectedMemberList;
					</c:forEach>
					
				}

				$projectEditBtn[i].onclick = function() {
   
					if(i === 0) {
					   
					   $proBox[i].style.top = '190px';
					   
					} else if(i === 1) {
					   
					   $proBox[i].style.top = '260px';
					   
					} else if(i === 2) {
					   
					   $proBox[i].style.top = '330px';
					   
					} else if(i === 3) {
					   
					   $proBox[i].style.top = '400px';
					
					} else {
					   
					   $proBox[i].style.top = '470px';
					
					} 
					
					if($proBox[i].style.display =='none') {
					   $proBox[i].style.display = 'block';
					} else {
					   $proBox[i].style.display = 'none';
					}
   
				}
				
				$deleteProject[i].onclick = function() {
					
					document.getElementById("delete_modal").style.display="block";
                    $proBox[i].style.display = 'none';
                    
                    document.getElementById("delete_modal_close_btn").onclick = function() {
                         document.getElementById("delete_modal").style.display="none";
                     }
                    
                    document.getElementById("delete_modal_ok_btn").onclick = function() {
                       
                       location.href = "${ pageContext.servletContext.contextPath }/project/remove?code=" + $code[i].value;                        
                    }
					
				}				
			
				$updateAndSelectProject[i].onclick = function() {
					
					$proBox[i].style.display = 'none';
					
					$.ajax({
						url: "/byat/project/detail",
						type: 'get',
						data: { code : $code[i].value },
						success: function(data, status, xhr) {
                     
							const project = JSON.parse(data.projectDetail);
							const $projectUpdateModalTitle = $("#projectUpdateModalTitle");
							const $updateStartDate = $("#updateStartDate");
							const $updateEndDate = $("#updateEndDate");
							const $projectUpdateDescription = $("#projectUpdateDescription");
							const $projectUpdateCode = $("#projectUpdateCode");
	                     
							projectUpdateModalTitle.value = project.title;
							projectUpdateDescription.value = project.body;
							updateStartDate.value = project.startDate;
							updateEndDate.value = project.endDate;
							projectUpdateCode.value = project.code;
					   
							document.getElementById("projectUpdateModal").style.display="block";
							
							if(${sessionScope.loginMember.no} !=  project.projectMembers[0].no) {
							   
								projectUpdateModalTitle.readOnly = true;
								projectUpdateDescription.readOnly = true;
								updateStartDate.readOnly = true;
								updateEndDate.readOnly = true;
							
							} else {
								
								projectUpdateModalTitle.readOnly = false;
								projectUpdateDescription.readOnly = false;
								updateStartDate.readOnly = false;
								updateEndDate.readOnly = false;
								
							}
				   
						},
						error: function(xhr, status, error) {
						   console.log(xhr);
						}
					});
				}
				
			}
		}
		
		let addMembersFormTag;
		let selectedMemberAreaDiv;
		let selectedMemberDiv;
		let selectedMemberProjectRoleDiv;
		let selectMemberValue;
		let roleOption;
		let hiddenMemberNo;
		let selectMemberNo;
		
		let searchMembersValue;
        // 모든 텍스트의 변경에 반응합니다.
        $("#searchMembers").on("propertychange change keyup paste input", function() {
           
           // 현재 변경된 데이터 셋팅
           searchMembersValue = $(this).val();
           
        });
        
        let selectMembers = [];
        
		$(function() {
			$('#searchMembers').autocomplete({
				source : function(request, response) {
					$.ajax({
						type : 'get',
		                url : '/byat/project/searchMembers',
		                data : { 
		                	searchValue : searchMembersValue,
		                	code : projectCode,
		                	selectMembers : selectMembers,
		                	projectMembersList : projectMembersList
		                },
		                traditional : true,
		                dataType : 'json',
		                success : function(data) {
		                	
		                    // 서버에서 json 데이터 response 후 목록 추가
		                    response(
		                        $.map(data, function(item) {
		                            return {
		                                value : item
		                            }
		                        })
		                    );
		                }
					});
				},
				select : function(event, ui) {
						
					selectMemberValue = ui.item.value.name + " " + ui.item.value.id;
					
					selectMemberNo = ui.item.value.no;
					
					selectMembers.push(selectMemberNo);
					
					console.log(selectMembers);
					
		        },
		        focus : function(event, ui) {
		            return false;
		        },
		        minLength : 1,
		        autoFocus : true,
		        classes : {
		            'ui-autocomplete': 'highlight'
		        },
		        delay : 300,
		        position : { my : 'right top', at : 'right bottom' },
		        close : function(event) {
		            console.log(event);
		            
		            if(selectMemberValue != null) {
		            	
		            	document.getElementById('searchMembers').value = "";
			            
			            addMembersFormTag = document.getElementById('addMembersForm');
			            selectedMemberAreaDiv = document.createElement('div');
			            selectedMemberDiv = document.createElement('div');
			            selectedMemberProjectRoleDiv = document.createElement('select');
			            roleOption = document.createElement('option');
			            hiddenMemberNo = document.createElement('input');
			            roleOption.innerText = '부PM';
			            
			            hiddenMemberNo.setAttribute('type', 'hidden');
			            hiddenMemberNo.setAttribute('name', 'no');
			            hiddenMemberNo.value = selectMemberNo;
			            
			            selectedMemberAreaDiv.setAttribute('class', 'selectedMemberArea');
			            selectedMemberAreaDiv.setAttribute('id', 'selectedMemberArea');
			            
			            selectedMemberDiv.setAttribute('class', 'selectedMember');
			            selectedMemberDiv.setAttribute('id', 'selectedMember');
			            selectedMemberDiv.innerHTML = selectMemberValue;
			            
			            selectedMemberProjectRoleDiv.setAttribute('class','selectedMemberProjectRole');
			            selectedMemberProjectRoleDiv.setAttribute('id','selectedMemberProjectRole');
			            selectedMemberProjectRoleDiv.setAttribute('name','role');
			            selectedMemberProjectRoleDiv.appendChild(roleOption);
			            
			            roleOption = document.createElement('option');
			            roleOption.innerText = '일반 멤버';
			            roleOption.setAttribute('selected', 'selected');
			            
			            selectedMemberProjectRoleDiv.appendChild(roleOption);
			            
			            addMembersFormTag.appendChild(selectedMemberAreaDiv);
			            selectedMemberAreaDiv.appendChild(selectedMemberDiv);
			            selectedMemberAreaDiv.appendChild(selectedMemberProjectRoleDiv);
			            selectedMemberAreaDiv.appendChild(hiddenMemberNo);
		            	
		            }
		            
		        }
			}).autocomplete('instance')._renderItem = function(ul, item) { // UI 변경 부
		        return $('<li>') //기본 tag가 li
		        .append('<div>' + item.value.name + " " + item.value.id + '</div>') // 원하는 모양의 HTML 만들면 됨
		        .appendTo(ul);
		    };
		});
		
	</script>

</body>
</html>