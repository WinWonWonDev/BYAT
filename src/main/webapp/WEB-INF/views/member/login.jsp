<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@300&display=swap"
	rel="stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	const message = '${ requestScope.message }';
	if (message != null && message != '') {
		alert(message);
	}
</script>

<style>
html, body {
	position: relative;
	width: 100%;
	height: 100%;
	background: url("/byat/resources/images/byatBackground.png") no-repeat;
	background-size: cover;
	overflow-y: hidden;
	overflow-x: hidden
}

#byatLogoDiv {
	width: 100%;
	float: left;
}

#firstDIV {
	float: left;
	width: 30%;
	height: 60%;
}

#secondDIV {
	position: absolute;
	top: 10%;
	left: 30%;
	border: 1px solid white;
	background-color: white;
	float: left;
	width: 40%;
	height: 80%;
	border-radius: 50px;
}

#loginTitle {
	font-family: 'Ubuntu', sans-serif;
	font-size: 30px;
	color: #191970;
}

#loginForm {
	width: 80%;
	height: 40%;
}

/* #loginMemberId {
	 	
	 		width: 100%;
			height:80%; 
			float:center;	
		} */
#passwordLink {
	/* margin-right: 10%; */
	
}

.btn {
	width: 130px;
	height: 50px;
	border-radius: 10px;
	color: white;
}

.btn-yg {
	background-color: #191970;
}

.btn-or {
	background-color: #191970;
	margin-right: 20px;
}

.idBox input {
	width: 100%;
	height: 43px;
}

#firstDIV2 {
	float: left;
	width: 30%;
	height: 60%;
}

#secondDIV2 {
	position: absolute;
	top: 10%;
	left: 30%;
	border: 1px solid white;
	background-color: white;
	float: left;
	width: 40%;
	height: 80%;
	border-radius: 50px;
}

#loginTitle2 {
	font-family: 'Ubuntu', sans-serif;
	font-size: 30px;
	color: #191970;
}

#passwordResetForm {
	width: 80%;
	height: 40%;
}

#inputIdModal {
	display: none;
	position: relative;
	width: 100%;
	height: 100%;
	z-index: 1050;
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


#inputIdModal h2 {
	margin: 0;
}


#inputIdModal .modalContent {
	width: 700px;
	height: 300px;
	margin: 100px auto;
	background: #fff;
	border: 2px solid #666;
}

.modalContentMessage {
	width: 100%;
	height: 30%;
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

#findPassword {
	border:none;
	cursor:pointer;
	
}

.inputPassword {
	position: relative;
	left:20%;
	width:430px;
	height:35px;

}

#modalOkBtn {
	background-color: rgb(25, 25, 112);
	color: white;
	text-align: center;
	cursor: pointer;
	width: 80px;
	height: 30px;
	position: absolute;
	right: 55%;
	top: 35%;
	}

#modalOkBtn2 {
	background-color: rgb(25, 25, 112);
	color: white;
	text-align: center;
	cursor: pointer;
	width: 80px;
	height: 30px;
	position: absolute;
	right: 55%;
	top: 35%;
	}
	
#modalCancelBtn {
	background-color: rgb(25, 25, 112);
	color: white;
	text-align: center;
	cursor: pointer;
	width: 80px;
	height: 30px;
	position: absolute;
	right: 35%;
	top: 35%;
}

