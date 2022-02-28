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
	
</style>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<title>Insert title here</title>
</head>
<body style="overflow-x: hidden;">
	<div id="whiteBoard">
		<div class="issueListHead">
			<div class="issueListName" id="issueListName">
				<c:forEach items="${ sprintList }" var="sprintList">
					<c:if test="${ sprintList.progress eq '진행전'}">
						<font style="color: rgba(48, 58, 154, 100)" id="titleFont">${ sprintList.title }</font>의 Issue						
					</c:if>
				</c:forEach>
			</div>
			<div class="selectArea">
				<select class="issueSelectBox" id="issueSelectBox" onchange="changeTitleSelection()">
					<c:forEach items="${ sprintList }" var="sprintList">
						<c:choose>
							<c:when test="${ sprintList.progress eq '진행전' }">
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
	
	<!-- 모달창 만들어야함! (이슈 상세, 수정) -->
	
	<script>
	
		let checkFirst = 0;
		let newProgress = "";
		
		const kanbanArea = document.querySelectorAll('#kanbanArea');

		const selectedTitle = document.getElementById("issueSelectBox");
		
		const selectedOption = selectedTitle.options[selectedTitle.selectedIndex].text;

		<c:forEach items="${sprintList}" var="sprintList" varStatus="status">
			if("${sprintList.title}" == selectedOption) {
				
				<c:forEach items="${sprintList.issueList}" var="issue" varStatus="issueStatus">
					
				if("${issue.progress}" == "해결전") {
					
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
					issueCode.setAttribute('value', "${issue.code}");
					issueCode.setAttribute('name', 'issueCode');
					
					issueTitle = document.createElement('div');
					issueTitle.setAttribute('id', 'issueTitle');
					issueTitle.setAttribute('class', 'issueTitle');
					issueTitle.setAttribute('name', 'title');
					issueTitle.innerText = "${issue.title}";
					
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
					issueWriter.innerText = "제기자 : " + "${issue.name}";
					
					issueWriteDate = document.createElement('div');
					issueWriteDate.setAttribute('id', 'issueWriteDate');
					issueWriteDate.setAttribute('class', 'issueWriteDate');
					issueWriteDate.setAttribute('name', 'writeTime');
					issueWriteDate.innerText = "제기일자 : " + "${issue.writingTime}";
					
					issueBody = document.createElement('input');
					issueBody.setAttribute('type', 'hidden');
					issueBody.setAttribute('id', 'issueBody');
					issueBody.setAttribute('name', 'body');
					issueBody.setAttribute('value', "${issue.body}");
					
					kanbanBox.appendChild(issueStatusBox);
					kanbanBox.appendChild(issueCode);
					kanbanBox.appendChild(issueTitle);
					kanbanBox.appendChild(issueEditBtn);
					kanbanBox.appendChild(issueEditBox);
					kanbanBox.appendChild(issueWriter);
					kanbanBox.appendChild(issueWriteDate);
					kanbanBox.appendChild(issueBody);
					
					kanbanArea[0].appendChild(kanbanBox);
				} else if("${issue.progress}" == "해결중") {
					
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
					issueCode.setAttribute('value', "${issue.code}");
					issueCode.setAttribute('name', 'issueCode');
					
					issueTitle = document.createElement('div');
					issueTitle.setAttribute('id', 'issueTitle');
					issueTitle.setAttribute('class', 'issueTitle');
					issueTitle.setAttribute('name', 'title');
					issueTitle.innerText = "${issue.title}";
					
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
					issueWriter.innerText = "제기자 : " + "${issue.name}";
					
					issueWriteDate = document.createElement('div');
					issueWriteDate.setAttribute('id', 'issueWriteDate');
					issueWriteDate.setAttribute('class', 'issueWriteDate');
					issueWriteDate.setAttribute('name', 'writeTime');
					issueWriteDate.innerText = "제기일자 : " + "${issue.writingTime}";
					
					issueBody = document.createElement('input');
					issueBody.setAttribute('type', 'hidden');
					issueBody.setAttribute('id', 'issueBody');
					issueBody.setAttribute('name', 'body');
					issueBody.setAttribute('value', "${issue.body}");
					
					kanbanBox.appendChild(issueStatusBox);
					kanbanBox.appendChild(issueCode);
					kanbanBox.appendChild(issueTitle);
					kanbanBox.appendChild(issueEditBtn);
					kanbanBox.appendChild(issueEditBox);
					kanbanBox.appendChild(issueWriter);
					kanbanBox.appendChild(issueWriteDate);
					kanbanBox.appendChild(issueBody);
					
					kanbanArea[1].appendChild(kanbanBox);
				} else {
					
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
					issueCode.setAttribute('value', "${issue.code}");
					issueCode.setAttribute('name', 'issueCode');
					
					issueTitle = document.createElement('div');
					issueTitle.setAttribute('id', 'issueTitle');
					issueTitle.setAttribute('class', 'issueTitle');
					issueTitle.setAttribute('name', 'title');
					issueTitle.innerText = "${issue.title}";
					
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
					issueWriter.innerText = "제기자 : " + "${issue.name}";
					
					issueWriteDate = document.createElement('div');
					issueWriteDate.setAttribute('id', 'issueWriteDate');
					issueWriteDate.setAttribute('class', 'issueWriteDate');
					issueWriteDate.setAttribute('name', 'writeTime');
					issueWriteDate.innerText = "제기일자 : " + "${issue.writingTime}";
					
					issueBody = document.createElement('input');
					issueBody.setAttribute('type', 'hidden');
					issueBody.setAttribute('id', 'issueBody');
					issueBody.setAttribute('name', 'body');
					issueBody.setAttribute('value', "${issue.body}");
					
					kanbanBox.appendChild(issueStatusBox);
					kanbanBox.appendChild(issueCode);
					kanbanBox.appendChild(issueTitle);
					kanbanBox.appendChild(issueEditBtn);
					kanbanBox.appendChild(issueEditBox);
					kanbanBox.appendChild(issueWriter);
					kanbanBox.appendChild(issueWriteDate);
					kanbanBox.appendChild(issueBody);
					
					kanbanArea[2].appendChild(kanbanBox);
				}
					
				
				</c:forEach>
				
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
			
			for(let i = 0; i < kanbanArea.length; i++) {
				
				while(kanbanArea[i].hasChildNodes()) {
					kanbanArea[i].removeChild(kanbanArea[i].firstChild);
				}
				
			}
			
			const titleFont = document.getElementById("titleFont");
			
			const selectedTitle = document.getElementById("issueSelectBox");
			
			const selectedOption = selectedTitle.options[selectedTitle.selectedIndex].text;
			
			<c:forEach items="${sprintList}" var="sprintList" varStatus="status">
				if("${sprintList.title}" == selectedOption) {
					
					titleFont.innerText = selectedOption;
					
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
									
									kanbanBox.appendChild(issueStatusBox);
									kanbanBox.appendChild(issueCode);
									kanbanBox.appendChild(issueTitle);
									kanbanBox.appendChild(issueEditBtn);
									kanbanBox.appendChild(issueEditBox);
									kanbanBox.appendChild(issueWriter);
									kanbanBox.appendChild(issueWriteDate);
									kanbanBox.appendChild(issueBody);
									
									kanbanArea[0].appendChild(kanbanBox);
									
								} else if(sprintIssueList[i].progress == "해결중") {
									
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
									
									kanbanBox.appendChild(issueStatusBox);
									kanbanBox.appendChild(issueCode);
									kanbanBox.appendChild(issueTitle);
									kanbanBox.appendChild(issueEditBtn);
									kanbanBox.appendChild(issueEditBox);
									kanbanBox.appendChild(issueWriter);
									kanbanBox.appendChild(issueWriteDate);
									kanbanBox.appendChild(issueBody);
									
									kanbanArea[1].appendChild(kanbanBox);
									
								} else {
									
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
									
									kanbanBox.appendChild(issueStatusBox);
									kanbanBox.appendChild(issueCode);
									kanbanBox.appendChild(issueTitle);
									kanbanBox.appendChild(issueEditBtn);
									kanbanBox.appendChild(issueEditBox);
									kanbanBox.appendChild(issueWriter);
									kanbanBox.appendChild(issueWriteDate);
									kanbanBox.appendChild(issueBody);
									
									kanbanArea[2].appendChild(kanbanBox);
									
								}
								
							}
							
							issueKanban = document.querySelectorAll('#issueKanban');
							console.log("issueKanban2 : " + issueKanban);
							
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
							
						},
						error : function(xhr, status, error) {
							console.log(xhr);
						}
					});
					
				}
				
			</c:forEach>
		}
		
		if(checkFirst == 0) {
			
			for(let i = 0; i < issueKanban.length; i++) {
				const item = issueKanban[i];
				
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
		
		$(document).ready(function() {
        	$(document).on("click", "#issueEditBtn", function(event) {
        		
        		const editBox = $(this).parent('div')[0].children[4];
        		
        		if(editBox.style.display == 'none') {
        			editBox.style.display = 'block';
        		} else {
        			editBox.style.display = 'none';
        		}
        		
        		editBox.children[0].onclick = function() {
        			
        			
        			
        		}
        		
        	});
        	
        });
		
		
	</script>
</body>
</html>