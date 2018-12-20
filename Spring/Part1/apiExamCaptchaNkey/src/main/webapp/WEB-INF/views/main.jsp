<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,shrink-to-fit=no">
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<title>Insert title here</title>
<script>

function checkNumber(){
	
	var value = document.getElementById("input").value;
	alert(value);
	$.ajax({
		url : 'checkNumber',
		type : 'POST',
		data : {"value":value},
		dataType : 'json',
		success : function(json){
			var result = eval(json);
			if(result.result == true){
				alert("성공");
			}else{
				alert("실패");
			}
		}
	}); 
}

</script>
</head>
<body>
<h1>index page</h1>
	<input type="button" value="이미지생성" id="btn" onclick="checkNumber()">
	<span class="captcha_img"><img name='captchaImage' id='chptchaimg' src='https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=${key }' width='100%' height='87' alt='자동입력 방지문자'></span>
	<input type="text" id="input" name="input">
</body>
</html>