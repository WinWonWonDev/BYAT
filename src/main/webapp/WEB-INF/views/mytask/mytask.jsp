<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ include file="/WEB-INF/views/common/manubar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<style>

	html  {
		width: 100%;
		height: 98%;
		background:#D7E3FA;
      	background-size: cover;
		position:relative;
	}
	
	body {
		width: 99%;
		height: 95%;
	}
	
	
	p {
  		margin: 5px;
 		text-indent: 1ch;
	}
	
	



	#myTaskWhiteBoard{
    	background:white;
      	position:absolute;
      	top:20%;
      	left:2%;
      	width:95%;
      	height:78%;
      	border:1px solid black;
	}

	#myTaskProjectListBox{
	 	position:absolute;
	 	top:2%;
	 	left:2%;
	 	width:31%;
	 	height:45%;
	}
	
	#myTaskProjectListInnerBox{
		position:absolute;
		overflow:scroll;
		width:98%;
		height:98%;
      	border:1px solid black;
      	text-align: center;
      	overflow-x:hidden;
	}
	
	.myTaskProjectTable{
		margin-left:10px;
		margin-top:5px;
		margin-bottom:5px;
		width:94%;
		height:auto;
		border:1px solid black;
	}



	#myTaskProjectChartBox{ 	
		position:absolute;
	 	top:2%;
	 	left:34%;
	 	bottom:50%;
	 	width:31%;
	 	height:45%;
	}
	
	.myTaskProjectChartInnerBox{
		position:absolute;
		width:98%;
		height:98%;
      	border:1px solid black;
	}
	
	#piechart{
		width: 400px;
        height: 230px;
        position: absolute;
	}






	#myTaskListBox{
	 	position:absolute;
	 	top:2%;
	 	left:66%;
	 	right:2%;
	 	bottom:2%;
	 	width:32%;
	 	height:96%;
	}

	#myTaskListInnerBox {
		position:absolute;
		left: 50%;
		width:96%;
		height:94%;
		overflow:scroll;
		overflow-x:hidden;
      	border:1px solid black;	
  		transform: translateX(-50%);
	}
	.myTaskList {
		position:absolute;
		left: 50%;
		width:98%;
		margin-top:10px;
		height:auto;
		border:1px solid black;
		background-color : #00FFFF;
		transform: translateX(-50%);
		border-radius: 20px;
	}
	#member-circle{
		position:absolute;
		width:30px;
		height:30px;
		border-radius:50%;
		background:red;
		color:white;
		font-size:10px;
		font-weight:bold;
		font-color:white;
		line-height:30px;
		text-align:center;
	}
	#myTaskProjectMemberBox {
		position:absolute;
		width:100px;
		height:50px;
		
	}
	#myTaskProjectMemberInnerBox{
		position:relative;
	} 


	

	.myTaskDoToListInnerBox{
		position:absolute;
		overflow:scroll;
		width:98%;
		height:98%;
		border:1px solid black;
		overflow-x:hidden;
	}
	
	#myTaskDoToListBox{ 	
		position:absolute;
	 	top:54%;
	 	bottom:2%;
	 	left:2%;
	 	right:10%;
	 	width:64%;
	 	height:39%;
	}
	
	.myTaskDoToList{
		position:absolute;
		width:auto;
		height:auto;
		border:1px solid black;
	}
	
	.myTaskDoToList-left{
		position:absolute;
		width:45%;
		height:auto;
		margin:20px;
	}
	
	.myTaskDoToList-right{
		position:absolute;
		left:50%;
		width:45%;
		height:auto;
		margin:20px;
	}
	
	.myTaskListProjectStateBox{
		width:80px;
		height:60px;
		border-radius:10%;
		background:red;
		color:white;
		font-size:15px;
		font-weight:bold;
		font-color:white;
		line-height:60px;
		text-align:center;
	}

	#ToDoListText01{
		width:350px;
	}

	
	.projectstate{
		width:60px;
		height:40px;
		border-radius:10%;
		background:red;
		color:white;
		font-size:15px;
		font-weight:bold;
		font-color:white;
		line-height:40px;
		text-align:center;
	}
	
	#pm-circle{
		width:40px;
		height:40px;
		border-radius:50%;
		background:red;
		color:white;
		font-size:15px;
		font-weight:bold;
		font-color:white;
		line-height:40px;
		text-align:center;
	}
	
	
