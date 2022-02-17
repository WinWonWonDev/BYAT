<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${ pageContext.servletContext.contextPath }/resources/css/sohyeon.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Comforter&family=Shadows+Into+Light&family=Ubuntu:wght@300&display=swap" rel="stylesheet">
 <style>
 
	@font-face {
    font-family: 'neon';
    src: url('../resources/font/ShadowsIntoLight-Regular.ttf') format('truetype');
	
	}
 
	html  {
		width: 100%;
		height: 98%;
		background:white;
		background-size: cover;
	}
   
	body {
		width: 99%;
		height: 95%;
	}
   
	#byatLogoDiv {
    
		width: 100%; 
		float: left;
	}
   
	#byatImg1 {
		float:center; 
		width:100%;
		height:100%;
	}
   
	#loginButton {
		position:absolute;
        top:600px;
        left:640px;
	}
   
	input.img-button {
		background: url("/byat/resources/images/byatButton01.png") no-repeat;
		border: none;
		width: 350px;
		height: 100px;
		cursor: pointer;
	}
	.firstPageClass {
		position: relative;
		width:100%;
		height:50%;
	}
   
	.imtext {
		font-family: 'Comforter', cursive;
		font-family: 'Shadows Into Light', cursive;
		font-family: 'neon', sans-serif;
		position:absolute;
		top:70%;
		left:25%;
		color:white;
		font-size:60px;
		font-weight:100;
 
	}
   
</style>
<title>Insert title here</title>
</head>
<body>
	<!-- 초기페이지 -->
	<div id="byatLogoDiv">
		<img class="Logo" src="/byat/resources/images/byatLogo.png">
	</div>

	<div class="firstPageClass">
		<img id="byatImg1" class="byatImg1" src="/byat/resources/images/byatBackground2.png" >
		<span class="imtext">Better Than your Agile Tool</span>
	</div>
   
	<div id="loginButton" align="center">
		<input type="button" class="img-button" onclick="location.href='${ pageContext.servletContext.contextPath }/member/login'">
	</div>

</body>
</html>