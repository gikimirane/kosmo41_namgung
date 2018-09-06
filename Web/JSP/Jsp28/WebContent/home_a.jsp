<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
	<meta charset="utf-8">
 	<!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

<style>
	.sub-name1{
		border-width:1px;
		border-style:solid;       /* solid */
		border-color:black;
	}
	.header1 {
		background-color:skyblue;
		height:
	}
	
</style>

<script>
/* 	$(document).ready( function() {
	
 		 $("#chat_area").load("./chat/client.jsp");
		
		$("#join").on("click", function(){
			document.location.href="./join.jsp";
		});
		 
	
		//다음에 스크롤 생기면 계속 맨 밑으로 보여주는거 추가 해
	});
	 */
</script>

</head>
<body>
<%	session.removeAttribute("input");
	session.removeAttribute("search"); %>
	
<div class="container">
	
      <header class="header clearfix">
        <nav>
          <ul class="nav nav-pills float-right">
            <li class="nav-item">
              <a class="nav-link" href="./main.jsp">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">About</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Contact</a>
            </li>
            <li class="nav-item">
            <% 
			if(session.getAttribute("ValidMem")!=null){ %>
				<a class="nav-link" href="./logout.do">Logout</a>			
			<%	} %>
            </li>
          </ul>
        </nav>
        <h3 class="text-muted">PLACIDO COFFEE</h3>
      </header>
      
		<div class="row">
			
			<div class="col-12" align="center">
				<% 
				if(session.getAttribute("ValidMem")==null){ %>
					<form action="loginOk.do" name="frm_form">
					<input type="text" class="form-control btn btn-outline-secondary" id="id1" name="id1" placeholder="Enter ID" style="width:400px;height:31px;">
		   			<input type="password" class="form-control btn btn-outline-secondary" id="pw" name="pw" placeholder="Enter Password" style="width:400px; height:31px;">&nbsp;
					
					<a href="#" onclick="javascript:document.frm_form.submit()" class="btn btn-outline-secondary btn-sm">LOGIN</a>&nbsp;
					<a href="join.jsp" class="btn btn-outline-secondary btn-sm">JOIN</a>
				  	</form>
				<%	}%>
			</div>
			
		</div>
	<br>
	<div class="row align-items-center">
		<div class="col" align="center" style="background-color:#cff09e;"><a href="list.do" name="board" id="board" >회원게시판</a></div>
		<div class="col" align="center" style="background-color:#cff09e;"><a href="#">전체게시판</a></div>
		<div class="col" align="center" style="background-color:#cff09e;"><a href="galleryList.jsp">갤러리</a></div>
		<div class="col" align="center" style="background-color:#cff09e;"><a href="./chat/client.jsp" id="chat">채팅</a></div> 
	</div>
	<p></p>
</div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
