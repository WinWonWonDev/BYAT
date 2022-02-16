<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ include file="../common/menubar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	html {
		width: 100%;
		height: 98%;
		background: #D7E3FA;
		background-size: cover;
		position: relative;
	}
	
	body {
		width: 99%;
		height: 95%;
		overflow-y:hidden;
	}
	
	#whiteBoard {
		position: absolute;
		background: white;
		border: 1px solid black;
		top: 15%;
		left: 2%;
		width: 95%;
		height: 78%;
	}
	
	#managementTitle {
		position: absolute;
		top: 4%;
		left: 3%;
		font-size: 35px;
	}
	
	.historyListHead {
		height: 15%;
	}
	
	.historyListName {
		margin-left: 3%;
		margin-top: 1%;
		font-size: 35px;
		float: left;
		display: inline;
	}
	
	.searchMember {
		float: right;
		margin-right: 3%;
		margin-top: 2%;
	}
	
	.search-area {
		margin-left: auto;
		margin-right: auto;
	}
	
	.managementListBox {
		border: 1px solid black;
		height: 77%;
		margin-left: 3%;
		margin-right: 3%;
	}
	
	.managementTable {
		margin-left: 3%;
		margin-top: 3%;
	}
	
	#memberCodeTh {
		width: 200px;
	}
	
	#nameTh {
		width: 600px;
	}
	
	#rightTh {
		width: 200px;
	}
	
	#memberNumTh {
		width: 250px;
	}
	
	.plusButton {
		background: url("/byat/resources/images/plusButton.png") no-repeat;
		position: absolute;
		top: 8%;
		left: 91%;
	}
	
	.minusButton {
		background: url("/byat/resources/images/minusButton.png") no-repeat;
		position: absolute;
		top: 9%;
		left: 94%;
	}
	
	input.img-button {
		width: 32px;
		height: 32px;
		border: none;
		cursor: pointer;
	}
	
	#management-create-modal {
		display: none;
		position: relative;
		width: 100%;
		height: 100%;
		z-index: 1;
	}
	
	#management-update-modal {
		display: none;
		position: relative;
		width: 100%;
		height: 100%;
		z-index: 1;
	}
	
	.modal_content {
		position: absolute;
		top: 6%;
		left: 35%;
		width: 400px;
		height: 440px;
		margin: 20px auto;
		background: #29428C;
		border: 1px solid black;
		border-radius: 25px;
	}
	
	#management-create-modal .modal_layer {
		position: fixed;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		background: rgba(0, 0, 0, 0.5);
		z-index: -1;
	}
	
	#management-update-modal .modal_layer {
		position: fixed;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		background: rgba(0, 0, 0, 0.5);
		z-index: -1;
	}
	
	.modal_head {
		margin-left: 25px;
		margin-top:20px;
		height: 35px;
		color: white;
		text-align: left;
		font-size: 20px;
	}
	
	#management-close-btn {
		right: 30%;
		top: 10%;
	}
	
	#management-update-close-btn {
		right: 30%;
		top: 10%;
	}
	
	#management-create {
		right: 55%;
		top: 10%;
	}
	
	#management-update {
		right: 55%;
		top: 10%;
	}
	
	.modal_content-box {
		width: 84%;
		height: 75%;
		font-size: 40px;
		text-align: center;
		background: white;
		border-radius: 25px;
		margin-left: 28px;
		margin-top:10px;
	}
	
	.modal_button {
		width: 100%;
		height: 30%;
		float: right;
		position: relative;
	}
	
	.modal_button button {
		background-color: rgb(25, 25, 112);
		color: white;
		text-align: center;
		cursor: pointer;
		width: 80px;
		height: 30px;
		position: absolute;
	}
	
	form {
		height: 95%;
	}
	
	.managementModalInput {
		position: absolute;
		width: 260px;
		height: 47px;
	}
	
	.title {
		top: 23%;
		left: 15%;
	}
	
	.ID {
		top: 40%;
		left: 15%;
	}
	
	.role {
		position: absolute;
		width: 270px;
		height: 47px;
		top: 58%;
		left: 14.5%;
	}
	
	#modal {
		display: none;
		position: relative;
		width: 100%;
		height: 100%;
		z-index: 1;
	}
	
	#modal h2 {
		margin: 0;
	}
	
	#modal button {
		display: inline-block;
		width: 100px;
		margin-left: calc(100% - 100px - 10px);
	}
	
	#modal .modal_content2 {
		position: absolute;
		top: 5%;
		left: 25%;
		width: 700px;
		height: 310px;
		margin: 100px auto;
		/* padding:20px 10px; */
		background: #fff;
		border: 2px solid #666;
	}
	
	#modal .modal_layer {
		position: fixed;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		background: rgba(0, 0, 0, 0.5);
		z-index: -1;
	}
	
	.modal_head2 {
		width: 100.1%;
		height: 35px;
		background-color: rgb(25, 25, 112);
		color: white;
		text-align: center;
		font-size: 20px;
		float: right;
	}
	
	#modal_close_btn {
		background-color: rgb(25, 25, 112);
		color: white;
		text-align: center;
		cursor: pointer;
		width: 80px;
		height: 50px;
		position: absolute;
		right: 30%;
		top: 20%;
	}
	
	#modal_ok_btn {
		background-color: rgb(25, 25, 112);
		color: white;
		text-align: center;
		cursor: pointer;
		width: 80px;
		height: 50px;
		position: absolute;
		right: 55%;
		top: 20%;
	}
	
	.modal_content_message {
		width: 100%;
		height: 50%;
		float: right;
		font-size: 30px;
		text-align: center;
	}
	
	.modal_button2 {
		width: 100%;
		height: 30%;
		float: right;
		position: relative;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<div id="whiteBoard">
		<div class="historyListHead">
			<div id="managementTitle">멤버 목록</div>
			<div class="searchMember">
				<div class="search-area">
					<select id="searchCondition" name="searchCondition">
						<option value="name">이름</option>
						<option value="right">권한</option>
						<option value="memberNum">사번</option>
					</select> <input type="search">
					<button type="submit">검색</button>
				</div>
				<div id="managementButtons">
					<input type="button" id="plusButton" class="img-button plusButton">
					<input type="button" id="minusButton"
						class="img-button minusButton">
				</div>
			</div>
		</div>
		<div class="managementListBox">
			<table border="1" class="managementTable">
				<tr>
					<th id="memberCodeTh">멤버번호</th>
					<th id="nameTh">이름</th>
					<th id="rightTh">권한</th>
					<th id="memberNumTh">사번</th>
				</tr>
				<tr>

				</tr>
			</table>
		</div>
	</div>

	<!-- 멤버 추가(+) 모달창 -->

	<div id="management-create-modal">

		<div class="modal_content">
			<form action="" method="post">
				<div class="modal_head">
					<h3>멤버계정 생성</h3>
				</div>
				<div class="modal_content-box">
					<input type="text" class="managementModalInput title" name="managementTitle" placeholder="멤버 이름"> 
					<br> 
					<input type="text" class="managementModalInput ID" name="managementNum" placeholder="ID(사번)"> 
					<br> 
					<select name="managementRole" class="role">
						<option value="PM">PM</option>
						<option value="사원" selected="selected">사원</option>
					</select>
				</div>
				<div class="modal_button">
					<button type="button" id="management-create">Ok</button>
					<button type="button" id="management-close-btn">Cancel</button>
				</div>
			</form>
		</div>
		<div class="modal_layer"></div>
	</div>

	<div id="management-update-modal">

		<div class="modal_content">
			<form action="" method="post">
				<div class="modal_head">
					<h3>멤버계정 조회/수정</h3>
				</div>
				<div class="modal_content-box">
					<input type="text" class="managementModalInput title" name="managementtitle" placeholder="멤버 이름"> 
					<br> 
					<input type="text" class="managementModalInput ID" name="managementNum" placeholder="ID(사번)"> 
					<br> 
					<select name="managementRole" class="role">
						<option value="PM">PM</option>
						<option value="사원" selected="selected">사원</option>
					</select>
				</div>
				<div class="modal_button">
					<button type="button" id="management-update">Ok</button>
					<button type="button" id="management-update-close-btn">Cancel</button>
				</div>
			</form>
		</div>
		<div class="modal_layer"></div>
	</div>

	<!-- 삭제하시겠습니까?시스템모달창, 일단 임시로 만들어둠(멤버정보가 들어와야 클릭해서 삭제하는기능아직구현 안함)  -->
	<div id="modal">

		<div class="modal_content2">
			<div class="modal_head2">System Message</div>
			<div class="modal_content_message">
				<br>정말 삭제하시겠습니까? <br>삭제한 멤버의 정보는 복구할 수 없습니다.
			</div>
			<div class="modal_button2">
				<button type="button" id="modal_ok_btn">Ok</button>
				<button type="button" id="modal_close_btn">Cancel</button>
			</div>

		</div>

		<div class="modal_layer"></div>
	</div>

	<script>
		document.getElementById("plusButton").onclick = function() {
			document.getElementById("management-create-modal").style.display = "block";
		}

		document.getElementById("management-close-btn").onclick = function() {
			document.getElementById("management-create-modal").style.display = "none";
		}

		document.getElementById("management-update-close-btn").onclick = function() {
			document.getElementById("management-update-modal").style.display = "none";
		}
		
		document.getElementById("minusButton").onclick = function() {
			document.getElementById("modal").style.display = "block";
		}

		document.getElementById("modal_close_btn").onclick = function() {
			document.getElementById("modal").style.display = "none";
		}
	</script>
</body>
</html>