<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ include file="../common/menubar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	const message = '${ requestScope.message }';
	if(message != null && message != '') {
		alert(message);
	}
</script>
<style>

   html  {
      width: 100%;
      height: 98%;
      background:#D7E3FA;
      background-size: cover;
      position:relative;
   }
   
   body {
      width: 100%;
      height: 95%;
      overflow-y:hidden;
   }
   
   #whiteBoard {
      position:absolute;
      background:white;
      border:1px solid black;
      top:20%;
      left:2%;
      width:95%;
      height:78%;
   }
   
   #myProfileTitle {
      position:absolute;
      top:4%;
      left:5%;
   }   

   #grayBoard {
      position:absolute;
      background:#F6F6F6;
      border:1px solid black;
      top:15%;
      left:5%;
      width:90%;
      height:70%;
   }
   
   #PicBoard {
      position:absolute;
      background:white;
      border:1px solid black;
      top:17%;
      left:5%;
      width:15%;
      height:50%;
   
   }
   
   #updateImg {
      position:absolute;
      background:white;
      border:1px solid black;
      top:67.4%;
      left:5%;
      width:50px;
      height:16px;
      font-size:2px;
      text-align:center;
      cursor:pointer;
   }
   
   #updateImgBtn {
      position:absolute;
      background:white;
      border:1px solid black;
      top:67.4%;
      left:17%;
      width:50px;
      height:16px;
      font-size:2px;
      text-align:center;
      cursor:pointer;
   }
   
   .btn {
      width: 100px;
      height: 30px;
      color: white;
   }
   
   .btn-yg {
      position:absolute;
      top:74%;
      left:8.7%;
      background-color: #191970;
   }
   
   .btn-or {
      position:absolute;
      top:85%;
      left:90%;
      background-color: #191970;
   }
   
   .btn-or2 {
      position:absolute;
      top:85%;
      left:80%;
      background-color: #191970;
   }
   
   .memberInfo  {
      position:absolute;
      color:#7F7FAE;
      font-size:1.5em;
   }
   
   .Info1 {
      top:15%;
      left:33%;
   }
   
   .Info2 {
      top:35%;
      left:30%;
   }
   
   .Info3 {
      top:55%;
      left:30%;
   }

   .Info4 {
      top:75%;
      left:30%;
   }
   
   #profile-create-modal {
      display: none;
      position:relative;
      width:100%;
      height:100%;
      z-index:1;
   }
      
   .modal_content {
      position:absolute;
      top:6%;
      left:35%;
      width:400px;
      height:440px;
      margin:20px auto;
      background:#29428C;
      border:1px solid black;
      border-radius:25px;
   }
      
   #profile-create-modal .modal_layer {
      position:fixed;
      top:0;
      left:0;
      width:100%;
      height:100%;
      background:rgba(0, 0, 0, 0.5);
      z-index:-1;
   }
   .modal_head {
      margin-left:25px;
      margin-top:20px;
      height:35px;
      color:white;
      text-align:left;
      font-size:20px;
   }
      
   #profile-close-btn {
      right:30%;
      top:10%;
   }
      
   #profile-create {
      right:55%;
      top:10%;
   }
   .modal_content-box {
      width:84%;
      height:75%;
      font-size:40px;
      text-align:center;
      background: white;
      border-radius: 25px;
      margin-left: 28px;
      margin-top:10px;
   }
   
   .modal_button {
      width:100%;
      height:30%;
      float:right;
      position:relative;
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
   
   form {
      height:95%;
   }
   
   
   .profileModalInput {
      position:absolute;
      width:260px;
      height:47px;
   }
   
   .Pwd {
      top:23%;
      left:15%;
   }
   
   .newPwd {
      top:40%;
      left:15%;
   }
   
   .newPwdAgain {
      top:60%;
      left:15%;
   }
   
   
   #updateModal_context {
      border:1px solid black;
      position:absolute;
      top:20%;
      left:30%;
      width:100px;
      height:100px;
   }
   
   #profile-update-modal .modal_layer2 {
      position:fixed;
      top:35%;
      left:6.8%;
      width:85.6%;
      height:54%;
      background:rgba(0, 0, 0, 0.5);
      z-index:1;
   }
