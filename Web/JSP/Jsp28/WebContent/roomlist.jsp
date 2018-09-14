<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.study.*" %>

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
		border-width:0.5px;
		border-style:solid;       /* solid */
		border-color:lightgray;
	}
	
	.*{
		color:lightgray;
		
	}
	.start{
		margin:0;
		padding:0;
	}
	
</style>
<jsp:include page="./home_a.jsp"/>

<body>
	<div class="container">
	
	
		<div class="row aaa" style="font-size:13px">
			<div class="col-4" >사용자 아이디 > <input type=text id="chatname" name="chatname" onkeyup="enterkey1()"></div>
			<div class="col-7" id="chatstart" style="display:block"><button class="btn btn-outline-secondary btn-sm" id="chatstart" type="button" onclick="join();" >채팅시작</button></div>
			<div class="col-7" id="chatexit" style="display:none;"><button class="btn btn-outline-secondary btn-sm" type="button" onclick="closeSocket();" >채팅종료</button></div>
			<div class="col aaa btn-outline-secondary btn-sm start" id="myroomcheck" style="text-align:center;"></div>
		</div>

		
	<div class="row" id="messagearea" style="display:none">
		<div class="col aaa" style="font-size:13px">메시지 입력 > <input type="text"  onkeyup="enterkey()" id ="messageinput">&nbsp;
			<button class="btn btn-outline-secondary btn-sm" type="button" onclick="send();">메시지보내기</button>&nbsp;
			<button class="btn btn-outline-secondary btn-sm" type="button" onclick="noticeAll()">전체공지</button>&nbsp;
			<button class="btn btn-outline-secondary btn-sm" type="button" onclick="eraser();">대화창지우기</button>&nbsp;
			"[/to 사용자 메시지 + 메시지보내기] > 1회 귓속말, [/to 사용자 + 메시지보내기] > 고정 귓속말"
		</div>
		
	</div>
	
	<div class="row">
		<div class="col" id="roombtn" style="display:none">
					
			<button class="btn btn-outline-secondary btn-sm" type="button" data-toggle="modal" href="#myModal">방만들기</button>&nbsp;
			<button class="btn btn-outline-secondary btn-sm" type="button" onclick="enterroom0()">방나가기</button>&nbsp;
			<button class="btn btn-outline-secondary btn-sm" type="button" onclick="listview()">전체방목록/방입장</button>&nbsp;
			<button class="btn btn-outline-secondary btn-sm" type="button" onclick="alluserlist()">전체사용자/쪽지</button>&nbsp;
			<button class="btn btn-outline-secondary btn-sm" type="button" onclick="waitinglist()">대기실사용자/초대</button>&nbsp;
			<button class="btn btn-outline-secondary btn-sm" type="button" onclick="nowroomlist()">같은방사용자/강퇴/방장인계</button>&nbsp;
			<button class="btn btn-outline-secondary btn-sm" type="button" onclick="blacklist()">친구차단/나쁜말추가</button>&nbsp;
		</div>
	</div>
	
	<div class="clearfix visible-xs-block"></div>
	<div class="row">
		<div class="radio">
	    	<input type="radio" name="transoption" id="transoption1" value="0" checked>한국어 / KOREAN ONLY  &nbsp;&nbsp;&nbsp;&nbsp;
		    <input type="radio" name="transoption" id="transoption2" value="1">한국어 > 영어 / KOREAN > ENGLISH  &nbsp;&nbsp;&nbsp;&nbsp;
		    <input type="radio" name="transoption" id="transoption3" value="2">영어 > 한국어 / ENGLISH > KOREAN &nbsp;&nbsp;&nbsp;&nbsp;
		</div>
	</div>
	<div class="row">
	<div class="col-7 aaa" id="messages" name="messages" style="height:300px;overflow:auto;background-color:#EAEBE6"></div>
	<div class="col-2 aaa" id="roomUser" style="height:300px;overflow:auto;background-color:#EAF6F6"></div>
	<div class="col-3 aaa" id="blackword" style="height:300px;overflow:auto;background-color:#F0DACF"></div>
	</div>
	
	</div>
		
	<div class="container" id="listtable" name="listtable">
		<div class="row">
		<div class="col-5 aaa" id="servermsg" style="height:300px;overflow:auto;background-color:#F0DACF"></div>
		<div class="col-3 aaa" id="alluser" style="height:300px;overflow:auto;background-color:#F3E9E8"></div>
		<div class="col-4 aaa" id="waitingUser" style="height:300px;overflow:auto;background-color:#DDE8E4"></div>
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
		<input type="radio" name="locktype" id="locktype" value="게임" onClick="this.form.roompw.disabled=true; this.form.limit.disabled=true">게임
		<br>
		<input type="password" name="roompw" id="roompw" disabled><br>
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
var gameSW = false;
var gameName="";

