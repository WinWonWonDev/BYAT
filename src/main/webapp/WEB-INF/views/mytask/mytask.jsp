<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/common/menubar.jsp"%>
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<title>Insert title here</title>
<script>
	const message = '${ requestScope.message }';
	if (message != null && message != '') {
		alert(message);
	}
</script>
	<style>
	html {
		width: 100%;
		height: 98%;
		background: #D7E3FA;
		background-size: cover;
		position: relative;
		overflow-Y: hidden;
	}
	
	body {
		width: 100%;
		height: 95%;
	}
	
	p {
		margin: 5px;
		text-indent: 1ch;
	}
	
	tr td {
		text-align: center;
	}
	
	.modal {
		display: none;
		position: fixed;
		z-index: 1; /* Sit on top */
		left: 0;
		top: 0;
		width: 100%;
		height: 100%;
		background-color: rgb(0, 0, 0);
		background-color: rgba(0, 0, 0, 0.4);
	}
	
	.modal-content {
		background-color: #3b60d0;
		border-radius: 30px;
		margin: 8% auto;
		padding: 10px;
		border: 1px solid #888;
		width: 40%;
	}
	
	.modal-content-white {
		background-color: white;
		border-radius: 30px;
		margin: 5% auto;
		padding: 20px;
		border: 1px solid #888;
		width: 80%;
		height: 300px;
	}
	
	.modal-content-gray {
		background-color: rgb(242, 242, 242);
		border-radius: 30px;
		padding: 15px;
		border: 2px solid #000000;
		width: 90%;
		height: 180px;
		text-align: center;
		overflow:hidden;
	}
	.modalTbodyDiv {
		max-heghit:150px;
		overflow-Y: auto;
		width:101%;
   		height: 150px;
    	padding-bottom:100px;
	}
	.modalTalbe{
		width: 100%;
		bordoer-collapse: collapse;
		
	}
	
	.ok-button {
		width: 30%;
		cursor: pointer;
		text-align: center;
		color: white;
		margin: 0 auto;
		border-radius: 10px;
		background-color: #172653;
		padding-bottom: 10px;
		padding-top: 10px;
	}
	 
	
	#myTaskWhiteBoard {
		background: white;
		position: absolute;
		top: 15%;
		left: 2%;
		width: 95%;
		height: 78%;
		border: 1px solid black;
	}
	
	#myTaskProjectListBox {
		position: absolute;
		top: 2%;
		left: 2%;
		width: 31%;
		height: 45%;
	}
	
	#myTaskProjectListInnerBox {
		position: absolute;
		overflow-Y: auto;
		width: 98%;
		height: 98%;
		border: 1px solid black;
		text-align: center;
	}
	
	.myTaskProjectTable {
		margin-left: 10px;
		margin-top: 5px;
		margin-bottom: 5px;
		width: 94%;
		height: auto;
		border-collapse: collapse;
	}
	
	#myTaskProjectChartBox {
		position: absolute;
		top: 3%;
		left: 34%;
		bottom: 50%;
		width: 31%;
		height: 44.5%;
	}
	
	.myTaskProjectChartInnerBox {
		position: absolute;
		width: 98%;
		height: 98%;
		border: 1px solid black;
	}
	
	#piechart {
		position: absolute;
		width: 100%;
		height: 100%;
	}
	
	#myTaskListBox {
		position: absolute;
		top: 2%;
		left: 66%;
		right: 2%;
		bottom: 2%;
		width: 34%;
		height: 96%;
	}
	
	#myTaskListInnerBox {
		position: absolute;
		width: 96%;
		height: 94%;
		border: 1px solid black;
	
	}
	
	.myTaskListTitle {
		border: 1px solid black;
		border-collapse:collapse;
		width: 100%;
		color: white;
		background-color: rgb(41, 60, 117);
	}
	
	.myTaskList {
		width: 98%;
		height: 90%;
		text-align: center;
		overflow-Y: auto;
		padding: 10px;
		margin-top:10px;
		display: block;
	}
	
	.myTaskTbody {
		margin-top: 200px;
		padding-top :100px;
		height:80px;
	}
	#taskListTr {
		height:50px;
		
	}
	
	#member1-circle {
		display: inline-block;
		width: 30px;
		height: 30px;
		border-radius: 50%;
		background: red;
		color: white;
		font-size: 10px;
		font-weight: bold;
		font-color: white;
		line-height: 30px;
		text-align: center;
	}
	
	#member2-circle {
		display: inline-block;
		width: 30px;
		height: 30px;
		border-radius: 50%;
		background: yellowgreen;
		color: white;
		font-size: 10px;
		font-weight: bold;
		font-color: white;
		line-height: 30px;
		text-align: center;
	}
	
	#member3-circle {
		display: inline-block;
		width: 30px;
		height: 30px;
		border-radius: 50%;
		background-color: rgb(255, 223, 186);
		color: white;
		font-size: 10px;
		font-weight: bold;
		font-color: white;
		line-height: 30px;
		text-align: center;
	}
	
	#myTaskProjectMemberBox {
		width: 100px;
	}
	
	.myTaskDoToListInnerBox {
		position: absolute;
		width: 98%;
		height: 98%;
		border: 1px solid black;
		overflow-Y:auto;
	}
	
	#myTaskDoToListBox {
		position: absolute;
		top: 54%;
		bottom: 2%;
		left: 2%;
		right: 10%;
		width: 64%;
		height: 39%;
	}
	
	.myTaskDoToList-left {
		position: absolute;
		width: 45%;
		height: auto;
		padding: 20px;
	}
	
	.myTaskDoToList-right {
		position: absolute;
		left: 50%;
		width: 45%;
		height: auto;
		padding: 20px;
	}
	#todolistTr {
		height: 30px;
		width: 100px;
	}
	
	#ToDoListCheckBox {
		margin-right: 10px;
	}
	
	#ToDoListText {
		width: 80%;
	}
	
	.projectstate1 {
		width: 60px;
		height: 40px;
		border-radius: 10%;
		background-color: rgb(196, 196, 196);
		color: white;
		font-size: 15px;
		font-weight: bold;
		font-color: white;
		line-height: 40px;
		text-align: center;
	}
	
	.projectstate2 {
		width: 60px;
		height: 40px;
		border-radius: 10%;
		background: rgb(78, 115, 223);
		color: white;
		font-size: 15px;
		font-weight: bold;
		font-color: white;
		line-height: 40px;
		text-align: center;
	}
	
	.projectstate3 {
		width: 60px;
		height: 40px;
		border-radius: 10%;
		background-color: rgb(41, 60, 117);
		color: white;
		font-size: 15px;
		font-weight: bold;
		font-color: white;
		line-height: 40px;
		text-align: center;
	}
	
	.progressNotDoneArea{
		width: 60px;
		height: 40px;
		border-radius: 10%;
		color: white;
		font-size: 15px;
		font-weight: bold;
		font-color: white;
		line-height: 40px;
		text-align: center;
		margin-left:20px;
		background: rgb(196, 196, 196);
	
	}
	
	.progressingArea{
		width: 60px;
		height: 40px;
		border-radius: 10%;
		color: white;
		font-size: 15px;
		font-weight: bold;
		font-color: white;
		line-height: 40px;
		text-align: center;
		margin-left:20px;
		background: rgb(78, 115, 223);
	}
	
	.progressDoneArea{
		width: 60px;
		height: 40px;
		border-radius: 10%;
		color: white;
		font-size: 15px;
		font-weight: bold;
		font-color: white;
		line-height: 40px;
		text-align: center;
		margin-left:20px;
		background: rgb(41, 60, 117);
	}
	.progressErrorArea{
		width: 60px;
		height: 40px;
		border-radius: 10%;
		color: white;
		font-size: 15px;
		font-weight: bold;
		font-color: white;
		line-height: 40px;
		text-align: center;
		margin-left:20px;
		background: rgb(255,0,0);
	}
	
	#pm-circle {
		width: 40px;
		height: 40px;
		border-radius: 50%;
		background: red;
		color: white;
		font-size: 15px;
		font-weight: bold;
		font-color: white;
		line-height: 40px;
		text-align: center;
	}
	
	.img-button-plus {
		width: 20px;
		height: 20px;
	}
	
	.img-button-minus {
		width: 20px;
		height: 20px;
	}

	.strike {
		text-decoration: line-through;
	}
	
	.border {
		border: 1px solid black;
		height: 90px;
	}

	#projectList:hover {
		background-color: skyblue;
		cursor:pointer;
	}
	
	.projectBackbtn{
		background:url("/byat/resources/images/back-button-arrow-sign.png") no-repeat;
        width:40px;
        height:36px;
        cursor:pointer;
        float:center;
	}
	
	.membersModalButton{
		border: 1px solid black;
	 	width:32px;
        height:32px;
		cursor:pointer;
	}
	
	.titleText{
		 float:left;
	}
	
	 .modalTbodyDiv::-webkit-bar {
	    width: 10px;
	}
	  
	  .modalTbodyDiv::-webkit-bar-thumb {
	    background-color: #2f3542;
	    border-radius: 10px;
	}
	
	  .modalTbodyDiv::-webkit-bar-track {
	    background-color: grey;
	    border-radius: 10px;
	    box-shadow: inset 0px 0px 5px white;
	}
	#startDate{
		width: auto;
		padding-right:30px
	}

