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
	<script src="http://code.jquery.com/jquery.js"></script>
	
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	
	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	
	<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	
	
<style>
	.sub-name1{
		border-width:1px;
		border-style:solid;       /* solid */
		border-color:black;
	}

</style>
</head>
<body>
<div class="container sub-name1" style="width:1200px">
	<div class="row sub-name1">
	  <div class="col-xs-6 col-sm-4 sub-name1"></div>
	  <div class="col-xs-6 col-sm-4 sub-name1" align="center"><h1>Project Page</h1></div>
	</div>
	
	<div class="row sub-name1">
	  <div class="col-xs-12 col-md-8 text-muted sub-name1"></div>
	  <div class="col-xs-6 col-md-4 text-muted sub-name1" align="right">
		<% 
			if(session.getAttribute("ValidMem")!=null){ %>
			<h6><%=session.getAttribute("id") %>님 반가워요!</h6>
			<input type="button" value="LOGOUT" class="btn btn-info btn-sm" onclick="javascript:document.location.href='./logout.do'">
		<%	}else { %>
	  	
	  		<form action="loginOk.do" method="post" class="form-inline" >
				<input type="text" class="form-control" id="id" name="id" placeholder="Enter ID"><br>&nbsp;
	   			<input type="password" class="form-control" id="pw" name="pw" placeholder="Password"><br>&nbsp;
				<br/>
			  	<input type="submit" value="LOGIN" class="btn btn-info btn-sm">&nbsp;
			  	<input type="button" value="JOIN" class="btn btn-info btn-sm"" onclick="javascript:document.location.href='./join.jsp'">
	  		</form>
	  		<%} %>
	  </div>
	</div>
	<br>
	<div class="row sub-name1">
	  <div class="col-md-4 sub-name1" align="center"><a href="list.do">게시판</a></div>
	  <div class="col-md-4 sub-name1" align="center"><a href="#">갤러리</a></div>
	  <div class="col-md-4 sub-name1" align="center"><a href="#">채팅</a></div>
	</div>
		
	<br>
	
	<div class="row">
	<div class="col-md-12">
	<ul class="media-list">
	  	<li class="media sub-name1">
		    <div class="media-left">
		      <a href="#">
		      <img class="media-object" src="http://mblogthumb3.phinf.naver.net/20120512_146/akcnvlcb12_1336784906941muzEv_JPEG/hjhgj.jpg?type=w2" style="width:64px; height: 64px;">
		      </a>
		    </div>
		    <div class="media-body">
		      <h4 class="media-heading">Welcome!!!!!!!!!!!</h4>
		      hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello
		    </div>
	 	</li>
	 	<br/>
	 	<div class="row">
	 	<div class="col-md-1"></div>
	 	<div class="col-md-11 sub-name1">
		 	<li class="media">
		 		<div class="media-right">
			      <a href="#">
			      <img class="media-object" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT46mEiNbWfHUAK3zaZ92_V8X3-rMmfpH8jZtLGZb0BqMgjbmXAyA" style="width:64px; height: 64px;">
			      </a>
			    </div>
			    <div class="media-body">
			      <h4 class="media-heading">Welcome!!!!!!!!!!!</h4>
			      hello hello hello hello hello hello hello hello hello hello hello hello hello hello hello
			    </div>
		 	</li>
	 	</div>
	 	</div>
	</ul>
	
	  <div id="myCarousel" class="carousel slide" data-ride="carousel">
	 <!-- Indicators -->
	 <ol class="carousel-indicators">
	   <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
	   <li data-target="#myCarousel" data-slide-to="1"></li>
	   <li data-target="#myCarousel" data-slide-to="2"></li>
	 </ol>

	 <!-- Wrapper for slides -->
	 <div class="carousel-inner">
	   <div class="item active">
	     <img src="https://fltgraph.files.wordpress.com/2016/01/htel-burdigala-bordeaux-e1469005589510.jpg?w=1000" style="width:1000px; height: 400px;">
	   </div>
	
	   <div class="item">
	     <img src="http://gcentre.net/files/attach/images/122153/500/146/7eb823be619d82825b66c9586d71f09e.jpg" style="width:1000px; height: 400px;">
	   </div>
	
	   <div class="item">
	     <img src="http://t1.daumcdn.net/liveboard/interparktour/e2f896215dc040f9a261086122742b70.JPG" style="width:1000px; height: 400px;">
	   </div>
	 </div>
	
	 <!-- Left and right controls -->
	  <a class="left carousel-control" href="#myCarousel" data-slide="prev">
	    <span class="glyphicon glyphicon-chevron-left"></span>
	    <span class="sr-only">Previous</span>
	  </a>
	  <a class="right carousel-control" href="#myCarousel" data-slide="next">
	    <span class="glyphicon glyphicon-chevron-right"></span>
	    <span class="sr-only">Next</span>
	  </a>
	</div>
	</div>
	
	<
</div>


    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
