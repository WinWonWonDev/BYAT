<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ include file="../common/menubar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.datatables.net/1.11.4/css/dataTables.jqueryui.min.css"/>  
<script src="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.js"></script>

<style>

	:root {
	  --theadColor: #6c7ae0;
	}

    table.dataTable {
      box-shadow: #bbbbbb 0px 0px 5px 0px;
    }

    thead {
      background-color: var(--theadColor);
    }


	a {
	  text-decoration: none;
	  color: #6A5ACD;	
	}
	
    thead > tr,
    thead > tr > th {
      background-color: transparent;
      color: #fff;
      font-weight: normal;
      text-align: start;
    }
    
    table.dataTable thead th,
    table.dataTable thead td {
      border-bottom: 0px solid #111 !important;
    }

    .dataTables_wrapper > div {
      margin: 5px;
    }

    table.dataTable.display tbody tr.even > .sorting_1,
    table.dataTable.order-column.stripe tbody tr.even> .sorting_1, 
    table.dataTable.display   tbody tr.even,
    table.dataTable.display tbody tr.odd > .sorting_1,
    table.dataTable.order-column.stripe tbody tr.odd > .sorting_1,
    table.dataTable.display tbody tr.odd {
      background-color: #ffffff;
    }

    table.dataTable thead th {
      position: relative;
      background-image: none !important;
    }

    table.dataTable thead th.sorting:after,
    table.dataTable thead th.sorting_asc:after,
    table.dataTable thead th.sorting_desc:after {
      position: absolute;
      top: 12px;
      right: 8px;
      display: block;
      font-family: "Font Awesome\ 5 Free";
    }

    table.dataTable thead th.sorting:after {
      content: "\f0dc";
      color: #ddd;
      font-size: 0.8em;
      padding-top: 0.12em;
    }

    table.dataTable thead th.sorting_asc:after {
      content: "\f0de";
    }

    table.dataTable thead th.sorting_desc:after {
      content: "\f0dd";
    }

    table.dataTable.display tbody tr:hover > .sorting_1,
    table.dataTable.order-column.hover tbody tr:hover > .sorting_1 {
      background-color: #f2f2f2 !important;
      color: #000;
    }

    tbody tr:hover {
      background-color: #f2f2f2 !important;
      color: #000;
    }
	
    .dataTables_wrapper .dataTables_paginate .paginate_button.current, 
    .dataTables_wrapper .dataTables_paginate .paginate_button.current:hover {

      background: none !important;
      border-radius: 50px;
      background-color: var(--theadColor) !important;
      color:#fff !important
	
	}
	
	.paginate_button.current:hover {
      background: none !important;
      border-radius: 50px;
      background-color: var(--theadColor) !important;
      color:#fff !important
	
	}
	
	.dataTables_wrapper .dataTables_paginate .paginate_button.current:hover,
	.dataTables_wrapper .dataTables_paginate .paginate_button:hover {
	
	  border: 1px solid #979797;
	  background: none !important;
	  border-radius: 50px !important;
	  background-color: #000 !important;
	  color: #fff !important;
	
	}

	html {
		width: 100%;
		height: 98%;
		background: #D7E3FA;
		background-size: cover;
		position: relative;
	}
	
	body {
		width: 100%;
		height: 95%;
		overflow-y:hidden;
	}
	
	#whiteBoard {
		position: absolute;
		background: white;
		border: 1px solid black;
		top: 15%;
		left: 2%;
		width: 95%;
		height: 78%;
	}
	
	#managementTitle {
		position: absolute;
		top: 4%;
		left: 3%;
		font-size: 35px;
	}
	
	.historyListHead {
		height: 15%;
	}
	
	.historyListName {
		margin-left: 3%;
		margin-top: 1%;
		font-size: 35px;
		float: left;
		display: inline;
	}
	
	.searchMember {
		float: right;
		margin-right: 3%;
		margin-top: 2%;
	}
	
	.search-area {
		margin-left: auto;
		margin-right: auto;
	}
	
	.managementListBox {
		border: 1px solid black;
		height: 75%;
		margin-left: 3%;
		margin-right: 3%;
	}
	
	.managementTable {
		margin-left: 3%;
		margin-top: 3%;
	}
	
	#memberCodeTh {
		width: 200px;
	}
	
	#nameTh {
		width: 600px;
	}
	
	#rightTh {
		width: 200px;
	}
	
	#memberNumTh {
		width: 250px;
	}
	
	.plusButton {
		background: url("/byat/resources/images/plusButton.png") no-repeat;
		position: absolute;
		top: 8%;
		left: 91%;
	}
	
	.moveDeletedManagementButton {
		background: url("/byat/resources/images/recycleBin.png") no-repeat;
		position: absolute;
		width:40px;
		height:40px;
		top: 91.5%;
		left: 97.5%;
		border:none;
	}
	
	input.img-button {
		width: 32px;
		height: 32px;
		border: none;
		cursor: pointer;
	}
	
	#management-create-modal {
		display: none;
		position: relative;
		width: 100%;
		height: 100%;
		z-index: 1;
	}
	
	#management-update-modal {
		display: none;
		position: relative;
		width: 100%;
		height: 100%;
		z-index: 1;
	}
	
	.modal_content {
		position: absolute;
		top: 6%;
		left: 35%;
		width: 400px;
		height: 440px;
		margin: 20px auto;
		background: #29428C;
		border: 1px solid black;
		border-radius: 25px;
	}
	
	#management-create-modal .modal_layer {
		position: fixed;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		background: rgba(0, 0, 0, 0.5);
		z-index: -1;
	}
	
	#management-update-modal .modal_layer {
		position: fixed;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		background: rgba(0, 0, 0, 0.5);
		z-index: -1;
	}
	
	.modal_head {
		margin-left: 25px;
		margin-top:20px;
		height: 35px;
		color: white;
		text-align: left;
		font-size: 20px;
	}
	
	#management-close-btn {
		right: 30%;
		top: 10%;
	}
	
	#management-update-close-btn {
		right: 17%;
		top: 10%;
	}
	
	#management-create {
		right: 55%;
		top: 10%;
	}
	
	#management-update {
		right: 67%;
		top: 10%;
	}
	
	#management-delete {
		right: 42%;
		top: 10%;
	}
	
	.modal_content-box {
		width: 84%;
		height: 75%;
		font-size: 40px;
		text-align: center;
		background: white;
		border-radius: 25px;
		margin-left: 28px;
		margin-top:10px;
	}
	
	.modal_button {
		width: 100%;
		height: 30%;
		float: right;
		position: relative;
	}
	
	.modal_button button {
		background-color: rgb(25, 25, 112);
		color: white;
		text-align: center;
		cursor: pointer;
		width: 80px;
		height: 30px;
		position: absolute;
	}
	
	form {
		height: 95%;
	}
	
	.managementModalInputNameforCreate {
		position: absolute;
		width: 260px;
		height: 47px;
		top: 23%;
		left: 15%;
	}
	.managementModalInputIdforCreate {
		position: absolute;
		width: 260px;
		height: 47px;
		top: 40%;
		left: 15%;
	}
	.managementModalInputNameforUpdate {
		position: absolute;
		width: 260px;
		height: 47px;
		top: 23%;
		left: 15%;
	}
	.managementModalInputIdforUpdate {
		position: absolute;
		width: 260px;
		height: 47px;
		top: 40%;
		left: 15%;
	}
	.role {
		position: absolute;
		width: 270px;
		height: 47px;
		top: 58%;
		left: 14.5%;
	}
	
	#deleteInfoModal {
		position: relative;
		width: 100%;
		height: 100%;
		z-index: 1050;
	}
	
	#deleteInfoModal h2 {
		margin: 0;
	}
	
	#deleteInfoModal button {
		display: inline-block;
		width: 100px;
		margin-left: calc(100% - 100px - 10px);
	}
	
	#deleteInfoModal .modal_content2 {
		position: absolute;
		top: 5%;
		left: 25%;
		width: 700px;
		height: 310px;
		margin: 100px auto;
		/* padding:20px 10px; */
		background: #fff;
		border: 2px solid #666;
	}
	
	.modal_head2 {
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
	
	#modal_ok_btn {
		background-color: rgb(25, 25, 112);
		color: white;
		text-align: center;
		cursor: pointer;
		width: 80px;
		height: 50px;
		position: absolute;
		right: 55%;
		top: 20%;
	}
	
	.modal_content_message {
		width: 100%;
		height: 50%;
		float: right;
		font-size: 30px;
		text-align: center;
	}
	
	.modal_button2 {
		width: 100%;
		height: 30%;
		float: right;
		position: relative;
	}
