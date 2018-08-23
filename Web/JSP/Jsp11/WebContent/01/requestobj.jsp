<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	out.println("서버 : "+request.getServerName()+"<br/>");
	out.println("포트번호 : "+request.getServerPort()+"<br/>");
	out.println("요청방식 : "+request.getMethod()+"<br/>");
	out.println("프로토콜 : "+request.getProtocol()+"<br/>");
	out.println("URL : "+request.getRequestURL()+"<br/>");
	out.println("URI : "+request.getRequestURI()+"<br/>");
%>
</body>
</html>