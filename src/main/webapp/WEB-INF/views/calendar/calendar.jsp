<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../common/manubar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<meta charset="UTF-8">
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Insert title here</title>
<style>
html {
   width: 100%;	
   height: 98%;
   background: #D7E3FA;
   background-size: cover;
   position: relative;
}

* {
   margin: 0;
   padding: 0;
   box-sizing: border-box;
}

body {
   padding: 0;
   margin: 0;
}

#whiteBoard {
   position: absolute;
   background: white;
   border: 1px solid black;
   top: 15%;
   left: 2%;
   width: 95%;
   height: 84%;
}

.searchCalendar {
   float: right;
   margin-right: 3%;
   margin-top: 2%;
}

.search-area {
   position: absolute;
   top: 1.2%;
   left: 80%;
   margin-left: auto;
   margin-right: auto;
}

.search-input {
   width: 100px;
}

td {
   width: 50px;
   height: 50px;
   text-align: center;
   font-size: 20px;
   font-family: 굴림;
   border: 2px border-color:#3333FF;
   border-radius: 8px; /*모서리 둥글게*/
}

#calendar {
   border: 1px solid black;
   position: absolute;
   top: 7%;
   left: 5%;
   width: 90%;
   height: 85%;
}

.plusButton {
   background: url("/byat/resources/images/plusButton.png") no-repeat;
   position: absolute;
   top: 0%;
   left: 91%;
}

input.img-button {
   width: 32px;
   height: 32px;
   border: none;
   cursor: pointer;
}

#calendarOwner {
   position: absolute;
   top: 2%;
   left: 6%;
   color: gray;
}

.onlyDate {
   text-align: right;
}

.day {
   width: 20px;
   height: 30px;
}

#tbCalendarYM {
   height: 5px;
}

#management-create-modal {
   display: none;
   position: relative;
   width: 100%;
   height: 100%;
   z-index: 1;
}

#management-create-modal2 {
   display: none;
   position: relative;
   width: 100%;
   height: 100%;
   z-index: 1;
}

.modal_content {
   position: absolute;
   top: 10%;
   left: 35%;
   width: 400px;
   height: 440px;
   margin: 20px auto;
   background: #29428C;
   border: 1px solid black;
   border-radius: 25px;
}

#management-create-modal .modal_layer {
   position: fixed;
   top: 0;
   left: 0;
   width: 100%;
   height: 100%;
   background: rgba(0, 0, 0, 0.5);
   z-index: -1;
}

.modal_head {
   margin-top: 5px; margin-left : 25px;
   height: 55px;
   color: white;
   text-align: left;
   font-size: 20px;
   margin-left: 25px;
}

#management-close-btn {
   right: 30%;
   top: 10%;
}

#management-close-btn2 {
   right: 30%;
   top: 10%;
}

#management-create {
   right: 55%;
   top: 10%;
}

.modal_content-box {
   width: 84%;
   height: 75%;
   background: white;
   border-radius: 25px;
   margin-left: 28px;
}

.modal_button {
   width: 100%;
   height: 30%;
   float: right;
   position: relative;
}

.modal_button button {
   background-color: rgb(25, 25, 112);
   color: white;
   text-align: center;
   cursor: pointer;
   width: 80px;
   height: 30px;
   position: absolute;
}

form {
   height: 95%;
   text-align: center;
}

.managementModalInput {
   position: absolute;
   width: 260px;
}

.managementModalDateInput {
   position:absolute;
   width:130px;
}

.start-day {
   top:30%;
   left:15%;
   
}

.end-day {
   top:30%;
   left:48%;

}

.title {
   top: 16%;
   left: 15%;
   height: 47px;
}

.content {
   height: 180px;
   top: 40%;
   left: 15%;
   resize:none;
}

#modal .modal_layer {
   position: fixed;
   top: 0;
   left: 0;
   width: 100%;
   height: 100%;
   background: rgba(0, 0, 0, 0.5);
   z-index: -1;
}




