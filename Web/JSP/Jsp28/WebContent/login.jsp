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


	<style type="text/css">
	.header,body{padding-bottom:20px}.header,.jumbotron{border-bottom:1px solid #e5e5e5}body{padding-top:20px}.footer,.header,.marketing{padding-right:15px;padding-left:15px}.header h3{margin-top:0;margin-bottom:0;line-height:40px}.footer{padding-top:19px;color:#777;border-top:1px solid #e5e5e5}@media (min-width:768px){.container{max-width:730px}}.container-narrow>hr{margin:30px 0}.jumbotron{text-align:center}.jumbotron .btn{padding:14px 24px;font-size:21px}.marketing{margin:40px 0}.marketing p+h4{margin-top:28px}@media screen and (min-width:768px){.footer,.header,.marketing{padding-right:0;padding-left:0}.header{margin-bottom:30px}.jumbotron{border-bottom:0}}
	</style>

	<meta name="google-signin-client_id" content="477735906673-gij7e82ii8htukmh08tejum8sc7gpp3e.apps.googleusercontent.com">

	<script>
    function onSignIn(googleUser) {
    	var profile = googleUser.getBasicProfile();
    	var email = profile.getEmail();
    	var name = profile.getName();
    	document.location.href="./loginOk.do?name="+name+"&type=google";	
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

</head>
<body>
<div class="contanier">
	<div class="row">
	<form action="loginOk.do" name="frm_form">
		<input type="text" class="form-control btn btn-outline-secondary" id="id1" name="id1" placeholder="Enter ID" style="width:400px;height:31px;">
	 	<input type="password" class="form-control btn btn-outline-secondary" id="pw" name="pw" placeholder="Enter Password" style="width:400px; height:31px;">&nbsp;
		<input type="hidden" id="type" name="type" value="none">
		<a href="#" onclick="javascript:document.frm_form.submit()" class="btn btn-outline-secondary btn-sm">LOGIN</a>&nbsp;
		<a href="join.jsp" class="btn btn-outline-secondary btn-sm">JOIN</a>
  	</form>
  	</div>
  	
  	<!-- 구글 -->
  	<div class="row">
  		
		<div id="my-signin2"></div>   
	    <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
	    
	    
  	</div>
  	
  	<!-- 페이스북 -->
 
	<div id="login" style="display: block;">
	    <input type="button" onclick="fbLogin();" value="로그인" /><br>
	</div>
	<div id="logout" style="display: none;">
	    <input type="button" onclick="fbLogout();" value="로그아웃" /><br>
	
	    <img id="upic" src=""><br>
	    <span id="uname"></span>
	</div> 	
</div>
    	
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	
</body>
</html>