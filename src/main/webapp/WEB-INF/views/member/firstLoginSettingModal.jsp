<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
		#modal {
			display: none;
			position:relative;
			width:100%;
			height:100%;
			z-index:1;
			margin-top:8%;
		}
		
		#modal button {
			display:inline-block;
			width:100px;
			margin-left:calc(100% - 100px - 10px);
		}
		
		#modal .modal_content {
			width:500px;
			height:500px;
			margin:20px auto;
			background:#29428C;
			border:1px solid black;
			border-radius:25px;
		}
		
		#modal .modal_layer {
			position:fixed;
			top:0;
			left:0;
			width:100%;
			height:100%;
			background:rgba(0, 0, 0, 0.5);
			z-index:-1;
		}
		.modal_head {
		
			height:35px;
			color:white;
			text-align:left;
			font-size:20px;
		}
		
		#modal_ok_btn {
			background-color:rgb(25,25,112);
			color:white;
			text-align:center;
			cursor:pointer;
			width:80px;
			height:30px;
			position:absolute;
			right:41%;
			top:10%;
		}
		
		.modal_content-box {
			width:90%;
			height:70%;
			font-size:40px;
			text-align:center;
			background: white;
			border-radius: 25px;
			margin-left: 28px;
		}
		
		.modal_button {
			width:100%;
			height:30%;
			float:right;
			position:relative;
			margin-top:3%;
		}
		
		h3 {
			margin-left: 20px;
		}
		
		.emailAddress {
			width: 400px;
			height: 40px;
			font-size: 20px;
		}
		
		.phoneNumber {
			width: 400px;
			height: 40px;
			font-size: 20px;
			float: none;
			margin-top:8%;
		}
		
		.newPassword {
			width: 400px;
			height: 40px;
			font-size: 20px;
			float: none;
			margin-top:8%;
		}
		
		.newPasswordConfirm {
			width: 400px;
			height: 40px;
			font-size: 20px;
			float: none;
			margin-top:8%;
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body style="overflow:hidden;">
<div id="modal">
   
    <div class="modal_content">
	    <div class="modal_head">
		    <h3>프로필 설정</h3>
	    </div>
       	<div class="modal_content-box">
       		<input type="email" class="emailAddress" placeholder="Email" required>
       		<input type="text" class="phoneNumber" placeholder="Phone" required>
       		<input type="password" class="newPassword" placeholder="newPassword" required>
       		<input type="password" class="newPasswordConfirm" placeholder="newPasswordConfirm" required>
       		
       	</div>
       	<div class="modal_button">
	        <button type="button" id="modal_ok_btn">Ok</button>
       	</div>
    </div>
    <div class="modal_layer"></div>
</div>
<script>
    document.getElementById("modal").style.display="block";
 	
   
    document.getElementById("modal_ok_btn").onclick = function() {
        document.getElementById("modal").style.display="none";
    }
    
</script>
</body>
</html>