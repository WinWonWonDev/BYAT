 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/menubar.jsp" %>
<!DOCTYPE html>
<html>
<style>

	html {
		background: url("/byat/resources/images/byatBackground2.png") no-repeat;
		background-size:cover;
		width:100%;
		height:100%;
		position:relative;
	}
	
	body {
		margin:0px;
		width:100%;
		height:100%;
	}
	
	.imtext {

 		 position:absolute;
 		 top:40%;
 		 left:25%;
 	 	color:white;
 		 font-size:40px;
 		 font-weight:100;
 	
   }

</style>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	const message = '${ requestScope.message }';
	if(message != null && message != '') {
		alert(message);
	}	
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	
	<div class="firstPageClass">
		<span class="imtext" align="center">Better Than your Agile Tool<br>
											You could use our Comfortable function</span>
	</div>
	<c:if test="${ sessionScope.loginMember.initPwdYN eq 'Y' }">
		<jsp:include page="../member/firstLoginModal.jsp">
	</c:if>
	
</body>
</html>