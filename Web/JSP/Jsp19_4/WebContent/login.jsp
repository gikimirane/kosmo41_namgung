<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
			alert(queryString);
			$.ajax({
				url : 'LogInProcess',
				type : 'POST',
				data : queryString,
				dataType : 'json',
				success : function(json){
					var results = eval(json);
					if(results[0].results == "ok"){
						alert("로그인 성공!")
						window.location.replace("loginResult.jsp");
					} else {
						alert(results[0].desc);
					}
					
					
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