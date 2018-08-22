<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%! 
	int i=10;
	String str = "ABCDEFG";
%>
<%! 
	//%!는 선언한 애들, 메소드나 변수?이런거..
	public int sum(int a,int b){
		return a+b;
	}
%>
<%
	out.println("i = "+i+"<br/>");
	out.println("str = "+str+"<br/>");
	out.println("sum = "+sum(1,5)+"<br/>");
%>
</body>
</html>