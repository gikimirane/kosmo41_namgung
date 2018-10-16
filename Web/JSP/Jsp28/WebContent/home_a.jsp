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
	<!--  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>-->
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
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
<meta name="google-signin-client_id" content="477735906673-gij7e82ii8htukmh08tejum8sc7gpp3e.apps.googleusercontent.com">
<script src="https://apis.google.com/js/api:client.js"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>

	
<script>
	/* 페이스북 로그아웃 */
	window.fbAsyncInit = function() {
	    FB.init({
	        appId        : '2158900160796131',
	        xfbml        : true,
	        version      : 'v3.1' ,
	        status       : true
	    });
	};
	
	(function(d, s, id){
	    var js, fjs = d.getElementsByTagName(s)[0];
	    if (d.getElementById(id)) {return;}
	    js = d.createElement(s); js.id = id;
	    js.src = "//connect.facebook.net/en_US/sdk.js";
	    fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));
	
	function fblogout(){
		FB.logout();
		document.location.href="./logout.do";
	}
	
	/* 구글로그아웃 */
	function onLoad() {
      	gapi.load('auth2', function() {
       		gapi.auth2.init();
      	});
    }
	function gloginout() {
	    var auth2 = gapi.auth2.getAuthInstance();
	     auth2.signOut().then(function () {   	 		
   	 		console.log('User signed out.');
   	 		document.location.href="./logout.do";
	   	 });
	}
	
	</script>
</head>
<body>
<%session.removeAttribute("input"); session.removeAttribute("search"); %>
	
<div class="container">
      <header class="header clearfix">
        <nav>
          <ul class="nav nav-pills float-right">
            <li class="nav-item">
              <a class="nav-link" href="./main.jsp">HOME</a>
            </li>
            
            <li class="nav-item">
            
            <% 
			if(session.getAttribute("ValidMem")!=null){ %>

				<%if(session.getAttribute("logintype").equals("google")){ %>
					<a class="nav-link" href="http://www.google.com" id="info" target="_blank">MYINFO</a></li>
					<li class="nav-item"><a class="nav-link" href="#" onclick="gloginout()">G_LOGOUT</a></li>
					<script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
				
				<%}else if(session.getAttribute("logintype").equals("facebook")){ %>
					<a class="nav-link" href="http://www.facebook.com" id="info" target="_blank">MYINFO</a></li>
					<li class="nav-item"><a class="nav-link" href="#" onclick="fblogout()">F_LOGOUT</a></li>
				
				<%}else if(session.getAttribute("logintype").equals("none")){ %>
					<%}if(session.getAttribute("id").equals("yooni")){ %>
						<a class="nav-link" href="./myinfo.jsp" id="info">MYINFO</a></li>
						<li class="nav-item"><a class="nav-link" href="./adminpage.jsp" >ADMINPAGE</a></li>
						<li class="nav-item"><a class="nav-link" href="./logout.do" >B_LOGOUT</a></li>
					<%} else {%>
						<a class="nav-link" href="./myinfo.jsp" id="info">MYINFO</a></li>
						<li class="nav-item"><a class="nav-link" href="./logout.do" >B_LOGOUT</a></li>
					<%} %>
			<%} else {%>
				<a class="nav-link" href="./join.jsp">LOGIN</a>	
			<%} %>
           
            </li>
            
            
          </ul>
        </nav>
        <a href="main.jsp"><h3 class="text-muted">PLACIDO COFFEE</h3></a>
      
     
    <hr>
    
	<div class="row align-items-center">
		<div class="col" align="center" >
			<button class="btn btn-outline-secondary btn-sm" type="button" id="cafeinfo" onclick="javascript:document.location.href='./about.jsp'">WHERE IS THE PLACIDO?</button></div>
		<div class="col" align="center" >
			<button class="btn btn-outline-secondary btn-sm" type="button" id="chat" onclick="javascript:document.location.href='./roomlist.chat'">CHATTING SPACE</button></div>
		<div class="col" align="center" >
			<button class="btn btn-outline-secondary btn-sm" type="button" name="board" id="board" onclick="javascript:document.location.href='./list.do'">MEMBER BOARD</button></div>
		<div class="col" align="center" >
			<button class="btn btn-outline-secondary btn-sm" type="button" onclick="javascript:document.location.href='./galleryList.jsp'">GALLERY</button></div>

	</div>
	<hr>
	<p></p><p></p>
</div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
