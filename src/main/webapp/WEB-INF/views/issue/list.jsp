<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/menubar.jsp" %>
<%@ include file="../common/subMenu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style>
	html {
		width: 100%;
      	height: 98%;
      	background:#D7E3FA;
      	background-size: cover;
      	position:relative;
      	overflow-y:hidden;
      	overflow-x:hidden;
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
      	height:82%;
	}
	
	.issueListHead {
		height:15%;
		border-bottom:1px solid  black;
	}

	.issueListName {
		margin-left:3%;
		margin-top:1%;
		font-size:35px;
		float:left;
		display:inline;
	}
	
	.issueBeforeSolution {
		border-right:1px solid black;
		float:left;
		width:33.3%;
		height:85%;
		text-align:center;
		position:relative;
	}
	
	.issueResolving {
		border-right:1px solid black;
		float:left;
		width:33.3%;
		height:85%;
		text-align:center;
		position:relative;
	}
	
	.issueSolved {
		float:left;
		width:33%;
		height:85%;
		text-align:center;
		position:relative;
	}
	
	.issueHead {
		display:inline-block;
		border:none;
		width:150px;
		height:50px;
		line-height: 50px;
		text-align:center;
		background-color:rgb(196,196,196);
		font-weight:bold;
		position:relative;
		top:20px;
	}
	
	.issueKanban {
		display:inline-block;
		position:relative;
		border:2px solid black;
		width:285px;
		height:130px;
		cursor:pointer;
		z-index:10;
	}

	.kanbanArea {
		position:absolute;
		margin-top:30px;
		height:80%;
		width:100%;
		overflow-y:scroll;
		overflow-x: hidden;
	}
	
	.kanbanArea::-webkit-scrollbar {
	
		background-color: white;		
		border-radius:15px;
	}
	
	.kanbanArea::-webkit-scrollbar-thumb {
	
		background-color: skyblue;
		border : none;
		border-radius : 15px;
		color : white;
	}
	
	.issueStatusBox {
		position:relative;
		background-color:red;
		border:2px solid black;
		width:20px;
		height:20px;
		margin-top:2%;
		margin-left:2%;
		float:left;
	}
	
	.issueWriter {
		position:absolute;
		font-weight:bold;
		float:left;
		bottom:35px;
		left:7px;
	}
	
	.issueWriteDate {
		position:absolute;
		font-weight:bold;
		float:left;
		bottom:10px;
		left:7px;
	}
	
	.issueEditBtn {
		background: url("/byat/resources/images/issueMoreBtn.png") no-repeat;
		position:absolute;
		cursor:pointer;
		width:28px;
		height:28px;
		border:none;
		margin-left:97px;
		margin-top:4px;
	}
	
	.issueTitle {
		position:absolute;
		width:195px;
		word-break:break-all;
		left:40px;
		text-align:left;
		top:7px;
		font-weight:bold;
	}
	
	.issueAllArea {
		width:100%;
		height:85%;
		position:absolute;
	}
	
	.selectArea {
		position:absolute;
		left:1300px;
		top: 30px;
	}
	
	.issueSelectBox {
		position:relative;
		right:100px;
		width:200px;
		padding: .8em .5em;                                        /* 여백으로 높이 설정 */
		border: 1px solid #999;
		font-family: inherit;                                          /* 폰트 상속 */
		background: url('/byat/resources/images/underArrow.png') no-repeat 95% 50%;    /* 네이티브 화살표 대체 이미지와 위치 */ 
		border-radius: 0px;                                          /* iOS 둥근모서리 제거 */
		-webkit-appearance: none;                                 /* 네이티브 외형 감추기 */
		-moz-appearance: none;
		appearance: none;
	}
	
	.issueSelectBox::-ms-expand {
		display:none;		/* 익스프롤러에서도 화살표가 안보이도록 */
	}
	
	.issueEditBox {
		position:absolute;
		width:80px;
		height:45px;
		border:2px solid black;
		z-index:1050;
		background-color:white;
		margin-left:196px;
		margin-top:28px;
	}
	
	.issueEditBox div {
		text-align:center;
		font-weight:bold;
	}
	
	.issueSelectAndEditBtn {
		position:relative;
		width:100%;
		height:50%;
		border-bottom: 2px solid black;
	}
	
	.issueDeleteBtn {
		position:relative;
		width:100%;
		height:50%;
	}
	
	.issueDetailAndModifyModal {
		position:absolute;
		width:600px;
		height:750px;
		z-index:1100;
		left:31%;
	}
	
	.issueDetailAndModifyModal .modal_content {
		width:80%;
		height:70%;
		margin:20px auto;
		background:#3B60D0;
		border:1px solid black;
		border-radius:25px;
	}
	
	.issueDetailAndModifyModal .modal_layer {
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
		font-size:25px;
		padding-top:30px;
		padding-left:40px;
	}
	
	.modal_content-box {
		position:relative;
		width:430px;
		height:390px;
		font-size:40px;
		text-align:center;
		background: white;
		border-radius: 25px;
		margin-left: 28px;
		margin-top:3%;
	}
	
	.issueModifyBtn {
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:110px;
		height:40px;
		position:absolute;
		right:60%;
	}
	
	.issueModifyModalCloseBtn {
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:110px;
		height:40px;
		position:absolute;
		right:18%;
	}
	
	.modal_button {
		width:100%;
		height:50px;
		float:right;
		position:relative;
		margin-top:2%;
	}
	
	.issueModifyModalTitle {
		width:82%;
		font-size:20px;
		padding-top:7px;
		padding-bottom:7px;
		margin-top:30px;
		border:2px solid black;
	}
	
	.issueMemberModifyArea {
		width:100%;
		height:32%;
		margin-top:15px;
	}
	
	.issueMemberModifySelectArea {
		width:100%;
		height:18%;
	}
	
	.modifyMemberSelectBox {
		position:relative;
		width:185px;
		border:2px solid black;
		margin-left:170px;
		bottom:25px;
	}
	
	.issueModifyMemberList {
		background-color:white;
		position:relative;
		width:82%;
		height:100px;
		border:2px solid black;
		margin-left:37px;
		overflow-y:scroll;
		bottom:20px;
	}
	
	.issueModifyMemberList::-webkit-scrollbar {
	
		border-radius:15px;
	}
	
	.issueModifyMemberList::-webkit-scrollbar-thumb {
		background-color: skyblue;
		border-radius:15px;
	}
	
	.issueModifyDescription {
		position:relative;
		border:2px solid black;
		resize: none;
		bottom:10px;
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
	
	#delete_modal {
		display: none;
		position:relative;
		width:100%;
		height:100%;
		z-index:2000;
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
	
</style>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<title>Insert title here</title>
<script>
   const message = '${ requestScope.message }';
   if(message != null && message != '') {
      alert(message);
   }   
</script>
</head>
<body style="overflow-x: hidden; overflow-y:hidden;">
	<div id="whiteBoard">
		<div class="issueListHead">
			<div class="issueListName" id="issueListName">
				<c:forEach items="${ sprintList }" var="sprintList" varStatus="firstStatus">
					<c:if test="${ firstStatus.index eq 0 }">
						<c:choose>
							<c:when test="${ sprintList.progress eq '진행전'}">
								<font style="color: rgba(48, 58, 154, 100)" id="titleFont">${ sprintList.title }</font><font id="titleProgress">의 Issue (${ sprintList.progress })</font>
							</c:when>
							<c:when test="${ sprintList.progress eq '진행중'}">
								<font style="color: rgba(48, 58, 154, 100)" id="titleFont">${ sprintList.title }</font><font id="titleProgress">의 Issue (${ sprintList.progress })</font>
							</c:when>
							<c:when test="${ sprintList.progress eq '완료'}">
								<font style="color: rgba(48, 58, 154, 100)" id="titleFont">${ sprintList.title }</font><font id="titleProgress">의 Issue (${ sprintList.progress })</font>
							</c:when>
						</c:choose>
						<input type="hidden" id="projectCode" value="${ sprintList.projectCode }">					
					</c:if>
				</c:forEach>
				<input type="hidden" name="sprintProgress" id="headSprintProgress">
			</div>
			<div class="selectArea">
				<select class="issueSelectBox" id="issueSelectBox" onchange="changeTitleSelection()">
					<c:forEach items="${ sprintList }" var="sprintList">
						<c:choose>
							<c:when test="${ sprintList.progress eq '진행전' }">
								<option selected="selected">${ sprintList.title }</option>
							</c:when>
							<c:when test="${ sprintList.progress eq '진행중' }">
								<option selected="selected">${ sprintList.title }</option>
							</c:when>
							<c:otherwise>
								<option>${ sprintList.title }</option>
							</c:otherwise>						
						</c:choose>
						
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="issueBeforeSolution" id="issueBeforeSolution">
			<div class="issueHead">해결전</div>
			<div class="kanbanArea" id="kanbanArea"></div>
		</div>
		<div class="issueResolving">
			<div class="issueHead">해결중</div>
			<div class="kanbanArea" id="kanbanArea"></div>
		</div>
		<div class="issueSolved">
			<div class="issueHead">완료</div>
			<div class="kanbanArea" id="kanbanArea"></div>
		</div>
	</div>
	
	<div class="issueDetailAndModifyModal" id="issueDetailAndModifyModal" style="display:none">
		<div class="modal_content">
				<div class="modal_head">
					이슈 조회 및 수정
				</div>
				<div class="modal_content-box">
					<form action="${ pageContext.servletContext.contextPath }/issue/modify" id="issueDetailAndModifyForm" method="post">
						<input type="text" class="issueModifyModalTitle" id="issueModifyModalTitle" name="issueModifyTitle" placeholder="IssueTitle" required>
						<div class="issueMemberModifyArea">
							<div class="issueMemberModifySelectArea">
								<div id="selectBoxMessage" class="selectBoxMessage">* [사번 이름 고유번호]</div>
								<select class="modifyMemberSelectBox" id="modifyMemberSelectBox">
									<option style="text-align:center">----------</option>
								</select>
							</div>
							<div class="issueModifyMemberList" id="issueModifyMemberList"></div>
							<textarea class="issueModifyDescription" id="issueModifyDescription" name="issueModifyBody" rows="10" cols="47" placeholder="상세내용을 입력해주세요" required></textarea>
						</div>
					</form>
				</div>
				<div class="modal_button"> 
		        	<button type="button" id="issueModifyBtn" class="issueModifyBtn">Ok</button>
		        	<button type="button" id="issueModifyModalCloseBtn" class="issueModifyModalCloseBtn">Cancel</button>
				</div>
		</div>
		<div class="modal_layer"></div>
	</div>
	<div id="delete_modal">
		<div class="delete_modal_content">
		    <div class="delete_modal_head">
		    	Alert Message
		    </div>
	       	<div class="delete_modal_content_message">
	  	   		<br>삭제한 이슈는 <font style="color:red;">복구</font>하실 수 없습니다. <br>정말 삭제하시겠습니까?
	       	</div>
	       	<div class="delete_modal_button">
		        <button type="button" id="delete_modal_ok_btn">Ok</button>
		        <button type="button" id="delete_modal_close_btn">Cancel</button>
	       	</div>
	    </div>
		<div class="modal_layer"></div>
	</div>
	
	<script>
	
		document.getElementById("selectIssueList").href = document.getElementById("selectIssueList").href + "${pjCode}";
		document.getElementById("selectSprintList").href = document.getElementById("selectSprintList").href + "${pjCode}";
		document.getElementById("selectRetrospectList").href = document.getElementById("selectRetrospectList").href + "${pjCode}";
		document.getElementById("selectMeetingLogList").href = document.getElementById("selectMeetingLogList").href + "${pjCode}";
		
		let checkFirst = 0;
		let newProgress = "";
		let memberCount = 0;
		
		const kanbanArea = document.querySelectorAll('#kanbanArea');

		const selectedTitle = document.getElementById("issueSelectBox");
		
		let selectedOption = "";
		
		if(selectedTitle.selectedIndex >= 0) {
			selectedOption = selectedTitle.options[selectedTitle.selectedIndex].text;
		}

		<c:forEach items="${sprintList}" var="sprintList" varStatus="status">
			
			console.log("${sprintList.title}");
		
			if("${sprintList.title}" == selectedOption) {
				
				document.getElementById("headSprintProgress").value = "${sprintList.progress}";
				
				$.ajax({
					url : "/byat/issue/issuelist",
					type : "get",
					data : {
						sprintCode : "${sprintList.code}"
					},
					success : function(data, status, xhr) {
						
						const sprintIssueList = JSON.parse(data.issueList);
						
						for(let i in sprintIssueList) {
							
							if(sprintIssueList[i].progress == "해결전") {
								
								memberCount = 0;
								
								kanbanBox = document.createElement('div');
								kanbanBox.setAttribute('id', 'issueKanban');
								kanbanBox.setAttribute('class', 'issueKanban');
								kanbanBox.setAttribute('draggable', 'true');
								
								issueStatusBox = document.createElement('div');
								issueStatusBox.setAttribute('id', 'issueStatusBox');
								issueStatusBox.setAttribute('class', 'issueStatusBox');
								issueStatusBox.setAttribute('style', 'background-color:red');
								
								issueCode = document.createElement('input');
								issueCode.setAttribute('type', 'hidden');
								issueCode.setAttribute('value', sprintIssueList[i].code);
								issueCode.setAttribute('name', 'issueCode');
								
								issueTitle = document.createElement('div');
								issueTitle.setAttribute('id', 'issueTitle');
								issueTitle.setAttribute('class', 'issueTitle');
								issueTitle.setAttribute('name', 'title');
								issueTitle.innerText = sprintIssueList[i].title;
								
								issueEditBtn = document.createElement('button');
								issueEditBtn.setAttribute('id', 'issueEditBtn');
								issueEditBtn.setAttribute('class', 'issueEditBtn');
								
								issueEditBox = document.createElement('div');
								issueEditBox.setAttribute('id', 'issueEditBox');
								issueEditBox.setAttribute('class', 'issueEditBox');
								issueEditBox.setAttribute('style', 'display:none');
								
								issueSelectAndEditBtn = document.createElement('div');
								issueSelectAndEditBtn.setAttribute('id', 'issueSelectAndEditBtn');
								issueSelectAndEditBtn.setAttribute('class', 'issueSelectAndEditBtn');
								issueSelectAndEditBtn.innerText = '조회/수정';
								
								issueDeleteBtn = document.createElement('div');
								issueDeleteBtn.setAttribute('id', 'issueDeleteBtn');
								issueDeleteBtn.setAttribute('class', 'issueDeleteBtn');
								issueDeleteBtn.innerText = '삭제';
								
								issueEditBox.appendChild(issueSelectAndEditBtn);
								issueEditBox.appendChild(issueDeleteBtn);
								
								issueWriter = document.createElement('div');
								issueWriter.setAttribute('id', 'issueWriter');
								issueWriter.setAttribute('class', 'issueWriter');
								issueWriter.setAttribute('name', 'writerName');
								issueWriter.innerText = "제기자 : " + sprintIssueList[i].name;
								
								issueWriteDate = document.createElement('div');
								issueWriteDate.setAttribute('id', 'issueWriteDate');
								issueWriteDate.setAttribute('class', 'issueWriteDate');
								issueWriteDate.setAttribute('name', 'writeTime');
								issueWriteDate.innerText = "제기일자 : " + sprintIssueList[i].writingTime;
								
								issueBody = document.createElement('input');
								issueBody.setAttribute('type', 'hidden');
								issueBody.setAttribute('id', 'issueBody');
								issueBody.setAttribute('name', 'body');
								issueBody.setAttribute('value', sprintIssueList[i].body);
								
								sprintCode = document.createElement('input');
								sprintCode.setAttribute('type', 'hidden');
								sprintCode.setAttribute('id', 'sprintCode');
								sprintCode.setAttribute('class', 'sprintCode');
								sprintCode.setAttribute('name', 'sprintCode');
								sprintCode.setAttribute('value', sprintIssueList[i].sprintCode);
								
								kanbanBox.appendChild(issueStatusBox);
								kanbanBox.appendChild(issueCode);
								kanbanBox.appendChild(issueTitle);
								kanbanBox.appendChild(issueEditBtn);
								kanbanBox.appendChild(issueEditBox);
								kanbanBox.appendChild(issueWriter);
								kanbanBox.appendChild(issueWriteDate);
								kanbanBox.appendChild(issueBody);
								kanbanBox.appendChild(sprintCode);
								
								for(let t in sprintIssueList[i].issueMemberList) {
									
									issueMembersList = document.createElement('input');
									issueMembersList.setAttribute('type', 'hidden');
									issueMembersList.setAttribute('id', 'issueMembersList');
									issueMembersList.setAttribute('class', 'issueMembersList');
									issueMembersList.setAttribute('name', 'issueModifyMemberInfo');
									issueMembersList.setAttribute('value', sprintIssueList[i].issueMemberList[t].id + ' ' + sprintIssueList[i].issueMemberList[t].name + ' ' + sprintIssueList[i].issueMemberList[t].no);
									memberCount++;
									kanbanBox.appendChild(issueMembersList);
								}
								
								issueMembersCount = document.createElement('input');
								issueMembersCount.setAttribute('type', 'hidden');
								issueMembersCount.setAttribute('id', 'issueMembersCount');
								issueMembersCount.setAttribute('class', 'issueMembersCount');
								issueMembersCount.setAttribute('value', memberCount);
								
								kanbanBox.appendChild(issueMembersCount);
								
								kanbanArea[0].appendChild(kanbanBox);
								
							} else if(sprintIssueList[i].progress == "해결중") {
								
								memberCount = 0;
								
								kanbanBox = document.createElement('div');
								kanbanBox.setAttribute('id', 'issueKanban');
								kanbanBox.setAttribute('class', 'issueKanban');
								kanbanBox.setAttribute('draggable', 'true');
								
								issueStatusBox = document.createElement('div');
								issueStatusBox.setAttribute('id', 'issueStatusBox');
								issueStatusBox.setAttribute('class', 'issueStatusBox');
								issueStatusBox.setAttribute('style', 'background-color:#FBC254');
		
								issueCode = document.createElement('input');
								issueCode.setAttribute('type', 'hidden');
								issueCode.setAttribute('value', sprintIssueList[i].code);
								issueCode.setAttribute('name', 'issueCode');
								
								issueTitle = document.createElement('div');
								issueTitle.setAttribute('id', 'issueTitle');
								issueTitle.setAttribute('class', 'issueTitle');
								issueTitle.setAttribute('name', 'title');
								issueTitle.innerText = sprintIssueList[i].title;
								
								issueEditBtn = document.createElement('button');
								issueEditBtn.setAttribute('id', 'issueEditBtn');
								issueEditBtn.setAttribute('class', 'issueEditBtn');
								
								issueEditBox = document.createElement('div');
								issueEditBox.setAttribute('id', 'issueEditBox');
								issueEditBox.setAttribute('class', 'issueEditBox');
								issueEditBox.setAttribute('style', 'display:none');
								
								issueSelectAndEditBtn = document.createElement('div');
								issueSelectAndEditBtn.setAttribute('id', 'issueSelectAndEditBtn');
								issueSelectAndEditBtn.setAttribute('class', 'issueSelectAndEditBtn');
								issueSelectAndEditBtn.innerText = '조회/수정';
								
								issueDeleteBtn = document.createElement('div');
								issueDeleteBtn.setAttribute('id', 'issueDeleteBtn');
								issueDeleteBtn.setAttribute('class', 'issueDeleteBtn');
								issueDeleteBtn.innerText = '삭제';
								
								issueEditBox.appendChild(issueSelectAndEditBtn);
								issueEditBox.appendChild(issueDeleteBtn);
								
								issueWriter = document.createElement('div');
								issueWriter.setAttribute('id', 'issueWriter');
								issueWriter.setAttribute('class', 'issueWriter');
								issueWriter.setAttribute('name', 'writerName');
								issueWriter.innerText = "제기자 : " + sprintIssueList[i].name;
								
								issueWriteDate = document.createElement('div');
								issueWriteDate.setAttribute('id', 'issueWriteDate');
								issueWriteDate.setAttribute('class', 'issueWriteDate');
								issueWriteDate.setAttribute('name', 'writeTime');
								issueWriteDate.innerText = "제기일자 : " + sprintIssueList[i].writingTime;
								
								issueBody = document.createElement('input');
								issueBody.setAttribute('type', 'hidden');
								issueBody.setAttribute('id', 'issueBody');
								issueBody.setAttribute('name', 'body');
								issueBody.setAttribute('value', sprintIssueList[i].body);
								
								sprintCode = document.createElement('input');
								sprintCode.setAttribute('type', 'hidden');
								sprintCode.setAttribute('id', 'sprintCode');
								sprintCode.setAttribute('class', 'sprintCode');
								sprintCode.setAttribute('name', 'sprintCode');
								sprintCode.setAttribute('value', sprintIssueList[i].sprintCode);
								
								kanbanBox.appendChild(issueStatusBox);
								kanbanBox.appendChild(issueCode);
								kanbanBox.appendChild(issueTitle);
								kanbanBox.appendChild(issueEditBtn);
								kanbanBox.appendChild(issueEditBox);
								kanbanBox.appendChild(issueWriter);
								kanbanBox.appendChild(issueWriteDate);
								kanbanBox.appendChild(issueBody);
								kanbanBox.appendChild(sprintCode);
								
								for(let t in sprintIssueList[i].issueMemberList) {
									
									issueMembersList = document.createElement('input');
									issueMembersList.setAttribute('type', 'hidden');
									issueMembersList.setAttribute('id', 'issueMembersList');
									issueMembersList.setAttribute('class', 'issueMembersList');
									issueMembersList.setAttribute('name', 'issueModifyMemberInfo');
									issueMembersList.setAttribute('value', sprintIssueList[i].issueMemberList[t].id + ' ' + sprintIssueList[i].issueMemberList[t].name + ' ' + sprintIssueList[i].issueMemberList[t].no);
									memberCount++;
									kanbanBox.appendChild(issueMembersList);
								}
								
								issueMembersCount = document.createElement('input');
								issueMembersCount.setAttribute('type', 'hidden');
								issueMembersCount.setAttribute('id', 'issueMembersCount');
								issueMembersCount.setAttribute('class', 'issueMembersCount');
								issueMembersCount.setAttribute('value', memberCount);
								
								kanbanBox.appendChild(issueMembersCount);
								
								kanbanArea[1].appendChild(kanbanBox);
								
							} else if(sprintIssueList[i].progress == "완료") {
								
								memberCount = 0;
								
								kanbanBox = document.createElement('div');
								kanbanBox.setAttribute('id', 'issueKanban');
								kanbanBox.setAttribute('class', 'issueKanban');
								kanbanBox.setAttribute('draggable', 'true');
								
								issueStatusBox = document.createElement('div');
								issueStatusBox.setAttribute('id', 'issueStatusBox');
								issueStatusBox.setAttribute('class', 'issueStatusBox');
								issueStatusBox.setAttribute('style', 'background-color:#2EE957');
								
								issueCode = document.createElement('input');
								issueCode.setAttribute('type', 'hidden');
								issueCode.setAttribute('value', sprintIssueList[i].code);
								issueCode.setAttribute('name', 'issueCode');
								
								issueTitle = document.createElement('div');
								issueTitle.setAttribute('id', 'issueTitle');
								issueTitle.setAttribute('class', 'issueTitle');
								issueTitle.setAttribute('name', 'title');
								issueTitle.innerText = sprintIssueList[i].title;
								
								issueEditBtn = document.createElement('button');
								issueEditBtn.setAttribute('id', 'issueEditBtn');
								issueEditBtn.setAttribute('class', 'issueEditBtn');
								
								issueEditBox = document.createElement('div');
								issueEditBox.setAttribute('id', 'issueEditBox');
								issueEditBox.setAttribute('class', 'issueEditBox');
								issueEditBox.setAttribute('style', 'display:none');
								
								issueSelectAndEditBtn = document.createElement('div');
								issueSelectAndEditBtn.setAttribute('id', 'issueSelectAndEditBtn');
								issueSelectAndEditBtn.setAttribute('class', 'issueSelectAndEditBtn');
								issueSelectAndEditBtn.innerText = '조회/수정';
								
								issueDeleteBtn = document.createElement('div');
								issueDeleteBtn.setAttribute('id', 'issueDeleteBtn');
								issueDeleteBtn.setAttribute('class', 'issueDeleteBtn');
								issueDeleteBtn.innerText = '삭제';
								
								issueEditBox.appendChild(issueSelectAndEditBtn);
								issueEditBox.appendChild(issueDeleteBtn);
								
								issueWriter = document.createElement('div');
								issueWriter.setAttribute('id', 'issueWriter');
								issueWriter.setAttribute('class', 'issueWriter');
								issueWriter.setAttribute('name', 'writerName');
								issueWriter.innerText = "제기자 : " + sprintIssueList[i].name;
								
								issueWriteDate = document.createElement('div');
								issueWriteDate.setAttribute('id', 'issueWriteDate');
								issueWriteDate.setAttribute('class', 'issueWriteDate');
								issueWriteDate.setAttribute('name', 'writeTime');
								issueWriteDate.innerText = "제기일자 : " + sprintIssueList[i].writingTime;
								
								issueBody = document.createElement('input');
								issueBody.setAttribute('type', 'hidden');
								issueBody.setAttribute('id', 'issueBody');
								issueBody.setAttribute('name', 'body');
								issueBody.setAttribute('value', sprintIssueList[i].body);
								
								sprintCode = document.createElement('input');
								sprintCode.setAttribute('type', 'hidden');
								sprintCode.setAttribute('id', 'sprintCode');
								sprintCode.setAttribute('class', 'sprintCode');
								sprintCode.setAttribute('name', 'sprintCode');
								sprintCode.setAttribute('value', sprintIssueList[i].sprintCode);
								
								kanbanBox.appendChild(issueStatusBox);
								kanbanBox.appendChild(issueCode);
								kanbanBox.appendChild(issueTitle);
								kanbanBox.appendChild(issueEditBtn);
								kanbanBox.appendChild(issueEditBox);
								kanbanBox.appendChild(issueWriter);
								kanbanBox.appendChild(issueWriteDate);
								kanbanBox.appendChild(issueBody);
								kanbanBox.appendChild(sprintCode);
								
								for(let t in sprintIssueList[i].issueMemberList) {
									
									issueMembersList = document.createElement('input');
									issueMembersList.setAttribute('type', 'hidden');
									issueMembersList.setAttribute('id', 'issueMembersList');
									issueMembersList.setAttribute('class', 'issueMembersList');
									issueMembersList.setAttribute('name', 'issueModifyMemberInfo');
									issueMembersList.setAttribute('value', sprintIssueList[i].issueMemberList[t].id + ' ' + sprintIssueList[i].issueMemberList[t].name + ' ' + sprintIssueList[i].issueMemberList[t].no);
									memberCount++;
									kanbanBox.appendChild(issueMembersList);
								}
								
								issueMembersCount = document.createElement('input');
								issueMembersCount.setAttribute('type', 'hidden');
								issueMembersCount.setAttribute('id', 'issueMembersCount');
								issueMembersCount.setAttribute('class', 'issueMembersCount');
								issueMembersCount.setAttribute('value', memberCount);
								
								kanbanBox.appendChild(issueMembersCount);
								
								kanbanArea[2].appendChild(kanbanBox);
								
							}
							
						}
						
						issueKanban = document.querySelectorAll('#issueKanban');
						
						if("${sprintList.progress}" != "완료") {
							
							for(let k = 0; k < issueKanban.length; k++) {
								const item = issueKanban[k];
								
								item.addEventListener('dragstart', function() {
									draggedItem = item;
									setTimeout(function() {
										item.style.display = 'none';
									}, 0);
								});
								
								item.addEventListener('dragend', function() {
									setTimeout(function() {
										draggedItem.style.display = 'inline-block';
										draggedItem = null;
									}, 0);
								});
								
							}
							
							for(let j = 0; j < kanbanArea.length; j++) {
								const list = kanbanArea[j];
								
								list.addEventListener('dragover', function(e) {
									e.preventDefault();
								});
								
								list.addEventListener('dragenter', function(e) {
									e.preventDefault();
								});
								
								list.addEventListener('dragleave', function(e) {
									
								});
								
								list.addEventListener('drop', function(e) {
									
									if(j == 0 && checkFirst == 0) {  //해결전
										draggedItem.children[0].style.backgroundColor = 'red';
									
										issueAjaxCode = draggedItem.children[1].value;
										
										console.log("확인용");
										
										$.ajax({
											url : "/byat/issue/modifyissuestatus",
											type : "get",
											data : {
												issueCode : issueAjaxCode,
												progress : "해결전"
											},
											success : function(data, status, xhr) {
												console.log(xhr);
											},
											error : function(xhr, status, error) {
												console.log(xhr);
											}
										});
										
										
										
									} else if(j == 1 && checkFirst == 0) { //해결중

										draggedItem.children[0].style.backgroundColor = '#FBC254';
									
										issueAjaxCode = draggedItem.children[1].value;
										
										 $.ajax({
											url : "/byat/issue/modifyissuestatus",
											type : "get",
											data : {
												issueCode : issueAjaxCode,
												progress : "해결중"
											},
											success : function(data, status, xhr) {
												console.log(xhr);
											},
											error : function(xhr, status, error) {
												console.log(xhr);
											}
										});
										
										
									} else if(j == 2 && checkFirst == 0) { //완료
									
										draggedItem.children[0].style.backgroundColor = '#2EE957';
										
										issueAjaxCode = draggedItem.children[1].value;

										$.ajax({
											url : "/byat/issue/modifyissuestatus",
											type : "get",
											data : {
												issueCode : issueAjaxCode,
												progress : "완료"
											},
											success : function(data, status, xhr) {
												console.log(xhr);
											},
											error : function(xhr, status, error) {
												console.log(xhr);
											}
										});
									}
									
									this.append(draggedItem);
								});
							}
							
						}
						
					},
					error : function(xhr, status, error) {
						console.log(xhr);
					}
				});
				
			}
		</c:forEach>
		
		let issueKanban = document.querySelectorAll('#issueKanban');

		let draggedItem = null;
		
		let issueAjaxCode = 0;
		
		function changeTitleSelection() {
			
			issueKanban = null;
			draggedItem = null;
			issueAjaxCode = 0;
			checkFirst = 1;
			
			console.log(checkFirst);
			
			for(let i = 0; i < kanbanArea.length; i++) {
				
				while(kanbanArea[i].hasChildNodes()) {
					kanbanArea[i].removeChild(kanbanArea[i].firstChild);
				}
				
			}
			
			const titleFont = document.getElementById("titleFont");
			
			const titleProgress = document.getElementById("titleProgress");
			
			const selectedTitle = document.getElementById("issueSelectBox");
			
			const selectedOption = selectedTitle.options[selectedTitle.selectedIndex].text;
			
			<c:forEach items="${sprintList}" var="sprintList" varStatus="status">
				if("${sprintList.title}" == selectedOption) {
					
					document.getElementById("headSprintProgress").value = "${sprintList.progress}";
					
					if("${sprintList.progress}" == "진행전") {
						
						titleProgress.innerText = "(진행전)";
					} else if("${sprintList.progress}" == "진행중") {
						
						titleProgress.innerText = "(진행중)";
					} else {
						
						titleProgress.innerText = "(완료)";
					}
					
					
					titleFont.innerText = selectedOption;
					
					document.getElementById("titleProgress").innerText = "의 Issue (${sprintList.progress})"
					
					$.ajax({
						url : "/byat/issue/issuelist",
						type : "get",
						data : {
							sprintCode : "${sprintList.code}",
						},
						success : function(data, status, xhr) {
							
							const sprintIssueList = JSON.parse(data.issueList);
							
							for(let i in sprintIssueList) {
								
								if(sprintIssueList[i].progress == "해결전") {
									
									memberCount = 0;
									
									kanbanBox = document.createElement('div');
									kanbanBox.setAttribute('id', 'issueKanban');
									kanbanBox.setAttribute('class', 'issueKanban');
									kanbanBox.setAttribute('draggable', 'true');
									
									issueStatusBox = document.createElement('div');
									issueStatusBox.setAttribute('id', 'issueStatusBox');
									issueStatusBox.setAttribute('class', 'issueStatusBox');
									issueStatusBox.setAttribute('style', 'background-color:red');
									
									issueCode = document.createElement('input');
									issueCode.setAttribute('type', 'hidden');
									issueCode.setAttribute('value', sprintIssueList[i].code);
									issueCode.setAttribute('name', 'issueCode');
									
									issueTitle = document.createElement('div');
									issueTitle.setAttribute('id', 'issueTitle');
									issueTitle.setAttribute('class', 'issueTitle');
									issueTitle.setAttribute('name', 'title');
									issueTitle.innerText = sprintIssueList[i].title;
									
									issueEditBtn = document.createElement('button');
									issueEditBtn.setAttribute('id', 'issueEditBtn');
									issueEditBtn.setAttribute('class', 'issueEditBtn');
									
									issueEditBox = document.createElement('div');
									issueEditBox.setAttribute('id', 'issueEditBox');
									issueEditBox.setAttribute('class', 'issueEditBox');
									issueEditBox.setAttribute('style', 'display:none');
									
									issueSelectAndEditBtn = document.createElement('div');
									issueSelectAndEditBtn.setAttribute('id', 'issueSelectAndEditBtn');
									issueSelectAndEditBtn.setAttribute('class', 'issueSelectAndEditBtn');
									issueSelectAndEditBtn.innerText = '조회/수정';
									
									issueDeleteBtn = document.createElement('div');
									issueDeleteBtn.setAttribute('id', 'issueDeleteBtn');
									issueDeleteBtn.setAttribute('class', 'issueDeleteBtn');
									issueDeleteBtn.innerText = '삭제';
									
									issueEditBox.appendChild(issueSelectAndEditBtn);
									issueEditBox.appendChild(issueDeleteBtn);
									
									issueWriter = document.createElement('div');
									issueWriter.setAttribute('id', 'issueWriter');
									issueWriter.setAttribute('class', 'issueWriter');
									issueWriter.setAttribute('name', 'writerName');
									issueWriter.innerText = "제기자 : " + sprintIssueList[i].name;
									
									issueWriteDate = document.createElement('div');
									issueWriteDate.setAttribute('id', 'issueWriteDate');
									issueWriteDate.setAttribute('class', 'issueWriteDate');
									issueWriteDate.setAttribute('name', 'writeTime');
									issueWriteDate.innerText = "제기일자 : " + sprintIssueList[i].writingTime;
									
									issueBody = document.createElement('input');
									issueBody.setAttribute('type', 'hidden');
									issueBody.setAttribute('id', 'issueBody');
									issueBody.setAttribute('name', 'body');
									issueBody.setAttribute('value', sprintIssueList[i].body);
									
									sprintCode = document.createElement('input');
									sprintCode.setAttribute('type', 'hidden');
									sprintCode.setAttribute('id', 'sprintCode');
									sprintCode.setAttribute('class', 'sprintCode');
									sprintCode.setAttribute('name', 'sprintCode');
									sprintCode.setAttribute('value', sprintIssueList[i].sprintCode);
									
									kanbanBox.appendChild(issueStatusBox);
									kanbanBox.appendChild(issueCode);
									kanbanBox.appendChild(issueTitle);
									kanbanBox.appendChild(issueEditBtn);
									kanbanBox.appendChild(issueEditBox);
									kanbanBox.appendChild(issueWriter);
									kanbanBox.appendChild(issueWriteDate);
									kanbanBox.appendChild(issueBody);
									kanbanBox.appendChild(sprintCode);
									
									for(let t in sprintIssueList[i].issueMemberList) {
										
										issueMembersList = document.createElement('input');
										issueMembersList.setAttribute('type', 'hidden');
										issueMembersList.setAttribute('id', 'issueMembersList');
										issueMembersList.setAttribute('class', 'issueMembersList');
										issueMembersList.setAttribute('name', 'issueModifyMemberInfo');
										issueMembersList.setAttribute('value', sprintIssueList[i].issueMemberList[t].id + ' ' + sprintIssueList[i].issueMemberList[t].name + ' ' + sprintIssueList[i].issueMemberList[t].no);
										memberCount++;
										kanbanBox.appendChild(issueMembersList);
									}
									
									issueMembersCount = document.createElement('input');
									issueMembersCount.setAttribute('type', 'hidden');
									issueMembersCount.setAttribute('id', 'issueMembersCount');
									issueMembersCount.setAttribute('class', 'issueMembersCount');
									issueMembersCount.setAttribute('value', memberCount);
									
									kanbanBox.appendChild(issueMembersCount);
									
									kanbanArea[0].appendChild(kanbanBox);
									
								} else if(sprintIssueList[i].progress == "해결중") {
									
									memberCount = 0;
									
									kanbanBox = document.createElement('div');
									kanbanBox.setAttribute('id', 'issueKanban');
									kanbanBox.setAttribute('class', 'issueKanban');
									kanbanBox.setAttribute('draggable', 'true');
									
									issueStatusBox = document.createElement('div');
									issueStatusBox.setAttribute('id', 'issueStatusBox');
									issueStatusBox.setAttribute('class', 'issueStatusBox');
									issueStatusBox.setAttribute('style', 'background-color:#FBC254');
			
									issueCode = document.createElement('input');
									issueCode.setAttribute('type', 'hidden');
									issueCode.setAttribute('value', sprintIssueList[i].code);
									issueCode.setAttribute('name', 'issueCode');
									
									issueTitle = document.createElement('div');
									issueTitle.setAttribute('id', 'issueTitle');
									issueTitle.setAttribute('class', 'issueTitle');
									issueTitle.setAttribute('name', 'title');
									issueTitle.innerText = sprintIssueList[i].title;
									
									issueEditBtn = document.createElement('button');
									issueEditBtn.setAttribute('id', 'issueEditBtn');
									issueEditBtn.setAttribute('class', 'issueEditBtn');
									
									issueEditBox = document.createElement('div');
									issueEditBox.setAttribute('id', 'issueEditBox');
									issueEditBox.setAttribute('class', 'issueEditBox');
									issueEditBox.setAttribute('style', 'display:none');
									
									issueSelectAndEditBtn = document.createElement('div');
									issueSelectAndEditBtn.setAttribute('id', 'issueSelectAndEditBtn');
									issueSelectAndEditBtn.setAttribute('class', 'issueSelectAndEditBtn');
									issueSelectAndEditBtn.innerText = '조회/수정';
									
									issueDeleteBtn = document.createElement('div');
									issueDeleteBtn.setAttribute('id', 'issueDeleteBtn');
									issueDeleteBtn.setAttribute('class', 'issueDeleteBtn');
									issueDeleteBtn.innerText = '삭제';
									
									issueEditBox.appendChild(issueSelectAndEditBtn);
									issueEditBox.appendChild(issueDeleteBtn);
									
									issueWriter = document.createElement('div');
									issueWriter.setAttribute('id', 'issueWriter');
									issueWriter.setAttribute('class', 'issueWriter');
									issueWriter.setAttribute('name', 'writerName');
									issueWriter.innerText = "제기자 : " + sprintIssueList[i].name;
									
									issueWriteDate = document.createElement('div');
									issueWriteDate.setAttribute('id', 'issueWriteDate');
									issueWriteDate.setAttribute('class', 'issueWriteDate');
									issueWriteDate.setAttribute('name', 'writeTime');
									issueWriteDate.innerText = "제기일자 : " + sprintIssueList[i].writingTime;
									
									issueBody = document.createElement('input');
									issueBody.setAttribute('type', 'hidden');
									issueBody.setAttribute('id', 'issueBody');
									issueBody.setAttribute('name', 'body');
									issueBody.setAttribute('value', sprintIssueList[i].body);
									
									sprintCode = document.createElement('input');
									sprintCode.setAttribute('type', 'hidden');
									sprintCode.setAttribute('id', 'sprintCode');
									sprintCode.setAttribute('class', 'sprintCode');
									sprintCode.setAttribute('name', 'sprintCode');
									sprintCode.setAttribute('value', sprintIssueList[i].sprintCode);
									
									kanbanBox.appendChild(issueStatusBox);
									kanbanBox.appendChild(issueCode);
									kanbanBox.appendChild(issueTitle);
									kanbanBox.appendChild(issueEditBtn);
									kanbanBox.appendChild(issueEditBox);
									kanbanBox.appendChild(issueWriter);
									kanbanBox.appendChild(issueWriteDate);
									kanbanBox.appendChild(issueBody);
									kanbanBox.appendChild(sprintCode);
									
									for(let t in sprintIssueList[i].issueMemberList) {
										
										issueMembersList = document.createElement('input');
										issueMembersList.setAttribute('type', 'hidden');
										issueMembersList.setAttribute('id', 'issueMembersList');
										issueMembersList.setAttribute('class', 'issueMembersList');
										issueMembersList.setAttribute('name', 'issueModifyMemberInfo');
										issueMembersList.setAttribute('value', sprintIssueList[i].issueMemberList[t].id + ' ' + sprintIssueList[i].issueMemberList[t].name + ' ' + sprintIssueList[i].issueMemberList[t].no);
										memberCount++;
										kanbanBox.appendChild(issueMembersList);
									}
									
									issueMembersCount = document.createElement('input');
									issueMembersCount.setAttribute('type', 'hidden');
									issueMembersCount.setAttribute('id', 'issueMembersCount');
									issueMembersCount.setAttribute('class', 'issueMembersCount');
									issueMembersCount.setAttribute('value', memberCount);
									
									kanbanBox.appendChild(issueMembersCount);
									
									kanbanArea[1].appendChild(kanbanBox);
									
								} else if(sprintIssueList[i].progress == "완료") {
									
									memberCount = 0;
									
									kanbanBox = document.createElement('div');
									kanbanBox.setAttribute('id', 'issueKanban');
									kanbanBox.setAttribute('class', 'issueKanban');
									kanbanBox.setAttribute('draggable', 'true');
									
									issueStatusBox = document.createElement('div');
									issueStatusBox.setAttribute('id', 'issueStatusBox');
									issueStatusBox.setAttribute('class', 'issueStatusBox');
									issueStatusBox.setAttribute('style', 'background-color:#2EE957');
									
									issueCode = document.createElement('input');
									issueCode.setAttribute('type', 'hidden');
									issueCode.setAttribute('value', sprintIssueList[i].code);
									issueCode.setAttribute('name', 'issueCode');
									
									issueTitle = document.createElement('div');
									issueTitle.setAttribute('id', 'issueTitle');
									issueTitle.setAttribute('class', 'issueTitle');
									issueTitle.setAttribute('name', 'title');
									issueTitle.innerText = sprintIssueList[i].title;
									
									issueEditBtn = document.createElement('button');
									issueEditBtn.setAttribute('id', 'issueEditBtn');
									issueEditBtn.setAttribute('class', 'issueEditBtn');
									
									issueEditBox = document.createElement('div');
									issueEditBox.setAttribute('id', 'issueEditBox');
									issueEditBox.setAttribute('class', 'issueEditBox');
									issueEditBox.setAttribute('style', 'display:none');
									
									issueSelectAndEditBtn = document.createElement('div');
									issueSelectAndEditBtn.setAttribute('id', 'issueSelectAndEditBtn');
									issueSelectAndEditBtn.setAttribute('class', 'issueSelectAndEditBtn');
									issueSelectAndEditBtn.innerText = '조회/수정';
									
									issueDeleteBtn = document.createElement('div');
									issueDeleteBtn.setAttribute('id', 'issueDeleteBtn');
									issueDeleteBtn.setAttribute('class', 'issueDeleteBtn');
									issueDeleteBtn.innerText = '삭제';
									
									issueEditBox.appendChild(issueSelectAndEditBtn);
									issueEditBox.appendChild(issueDeleteBtn);
									
									issueWriter = document.createElement('div');
									issueWriter.setAttribute('id', 'issueWriter');
									issueWriter.setAttribute('class', 'issueWriter');
									issueWriter.setAttribute('name', 'writerName');
									issueWriter.innerText = "제기자 : " + sprintIssueList[i].name;
									
									issueWriteDate = document.createElement('div');
									issueWriteDate.setAttribute('id', 'issueWriteDate');
									issueWriteDate.setAttribute('class', 'issueWriteDate');
									issueWriteDate.setAttribute('name', 'writeTime');
									issueWriteDate.innerText = "제기일자 : " + sprintIssueList[i].writingTime;
									
									issueBody = document.createElement('input');
									issueBody.setAttribute('type', 'hidden');
									issueBody.setAttribute('id', 'issueBody');
									issueBody.setAttribute('name', 'body');
									issueBody.setAttribute('value', sprintIssueList[i].body);
									
									sprintCode = document.createElement('input');
									sprintCode.setAttribute('type', 'hidden');
									sprintCode.setAttribute('id', 'sprintCode');
									sprintCode.setAttribute('class', 'sprintCode');
									sprintCode.setAttribute('name', 'sprintCode');
									sprintCode.setAttribute('value', sprintIssueList[i].sprintCode);
									
									kanbanBox.appendChild(issueStatusBox);
									kanbanBox.appendChild(issueCode);
									kanbanBox.appendChild(issueTitle);
									kanbanBox.appendChild(issueEditBtn);
									kanbanBox.appendChild(issueEditBox);
									kanbanBox.appendChild(issueWriter);
									kanbanBox.appendChild(issueWriteDate);
									kanbanBox.appendChild(issueBody);
									kanbanBox.appendChild(sprintCode);
									
									for(let t in sprintIssueList[i].issueMemberList) {
										
										issueMembersList = document.createElement('input');
										issueMembersList.setAttribute('type', 'hidden');
										issueMembersList.setAttribute('id', 'issueMembersList');
										issueMembersList.setAttribute('class', 'issueMembersList');
										issueMembersList.setAttribute('name', 'issueModifyMemberInfo');
										issueMembersList.setAttribute('value', sprintIssueList[i].issueMemberList[t].id + ' ' + sprintIssueList[i].issueMemberList[t].name + ' ' + sprintIssueList[i].issueMemberList[t].no);
										memberCount++;
										kanbanBox.appendChild(issueMembersList);
									}
									
									issueMembersCount = document.createElement('input');
									issueMembersCount.setAttribute('type', 'hidden');
									issueMembersCount.setAttribute('id', 'issueMembersCount');
									issueMembersCount.setAttribute('class', 'issueMembersCount');
									issueMembersCount.setAttribute('value', memberCount);
									
									kanbanBox.appendChild(issueMembersCount);
									
									kanbanArea[2].appendChild(kanbanBox);
									
								}
								
							}
							
							issueKanban = document.querySelectorAll('#issueKanban');
							
							if("${sprintList.progress}" != "완료") {
								
								for(let k = 0; k < issueKanban.length; k++) {
									const item = issueKanban[k];
									
									item.addEventListener('dragstart', function() {
										draggedItem = item;
										setTimeout(function() {
											item.style.display = 'none';
										}, 0);
									});
									
									item.addEventListener('dragend', function() {
										setTimeout(function() {
											draggedItem.style.display = 'inline-block';
											draggedItem = null;
										}, 0);
									});
									
								}
								
								for(let j = 0; j < kanbanArea.length; j++) {
									const list = kanbanArea[j];
									
									list.addEventListener('dragover', function(e) {
										e.preventDefault();
									});
									
									list.addEventListener('dragenter', function(e) {
										e.preventDefault();
									});
									
									list.addEventListener('dragleave', function(e) {
										
									});
									
									list.addEventListener('drop', function(e) {
										
										if(j == 0) {  //해결전
											draggedItem.children[0].style.backgroundColor = 'red';
										
											issueAjaxCode = draggedItem.children[1].value;
											
											$.ajax({
												url : "/byat/issue/modifyissuestatus",
												type : "get",
												data : {
													issueCode : issueAjaxCode,
													progress : "해결전"
												},
												success : function(data, status, xhr) {
													console.log(xhr);
												},
												error : function(xhr, status, error) {
													console.log(xhr);
												}
											});
											
											
											
										} else if(j == 1) { //해결중

											draggedItem.children[0].style.backgroundColor = '#FBC254';
										
											issueAjaxCode = draggedItem.children[1].value;
											
											 $.ajax({
												url : "/byat/issue/modifyissuestatus",
												type : "get",
												data : {
													issueCode : issueAjaxCode,
													progress : "해결중"
												},
												success : function(data, status, xhr) {
													console.log(xhr);
												},
												error : function(xhr, status, error) {
													console.log(xhr);
												}
											});
											
											
										} else { //완료
										
											draggedItem.children[0].style.backgroundColor = '#2EE957';
											
											issueAjaxCode = draggedItem.children[1].value;

											$.ajax({
												url : "/byat/issue/modifyissuestatus",
												type : "get",
												data : {
													issueCode : issueAjaxCode,
													progress : "완료"
												},
												success : function(data, status, xhr) {
													console.log(xhr);
												},
												error : function(xhr, status, error) {
													console.log(xhr);
												}
											});
										}
										
										this.append(draggedItem);
									});
								}
								
							}
							
						},
						error : function(xhr, status, error) {
							console.log(xhr);
						}
					});
					
				}
				
			</c:forEach>
		}
		
		let removeResult = 0;
		
		$(document).ready(function() {
        	$(document).on("click", "#issueEditBtn", function(event) {
        		
        		const editBox = $(this).parent('div')[0].children[4];
        		
        		if(editBox.style.display == 'none') {
        			editBox.style.display = 'block';
        		} else {
        			editBox.style.display = 'none';
        		}
        		
        		editBox.children[0].onclick = function() {
        			
        			const issueModifyModal = document.getElementById("issueDetailAndModifyModal");
        			
        			const issueModifyModalTitle = document.getElementById("issueModifyModalTitle");
        			const issueModifyDescription = document.getElementById("issueModifyDescription");
        			const issueModifyMemberList = document.getElementById("issueModifyMemberList");
        			
        			let memberCountIndex = 0;
        			
        			memberCountIndex = $(this).parent('div').parent('div')[0].children[$(this).parent('div').parent('div')[0].children.length - 1].value;
        			memberListStartIndex = $(this).parent('div').parent('div')[0].children.length - memberCountIndex - 1;
        			
        			issueModifyModalTitle.value = $(this).parent('div').parent('div')[0].children[2].innerText;
        			issueModifyDescription.value = $(this).parent('div').parent('div')[0].children[7].value;
        			const issueModifySprintCode = $(this).parent('div').parent('div')[0].children[8].value;
        			
        			$.ajax({
						url : "/byat/issue/sprintmemberlist",
						type : "post",
						data : {
							sprintCode : issueModifySprintCode,
						},
						success : function(data, status, xhr) {
							
							const selectBoxSprintMemberList = JSON.parse(data.sprintMemberList);
							
							modifyMemberSelectBox = document.getElementById("modifyMemberSelectBox");
							
							for(let i in selectBoxSprintMemberList) {
								
								selectBoxSprintMember = document.createElement('option');
								selectBoxSprintMember.setAttribute('style', 'text-align:center');
								selectBoxSprintMember.innerText = selectBoxSprintMemberList[i].id + " " + selectBoxSprintMemberList[i].name + " " + selectBoxSprintMemberList[i].no;
								
								modifyMemberSelectBox.appendChild(selectBoxSprintMember);
								
							}
							
						},
						error : function(xhr, status, error) {
							console.log(xhr);
						}
        			});
        			
        			memberCountIndex *= 1;
        			memberListStartIndex *= 1;
        			
        			console.log($(this).parent('div').parent('div')[0].children[8].value);
        			
        			let lastCount = 0;
        			lastCount = (memberCountIndex + memberListStartIndex);
        			
        			for(let index = memberListStartIndex; index < lastCount; index++) {
        				
        				issueMemberDiv = document.createElement('div');
        				issueMemberDiv.setAttribute('id', 'issueMember');
        				issueMemberDiv.setAttribute('class', 'issueMember');
        				
        				issueMemberInfoDiv = document.createElement('div');
        				issueMemberInfoDiv.setAttribute('id', 'issueMemberInfo');
        				issueMemberInfoDiv.setAttribute('class', 'issueMemberInfo');
        				issueMemberInfoDiv.setAttribute('name', 'issueMemberInfo');
        				issueMemberInfoDiv.innerText = $(this).parent('div').parent('div')[0].children[index].value;

        				issueMemberBody = document.createElement('input');
        				issueMemberBody.setAttribute('type', 'hidden');
        				issueMemberBody.setAttribute('id', 'issueMemberBody');
        				issueMemberBody.setAttribute('class', 'issueMemberBody');
        				issueMemberBody.setAttribute('name', 'issueMemberBody');
        				issueMemberBody.setAttribute('value', $(this).parent('div').parent('div')[0].children[index].value);
        				
        				thisSprintProjectCode = document.createElement('input');
        				thisSprintProjectCode.setAttribute('type', 'hidden');
        				thisSprintProjectCode.setAttribute('name', 'projectCode');
        				thisSprintProjectCode.setAttribute('value', document.getElementById("projectCode").value);
        				
        				thisSprintCode = document.createElement('input');
        				thisSprintCode.setAttribute('type', 'hidden');
        				thisSprintCode.setAttribute('name', 'sprintCode');
        				thisSprintCode.setAttribute('value', $(this).parent('div').parent('div')[0].children[8].value);
        				
        				thisIssueCode = document.createElement('input');
        				thisIssueCode.setAttribute('type', 'hidden');
        				thisIssueCode.setAttribute('name', 'issueCode');
        				thisIssueCode.setAttribute('value', $(this).parent('div').parent('div')[0].children[1].value);
        				
        				removeIssueMemberBtn = document.createElement('input');
        				removeIssueMemberBtn.setAttribute('type', 'button');
        				removeIssueMemberBtn.setAttribute('id', 'removeIssueMember');
        				removeIssueMemberBtn.setAttribute('class', 'removeIssueMember');
        				removeIssueMemberBtn.setAttribute('value', '제외하기');
        				
        				issueMemberDiv.appendChild(issueMemberInfoDiv);
        				issueMemberDiv.appendChild(issueMemberBody);
        				issueMemberDiv.appendChild(thisSprintProjectCode);
        				issueMemberDiv.appendChild(thisSprintCode);
        				issueMemberDiv.appendChild(thisIssueCode);
        				issueMemberDiv.appendChild(removeIssueMemberBtn);
        				
        				issueModifyMemberList.appendChild(issueMemberDiv);
        			}
        			
        			if(issueModifyModal.style.display == 'none') {
        				issueModifyModal.style.display = 'block';
        				editBox.style.display = 'none';
        			} else {
        				issueModifyModal.style.display = 'none';
        			}
        			
        		}
        		
        		editBox.children[1].onclick = function() {
        			
        			const thisKanban = $(this).parent('div').parent('div');
        			
        			if(!(document.getElementById("headSprintProgress").value == "완료")){
        				
        				document.getElementById("delete_modal").style.display="block";
        				editBox.style.display = 'none';
        				
        				document.getElementById("delete_modal_close_btn").onclick = function() {
                            document.getElementById("delete_modal").style.display="none";
                        }
                       
						document.getElementById("delete_modal_ok_btn").onclick = function() {
						   
						   location.href = "${ pageContext.servletContext.contextPath }/issue/remove?code=" + thisKanban[0].children[1].value;
						}
        				
        			} else {
        				
        				alert("완료된 스프린트의 이슈는 삭제하실 수 없습니다.");
        				
        			}
        			
        		}
        		
        	});
        	
        	$("#modifyMemberSelectBox").on('change', function() {

        		console.log(this);
        		
				const $alreadyissueMember = document.querySelectorAll("#issueMemberInfo");
				
				let checkAlreadyIssueMember = false;
				
				if(this.value != "----------") {
					
					for(let a = 0; a < $alreadyissueMember.length; a++) {
						
						if($alreadyissueMember[a].innerText == this.value) {
							
							checkAlreadyIssueMember = true;
						}
					}
					
					if(checkAlreadyIssueMember) {
						
						alert("이미 이슈의 담당자입니다.");
						
					} else {
						
						issueModifyMemberList = document.getElementById("issueModifyMemberList");
					
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
        				thisSprintProjectCode.setAttribute('value', document.getElementById("projectCode").value);
        				
        				thisSprintCode = document.createElement('input');
        				thisSprintCode.setAttribute('type', 'hidden');
        				thisSprintCode.setAttribute('name', 'sprintCode');
        				thisSprintCode.setAttribute('value', document.getElementById("issueKanban").children[8].value);
        				
        				thisIssueCode = document.createElement('input');
        				thisIssueCode.setAttribute('type', 'hidden');
        				thisIssueCode.setAttribute('name', 'issueCode');
        				thisIssueCode.setAttribute('value', document.getElementById("issueKanban").children[1].value);
        				
        				removeIssueMemberBtn = document.createElement('input');
        				removeIssueMemberBtn.setAttribute('type', 'button');
        				removeIssueMemberBtn.setAttribute('id', 'removeIssueMember');
        				removeIssueMemberBtn.setAttribute('class', 'removeIssueMember');
        				removeIssueMemberBtn.setAttribute('value', '제외하기');
        				
        				issueMemberDiv.appendChild(issueMemberInfoDiv);
        				issueMemberDiv.appendChild(issueMemberBody);
        				issueMemberDiv.appendChild(thisSprintProjectCode);
        				issueMemberDiv.appendChild(thisSprintCode);
        				issueMemberDiv.appendChild(thisIssueCode);
        				issueMemberDiv.appendChild(removeIssueMemberBtn);
        				
        				issueModifyMemberList.appendChild(issueMemberDiv);
						
					}
				}
				
			});
        	
        	$(document).on("click", "#issueModifyBtn", function(event) { 
        		
        		modifyMemberSelectBox = document.getElementById("issueModifyMemberList");
    			
    			if(modifyMemberSelectBox.children.length == 0) {
    				
    				alert("이슈 담당자를 지정해야합니다.");
    				
    			} else if(document.getElementById("headSprintProgress").value == "완료") {
    				
    				alert("완료된 스프린트의 이슈는 변경하실 수 없습니다.");
    				
    			} else {
    				
    				document.getElementById("issueDetailAndModifyForm").submit();
    			}
        		
        	});
        	
        	$(document).on("click", "#removeIssueMember", function(event) { 
        		
        		if($(this).parent('div').parent('div')[0].children.length == 1) {
        			
        			alert("담당자는 반드시 1명 이상이여야 합니다. 담당자 추가 후 제외시켜주시기 바랍니다.");
        		} else {
        			
        			$.ajax({
						url : "/byat/issue/removeissuemember",
						type : "post",
						data : {
							info : $(this).parent('div')[0].children[1].value,
							sprintCode : $(this).parent('div')[0].children[3].value,
							issueCode : $(this).parent('div')[0].children[4].value
						},
						async : false,
						success : function(data, status, xhr) {
							
							removeResult = 1;
							
						},
						error : function(xhr, status, error) {
							console.log(xhr);
						}
	        		});
        			
        			
        			if(removeResult == 1) {
        				alert("담당자 제외에 성공하셨습니다!");
        				$(this).parent('div').remove();
        				
        				const kanbanCheck = document.querySelectorAll("#issueKanban");
        				
        				for(let i = 0; i < kanbanCheck.length; i++) {

        					if(kanbanCheck[i].children[1].value == $(this).parent('div')[0].children[4].value) {
        						
        						for(let j = 0; j < kanbanCheck[i].children.length; j++) {
        							
        							if(kanbanCheck[i].children[j].value == $(this).parent('div')[0].children[1].value) {
        								
        								kanbanCheck[i].children[j].remove();
        								kanbanCheck[i].children[kanbanCheck[i].children.length - 1].value -= 1;
        								console.log("성공!");
        							}
        							
        						}
        						
        					}
        					
        				}
        				
        			} else {
        				alert("담당자 제외에 실패하셨습니다!");
        			}
        			
        		}
        		
        		
        	});
        	
        });

		document.getElementById("issueModifyModalCloseBtn").onclick = function() {

			document.getElementById("issueDetailAndModifyModal").style.display = 'none';
			document.getElementById("issueModifyModalTitle").value = "";
			document.getElementById("issueModifyDescription").value = "";
			modifyMemberSelectBoxChildNodes = document.getElementById("modifyMemberSelectBox");
			
			issueModifyMemberListChildNodes = document.getElementById("issueModifyMemberList");
			
			while(issueModifyMemberListChildNodes.hasChildNodes()) {
	            
	            issueModifyMemberListChildNodes.removeChild(issueModifyMemberListChildNodes.firstChild);
	            
	        }
			
			while(modifyMemberSelectBoxChildNodes.hasChildNodes()) {
				
				modifyMemberSelectBoxChildNodes.removeChild(modifyMemberSelectBoxChildNodes.firstChild);
			}
			
			modifyMemberSelectBox = document.getElementById("modifyMemberSelectBox");
			
			boxFirstElement = document.createElement('option');
			boxFirstElement.setAttribute('style', 'text-align:center');
			boxFirstElement.innerText = "----------";
			
			modifyMemberSelectBox.appendChild(boxFirstElement);
		}
		
	</script>
</body>
</html>