</style>
<title>Insert title here</title>
</head>
<body>
	<div id="myTaskWhiteBoard">
		<div id="myTaskProjectListBox">
			<input type="button" class="projectBackbtn" id="projectBackbtn"/>
			<p class="titleText">프로젝트 목록</p>
			<div id="myTaskProjectListInnerBox">
				<table class="myTaskProjectTable" >
					<c:if test="${not empty projectList}">
						<c:forEach items="${ projectList }" var="project" >
						<tbody id="projectList" class="border" >
							<tr>
								<th colspan="5" id="myTaskProjectName" >
									 <c:out value="${ project.title }"/> 
								</th>
							</tr>
							<tr>
								<td id="myTaskProjectMemberBox">
                           			<c:forEach items="${ project.projectMembers }" var="projectmember" varStatus="status">
                                      	<c:if test="${status.index < 3}">
                                      		<c:choose>  
		                                         <c:when test="${projectmember.roleName eq 'PM'}">   
		                                            <div id="member1-circle"> 
		                                                   <c:out value="${ projectmember.name }" />
		                                            </div>
		                                         </c:when>
		                                         <c:when test="${projectmember.roleName eq '부PM'}">   
		                                            <div id="member2-circle"> 
		                                                   <c:out value="${ projectmember.name }" />
		                                            </div>
		                                         </c:when>
		                                         <c:otherwise>
		                                            <div id="member3-circle"> 
		                                                   <c:out value="${ projectmember.name }" />
		                                            </div>
		                                         </c:otherwise>
	                                         </c:choose>
	                                     </c:if>
                                    </c:forEach>
                       			</td>
                        		<td>
                        			<input type="hidden" id=projectCode value="${project.code}"/>
                        			<input id="membersModalButton" class="membersModalButton"type="button" value="..."/>
                       			 </td>
                      			 <td id="myTaskPrjectDeadline">
                          			 <c:out value="${ project.startDate }"/> <br> ~ <c:out value="${ project.endDate }"/>
                        		 </td>
                       			 <td id="myTaskProjectStateBox">
                         			  	<c:choose>
                         			  		<c:when test="${ project.progress eq '미진행' }">
                         			  			<div class="projectstate1"><c:out value="${ project.progress }"/></div>
                         			  		</c:when>
                         			  		<c:when test="${ project.progress eq '진행중' }">
                         			  			<div class="projectstate2"><c:out value="${ project.progress }"/></div>
                         			  		</c:when>
                         			  		<c:when test="${ project.progress eq '완료' }">
                         			  			<div class="projectstate3"><c:out value="${ project.progress }"/></div>
                         			  		</c:when>
                         			  	</c:choose>
                       			 </td>
                        		 <td class="myTaskProjectPMBox">
                          			 <div id="pm-circle"><c:out value="${ project.writer }"/></div>
                        		</td>
                     		</tr>
						</tbody>	
					</c:forEach>
					</c:if>
					<c:if test="${ empty projectList}">
					<h3 style="margin-top:35%;">해당 프로젝트가 없습니다.</h3>
					</c:if>
				</table>
			</div>
		</div>

		<div id="myTaskProjectChartBox">
			<p>나의 프로젝트
			<p>
			<div class="myTaskProjectChartInnerBox">
				<div id="piechart"></div>
			</div>
		</div>

		<div id="myTaskListBox">
			<p>나의 태스크</p>
				<div id="myTaskListInnerBox">
					<table id="myTaskListTitle" class="myTaskListTitle">
                			<colgroup>
                				<col width="35%">
                				<col width="20%">
                				<col width="20%">                			
                				<col width="25%">               			
                			</colgroup>
	                		<thead>
	                			<tr >
	  	  							<th>태스크 제목</th>
	  	  							<th>시작일</th>
	  	  							<th>종료일</th>
	  	  							<th>상태</th>
	  	  						</tr>
	  	  					</thead>
                	</table>
					<table class="myTaskList" id="myTaskList">
						
						<c:forEach items="${taskList}" var="taskList" varStatus="status">
							<tr id="taskListTr">
								<td><c:out value="${taskList.title}"/> </td>
								
								<c:choose>
                   			  		<c:when test="${empty taskList.startDate}">
                   			  			<td id="startDate"><c:out value="미정"/><td>
                   			  		</c:when>
									<c:otherwise>
										<td id="startDate"><c:out value="${taskList.startDate}"/><td>
									</c:otherwise>
								</c:choose>
								
								<c:choose>
                   			  		<c:when test="${ empty taskList.endDate }">
                   			  			<td><c:out value="미정 "/><td>
                   			  		</c:when>
									<c:otherwise>
										<td><c:out value="${ taskList.endDate }"/><td>
									</c:otherwise>
								</c:choose>
								
								<td>
									<c:choose>
	                   			  		<c:when test="${ taskList.progress eq '진행전' }">
	                   			  			<div class="progressNotDoneArea"><c:out value="${ taskList.progress }"/></div>
	                   			  		</c:when>
	                   			  		<c:when test="${ taskList.progress eq '진행중' }">
	                   			  			<div class="progressingArea"><c:out value="${ taskList.progress }"/></div>
	                   			  		</c:when>
	                   			  		<c:when test="${ taskList.progress eq '완료' }">
	                   			  			<div class="progressDoneArea"><c:out value="${ taskList.progress }"/></div>
	                   			  		</c:when>
	                   			  		<c:otherwise>
	                   			  			<div class="progressErrorArea"><c:out value="${ taskList.progress }"/></div>
	                   			  		</c:otherwise>
	                       			 </c:choose>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
		</div>

		<div id="myTaskDoToListBox">
			<input type="hidden" id="loginMemberNo" value="${sessionScope.loginMember}"/>
			<p>
				ToDoList 
				<input type="button" id="img-button-plus" class="img-button-plus" value="+" >
				<input type="button" class="img-button-minus" value="-" />
			</p>
			<div class="myTaskDoToListInnerBox">
				 <table class="myTaskDoToList-left">
						<c:forEach items="${ todoList }" var="todolist" varStatus="status">
				 			<c:choose>	
				 				<c:when test="${status.index % 2 == 0}">	
									<tr id="todolistTr" class="todolistTrStyle">
										<td>
											<input type ="hidden" id="toDoListNo" value="${todolist.no}" maxlength='5'/>
											<input type="checkbox" id="ToDoListCheckBox" name ="ToDoListCheckBox" ${todolist.checkStatus  eq "Y" ? "checked" : ""}/>
											<input type="text" id="ToDoListText" name="ToDoListText" value="${todolist.title}" ${todolist.checkStatus  eq "Y" ? "style=text-decoration:line-through;" : ""} />
										</td>
									</tr>
								</c:when>
							</c:choose>
						</c:forEach>
					</table>
				<table class="myTaskDoToList-right">
					<c:forEach items="${ todoList }" var="todolist" varStatus="status">
						<c:choose>	
							<c:when test="${status.index % 2 == 1}">	
								<tr id="todolistTr" class="todolistTrStyle">
									<td>
										<input type ="hidden" id="toDoListNo" value="${ todolist.no }" maxlength='5'/>
										<input type="checkbox" id="ToDoListCheckBox" name ="ToDoListCheckBox" ${todolist.checkStatus  eq "Y" ? "checked" : ""}/>
										<input type="text" id="ToDoListText" name="ToDoListText" value="${todolist.title}" ${todolist.checkStatus  eq "Y" ? "style=text-decoration:line-through;" : ""} />	
									</td>
								</tr>
							</c:when>
						</c:choose>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	
	<div id="myModal" class="modal">
      <div class="modal-content">
      		<h2 style="color:white; text-align: center; margin-top:10px;"> 프로젝트 구성원</h2>
      		<div class="modal-content-white">
                <h2 style="color:gray; margin-bottom:10px">팀원 목록</h2>
                	<div class ="modal-content-gray">
                		<table id="modalTable" class="modalTalbe">
                			<colgroup>
                				<col width="32%">
                				<col width="32%">
                				<col width="30%">                			
                			</colgroup>
	                		<thead>
	                			<tr>
	  	  							<th>사번</th>
	  	  							<th>구성원 이름</th>
	  	  							<th>역할</th>
	  	  						</tr>
	  	  					</thead>
                		</table>
                		<div class = modalTbodyDiv>
		                	<table id="modalTable" class="modalTalbe">
		                		<colgroup>
		                			<col width="32%">
		                			<col width="32%">
		                			<col width="30%">                			
		                		</colgroup>
		  	  					<tbody id= modalTbody></tbody>
		                	</table>
	                	</div>
           		</div>
           	</div>
            <div class="ok-button" onClick="close_pop();">
                	<span class="pop_bt" style="font-size: 13pt;">ok</span>
            </div>
      </div>
    </div>
        <!--End Modal-->
 
 
 
    <script type="text/javascript">
    //모달창 닫기 생성 버튼
        $(document).ready(function() {
        	 $('#myModal').hide();
        });
        //팝업 Close 기능
        function close_pop(flag) {
             $('#myModal').hide();
        };
      
    </script>
    
	<script>
		let count = 0;
		const $ToDoListCheckBox = document.querySelectorAll("#ToDoListCheckBox");
		let $ToDoListText = document.querySelectorAll("#ToDoListText");
		const $todolist = document.querySelectorAll("#todolist");
		const $toDoListNo = document.querySelectorAll("#toDoListNo");
    	const $modalTable = document.querySelectorAll("#modalTable");
		const $membersModalButton = document.querySelectorAll("#membersModalButton");
		const $projectCode = document.querySelectorAll("#projectCode");
		const $projectList = document.querySelectorAll("#projectList");
		document.getElementById("projectBackbtn").onclick = function(e) {
			window.location.href="${ pageContext.servletContext.contextPath }/mytask/list";
	    }
		
		document.getElementById("img-button-plus").onclick = function(e) {

			window.location.href ="${pageContext.servletContext.contextPath}/mytask/regist"
		};
		google.charts.load('current', {'packages':['corechart']});
    	google.charts.setOnLoadCallback(drawChart);
    	//그래프 그리기
    	function drawChart() {
    	
    		if(${projectProgress[0]} != ""){
	    	var data = google.visualization.arrayToDataTable([
                ['Task', 'Hours per Day'],
                ['진행 중',${projectProgress[0]} ] ,
                ['완료', ${projectProgress[1]} ] ,
                ['미진행', ${projectProgress[2]}]
             ]); 

            var options = {   title:'나의 프로젝트 상태'
            				, alignment:'center'
            				, width:'80%'
            				, height:'70%' 
            				, legend: {'position':'right','alignment':'center'}
            	  			, slices: [{color: 'rgb(78,115,223)'}, {color :'rgb(41, 60, 117)'}, {color: 'rgb(196, 196, 196)'}]};
            var chart = new google.visualization.PieChart(document.getElementById('piechart'));
            
            chart.draw(data, options);
           
    		}else{
    			$piechart = document.getElementById("piechart");
    			$piechart.innerHTML="<h3 style='text-align:center; margin-top:30%'>해당 프로젝트가 없습니다.</h3>";
    		}
        }
    	
    	//project Task  목록 조회
    	 $(document).ready(function(){
			for(let i = 0; i < $projectList.length; i++){
				 $projectList[i].onclick = function() {
					 $.ajax({
			  				url: "/byat/mytask/selecttasklistforproject",
			  				type: 'get',
			  				data:  { projectCode : projectCode[i].value},
			  				success:function(data, status, xhr){
			  					const $table = $("#myTaskList");
			  					$table.html("");
			  					$table.html("<colgroup><col width='40%'> <col width='25%'><col width='25%'><col width='20%'></colgroup>");
			  					
			  					for(let i = 0; i < $projectList.length; i++){
			  						$projectList[i].style.background=""
			  					}
			  					$projectList[i].style.background="SkyBlue";
			  					
			  					if(data!=0){
				  					for(let index in data) {
										const $tr = $("<tr id='taskListTr'>");
										const $titleTd = $("<td>").text(data[index].title);
										let $progressTd =  $("<td>");
										let $div = "";
										
										//태스크 시작 날짜 예외
										if(data[index].startDate == null){
											$startDateTd = $("<td>").append('미정');
										}else{
											$startDateTd = $("<td>").text(data[index].startDate.substring(0,10));
										}
										
										//태스크 종료 날짜 예외
										if(data[index].endDateTd == null){
											$endDateTd = $("<td>").html('미정');
										}else{
											$endDateTd = $("<td>").text(data[index].endDate.substring(0,10));
										}
										
										 if('진행전' == data[index].progress){
											 $div = $("<div class='progressNotDoneArea'>").text(data[index].progress);
										}else if('진행중'== data[index].progress){
											 $div = $("<div class='progressingArea'>").text(data[index].progress);
										}else if('완료'== data[index].progress){
											 $div = $("<div class='progressDoneArea'>").text(data[index].progress);
										} else{
											 $div = $("<div class='progressErrorArea'>").text(data[index].progress);
										}
										 
										$progressTd.append($div);
										$tr.append($titleTd);
										$tr.append($startDateTd);
										$tr.append($endDateTd);
										$tr.append($progressTd);
										$table.append($tr);
									}
				  			
			  					}else{
					  				const $tbody =$("<tbody id= myTaskTbody>");
									const $titleTd = $("<h3 style='text-align:center; margin-top:50%'>").text("해당 태스크가 없습니다.");
									$table.append($titleTd);
					  			}
			  				},
							error: function(xhr, status, error) {
							   console.log(xhr);
							}
			  		});
				}
			}
		}) 
    	//투두리스크 삭제 버튼 클릭 시
		$('.img-button-minus').on("click", function () {
        	for(let i = 0; i < $ToDoListCheckBox.length; i++){
        		if($ToDoListCheckBox[i].checked){
        			$.ajax({
      	  				url: "/byat/mytask/remove",
      	  				type: "get",
      	  				data: { "toDoListNo" : $toDoListNo[i].value},
      	  				success:function(data, status, xhr){
  	  						window.location.href ='${pageContext.servletContext.contextPath}/mytask/list';
      	  				},
						error: function(xhr, status, error) {
						   console.log(xhr);
						}
      	  			});
      	  		}
        	}
        });
    	
        
        let $toDoListTextStyle = "";
        let checked = "";
        let checkedValue = "";
        // todolist 체크 상태 변경
        for(let i = 0; i < $ToDoListCheckBox.length; i++){
            $ToDoListCheckBox[i].onclick = function() {
            	
            	if($ToDoListCheckBox[i].checked){
            		style = "text-decoration:line-through";
            		checkedValue="Y";
            		checked ="checked";
                }else{
                	style = "text-decoration:none";
                	checkedValue="N";
                	checked ="";
                }
            	
            	 $.ajax({
                     url : "/byat/mytask/modifytodoliststatus",      
                     type : "get",            
                     data : {"no"  : $toDoListNo[i].value
                    	 	,"checkStatus" : checkedValue},           
                     success: function(data, status, xhr) {
                    	
                    	 $ToDoListText[i].style = style;
                    	 $ToDoListCheckBox[i].checked = checked;
                     },
                     error: function(xhr, status, error) {
						   console.log(xhr);
						 }
                  });
      		 }
        }
     
      
      //ToDoList 내용 수정 
      $(document).ready(function(){
    	  $ToDoListText = document.querySelectorAll("#ToDoListText");
    	  
        	for(let i = 0; i < $ToDoListText.length; i++){
        		
        		$(ToDoListText[i]).on('focus', function(){
        			
	        		$(ToDoListText[i]).on("propertychange change keyup paste input", function() {
	        			console.log("gg");
	        		 	$.ajax({
	                     	url : "/byat/mytask/modify",      
	                     	type : "post",            
	                    	 data : {"title"  : $ToDoListText[i].value
	                    	 		, "no" : $toDoListNo[i].value },           
	                     	success: function(data, status, xhr) {
	                     		console.log("수정 성공");
	                     	},
	                     	error: function(xhr, status, error) {
							   	console.log(xhr);
							 }
	                  	});
	        		});
        	  	});
      		}
       	});
      	  					

      
        //구성원 모달창
        for(let i = 0; i < $membersModalButton.length; i++){
       	
       	  $membersModalButton[i].onclick = function(e) {
       		 e.stopPropagation();
       		  console.log($projectCode[i].value);
       		  
       		 $.ajax({
 	  				url: "/byat/mytask/selectmembermodal",
 	  				type: "post",
 	  				data: { "projectCode" : $projectCode[i].value},
 	  				success:function(data, status, xhr){
 	  					console.table(data);
					
						const $table = $("#modalTable tbody");
						$table.html("");
					
						for(let index in data) {
							const $tr = $("<tr>");
							const $noTd = $("<td id=myTaskListProjectName>").text(data[index].id);
							const $nameTd = $("<td id=myTaskListProjectDeadLine>").text(data[index].name);
							const $roleNameTd = $("<td>").text(data[index].roleName);
							
							$tr.append($noTd);
							$tr.append($nameTd);
							$tr.append($roleNameTd);
						
							$table.append($tr);
 	  					}
 	  					
 	  					$('#myModal').show(); 
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
