<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/menubar.jsp" %>
<%@ include file="../common/subMenu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	#retrospectTitle {
		position:absolute;
		top:4%;
		left:3%;
		font-size:35px;
	}	
	.retrospectListHead {
		height:15%;
	}
	.searchMember {
		float:right;
		margin-right:3%;
		margin-top:2%;
	}
	.search-area {
		margin-left:auto;
		margin-right:auto;
	}
	.retrospectListBox {
		border:1px solid black;
		height:77%;
		margin-left:3%;
		margin-right:3%;
	}
/* 	#retrospectBox {
		
		position:absolute;
		top:20%;
		left:7%;
		width:200px;
		height:30px;
		border:1px solid black;
		
	}
	*/	
/* 	input.retrospectSelectBoxButtonImg {
		background:url("/byat/resources/images/selectBoxImg1.png") no-repeat;
		position:absolute;
		top:2%;
		left:82%;
		width:29px;
        height:22px;
        border:none;
        cursor:pointer;
		
	} */
	#selectBoxOfBox {
		border:1px solid black;
		position:absolute;
		top:100%;
		left:-0.5%;
		width:200px;
		height:250px;
	}
	.CommentsBox {
		margin-left:20px;
		width: 170px;
		height: 20px;
		margin-left: 5px;
		margin-bottom: 5px;
	}
	
	#updateButton {
		background:url("resources/images/updateButton.png") no-repeat;
		background-size:cover;
		width:10px;
		height:10px;
		border:none;
		cursor:pointer;
	}
	#deleteButton {
		background:url("resources/images/deleteButton.png") no-repeat;
		background-size:cover;
		width:10px;
		height:10px;
		border:none;
		cursor:pointer;
	}
	.toggleDetails {
		position: relative;
		float: left;
		margin-left: 22px;
		margin-right: 22px;
		
	}
	#toggle {
		position: relative;
		margin-top: 20px;
		width: 220px;
		height: 40px;
		z-index: 1;
		background-color:rgb(25,25,112);
		color: white;
		text-align: center;
		line-height: 40px;
		border:1px solid black;
		cursor: pointer;
	}
	#comments {
		position:absolute;
		top: 62px;
		border:1px solid black;
		width: 220px;
		height:250px;
		font-size:1.2em;
		background: white;
	}
	#searchCondition {
		position: relative;
	}
	#message {
		font-size: 10px;
		margin-left: 10px;
		margin-top: 5px;
		margin-bottom: 5px;
	}
	#comment-box {
		width: 90%;
		margin-left: 9px;
		overflow-y:scroll;
		height: 180px;
		border: 1px solid black;
	}
	#deleteComment {
		background: url("/byat/resources/images/commentDeleteButton.png") no-repeat;
		width: 20px;
		height: 20px;
		margin-bottom: 5px;
		float: none;
		right: 10px;
		border: white;
		cursor: pointer;
	}
	.commentMemberName {
		margin-left: 12px;
	}
	.commentDescription {
		margin-left: 9px;
		width: 150px;	
		float: left;
	}
	#regist-comment {
		cursor: pointer;
	}
</style>
</head>
<body>
	<div id="whiteBoard">
		<div class="retrospectListHead">
			<div id="retrospectTitle">Retrospect</div>
		</div>
		<div class="retrospectListBox">
			<c:forEach items="${ requestScope.retrospectList }" var="retrospect">
				<details class="toggleDetails">
					<summary id="toggle">
						${ retrospect.title }의 회고록
					</summary>
					<div id="comments">
						<input type="hidden" id="sprintCode" value="${ retrospect.sprintCode }">
						<input type="hidden" id="retrospectCode" value="${ retrospect.code }">
						<h4 id="message">댓글을 달아주세요.</h4>
					  	<span>
							<input type="text" class="CommentsBox" id="comment-body" name="body">
							<button type="button" id="regist-comment">등록</button>
						</span>
						<div id="comment-box">
							<c:forEach items="${ retrospect.commentList }" var="comment">
								<h6 class="commentMemberName">${ comment.memberName }</h6>
								<h6 class="commentDescription">-${ comment.body }</h6>
								<button type="button" id="deleteComment" value="${ comment.no }"></button>
							</c:forEach>
						</div>
						<button type="button" id="updateButton"></button>
						<button type="button" id="deleteButton"></button>
	
					</div>
				</details>
			</c:forEach>

		</div>
	</div>
	<script>
	
	document.getElementById("selectIssueList").href = document.getElementById("selectIssueList").href + ${ requestScope.code };
	document.getElementById("selectMeetingLogList").href = document.getElementById("selectMeetingLogList").href + ${ requestScope.code };
	document.getElementById("selectSprintList").href = document.getElementById("selectSprintList").href + ${ requestScope.code };
	document.getElementById("selectRetrospectList").href = document.getElementById("selectRetrospectList").href + ${ requestScope.code };
	
	const $registBtn = document.querySelectorAll("#regist-comment");
	const $retrospectCode = document.querySelectorAll("#retrospectCode");
	const $comment = document.querySelectorAll("#comment-body");
	
	console.log($registBtn);
	console.log($retrospectCode);
	console.log($comment);
	
	for(let i = 0; i < $registBtn.length; i++) {
		
		$registBtn[i].onclick = function() {
			
			console.log("zzzzzz");
			console.log($comment[i].value);
			console.log($retrospectCode[i].value);
			
			$.ajax({
				url: "/byat/retrospect/regist",
				type: "post",
				async: false,
				data: { "body": $comment[i].value,
						"retrospectCode": $retrospectCode[i].value },
				success: function(data, status, xhr){
					console.table(data);
					
					const $commentBox = $("#comment-box");
					$commentBox.html("");
					
					for(let j = 0; j < data.length; j++){
						
						const $nameH6 = $("<h6 class='commentMemberName'>").text(data[j].memberName);
						const $commentH6 = $("<h6 class='commentDescription'>").text("-" + data[j].body).css("margin-bottom","5px;");
						const $deleteButton = $("<button type='button' id='deleteComment'>").val(data[j].no);
						
						$commentBox.append($nameH6);
						$commentBox.append($commentH6);
						$commentBox.append($deleteButton);
					}
					
				},
				error: function(xhr, status, error){
					console.log(xhr);
				}
			});
			
		}
		
	}
	
	$(document).ready(function() {
		
		$(document).on("click", "#deleteComment", function(event) {

			const $commentNo = $(this).val();
			
			$.ajax({
				url: "/byat/retrospect/remove",
				type: "get",
				async: false,
				data: { "no": $commentNo },
				success: function(data, status, xhr){
					console.table(data);
					
					const $commentBox = $("#comment-box");
					$commentBox.html("");
					
					for(let j = 0; j < data.length; j++){
						
						const $nameH6 = $("<h6 class='commentMemberName'>").text(data[j].memberName);
						const $commentH6 = $("<h6 class='commentDescription'>").text("-" + data[j].body).css("margin-bottom","5px;");
						const $deleteButton = $("<button type='button' id='deleteComment'>").val(data[j].no);
						
						$commentBox.append($nameH6);
						$commentBox.append($commentH6);
						$commentBox.append($deleteButton);
					}
				},
				error: function(xhr, status, error){
					console.log(xhr);
				}
			});
		});
	});
	
	
	</script>
</body>
</html>

