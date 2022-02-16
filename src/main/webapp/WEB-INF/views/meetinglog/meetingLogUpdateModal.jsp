<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
	#meetingLogUpdateModal {
		display: none;
		position:relative;
		width:100%;
		height:100%;
		z-index:1;
		margin-top:5%;
	}
	
	#meetingLogUpdateModal button {
		display:inline-block;
		width:100px;
		margin-left:calc(100% - 100px - 10px);
	}
	
	#meetingLogUpdateModal .modal_content {
		width:500px;
		height:600px;
		margin:20px auto;
		background:#29428C;
		border:1px solid black;
		border-radius:25px;
	}
	
	#meetingLogUpdateModal .modal_layer {
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
	
	.modal_button button {
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:80px;
		height:30px;
		position:absolute;
	}
	
	#meetingLogCloseBtn {
		right:15%;
		top:5%;
	}
	
	#meetingLogDeleteBtn {
		right:40%;
		top:5%;
	}
	
	#meetingLogUpdateBtn {
		right:65%;
		top:5%;
	}
	
	.modal_content-box {
		width:90%;
		height:70%;
		font-size:40px;
		text-align:center;
		background: white;
		border-radius: 25px;
		margin-left: 28px;
		margin-top:3%;
	}
	
	.modal_button {
		width:100%;
		height:30%;
		float:right;
		position:relative;
		margin-top:5%;
	}
	
	h3 {
		margin-left: 20px;
	}
	
	h5 {
		font-size: 20px;
		float: left;
		margin-left: 30px;
		margin-right: 105px;
		margin-bottom: 0px;
		height: 20px;
	}
	
	form {
		width:100%;
		height:100%;
	}
	
	.meetingLogTitle {
		font-size:30px;	
	}
	
	.meetingLogDescription {
		margin-top: 10%;
	}
</style>

</head>
<body>
	<div id="meetingLogUpdateModal">
  
  		<div class="modal_content">
  		<form action="" method="post">
			<div class="modal_head">
			<h3>회의록 상세</h3>
	    	</div>
      		<div class="modal_content-box">
      			<input type="text" class="meetingLogTitle" name="meetingLogTitle" placeholder="MeetingLog-001">
      			<textarea class="meetingLogDescription" id="meetingLogDescription" rows="20" cols="49" placeholder="상세내용을 입력해주세요"></textarea>
      		</div>
      		<div class="modal_button">
	        	<button type="button" id="meetingLogUpdateBtn">Ok</button>
	        	<button type="button" id="meetingLogDeleteBtn">Delete</button>
	        	<button type="button" id="meetingLogCloseBtn">Cancel</button>
      		</div>
		</form>
   		</div>
  		<div class="modal_layer"></div>
	</div>
	
	<script>
		/* document.getElementById("plusBlackImage").onclick = function() { */
	        document.getElementById("meetingLogUpdateModal").style.display="block";
	    /* } */

		document.getElementById("meetingLogCloseBtn").onclick = function() {
	        document.getElementById("meetingLogUpdateModal").style.display="none";
	    }
		
		
	</script>
</body>
</html>