</style>
<title>Insert title here</title>
</head>
<body>
	<div id="myTaskWhiteBoard">
		
		<div id ="myTaskProjectListBox">
			<p>프로젝트 목록</p>
			<div id="myTaskProjectListInnerBox">
				<table class="myTaskProjectTable">
					<tr>
						<th colspan="3" id="myTaskProjectName">당근마켓 사이트 제작</th>
					</tr>
					<tr>
						<td id="myTaskProjectMemberBox"> 
							<div id="myTaskProjectMemberInnerBox"> 
								<div id="member-circle"">수빈</div>
								<div id="member-circle" style="left:20px;">소현</div>
							</div>
						</td>
						
						<td id="myTaskPrjectDeadline">2022-01-22<br>~2022-03-25</td>
						<td id="myTaskProjectStateBox">
							<div class="projectstate">미진행</div>
						</td>
						<td class="myTaskProjectPMBox">
							<div id="pm-circle">소현</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		
		<div id ="myTaskProjectChartBox">
			<p>나의 프로젝트<p>
			<div class ="myTaskProjectChartInnerBox">
				<div id="piechart"></div>
			</div>
		</div>
		
		<div id ="myTaskListBox">
			<p>나의 테스크</p>
			<div id="myTaskListInnerBox">
				<table class="myTaskList">
					<tr>
						<td id="myTaskListProjectNmae">키오스크결제시스템</td>
						<td id="myTaskListProjectDeadLine">2022-01-17<br>~</td>
						<td class="myTaskListProjectStateBox">진행전</td>
					</tr>
				</table>
			</div>
		</div>
		
		<div id ="myTaskDoToListBox">
			<p>ToDoList
			<input type="button" class="img-button-plus" onClick="ListCreate()" value="+">
			<input type="button" class="img-button-minus" onClick="ListRemove()" value="-">
			</p>	
			<div class="myTaskDoToListInnerBox" >
				<table class="myTaskDoToList-left">
					<tr>
						<td>
							<input type="checkbox" id="ToDoListCheckBox01" name="ToDoListCheckBox01"/>
							<input type="text" id ="ToDoListText01" name="ToDoListText01" value="33"/>
						</td>
					</tr>
					<tr>
						<td>
							<input type="checkbox" id="ToDoListCheckBox01" name="ToDoListCheckBox01"/>
							<input type="text" id ="ToDoListText01" name="ToDoListText01" value="33"/>
						</td>
					</tr>
				</table>
				<table class="myTaskDoToList-right">
					
				</table>
			</div>
		</div>
		
	</div>
	
	
	<script type="text/javascript">

		google.charts.load('current', {'packages':['corechart']});
		google.charts.setOnLoadCallback(drawChart);


		function drawChart() {
  			var data = google.visualization.arrayToDataTable([
  				['Task', 'Hours per Day'],
  				['진행 중', 6],
  				['완료', 2],
 			 	['미진행', 4]
			]);
  			
  			var options = {'alignment':'center','width':390, 'height':230 ,
  					legend: {'position':'right','alignment':'center'}};
  			
  			var chart = new google.visualization.PieChart(document.getElementById('piechart'));
  			
  			chart.draw(data, options);
		}
		function ListCreate(){
			var left = document.getElementsByClassName("myTaskDoToList-left");
			var right = document.getElementsByClassName("myTaskDoToList-right");
			var newDiv = document.createElement("div");
			var table = document.createElement('table');
			
				newDiv.onclick = function(){
                p = this.parentElement; //부모 HTML 태그요소
                p.removeChild(this); // 자신을 부모 태그로 부터 제거   
				}
				 obj.appendChild(newDiv);
		}
		
		function ListRemove(){
			
		}
		
	</script>
	
</body>
</html>