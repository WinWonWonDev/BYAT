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
<style>
html {
	width: 100%;
	height: 98%;
	background: #D7E3FA;
	background-size: cover;
	position: relative;
}

body {
	width: 99%;
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
	top: 20%;
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

#member-circle {
	position: absolute;
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

#myTaskProjectMemberBox {
	position: absolute;
	width: 100px;
	height: 50px;
}

#myTaskProjectMemberInnerBox {
	position: relative;
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
	margin: 20px;
}

.myTaskDoToList-right {
	position: absolute;
	left: 50%;
	width: 45%;
	height: auto;
	margin: 20px;
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

.ToDoListText {
	width: 350px;
}

.projectstate {
	width: 60px;
	height: 40px;
	border-radius: 10%;
	background: red;
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

#border{
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
						<tbody id="border" >
							<tr>
								<th colspan="5" id="myTaskProjectName" >
									 <c:out value="${ project.title }"/> 
								</th>
							</tr>
							<tr>
								<td id="myTaskProjectMemberBox">
									<div id="myTaskProjectMemberInnerBox">
										<c:forEach items="${ project.projectMembers }" var="member" varStatus="status">
                                			 <div id="member-circle"> 
                                 		   		<c:out value="${ member.name }" />
                                			 </div>
                              			</c:forEach>
									</div>
								</td>
								<td>
									<input type="button" value="..."/>
								</td>
								<td id="myTaskPrjectDeadline">
									<c:out value="${ project.startDate }"/> <br> ~ <c:out value="${ project.endDate }"/>
								</td>
								<td id="myTaskProjectStateBox">
									<div class="projectstate"><c:out value="${ project.progress }"/></div>
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
			<p>
				ToDoList 
				<input type="button" class="img-button-plus" value="+">
				<input type="button" class="img-button-minus" value="-">
			</p>
			<div class="myTaskDoToListInnerBox">
				 <table class="myTaskDoToList-left">
						<c:forEach items="${ todolist }" var="todolist" varStatus="status">
				 			<c:choose>	
				 				<c:when test="${status.index%2==1}">	
									<tr>
										<td>
											<input type="checkbox" class="ToDoListCheckBox" name ="ToDoListText" ${todolist.checkStatus  eq "Y" ? "checked" : ""}/>
											<input type="text" class="ToDoListText" name="ToDoListText" value="${todolist.title}" ${todolist.checkStatus  eq "Y" ? "style=text-decoration:line-through;" : ""} />
											
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
								<tr>
									<td>
										<input type="checkbox" class="ToDoListCheckBox" name ="ToDoListText" ${todolist.checkStatus  eq "Y" ? "checked" : ""}/>
										<input type="text" class="ToDoListText" name="ToDoListText" value="${todolist.title}" ${todolist.checkStatus  eq "Y" ? "style=text-decoration:line-through;" : ""} />	
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
 
      <!-- Modal content -->
      <div class="modal-content">
      		<h2 style="color:white; text-align: center;"> 프로젝트 구성원</h2>
      		<div class="modal-content-white">
                <h2 style="color:gray;">팀원 역할</h2>
                <div>
                	
                </div>
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
		
		google.charts.load('current', {'packages':['corechart']});
    	google.charts.setOnLoadCallback(drawChart);
    	
    	function drawChart() {

        	var data = google.visualization.arrayToDataTable([
                ['Task', 'Hours per Day'],
                ['진행 중',${projectProgress[0]} ] ,
                ['완료', ${projectProgress[1]} ] ,
                ['미진행', ${projectProgress[2]}]
             ]);
            
            var options = {'alignment':'center','width':390, 'height':328 ,
                    legend: {'position':'right','alignment':'center'}};
            var chart = new google.visualization.PieChart(document.getElementById('piechart'));
            chart.draw(data, options);
        }
            
        $('.img-button-plus').on("click", function () {
            var rowItem = "<tr>"
                rowItem += "<td> <input type='checkbox' class='ToDoListCheckBox' name='ToDoListCheckBox'/>"
                rowItem += "<input type='text' class ='ToDoListText' name='ToDoListText'/></td>"
                rowItem += "</tr>"
                if(count % 2 == 0){
                	$('.myTaskDoToList-left').append(rowItem)
                }else{
                	$('.myTaskDoToList-right').append(rowItem)        
                }
            	count++;
        });
        
	</script>
</body>
</html>