/* checkmyroom(); <-얘를 어디다가 넣어야 할까.. */

$(window).on("load", function(){
	document.getElementById("chatname").focus()
});

function enterkey() {
    if (window.event.keyCode == 13) {
		send();
    }
}

function enterkey1() {
    if (window.event.keyCode == 13) {
    	join();
    }
}
function enterKey2(){
	if (window.event.keyCode == 13) {
    	blacklist();
    }
}

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
		showbutton();		
	};
		
	webSocket.onmessage = function(event){
		myroomcheck.innerHTML ="";
		writeResponse(event.data);
	};
	
	webSocket.onclose = function(event){
		var chatname = document.getElementById('chatname');
		chatname.disabled = false;
		eraser();
		var div = document.getElementById('roombtn');
	    var div1 = document.getElementById('chatexit');
	    var div2 = document.getElementById('messagearea');
	    var div3 = document.getElementById('chatstart');
	    
	    
	    if ( div3.style.display = 'block') {
	        div.style.display = 'none';
	        div1.style.display = 'none';
	        div2.style.display = 'none';
	      	div3.style.display = 'block';
	    }
		writeResponse("접속을 종료합니다.");
	};
}

function checkmyroom(){
	myroomcheck.innerHTML ="";
	roomUser.innerHTML ="";
	id = document.getElementById("chatname").value;
	var data = "id="+id;
	
	$.ajax({
		url : './checkmyroom.chat',
		type : 'POST',
		data : data,
		dataType : 'json',
		error : function(xhr,status,error,json){
			alert(error);
			alert("에러다");
		},
		success : function(json){
			console.log(json);
			var results = eval(json);
			var myroom = results[0].myroom;
			myroomcheck.innerHTML ="";
			$("#myroomcheck").append(myroom);
		}
	});
}


function noticeAll(){
	var text=document.getElementById('messageinput').value;
	webSocket.send(id+"|!전체공지@"+text);
}

function waitinglist(){
	waitingUser.innerHTML="";
	$.ajax({
		url : './waitinglist.chat',
		type : 'POST',
		dataType : 'json',
		error : function(xhr,status,error,json){
			alert(error);
			alert("에러다");
		},
		success : function(json){
			console.log(json);
			var results = eval(json);
			var html = "<tr><td>대기실 사용자 목록</td></tr>";
				html += "<tr><td><button type=\"button\" onclick=\"invitation()\" class=\"btn btn-outline-secondary btn-sm\">초대</button></td></tr>";
			for(var i=0;i<results.length;i ++) {
				html += "<tr>";
				html += "<td><input type=\"radio\" id=\"waiting\" name=\"waiting\" value=\""+results[i].user+"\">"+results[i].user + "</td>";
				html += "</tr>";
			}
			$("#waitingUser").append(html);

		}
	});
}



