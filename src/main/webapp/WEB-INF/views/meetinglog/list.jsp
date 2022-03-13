<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/menubar.jsp" %>
<%@ include file="../common/subMenu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BYAT 회의록 jsp</title>
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
      	overflow-y:hidden;
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
      	z-index:1;
      	
      	}
	
	.meetinglogListHead {
		height:20%;
	}

	.meetinglogListName {
		margin-left:3%;
		margin-top:3%;
		font-size:35px;
		float:left;
		display:inline;
	}
	
	.searchMeetinglog {
		float:right;
		margin-right:3%;
		margin-top:5%;
	}
	
	.search-area {
		margin-left:auto;
		margin-right:auto;
	}
	
	.meetinglogListBody{
		width:100%;
		height: 80%;
		border:1px solid black;
		overflow-y:scroll;
	}
	
	.meetinglogBox , .meetinglogPlusBox {
		float: left;
		width: 250px;
		height: 120px;
		margin-left:7%;
		margin-top:5%;
		border:2px solid black;
		cursor:pointer;
	}
	
	.titleBox{
		width:228px;
		height: 30px;
		font-size: 120%;
		text-align: center;
		border:1px solid black;
		text-shadow: 2px 2px 2px gray;
		overflow: hidden;
  		text-overflow: ellipsis;
  		white-space: nowrap;
		margin-top:5%;
		padding-left:10px;
		padding-right:10px;
	}
	
	.meetinglogCode{
		position: relative;
		text-align: left;
	 	margin-left:3%;
	 	width:10px;
	 	font-size:10px;
	 	margin-top:4px;
	}
	
	.dateBox{
		position: relative;
		float:right;
		margin-left:30px;
		margin-top:13%;
		margin-right:3%;
		cursor:pointer
	}
	
	.writerBox{
		position: relative;
		text-align: left;
	 	font-size: 10%;
	 	margin-top:15%;
	 	margin-left:3%;
	 	width:100px;
	}
	
	.plusBox {
		border:1px solid black;
		width:240px;
		height:30px;
		margin-left:2%;
		margin-top:10%;
		cursor:pointer;
	}
	
	.plusBlackImage {
		width:15px;
		height:15px;
		background:url("/byat/resources/images/plusBlackButton.png") no-repeat;
		background-size:cover;
		border:0px;
		margin-left:45%;
		margin-top:10px;
		cursor:pointer;
	}
	
	#meetinglogCreateModal {
		display:none;
		position:absolute;
		width:100%;
		height:100%;
		bottom:1%;
		z-index:1;
	}
	#meetinglogCreateModal button {
		display:inline-block;
		width:100px;
		margin-left:calc(100% - 100px - 10px);
	}
	#meetinglogCreateModal .modal_content {
		width:500px;
		height:600px;
		margin:20px auto;
		background:#29428C;
		border:1px solid black;
		border-radius:25px;
	}
	#meetinglogCreateModal .modal_layer {
		position:fixed;
		top:0;
		left:0;
		width:100%;
		height:100%;
		background:rgba(0, 0, 0, 0.5);
		bottom:1%;
		z-index:-1;
	}
	
	.modal_head {
		height:35px;
		color:white;
		text-align:left;
		font-size:20px;
		margin-top:20px;
	}
	
	#meetinglogDetailModal button {
		display:inline-block;
		width:100px;
		margin-left:calc(100% - 100px - 10px);
	}
	
	#meetinglogDetailModal{
		display: none;
		position: absolute;
		width:100%;
		height:100%;
		z-index:1;
	}
	
	#meetinglogDetailModal .modal_content {
		width:500px;
		height:600px;
		margin:20px auto;
		background:#29428C;
		border:1px solid black;
		border-radius:25px;
	}
	
	#meetinglogDetailModal .modal_layer {
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
		margin-top:20px;
	}
	
	#meetingLogCloseBtn, #meetingLogDetailCloseBtn {
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:80px;
		height:30px;
		position:absolute;
		right:15%;
		top:5%;
	}
	
	#meetingLogCreateBtn, #meetingLogDetailCreateBtn {
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:80px;
		height:30px;
		position:absolute;
		right:60%;
		top:5%;
	}
	#meetingLogDetailDeleteBtn {
		display:none;
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:80px;
		height:30px;
		position:absolute;
		right:38%;
		top:5%;
	}
	
	.modal_content-box {
		width:90%;
		height:70%;
		font-size:40px;
		text-align:center;
		background: white;
		border-radius: 25px;
		margin-left: 28px;
		margin-top:3%;
	}
	
	.modal_button {
		width:100%;
		height:30%;
		float:right;
		position:relative;
		margin-top:5%;
	}
	
	h3 {
		margin-left: 20px;
	}
	
	form {
		width:100%;
		height:100%;
	}
	
	.meetingLogTitle, .meetingLogDetailTitle {
		font-size:30px;	
		margin-top:20px;
	}
	
	.meetingLogDescription , .meetingLogDetailDescription {
		margin-top: 10%;
	}
	
	.titleError {
		display: none;
		color:red;
		font-size:15px;
		float:left;
		margin-left:40px;
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
	.delete_modal_head {
		width:100.1%;
		height:35px;
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		font-size:20px;
		float:right;
	}

</style>
<title>Insert title here</title>
</head>
<body>
	<div id="whiteBoard">
		<div class="meetinglogListHead">
			<div class="meetinglogListName">회의록 :  <c:out value="${projectName}" /></div>
			<div class="searchMeetinglog">
				<div class="search-area">
				</div>
			</div>
		</div>
		<div class="meetinglogListBody">
			<div class="meetinglogPlusBox" id="meetinglogPlusBox">
				<div class="plusBox" id="plusBox">
			      	<button type="button" class="plusBlackImage" id="plusBlackImage"></button>
				</div>
			</div>
				<c:forEach items="${meetinglogList}" var="meetinglogList">
					<div class="meetinglogBox" id="meetinglogBox">
						<input type="hidden" id="meetinglogCode" value="${meetinglogList.code}"/>
						<input type="hidden" id="projectCode" value="${meetinglogList.projectCode}"/>
						<input type="hidden" id="meetinglogListBodys" value="${meetinglogList.body}"/>
						<input type="hidden" id="meetinglogListTitle" value="${meetinglogList.title}"/>
						<div id="meetinglogCodeView" class="meetinglogCode"><c:out value="NO.${meetinglogList.code}"/></div>
						<div id="titleBox" class="titleBox"><c:out value="${meetinglogList.title}"/></div>
						<div id="dateBox" class="dateBox"><c:out value="작성날짜  : ${meetinglogList.writingDate}"/></div>
						<div class="writerBox"><c:out value=" ${meetinglogList.memberName}"/></div>
					</div>
				</c:forEach>
		</div>
	</div>
	
	<%-- 모달창  생성. --%>
	<div id="meetinglogCreateModal">
  		<div class="modal_content">
	  		<form id="createProjectForm" action="${ pageContext.servletContext.contextPath }/meetinglog/regist"  method="post">
				<div class="modal_head">
					<h3>회의록 생성</h3>
		    	</div>
	      		<div class="modal_content-box">
	      			<input type="hidden" id="projectCode" name="code" value="${code}"/>
	      			<input type="text"  id ="meetingLogTitle" class="meetingLogTitle" name="meetingLogTitle" placeholder="제목  (필수항목)">
	      			<div id ="titleError" class="titleError"><c:out value="제목(필수항목)을 입력해주세요.."/></div>
	      			<textarea  id ="meetingLogBody"class="meetingLogDescription" name="meetingLogDescription" rows="20" cols="49" placeholder="상세내용을 입력해주세요"></textarea>
	      		</div>
	      		<div class="modal_button">
		        	<button type="button" id="meetingLogCreateBtn">Ok</button>
		        	<button type="button" id="meetingLogCloseBtn">Cancel</button>
	      		</div>
			</form>
   		</div>
   		<div class="modal_layer"></div>
	</div>
	
	<%-- 모달창 상세 수정. --%>
	<div id="meetinglogDetailModal">
  		<div class="modal_content">
  			<form id="updateProjectForm" action="${ pageContext.servletContext.contextPath }/meetinglog/modify"  method="post">
				<div class="modal_head">
					<h3>회의록 상세/수정</h3>
		    	</div>
	      		<div class="modal_content-box">
	      			<input type="hidden" name="code" value="${code}">
	      			<input type="hidden" id="meetingLogDetailCode" name="meetingLogDetailCode" value="">
	      			<input type="text" id ="meetingLogDetailTitle" class="meetingLogDetailTitle" name="meetingLogDetailTitle" value="">
	      			<div id ="DetailTitleError" class="titleError"><c:out value="제목(필수항목)을 입력해주세요.."/></div>
	      			<textarea id="meetingLogDetailBody" class="meetingLogDescription"  name="meetingLogDetailBody"  rows="20" cols="49" ></textarea>
	      		</div>
	      		<div class="modal_button" id ="modal_button">
		        	<button type="button" id="meetingLogDetailCreateBtn">Ok</button>
	        		<button type="button" id="meetingLogDetailDeleteBtn" >Delete</button>
		        	<button type="button" id="meetingLogDetailCloseBtn">Cancel</button>
	      		</div>
      		</form>
   		</div>
   		<div class="modal_layer"></div>
	</div>
	
	<div id="delete_modal">
   
	    <div class="delete_modal_content">
		  	<form id="removeMeetingForm" action="${ pageContext.servletContext.contextPath }/meetinglog/remove"  method="post">
			    <input type="hidden" id ="meetingCode" name="meetingCode" value="">
			    <input type="hidden" name="code" value="${code}">
			    <div class="delete_modal_head">
			    	Alert Message
			    </div>
		       	<div class="delete_modal_content_message">
		  	   		<br>삭제한 회의록는 <font style="color:red;">복구</font>하실 수 없습니다. <br>정말 삭제하시겠습니까?
		       	</div>
		       	<div class="delete_modal_button">
			        <button type="button" id="delete_modal_ok_btn">Ok</button>
			        <button type="button" id="delete_modal_close_btn">Cancel</button>
		       	</div>
		    </form>
    	</div>
    	<div class="modal_layer"></div>
	</div>
	
	
	
	
	<script>
	
		/* 이슈 서브메뉴 */
		document.getElementById("selectIssueList").href = document.getElementById("selectIssueList").href + ${ requestScope.code };
		
		/*회의록 서브메뉴 */
		document.getElementById("selectMeetingLogList").href = document.getElementById("selectMeetingLogList").href + ${ requestScope.code };
	
		//스프린트 서브메뉴
		document.getElementById("selectSprintList").href = document.getElementById("selectSprintList").href + ${ requestScope.code };

		//회고록 서브메뉴
		document.getElementById("selectRetrospectList").href = document.getElementById("selectRetrospectList").href + ${ requestScope.code };

	
	
		const $meetinglogbody = document.getElementById("meetinglogbody");
		const $meetingLogDetailTitle = document.getElementById("meetingLogDetailTitle");
		const $meetingLogDetailBody = document.getElementById("meetingLogDetailBody");
		const $meetinglogDetailModal = document.getElementById("meetinglogDetailModal");
		const $meetinglogCreateModal = document.getElementById("meetinglogCreateModal");
		const $modal_button = document.getElementById("modal_button");
		const $meetingLogDetailDeleteBtn = document.getElementById("meetingLogDetailDeleteBtn");
		const $meetingLogDetailCode = document.getElementById("meetingLogDetailCode");
		const $meetingCode= document.getElementById("meetingCode");
		
		const $meetinglogBox = document.querySelectorAll("#meetinglogBox")
		const $meetinglogCode = document.querySelectorAll("#meetinglogCode");
		
		document.getElementById("meetinglogPlusBox").onclick = function() {
	        document.getElementById("meetinglogCreateModal").style.display="block";
	    }

		document.getElementById("meetingLogCloseBtn").onclick = function() {
	        document.getElementById("meetinglogCreateModal").style.display="none";
			document.getElementById("titleError").style.display="none";
	    }
		
		document.getElementById("meetingLogDetailCloseBtn").onclick = function() {
			document.getElementById("meetinglogDetailModal").style.display="none";
	    }
		
		document.getElementById("meetingLogCreateBtn").onclick = function(){
			$meetingLogTitleInput = document.getElementById("meetingLogTitle");
			/*생성 모달창 공백시 예외처리*/
			if($meetingLogTitleInput.value.trim() ==""){
				document.getElementById("titleError").style.display="block";
				console.log("예외");

			}else{
				document.getElementById("createProjectForm").submit();
				document.getElementById("titleError").style.display="none";

			}
		}
		
		document.getElementById("meetingLogDetailDeleteBtn").onclick=function(){
			document.getElementById("delete_modal").style.display="block";
			document.getElementById("DetailTitleError").style.display="none";
		}
		
		document.getElementById("delete_modal_ok_btn").onclick=function(){
			document.getElementById("removeMeetingForm").submit();
		}
		
		document.getElementById("delete_modal_close_btn").onclick=function(){
			document.getElementById("delete_modal").style.display="none";
		}
	
		
		document.getElementById("meetingLogDetailCreateBtn").onclick = function(){
			$meetingLogDetailTitleInput = document.getElementById("meetingLogDetailTitle");
			/*상세&수정 모달창 공백시 예외처리*/
			if($meetingLogDetailTitleInput.value.trim() ==""){
				document.getElementById("DetailTitleError").style.display="block";
			}else{
				console.log("디데일전송");
				document.getElementById("updateProjectForm").submit();
				document.getElementById("DetailTitleError").style.display="none";

			}

		}
		  // 상세 조회
		   $(document).ready(function(){
			for(let i = 0; i < $meetinglogBox.length; i++){
				$meetinglogBox[i].onclick = function() {
		     		$.ajax({
		   	  			url: "/byat/meetinglog/detail",
		   	  			type: "post",
		   	  			data: { "meetinglogCode" : $meetinglogCode[i].value},
		   	  			success:function(data, status, xhr){
		   	  				if(data.memberNo == "${ sessionScope.loginMember.no }"){
		   	  					$meetingLogDetailDeleteBtn.style.display="block";
		   	  				}else{
		   	  					$meetingLogDetailDeleteBtn.style.display="none";
		   	  				}
		   	  				$meetingLogDetailCode.value= data.code;
		   	  				$meetingLogDetailTitle.value=data.title;
		   	  				
		   	  				$meetingLogDetailBody.value=data.body;
		   	  				$meetinglogDetailModal.style.display="block";
		   	  				
		   	  			    $meetingCode.value= data.code;
		   	  			},
						error: function(xhr, status, error) {
							alert("상세 조회 실패");
						}
		   	  		});
		        }
			}
		});   
		  
	</script>

</body>
</html>