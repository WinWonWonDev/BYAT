<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
	
	.btn-or {
	
		background:url("/byat/resources/images/subMenu.png") no-repeat;
		width:30px;
		height:150px;
		cursor:pointer;
		position:absolute;
		top:15%;
		left:0%;
		z-index: 1;
	}
 
 	#subMenuBox {
 		position:absolute;
 		top:15%;
 		left:2%;
 		border:1px solid black;
 		background:#4E73DF;
 		width:220px;
 		height:195px;
 		z-index: 1;
 	}
 	
	.subMenuUlTag {
 		padding-left:14px;
	}
 	
 	.subMenuUlTag li {
 		list-style:none;
 		font-size:1.1em;
 		padding-top:14px;
 	}
 
 	hr {
 		width:160px;
 	}
 	
 	.subMenuATag {
 		text-decoration : none;
 		color:white;
 	}
 	
</style>
</head>
<body>
 	<!-- MENU -->
	<div id="menu-box" class="btn-or" align="center" onclick="doDisplay()"></div>
	
		
	<!-- 마우스 올렸을 때 -->
	<div id="subMenuBox" style="display:none"> 
		<div id="subMenuTitles">
			<ul class="subMenuUlTag">
				<!-- 이거 href경로 추가해서 적어줘야함 쿠쿠르삥뽕 -->
				<li><a href="/byat/WEB-INF/views/sprint/ " class="subMenuATag">※ SPRINT</a></li>
				<hr>
				<li><a href="/byat/WEB-INF/views/issue/ " class="subMenuATag">※ ISSUE</a></li>
				<hr>
				<li><a href="/byat/WEB-INF/views/retrospect/ " class="subMenuATag">※ RETROSPECET</a></li>
				<hr>
				<li><a href="/byat/WEB-INF/views/meetingLog/ " class="subMenuATag">※ MEETING LOG</a></li>
				<hr>
			</ul>
		
		</div>

	</div>	
	
	
<script>
	

	function doDisplay(){
	   var con = document.getElementById("subMenuBox");
	   if(con.style.display=='none'){
	      con.style.display = 'block';
	   } else {
	      con.style.display = 'none';
	   }
	}

</script>

</body>
</html>