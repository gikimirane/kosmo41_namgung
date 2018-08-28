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
		request.setAttribute("id","abcde");
		request.setAttribute("pw","12345");
		
		response.sendRedirect("RequestObj");
		//요청받은 request객체를 위임받은 컴포넌트에 전달하는게 아닌
		//새로 request를 만들어서 보냄
	%>
</body>
</html>