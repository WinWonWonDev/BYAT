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

	<style>
	html {
		width: 100%;
		height: 98%;
		background: #D7E3FA;
		background-size: cover;
		position: relative;
		overflow-y: hidden;
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
		overflow-y: scroll;
		width:101%;
   		height: 150px;
    	overflow: auto;
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
	/*스크롤 스타일*/
	.modalTbodyDiv {
    
  	}
	 
	 .modalTbodyDiv::-webkit-scrollbar {
	    width: 10px;
	}
	  
	  .modalTbodyDiv::-webkit-scrollbar-thumb {
	    background-color: #2f3542;
	    border-radius: 10px;
	  }
	  .modalTbodyDiv::-webkit-scrollbar-track {
	    background-color: grey;
	    border-radius: 10px;
	    box-shadow: inset 0px 0px 5px white;
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
		overflow: scroll;
		width: 98%;
		height: 98%;
		border: 1px solid black;
		text-align: center;
		overflow-x: hidden;
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
		top: 2%;
		left: 34%;
		bottom: 50%;
		width: 31%;
		height: 45%;
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
		width: 32%;
		height: 96%;
	}
	
	#myTaskListInnerBox {
		position: absolute;
		width: 96%;
		height: 94%;
		overflow-y: scroll;
		border: 1px solid black;
	
	}
	
	.myTaskList {
		width: 98%;
		height: auto;
		text-align: center;
		
		padding: 10px;
		margin-top:10px;
		display: block;
	}
	
	.myTaskTbody {
		margin-top: 200px;
		padding-top :100px;
		height:80px;
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
		overflow: scroll;
		width: 98%;
		height: 98%;
		border: 1px solid black;
		overflow-x: hidden;
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
	
	.myTaskDoToList {
		position: absolute;
		width: auto;
		height: auto;
		border: 1px solid black;
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
	
	#ToDoListCheckBox {
		margin-right: 10px;
	}
	
	#ToDoListText {
		width: 80%;
	}
	
	.todolist {
		border: 1px solid black;
		border-radius: 10%;
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
/* 스클롤*/


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
			<p>나의 테스크</p>
				<div id="myTaskListInnerBox">
					<table></table>
						<table class="myTaskList" id="myTaskList">
							<c:forEach items="${ taskList }" var="taskList" varStatus="status">
								<tbody id= "myTaskTbody" class="myTaskTbody">
									
									<tr >
										<td><c:out value="${ taskList.title }"/> </td>
										<td><c:out value="${ taskList.startDate }"/><br>~ <c:out value="${ taskList.endDate }"/></td>
										<td>
											<c:choose>
			                   			  		<c:when test="${ taskList.progress eq '진행전' }">
			                   			  			<div class="projectstate1"><c:out value="${ taskList.progress }"/></div>
			                   			  		</c:when>
			                   			  		<c:when test="${ taskList.progress eq '진행중' }">
			                   			  			<div class="projectstate2"><c:out value="${ taskList.progress }"/></div>
			                   			  		</c:when>
			                   			  		<c:when test="${ taskList.progress eq '진행완료' }">
			                   			  			<div class="projectstate3"><c:out value="${ taskList.progress }"/></div>
			                   			  		</c:when>
			                       			 </c:choose>
										</td>
									</tr>
							</tbody>
						</c:forEach>
					</table>
			</div>
		</div>

		<div id="myTaskDoToListBox">
			<input type="hidden" id="loginMemberNo" value="${sessionScope.loginMember}"/>
			<p>
				ToDoList 
				<input type="button" class="img-button-plus" value="+" onclick="location.href ='${pageContext.servletContext.contextPath}/mytask/regist'">
				<input type="button" class="img-button-minus" value="-" />
			</p>
			<div class="myTaskDoToListInnerBox">
				 <table class="myTaskDoToList-left">
						<c:forEach items="${ todoList }" var="todolist" varStatus="status">
				 			<c:choose>	
				 				<c:when test="${status.index % 2 == 1}">	
									<tr id="todolist" class="todolist">
										<td>
											<input type ="hidden" id="toDoListNo" value="${todolist.no}"/>
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
							<c:when test="${status.index % 2 == 0}">		
								<tr id="todolist" class="todolist">
									<td>
										<input type ="hidden" id="toDoListNo" value="${ todolist.no }"/>
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
		const $ToDoListText = document.querySelectorAll("#ToDoListText");
		const $todolist = document.querySelectorAll("#todolist");
		const $toDoListNo = document.querySelectorAll("#toDoListNo");
    	const $modalTable = document.querySelectorAll("#modalTable");
		const $membersModalButton = document.querySelectorAll("#membersModalButton");
		const $projectCode = document.querySelectorAll("#projectCode");
		const $projectList = document.querySelectorAll("#projectList");
		
		document.getElementById("projectBackbtn").onclick = function(e) {
			window.location.href="${ pageContext.servletContext.contextPath }/mytask/list";
	    }
		google.charts.load('current', {'packages':['corechart']});
    	google.charts.setOnLoadCallback(drawChart);
    	
    	//그래프 그리기
    	function drawChart() {

        	var data = google.visualization.arrayToDataTable([
                ['Task', 'Hours per Day'],
                ['진행 중',${projectProgress[0]} ] ,
                ['완료', ${projectProgress[1]} ] ,
                ['미진행', ${projectProgress[2]}]
             ]);
        
            var options = {   title:'나의 프로젝트 상태'
            				, alignment:'center'
            				, width:'80%'
            				, height:'80%' 
            				, legend: {'position':'right','alignment':'center'}
            	  			, slices: [{color: 'rgb(78,115,223)'}, {color :'rgb(41, 60, 117)'}, {color: 'rgb(196, 196, 196)'}]};
            var chart = new google.visualization.PieChart(document.getElementById('piechart'));
            chart.draw(data, options);
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
			  					
			  					for(let i = 0; i < $projectList.length; i++){
			  						$projectList[i].style.background=""
			  					}
			  					$projectList[i].style.background="SkyBlue";
			  					
			  					if(data!=0){
				  					for(let index in data) {
				  						console.log(data);
				  						const $tbody =$("<tbody id='myTaskTbody' class='myTaskTbody'>");
										const $tr = $("<tr>");
										const $titleTd = $("<td>").text(data[index].title);
										const $dateTd = $("<td>").text(data[index].startDate);
										
										const $br = $("<br>").text("~" + data[index].endDate);
										const $progressTd = $("<td>");
										let $div = $("<div>");
										
										 if('진행전' == data[index].progress){
											$div = $("<div class='projectstate1'>").text(data[index].progress);
										}else if('진행중'== data[index].progress){
											$div = $("<div class='projectstate2'>").text(data[index].progress);
										}else if('진행완료'== data[index].progress){
											$div = $("<div class='projectstate3'>").text(data[index].progress);
										}else{
											$div = $("<div class='projectstate4'>").text(data[index].progress);
										} 
										 
										$progressTd.append($div);
										$tr.append($br);
										$tr.append($titleTd);
										$tr.append($dateTd);
										$tr.append($progressTd);
										$tbody.append($tr);
										$table.append($tbody);
									}
				  			
			  					}else{
					  				const $tbody =$("<tbody id= myTaskTbody>");
					  				const $tr = $("<tr>");
									const $titleTd = $("<td>").text("해당 태스크가 없습니다.");
									$tr.append($titleTd);
									$tbody.append($tr);
									$table.append($tbody);
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
      	  				data: { "todolistNo" : $toDoListNo[i].value},
      	  				success:function(data, status, xhr){
      	  						$todolist[i].remove();
      	  				},
						error: function(xhr, status, error) {
						   console.log(xhr);
						}
      	  			});
      	  		}else{
      	  			//정렬 여기서해줄까???
      	  		}
        	}
        });
    	//
       /*   for(let i = 0; i < $membersModalButton.length; i++){
        	
        	 $membersModalButton[i].onclick = function(e) {
        		 
        		 $.ajax({
  	  				url: "/byat/mytask/selectmembermodal",
  	  				type: "post",
  	  				data: { "projectCode" : $projectCode[i].value},
  	  				success:function(data, status, xhr){
						const $table = $("#modalTable tbody");
						$table.html("");
					
						for(let index in data) {
							const $tr = $("<tr>");
							const $noTd = $("<td>").text(data[index].id);
							const $nameTd = $("<td>").text(data[index].name);
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
        }  */
        
        let style ="";
        let checked="";
        // 체크 상태 변경
        for(let i = 0; i < $ToDoListCheckBox.length; i++){
            $ToDoListCheckBox[i].onclick = function() {
            	if($ToDoListCheckBox[i].checked){
            		style = "text-decoration:line-through";
            		checked="Y";
                }else{
                	style = "text-decoration:none";
                	checked="N";
                }
            	
            	 $.ajax({
                     url : "/byat/mytask/modifytodoliststatus",      
                     type : "get",            
                     data : {"no"  : $toDoListNo[i].value
                    	 	,"checkStatus" : checked},           
                     success: function(data, status, xhr) {
                    	 ToDoListText[i].style=style;
                     },
                     error: function(xhr, status, error) {
						   console.log(xhr);
						 }
                  });
      		 }
        }
     
        
        //ToDoList 내용 수정 
      $(document).ready(function(){
    	  let focusindex ="";
        	for(let i = 0; i < ToDoListText.length; i++){
        		
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
      		}
       	});
        //구성원 모달창 Ajax
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
							const $noTd = $("<td id=myTaskListProjectNmae>").text(data[index].id);
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
