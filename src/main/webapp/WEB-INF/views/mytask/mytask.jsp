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
}

body {
	width: 100%;
	height: 95%;
}

p {
	margin: 5px;
	text-indent: 1ch;
}

.modal {

	display: none; 
    position: fixed; 
    z-index: 1; /* Sit on top */
    left: 0;
    top: 0;
	width: 100%; 
	height: 100%; 
	overflow: auto; 
	background-color: rgb(0,0,0);
	background-color: rgba(0,0,0,0.4); 
	overflow-y: hidden;
	
}
.modal-content {
	background-color:#3b60d0;
	border-radius: 30px;
	margin: 8% auto; 
	padding: 10px;
	border: 1px solid #888;
    width: 40%;     
}
.modal-content-white{
	background-color:white;
	border-radius: 30px;
	margin: 5% auto; 
	padding: 20px;
	border: 1px solid #888;
    width: 80%; 
    min-height: 200px;
}
.modal-content-gray{
	background-color: rgb(242,242,242);
	border-collapse: collapse;
	border-radius: 30px;
	padding: 10px;
	border: 2px solid #000000;
    width: 90%; 
    min-height: 100px;
    max-height:60%;
}
.ok-button{
	width:30%;
	cursor:pointer;
	text-align:center;
	color:white;
	margin: 0 auto;
	border-radius: 10px;
	background-color:#172653;
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
	left: 50%;
	width: 96%;
	height: 94%;
	overflow: scroll;
	overflow-x: hidden;
	border: 1px solid black;
	transform: translateX(-50%);
}

.myTaskList {
	position: absolute;
	left: 50%;
	width: 98%;
	margin-top: 10px;
	height: auto;
	border: 1px solid black;
	background-color: #00FFFF;
	transform: translateX(-50%);
	border-radius: 20px;
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
	background-color:rgb(255,223,186);
	color: white;
	font-size: 10px;
	font-weight: bold;
	font-color: white;
	line-height: 30px;
	text-align: center;
}

#myTaskProjectMemberBox {
	width :100px;
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

.myTaskListProjectStateBox {
	width: 80px;
	height: 60px;
	border-radius: 10%;
	background: red;
	color: white;
	font-size: 15px;
	font-weight: bold;
	font-color: white;
	line-height: 60px;
	text-align: center;
}

#ToDoListCheckBox{
	margin-right:10px;
}

#ToDoListText {
	width: 80%;
}
.todolist{
	border: 1px solid black;
	border-radius: 10%;
}