function nowroomlist(){
	roomUser.innerHTML ="";
	var id = document.getElementById('chatname').value;
	var data = "id="+id;
	
	$.ajax({
		url : './roomUserlist.chat',
		type : 'POST',
		data : data,
		dataType : 'json',
		error : function(xhr,status,error,json){
			alert(error);
			alert("에러다");
		},
		success : function(json){
			console.log(json);
			var results = eval(json);
			var html = "<tr><td>같은방 사용자 목록</td></tr>";
				html += "<tr><td><button type=\"button\" onclick=\"outofroom()\" class=\"btn btn-outline-secondary btn-sm\">강퇴&nbsp;&nbsp;</button><button type=\"button\" class=\"btn btn-outline-secondary btn-sm\" onclick=\"changeowner()\">방장인계</button></td></tr>";
			for(var i=0;i<results.length;i ++) {
				html += "<tr>";
				html += "<td><input type=\"radio\" id=\"nowroom\" name=\"nowroom\" value=\""+results[i].user+"\">"+results[i].user + "</td>";
				html += "</tr>";
			}
			$("#roomUser").append(html);
		}
	});
}

function outofroom(){
	
	var nowroom = document.getElementsByName('nowroom');
	var id = document.getElementById("chatname").value;
	
	for(var i=0;i<nowroom.length; i++) {
	    if(nowroom[i].checked) {
	        if(nowroom[i].value==id){
	        	alert("본인을 선택할 수 없습니다.");
	        }else {
	        	user = nowroom[i].value;
		        alert("user:"+user);
		        roomUser.innerHTML ="";
	        }
	    }
	}

	myroomcheck.innerHTML ="";
	webSocket.send(id+"|"+"!강제퇴장@"+user);
	
}

function changeowner(){
	
	var roomuser = document.getElementsByName('nowroom');
	var id = document.getElementById("chatname").value;
	
	for(var i=0;i<roomuser.length; i++) {
	    if(roomuser[i].checked) {
	        if(roomuser[i].value==id){
	        	alert("본인을 선택할 수 없습니다.");
	        }else {
	        	user = roomuser[i].value;
	        }
	    }
	}
	alert("선택한 사람 : "+user);
	var data ="id="+id+"&user="+user;
	$.ajax({
		url : './ownerchange.chat',
		type : 'POST',
		data : data,
		dataType : 'json',
		success : function(json){
			var results = eval(json);
			if(results[0].results == "ok"){
				webSocket.send(id+"|"+"!방장승계@"+user);	
				alert(results[0].desc);
				roomUser.innerHTML ="";
			}else if(results[0].results=="fail1"){
				alert(results[0].desc);
			}else if(results[0].results=="fail2"){
				alert(results[0].desc);
			}
		}
	}); 
	myroomcheck.innerHTML ="";
}

function join(){
	var chatname = document.getElementById('chatname').value;
	
	if(chatname==""||chatname==null){
		alert("이름이 공란입니다. 이름을 입력하세요.");
	}
	else {
		var data = "chatname="+chatname;
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
					
				    var div = document.getElementById('roombtn');
				    var div1 = document.getElementById('chatexit');
				    var div2 = document.getElementById('messagearea');
				    var div3 = document.getElementById('chatstart');
				    
				    if ( div3.style.display = 'block') {
				        div.style.display = 'block';
				        div1.style.display = 'block';
				        div2.style.display = 'block';
				      	div3.style.display = 'none';
				    }
				    
				   //내일 html안지워지는거 고쳐
				   //버튼 옆으로 옮겨
					openSocket();		
				}
			}
		}); 
	}
	
}


