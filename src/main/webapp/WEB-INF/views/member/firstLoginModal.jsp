<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
	#modal {
		display: none;
		position: relative;
		width: 100%;
		height: 100%;
		z-index: 1;
	}
	
	#modal h2 {
		margin: 0;
	}
	
	#modal button {
		display: inline-block;
		width: 100px;
		margin-left: calc(100% - 100px - 10px);
	}
	
	.modalContent {
		width: 700px;
		height: 300px;
		margin: 100px auto;
		/* padding:20px 10px; */
		background: #fff;
		border: 2px solid #666;
	}
	
	#modal .modalLayer {
		position: fixed;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		background: rgba(0, 0, 0, 0.5);
		z-index: -1;
	}
	
	.modalHead {
		width: 100.1%;
		height: 35px;
		background-color: rgb(25, 25, 112);
		color: white;
		text-align: center;
		font-size: 20px;
		float: right;
	}
	
	#modal_close_btn {
		background-color: rgb(25, 25, 112);
		color: white;
		text-align: center;
		cursor: pointer;
		width: 80px;
		height: 50px;
		position: absolute;
		right: 30%;
		top: 20%;
	}
	
	#modalOkBtn {
		background-color: rgb(25, 25, 112);
		color: white;
		text-align: center;
		cursor: pointer;
		width: 80px;
		height: 50px;
		position: absolute;
		right: 44%;
		top: 20%;
	}
	
	.modalContentMessage {
		width: 100%;
		height: 50%;
		float: right;
		padding: 0px;
	}
	
	.modalButton {
		width: 100%;
		height: 30%;
		float: right;
		position: relative;
	}
	
	#contentHead {
		font-size: 40px;
		text-align: center;
		font-weight: bold;
		margin-top: -10px;
	}
	
	#contentBody {
		font-size: 25px;
		text-align: center;
		margin-top: -20px;
	}
	
	#initInfoSettingModal {
		display: none;
		position: relative;
		width: 100%;
		height: 100%;
	}
	
	#initInfoSettingModal button {
		display: inline-block;
		width: 100px;
		margin-left: calc(100% - 100px - 10px);
	}
	
	#initInfoSettingModal .modal_content2 {
		position: absolute;
		top: 10%;
		left: 32%;
		width: 500px;
		height: 500px;
		margin: 20px auto;
		background: #29428C;
		border: 1px solid black;
		border-radius: 25px;
		z-index: 1050;
		width: 500px;
	}
	
	#initInfoSettingModal .modal_layer2 {
		position: fixed;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		background: rgba(0, 0, 0, 0.5);
	}

	#inputEmailVeficationModal .modal_layer2 {
		position: fixed;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		background: rgba(0, 0, 0, 0.5);
		z-index:-1;
	}
	
	.modal_head2 {
		height: 35px;
		color: white;
		text-align: left;
		font-size: 20px;
	}
	
	#modal_ok_btn {
		background-color: rgb(25, 25, 112);
		color: white;
		text-align: center;
		cursor: pointer;
		width: 80px;
		height: 30px;
		position: absolute;
		right: 41%;
		top: 10%;
	}
	
	.modal_content-box2 {
		position: absolute;
		width: 90%;
		height: 80%;
		font-size: 40px;
		text-align: center;
		background: white;
		border-radius: 25px;
		margin-left: 28px;
	}
	
	.modal_button2 {
		width: 100%;
		height: 20%;
		float: right;
		position: relative;
		margin-top: 3%;
		top: 80%;
	}
	
	h3 {
		margin-left: 20px;
	}
	
	.emailAddress {
		position: relative;
		top: 5%;
		width: 400px;
		height: 40px;
		font-size: 20px;
		height: 40px;
	}
	
	#emailDuplicateButton {
		position: relative;
	}
	
	#AuthenticationNumber {
		position: relative;
		left: 2%;
	}
	
	.phoneNumber {
		width: 400px;
		height: 40px;
		font-size: 20px;
		float: none;
		margin-top: 6%;
	}
	
	.newPassword {
		width: 400px;
		height: 40px;
		font-size: 20px;
		float: none;
		margin-top: 8%;
	}
	
	.newPasswordConfirm {
		width: 400px;
		height: 40px;
		font-size: 20px;
		float: none;
		margin-top: 8%;
	}
	
	h5 {
		font-size: 20px;
		float: left;
		margin-left: 30px;
		margin-right: 105px;
		margin-bottom: 0px;
		height: 20px;
	}
	
	
	#inputEmailVeficationModal {
		display: none;
		position: relative;
		width: 100%;
		height: 100%;
		z-index: 1050;
	}


	.modalContentMessageforEmail {
		width: 100%;
		height: 30%;
		float: right;
		padding: 0px;
	}

	.modalButton3 {
		width: 100%;
		height: 30%;
		float: right;
		position: relative;
	}
	
	.inputVerificationNum {
		position: relative;
		left:20%;
		width:430px;
		height:35px;
	}
	


	#timerDiv {
		position:relative;
		left:38%;
		
	}
	
	#modalOkBtn2 {
		background-color: rgb(25, 25, 112);
		color: white;
		text-align: center;
		cursor: pointer;
		width: 80px;
		height: 30px;
		position: absolute;
		right: 55%;
		top: 35%;
		}
	
	#modalCancelBtn2 {
		background-color: rgb(25, 25, 112);
		color: white;
		text-align: center;
		cursor: pointer;
		width: 80px;
		height: 30px;
		position: absolute;
		right: 35%;
		top: 35%;
	}
	
	##reSubmitBtn {
		background-color: rgb(25, 25, 112);
		color: white;
		text-align: center;
		cursor: pointer;
		width: 80px;
		height: 30px;
		position: absolute;
		right:29%;
		top:18.5%;
	}
	
	