.projectstate1 {
	width: 60px;
	height: 40px;
	border-radius: 10%;
	background-color:rgb(196, 196, 196);
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
	background:rgb(78,115,223);
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
	background-color:rgb(41, 60, 117);
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

.border{
  	border: 1px solid black;
  	height: 90px;
  	
}
tbody:hover {background-color: skyblue;}

</style>
<title>Insert title here</title>
</head>
<body>
	<div id="myTaskWhiteBoard">
		<div id="myTaskProjectListBox">
			<p>프로젝트 목록</p>
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
                           			<c:forEach items="${  project.projectMembers  }" var="projectmember" varStatus="status">
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
                                    </c:forEach>
                       			</td>
                        		<td>
                        			<input type="hidden" id=projectNum value="${project.code}"/>
                        			<input id="membersModal" type="button" value="..."/>
                       			 </td>
                      			 <td id="myTaskPrjectDeadline">
                          			 <c:out value="${ project.startDate }"/> <br> ~ <c:out value="${ project.endDate }"/>
                        		</td>
                       			 <td id="myTaskProjectStateBox">
                         			  	<c:choose>
                         			  		<c:when test="${ project.progress eq '미진행' }">
                         			  			<div class="projectstate1"><c:out value="${ project.progress }"/></div>
                         			  		</c:when >
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
				<table class="myTaskList">
					<%-- <c:forEach items="${ taskList }" var="member" varStatus="status">
						<tbody id="border">
							<tr>
								<td id="myTaskListProjectNmae"><c:out value="${ taskList.title }"/></td>
								<td id="myTaskListProjectDeadLine"><c:out value="${ taskList.endDate }"/><br>~</td>
								<td class="myTaskListProjectStateBox"><c:out value="${ taskList.progress }"/></td>
							</tr>
						</tbody>
					</c:forEach> --%>
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
						<c:forEach items="${ todolist }" var="todolist" varStatus="status">
				 			<c:choose>	
				 				<c:when test="${status.index%2==1}">	
									<tr id="todolist" class="todolist">
										<td>
											<input type ="hidden" id="toDoListNo" value="${todolist.no}"/>
											<input type="checkbox" id="ToDoListCheckBox" name ="ToDoListText" ${todolist.checkStatus  eq "Y" ? "checked" : ""}/>
											<input type="text" id="ToDoListText" name="ToDoListText" value="${todolist.title}" ${todolist.checkStatus  eq "Y" ? "style=text-decoration:line-through;" : ""} />
											</td>
										</tr>
								</c:when>
							</c:choose>
						</c:forEach>
					</table>
				<table class="myTaskDoToList-right">
					<c:forEach items="${ todolist }" var="todolist" varStatus="status">
						<c:choose>	
							<c:when test="${status.index%2==0}">		
								<tr id="todolist" class="todolist">
									<td>
										<input type ="hidden" id="toDoListNo" value="${todolist.no}"/>
										<input type="checkbox" id="ToDoListCheckBox" name ="ToDoListText" ${todolist.checkStatus  eq "Y" ? "checked" : ""}/>
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
                	<table class="modal-content-gray">
                		<tr>
                			<th>사번</th>
                			<th>구성원 이름</th>
                			<th>역할</th>
                		</tr>

                	</table>
           	</div>
            <div class="ok-button" onClick="close_pop();">
                	<span class="pop_bt" style="font-size: 13pt;">ok</span>
            </div>
      </div>
 
    </div>
        <!--End Modal-->
 
 
    <script type="text/javascript">
      
        jQuery(document).ready(function() {
        	 $('#myModal').hide();
        });
        //팝업 Close 기능
        function close_pop(flag) {
             $('#myModal').hide();
        };
      
      </script>
 
	
	<script>
		let count = 0;
		const $membersModal = document.querySelectorAll("#membersModal");
		const $ToDoListCheckBox = document.querySelectorAll("#ToDoListCheckBox");
		const $ToDoListText = document.querySelectorAll("#ToDoListText");
		const $todolist = document.querySelectorAll("#todolist");
		const $toDoListNo = document.querySelectorAll("#toDoListNo");
		const #projectNum = document.qerySelecttorAll("projectNum");
		
		google.charts.load('current', {'packages':['corechart']});
    	google.charts.setOnLoadCallback(drawChart);
    	
    	function drawChart() {

        	var data = google.visualization.arrayToDataTable([
                ['Task', 'Hours per Day'],
                ['진행 중',${projectProgress[0]} ] ,
                ['완료', ${projectProgress[1]} ] ,
                ['미진행', ${projectProgress[2]}]
             ]);
            
            var options = {   title:'나의 프로젝트 상태'
            				, alignment:'center'
            				, width:'390px'
            				, height:'328px' 
            				, legend: {'position':'right','alignment':'center'}
            	  			, slices: [{color: 'rgb(78,115,223)'}, {color :'rgb(41, 60, 117)'}, {color: 'rgb(196, 196, 196)'}]};
            var chart = new google.visualization.PieChart(document.getElementById('piechart'));
            chart.draw(data, options);
        }
            
    	
    	/* var timerID;
    	$(document).ready(function () {
    	    $('#execute').on('click',function(e){
    	        e.preventDefault();
    	        updateData();
    	    });
    	    $('#stop').on('click',function(e){
    	        e.preventDefault();
    	        clearTimeout(timerID); // 타이머 중지
    	        $('#showtime').html('');S
    	    });   
    	}); */

		
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
      	  			
      	  		}
        	}
        });
        
    	//모달 버튼
        for(let i = 0; i < $membersModal.length; i++){
        	membersModal[i].onclick = function() {
        		$.ajax({
  	  				url: "/byat/mytask/remove",
  	  				type: "get",
  	  				data: { "projectNum" : $projectNum[i].value},
  	  				success:function(data, status, xhr){
  	  						$('#myModal').show();
  	  				},
					error: function(xhr, status, error) {
					   console.log(xhr);
					}
  	  			}); 
        		
        	}
        }
        
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
                     url : "/byat/mytask/modifytodoListstatus",      
                     type : "get",            
                     data : {"no"  : $toDoListNo[i].value
                    	 	,"checkStatus" : checked},           
                     success: function(data, status, xhr) {
                    	 $ToDoListText[i].style=style;
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
