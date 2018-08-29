<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 	
	 
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		if(id==null){
%> 			<jsp:forward page="login.jsp"/>
<% 
		}else {
			session.setAttribute("uid", id);
		}
%>	
	
	<div>
		사용자 아이디 : <%=id %>
	</div>
	<div>
		<input type="text" id ="messageinput" />
		<input type="hidden" id="id" value=<%=id %>>
	</div>
	<div>
		<button type="button" onclick="openSocket();">Open</button>
		<button type="button" onclick="send();">Send</button>
		<button type="button" onclick="closeSocket();">Close</button>
	</div>
	<div id="messages"></div>
	<script type="text/javascript">
		var webSocket;
		var messages = document.getElementById("messages");
		var id =document.getElementById("id").value;
		var sw=false;
		
		//커넥트 눌렀을 때 오는 부분
		function openSocket(){
			//이미 열려있는지 체크하는영역
			if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED){
				writeResponse("WebSocket is already opend.");
				return;
			}
			//이 주소에 접속~ 나중에 domain name으로 바꾸면 됨 AWS
			webSocket = new WebSocket("ws://localhost:8081/WebSocketEx_my/websocketendpoint3");
			//on은 ~할때, 오픈되었을 때를 의미

			webSocket.onopen = function (event){
				if(event.data == undefined){
					return;
				}
				writeResponse(event.data);
			};
			
			if(webSocket.onopen){
				writeResponse(id+"님아 방가방가");
			}
			
			//서버로 부터 나에게 메시지가 왔을때
			//event.data란 이벤트가 일어났을때 나한테 온 메시지를 의미
			webSocket.onmessage = function(event){
				writeResponse(event.data);
			};
			
			webSocket.onclose = function(event){
				writeResponse("Connection closed");
			};
		}
		
		function send(){
			var id = "<%=id%>";
			var text = document.getElementById("messageinput").value;
			webSocket.send(id+"|"+text);
			//send는 내가 보낸거 서버한테
			
			document.getElementById("messageinput").value="";
			document.getElementById("messageinput").focus();
			//text상자 값 공란으로 바꾸고, focus를 두기
		}
		function closeSocket(){
			webSocket.close();
		}
		//서버가 나한테 데이터가 왔을 때
		function writeResponse(text){
			//대화가 길어지면 스크롤이 생기게
			//대화가 또 길어지만 첫번째 줄 자르고 마지막줄에 추가하고..
			//추가 필요
			messages.innerHTML += "<br/>"+text;
		}
	</script>
</body>
</html>