</style>
<title>Insert title here</title>
</head>
<body style="overflow: hidden;">
	<div id="modal">

		<div class="modalContent">
			<div class="modalHead">Alert Message</div>
			<div class="modalContentMessage">
				<br>
				<p id="contentHead">환영합니다!</p>
				<br>
				<p id="contentBody">
					처음 이용하기 전 <font color="red">프로필</font>을 설정해주세요!
				</p>
			</div>
			<div class="modalButton">
				<button type="button" id="modalOkBtn">Ok</button>
			</div>

		</div>

		<div class="modalLayer"></div>
	</div>
<input type="hidden" id="inputId" name="inputId" value="${ sessionScope.loginMember.id }">
	<!-- setting쪽 모달 -->
	<div id="initInfoSettingModal">
		<div class="modal_content2">
			<div class="modal_head2">
				<h3>프로필 설정</h3>
			</div>
			<div class="modal_content-box2">
				<input type="email" class="emailAddress" id="emailAddress" placeholder="Email" required> 
				<input type="button" id="emailDuplicatieButton" value="중복확인">
				<input type="button" id="AuthenticationNumber" value="인증번호보내기">
				<input type="text" class="phoneNumber" placeholder="Phone" required>
				<input type="password" class="newPassword" placeholder="newPassword" required> 
				<input type="password" class="newPasswordConfirm" placeholder="newPasswordConfirm" required>
			</div>
			<div class="modal_button2">
				<button type="submit" id="modal_ok_btn">Ok</button>
			</div>
		</div>
		<div class="modal_layer2" id="modal_layer2"></div>
	</div>

<!-- 이메일 인증번호 입력 모달창 -->
	<div id="inputEmailVeficationModal" style="display:none;">
		<div class="modalContent">
			<div class="modalHead">Alert Message</div>
			<div class="modalContentMessageforEmail">
				<br> <br>
				<p id="contentBody">
					등록된 이메일로 인증번호가 전송되었습니다.
				</p>
			</div>
			<input type="text" class="inputVerificationNum" id="inputVerificationNum" name="verificationNum" placeholder="인증번호를 입력해주세요">
			<div id="timerDiv">
				<span style="color:red;">남은 시간: </span><span id="timer"></span>
			</div>
			<button type="button" id="reSubmitBtn">재전송</button>
			<div class="modalButton">
				<button type="button" id="modalOkBtn2">Ok</button>
				<button type="button" id="modalCancelBtn2">Cancel</button>
			</div>
		</div>
		<div class="modal_layer2" id="modal_layer2"></div>
	</div>

<input type="hidden" id="oneOrEmail" name="oneOrEmail">
<input type="hidden" id="isDidDuplicate" name="isDidDuplicate">

