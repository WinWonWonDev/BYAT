<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/menubar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>
<meta charset="UTF-8">
<title>전체 알림</title>
<style>
	body {
		width: 100%;
     	height: 95%;
     	background-color: rgb(240, 242, 245);
	}
	
	.noticeBoard {
		position: absolute;
		width: 500px;
		height: 670px;
		border-radius: 10px;
		background-color: white;
		top:80px;
		left:500px;
		border:0.5px solid #DCDCDC;
	}
	
	.boxTitle {
		font-size: 30px;
		position: relative;
		font-weight: bold;
		margin-top: 10px;
		margin-left: 18px;
		padding-bottom: 30px;
	}
	
	.contentBox {
		width:100%;
		height: 94%;
	}
	
	.noticeBox {
		width: 100%;
		height: 50px;
		position: relative;
		cursor:pointer;
	}
	
	.bodyBox {
		width:82%;
		font-size: 15px;
		font: inherit;
		margin-left:50px;
		padding-top: 10px;
	}
	
	.noticeStatusBox {
		position: absolute;
		border-radius: 50px;
		width:15px;
		height:15px;
		margin-left:10px;
		margin-top:15px;
	}
	
	.noticeSettingMoreBtn {
		background: url("/byat/resources/images/noticeListAllMoreBtn.png") no-repeat;
		width: 30px;
		height: 30px;
		border: none;
		cursor: pointer;
		position: absolute;
		margin-left:460px;
		bottom:13px;
	}
	
	.settingSelectBtn {
		position: absolute;
		width: 180px;
		height: 38px;
		border:2px solid black;
		font-size: 13px;
		text-align: center;
		font-weight: bold;
		margin-left:455px;
		margin-top:20px;
		background-color: white;
		z-index: 10;
	}
	
	.statusChangeBtn {
		position: relative;
		width:100.5%;
		height: 50%;
	}
	
	.deleteNoticeBtn {
		position: relative;
		width:100.5%;
		height: 50%;
	}
	
	#delete_modal {
		display: none;
		position:fixed;
		width:100%;
		height:100%;
		z-index:2000;
	}
	
	#delete_modal .modal_layer {
		position:fixed;
		top:0;
		left:0;
		width:100%;
		height:100%;
		background:rgba(0, 0, 0, 0.5);
		z-index:-1;
	}
	
	#delete_modal button {
		display:inline-block;
		width:100px;
		margin-left:calc(100% - 100px - 10px);
	}
	
	#delete_modal .delete_modal_content {
		width:700px;
		height:300px;
		margin:100px auto;
		/* padding:20px 10px; */
		background:#fff;
		border:2px solid #666;
	}
	
	.delete_modal_head {
		width:100.1%;
		height:35px;
		background-color:rgb(25,25,112);
		color:white;
		text-align:center;
		font-size:20px;
		float:right;
	}
	
	#delete_modal_close_btn {
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
	
	#delete_modal_ok_btn {
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
	
	.delete_modal_content_message {
		width:100%;
		height:50%;
		float:right;
		font-size:30px;
		text-align:center;
	}
	
	.delete_modal_button {
		width:100%;
		height:30%;
		float:right;
		position:relative;
	}
	
