<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Batang:wght@700&family=Song+Myung&display=swap" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>
<title>Better-than-Your-Agile-Tool</title>
<style>

	html{
		font-family: 'Gowun Batang', serif;
		font-family: 'Song Myung', serif;
	}
	* {
		margin: 0;
		padding: 0;
	}
	i {
		margin-right: 10px;
	}
	/*----------bootstrap-navbar-css------------*/
	.navbar-mainbg{
		background-color: #5161ce;
		padding: 0px;
	}
	#navbarSupportedContent{
		overflow: visible;
		position: relative;
	}
	#navbarSupportedContent ul{
		padding: 0px;
		margin: 0px;
	}
	#navbarSupportedContent ul li a i{
		margin-right: 10px;
	}
	#navbarSupportedContent li {
		list-style-type: none;
		float: left;
	}
	#navbarSupportedContent ul li a{
		color: rgba(255,255,255,0.5);
	    text-decoration: none;
	    font-size: 15px;
	    display: block;
	    padding: 20px 35px;
	    position: relative;
	}
	#navbarSupportedContent>ul>li.active>a{
		color: #5161ce;
		background-color: transparent;
		transition: all 0.7s;
	}
	#navbarSupportedContent a:not(:only-child):after {
		content: "\f105";
		position: absolute;
		right: 20px;
		top: 10px;
		font-size: 14px;
		font-family: "Font Awesome 5 Free";
		display: inline-block;
		padding-right: 3px;
		vertical-align: middle;
		font-weight: 900;
		transition: 0.5s;
	}
	#navbarSupportedContent .active>a:not(:only-child):after {
		transform: rotate(90deg);
		
	}
	.hori-selector{
		display:inline-block;
		position:absolute;
		height: 83%;
		top: 0px;
		left: 100px;
		transition-duration:0.6s;
		transition-timing-function: cubic-bezier(0.68, -0.55, 0.265, 1.55);
		background-color: #fff;
		border-top-left-radius: 15px;
		border-top-right-radius: 15px;
		margin-top: 10px;
	}
	.hori-selector .right,
	.hori-selector .left{
		position: absolute;
		width: 25px;
		height: 25px;
		background-color: #fff;
		top:23px;
	}
	.hori-selector .right{
		right: -25px;
	}
	.hori-selector .left{
		left: -25px;
	}
	.hori-selector .right:before,
	.hori-selector .left:before{
		content: '';
	    position: absolute;
	    width: 50px;
	    height: 50px;
	    border-radius: 50%;
	    background-color: #5161ce;
	}
	.hori-selector .right:before{
		bottom: 0;
	    right: -25px;
	}
	.hori-selector .left:before{
		bottom: 0;
	    left: -25px;
	}
	
	
	@media(min-width: 992px){
		.navbar-expand-custom {
		    -ms-flex-flow: row nowrap;
		    flex-flow: row nowrap;
		    -ms-flex-pack: start;
		    justify-content: flex-start;
		}
		.navbar-expand-custom .navbar-nav {
		    -ms-flex-direction: row;
		    flex-direction: row;
		}
		.navbar-expand-custom .navbar-toggler {
		    display: none;
		}
		.navbar-expand-custom .navbar-collapse {
			margin-left:20%;
			width:70%;
		    display: -ms-flexbox!important;
		    display: flex!important;
		    -ms-flex-preferred-size: auto;
		    flex-basis: auto;
		}
	}
	
	
	@media (max-width: 991px){
		#navbarSupportedContent ul li a{
			padding: 12px 30px;
		}
		.hori-selector{
			margin-top: 0px;
			margin-left: 10px;
			border-radius: 0;
			border-top-left-radius: 25px;
			border-bottom-left-radius: 25px;
		}
		.hori-selector .left,
		.hori-selector .right{
			right: 10px;
		}
		.hori-selector .left{
			top: -25px;
			left: auto;
		}
		.hori-selector .right{
			bottom: -25px;
		}
		.hori-selector .left:before{
			left: -25px;
			top: -25px;
		}
		.hori-selector .right:before{
			bottom: -25px;
			left: -25px;
		}
	}
	
	.homeButtonImg {
		background: url("/byat/resources/images/byatLogo2.png") no-repeat;
		float: left;
		width: 60px;
		height: 60px;
		border: 0px;
		cursor:pointer;
		position:absolute;
	}
	
	input.noticeButtonImg {
		position: absolute;
		background: url("/byat/resources/images/noticeIcon.png") no-repeat;
		width: 50px;
		height: 50px;
		margin-top: 15px;
		border: 0px;
		top: -10px;
		left: 840px;
		cursor:pointer;
	}

	.note-num {
		position: absolute;
		top: 4px;
		left:880px;
		height: 20px;
		width: 20px;
		line-height: 20px;
		text-align: center;
		background-color: red;
		border-radius: 15px;
		display: inline-block;
		color:white;
		border:none;
		font-size:10px;
	}
	
	.user-name {
	
		position:absolute;
		top: 20%;
		left: 100%;	
		height:30px;
		width:100px;
		font-size:1.5em;
	}
	
	#profileAndLogoutWhiteBoard {
		position:relative;
		left:1300px;
		top:-15px;
		width:150px;
		height:43px;
		background:white;
	}
	
	#ProfileArea {
		position: relative;
		border:1px solid black;
		text-align: center;
	}
	
	#logoutArea {
		position: relative;
		border:1px solid black;
		text-align: center;
	}
	
	#logoutModal {
		display: none;
		position:relative;
		width:100%;
		z-index:1500;
	}
	
	#logoutModal h2 {
			margin:0;
	}
	#logoutModal button {
			display:inline-block;
			width:100px;
			margin-left:calc(100% - 100px - 10px);
	}
		
	#logoutModal .logoutModalContent {
			width:700px;
			height:300px;
			margin:100px auto;
			/* padding:20px 10px; */
			background:#fff;
			border:2px solid #666;
	}
		
	#logoutModal .modal_layer {
			position:fixed;
			top:0;
			left:0;
			width:100%;
			height:100%;
			background:rgba(0, 0, 0, 0.5);
			z-index:-1;
	}
	.logoutModalHead {
			width:100.1%;
			height:35px;
			background-color:rgb(25,25,112);
			color:white;
			text-align:center;
			font-size:20px;
			float:right;
	}
		
	#logoutModalCloseButton {
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
		
	#logoutModalOkButton {
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
		
	.logoutModalContent_message {
			width:100%;
			height:50%;
			float:right;
			font-size:40px;
			text-align:center;
	}
		
	.logoutModalButton {
			width:100%;
			height:30%;
			float:right;
			position:relative;
	}
	
	.profile-area , #ProfileArea, #logoutArea {
		cursor: pointer;
	}

	.notificationBox {
		width:300px;
		height:310px;
		border-radius:18px;
		background-color:rgb(25, 25, 112);
		position:relative;
		margin-left:900px;
		z-index:1500;
		bottom:5px;
	}	
	
	.notificationContentBox {
		background-color:white;
		border-radius:18px;
		width:280px;
		height:250px;
		position:relative;
		margin-left:10px;
		margin-top:10px;
	}
	
	.alreadyConfirmNoticeRemoveBtn {
		position:relative;
		border:none;
		color:white;
		font-size:12px;
		font-weight:bold;
		cursor:pointer;	
		background-color:transparent;
		margin-left:10px;
		margin-top:8px;
	}
	
	.notificationSettingBtn {
		background: url("/byat/resources/images/noticeSettingGearBtn.png") no-repeat;
		position:relative;
		border:none;
		background-color:transparent;
		width:32px;
		height:20px;
		margin-left:20px;
		margin-top:2px;
		cursor:pointer;
	}
	
	.notification {
		position:relative;
		border:none;
		border-bottom:2px solid black;
		width:100%;
		height:40px;
		cursor:pointer;
	}
	
	.notification:last-child {
		
		border-bottom-left-radius: 18px;
		border-bottom-right-radius: 18px;
	}
	
	.notification:first-child {
		border-top-left-radius: 18px;
		border-top-right-radius: 18px;
	}
	
	.notificationText {
		font-size : 11px;
		position:relative;
		left: 7px;
		top: 5px;
	}
	
	.moreNoticeBtn {
		position:absolute;
		border:none;
		background-color:transparent;
		color:white;
		font-size:12px;
		cursor:pointer;
		left:240px;
		font-weight:bold;
		padding-top:6px;
	}
	
	div.user-image {
      position: absolute;
      width: 50px;
      height: 55px;
      right:0.5%;
   }
	