function blacklist(){
	blackword.innerHTML ="";
	$.ajax({
		url : './alluserlist.chat',
		type : 'POST',
		dataType : 'json',
		error : function(xhr,status,error){
			alert(error);
			alert("error");
		},
		success : function(json){
			
			var results = eval(json);
			var html = "<tr><td>나쁜말추가</td></tr>";
				html += "<tr><td><input type=\"text\" onkeyup=\"enterkey2()\" id=\"block\" name=\"block\" size=15></td><br>";
				html+= "<td><button type=\"button\" class=\"btn btn-outline-secondary btn-sm\" onclick=\"blockword()\">나쁜말설정</td></tr>";
				html += "<tr><td>친구차단</td></tr>";
				html += "<tr><td><button type=\"button\" class=\"btn btn-outline-secondary btn-sm\" onclick=\"setblacklist()\">차단</button>&nbsp;";
				html += "<button type=\"button\" class=\"btn btn-outline-secondary btn-sm\" onclick=\"unblacklist()\">차단해제</button></td></tr>";
				
				for(var i=0;i<results.length;i ++) {
					html += "<tr>";
					html += "<td><input type=\"radio\" id=\"alluserlist\" name=\"alluserlist\" value=\""+results[i].user+"\">"+results[i].user + "</td>";
					html += "</tr>";
				}
			$("#blackword").append(html); 
		}
	});
}
function unblacklist(){
	alert("빠른 시일내에 찾아뵙겠습니다.");
	blackword.innerHTML ="";
}
function setblacklist(){
	alert("빠른 시일내에 찾아뵙겠습니다.");
	blackword.innerHTML ="";
}
function blockword(){
	
	var chatname = document.getElementById('chatname').value;
	var word = document.getElementById('block').value;
	alert(word);
	var data = "id="+chatname+"&word="+word;
	
	$.ajax({
		url : './blockword.chat',
		type : 'POST',
		data : data,
		dataType : 'json',
		error : function(xhr,status,error){
			alert(error);
			alert("error");
		},
		success : function(json){
			var results = eval(json);
			if(results[0].results == "ok"){
				alert(results[0].desc);
			}else if(results[0].results=="fail"){
				alert(results[0].desc);
			}
		}
	}); 
	blackword.innerHTML ="";
}

function alluserlist(){
	alluser.innerHTML = "";
	$.ajax({
		url : './alluserlist.chat',
		type : 'POST',
		dataType : 'json',
		error : function(xhr,status,error){
			alert(error);
			alert("error");
		},
		success : function(json){
			var results = eval(json);
			var html = "<tr><td>전체사용자/쪽지보내기</td></tr>";
				html += "<tr><td><button type=\"button\" class=\"btn btn-outline-secondary btn-sm\" id=\"instancesend\" onclick=\"popupmsg()\">쪽지보내기</button></td></tr>";
				for(var i=0;i<results.length;i ++) {
					html += "<tr>";
					html += "<td><input type=\"radio\" id=\"alluserlist\" name=\"alluserlist\" value=\""+results[i].user+"\">"+results[i].user + "</td>";
					html += "</tr>";
				}
			$("#alluser").append(html); 
		}
	});
	
}

function popupmsg(){
	var alluserlist = document.getElementsByName('alluserlist');
	var id = document.getElementById("chatname").value;
	
	for(var i=0;i<alluserlist.length; i++) {
	    if(alluserlist[i].checked) {
        	if(alluserlist[i].value==id){
        		alert("본인은 선택할 수 없습니다.");
        	}else {
        		var user = alluserlist[i].value;
        	}
	    	
	    }
	}
	var msg = prompt(user+'님께 보낼 쪽지 내용을 입력하세요.', '' );
	if(msg){
		webSocket.send(id+"|!쪽지전송@"+user+","+msg);
	}
	alluser.innerHTML ="";
}

