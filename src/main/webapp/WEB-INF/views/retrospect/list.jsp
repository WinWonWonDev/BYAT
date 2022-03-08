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
		
	}
	

	
	#toggle {
		
		border:1px solid black;
		position:absolute;
		top:20%;
		left:6%;
		width:200px;
		height:30px;
	}

	

	#comments {
		position:absolute;
		top:25.5%;
		left:6%;
		border:1px solid black;
		width:200px;
		height:250px;
		font-size:1.2em;
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
			
		
		

</style>
</head>
<body>
	<!-- 이 버튼위에 마우스 올리면 서브메뉴가 나오게 하던지(onmouseenter이걸로 하면될듯?), 아니면 버튼을 클릭했을 때 서브메뉴가 나오게 하던지? -->

	<div id="whiteBoard">
		<div class="retrospectListHead">
			<div id="retrospectTitle">Retrospect</div>
			<div class="searchMember">
				<div class="search-area">
					<select id="searchCondition" name="searchCondition">
						<option value="retrospectTitle">회고록 제목</option>
						<option value="name">이름</option>
						<option value="content">내용</option>
					</select> <input type="search">
					<button type="submit">검색</button>
				</div>
			</div>
		</div>
		<div class="retrospectListBox">
			<c:forEach items="${ requestScope.retrospectList }" var="retrospect">
				<details class="toggleDetails">
					<summary id="toggle">${ retrospect.title }의 회고록</summary>
					<div id="comments">
						<input type="hidden" id="sprintCode" value="${ retrospect.sprintCode }">
						<input type="hidden" id="retrospectCode" value="${ retrospect.code }">
					  댓글 <span>
							<input type="text" class="CommentsBox" id="comment-body" name="body">
							<button type="submit" id="regist-comment">등록</button>
						</span>
						<div id="comment-box">
							<c:forEach items="${ retrospect.commentList }" var="comment">
								<h6>${ comment.memberName } : </h6>
								<h6>${ comment.body }</h6>
								<!-- if) 이 댓글을 삭제하거나 수정하고 싶을 떄 -> 수정/삭제버튼 만들어주기  -->
								<br clear="both">							
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
	
	const $registBtn = document.querySelectorAll("#regist-comment");
	const $retrospectCode = document.querySelectorAll("#retrospectCode");
	const $comment = document.querySelectorAll("#comment-body");
	
	console.log($registBtn);
	console.log($retrospectCode);
	console.log($comment);
	
	for(let i = 0; i < $registBtn.length; i++) {
		
		$registBtn[i].onclick = function() {
			
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
					
					for(let j = 0; j < data.size(); j++){
						
						const $nameH6 = $("<h6>").text(data[i].memberName);
						const $commentH6 = $("<h6>").text(data[i].body);
						const $br = $("<br clear='both'>");
						
						
						$commentBox.append($nameH6);
						$commentBox.append($commentH6);
						$commentBox.append($br);
						
					}
					
				},
				error: function(xhr, status, error){
					console.log(xhr);
				}
			});
			
		}
		
	}

	
	</script>
</body>
</html>