</style>

<script type="text/javascript">-
	/* $(".horiselector")[0].style.left = 210.35 + "px"; */
	console.log(window.location.pathname);
</script>
</head>
<body scroll="no">
	<nav class="navbar navbar-expand-custom navbar-mainbg">
		<div class="logoIcon">
			<input type="button" class="homeButtonImg" onclick="location.href='${ pageContext.servletContext.contextPath }/home'" >
		</div>
       
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto" style="margin-left:100px">
                 
             	<div class="hori-selector"><div class="left"></div><div class="right"></div></div>
                <li class="nav-item">
                    <a class="nav-link" href="${ pageContext.servletContext.contextPath }/home">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${ pageContext.servletContext.contextPath }/project/list">Project</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${ pageContext.servletContext.contextPath }/mytask/list">My Task</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${ pageContext.servletContext.contextPath }/calendar/list">Calendar</a>
                </li>
                <c:if test="${ sessionScope.loginMember.permitCode == 1 }"> 
	                <li class="nav-item">
	                    <a class="nav-link" href="${ pageContext.servletContext.contextPath }/management/list">Management</a>
	                </li>                
                </c:if> 
            </ul>

  <%--           	
  					<메뉴를 눌렀을 시 그 페이지로 이동 + 하얀색이 그 페이지에 맞게 이동되는 것! (밑에 javascript에도 있음)ㄱㄷㄱㄷ 나중에 해봄>
  				<div class="hori-selector"><div class="left"></div><div class="right"></div></div>
                <li class="nav-item active" id="homeMenu">
                    <a class="nav-link" href="${ pageContext.servletContext.contextPath }/home">Home</a>
                </li>
                <li class="nav-item" id="projectMenu">
                    <a class="nav-link" href="${ pageContext.servletContext.contextPath }/project/list">Project</a>
                </li>
                <li class="nav-item" id="mytaskMenu">
                    <a class="nav-link" href="${ pageContext.servletContext.contextPath }/mytask/list">My Task</a>
                </li>
                <li class="nav-item" id="calendarMenu">
                    <a class="nav-link" href="${ pageContext.servletContext.contextPath }/calendar/list">Calendar</a>
                </li>
                <li class="nav-item" id="historyMenu">
                    <a class="nav-link" href="${ pageContext.servletContext.contextPath }/history/list">History</a>
                </li>
                <c:if test="${ sessionScope.loginMember.permit eq 'ADMIN' }"> 
	                <li class="nav-item" id="adminMenu">
	                    <a class="nav-link" href="${ pageContext.servletContext.contextPath }/management/list">Management</a>
	                </li>                
                </c:if> --%>


            <input type="button" class="noticeButtonImg" onclick="noticeDisplay();">
            
            <!-- 알림 갯수에 따라 숫자 나오게 하는거 ㅋㅋ ㄱㄷ... -->
			<div class="note-num" id="note-num">0</div>
			<div class="profile-area" id="profileName">
			<div class="user-name">
				${ sessionScope.loginMember.name }
			</div>
			<div class="user-image">
			<img src="${ pageContext.servletContext.contextPath }${ sessionScope.attachment.thumbnailPath }" alt="프로필사진" onerror="this.src='${ pageContext.servletContext.contextPath }/resources/images/defaultProfile.png'">
         	</div>
			</div>
        </div>
    </nav>
    
    <!-- 프로필관리/로그아웃 모달창 -->
    <div id="profileAndLogoutModal" style="display:none;">
    	<div id="profileAndLogoutWhiteBoard">
    		<div id="ProfileArea" onclick="location.href='${ pageContext.servletContext.contextPath }/profile/mypage'">프로필 관리</div>
    		<div id="logoutArea">로그아웃</div>
    	</div>
    </div>
    
    <!-- 로그아웃 모달창 -->
    <div id="logoutModal" style="display:none;">
	    <div class="logoutModalContent">
		    <div class="logoutModalHead">
		    	System Message
		    </div>
	       	<div class="logoutModalContent_message">
	  	   		<br>로그아웃 하시겠습니까?
	       	</div>
	       	<div class="logoutModalButton">
		        <button type="button" id="logoutModalOkButton" onclick="location.href='${ pageContext.servletContext.contextPath }/member/logout'">Ok</button>
		        <button type="button" id="logoutModalCloseButton">Cancel</button>
	       	</div>
	    </div>
    	<div class="modal_layer"></div>
	</div>
	<div class="notificationBox" id="notificationBox" style="display:none">
		<button type="button" class="alreadyConfirmNoticeRemoveBtn" id="alreadyConfirmNoticeRemoveBtn">조회한 알림 삭제</button>
		<div class="notificationContentBox" id="notificationContentBox">
			<div class="notification" id="notification"><div class="notificationText" id="notificationText"></div></div>
			<div class="notification" id="notification"><div class="notificationText" id="notificationText"></div></div>
			<div class="notification" id="notification"><div class="notificationText" id="notificationText"></div></div>
			<div class="notification" id="notification"><div class="notificationText" id="notificationText"></div></div>
			<div class="notification" id="notification"><div class="notificationText" id="notificationText"></div></div>
			<div class="notification" id="notification" style="border-bottom:none"><div class="notificationText" id="notificationText"></div></div>
		</div>
		<button type="button" class="notificationSettingBtn" id="notificationSettingBtn"></button>
		<button type="button" class="moreNoticeBtn" id="moreNoticeBtn">더보기</button>
		
	</div>
	

    <script>
    
    	document.getElementById("notificationSettingBtn").onclick = function() {
    		
    		location.href = '${ pageContext.servletContext.contextPath }/notice/noticesetting?no=' + '${ sessionScope.loginMember.no }';
    		
    	}
    	
    	document.getElementById("moreNoticeBtn").onclick = function() {
    		
    		location.href = '${ pageContext.servletContext.contextPath }/notice/listall?no=' + '${ sessionScope.loginMember.no }';
    	}
    
		const $notificationText = document.querySelectorAll("#notificationText");
		const noteNum = document.getElementById("note-num");
		const $notification = document.querySelectorAll("#notification");

		let alreadyConfirmCount = 0;
		
		let sock = new SockJS("http://localhost:8001/byat/echo-ws/");
		sock.onmessage = onMessage;
		sock.onclose = onClose;

		window.onload = function() {
			
			$.ajax({
				url:"/byat/notice/list",
				type: 'post',
				data: { no : "${sessionScope.loginMember.no}" },
				success: function(data, status, xhr) {
					
					const notice = JSON.parse(data.noticeList);
					
					let noticeCount = 0;
					
					if(notice.length == 0) {
						noteNum.innerText = noticeCount;
					}
					
					for(let i in notice) {
						
						if(notice[i].status != "Y") {
							noticeCount++;
						}
						
					}
					
					noteNum.innerText = noticeCount + "+";
				},
				error: function(xhr, status, error) {
					console.log(xhr);
				}
			});
			
		}
		
		// 서버로부터 메시지를 받았을 때
		function onMessage(msg) {
			var data = msg.data;
			
			noteNum.innerText = "";
			
			$.ajax({
				url:"/byat/notice/list",
				type: 'post',
				data: { no : data },
				success: function(data, status, xhr) {
					
					const notice = JSON.parse(data.noticeList);
					
					let noticeCount = 0;
					
					if(notice.length == 0) {
						noteNum.innerText = noticeCount;
					}
					
					for(let i = 0; i < $notification.length; i++) {
						
						$notificationText[i].innerText = "";
						$notification[i].style.background = 'white';
						$notification[i].onclick = null;
					}
					
					for(let i in notice) {
						
						if(notice[i].status != "Y") {
							noticeCount++;
						} else {
							alreadyConfirmCount++;
						}
						
						if(i < 6) {
							$notificationText[i].innerText = notice[i].body;
							
							if(notice[i].status == "Y") {
								$notification[i].style.background  = "#C2C2C2";
							}
							
							$notification[i].onclick = function() {
								
								$.ajax({
									url:"/byat/notice/select",
									type: "post",
									data:{ 
										code : notice[i].code,
										no : notice[i].no	
									}
								});
								
								location.href = '${ pageContext.servletContext.contextPath }' + notice[i].url;
								
							}
						}
						
					}
					noteNum.innerText = noticeCount + "+";
				},
				error: function(xhr, status, error) {
					console.log(xhr);
				}
			});
			
		}
		// 서버와 연결을 끊었을 때
		function onClose(evt) {
			alert("실시간 통신 연결이 끊겼습니다!");
		}
		
		function noticeDisplay() {
    		
			if(document.getElementById("profileAndLogoutModal").style.display == "block") {
				document.getElementById("profileAndLogoutModal").style.display = "none";
			}
			
    		sock.send("${sessionScope.loginMember.no}");
    		
    		const notificationBox = document.getElementById("notificationBox");
    		if(notificationBox.style.display == 'none') {
    			notificationBox.style.display = 'block';
    		} else {
    			notificationBox.style.display = 'none';
    		}
    		
    	}
		
	    function test() {
	    	var tabsNewAnim = $('#navbarSupportedContent');
	    	var selectorNewAnim = $('#navbarSupportedContent').find('li').length;
	    	var activeItemNewAnim = tabsNewAnim.find('.active');
	    	var activeWidthNewAnimHeight = activeItemNewAnim.innerHeight();
	    	var activeWidthNewAnimWidth = activeItemNewAnim.innerWidth();
	    	var itemPosNewAnimTop = activeItemNewAnim.position();
	    	var itemPosNewAnimLeft = activeItemNewAnim.position();
	    	
	    	$(".hori-selector").css({
	    		"top":itemPosNewAnimTop.top + "px", 
	    		"left":itemPosNewAnimLeft.left + "px",
	    		"height": activeWidthNewAnimHeight - 11.0 + "px",
	    		"width": activeWidthNewAnimWidth + "px"
	    	});
	    	$("#navbarSupportedContent").on("click","li",function(e){
	    		$('#navbarSupportedContent ul li').removeClass("active");
	    		$(this).addClass('active');
	    		var activeWidthNewAnimHeight = $(this).innerHeight();
	    		var activeWidthNewAnimWidth = $(this).innerWidth();
	    		var itemPosNewAnimTop = $(this).position();
	    		var itemPosNewAnimLeft = $(this).position();
	    		$(".hori-selector").css({
	    			"top":itemPosNewAnimTop.top + "px", 
	    			"left":itemPosNewAnimLeft.left + "px",
	    			"height": activeWidthNewAnimHeight - 11.0 + "px",
	    			"width": activeWidthNewAnimWidth + "px"
	    		});
	    	});
	    }
	    $(document).ready(function(){
	    	setTimeout(function(){ test(); });
	    });
	    $(window).on('resize', function(){
	    	setTimeout(function(){ test(); }, 500);
	    });
	    $(".navbar-toggler").click(function(){
	    	$(".navbar-collapse").slideToggle(300);
	    	setTimeout(function(){ test(); });
	    });
	
	
	
