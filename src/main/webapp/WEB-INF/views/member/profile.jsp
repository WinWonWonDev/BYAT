 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ include file="../common/menubar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
   
   #updatePic {
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
            </div>
               <div>
               <button type="button" id="updatePic">업로드..</button> 
               </div>
            
            <div class="memberInfo Info1" id="textIdTitle">Id : <input id="textId" type="text" readonly="readonly"></div>
            <div class="memberInfo Info2" id="textNameTitle">name : <input id="textName" type="text" readonly="readonly"></div>
            <div class="memberInfo Info3" id="textEmailTitle">email : <input id="textEmail" type="text" readonly="readonly"></div>
            <div class="memberInfo Info4" id="textPhoneTitle">phone : <input id="textPhone" type="text" readonly="readonly"> </div>
            
   
            <div class="btns" align="center">
               <input type="button" class="btn btn-yg" value="비밀번호변경" id="regist">
               <input type="button" class="btn btn-or" value="수정" id="update">
               
               <div id="updateModalButtons1" style="display:none;">
               <input type="button" class="btn btn-or" value="cancel" id="updateCancelButton">
               </div>
               <!-- 수정 버튼을 누르면 생기도록 하고 싶다!  -->
               <div id="updateModalButtons2" style="display:none;">
               <input type="button" class="btn btn-or2" value="ok" id="updateOk">
               </div>
            </div>
      
      </div>
   </div>
   
      <!-- 비밀번호 변경 모달창 -->
   
   <div id="profile-create-modal">
   
         <div class="modal_content">
            <form action="" method="post">
               <div class="modal_head">
                  <h3>비밀번호 변경 </h3>
                </div>
                   <div class="modal_content-box">
                      <input type="text" class="profileModalInput Pwd" name="profileOriginalPwd" placeholder="기존 비밀번호">
                      <br>
                      <input type="text" class="profileModalInput newPwd" name="profileNewPwd" placeholder="변경할 비밀번호">
                      <br>
                      <input type="text" class="profileModalInput newPwdAgain" name="profileNewPwd" placeholder="변경할 비밀번호 확인">
                      <br>
                   </div>
                   <div class="modal_button">
                    <button type="button" id="profile-create">Ok</button>
                    <button type="button" id="profile-close-btn">Cancel</button>
                   </div>
                  </form>
       </div>
       <div class="modal_layer"></div>
   </div>
   
   
   
<script>



   document.getElementById("regist").onclick = function() {
       document.getElementById("profile-create-modal").style.display="block";
   }
   
   document.getElementById("profile-close-btn").onclick = function() {
        document.getElementById("profile-create-modal").style.display="none";
    }
   
   document.getElementById("update").onclick = function() {
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
   }
   
   document.getElementById("updateCancelButton").onclick = function() {
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