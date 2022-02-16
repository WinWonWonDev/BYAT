<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Insert title here</title>
<style>
	@import url('https://fonts.googleapis.com/css?family=Roboto');

	body{
		font-family: 'Roboto', sans-serif;
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
	    transition-duration:0.6s;
		transition-timing-function: cubic-bezier(0.68, -0.55, 0.265, 1.55);
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
		left: 0px;
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
	}
	
</style>
</head>
<body>
	<nav class="navbar navbar-expand-custom navbar-mainbg">
		<div class="logoIcon">
			<input type="button" class="homeButtonImg" onclick="location.href='${ pageContext.servletContext.contextPath }/main/main.jsp'" >
		</div>
       
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
            	<div class="hori-selector"><div class="left"></div><div class="right"></div></div>
                <li class="nav-item active">
                    <a class="nav-link" href="javascript:void(0);">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="javascript:void(0);">Project</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="javascript:void(0);">My Task</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="javascript:void(0);">Calendar</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="javascript:void(0);">History</a>
                </li>
                <%-- <c:if test="관리자만"> --%>
	                <li class="nav-item">
	                    <a class="nav-link" href="javascript:void(0);">Management</a>
	                </li>                
                <%-- </c:if> --%>
            </ul>
            <input type="button" class="noticeButtonImg" onclick="noticeDisplay();">
			<div class="note-num">3</div>
			<div class="profile-area" onclick="location.href='        '">
			<div class="user-name">
				<h1><!-- 로그인한 유저 이름 --></h1>
			</div>
			<div class="user-image">
		   		<img src=""><!-- 로그인한 유저 사진 -->
			</div>
			</div>
        </div>
    </nav>
    <script>
	    function test(){
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
	    		"height": activeWidthNewAnimHeight - 10.0 + "px",
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
	    			"height": activeWidthNewAnimHeight - 10.0 + "px",
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
	
	
	
	    // --------------add active class-on another-page move----------
	    jQuery(document).ready(function($){
	    	// Get current path and find target link
	    	var path = window.location.pathname.split("/").pop();
	
	    	// Account for home page with empty path
	    	if ( path == '' ) {
	    		path = 'index.html';
	    	}
	
	    	var target = $('#navbarSupportedContent ul li a[href="'+path+'"]');
	    	// Add active class to target link
	    	target.parent().addClass('active');
	    });
    </script>
</body>
</html>