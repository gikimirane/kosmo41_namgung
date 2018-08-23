<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.Enumeration" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>하이하이</h1>
<% 

	Object obj1 = session.getAttribute("id");
	String id1 = (String)obj1;
		
	if(id1==null){
		response.sendRedirect("login.html");
	}else {
		out.println(id1+"님 반갑습니당! <br>");
	}
	
	
	/*굳이 할필요는 없음!
	Enumeration enumeration = session.getAttributeNames();
	String id,name;
 
	while(enumeration.hasMoreElements()){
		id=enumeration.nextElement().toString();
		name=session.getAttribute(id).toString();
		if(id.equals("id")) {
			out.println(name+"님 반갑습니당! <br>");
		}
	}
	*/
%>
<a href="logout.jsp">로그아웃</a>
<a href="sessiontest.jsp">세션테스트</a>
</body>
</html>