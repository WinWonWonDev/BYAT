<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <style>
 
	@font-face {
    font-family: 'neon';	
    src: url('../resources/font/ShadowsIntoLight-Regular.ttf') format('truetype');
	
	}
	
	html {
		background: url("/byat/resources/images/BackgroundPage.png") no-repeat;
		width:100%;
		height:100%;
		position:relative;
		background-size:cover;
	}
	
	body {
		margin:0px;
		width:100%;
		height:100%;
	}
	
	.errorimg {
		width:1000px;
		height:1000px;
		position:absolute;
		top:60%;
		left:45%;
	}
	
	.crystar {
		width:150px;
		height:150px;
	}
	
	.errorDefault {
		color:white;
		position:absolute;
		font-size: 70px;
		text-align:center;
		left:27%;
	}
	
</style>
<title>Insert title here</title>
</head>
<body style="overflow-y:hidden; overflow-x:hidden;">
	<div class="errorMessage">
		<p class="errorDefault"><strong>Default<br>ooops, sorry<br>tel : 010-6222-0160</strong></p>
	</div>
	<div class="errorimg">
		<img src="/byat/resources/images/cryStar.png" class="crystar">
	</div>
</body>
</html>