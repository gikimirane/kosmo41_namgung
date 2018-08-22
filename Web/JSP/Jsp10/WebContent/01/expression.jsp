<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- %!에 작성된 애들은 전역변수, 전역메소드!! --%>
<%! 
	int i=10;
	String str = "ABCDEFG";

	public int sum(int a,int b){
		return a+b;
	}
%>

숫자 i의 값은 <%=i%>입니다.<br>
문자변수에는 <%=str %>값이 저장되어 있습니다.<br>
1과 5의 합은 <%=sum(1,5)%> 입니다.

</body>
</html>