function listview(){
	servermsg.innerHTML ="";
	$.ajax({
		url : './allroomlist.chat',
		type : 'POST',
		dataType : 'json',
		error : function(xhr,status,error){
			alert(error);
			alert("에러다");
		},
		success : function(json){
			var results = eval(json);
			var html = "<tr><td>전체방목록/공개여부/제한인원</td></tr>";
				html += "<tr><td><button type=\"button\" class=\"btn btn-outline-secondary btn-sm\" onclick=\"enterRoom()\">방입장</button></td></tr>";
				for(var i=0;i<results.length;i ++) {
					html += "<tr>";
					html += "<td><input type=\"radio\" id=\"allroomlist\" name=\"allroomlist\" value=\""+results[i].room+"/"+results[i].lock+"\">"+ results[i].room + " / "+results[i].lock+" / "+results[i].limit+"</td>";
					html += "</tr>";
				}
			$("#servermsg").append(html); 
		}
	});
	
}
function enterRoom(){
	
	var allroomlist = document.getElementsByName('allroomlist');
	var id = document.getElementById("chatname").value;
	
	for(var i=0;i<allroomlist.length; i++) {
	    if(allroomlist[i].checked) {
        	var roominfo = allroomlist[i].value;
	    }
	}
	var index = roominfo.indexOf("/");
	var rno = roominfo.substring(0,index);
	var lock = roominfo.substring(index+1,roominfo.length);
	
	if(lock=="공개"||lock=="게임"){
		webSocket.send(id+"|"+"!방입장@"+rno);
		servermsg.innerHTML ="";
	}else if(lock=="비공개"){
		checkpassword(rno);
	}
}
function checkpassword(rno){
	var password = prompt( '방 비밀번호를 입력하세요.', '' );
	var roomno = rno;
	var data = "password="+password+"&roomno="+roomno;
	
	$.ajax({
		url : './passwordcheck.chat',
		type : 'POST',
		data : data,
		dataType : 'json',
		success : function(json){
			var results = eval(json);
			if(results[0].results == "ok"){
				alert(results[0].desc);
				webSocket.send(id+"|"+"!방입장@"+rno);		
			}else {
				alert(results[0].desc);
			}
		}
	});
	servermsg.innerHTML ="";
	
}

function send(){
	var divdiv = document.getElementById("messages");
	divdiv.scrollTop = divdiv.scrollHeight;
	var text = document.getElementById("messageinput").value;
	
	var allroomlist = document.getElementsByName('transoption');
	var id = document.getElementById("chatname").value;
	
	for(var i=0;i<allroomlist.length; i++) {
	    if(allroomlist[i].checked) {
        	var roominfo = allroomlist[i].value;
	    }
	}
	
	
	if(gameSW){
		if(text)
		webSocket.send(id+"|!게임중@"+text+gameName);
	}	
	else if(text.startsWith("/to")){
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
		
		webSocket.send(id+"|!귓속말@"+"/to"+whisperName+" "+text);
		
	}else {
		webSocket.send(id+"|!메세지@"+text);
	}
	checkmyroom();
	clear();
}
function closeSocket(){

	
    
    eraser();
	webSocket.close();
}

function enterroom0(){
	webSocket.send(id+"|"+"!방나가기@");
	clear();
}

function invitation(){
	var user="";
	var waiting = document.getElementsByName('waiting');
	var id = document.getElementById("chatname").value;
	
	for(var i=0;i<waiting.length; i++) {
	    if(waiting[i].checked) {
	        if(waiting[i].value==id){
	        	alert("본인을 선택할 수 없습니다.");
	        }else {
	        	user = waiting[i].value;
	        }
	    }
	}
	
	var data = "id="+id+"&user="+user;
	
	$.ajax({
		url : './invitation.chat',
		type : 'POST',
		data : data,
		dataType : 'json',
		success : function(json){
			var results = eval(json);
			if(results[0].results == "ok"){
				alert(results[0].desc);
				webSocket.send(id+"|!초대받음@"+user);
				writeResponse(user+"님께 초대 요청 메시지를 보냈습니다.");
			}else if(results[0].results=="fail1"){
				alert(results[0].desc);
			}else if(result[0].results=="fail2"){
				alert(results[0].desc);
			}
		}
	});
	waitingUser.innerHTML="";	
	
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
	    }
	}
	
	if(locktype=="비공개"){
		pw=document.getElementById("roompw").value;
		
		if(pw==null||pw==""||pw=="undefined"||pw.length==0){
			alert("비공개 방에는 비밀번호가 필요합니다.");
			document.getElementById('limit').value="";
			return;
		}
	}
	if(locktype!="게임"){
		if(!($.isNumeric(limit))){
			alert("정원은 숫자로만 입력하세요.");
			document.getElementsByName('locktype').value="";
			return;
		}
	}
	if(locktype=="게임"){
		alert("빠른 시일내에 찾아뵙겠습니다.");
	}
	
	webSocket.send(id+"|!방만들기@,"+locktype+","+limit+","+pw);
	clear();
	
}


