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
	
	
	
	</script>
</head>
<body>
<%
	name=(String)session.getAttribute("name");
	pw = (String)session.getAttribute("pw");
	
%>
회원탈퇴를 위해 비밀번호를 입력하세요. <input text="text" name="pw">
<input type="submit" value="비밀번호 확인">


</body>
</html>