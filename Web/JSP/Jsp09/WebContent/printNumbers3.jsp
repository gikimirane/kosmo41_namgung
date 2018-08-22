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
	String max = request.getParameter("max");
	if(max!=null){
		int maxValue = Integer.parseInt(max);
		
		for(int i=0;i<maxValue;i++){
			out.println(i+"<br>");
		}
	}else{
		out.println("<h1>You must set 'max' parameter!! </h1>");
	}
	
%>
</body>
</html>