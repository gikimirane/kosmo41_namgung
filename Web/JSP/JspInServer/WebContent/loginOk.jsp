<%@ page import="java.net.URLDecoder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
response.setCharacterEncoding("utf-8");

	String id = request.getParameter("userid");
	id=URLDecoder.decode(id);
	String pwd = request.getParameter("userpwd");

if(id.equals("홍길동")){
%>
	한글파라미터!!!!!! <br>
	<%=id %><br>
	<%=pwd %>
<%
} else {
%>
	영어파라미터!!!!!!! <br>
	<%=id %><br>
	<%=pwd %>
<% }
%>


</body>
</html>