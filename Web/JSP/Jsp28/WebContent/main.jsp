<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>WebPage Project</title>
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
	</style>
	
</head>
<body>

<jsp:include page="home_a.jsp"/>

<div class="container">


        <div class="jumbotron">
          <h1 class="display-3">WELCOME, PLACIDO COFFEE ! </h1>
          <p class="lead">
          <% if(session.getAttribute("name")!=null){ %>
        	  <%=session.getAttribute("name") %>님 반갑습니다! PLACIDO COFFEE 입니다. <br>
        	  소통을 위한 채팅으로!
        	  <p>
           <a class="btn btn-lg btn-outline-secondary" href="roomlist.chat" role="button">LET'S START!</a></p>
          <% }else {%>
          	반갑습니다! PLACIDO COFFEE 입니다.
          	회원가입 후 더욱 많이 소통해요! <br>
          	<p>
           <a class="btn btn-lg btn-outline-secondary" href="join.jsp" role="button">SIGN UP TODAY!</a></p>
          <%} %>
          </p>
          
        </div>
        
        <div class="row" align="center">
	     <div class="col"><img src="https://scontent.cdninstagram.com/vp/573ba054016a30b0c92cc4f545fddbbb/5C049941/t51.2885-15/sh0.08/e35/s640x640/38022181_2208841679361458_1070674667594842112_n.jpg" width=200px height=200px alt="..." class="img-rounded"></div>
	      <div class="col"><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKi1K0bnbqKjALYlJBtDbdnchDoDGd3tp8B32r00hXohhms59f" width=200px height=200px alt="..." class="img-rounded"></div>
	      <div class="col"><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQVkxjMKKld4z4jpAfSESTaz_vkHt6_lhYQiKiicUcqQgLXPGCl" width=200px height=200px alt="..." class="img-rounded"></div>
	      <div class="col"><img src="https://scontent-lhr3-1.cdninstagram.com/vp/fa5b05dae2dab14d7780bd7494334b34/5C1921C8/t51.2885-15/e35/39596403_468235863680180_3249153475367403520_n.jpg?se=7&ig_cache_key=MTg1ODYwMjI0NjYyODA3MjUzNw%3D%3D.2" width=200px height=200px class="img-rounded"></div>
	    </div>
		<p></p><p></p>
        <div class="row marketing">
          <div class="col-lg-6">
            <h4>DUKES COFFEE</h4>
            <p>최상급의 고급원두를 사용해 최고의 풍미를 느끼실 수 있습니다. <br> 좋은 원두로 최고의 맛을 내는 바리스타도 함께 합니다. </p>

            <h4>A GOOD PLACE</h4>
            <p>HOT! PLACE 에 위치하여 좋은 사람들과 멋스러운 분위기 속에<br> 차분히 대화를 나누실 수 있습니다.</p>

          </div>

          <div class="col-lg-6">
         	<h4>DESSERT</h4>
	            <p>
	               최고의 맛을 위해 좋은 재료로 만들었습니다. <br>
	               티라미수, 말차와 단팥, 초콜릿과 체리를 담은 멋진 디저트가 있습니다.
	      
	           	</p>
            <h4>PHOTO SPOT</h4>
            <p>멋진 인테리어와 예쁜 꽃과 나무가 함께합니다. <br>매장 앞에 마련된 PHOTO SPOT에서 사진 촬영도 추천드립니다.</p>
          </div>
        </div>
        
        
	      
</div>
<jsp:include page="footer.jsp" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
