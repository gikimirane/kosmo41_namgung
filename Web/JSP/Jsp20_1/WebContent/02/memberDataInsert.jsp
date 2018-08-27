<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>

<%!
	Connection con;
	PreparedStatement pstmt;

	String driver="oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String uid = "scott";
	String upw = "tiger";
	
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

//목적은 JSP에서 preparedStatment 사용하는 것
	try{
		Class.forName(driver);
		con = DriverManager.getConnection(url,uid,upw);
		String sql = "insert into member values (?,?,?,?,?)";
		pstmt = con.prepareStatement(sql);
		int n;
		pstmt.setString(1,"abc");
		pstmt.setString(2,"123");
		pstmt.setString(3,"홍길동");
		pstmt.setString(4,"010-1234-5678");
		pstmt.setString(5,"man");
		n = pstmt.executeUpdate();
		
		pstmt.setString(1,"def");
		pstmt.setString(2,"456");
		pstmt.setString(3,"전우치");
		pstmt.setString(4,"010-1212-5678");
		pstmt.setString(5,"man");
		n = pstmt.executeUpdate();
		
		pstmt.setString(1,"ghi");
		pstmt.setString(2,"789");
		pstmt.setString(3,"손오공");
		pstmt.setString(4,"010-1234-5656");
		pstmt.setString(5,"man");
		n = pstmt.executeUpdate();
		
		if(n==1){
			out.println("insert success");
		}
	}catch(Exception e){
		
		if(e.getMessage().contains("ORA-00001")){
			out.println("중복되는 회원데이터가 있습니다.");
		}
	}finally{
		try{
			try {
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}catch(Exception e){}
	}
%>

<br/>
<a href = "memberDataView.jsp">회원정보 보기</a>
</body>
</html>