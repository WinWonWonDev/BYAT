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
  	      selectable: true,
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
            color : 'gray',
            backgroundColor: "#483D8B"
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
			obj.allDay = allEvent[i]._def.adllDay //하루종일 이벤트인지 아닌지 */
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
</head>
<body>
	<div id='external-events'>
		<button id="saveButton" style="cursor:pointer;" onclick="allSave();" >일정 저장</button>	
	</div>

	<div id="calendarDiv">
		<div id='calendar'></div>
	</div>

	<c:if test="${ sessionScope.loginMember.name eq '관리자' }">
		<select id="selectBox" name="selectBox" onchange="moveCalendarByMember(this.value);">
			<!-- 옵션이 생기는 곳 -->
		</select>
	</c:if>
	
<script>

console.log("여기 클릭용");
		const selectBoxDiv = document.getElementById("selectBoxDiv");
		const select = document.getElementById("selectBox");
		const option = document.createElement("option");

		function moveCalendarByMember(value) {
			
			console.log("되긴되냐?" + value);
			console.log("되긴되냐?");
			$.ajax({
				url : "/byat/calendar/movecalendarbymember",
				method : "get",
				data : {"memberNoForMove":value},
				successs : function(data, status, xhr) {
					alert("성공");
				},
				error : function(error) {
					alert("에러 발생... " + error)
				}
				
			});
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

				for(i = 0; i < data.length; i++) { //여러개가 담겻을 테니 
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