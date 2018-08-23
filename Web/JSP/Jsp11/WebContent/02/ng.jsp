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
	int age;
%>
<%
	String str=request.getParameter("age");
	age=Integer.parseInt(str);
%>
미성년자 입니다. 주류구매가 불가능합니다. <br/>
<a href="requestex.html">처음으로 이동</a>
</body>
</html>