</style>
</head>
<body>
   <div id="whiteBoard">
      <div id="calendarOwner">000의 일정</div>
      <table id="calendar" align="center" border="1"
         style="border-color: black">
         <tr>
            <td><label onclick="prevCalendar()"><</label></td>
            <td align="center" id="tbCalendarYM" colspan="5">yyyy년 m월</td>
            <td><label onclick="nextCalendar()">></label></td>
         </tr>
         <br>
         <div class="searchCalendar">
            <div class="search-area">
               <input class="search-input" type="search">
               <button type="submit">검색</button>
            </div>
         </div>
         <div id="managementButtons">
            <input type="button" class="img-button plusButton" id="plusButton">
         </div>
         <tr>
            <td align="center" class="day"><font color="#F79DC2">일</td>
            <td align="center" class="day">월</td>
            <td align="center" class="day">화</td>
            <td align="center" class="day">수</td>
            <td align="center" class="day">목</td>
            <td align="center" class="day">금</td>
            <td align="center" class="day"><font color="skyblue">토</td>
         </tr>
      </table>

   </div>

   <!-- 캘린더생성 모달창 -->

   <div id="management-create-modal">

      <div class="modal_content">
         <form action="" method="post">
            <div class="modal_head">
               <h3>일정 생성</h3>
            </div>
            <div class="modal_content-box">
               <input type="text" class="managementModalInput title"
                  name="managementTitle" placeholder="Meeting Log Title"> <br>
                      <input type='date' class="managementModalDateInput start-day" name='taskStartday'/>
                      <input type='date' class="managementModalDateInput end-day" name='taskEndday'/> 
                  <br> 
                  <textarea class="managementModalInput content" name="managementNum" rows="13" cols="51" placeholder="상세내용을 입력해주세요"></textarea>
            </div>
            <div class="modal_button">
               <button type="button" id="management-create">Ok</button>
               <button type="button" id="management-close-btn">Cancel</button>
            </div>
         </form>
      </div>
      <div class="modal_layer"></div>
   </div>

   <!-- 캘린더 상세 보기 모달창  -->
   <div id="management-create-modal2">

      <div class="modal_content">
         <form action="" method="post">
            <div class="modal_head">
               <h3>일정 조회/수정</h3>
            </div>
            <div class="modal_content-box">
               <input type="text" class="managementModalInput title" name="managementTitle" placeholder="Meeting Log Title"> 
               <br>
                   <input type='date' class="managementModalDateInput start-day" name='taskStartday'/>
                   <input type='date' class="managementModalDateInput end-day" name='taskEndday'/> 
               <br> 
               <textarea class="managementModalInput content" name="managementNum" rows="13" cols="51" placeholder="상세내용을 입력해주세요"></textarea>
            </div>
            <div class="modal_button">
               <button type="button" id="management-create">Ok</button>
               <button type="button" id="management-close-btn2">Cancel</button>
            </div>
         </form>
      </div>
      <div class="modal_layer"></div>
   </div>
   


   <script>
       
      document.getElementById("plusButton").onclick = function() {
          document.getElementById("management-create-modal").style.display="block";
      }

      document.getElementById("management-close-btn").onclick = function() {
           document.getElementById("management-create-modal").style.display="none";
       }
   
   
   
      var today = new Date();//오늘 날짜//내 컴퓨터 로컬을 기준으로 today에 Date 객체를 넣어줌
      var date = new Date();//today의 Date를 세어주는 역할
      
      function prevCalendar() {//이전 달
      // 이전 달을 today에 값을 저장하고 달력에 today를 넣어줌
      //today.getFullYear() 현재 년도//today.getMonth() 월  //today.getDate() 일 
      //getMonth()는 현재 달을 받아 오므로 이전달을 출력하려면 -1을 해줘야함
       today = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());
       buildCalendar(); //달력 cell 만들어 출력 
      }

      function nextCalendar() {//다음 달
          // 다음 달을 today에 값을 저장하고 달력에 today 넣어줌
          //today.getFullYear() 현재 년도//today.getMonth() 월  //today.getDate() 일 
          //getMonth()는 현재 달을 받아 오므로 다음달을 출력하려면 +1을 해줘야함
           today = new Date(today.getFullYear(), today.getMonth() + 1, today.getDate());
           buildCalendar();//달력 cell 만들어 출력
      }
      function buildCalendar(){//현재 달 달력 만들기
          var doMonth = new Date(today.getFullYear(),today.getMonth(),1);
          //이번 달의 첫째 날,
          //new를 쓰는 이유 : new를 쓰면 이번달의 로컬 월을 정확하게 받아온다.     
          //new를 쓰지 않았을때 이번달을 받아오려면 +1을 해줘야한다. 
          //왜냐면 getMonth()는 0~11을 반환하기 때문
          var lastDate = new Date(today.getFullYear(),today.getMonth()+1,0);
          //이번 달의 마지막 날
          //new를 써주면 정확한 월을 가져옴, getMonth()+1을 해주면 다음달로 넘어가는데
          //day를 1부터 시작하는게 아니라 0부터 시작하기 때문에 
          //대로 된 다음달 시작일(1일)은 못가져오고 1 전인 0, 즉 전달 마지막일 을 가져오게 된다
          var tbCalendar = document.getElementById("calendar");
          //날짜를 찍을 테이블 변수 만듬, 일 까지 다 찍힘
          var tbCalendarYM = document.getElementById("tbCalendarYM");
          //테이블에 정확한 날짜 찍는 변수
          //innerHTML : js 언어를 HTML의 권장 표준 언어로 바꾼다
          //new를 찍지 않아서 month는 +1을 더해줘야 한다. 
           tbCalendarYM.innerHTML = today.getFullYear() + "년 " + (today.getMonth() + 1) + "월"; 

           /*while은 이번달이 끝나면 다음달로 넘겨주는 역할*/
          while (tbCalendar.rows.length > 2) {
          //열을 지워줌
          //기본 열 크기는 body 부분에서 2로 고정되어 있다.
                tbCalendar.deleteRow(tbCalendar.rows.length-1);
                //테이블의 tr 갯수 만큼의 열 묶음은 -1칸 해줘야지 
              //30일 이후로 담을달에 순서대로 열이 계속 이어진다.
           }
           var row = null;
           row = tbCalendar.insertRow();
           //테이블에 새로운 열 삽입//즉, 초기화
           var cnt = 0;// count, 셀의 갯수를 세어주는 역할
          // 1일이 시작되는 칸을 맞추어 줌
           for (i=0; i<doMonth.getDay(); i++) {
           /*이번달의 day만큼 돌림*/
                cell = row.insertCell();//열 한칸한칸 계속 만들어주는 역할
                cnt = cnt + 1;//열의 갯수를 계속 다음으로 위치하게 해주는 역할
           }
          /*달력 출력*/
           for (i=1; i<=lastDate.getDate(); i++) { 
           //1일부터 마지막 일까지 돌림
                cell = row.insertCell();//열 한칸한칸 계속 만들어주는 역할
                cell.innerHTML = "<p class='onlyDate'>" + i + "<div>&nbsp;</div>" + "<div>&nbsp;</div>";//셀을 1부터 마지막 day까지 HTML 문법에 넣어줌
                cnt = cnt + 1;//열의 갯수를 계속 다음으로 위치하게 해주는 역할
                
            if (cnt % 7 == 1) {/*일요일 계산*/
                //1주일이 7일 이므로 일요일 구하기
                //월화수목금토일을 7로 나눴을때 나머지가 1이면 cnt가 1번째에 위치함을 의미한다
              cell.innerHTML = "<p style='text-align:right'>" + "<font color=#F79DC2>" + i + "<div>&nbsp;</div>" + "<div>&nbsp;</div>";
              //1번째의 cell에만 색칠
          }    
            if (cnt%7 == 0){/* 1주일이 7일 이므로 토요일 구하기*/
                //월화수목금토일을 7로 나눴을때 나머지가 0이면 cnt가 7번째에 위치함을 의미한다
                cell.innerHTML = "<p style='text-align:right'>" + "<font color=skyblue>" + i +  "<div>&nbsp;</div>" + "<div>&nbsp;</div>";
                //7번째의 cell에만 색칠
                 row = calendar.insertRow();
                 //토요일 다음에 올 셀을 추가
            }
            /*오늘의 날짜에 노란색 칠하기*/
            if (today.getFullYear() == date.getFullYear()
               && today.getMonth() == date.getMonth()
               && i == date.getDate()) {
                //달력에 있는 년,달과 내 컴퓨터의 로컬 년,달이 같고, 일이 오늘의 일과 같으면
              cell.bgColor = "#FAF58C";//셀의 배경색을 노랑으로 
             }
           }
      }
      
      
      <!-- 캘린더 상세 보기  자바스크립트 (ㄱㄷㄱㄷ)-->
      //만약에  그.. 각 .. 네모를 클릭을 하면 
      //그... 모달창이 뜨도록.. ; (너무어렵다..생각해보자)
      //if(onclick누른게 == date.getDate라면) { document.getElementById("네브바아이딩 ㅇ").style.display="block"}
         
      </script>

   <script language="javascript" type="text/javascript">
    buildCalendar();//
</script>

<script>

      <!-- 캘린더 일정 보기 모달창용 스크립트 -->

</script>

</body>
</html>