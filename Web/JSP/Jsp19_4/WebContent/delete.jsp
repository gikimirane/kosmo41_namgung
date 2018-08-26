<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%! String name, pw; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery.js"></script>	
<script>
	function DeleteProcess(){
		var pw;
		pw = document.getElementById('pw');
		pw = "pw="+pw.value;
		
		$.ajax({
			url : 'DeleteProcess',
			type : 'POST',
			data : pw,
			dataType : 'json',
			success : function(json){
				var results = eval(json);
				if(results[0].results == "ok"){
					alert(results[0].desc);
					window.location.replace("deleteResult.jsp");
				} else {
					alert(results[0].desc);
				}
			}
		});
	}
</script>
</head>
<body>

회원탈퇴를 위해 비밀번호를 입력하세요. <input type="text" name="pw" id="pw">
<input type="button" value="비밀번호 확인" onclick="DeleteProcess()">

</body>

</html>