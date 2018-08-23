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
	int num1, num2, result;
	String cal;
%>
<%
	request.setCharacterEncoding("UTF-8");
	
	try{
		num1 = Integer.parseInt(request.getParameter("num1"));
		num2 = Integer.parseInt(request.getParameter("num2"));
	}catch(Exception e){
%>
		<h1>숫자로 입력하세요.</h1><br/>
<%
	}
	cal = request.getParameter("cal");
	
	if(cal.equals("add")) {
		result = num1+num2;
	}else if(cal.equals("sub")) {
		if(num1>num2) {
			result = num1-num2;
		}else {
			result = num2-num1;
		}
	}else if(cal.equals("mul")) {
		result = num1*num2;
	}else if(cal.equals("div")) {
		if(num1==0 || num2==0) {
%>
			<h1 style="background-color:yellow">0으로 나눌 수 없습니다.</h1><br/>
<%
			return;
		}else {
			result = num1/num2;
		}
	}
%>
	<h1 style=color:pink>연산 결과 : <%=result %></h1><br/>
	
</body>
</html>