<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script src="http://code.jquery.com/jquery.js"></script>	
	<script>
		function form_check() {
			submit_ajax();
		}
	
		function submit_ajax(){
			var queryString = $("#LogInProcess").serialize() ;
			$.ajax({
				url : 'LogInProcess',
				type : 'POST',
				data : queryString,
				dataType : 'json',
				success : function(json){
					alert("성공");
					window.location.replace("loginResult.jsp");
				}
			});
		}
	</script>
<body>

<form name="LogInProcess" id="LogInProcess">
	아이디 : <input type="text" name="id"><br>
	비밀번호 : <input type="text" name="pw"><br>
	<input type="button" value="로그인" onclick="form_check()"/>
</form>
</body>
</html>