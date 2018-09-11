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
<script>
$('#invitation').click(function()
{
    $('#invitation').modal('show');
});
</script>
<body>
	<div class="container">
	<div class="row">
		<div class="col aaa">
			<button type="button" onclick="join();">채팅시작</button>
			<button type="button" onclick="send();">메시지보내기</button>
			<button type="button" onclick="closeSocket();">채팅종료</button>
			<button type="button" onclick="eraser();">대화창지우기</button>
			<button type="button" data-toggle="modal" href="#myModal">방만들기</button>
			<button type="button" onclick="listview()">전체대화목록</button>
			<button type="button" onclick="enterroom0()">방나가기</button>
			<button type="button" onclick="alluserlist()">전체사용자</button>
			<button type="button" onclick="waitinglist()">대기실사용자</button>
			<button type="button" onclick="nowroomlist()">같은방사용자</button>
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
	
	</div>
		
	<div class="container" id="listtable" name="listtable">
		<div class="row aaa">
		<div class="col aaa" id="servermsg" style="height:300px;overflow:auto;"></div>
		</div>
		
		<div class="row aaa">
		<div class="col aaa"><input type="text" id="roomno" name="roomno">&nbsp;&nbsp;
		<input type="button" value="방이동" onclick="enterRoom();">
		</div>
		</div>
		
		<div class="row aaa">
		<div class="col aaa" id="listarea" name="listarea"></div>
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

   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
   <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</body>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
var webSocket;
var messages = document.getElementById("messages");
var id="";	
var whisperSW=false;
var whisperName="";

function openSocket(){
	var chatname = document.getElementById('chatname');
	chatname.disabled = true; 
	id = document.getElementById("chatname").value;
	
	if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED){
		writeResponse("이미 접속중입니다.");
		return;
	}
	
	webSocket = new WebSocket("ws://localhost:8081/Jsp28/websocketendpoint2");
	
	webSocket.onopen = function(event){
		webSocket.send(id+"|"+"!입장@");
		if(event.data == undefined){
			return;
		}	
		writeResponse(event.data);
		
	};
		
	webSocket.onmessage = function(event){
		
		writeResponse(event.data);
	};
	
	webSocket.onclose = function(event){
		var chatname = document.getElementById('chatname');
		chatname.disabled = false;
		eraser();
		document.getElementById('roomno').value="";
		document.getElementById('messageinput').value="";
		writeResponse("접속을 종료합니다.");
	};
}

function waitinglist(){
	webSocket.send(id+"|"+"!대기실사용자@");
}

function nowroomlist(){
	webSocket.send(id+"|"+"!같은방사용자@");
}


function join(){
	var chatname = document.getElementById('chatname').value;
	if(chatname==""||chatname==null){
		alert("이름이 공란입니다. 이름을 입력하세요.");
	}else {
		var data = "id="+chatname;
		
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
					var chatname = document.getElementById('chatname');
					chatname.disabled = true;
					openSocket();		
				}
			}
		});
	}
}


function alluserlist(){
	webSocket.send(id+"|"+"!전체사용자@");
}
function listview(){
	webSocket.send(id+"|"+"!방목록@");
}
function enterRoom(){
	var rno = document.getElementById('roomno').value;
	alert(rno);
	webSocket.send(id+"|"+"!방입장@"+rno);
	document.getElementById('roomno').value="";
}


function send(){
	var text = document.getElementById("messageinput").value;
	
	if(text.startsWith("/to")){
		var a = text;
		var results = a.match(/ /g); 
		
		if(results != null) {
		    if(results.length==1){
		    	var index = text.indexOf(" ");
		    	var inputname = text.substring(index,text.length);
		    	if(whisperSW){
		    		if(inputname==whisperName){
		    			whisperSW = !whisperSW;
		    			whisperName="";
		    		}else {
		    			alert("고정귓속말을 끄려면 /to 귓속말대상 형식으로 입력하세요.");
		    		}
		    	}else {
		    		whisperSW = !whisperSW;
			    	whisperName=text.substring(index,text.length);
			    	alert(whisperName.length);
		    	}
		    	if(whisperName==" "){
		    		alert("귓속말은 /to 상대방이름 전달메시지 형식으로 기재해주세요.");
		    		whisperSW=!whisperSW;
		    	}		    	
		    	//이름이 공백일 때 예외 해야해
		    }else {
		    	webSocket.send(id+"|!귓속말@"+text);
		    }
		}else {
			alert("귓속말은 /to 상대방이름 전달메시지 형식으로 기재해주세요.");
		}
		
	}else if(whisperSW){
		alert(id+"|!귓속말@"+"/to"+whisperName+" "+text);
		webSocket.send(id+"|!귓속말@"+"/to"+whisperName+" "+text);
		
	}else {
		webSocket.send(id+"|!메세지@"+text);
	}
	 
	clear();
}
function closeSocket(){
	webSocket.close();
}

function enterroom0(){
	
	webSocket.send(id+"|"+"!방입장@0");
	clear();
}

function checkform(){
	var id = document.getElementById("chatname").value;
	var lock = document.getElementsByName('locktype');
	var limit=document.getElementById("limit").value;

	var locktype; 
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
	}
		alert(id);
		alert(locktype);
		alert(limit);
		webSocket.send(id+"|!방만들기@,"+locktype+","+limit+","+pw);
		clear();
}

function writeResponse(text){
	
	if(text.startsWith('> !')){
		text = text.replace("!","");
		servermsg.innerHTML += "<br/>"+text;
	}else {
		messages.innerHTML += "<br/>"+text;
	}
}
function clear(){
	document.getElementById("roompw").value ="";
	document.getElementById("limit").value ="";
	document.getElementById("messageinput").value="";
	document.getElementById("messageinput").focus();	
}
function eraser(){
	messages.innerHTML = "";
	servermsg.innerHTML="";
}
</script>
</html>