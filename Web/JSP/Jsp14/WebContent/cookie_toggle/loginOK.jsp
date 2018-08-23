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
	String id, pw, name;
%>
<%
	request.setCharacterEncoding("UTF-8");
	id=request.getParameter("id");
	pw=request.getParameter("pw");
	name=request.getParameter("name");

	if(id.equals("abcde")&&pw.equals("12345")){
		session.setAttribute("id",id);
		session.setAttribute("name",name);
		session.setMaxInactiveInterval(60*10);
		response.sendRedirect("welcome.jsp");
	}else {
		response.sendRedirect("login.html");
	}
%>

<%--참고 : 365일 설정 시 cookie.setMaxAge(365*24*60*60); --%>

</body>
</html>