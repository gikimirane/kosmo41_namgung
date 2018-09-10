<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
<%
	String id = request.getParameter("id");
%>
<div class="container">
	<div class="row aaa">
		<button type="button" onclick="join();">채팅시작</button>
		<button type="button" onclick="send();">메시지보내기</button>
		<button type="button" onclick="closeSocket();">채팅종료</button>
		<button type="button" onclick="eraser();">대화창지우기</button>
	</div>
	<div class="row aaa">
		<div class="col aaa">메시지 입력 : <input type="text" id ="messageinput"/></div>
	</div>
	<div class="row aaa">
	<div class="col aaa" id="messages" style="height:300px;overflow:auto;"></div>
	</div>
	<div class="row aaa">
	<div class="col aaa">
	아이디 : <input type="text" value=<%=id %> disabled>
	</div>
	</div>
</div>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
   <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</body>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">

	//이 주소에 접속~ 나중에 domain name으로 바꾸면 됨 AWS
	webSocket = new WebSocket("ws://localhost:8081/Jsp28/websocketendpoint2");
	//on은 ~할때, 오픈되었을 때를 의미

	webSocket.onopen = function(event){
		
		if(event.data == undefined){
			return;
		}			
		
		writeResponse(event.data);
		WebSocket.send(id+"|");
	};
				
	//서버로 부터 나에게 메시지가 왔을때
	//event.data란 이벤트가 일어났을때 나한테 온 메시지를 의미
	webSocket.onmessage = function(event){
		writeResponse(event.data);
	};
	
	//소켓이 종료되었을 때
	webSocket.onclose = function(event){
		writeResponse("채팅을 종료합니다.");
		
		var data = "id='"+<%=id%>+"'";
		
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
	
function join(){
	alert("join");
		
	var data = "id='"+<%=id%>+"'";
	
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
	
	webSocket.send(<%=id%>+"|"+text);
	//send는 내가 보낸거 서버한테
	
	document.getElementById("messageinput").value="";
	document.getElementById("messageinput").focus();
	//text상자 값 공란으로 바꾸고, focus를 두기
}
function closeSocket(){
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
				document.location.href="./chatRoom.jsp"
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