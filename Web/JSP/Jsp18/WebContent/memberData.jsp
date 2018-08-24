<%@ page language="java" contentType="text/html; charesultsetet=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charesultsetet=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%!
	Connection connection;
	Statement statement;
	ResultSet resultset;
	
	String driver="oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String uid = "scott";
	String upw = "tiger";
	String query ="select * from member";
%>
<%
	try{
		Class.forName(driver);
		connection = DriverManager.getConnection(url,uid,upw);
		statement = connection.createStatement();
		resultset = statement.executeQuery(query);
		
		
		while(resultset.next()){
			String id = resultset.getString("id");
			String pw = resultset.getString("pw");
			String name = resultset.getString("name");
			String phone = resultset.getString("phone");
			
			out.println("아이디 : "+id+"<br> 비밀번호 : "+pw+"<br> 이름 : "+name+"<br> 전화번호 : "+phone+"<br/>");
			out.println("---------------------------------------------------------------------<br>");
		}
	}catch(Exception e){
	}finally{
		try{
			if(resultset!=null)resultset.close();
			if(statement!=null)statement.close();
			if(connection!=null)connection.close();
		}catch(Exception e){
		}	
	}
%>
</body>
</html>