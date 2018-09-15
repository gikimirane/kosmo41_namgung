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
	<script src="http://code.jquery.com/jquery.js"></script>

<style>
	.sub-name1{
		border-width:1px;
		border-style:solid;       /* solid */
		border-color:black;
	}
	
	.size {
		width:800px;
		height:800px;
	}

	.layer{ position:absolute; left:15%; top:20%;}
	.logtag { color:gray; font-weight: bold; font-size:20pt;}
</style>




<meta name="google-signin-client_id" content="477735906673-gij7e82ii8htukmh08tejum8sc7gpp3e.apps.googleusercontent.com">
<script language="JavaScript" src="member1.js"></script>
<script>

	/* 구글로 로그인 */
    function onSignIn(googleUser) {
    	var profile = googleUser.getBasicProfile();
    	var email = profile.getEmail();
    	var name = profile.getName();
    	document.location.href="./loginOk.do?name="+email+"&type=google";	
    }
    	
   	function onFailure(error) {
    }
   	
	function signOut() {
	    var auth2 = gapi.auth2.getAuthInstance();
	    auth2.signOut().then(function () {
	    	$('#my-signin2').css('display', 'block');
	    	$('#logout').css('display', 'none');
	    	$('#upic').attr('src', '');
	    	$('#uname').html('');
	    });
	}
    function renderButton() {
        gapi.signin2.render('my-signin2', {
	        'scope': 'profile email',
	        'width': 400,
	        'height': 31,
	        'longtitle': true,
	        'theme': 'dark',
	        'onsuccess': onSignIn,
	        'onfailure': onFailure
        });
    }
    $(document).ready(function() {
    	
    });
    
    /* 페이스북 */
     window.fbAsyncInit = function() {
    FB.init({
      appId      : '2158900160796131',
      cookie     : true,
      xfbml      : true,
      version    : 'v3.1'
    });

    FB.getLoginStatus(function(response) {
    	console.log(response);
      	/* statusChangeCallback(response); */
    });
  };

 
  (function(d, s, id) {
	    var js, fjs = d.getElementsByTagName(s)[0];
	    if (d.getElementById(id)) return;
	    js = d.createElement(s); js.id = id;
	    js.src = "https://connect.facebook.net/en_US/sdk.js";
	    fjs.parentNode.insertBefore(js, fjs);
  	}(document, 'script', 'facebook-jssdk'));

  function statusChangeCallback(response) {
    if (response.status === 'connected') {
      getINFO();
    }
  }
	  
  function fbLogin() {
    FB.login(function(response){
      statusChangeCallback(response);
    }, {scope: 'public_profile, email'});
  }

  function fbLogout() {
    FB.logout(function(response) {
      statusChangeCallback(response);
    });
  }

  function getINFO() {
    FB.api('/me?fields=id,name,picture.width(100).height(100).as(picture_small)', function(response) {
      console.log(response);
      var name = response.name;
      
      document.location.href="./loginOk.do?name="+name+"&type=facebook";	

    });
  }
  
	</script>

<script>

function check(){

	id = document.getElementById('id');
	id = "id="+id.value;
	window.location.href="duplication.do?"+id;

}

</script>
</head>
<jsp:include page="home_a.jsp" />
<body>

<div class="container">
	
	<p class="logtag">LOGIN</p><br>
	<div class="row" align="center">
		<form action="loginOk.do" name="frm_form">
			<input type="text" class="form-control btn btn-outline-secondary" id="id1" name="id1" placeholder="Enter ID" style="width:400px;height:31px;">
			<input type="password" class="form-control btn btn-outline-secondary" id="pw1" name="pw1" placeholder="Enter Password" style="width:400px; height:31px;">&nbsp;
			<input type="hidden" id="type" name="type" value="none">
			<a href="#" onclick="javascript:document.frm_form.submit()" class="btn btn-outline-secondary btn-sm">LOGIN</a>&nbsp;
					</form>
		</div>
	<br>	
	<div class="row">
		<p></p><p></p>
		<!-- 구글 -->
		<div id="my-signin2" align="center"></div>   
  		<script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
  		  		
  		<p></p>
  		&nbsp;&nbsp;	
		<div id="login" style="display:block;">
		<a href="javascript:fbLogin();">페이스북으로 로그인</a>	
		</div>
	</div>

	<p></p><p></p>
	<p class="logtag">JOIN</p><br>
	<div class="row">
		<div class="col">
			<form action="joinOk.do" method="post" name="reg_frm" >
				<input type="text" class="form-control" id="id2" name="id2" placeholder="Enter ID"><br>
				<input type="password" class="form-control" id="pw2" name="pw2" placeholder="Password"><br>
				<input type="password" class="form-control" id="pw_check" name="pw_check" placeholder="Check Password"><br>
				<input type="text" class="form-control" id="name" name="name" placeholder="Name"><br>
				<input type="email" class="form-control" id="eMail" name="eMail" placeholder="eMail"><br>
				<input type="text" class="form-control" id="address" name="address" placeholder="address"><br>
				<input type="button" class="btn btn-outline-secondary btn-sm" value="JOIN" onclick="infoConfirm();">&nbsp;&nbsp;&nbsp;
			</form>
		</div>
		
		
	</div>
</div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<jsp:include page="footer.jsp" />
</body>
</html>