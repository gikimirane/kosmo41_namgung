<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function check(bid){
		var uPass = document.getElementById("uPass").value;
		
		alert(uPass);
		alert(${content_view.bPass})
		if(${content_view.bPass}==uPass){
			document.location.href="content_view.do?bId="+bid;
			session.setAttribute("bPass","1");
		}else {
			alert("비밀번호가 틀립니다.");
			return;
		}
	}
</script>
</head>
<body>
	<% int bid=Integer.parseInt(request.getParameter("bId")); %>
	
	게시물 비밀번호를 입력하세요. <br>
	<input type="password" name="uPass" id="uPass">
	<input type="button" name="check" id="check" value="확인" onclick="javascript:check(<%=bid%>)">

</body>
</html>