function statusInvitation(text){
	var message = text;
	var indexof = message.indexOf("|")+1;
	var indexof2 = message.indexOf(",");
	
	var ownername = message.substring(indexof,indexof2);	
	var roomno=message.substring((indexof2)+1,message.length);
	
	var result = confirm(ownername+'님이 당신을 초대합니다. '+roomno+' 방으로 입장하시겠습니까?');	
	
	if(result){
		webSocket.send(id+"|"+"!방입장@"+roomno);
		
	}else {
		webSocket.send(id+"|"+"!초대거절@"+ownername);
		text = ownername+"님의 초대를 거절했습니다.";
		writeResponse(text);
		
		var data = "id="+id;
		
		$.ajax({
			url : './reject.chat',
			type : 'POST',
			data : data,
			dataType : 'json',
			success : function(json){
				var results = eval(json);
				if(results[0].results == "ok"){
					alert(results[0].desc);
					webSocket.send(id+"|!초대받음@"+user);
					writeResponse(user+"님께 초대 요청 메시지를 보냈습니다.");
				}else if(results[0].results=="fail"){
					alert(results[0].desc);
				}
			}
		});
	}
}
function recivepopup(text){
	
	var index = text.indexOf(",");

	var sender = text.substring(text.indexOf("@")+1,index);
	var recivemsg = text.substring(index+1,text.length);
	alert(sender+"님의 쪽지 > "+recivemsg);
	
	
}
function writeResponse(text){

	if(text.startsWith('!방목록')){
		servermsg.innerHTML += "<br/>"+text;
	}else if(text.startsWith('!전체사용자')){
		text=text.substring(1,text.length);
		alluser.innerHTML += "<br/>"+text;
	}else if(text.startsWith('!초대받음|')){
		statusInvitation(text);
	}else if(text.startsWith('!쪽지받음')){
		recivepopup(text);
	}else if(text.startsWith('!방장방나가기|')){
		ownerexit(text);
	}else if(text.startsWith('!게임입장|')){
		gameSW=true;
		gameroom(text);
		//messages.innerHTML += "<br/>"+text;
	}
	else {
		messages.innerHTML += "<br/>"+text;
	}
	if(text.startsWith("접속을 종료합니다.")){
		
	}else {
		checkmyroom();
	}
		
	var divdiv = document.getElementById("messages");
	divdiv.scrollTop = divdiv.scrollHeight;
	
}
function gameroom(text){
	var index = text.indexOf("|");
	var index2 = text.indexOf("@");
	gameName = text.substring(index+1,index2);
	var text1 = gameName+" 추출한내이름";
	writeResponse(text1);
}

function ownerexit(text){
	var index = text.indexOf("|")+1;
	text=text.substring(index,text.length);
	 var result = confirm(text);
	
	 if(result){
		 webSocket.send(id+"|"+"!방장방나가기@");
	 }
	
	 
}

function clear(){
	document.getElementById("roompw").value ="";
	document.getElementById("roompw").value ="";
	document.getElementById("limit").value ="";
	document.getElementById("messageinput").value="";
	document.getElementById("messageinput").focus();	
	
	
}
function eraser(){
	messages.innerHTML = "";
	servermsg.innerHTML="";
	roomUser.innerHTML="";
	alluser.innerHTML="";
	waitingUser.innerHTML="";
	myroomcheck.innerHTML ="";
	blackword.innerHTML="";
	
	document.getElementById('messageinput').value="";
}
</script>
</html>