#modalCancelBtn2 {
	background-color: rgb(25, 25, 112);
	color: white;
	text-align: center;
	cursor: pointer;
	width: 80px;
	height: 30px;
	position: absolute;
	right: 35%;
	top: 35%;
}
</style>
</head>
<body>

	<div id="loginDiv" style="display: block;">
		<div id="byatLogoDiv">
			<img class="Logo" src="/byat/resources/images/byatLogo.png">
		</div>
		<br clear="left">

		<div id="firstDIV"></div>
		<div id="secondDIV" align="center">
			<div id="loginTitle" align="center">
				<h1>LOGIN</h1>
			</div>

			<c:if test="${ empty sessionScope.loginMember }">
				<!-- 로그인이 필요한 경우 -->
				<form id="loginForm"
					action="${ pageContext.servletContext.contextPath }/member/login"
					method="post">
					<div class="idBox" align="center">
						<input type="text" name="id" id="loginMemberId" placeholder="Id">
						<br>
						<!-- 아이디 예외처리 -->
						<c:choose>
							<c:when test="">
								<input type="text" name="errorId" value="아이디를 입력해주세요."
									style="color: red">
							</c:when>
							<c:when test="">
								<input type="text" name="errorId" value="아이디를 찾을 수 없습니다."
									style="color: red">
							</c:when>


						</c:choose>
						<br> <input type="password" name="pwd" id="loginMemberPwd"
							placeholder="Password"> <br>

						<!-- 비밀번호 예외처리 -->
						<c:choose>
							<c:when test="">
								<input type="text" name="errorPwd" value="비밀번호를 입력해주세요."
									style="color: red">
							</c:when>
							<c:when test="">
								<input type="text" name="errorPwd" value="비밀번호가 일치하지 않습니다."
									style="color: red">
							</c:when>
						</c:choose>
						<br>

					</div>

					<h4 align="right" id="passwordLink">
						<p id="findPassword">비밀번호를잊으셨나요?</p>
					</h4>
					<br>

					<div class="btns" align="center">
						<input type="submit" class="btn btn-or" value="login" id="login">
						<input type="button" class="btn btn-yg" value="cancel" id="cancel">
					</div>
				</form>
			</c:if>
		</div>
	</div>
	
	<!-- 비밀번호 찾기: 아이디 입력 모달창 -->
	<div id="inputIdModal" style="display:none;">
		<div class="modalContent">
			<div class="modalHead">Alert Message</div>
			<div class="modalContentMessage">
				<br> <br>
				<p id="contentBody">
					비밀번호를 찾고자 하는 <font color="red">아이디</font>를 입력해주세요!
				</p>
			</div>
			<input type="text" class="inputPassword" name="password" placeholder="id">
			<div class="modalButton">
				<button type="button" id="modalOkBtn" onclick="${ pageContext.servletContext.contextPath }/member/passwordFind">Ok</button>
				<button type="button" id="modalCancelBtn">Cancel</button>
			</div>
		</div>
	</div>


	<!-- 비밀번호 찾기: 비밀번호 변경 모달창  -->
	<div id="passwordFindModal" style="display: none;">
		<div id="firstDIV2"></div>
		<div id="secondDIV2" align="center">
			<div id="loginTitle2" align="center">
				<h1>새 비밀번호 설정</h1>
			</div>

			<form id="passwordResetForm"
				action="${ pageContext.servletContext.contextPath }/member/passwordreset"
				method="post">
				<div class="idBox" align="center">
					<input type="password" placeholder="Change Password"> <br>

					<!-- 아이디 예외처리 -->
					<c:choose>
						<c:when test="">
							<input type="text" name="errorId" value="아이디를 입력해주세요."
								style="color: red">
						</c:when>
						<c:when test="">
							<input type="text" name="errorId" value="아이디를 찾을 수 없습니다."
								style="color: red">
						</c:when>


					</c:choose>
					<br> <input type="password"
						placeholder="Confirm Change Password"> <br>

					<!-- 비밀번호 예외처리 -->
					<c:choose>
						<c:when test="">
							<input type="text" name="errorPwd" value="비밀번호를 입력해주세요."
								style="color: red">
						</c:when>
						<c:when test="">
							<input type="text" name="errorPwd" value="비밀번호가 일치하지 않습니다."
								style="color: red">
						</c:when>
					</c:choose>
					<br>

				</div>

				<br> <br> <br>

				<div class="btns" align="center">
					<input type="submit" class="btn btn-or" value="Ok" id="modalOkBtn2"> 
					<input type="button" class="btn btn-yg" value="cancel" id="modalCancelBtn2">
				</div>
			</form>

		</div>
	</div>
	
	<script>
		const $cancel = document.getElementById("cancel");
		const $findPassword = document.getElementById("findPassword"); 
		const $modalCancelBtn = document.getElementById("modalCancelBtn");
		const $modalOkBtn = document.getElementById("modalOkBtn");
		const $modalCancelBtn2 = document.getElementById("modalCancelBtn2");
		
		$cancel.onclick = function() {
			location.href = "/byat";
		}
		
		$findPassword.onclick = function() {
			document.getElementById("inputIdModal").style.display="block";
			
		}
		
		$modalCancelBtn.onclick = function() {
			document.getElementById("inputIdModal").style.display="none";
		}
		
		$modalOkBtn.onclick = function() {
			document.getElementById("passwordFindModal").style.display="block";
		}
			
		$modalCancelBtn2.onclick = function() {
			document.getElementById("passwordFindModal").style.display="none";
			document.getElementById("inputIdModal").style.display="block";
			
		}
		
	    	
	    
	    
	</script>

</body>
</html>