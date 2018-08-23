<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%!
	String id, pw;
%>
<%
	id= request.getParameter("id");
	pw= request.getParameter("pw");
%>

<h1>forward_param.jsp 입니다.</h1>
아이디 : <%=id %><br/>
비밀번호 : <%=pw %><br/>
</body>
</html>