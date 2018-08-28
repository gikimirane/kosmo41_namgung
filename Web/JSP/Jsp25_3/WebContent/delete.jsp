<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script language="javascript" src="member.js"></script>

</head>
<body>

	<form action="delete.do" name = "reg_frm" method="post">
	비밀번호 : <input type="password" name="pw" size="20"><br>
	비밀번호 확인 : <input type="password" name="pw_check" size="20"><br>
	<input type="button" value="확인" onclick="deleteInfoConfirm()">
	</form>
</body>
</html>