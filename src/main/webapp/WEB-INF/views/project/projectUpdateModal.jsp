<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	#projectUpdateModal {
		display: none;
		position:relative;
		width:100%;
		height:100%;
		z-index:1;
		bottom:1%;
	}
	
	#projectUpdateModal button {
		display:inline-block;
		width:100px;
		margin-left:calc(100% - 100px - 10px);
	}
	
	#projectUpdateModal .modal_content {
		width:1000px;
		height:680px;
		margin:20px auto;
		background:#29428C;
		border:1px solid black;
		border-radius:25px;
	}
	
	#projectUpdateModal .modal_layer {
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
	
	#projectUpdateModalCloseBtn {
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:110px;
		height:50px;
		position:absolute;
		right:30%;
	}
	
	#projectUpdateBtn {
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		cursor:pointer;
		width:110px;
		height:50px;
		position:absolute;
		right:65%;
	}
	
	.modal_content-box {
		width:90%;
		height:75%;
		font-size:40px;
		text-align:center;
		background: white;
		border-radius: 25px;
		margin-left: 53px;
		margin-top:3%;
	}
	
	.modal_button {
		width:100%;
		height:30%;
		float:right;
		position:relative;
		margin-top:2%;
	}
	
	h3 {
		text-align:center;
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
	
	.projectModalTitle {
		width:82%;
		font-size:20px;
	}
	
	.projectDescription {
		margin-top:2%;
	}
	
	.projectTitleTag {
		font-size:20px;
		text-align:left;
		margin-left:9%;
		color:rgba(25,25,112,54);
	}
	
	.start-day {
		margin-top:10px;
		margin-left:80px;
		width:250px;
		font-size:20px;
		float:left;
		
	}
	.end-day {
		margin-top:10px;
		margin-left:230px;
		width:250px;
		font-size:20px;
		float:left;
	}
	
	.projectStartDayTag {
		font-size:20px;
		float:left;
		margin-top:3%;
		margin-left:9%;
		color:rgba(25,25,112,54);
	}
	
	.projectEndDayTag {
		font-size:20px;
		float:left;
		margin-top:3%;
		margin-left:35.5%;
		color:rgba(25,25,112,54);
	}
	
	.projectDescriptionTag {
		font-size:20px;
		float:left;
		margin-top:3%;
		margin-left:9%;
		color:rgba(25,25,112,54);
	}
	
	.prjectCodeMessage {
		font-size:18px;
		float:left;
		color:rgb(54,231,71);
		margin-left:9%;
	}
	
</style>


</head>
<body>
	<div id="projectUpdateModal">
  
  		<div class="modal_content">
	  		<form action="" method="post">
				<div class="modal_head">
					<h3>프로젝트 상세</h3>
		    	</div>
	      		<div class="modal_content-box">
	      			<div style="height:30px;"></div>
	      			<div class="projectTitleTag">프로젝트 명</div>
	      			<input type="text" class="projectModalTitle" name="projectModalTitle" placeholder="Project-001">
	      			<div class="projectStartDayTag">프로젝트 시작일자</div>
	      			<div class="projectEndDayTag">프로젝트 종료일자</div>
	       			<br clear="both">
	       			<input type='date' class="start-day" name='projectStartDay'/>
	       			<input type='date' class="end-day" name='projectEndDay'/>
	       			<div class="projectDescriptionTag">프로젝트 상세 설명</div>
	      			<textarea class="projectDescription" id="projectDescription" rows="13" cols="100" placeholder="상세내용을 입력해주세요"></textarea>
	      			<div class="prjectCodeMessage">프로젝트  코드는 자동으로 생성됩니다.</div>
	      		</div>
	      		<div class="modal_button">
		        	<button type="button" id="projectUpdateBtn">Ok</button>
		        	<button type="button" id="projectUpdateModalCloseBtn">Cancel</button>
	      		</div>
			</form>
   		</div>
   		<div class="modal_layer"></div>
	</div>
	
	<script>
		document.getElementById("createProject").onclick = function() {
	        document.getElementById("projectUpdateModal").style.display="block";
	    }

		document.getElementById("projectUpdateModalCloseBtn").onclick = function() {
	        document.getElementById("projectUpdateModal").style.display="none";
	    }
	</script>
</body>
</html>