<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

탈퇴가 완료되었습니다. <br></br>
<%
	session.invalidate();
%>

<a href="login.jsp">로그인</a>&nbsp;&nbsp;<a href="join.jsp">회원가입</a>

</body>
</html>