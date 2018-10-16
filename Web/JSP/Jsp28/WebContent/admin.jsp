<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.study.*" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
function form_check(){
	var id = document.getElementById('id').value;
	var pw = document.getElementById('pw').value;
	var myid="admin";
	var mypw="7786";
	if(id=="" || pw==""){
		alert("ADMIN ID와 PW를 입력하세요.");
	}else if(id==myid && pw==mypw){
		alert("YOONI ADMIN님 반갑습니다!");
		document.frm_form.submit();
		
	}else {
		alert("올바른 ADMIN ID와 PW를 입력하세요.");
	}
}
	
	
</script>
<title>ADMIN PUSH PAGE</title>
 	<meta charset="utf-8">
 	<!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<!--  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>-->
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<body>
	<div class="container">
		<h1>ADMIN PAGE 입니다.</h1>
		<div class="row">
			<form action="adminpage.jsp" name="frm_form"  method = "post">
			<input type="text" class="form-control btn btn-outline-secondary" id="id" name="id" placeholder="Enter ADMIN ID" style="width:400px;height:31px;">
		 	<input type="password" class="form-control btn btn-outline-secondary" id="pw" name="pw" placeholder="Enter ADMIN PW" style="width:400px; height:31px;">&nbsp;
			
			<a href="#" onclick="form_check()" class="btn btn-outline-secondary btn-sm">LOGIN</a>&nbsp;
	  		</form>
  		</div>
	</div>
</body>
</html>