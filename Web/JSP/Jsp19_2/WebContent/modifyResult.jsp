<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%= session.getAttribute("name") %>님의 회원정보 수정이 정상처리 되었습니다.<br><p>
<%= session.getAttribute("id") %> 아이디<br>
<%= session.getAttribute("pw") %> 비번<br>
<a href="logout.jsp">로그아웃</a> &nbsp;&nbsp; <a href="modify.jsp">정보수정</a>
</body>
</html>