</style> 
</head>
<body>
   <div id="whiteBoard">
      <div id="myProfileTitle"><br><h1>My Profile</h1></div>
         <div id="grayBoard">
            <div id="PicBoard">
	            <img id="profileImg" width="100%" height="100%" src="${ pageContext.servletContext.contextPath }/resources/upload/original/${ requestScope.attachment.savedName }"/><br>
            </div>
            <form action="${ pageContext.servletContext.contextPath }/profile/uploadimage" method="post" enctype="multipart/form-data">
				<div>
					<input type="file" id="imageFile" name="uploadedImg" onchange="loadImg(this)" style="display:none"/>
					<button type="button" id="updateImg" disabled>선택..</button>
					<button type="submit" id="updateImgBtn" style="display: none;">업로드</button>
				</div>
            </form>
            <form action="${ pageContext.servletContext.contextPath }/profile/modify" method="post">
            	<div class="memberInfo Info1" id="textIdTitle">Id : <input id="textId" name="id" type="text" readonly="readonly" value="${ sessionScope.loginMember.id }"></div>
            	<div class="memberInfo Info2" id="textNameTitle">name : <input id="textName" name="name" type="text" readonly="readonly" value="${ sessionScope.loginMember.name }"></div>
            	<div class="memberInfo Info3" id="textEmailTitle">email : <input id="textEmail" name="email" type="text" readonly="readonly" value="${ sessionScope.loginMember.email }"></div>
            	<div class="memberInfo Info4" id="textPhoneTitle">phone : <input id="textPhone" name="phone" type="text" readonly="readonly" value="${ sessionScope.loginMember.phone }"></div>


				<div class="btns" align="center">
					<input type="button" class="btn btn-yg" value="비밀번호변경" id="regist">
					<input type="button" class="btn btn-or" value="수정" id="update">

					<div id="updateModalButtons1" style="display: none;">
						<input type="button" class="btn btn-or" value="cancel"
							id="updateCancelButton">
					</div>
					<!-- 수정 버튼을 누르면 생기도록 하고 싶다!  -->
					<div id="updateModalButtons2" style="display: none;">
						<input type="submit" class="btn btn-or2" value="ok" id="updateOk">
					</div>
				</div>
			</form>
      
      </div>
   </div>
   
      <!-- 비밀번호 변경 모달창 -->
   
   <div id="profile-create-modal">
   
         <div class="modal_content">
            <form action="${ pageContext.servletContext.contextPath }/profile/modifypwd" method="post">
               <div class="modal_head">
                  <h3>비밀번호 변경 </h3>
                </div>
                   <div class="modal_content-box">
                      <input type="password" class="profileModalInput Pwd" name="requestOriginPwd" placeholder="기존 비밀번호">
                      <br>
                      <input type="password" class="profileModalInput newPwd" name="requestNewPwd" placeholder="변경할 비밀번호">
                      <br>
                      <input type="password" class="profileModalInput newPwdAgain" name="requestNewPwdAgain" placeholder="변경할 비밀번호 확인">
                      <br>
                   </div>
                   <div class="modal_button">
                    <button type="submit" id="profile-create">Ok</button>
                    <button type="button" id="profile-close-btn">Cancel</button>
                   </div>
                  </form>
       </div>
       <div class="modal_layer"></div>
   </div>
   
   
   
<script>

	$("#updateImg").click(function(e) {
		e.preventDefault();
		$("#imageFile").click();
	});

	
	
	/* Image 미리보기 함수 (File API 활용) */
	function loadImg(val) {
		
		if(value.files && value.files[0]) {
			const reader = new FileReader();
			
			reader.readAsDataURL(value.files[0]);
			
			reader.onload = function(e) {
				console.log(e.targer.result);
				document.getElementById("profileImg").src = e.targer.result;
			}
		}
	}

   document.getElementById("regist").onclick = function() {
       document.getElementById("profile-create-modal").style.display="block";
   }
   
   document.getElementById("profile-close-btn").onclick = function() {
        document.getElementById("profile-create-modal").style.display="none";
    }
   
   document.getElementById("update").onclick = function() {
	  document.getElementById("updateImg").disabled = false;
	  document.getElementById("updateImgBtn").style.display = "block";
      document.getElementById("textId").readOnly = false;
      document.getElementById("textName").readOnly = false;
      document.getElementById("textEmail").readOnly = false;
      document.getElementById("textPhone").readOnly = false;  
      document.getElementById("textIdTitle").style.color = "#29428C";
      document.getElementById("textNameTitle").style.color = "#29428C";
      document.getElementById("textEmailTitle").style.color = "#29428C";
      document.getElementById("textPhoneTitle").style.color = "#29428C";  
      //ok버튼이 생기고, 수정버튼이 cancel버튼이 된다!.style.display="block";updateModalButtons
      document.getElementById("updateModalButtons2").style.display = "block";
      document.getElementById("updateModalButtons1").style.display = "block";
      document.getElementById("grayBoard").style.background = "rgba(0, 0, 0, 0.5)";
      
      /* 전화번호 hyphen('-') 자동 삽입 */
      $(document).on("keyup", "#textPhone", function() {
    	  $(this).val( $(this).val().replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/,"$1-$2-$3").replace("--", "-") );
      });
   }
   

	document.getElementById("updateCancelButton").onclick = function() {
		document.getElementById("updateImg").disabled = true;
		document.getElementById("updateImgBtn").style.display = "none";
		document.getElementById("textId").readOnly = true;
		document.getElementById("textName").readOnly = true;
		document.getElementById("textEmail").readOnly = true;
		document.getElementById("textPhone").readOnly = true;
		document.getElementById("textIdTitle").style.color = "#7F7FAE";
		document.getElementById("textNameTitle").style.color = "#7F7FAE";
		document.getElementById("textEmailTitle").style.color = "#7F7FAE";
		document.getElementById("textPhoneTitle").style.color = "#7F7FAE";
		document.getElementById("updateModalButtons2").style.display = "none";
		document.getElementById("updateModalButtons1").style.display = "none";
		document.getElementById("grayBoard").style.background = "#F6F6F6";
	}
	
</script>
</body>
</html>