</style>
<title>Insert title here</title>
<script>
	const message = '${ requestScope.message }';
	if(message != null && message != '') {
		alert(message);
	}

</script>
</head>
<script type="text/javascript">
$(document).ready(function() {
    
    $.extend( $.fn.dataTable.defaults, {
       
       language: {
          url : "http://cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Korean.json"
       }
    });
    
 
    $('#managementTable').DataTable({
    lengthChange: false,
       ordering: false,
       order: [],
       displayLength : 7,
       stateSave: true,
    });
 });
</script>
<body>
	<div id="whiteBoard">
		<div class="historyListHead">
			<div id="managementTitle">멤버 목록</div>
			<div class="searchMember">
				<div id="managementButtons">
					<input type="button" id="plusButton" class="img-button plusButton">
				</div>
			</div>
		</div>
		<div class="managementListBox">
			<table border="1" class="managementTable" id="managementTable">
				<thead>
					<tr>
						<th id="memberCodeTh">멤버번호</th>
						<th id="nameTh">이름</th>
						<th id="rightTh">권한</th>
						<th id="memberNumTh">사번</th>
					</tr>	
				</thead>
				<tbody>
					<c:forEach var="management" items="${ requestScope.managementList }">
						<tr>  
							<td><div id="memberNo">${ management.memberNo }</div></td>
							<c:if test="${ management.initPwdYN == 'Y' }">
								<td><div id="memberName" style="color:orangered;">${ management.memberName }</div></td>
							</c:if>
							<c:if test="${management.initPwdYN == 'N' }">
								<td><div id="memberName">${ management.memberName }</div></td>
							</c:if>
							<td><div id="permitName">${ management.permitName }</div></td> 
							<td><div id="memberId">${ management.memberId }</div></td>
					   </tr>
				   </c:forEach>
			   </tbody>
			</table>
			<input type="button" id="moveDeletedManagementList" class="moveDeletedManagementButton" onclick="location.href='${ pageContext.servletContext.contextPath }/management/removedList'"/>
		</div>
	</div>
	

	<!-- 멤버 추가(+) 모달창 -->
	<div id="management-create-modal">
		<div class="modal_content">
			<form action="${ pageContext.servletContext.contextPath }/management/regist" method="post" name="createMember">
				<div class="modal_head">
					<h3>멤버계정 생성</h3>
				</div>
				<div class="modal_content-box">
					<input type="text" class="managementModalInputNameforCreate" name="name" id="name" placeholder="멤버 이름"> 
					<br> 
					<input type="text" class="managementModalInputIdforCreate" name="id" id="id" placeholder="ID(사번)"> 
					<br> 
					
					<select name="managementRoleforCreate" class="role">
						<option value="PM">PM</option>
						<option value="일반 멤버" selected="selected">일반 멤버</option>
					</select>
				</div>
				<div class="modal_button">
					<button type="submit" id="management-create" class="management-create">Ok</button>
					<button type="button" id="management-close-btn">Cancel</button>
				</div>
			</form>
		</div>
		<div class="modal_layer"></div>
	</div>

	<div id="management-update-modal" style="display:none;">
		<div class="modal_content">
			<form action="${ pageContext.servletContext.contextPath }/management/modify" method="post">
				<div class="modal_head">
					<h3>멤버계정 조회/수정</h3>
				</div>
				<div class="modal_content-box">
						<input type="text" class="managementModalInputNameforUpdate" name="memberName" id="managementModalInputNameforUpdate" placeholder="멤버 이름"> 
						<br> 
						<input type="text" class="managementModalInputIdforUpdate" name="memberId" id="managementModalInputIdforUpdate" placeholder="ID(사번)"> 
						<br> 
						<input type="hidden" id="memberNoforUpdate" name="memberNo">
						<select name="permitName" class="role" id="managementRoleforUpdate"></select>
				</div>
				<div class="modal_button">
					<button type="submit" id="management-update">Ok</button>
					<button type="button" id="management-update-close-btn">Cancel</button>
					<button type="button" id="management-delete">delete</button>
				</div>
			</form>
		</div> 
		<div class="modal_layer"></div>
		<div id="deleteInfoModal" style="display:none;">
			<div class="modal_content2">
				<div class="modal_head2">System Message</div>
				<div class="modal_content_message">
					<br>정말 삭제하시겠습니까? <br>삭제한 멤버의 정보는 복구할 수 없습니다.
				</div>
				<div class="modal_button2">
					<button type="button" id="modal_ok_btn" class="modal_ok_btn">Ok</button>
					<button type="button" id="modal_close_btn">Cancel</button>
				</div>
			</div>
		</div>
	</div>


	<script>
	
	var permitName = document.querySelectorAll("#permitName");
	var arrayList = new Array();
	var managementforPermit = $("#managementRoleforUpdate");
	
	<c:forEach var="management" items="${ requestScope.managementList }" varStatus="status">
		arrayList.push("${ management.permitName }");
	</c:forEach>
	
	
		document.getElementById("plusButton").onclick = function() {
			document.getElementById("management-create-modal").style.display = "block";
		}

		document.getElementById("management-close-btn").onclick = function() {
			document.getElementById("management-create-modal").style.display = "none";
		}

		document.getElementById("management-update-close-btn").onclick = function() {
			document.getElementById("management-update-modal").style.display = "none";
		}
		
		document.getElementById("modal_close_btn").onclick = function() {
			document.getElementById("deleteInfoModal").style.display = "none";
		}
		
		document.getElementById("management-delete").onclick = function() {
			document.getElementById("deleteInfoModal").style.display = "block";
		}
		
	 	document.getElementById("modal_ok_btn").onclick = function() {
	 		console.log(document.getElementById("memberNoforUpdate").value);
			location.href="${ pageContext.servletContext.contextPath }/management/remove?no=" + document.getElementById("memberNoforUpdate").value;	
		} 
		
			let $memberNo = document.querySelectorAll("#memberNo");
			let $memberName = document.querySelectorAll("#memberName");
			let $permitName = document.querySelectorAll("#permitName");
			let $memberId = document.querySelectorAll("#memberId");
			let memberNoInput = document.getElementById("memberNoforUpdate");
			let memberNameInput = document.getElementById("managementModalInputNameforUpdate");
			let memberIdInput = document.getElementById("managementModalInputIdforUpdate");
			
			
			
		if(document.querySelectorAll("#managementTable tbody tr")) {
			const $tr = document.querySelectorAll("#managementTable tbody tr");
			
			for(let i = 0; i < $tr.length; i++) {
				
				$tr[i].onmouseenter = function() {
					this.parentNode.style.cursor = "pointer";
					
				}
				
				$tr[i].onmouseout = function() {
					this.parentNode.style.backgroundColor = "white";
				}
				
					
				$tr[i].onclick = function() {
					document.getElementById("management-update-modal").style.display = "block";
				
					memberNameInput.value = $memberName[i].innerText;
					memberNoInput.value = $memberNo[i].innerText;
					memberIdInput.value = $memberId[i].innerText;
					
					if(permitName[i].innerText == 'PM') {
							
						const option1 = document.createElement('option');
						option1.setAttribute("selected","selected");
						option1.setAttribute("value","PM");
						option1.innerText = 'PM';
						
						const option2 = document.createElement('option');
						option2.setAttribute("value","일반 멤버");
						option2.innerText = '일반 멤버';
						
						managementforPermit.append(option1);
						managementforPermit.append(option2);
						
					} else {

						const option1 = document.createElement('option');
						option1.setAttribute("value","PM");
						option1.innerText = 'PM';
						
						const option2 = document.createElement('option');
						option2.setAttribute("selected","selected");
						option2.setAttribute("value","일반 멤버");
						option2.innerText = '일반 멤버';
						
						managementforPermit.append(option1);
						managementforPermit.append(option2);
					}
					
				}
				
			}
		}
		
		
	</script>
</body>
</html>