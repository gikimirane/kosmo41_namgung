<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="org.json.simple.JSONArray" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import="java.sql.*" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>
    
<%

	response.setCharacterEncoding("utf-8");
	request.setCharacterEncoding("utf-8"); 
	
	JSONObject obj = new JSONObject();
	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}catch(ClassNotFoundException cnfe) {
		cnfe.printStackTrace();
	}
	Date d = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	sdf.format(d);
	
	String menu = request.getParameter("menu");
	String code = request.getParameter("code");
	String price = request.getParameter("price");
	String client = request.getParameter("client");
	
	String sql = "insert into orderlist values (?,?,?,?,'결제대기',(SELECT SYSDATE FROM DUAL))";
	
	Connection con=null;
	PreparedStatement pstmt = null;
	try {
	    String user = "scott"; 
	    String pw = "tiger";
	   	//String url = "jdbc:oracle:thin:@ec2-13-209-64-83.ap-northeast-2.compute.amazonaws.com:1521:xe";
	    String url = "jdbc:oracle:thin:@localhost:1521:xe";
	  	con = DriverManager.getConnection(url,user,pw);
	    
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1,code);
		pstmt.setString(2,menu);
		pstmt.setString(3,price);
		pstmt.setString(4,client);
		
		int update = pstmt.executeUpdate();
	    obj.put("result",update);
	    
	} catch (SQLException sqle) {
	    System.out.println("DB 접속실패 : "+sqle.toString());
	} catch (Exception e) {
	    System.out.println("Unkonwn error");
	    e.printStackTrace();
	}finally{
		if(pstmt!=null)pstmt.close();
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