<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

</head>
<style>
	.aaa{
		border-width:1px;
		border-style:solid;       /* solid */
		border-color:black;
	}
</style>
<body>
<jsp:include page="./home_a.jsp"/>
	<% 	
		request.setCharacterEncoding("UTF-8");
		String id = (String)session.getAttribute("name");
		String pw = (String)session.getAttribute("pw");
		
		if(id!=null){
			session.setAttribute("id", id);
		}else {
			id="Guest";
		}
		
	%>

<div class="container" style="height:800px;border-width:1px;border-style:solid;border-color:black;overflow:auto">
	<div class="row" align="left">
		<div class="col-8 aaa">
		<button type="button" onclick="openSocket();">채팅시작</button>
		<button type="button" onclick="send();">메시지보내기</button>
		<button type="button" onclick="closeSocket();">채팅종료</button>
		<button type="button" onclick="eraser();">대화창지우기</button>
		</div>
		<div class="col-4 aaa">
			<button type="button">방만들기</button>
		</div>
	</div>
	
	<div class="row aaa" style="background-color:yellow;">나중에 뭐라도 넣어야지!</div>
	<div class="row aaa">
		사용자 아이디 : <%=id %>
	</div>
	
	
	<div class="row">
		<input type="text" id ="messageinput"/>
		
	</div>
	
	<div class="row">
		<input type="hidden" id="id" value=<%=id %>>
	</div>
	
	<div class="col-8 aaa" id="messages" style="height:900px;"></div>
	</div>
</div>


	<script type="text/javascript">
		var webSocket;
		var messages = document.getElementById("messages");
						
		//커넥트 눌렀을 때 오는 부분
		function openSocket(){
			//이미 열려있는지 체크하는영역
			if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED){
				writeResponse("WebSocket is already opend.");
				return;
			}
			//이 주소에 접속~ 나중에 domain name으로 바꾸면 됨 AWS
			webSocket = new WebSocket("ws://localhost:8081/Jsp28/websocketendpoint2");
			//on은 ~할때, 오픈되었을 때를 의미

			webSocket.onopen = function (event){
				if(event.data == undefined){
					return;
				}
				
				writeResponse(event.data);
			};
						
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
		
		function eraser(){
			messages.innerHTML = "";
		}
	
	</script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	
<jsp:include page="./footer.jsp" />
 
</body>
</html>