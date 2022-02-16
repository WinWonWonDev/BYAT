<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@300&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	html, body {
		position:relative;
		width: 100%;
		height: 100%;
		background: url("/byat/resources/images/byatBackground.png") no-repeat;
		background-size: cover;
		overflow-y:hidden;
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
 	
 		position:absolute;
 		top:10%;
 		left:30%;
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
 
	#passwordResetForm {
 	
 		width:80%;
 		height:40%;
	}
 
	.btn{
	
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

</style>
</head>
<body>

	<div id="byatLogoDiv">
		<img class="Logo" src="/byat/resources/images/byatLogo.png">
	</div>
	<br clear="left">

	<div id="firstDIV"></div>
	<div id="secondDIV" align="center">
		<div id="loginTitle" align="center"><h1>새 비밀번호 설정</h1></div>
		
		<form id="passwordResetForm" action="${ pageContext.servletContext.contextPath }/member/passwordreset" method="post">
			<div class="idBox" align="center">
				<input type="password" placeholder="Change Password"> 
				<br>
				
				<!-- 아이디 예외처리 -->
				<c:choose>
				<c:when test="">
				<input type="text" name="errorId" value="아이디를 입력해주세요." style="color:red"> 
				</c:when>
				<c:when test="">
				<input type="text" name="errorId" value="아이디를 찾을 수 없습니다." style="color:red"> 
				</c:when>
				
				
				</c:choose>
				<br>
				<input type="password" placeholder="Confirm Change Password">
				<br>
				
				<!-- 비밀번호 예외처리 -->
				<c:choose>
				<c:when test="">
				<input type="text" name="errorPwd" value="비밀번호를 입력해주세요." style="color:red">
				</c:when>
				<c:when test="">
				<input type="text" name="errorPwd" value="비밀번호가 일치하지 않습니다." style="color:red">
				</c:when>
				</c:choose>
				<br>
				
			</div>

			<br><br><br>
				 
			<div class="btns" align="center">
				<input type="submit" class="btn btn-or" value="Ok">
				<input type="button" class="btn btn-yg" value="cancel" id="regist">
			</div>
		</form>
		
	</div>
</body>
</html>