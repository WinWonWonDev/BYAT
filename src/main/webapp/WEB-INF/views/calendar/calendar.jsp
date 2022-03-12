<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.greedy.byat.calendar.model.dto.ScheduleDTO"%> 
<%@page import="com.greedy.byat.member.model.dto.MemberDTO"%> 
<%@ include file="../common/menubar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href='${pageContext.request.contextPath}/resources/fullcalendar-5.1.0/lib/main.css' rel='stylesheet' />
<script src='${pageContext.request.contextPath}/resources/fullcalendar-5.1.0/lib/main.js'></script>
<script src='${pageContext.request.contextPath}/resources/fullcalendar-5.1.0/lib/locales/ko.js'></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	
	  var calendar = null;
	  var sch;

	$(document).ready(function() {
	  var Calendar = FullCalendar.Calendar;
	  var Draggable = FullCalendar.Draggable;
	  var containerEl = document.getElementById('external-events');
	  var calendarEl = document.getElementById('calendar');
	  var checkbox = document.getElementById('drop-remove');
	
	   calendar = new Calendar(calendarEl, {
		  initialView: 'dayGridMonth',
		  locale: 'ko', 
		  height: '900px', 
		  expandRows: true,
	      headerToolbar: {
	      left: 'prev,next today',
	      center: 'title',
	      right: 'dayGridMonth,timeGridWeek,timeGridDay',
	      },
  	      editable: true,
	      selectable: true,
          selectMirror: true,
          navLinks: true,
          dayMaxEvents: true,
          select: function(arg) {
         
       	  var title = prompt('일정을 작성해주세요!');
          
       	  if (title) {
          calendar.addEvent({
          title: title,
          start: arg.start,
          end: arg.end,
          allDay: arg.allDay
          })
        }
        calendar.unselect()
      }, 
	      eventClick: function(arg) {
          if (confirm('정말 삭제하시겠습니까?')) {
            arg.event.remove()
          }
        },
      droppable: true,
      events: [ 
   	    <%List<ScheduleDTO> calendarList = (List<ScheduleDTO>) request.getAttribute("calendarList");%>
        <%if (calendarList != null) {%>
        <%for (ScheduleDTO dto : calendarList) {%>
        <%if (dto.getMemberNo() == 13) {%>
        	{
	        	title : '<%=dto.getTitle()%>',
	            start : '<%=dto.getStartDate()%>',
	            end : '<%=dto.getEndDate()%>',
	            color : 'gray',
	            backgroundColor: "skyblue"
	         },  
        	
        <%} else {%>

        <%if (dto.getTypeCode() == 2) {%>
        {
        	title : '<%=dto.getTitle()%>',
            start : '<%=dto.getStartDate()%>',
            end : '<%=dto.getEndDate()%>',
            color : 'gray',
            backgroundColor: "green"
         },  
        	 
        <%} else if(dto.getTypeCode() == 1) {%>
        
        {
        	title : '<%=dto.getTitle()%>',
            start : '<%=dto.getStartDate()%>',
            end : '<%=dto.getEndDate()%>',
            color : 'gray',
            editable : false,
            backgroundColor: "#483D8B"
         },
        
        <%}  else if(dto.getTypeCode() == 3) {%>
        	
        {
        	title : '<%=dto.getTitle()%>',
            start : '<%=dto.getStartDate()%>',
            end : '<%=dto.getEndDate()%>',
            color : 'gray',
            backgroundColor: "skyblue",
            editable: false
         }, 
        	
        
        <%}%>
        <%}%>
	<%}%>
<%}%>
		], 
     	 drop: function(info) {
	      if (checkbox.checked) {
	        info.draggedEl.parentNode.removeChild(info.draggedEl);
	      }
	    }
	  });
	
	  calendar.render();
	});
	
	function allSave() {
		var allEvent = calendar.getEvents();
		var events = new Array();
		
		for(var i = 0; i < allEvent.length; i++) {
			var obj = new Object();
			
			obj.title = allEvent[i]._def.title; 
			obj.allDay = allEvent[i]._def.adllDay 
			obj.startDate = allEvent[i]._instance.range.start
			obj.endDate = allEvent[i]._instance.range.end 
			
			events.push(obj);
		}
			var jsondata = JSON.stringify(events);
			savedata(jsondata);
	}
	
	function savedata(jsondata) {
		$.ajax({
			url: "regist",
			type: "post",
			data: {"alldata":jsondata},
			dataType: "text",
			async: false, 
		})
		.done(function(data) {
			alert("일정 등록 성공!");
		}) 
		.fail(function(request, status, error) {
			alert("에러발생! 다시 시도해주세요!");
		});
	}
	
</script>
<style>

	.fc-event {
		margin-top:5px;
		cursor:move;
		background-color:#483D8B;
	}
	
	#saveButton {
		text-align: center;
		background-color:rgb(25,25,112);
		width:80px;
		height:30px;
		color:white;
		cursor:pointer;
	}
	
	#external-events {
		float:right; 
		position:absolute;
		left:90%;
		top:19%;
	
	}
	
	#calendarDiv {
		float:right; 
		width:85%;  
		margin-top:30px;
		position:absolute;
		left:1%;
		top:6%;
		z-index:-1;
	
	}
	
	#selectBox {
		position:absolute;
		top:11%;
		left:88%;
		width:150px;
		height:30px;
		font-size:5px;
		text-align:center;
		background: white;
		border-radius: 3px;
		z-index:-1;	
	
	}

</style>
<script>
	const message = '${ requestScope.message }';
	if (message != null && message != '') {
		alert(message);
	}
</script>
<title>BYAT 캘린더 JSP</title>
</head>
<body>
	<c:if test="${ sessionScope.loginMember.no == requestScope.memberNos }">
		<div id='external-events'>
			<button id="saveButton" style="cursor:pointer;" onclick="allSave();" >일정 저장</button>	
		</div>
	</c:if>
	<div id="calendarDiv">
		<div id='calendar'></div>
	</div>
	<c:if test="${ sessionScope.loginMember.name eq '관리자' }">
		<select id="selectBox" name="selectBox" onchange="moveCalendarByMember(this.value);">
		
		</select>
	</c:if>
	
<script>
		const selectBoxDiv = document.getElementById("selectBoxDiv");
		const select = document.getElementById("selectBox");
		const option = document.createElement("option");

		function moveCalendarByMember(value) {
			location.href = "${ pageContext.servletContext.contextPath }/calendar/list?no=" + value;
		}
		
		function createSelectBox() {
			option.value = "ALL";
			option.text = "선택해주세요";
			select.appendChild(option);
			
			return select;
		} 

		$.ajax({
			url : "/byat/calendar/selectallmember",
			method : "get",
			data: {},
			success: function(data, status, xhr) {
				createSelectBox(); 

				for(i = 0; i < data.length; i++) {
					let option = document.createElement("option");
					option.value = data[i].no;
					
					option.text = data[i].name + " " + data[i].id;
					select.appendChild(option);
				}
			},
			error: function(error) {
				alert("에러 발생 다시 시도해주세요!");
			}
		});

</script>
</body>
</html>