/* 	  
		<메뉴를 눌렀을 시 그 페이지로 이동 + 하얀색이 그 페이지에 맞게 이동되는 것! ㄱㄷㄱㄷ 나중에 해봄>
		// --------------add active class-on another-page move----------
	    jQuery(document).ready(function($){
	    	// Get current path and find target link
	    	var path = window.location.pathname.split("/").pop();
	
	    	// Account for home page with empty path
	    	if ( path == '${ pageContext.servletContext.contextPath }/home}' ) {
	    		location.href = '${ pageContext.servletContext.contextPath }/home';
	    	}  else if ( path == '${ pageContext.servletContext.contextPath }/project/list') {
	    		location.href = '${ pageContext.servletContext.contextPath }/project/list';
	    	}  
	
	    	var target = $('#navbarSupportedContent ul li a[href="'+path+'"]');
	    	// Add active class to target link
	    	target.parent().addClass('active');
	    }); */
	    
 	    // --------------add active class-on another-page move----------
	    jQuery(document).ready(function($){
	    	// Get current path and find target link
	    	var path = window.location.pathname.split("/").pop();
	    	
	    	console.log(window.location.pathname.split("/")[2]);
	    	
	    	// Account for home page with empty path
	    	if( path == '' ) {
	    		path = 'index.html';
	    	} else if( path == 'home' ) {
	    		path = '${ pageContext.servletContext.contextPath }/home';
	    	} else if( window.location.pathname.split("/")[2] == 'project' ) {
	    		path = '${ pageContext.servletContext.contextPath }/project/list';
	    	} else if( window.location.pathname.split("/")[2] == 'mytask' ) {
	    		path = '${ pageContext.servletContext.contextPath }/mytask/list';
	    	} else if( window.location.pathname.split("/")[2] == 'calendar'){
	    		path = '${ pageContext.servletContext.contextPath }/calendar/list';
	    	} else if( window.location.pathname.split("/")[2] == 'notice' ) {
	    		path = '${ pageContext.servletContext.contextPath }/home';
	    	} else if( window.location.pathname.split("/")[2] == 'sprint' ) {
	    		path = '${ pageContext.servletContext.contextPath }/project/list';
	    	} else if( window.location.pathname.split("/")[2] == 'issue' ) {
	    		path = '${ pageContext.servletContext.contextPath }/project/list';
	    	} else if( window.location.pathname.split("/")[2] == 'retrospect' ) {
	    		path = '${ pageContext.servletContext.contextPath }/project/list';
	    	} else if( window.location.pathname.split("/")[2] == 'meetinglog' ) {
	    		path = '${ pageContext.servletContext.contextPath }/project/list';
	    	} else if( window.location.pathname.split("/")[2] == 'management' ) {
	    		path = '${ pageContext.servletContext.contextPath }/management/list'
	    	} else if( window.location.pathname.split("/")[2] == 'profile' ) {
	    		path = '${ pageContext.servletContext.contextPath }/home';
	    	}
	    	
	    	var target = $('#navbarSupportedContent ul li a[href="'+path+'"]');
	    	// Add active class to target link
	    	
	    	target.parent().addClass('active');
	    	
	    }); 
	    
	    const $profileName = document.getElementById("profileName");
	    const $logoutArea = document.getElementById("logoutArea");
		    
	    /* 결계 만들어야 함 ㄱㄷ */
	    $profileName.onclick = function() {
	    	if(document.getElementById("profileAndLogoutModal").style.display == 'none'){
	    		
	    		document.getElementById("profileAndLogoutModal").style.display = 'block';
	    		document.getElementById("notificationBox").style.display = 'none';
	    	}else{
	    		document.getElementById("profileAndLogoutModal").style.display = 'none';
	    	}
						
		} 
	    
	    $logoutArea.onclick = function() {
	    	document.getElementById("logoutModal").style.display="block";
	    	document.getElementById("profileAndLogoutModal").style.display="none";
	    }
	    
	    const $logoutModalCloseButton = document.getElementById("logoutModalCloseButton");
	    $logoutModalCloseButton.onclick = function() {
	    	document.getElementById("logoutModal").style.display="none";
	    	document.getElementById("profileAndLogoutModal").style.display="none";
	    }
	    
		document.getElementById("alreadyConfirmNoticeRemoveBtn").onclick = function() {
			
			if(alreadyConfirmCount == 0) {
				alert("조회한 알림이 없습니다.");
			} else {
				for(let i = 0; i < $notificationText.length; i++) {
					$notificationText[i].innerText = "";
					$notification[i].style.background  = "white";
				}
				
				$.ajax({
					url: "/byat/notice/removeconfirmnotice",
					data: { no: "${ sessionScope.loginMember.no }" },
					type: "post",
					success: function(data, status, xhr) {
						
						alert("조회한 알림 삭제 완료!");
						
						noteNum.innerText = "";
						
						$.ajax({
							url:"/byat/notice/list",
							type: 'post',
							data: { no : "${ sessionScope.loginMember.no }" },
							success: function(data, status, xhr) {
								
								const notice = JSON.parse(data.noticeList);
								
								let noticeCount = 0;
								
								if(notice.length == 0) {
									noteNum.innerText = noticeCount;
								}
								
								for(let i in notice) {
									
									if(notice[i].status != "Y" && noticeCount < 100) {
										noticeCount++;
									} else {
										alreadyConfirmCount++;
									}
									
									if(i < 6) {
										$notificationText[i].innerText = notice[i].body;
										
										if(notice[i].status == "Y") {
											$notification[i].style.background  = "#C2C2C2";
										}
										
										$notification[i].onclick = function() {
											
											$.ajax({
												url:"/byat/notice/select",
												type: "post",
												data:{ 
													code : notice[i].code,
													no : notice[i].no	
												}
											});
											
											location.href = '${ pageContext.servletContext.contextPath }' + notice[i].url;
											
										}
									}
									
								}
								noteNum.innerText = noticeCount + "+";
								alreadyConfirmCount = 0;
							},
							error: function(xhr, status, error) {
								console.log(xhr);
							}
						});
					},
					error: function(xhr, status, error) {
						console.log(xhr);
					}
				});
				
			}

		}
	    
	    
    </script>
</body>
</html>