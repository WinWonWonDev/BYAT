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
      width:100px;
      height:100px;
      position:absolute;
      top:60%;
      left:45%;
      background-size:cover;
   }
   
   .crystar {
      width:150px;
      height:150px;
   }
   
   .error403page {
      color:white;
      position:absolute;
      font-size: 70px;
      text-align:center;
      left:23%;
   }
   
</style>
<title>Insert title here</title>
</head>
<body>
   <div class="errorMessage">
      <p class="error403page"><strong>403<br>ooops, you cannot access<br>it with your authority!</strong></p>
   </div>
   <div class="errorimg">
      <img src="/byat/resources/images/cryStar.png" class="crystar">
   </div>
</body>
</html>