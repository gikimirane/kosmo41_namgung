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
<%

	Object obj1 = session.getAttribute("id");
	String sId=(String)obj1;
		
	Object obj2 = session.getAttribute("name");
	String sName=(String)obj2;
			
	String id;
	String name;

	Enumeration enumeration = session.getAttributeNames();
	int i=0;
	while(enumeration.hasMoreElements()){
		i++;
		id=enumeration.nextElement().toString();
		name=session.getAttribute(id).toString();
		
		out.println("키 이름 : "+id+"<br>");
		out.println("키 안에 내용 : "+name+"<br>");
	}
	
	if(i==0){
		out.println("해당 세션이 삭제되었습니다.");
	}
	
	out.println("**************************** <br>");
	String sessionID=session.getId();
	out.println("sessionID : "+sessionID+"<br>");
	
	int sessionInter=session.getMaxInactiveInterval();
	out.println("getMaxInactiveInterval : "+sessionInter+"<br>");
	
	out.println("**************************** <br>");
	
%>
</body>
</html>