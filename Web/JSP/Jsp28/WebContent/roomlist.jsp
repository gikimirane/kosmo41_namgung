<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>채팅 참여</title>
 	<meta charset="utf-8">
 	<!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<!--  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>-->
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

</head>
<style>
	.aaa{
		border-width:1px;
		border-style:solid;       /* solid */
		border-color:black;
	}
	
</style>
<jsp:include page="./home_a.jsp"/>
<body>
	<div class="container">
	<div class="row">
		<div class="col aaa">
		<button type="button" onclick="join();">채팅시작</button>
		<button type="button" onclick="send();">메시지보내기</button>
		<button type="button" onclick="closeSocket();">채팅종료</button>
		<button type="button" onclick="eraser();">대화창지우기</button>
		<button type="button" data-toggle="modal" href="#myModal">방만들기</button>
		<button type="button" onclick="listview">전체대화목록</button>
		</div>
	</div>
		<div class="row aaa">
		<div class="col aaa">사용자 아이디 : <input type=text id="chatname" name="chatname"></div>
		
		</div>
	
	<div class="row aaa">
	<div class="col aaa">메시지 입력 : <input type="text" id ="messageinput"/></div>
	</div>
	<div class="row aaa">
	<div class="col aaa" id="messages" style="height:300px;overflow:auto;"></div>
	</div>
		<div class="modal fade" id="myModal" role="dialog">
		 		<div class="modal-dialog">
		  
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header" style="text-align:right">
		        <h4 class="modal-title">공개방/비공개방?</h4>
		         <button type="button" class="close" data-dismiss="modal">×</button>
		      </div>
		    
		     <form>
		      <div class="modal-body" style="text-align:center">
		        <p>정원을 입력하세요.</p>
		        <input type="text" name="limit" id="limit">
		       <p/>
		        <p>방 타입을 선택하세요.</p>
			<input type="radio" name="locktype" id="locktype" value="공개" onClick="this.form.roompw.disabled=true"checked>공개
			<input type="radio" name="locktype" id="locktype" value="비공개" onClick="this.form.roompw.disabled=false">비공개
			<br>
			<input type="text" name="roompw" id="roompw" disabled><br>
			</form>
		      </div>        
		      <div class="modal-footer">
		        <input type="button" class="btn btn-default" onclick="checkform()" value="방만들기" data-dismiss="modal">
		      </div>
		    </div>
		  </div>
		</div>
		<div id="listarea" name="listarea"></div>
	</div>
	
	
	<div class="container" id="listtable" name="listtable">
		<table class="table table-hover">
			<thead>
			<tr>
				<th scope="col">방번호</th>
				<th scope="col">입장제한</th>
				<th scope="col">방공개타입</th>
				<th scope="col">방장</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${roomlist}" var="dto1">
			<tr>
				<td><button type="button" onclick="enterRoom(${dto1.rno})">${dto1.rno}</button></td>
				<td>${dto1.user_limit}</td>				
				<td>${dto1.open_type}</td>
				<td>${dto1.room_owner}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table> 
	</div>

   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
   <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</body>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
var webSocket;
var messages = document.getElementById("messages");
var id="";	
//커넥트 눌렀을 때 오는 부분

