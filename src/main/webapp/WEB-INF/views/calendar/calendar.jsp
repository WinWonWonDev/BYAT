<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.greedy.byat.calendar.model.dto.ScheduleDTO"%> 
<%@ include file="../common/menubar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href='${pageContext.request.contextPath}/resources/fullcalendar-5.1.0/lib/main.css' rel='stylesheet' />
<script src='${pageContext.request.contextPath}/resources/fullcalendar-5.1.0/lib/main.js'></script>
<script src='${pageContext.request.contextPath}/resources/fullcalendar-5.1.0/lib/locales/ko.js'></script>
<%-- <link rel='stylesheet' href='<%=request.getContextPath()%>/resources/css/main.css' type="text/css"/>
<script src='<%=request.getContextPath()%>/resources/css/main.js'></script> --%>
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
	
	  // initialize the external events
	  // -----------------------------------------------------------------
	
	  // initialize the calendar
	  // -----------------------------------------------------------------
	  
	  
	   calendar = new Calendar(calendarEl, {
		  initialView: 'dayGridMonth',
		  locale: 'ko', //한국어 나오게 하는 것 !
	      headerToolbar: {
	      left: 'prev,next today',
	      center: 'title',
	      right: 'dayGridMonth,timeGridWeek,timeGridDay',
	      },
  	      editable: true,
  	      eventLimit: true,
  	      selectable: true,
  	      selectHelper: true,
  	      /* 여기부터 추가했고 */
          selectMirror: true,
          navLinks: true,
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
      
      droppable: true, // this allows things to be dropped onto the calendar
      events: [ 
  	    <%List<ScheduleDTO> calendarList = (List<ScheduleDTO>) request.getAttribute("calendarList");%>
        <%if (calendarList != null) {%>
        <%for (ScheduleDTO dto : calendarList) {%>
        {
        	title : '<%=dto.getTitle()%>',
            start : '<%=dto.getStartDate()%>',
            end : '<%=dto.getEndDate()%>',
            color : '#' + Math.round(Math.random() * 0xffffff).toString(3)
         },
<%}
}%>
			],
      drop: function(info) {
	      // is the "remove after drop" checkbox checked?
	      if (checkbox.checked) {
	        // if so, remove the element from the "Draggable Events" list
	        info.draggedEl.parentNode.removeChild(info.draggedEl);
	      }
	    }
	  });
	
	  calendar.render();
	});
	
	
	function allSave() {
		
		var allEvent = calendar.getEvents();
		console.log(allEvent);
		
		var events = new Array();
		
		for(var i = 0; i < allEvent.length; i++) {
			
			var obj = new Object();
			
			obj.title = allEvent[i]._def.title; //이벤트 명칭
			/* obj.allDay = allEvent[i]._def.adllDay //하루종일 이벤트인지 아닌지 */
			obj.startDate = allEvent[i]._instance.range.start //시작시간
			obj.endDate = allEvent[i]._instance.range.end //종료시간
			
			events.push(obj);
		}
	
			var jsondata = JSON.stringify(events);
			console.log(jsondata);
			
			savedata(jsondata);
	}
	
	function savedata(jsondata) {
		$.ajax({
			url: "regist",
			type: "post",
			data: {"alldata":jsondata},
			dataType: "text",
			async: false, //동기식으로 넘기기! 
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
	background-color:rgb(25,25,112);
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
	width:15%; 
	padding-right:30px; 
	padding-left:20px; 
	margin-top:30px;
	position:absolute;
	left:86%;
	top:15%;

}

#calendarDiv {
	float:right; 
	width:80%;  
	margin-top:30px;
	position:absolute;
	left:1%;
	top:6%;
	z-index:-1;

}


</style>
<script>
	const message = '${ requestScope.message }';
	if (message != null && message != '') {
		alert(message);
	}
</script>
</head>
<body>
	<div id='external-events'>
		<button id="saveButton" style="cursor:pointer;" onclick="allSave();" >일정 저장</button>	
	</div>

	<div id="calendarDiv">
		<div id='calendar'></div>
	</div>

</body>
</html>