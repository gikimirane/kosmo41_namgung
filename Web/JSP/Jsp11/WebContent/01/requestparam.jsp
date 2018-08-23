<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Arrays" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%!
	String name, id, pw, major, protocol;
	String[] hobbys;
%>
<% 
	String sType = request.getMethod();
	//전반적인 Error에 대한 내용을 if로 추려서 error page로 보내버릴때 유용
	if(sType.equals("GET")){
		response.sendRedirect("error40A.html");
	}
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		String major = request.getParameter("major");
		String protocol = request.getParameter("protocol");
		String[] hobbys = request.getParameterValues("hobby");
%>
		이름 : <%=name %><br/>
		아이디 : <%=id %><br/>
		비밀번호 : <%=pw %><br/>
		취미 : <%=Arrays.toString(hobbys)%><br/>
		전공 : <%=major %><br/>
		프로토콜 : <%=protocol %><br/>

</body>
</html>