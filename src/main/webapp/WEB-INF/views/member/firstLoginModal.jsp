<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
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

#modal .modalContent {
	width: 700px;
	height: 300px;
	margin: 100px auto;
	/* padding:20px 10px; */
	background: #fff;
	border: 2px solid #666;
}

#modal .modalLayer {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.5);
	z-index: -1;
}

.modalHead {
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

#modalOkBtn {
	background-color: rgb(25, 25, 112);
	color: white;
	text-align: center;
	cursor: pointer;
	width: 80px;
	height: 50px;
	position: absolute;
	right: 44%;
	top: 20%;
}

.modalContentMessage {
	width: 100%;
	height: 50%;
	float: right;
	padding: 0px;
}

.modalButton {
	width: 100%;
	height: 30%;
	float: right;
	position: relative;
}

#contentHead {
	font-size: 40px;
	text-align: center;
	font-weight: bold;
	margin-top: -10px;
}

#contentBody {
	font-size: 25px;
	text-align: center;
	margin-top: -20px;
}

<!--
settingmodal 쪽 css -->#modal2 {
	display: none;
	position: relative;
	width: 100%;
	height: 100%;
	margin-top: 8%;
}

#modal2 button {
	display: inline-block;
	width: 100px;
	margin-left: calc(100% - 100px - 10px);
}

#modal2 .modal_content2 {
	position: absolute;
	top: 10%;
	left: 32%;
	width: 500px;
	height: 500px;
	margin: 20px auto;
	background: #29428C;
	border: 1px solid black;
	border-radius: 25px;
	z-index: 1050;
	width: 500px;
}

#modal2 .modal_layer2 {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.5);
}

.modal_head2 {
	height: 35px;
	color: white;
	text-align: left;
	font-size: 20px;
}

#modal_ok_btn {
	background-color: rgb(25, 25, 112);
	color: white;
	text-align: center;
	cursor: pointer;
	width: 80px;
	height: 30px;
	position: absolute;
	right: 41%;
	top: 10%;
}

.modal_content-box2 {
	position: absolute;
	width: 90%;
	height: 80%;
	font-size: 40px;
	text-align: center;
	background: white;
	border-radius: 25px;
	margin-left: 28px;
}

.modal_button2 {
	width: 100%;
	height: 20%;
	float: right;
	position: relative;
	margin-top: 3%;
	top: 80%;
}

h3 {
	margin-left: 20px;
}

.emailAddress {
	position: relative;
	top: 5%;
	width: 400px;
	height: 40px;
	font-size: 20px;
	height: 40px;
}

#emailDuplicateButton {
	position: relative;
}

#AuthenticationNumber {
	position: relative;
	left: 2%;
}

.phoneNumber {
	width: 400px;
	height: 40px;
	font-size: 20px;
	float: none;
	margin-top: 6%;
}

.newPassword {
	width: 400px;
	height: 40px;
	font-size: 20px;
	float: none;
	margin-top: 8%;
}

.newPasswordConfirm {
	width: 400px;
	height: 40px;
	font-size: 20px;
	float: none;
	margin-top: 8%;
}

h5 {
	font-size: 20px;
	float: left;
	margin-left: 30px;
	margin-right: 105px;
	margin-bottom: 0px;
	height: 20px;
}
</style>
<title>Insert title here</title>
</head>
<body style="overflow: hidden;">
	<div id="modal">

		<div class="modalContent">
			<div class="modalHead">Alert Message</div>
			<div class="modalContentMessage">
				<br>
				<p id="contentHead">환영합니다!</p>
				<br>
				<p id="contentBody">
					처음 이용하기 전 <font color="red">프로필</font>을 설정해주세요!
				</p>
			</div>
			<div class="modalButton">
				<button type="button" id="modalOkBtn">Ok</button>
			</div>

		</div>

		<div class="modalLayer"></div>
	</div>
<input type="hidden" value="${ sessionScope.loginMember.id }">
	<!-- setting쪽 모달 -->
	<div id="modal2">
		<div class="modal_content2">
			<div class="modal_head2">
				<h3>프로필 설정</h3>
			</div>
			<div class="modal_content-box2">
				<input type="email" class="emailAddress" id="emailAddress" placeholder="Email" required> 
				<input type="button" id="emailDuplicatieButton" value="중복확인" onclick="location.href='${ pageContext.servletContext.contextPath }/member/emailduplicationCheck'">
				<input type="button" id="AuthenticationNumber" value="인증번호보내기">
				<input type="text" class="phoneNumber" placeholder="Phone" required>
				<input type="password" class="newPassword" placeholder="newPassword" required> 
				<input type="password" class="newPasswordConfirm" placeholder="newPasswordConfirm" required>
			</div>
			<div class="modal_button2">
				<button type="submit" id="modal_ok_btn">Ok</button>
			</div>
		</div>
		<div class="modal_layer2"></div>
	</div>



	<script>
	document.getElementById("modal").style.display="block";
	document.getElementById("modal2").style.display="none";
 	
   
    document.getElementById("modalOkBtn").onclick = function() {
        document.getElementById("modal").style.display="none";
		document.getElementById("modal2").style.display="block";
    }
    
    document.getElementById("modal_ok_btn").onclick = function() {
    	document.getElementById("")
    	
    }
    
    
</script>
</body>
</html>