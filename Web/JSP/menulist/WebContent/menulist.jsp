<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.json.simple.JSONArray" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import="java.sql.*" %>
    
<%
	JSONObject obj = new JSONObject();

	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}catch(ClassNotFoundException cnfe) {
		cnfe.printStackTrace();
	}
	Connection con=null;
	Statement stmt = null;
	ResultSet rs = null;
	String sql = "select * from menulist";
	
	try {
	    String user = "scott"; 
	    String pw = "tiger";
	    String url = "jdbc:oracle:thin:@ec2-13-209-64-83.ap-northeast-2.compute.amazonaws.com:1521:xe";
	    
	  con = DriverManager.getConnection(
				url,user,pw);
	    
		stmt = con.createStatement();
	    System.out.println("Database에 연결되었습니다.\n");
	    
	    stmt = con.createStatement();
	    rs = stmt.executeQuery(sql);
	    
	    while(rs.next()){
	    	obj.put("menu",rs.getString("menu"));
	    }
	    
	} catch (SQLException sqle) {
	    System.out.println("DB 접속실패 : "+sqle.toString());
	} catch (Exception e) {
	    System.out.println("Unkonwn error");
	    e.printStackTrace();
	}finally{
		if(rs!=null)rs.close();
		if(stmt!=null)stmt.close();
		if(con!=null)con.close();
	}
	
	out.println(obj.toJSONString());
	
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>