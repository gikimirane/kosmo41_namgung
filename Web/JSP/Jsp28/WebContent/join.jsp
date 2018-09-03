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
<script language="JavaScript" src="member.js"></script>
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
</style>
<script>

function check(){

	id = document.getElementById('id');
	id = "id="+id.value;
	window.location.href="duplication.do?"+id;

}

</script>
</head>
<body>
<div class="container sub-name1 size">
<form action="joinOk.do" method="post" name="reg_frm" >

			<input type="text" class="form-control" id="id" name="id" placeholder="Enter ID"><br>
			<input type="password" class="form-control" id="pw" name="pw" placeholder="Password"><br>
			<input type="password" class="form-control" id="pw_check" name="pw_check" placeholder="Check Password"><br>
			<input type="text" class="form-control" id="name" name="name" placeholder="Name"><br>
			<input type="email" class="form-control" id="eMail" name="eMail" placeholder="eMail"><br>
			<input type="text" class="form-control" id="address" name="address" placeholder="address"><br>
			<input type="button" class="btn btn-outline-secondary btn-sm" value="회원가입" onclick="infoConfirm();">&nbsp;&nbsp;&nbsp;
			<input type="reset" class="btn btn-outline-secondary btn-sm" value="로그인" onclick="javascript:window.location='home_a.jsp'">
		</form>
</div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</body>
</html>