function openSocket(){
	//이미 열려있는지 체크하는영역
	if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED){
		writeResponse("이미 접속중입니다.");
		return;
	}
	//이 주소에 접속~ 나중에 domain name으로 바꾸면 됨 AWS
	webSocket = new WebSocket("ws://localhost:8081/Jsp28/websocketendpoint2");
	//on은 ~할때, 오픈되었을 때를 의미

	webSocket.onopen = function(event){
		
		if(event.data == undefined){
			return;
		}			
		
		writeResponse(event.data);
		WebSocket.send(id+"|");
		session.setAttribute("chatname",id);
	};
				
	//서버로 부터 나에게 메시지가 왔을때
	//event.data란 이벤트가 일어났을때 나한테 온 메시지를 의미
	webSocket.onmessage = function(event){
		writeResponse(event.data);
	};
	
	//소켓이 종료되었을 때
	webSocket.onclose = function(event){
		writeResponse("채팅을 종료합니다.");
		
		id = document.getElementById("chatname").value;
		var data = "id="+id;
		
		$.ajax({
			url : './deleteroom.chat',
			type : 'POST',
			data : data,
			dataType : 'json',
			success : function(json){
				var results = eval(json);
				if(results[0].results == "fail"){
					alert(results[0].desc);
				}else {
					alert("채팅 종료 시 "+id+"님이 방장인 방은 삭제됩니다.");
				}
			}
		});
		var chatname = document.getElementById('chatname');
		chatname.disabled = false;
	};
	
}
function enterRoom(rno){
	//방넘버랑, 아이디랑 받아야 돼
	id=document.getElementById("chatname");
	id = id.value;
	data = "id="+id+"&rno="+rno;
	alert("방번호 : "+data);
	
	$.ajax({
		url : './Entrance.chat',
		type : 'POST',
		data : data,
		dataType : 'json',
		success : function(json){
			var results = eval(json);
			if(results[0].results == "fail"){
				alert(results[0].desc);
			}else {
				alert("요청하신 방으로 입장했습니다.");
				WebSocket.send(id+"님이 입장했습니다.");
			}
		}
	}); 
}
function join(){
	alert("join");
	var id = document.getElementById("chatname").value;
	
	if(id==null || id==""){
		alert("Enter your Name");
		return;
	}
	
	alert(id);
	
	var data = "id="+id;
	
	$.ajax({
		url : './join.chat',
		type : 'POST',
		data : data,
		dataType : 'json',
		success : function(json){
			var results = eval(json);
			if(results[0].results == "fail"){
				alert(results[0].desc);
				
			}else {
				alert(id+"님으로 접속합니다.");
				var chatname = document.getElementById('chatname');
				chatname.disabled = true;
				
				openSocket();		
			}
		}
	});
}
function send(){
	var text = document.getElementById("messageinput").value;

	webSocket.send(id+"|"+text);
	//send는 내가 보낸거 서버한테
	
	document.getElementById("messageinput").value="";
	document.getElementById("messageinput").focus();
	//text상자 값 공란으로 바꾸고, focus를 두기
}
function closeSocket(){
	id = document.getElementById("chatname").value;
	var data = "id="+id;
	
	$.ajax({
		url : './exit.chat',
		type : 'POST',
		data : data,
		dataType : 'json',
		success : function(json){
			var results = eval(json);
			if(results[0].results == "fail"){
				alert(results[0].desc);
			}else {
				alert(id+"님으로 채팅 접속을 종료합니다.");
				webSocket.close();
			}
		}
	});
	
}

function checkform(){
	var id = document.getElementById("chatname").value;
	var lock = document.getElementsByName('locktype');
	var limit=document.getElementById("limit").value;
	document.getElementById("limit").value ="";
	var locktype; // 여기에 선택된 radio 버튼의 값이 담기게 된다.
	var pw;
	
	for(var i=0; i<lock.length; i++) {
	    if(lock[i].checked) {
	        locktype = lock[i].value;
	        alert("locktype:"+locktype);
	    }
	}
				
	if(locktype=="비공개"){
		pw=document.getElementById("roompw").value;
		
		if(pw==null||pw==""||pw=="undefined"||pw.length==0){
			alert("비공개 방에는 비밀번호가 필요합니다.");
			return;
		}
	}
	if(!($.isNumeric(limit))){
		alert("정원은 숫자로만 입력하세요.");
		return;
	}else {
		this.newRoom(id,locktype,limit,pw);
		
	}
}
function newRoom(id,locktype,limit,pw){
	alert("newroom");
	var id = id
	var locktype=locktype;
	var limit=limit;
	var pw=pw;
							
	alert("id : "+id);
	alert("locktype : "+locktype);
	alert("pw : "+pw);
	var data = "id="+id+"&locktype="+locktype+"&limit="+limit+"&roompw="+pw;
	
	alert("data = "+data);
	$.ajax({
		url : './newroom.chat',
		type : 'POST',
		data : data,
		dataType : 'json',
		success : function(json){
			var results = eval(json);
			if(results[0].results == "fail"){
				alert(results[0].desc);
			}else {
				alert("방을 만들었습니다.");
			}
		}
	});
}

//서버가 나한테 데이터가 왔을 때
function writeResponse(text){
	//대화가 길어지면 스크롤이 생기게
	//대화가 또 길어지만 첫번째 줄 자르고 마지막줄에 추가하고..
	//추가 필요
	messages.innerHTML += "<br/>"+text;
}

function eraser(){
	messages.innerHTML = "";
}
</script>
</html>