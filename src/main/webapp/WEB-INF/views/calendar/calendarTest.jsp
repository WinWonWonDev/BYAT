<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/menubar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 구글캘린더 api랑 연동한 것 -->
<link href='${pageContext.request.contextPath}/resources/fullcalendar-5.1.0/lib/main.css' rel='stylesheet' />
<script src='${pageContext.request.contextPath}/resources/fullcalendar-5.1.0/lib/main.js'></script>
<%-- <link rel='stylesheet' href='<%=request.getContextPath()%>/resources/css/main.css' type="text/css"/>
<script src='<%=request.getContextPath()%>/resources/css/main.js'></script> --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type='text/javascript'>

	document.addEventListener('DOMContentLoaded', function() {
	  var calendarEl = document.getElementById('calendar');

	  var calendar = new FullCalendar.Calendar(calendarEl, {
	    googleCalendarApiKey: 'AIzaSyA_8rinYhkiVxVuQzQFJeeMvgLDycDaCxg',
	    editable : true, //구글캘린더에도 연동이 안되면 쓸모가 없는데 ㄱㄷ 
	    eventLimit : true, //얘 기능모륷ㄱ
	    dayMaxEventRows: true, // for all non-TimeGrid views
	    views: {
	    timeGrid: {
	    dayMaxEventRows: 6 // adjust to 6 only for timeGridWeek/timeGridDay
	    	}
	    },
	    eventSources: [
	    {
	          googleCalendarId: 'vkfkswjs26@gmail.com',
	          className: 'vkfkswjs26',
	          color: '#be5683', //rgb,#ffffff 등의 형식으로 할 수 있어요.
	          textColor: 'white'
	        },
	      {
	          googleCalendarId: 'addressbook#contacts@group.v.calendar.google.com',
	          className: 'addressbook',
	            color: '#204051',
	            //textColor: 'black' 
	        },
	      {
	          googleCalendarId: 'ko.south_korea#holiday@group.v.calendar.google.com',
	          className: 'ko.south_korea',
	            color: '#3b6978',
	            textColor: 'white' 
	        }
	    ]
	  });
	  calendar.render();
	});

</script>
<title>Insert title here</title>
<style>

.add-button {
	position: absolute;
	top: 1px;
	right: 230px;
	background: #2C3E50;
	border: 0;
	color: white;
	height: 35px;
	border-radius: 3px;
	width: 157px;
	
	}
	
#calendar{
   width:60%;
   margin:20px auto;
}

</style>
</head>
<body>
    <div id='calendar'></div>
</body>
</html> 