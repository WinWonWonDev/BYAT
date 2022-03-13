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
	
	.deletedManagementListHead {
		height: 15%;
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
	
	.moveManagementList {
		background: url("/byat/resources/images/moveManagementListButton.png") no-repeat;
		position: absolute;
		width:40px;
		height:40px;
		top: 91.5%;
		left: 97%;
		border:none;
		cursor: pointer;
	}
	
	#restoreInfoModal .modal_layer {
		position: fixed;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		background: rgba(0, 0, 0, 0.5);
		z-index: -1;
	}
	
	
	#restoreInfoModal {
		position: relative;
		width: 100%;
		height: 100%;
		z-index: 1050;
	}
	
	#restoreInfoModal h2 {
		margin: 0;
	}
	
	#restoreInfoModal button {
		display: inline-block;
		width: 100px;
		margin-left: calc(100% - 100px - 10px);
	}
	
	#restoreInfoModal .modal_content2 {
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
		<div class="deletedManagementListHead">
			<div id="managementTitle">삭제된 멤버 목록</div>
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
					<c:forEach var="management" items="${ requestScope.deletedManagementList }">
						<input type="hidden" id="memberNoForRestore" name="no" value="${ management.memberNo }">
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
			<input type="button" id="moveManagementList" class="moveManagementList" onclick="location.href='${ pageContext.servletContext.contextPath }/management/list'"/>
		</div>
	</div>
	
		<div id="restoreInfoModal" style="display:none;">
			<div class="modal_content2">
				<div class="modal_head2">System Message</div>
				<div class="modal_content_message">
					<br><br>이 멤버의 정보를 복구하시겠습니까? 
				</div>
				<div class="modal_button2">
					<button type="button" id="modal_ok_btn" class="modal_ok_btn">Ok</button>
					<button type="button" id="modal_close_btn">Cancel</button>
				</div>
			</div>
		<div class="modal_layer"></div>
		</div>

<script>
		var Okbutton = document.getElementById("modal_ok_btn");

		if(document.querySelectorAll("#managementTable tbody tr")) {
			const $tr = document.querySelectorAll("#managementTable tbody tr");
	 		var memberNoForRestore = document.querySelectorAll("#memberNoForRestore");
			
			for(let i = 0; i < $tr.length; i++) {
				$tr[i].onmouseenter = function() {
					this.parentNode.style.cursor = "pointer";
					
				}
				$tr[i].onmouseout = function() {
					this.parentNode.style.backgroundColor = "white";
				}
				$tr[i].ondblclick = function() {
					document.getElementById("restoreInfoModal").style.display = "block";
					document.getElementById("modal_ok_btn").value = memberNoForRestore[i].value;
				}
				
			}
		}
 		
		Okbutton.onclick = function() {
 			location.href="${ pageContext.servletContext.contextPath }/management/restore?no=" + Okbutton.value;	
		} 
	 	document.getElementById("modal_close_btn").onclick = function() {
			document.getElementById("restoreInfoModal").style.display = "none";
	 	}
		
</script>
</body>
</html>