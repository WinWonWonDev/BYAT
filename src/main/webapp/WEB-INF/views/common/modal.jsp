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
		}
		
		#modal h2 {
			margin:0;
		}
		#modal button {
			display:inline-block;
			width:100px;
			margin-left:calc(100% - 100px - 10px);
		}
		
		#modal .modal_content {
			width:700px;
			height:300px;
			margin:100px auto;
			/* padding:20px 10px; */
			background:#fff;
			border:2px solid #666;
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
			width:100.1%;
			height:35px;
			background-color:rgb(25,25,112);
			color:white;
			text-align:center;
			font-size:20px;
			float:right;
		}
		
		#modal_close_btn {
			background-color:rgb(25,25,112);
			color:white;
			text-align:center;
			cursor:pointer;
			width:80px;
			height:50px;
			position:absolute;
			right:30%;
			top:20%;
		}
		
		#modal_ok_btn {
			background-color:rgb(25,25,112);
			color:white;
			text-align:center;
			cursor:pointer;
			width:80px;
			height:50px;
			position:absolute;
			right:55%;
			top:20%;
		}
		
		.modal_content_message {
			width:100%;
			height:50%;
			float:right;
			font-size:40px;
			text-align:center;
		}
		
		.modal_button {
			width:100%;
			height:30%;
			float:right;
			position:relative;
		}
</style>
<title>Insert title here</title>
</head>
<body>
<div id="root">
   
    <button type="button" id="modal_opne_btn">모달 창 열기</button>
       
</div>

<div id="modal">
   
    <div class="modal_content">
	    <div class="modal_head">
	    	System Message
	    </div>
       	<div class="modal_content_message">
  	   		<br>testMessage
       	</div>
       	<div class="modal_button">
	        <button type="button" id="modal_ok_btn">Ok</button>
	        <button type="button" id="modal_close_btn">Cancel</button>
       	</div>
       
    </div>
   
    <div class="modal_layer"></div>
</div>
<script>
    document.getElementById("modal_opne_btn").onclick = function() {
        document.getElementById("modal").style.display="block";
    }
   
    document.getElementById("modal_close_btn").onclick = function() {
        document.getElementById("modal").style.display="none";
    }
</script>
</body>
</html>