<script>
	
	$("#emailDuplicatieButton").click(function () {
		if($("#emailAddress").val().includes('@', 1)) {
			$.ajax({
	    		url : "${ pageContext.servletContext.contextPath }/member/emailduplicationcheckforinit",
	    		type : "GET",
	    		contentType : "json",
	    		data : {"emailAddress":$("#emailAddress").val()},
	    		success : function(data, status, xhr) {
					
	    			if(data == 1) {
	    				alert("이미 존재하는 이메일입니다. 다른 이메일을 써주시기 바랍니다!");
				        document.getElementById("modal").style.display="none";
						document.getElementById("initInfoSettingModal").style.display="block";
						document.getElementById("oneOrEmail").value = data; 
	    			} else {
	    				alert("사용 가능한 이메일입니다.");
				        document.getElementById("modal").style.display="none";
						document.getElementById("initInfoSettingModal").style.display="block";
						document.getElementById("oneOrEmail").value = data;  
						document.getElementById("isDidDuplicate").value = 2;
	    				
	    			}
	    		},
	    		error : function(error) {
	    			alert("에러가 발생했습니다. 다시 시도해주세요!");	
	    		}
	    	});
    	
		} else {
			alert("이메일 형식에 맞게 적어주세요!");
	        document.getElementById("modal").style.display="none";
			document.getElementById("initInfoSettingModal").style.display="block";
		}
    });


    $("#AuthenticationNumber").click(function () {
    	if($("#isDidDuplicate").val() == 2) { 
    		if($("#oneOrEmail").val() != 1) {
   				if($("#emailAddress").val().includes('@', 1)) {
				   $.ajax({
			 			url : "${ pageContext.servletContext.contextPath }/member/registverification",
						type : "GET",
						contentType : "json",
						data : {"emailAddress":$("#emailAddress").val(),
							"inputId":$("#inputId").val()},
						success : function(data, status, xhr) {
				
							if(data > 0) {
								alert("인증번호가 발송되었습니다! 인증번호를 입력해주세요!");
								document.getElementById("inputEmailVeficationModal").style.display="block";
								document.getElementById("initInfoSettingModal").style.display="none";
									
								var AuthTimer = new $ComTimer()
								  AuthTimer.comSecond = 180;
								  AuthTimer.fnCallback = function(){alert("다시인증을 시도해주세요.")};
								  AuthTimer.timer =  setInterval(function(){AuthTimer.fnTimer()},1000);
								  AuthTimer.domId = document.getElementById("timer");
									
							} else {
								alert("존재하지 않는 이메일입니다. 다시 입력해주세요.")
								document.getElementById("initInfoSettingModal").style.display="block";
								document.getElementById("inputEmailVeficationModal").style.display="none";
							}
						},
							
						error : function(error) {
							alert("에러가 발생했습니다. 다시 시도해주세요.");
						}
					});
					   
			   	} else {
					alert("이메일 형식에 맞게 적어주세요!");
			        document.getElementById("modal").style.display="none";
					document.getElementById("initInfoSettingModal").style.display="block";
		   		}
		    			
		   	} else {
   				alert("이미 존재하는 이메일 입니다! 다른 이메일을 써주세요!");
		   	}
   		} else {
   			alert("중복체크 먼저 해주세요!");
   		}
    });
	    
	function $ComTimer(){
	    //prototype extend
	}
	
	$ComTimer.prototype = {
	      comSecond : ""
	    , fnCallback : function(){}
	    , timer : ""
	    , domId : ""
	    , fnTimer : function(){
	        var m = Math.floor(this.comSecond / 60) + "분 " + (this.comSecond % 60) + "초"
	        this.comSecond--;					
	        this.domId.innerText = m;
	        
	        if (this.comSecond < 0) {			
	            clearInterval(this.timer);		
	            alert("인증시간이 초과하였습니다. 다시 인증해주시기 바랍니다.");
	            
	        }
	    }
	    ,fnStop : function(){
	        clearInterval(this.timer);
	    }
	}
    
    
 	  $("#reSubmitBtn").click(function() {
			$.ajax({
				url : "${ pageContext.servletContext.contextPath }/member/resubmitverificationnum",
				type : "POST",
				data : {"inputId":$("#inputId").val(),
						"emailAddress":$("#emailAddress").val()}, 
				success : function(data, status, xhr) {
					
					if(data > 0) {
						alert("인증번호가 재발송 되었습니다.")
						document.getElementById("initInfoSettingModal").style.display="none";
						document.getElementById("inputEmailVeficationModal").style.display="block";
						
				    	var AuthTimer2 = new $ComTimer()
			   	 		AuthTimer2.comSecond = 180;
			    		AuthTimer2.fnCallback = function(){alert("다시 인증을 시도해주세요.")};
			    		AuthTimer2.timer = clearInterval(AuthTimer.timer);
			    		AuthTimer2.timer = setInterval(function(){AuthTimer2.fnTimer()}, 1000);
			    		AuthTimer2.domId = document.getElementById("timer");
					
			    		
					} else {
						alert("인증번호 재발송에 실패하였습니다.");
						document.getElementById("initInfoSettingModal").style.display="none";
						document.getElementById("inputEmailVeficationModal").style.display="block";
					}
				},
				error : function (error){
			        alert("에러가 발생했습니다. 다시 접속해주세요."); 
				}
			});
		}); 
	
	
    $("#modalOkBtn2").click(function () {
    	$.ajax({
    		url : "checkverification",
    		type : "POST",
    		contentType : "json",
    		data : {"inputVerificationNum":$("#inputVerificationNum").val()},
    		success : function(data, status, xhr) {
    			
    			if(data > 0) {
    				alert("인증이 완료되었습니다.");
					document.getElementById("initInfoSettingModal").style.display="block";
					document.getElementById("inputEmailVeficationModal").style.display="none";
				
    			} else {
    				alert("인증이 실패하였습니다! 다시 입력해주세요!");
					document.getElementById("initInfoSettingModal").style.display="block";
					document.getElementById("inputEmailVeficationModal").style.display="none";
    			}
    		},
    		error : function(error) {
    			alert("에러가 발생했습니다. 다시 시도해주세요.");
    		}
    		
    	});
    	
    });
    
    
    
    
    
    
    
    
    
    
    
     
	document.getElementById("modal").style.display="block";
	document.getElementById("initInfoSettingModal").style.display="none";
 	
   
    document.getElementById("modalOkBtn").onclick = function() {
        document.getElementById("modal").style.display="none";
		document.getElementById("initInfoSettingModal").style.display="block";
    }
    
    document.getElementById("modal_ok_btn").onclick = function() {
    	document.getElementById("")
    	
    }
    
    
    
</script>


</body>
</html>