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

	.projectTable:nth-child(2n+3) {
		margin-top:10px;
	}
	
	.projectTable:nth-child(2n) {
		margin-top:10px;
	}
	
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
		<button id="createProject">새 프로젝트 생성</button>			
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
					<c:forEach items="${ projectList }" var="project">
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
								<c:if test="${ member.roleName eq '일반멤버' }">
									<div id="projectRealMember3">
										<c:out value="${ member.name }" />															
									</div>
								</c:if>
							</c:forEach>
						</td>
						<td><input type="button" class="projectAddMemberBtn"></td>
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
						<td><input type="button" class="projectEditBtn"></td>
					</tr>
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
	  		<form action="" method="post">
				<div class="modal_head">
					<h3>프로젝트 상세</h3>
		    	</div>
	      		<div class="modal_content-box">
	      			<div style="height:30px;"></div>
	      			<div class="projectTitleTag">프로젝트 명</div>
	      			<input type="text" class="projectModalTitle" name="title" placeholder="Project-001">
	      			<div class="projectStartDayTag">프로젝트 시작일자</div>
	      			<div class="projectEndDayTag">프로젝트 종료일자</div>
	       			<br clear="both">
	       			<input type='date' class="start-day" name='startDate'/>
	       			<input type='date' class="end-day" name='endDate'/>
	       			<div class="projectDescriptionTag">프로젝트 상세 설명</div>
	      			<textarea class="projectDescription" id="projectDescription" name="body" rows="13" cols="100" placeholder="상세내용을 입력해주세요"></textarea>
	      			<div class="prjectCodeMessage">프로젝트  코드는 자동으로 생성됩니다.</div>
	      		</div>
	      		<div class="modal_button">
		        	<button type="button" id="projectUpdateBtn">Ok</button>
		        	<button type="button" id="projectUpdateModalCloseBtn">Cancel</button>
	      		</div>
			</form>
   		</div>
   		<div class="modal_layer"></div>
	</div>
	<div id="projectMenuBox" class="projectMenuBox" style="display:none"> 
		<div id="projectMenuTitles">
			<div id="updateAndSelectProject">조회/수정</div>
			<div id="deleteProject">삭제하기</div>
		</div>
	</div>
	
	<script>
		document.getElementById("createProject").onclick = function() {
	        document.getElementById("projectCreateModal").style.display="block";
	    }

		document.getElementById("projectCreateModalCloseBtn").onclick = function() {
	        document.getElementById("projectCreateModal").style.display="none";
	    }

		document.getElementById("updateAndSelectProject").onclick = function() {
	        document.getElementById("projectUpdateModal").style.display="block";
	    }

		document.getElementById("projectUpdateModalCloseBtn").onclick = function() {
	        document.getElementById("projectUpdateModal").style.display="none";
	    }
		
		if(document.querySelectorAll("#projectTable td")) {
			const $tds = document.querySelectorAll("#projectTitle");
			const $code = document.querySelectorAll("#projectCode");
			const $projectEditBtn = document.querySelectorAll(".projectEditBtn");
			let proBox = document.getElementById("projectMenuBox");
			
			for(let i = 0; i < $tds.length; i++) {
				
				$tds[i].onclick = function() {
					
					location.href = "${ pageContext.servletContext.contextPath }/sprint/list?code=" + $code[i].value;
				}
			}
		
			for(let i = 0; i < $projectEditBtn.length; i++) {
			
				proBox.style.left = '1345px';
				
				$projectEditBtn[i].onclick = function() {
					
					if(i === 0) {
						
						proBox.style.top = '300px';
						document.getElementById("deleteProject").onclick = function() {
							
							location.href = "${ pageContext.servletContext.contextPath }/project/remove?code=" + $code[i].value;
						}
					} else if(i === 1) {
						
						proBox.style.top = '370px';
						document.getElementById("deleteProject").onclick = function() {
							
							location.href = "${ pageContext.servletContext.contextPath }/project/remove?code=" + $code[i].value;
						}
					} else if(i === 2) {
						
						proBox.style.top = '440px';
						document.getElementById("deleteProject").onclick = function() {
							
							location.href = "${ pageContext.servletContext.contextPath }/project/remove?code=" + $code[i].value;
						}
					} else if(i === 3) {
						
						proBox.style.top = '510px';
						document.getElementById("deleteProject").onclick = function() {
							
							location.href = "${ pageContext.servletContext.contextPath }/project/remove?code=" + $code[i].value;
						}
					} else {
						
						proBox.style.top = '580px';
						document.getElementById("deleteProject").onclick = function() {
							
							location.href = "${ pageContext.servletContext.contextPath }/project/remove?code=" + $code[i].value;
						}
					}
					
					if(proBox.style.display =='none') {
						   proBox.style.display = 'block';
					} else {
					 proBox.style.display = 'none';
					}
					
				}

			}
			
			
		}
		
	</script>

</body>
</html>