</style>
</head>
<body>
	<div id="noticeBoard" class="noticeBoard">
		<div id="boxTitle" class="boxTitle">알림</div>
		<div id="contentBox" class="contentBox">
			<!-- <div class="noticeBox">
				<div class="noticeStatusBox" style="background-color: red"></div>
				<div class="bodyBox">sdafdasfadsfsdafadsfsdasdafdasfadsfsdafadsfsdasdafdasfadsfsdafadsfsda</div>
				<button class="noticeSettingMoreBtn"></button>
				<div id="settingSelectBtn" class="settingSelectBtn">
					<div id="statusChangeBtn" class="statusChangeBtn">이 알림을 읽지 않음으로 표시</div>
					<div id="deleteNoticeBtn" class="deleteNoticeBtn">해당 알림 삭제</div>
				</div>
			</div> -->
		</div>
	</div>
	
	<div id="delete_modal">
		<div class="delete_modal_content">
		    <div class="delete_modal_head">
		    	Alert Message
		    </div>
	       	<div class="delete_modal_content_message">
	  	   		<br>삭제한 알림은 <font style="color:red;">복구</font>하실 수 없습니다. <br>정말 삭제하시겠습니까?
	       	</div>
	       	<div class="delete_modal_button">
		        <button type="button" id="delete_modal_ok_btn">Ok</button>
		        <button type="button" id="delete_modal_close_btn">Cancel</button>
	       	</div>
	    </div>
		<div class="modal_layer"></div>
	</div>
	
	<script>
			
		const contentBox = document.getElementById("contentBox");
	
		<c:forEach items="${ noticeList }" var="notice" varStatus="status">
			noticeBox = document.createElement('div');
			noticeBox.setAttribute('id', 'noticeBox');
			noticeBox.setAttribute('class', 'noticeBox');
			
			noticeStatusBox = document.createElement('div');
			noticeStatusBox.setAttribute('id', 'noticeStatusBox');
			noticeStatusBox.setAttribute('class', 'noticeStatusBox');
			
			if("${ notice.status }" == 'Y') {
				noticeStatusBox.style.backgroundColor = 'red';
			} else {
				noticeStatusBox.style.backgroundColor = 'yellowgreen';
			}
			
			bodyBox = document.createElement('div');
			bodyBox.setAttribute('id', 'bodyBox');
			bodyBox.setAttribute('class', 'bodyBox');
			bodyBox.innerText = "${notice.body}";
			
			
			if("${status.index}" > 11) {
				document.getElementById("noticeBoard").style.height = document.getElementById("noticeBoard").offsetHeight + 50 + "px";
				console.log(document.getElementById("noticeBoard").offsetHeight);
			}
			
			noticeSettingMoreBtn = document.createElement('button');
			noticeSettingMoreBtn.setAttribute('id', 'noticeSettingMoreBtn');
			noticeSettingMoreBtn.setAttribute('class', 'noticeSettingMoreBtn');
			noticeSettingMoreBtn.style.display = 'none';
			
			noticeUrl = document.createElement('input');
			noticeUrl.setAttribute('type', 'hidden');
			noticeUrl.setAttribute('id', 'noticeUrl');
			noticeUrl.setAttribute('class', 'noticeUrl');
			noticeUrl.setAttribute('value', '${notice.url}');
			
			noticeCode = document.createElement('input');
			noticeCode.setAttribute('type', 'hidden');
			noticeCode.setAttribute('id', 'noticeCode');
			noticeCode.setAttribute('value', '${notice.code}');
			
			settingSelectBtn = document.createElement('div');
			settingSelectBtn.setAttribute('id', 'settingSelectBtn');
			settingSelectBtn.setAttribute('class', 'settingSelectBtn');
			settingSelectBtn.style.display = 'none';
			
			statusChangeBtn = document.createElement('div');
			statusChangeBtn.setAttribute('id', 'statusChangeBtn');
			statusChangeBtn.setAttribute('class', 'statusChangeBtn');
			
			if("${notice.status}" == 'Y') {
				statusChangeBtn.innerText = '알림을 읽지 않은 상태로 표시';
			} else {
				statusChangeBtn.innerText = '알림을 읽은 상태로 표시';
			}
			
			deleteNoticeBtn = document.createElement('div');
			deleteNoticeBtn.setAttribute('id', 'deleteNoticeBtn');
			deleteNoticeBtn.setAttribute('class', 'deleteNoticeBtn');
			deleteNoticeBtn.innerText = '해당 알림 삭제';
			
			noticeStatus = document.createElement('input');
			noticeStatus.setAttribute('type', 'hidden');
			noticeStatus.setAttribute('id', 'noticeStatus');
			
			if("${notice.status}" == 'Y') {
				noticeStatus.value = 'Y';
			} else {
				noticeStatus.value = 'N';
			}
			
			settingSelectBtn.appendChild(statusChangeBtn);
			settingSelectBtn.appendChild(deleteNoticeBtn);
			settingSelectBtn.appendChild(noticeStatus);
			
			noticeStatusBox.appendChild(settingSelectBtn);
			
			noticeBox.appendChild(noticeStatusBox);
			noticeBox.appendChild(bodyBox);
			noticeBox.appendChild(noticeSettingMoreBtn);
			noticeBox.appendChild(noticeUrl);
			noticeBox.appendChild(noticeCode);
			
			contentBox.appendChild(noticeBox);
		</c:forEach>
		
		$(document).ready(function() {
			
			const $noticeBoxAll = document.querySelectorAll("#noticeBox");
			const $noticeSettingMoreBtnAll = document.querySelectorAll("#noticeSettingMoreBtn");
			const $noticeCodeAll = document.querySelectorAll("#noticeCode");
			const $noticeUrl = document.querySelectorAll("#noticeUrl");
			const $settingSelectBtn = document.querySelectorAll("#settingSelectBtn");
			const $statusChangeBtn = document.querySelectorAll("#statusChangeBtn");
			const $deleteNoticeBtn = document.querySelectorAll("#deleteNoticeBtn");
			const $noticeStatus = document.querySelectorAll("#noticeStatus");
			const $noticeStatusBox = document.querySelectorAll("#noticeStatusBox");
			
			for(let i = 0; i < $noticeBoxAll.length; i++) {
				
				$noticeBoxAll[i].onmouseover = function() {
					$noticeBoxAll[i].style.background = "#C2C2C2";
					$noticeSettingMoreBtnAll[i].style.display = 'block';
				}
				
				$noticeBoxAll[i].onmouseout = function() {
					$noticeBoxAll[i].style.background = "white";
					$noticeSettingMoreBtnAll[i].style.display = 'none';
				}
				
				$noticeBoxAll[i].onclick = function() {
					
					$.ajax({
						url:"/byat/notice/select",
						type: "post",
						data:{ 
							code : $noticeCodeAll[i].value,
							no : '${ sessionScope.loginMember.no }'
						}
					});

					location.href = '${ pageContext.servletContext.contextPath }' + $noticeUrl[i].value;
						
				}
				
				$noticeSettingMoreBtnAll[i].onclick = (function(e) {

					e.stopPropagation();  // 부모의 이벤트도 같이 실행되는 버블링을 막기 위해 사용
					
					if($settingSelectBtn[i].style.display == 'block') {
						$settingSelectBtn[i].style.display = 'none';
					} else {
						$settingSelectBtn[i].style.display = 'block';
					}
					
					
				});
				
				$statusChangeBtn[i].onmouseover = function() {
					$statusChangeBtn[i].style.background = '#C2C2C2';
				}
				
				$statusChangeBtn[i].onmouseout = function() {
					$statusChangeBtn[i].style.background = 'white';
				}

				$deleteNoticeBtn[i].onmouseover = function() {
					$deleteNoticeBtn[i].style.background = '#C2C2C2';
				}
				
				$deleteNoticeBtn[i].onmouseout = function() {
					$deleteNoticeBtn[i].style.background = 'white';
				}
				
				$('html').click(function(e) {
					if(!$(e.target).hasClass('settingSelectBtn')) {
						$settingSelectBtn[i].style.display = 'none';
					}
				});
				
				
				$statusChangeBtn[i].onclick = (function(e) {
					
					e.stopPropagation();
					
					$.ajax({
						url:"/byat/notice/changestatus",
						type: "post",
						data:{ 
							code : $noticeCodeAll[i].value,
							no : '${ sessionScope.loginMember.no }',
							noticeStatus : $noticeStatus[i].value
						},
						success: function(data, status, xhr) {
							alert("상태 변경 성공!");
						},
						error: function(xhr, status, error) {
							alert("상태 변경 실패!");
							console.log(xhr);
						}
					});
					
					if($noticeStatus[i].value == 'Y') {
						$statusChangeBtn[i].innerText = '알림을 읽은 상태로 표시';
						$noticeStatusBox[i].style.background = 'yellowgreen';
						$noticeStatus[i].value = 'N';
					} else {
						$statusChangeBtn[i].innerText = '알림을 읽지 않은 상태로 표시';
						$noticeStatusBox[i].style.background = 'red';
						$noticeStatus[i].value = 'Y';
					}
					
					$settingSelectBtn[i].style.display = 'none';
					
				});
				
				$deleteNoticeBtn[i].onclick = (function(e) {
					
					e.stopPropagation();
					
					let checkDeleteNotice = 0;
					
					document.getElementById("delete_modal").style.display = 'block';
					$settingSelectBtn[i].style.display = 'none';
					
					document.getElementById("delete_modal_ok_btn").onclick = function() {
						
						
						document.getElementById("delete_modal").style.display = 'none';
						
						$.ajax({
							url: "/byat/notice/removenotice",
							data: { 
								no: "${ sessionScope.loginMember.no }",
								code : $noticeCodeAll[i].value
							},
							type: "post",
							async : false,
							success: function(data, status, xhr) {
								checkDeleteNotice = 1;
							},
							error: function(xhr, status, error) {
								console.log(xhr);
							}
						});
						
						if(checkDeleteNotice == 1) {
							alert("알림 삭제 완료!");
							$noticeBoxAll[i].remove();
							
						}
						
					}

					document.getElementById("delete_modal_close_btn").onclick = function() {
						
						document.getElementById("delete_modal").style.display = 'none';
						
					}

				});
				
			}
			
		 });
	</script>
</body>
</html>