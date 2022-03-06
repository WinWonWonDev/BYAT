<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	
	console.log("ㅇ? : " + document.getElementById("memberNo"));
	var calendar = null;

	$(document).ready(function() {
	  var Calendar = FullCalendar.Calendar;
	  var Draggable = FullCalendar.Draggable;
	
	  var containerEl = document.getElementById('external-events');
	  var calendarEl = document.getElementById('calendar');
	  var checkbox = document.getElementById('drop-remove');
	
	  // initialize the external events
	  // -----------------------------------------------------------------
	
	  new Draggable(containerEl, {
	    itemSelector: '.fc-event',
	    eventData: function(eventEl) {
	      return {
	        title: eventEl.innerText
	      };
	    }
	  });
	
	  // initialize the calendar
	  // -----------------------------------------------------------------
	
	   calendar = new Calendar(calendarEl, {
		  initialView: 'dayGridMonth',
		  locale: 'ko', //한국어 나오게 하는 것 !
	    headerToolbar: {
	      left: 'prev,next today',
	      center: 'title',
	      right: 'dayGridMonth,timeGridWeek,timeGridDay'
	    },
	    editable: true,
	    droppable: true, // this allows things to be dropped onto the calendar
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
			
			  //var date = curDate.toISOString().split("T")[0];        
			
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
			console.log("data:  " + data);
			console.log("음?done은 옴");
		}) //인제 저장을 했으니까 그 ...
		.fail(function(request, status, error) {
			alert("에러발생! : " + error);
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
	position:absolute;
	top:100%;
	background-color:rgb(25,25,112);
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
	left:81%;
	top:10%;

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
	<input type="hidden" name="memberNo" id="memberNo" value="${ sessionScope.loginMember.no }">
	<input type="hidden" name="memberName" id="memberName" value="${ sessionScope.loginMember.name }">
	<input type="hidden" name="permitCode" id="permitCode" value="${ sessionScope.loginMember.permitCode }">


	<div id='external-events'>
		<p>
			<strong>드래그하여 일정을 추가해주세요</strong>
		</p>
		<button id="saveButton" style="cursor:pointer;" onclick="allSave();" >일정 저장</button>	

		<div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main'>일정 추가하기</div>
		</div>

		<p>
			<input type='checkbox' id='drop-remove' /> <label for='drop-remove'>드래그 앤 드롭후 제거 </label>
		</p>
	</div>

	<div id="calendarDiv">
		<div id='calendar'></div>
	</div>

</body>
</html>