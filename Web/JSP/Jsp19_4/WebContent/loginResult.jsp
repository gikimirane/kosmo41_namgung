<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%!
    	String name, id, pw;
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>

<%
	name=(String)session.getAttribute("name");
	id=(String)session.getAttribute("id");
	pw=(String)session.getAttribute("pw");

	//로그인없이 즉, 세션에 등록된 정보가 없이 loginresult 페이지 진입불가
	if(name==null){
		response.sendRedirect("login.jsp");
	}else {
%>
<%=name %>님 안녕하세요! <br><p>
<% } %>
<a href="modify.jsp">회원정보 수정</a>
<a href="delete.jsp">회원탈퇴</a>

</body>
</html>