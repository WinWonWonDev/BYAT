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
	
	.error404page {
		color:white;
		position:absolute;
		font-size: 70px;
		text-align:center;
		left:20%;
	}

</style>
<title>Insert title here</title>
</head>
<body>
	<div class="errorMessage">
		<p class="error404page"><strong>404<br>ooops, nothing to see here!<br>BYAT can’t find the page<br>please check url !</strong></p>
	</div>
	<div class="errorimg">
		<img src="/byat/resources/images/cryStar.png" class="crystar">
	</div>
</body>
</html>