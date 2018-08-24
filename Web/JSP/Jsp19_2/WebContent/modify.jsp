<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.sql.*" %>
    
<%!
	Connection con;
	Statement stmt;
	ResultSet resultSet;
	
	String driver="oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String uid = "scott";
	String upw = "tiger";
	
	String name,id, pw, phone1, phone2, phone3, gender;
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery.js"></script>	
	<script>
		function form_check() {
			submit_ajax();
		}
	
		function submit_ajax(){
			var queryString = $("#ModifyProcess").serialize() ;
			$.ajax({
				url : 'ModifyProcess',
				type : 'POST',
				data : queryString,
				dataType : 'json',
				success : function(json){
					var results = eval(json);
					var result1 = results[0].results;
					alert(result1)
					if(result1=="ok"){
						alert(result1);
						alert("업데이트 완료!");
						window.location.replace("modifyResult.jsp");
					}
					else if(result1 =="fail"){
						alert("업데이트 실패!");
						alert(result1);
					}
					else if(result1 =="not"){
						alert("비밀번호 안맞아요");
						alert(result1);
						window.location.replace("modify.jsp");
					}else if(result1=="long"){
						alert("길이가 너무 길어요!");
					}
					else {
						alert("다 틀렸어 난 에러야!");
					}
				}
			});
		}
	</script>
</head>
<body>

<%
	id=(String)session.getAttribute("id");
	String query="select * from member where id='"+id+"'";
	
	Class.forName(driver);
	con=DriverManager.getConnection(url,uid,upw);
	stmt = con.createStatement();
	resultSet = stmt.executeQuery(query);
	String phone="";
	while(resultSet.next()){
		name=resultSet.getString("name");
		pw=resultSet.getString("pw");
		phone=resultSet.getString("phone");
		gender=resultSet.getString("gender");
	}

	phone1 = phone.substring(0,3);
	phone2 = phone.substring(4,8);
	phone3 = phone.substring(9,13);
		
%>

<form name ="ModifyProcess" id="ModifyProcess">
	아이디 : <%=id %><br>
	비밀번호 : <input type="password" name="pw" size="10"><br>
	이름 : <input type="text" name="name" size="10"><br>
	전화번호 : <select name="phone1">
			<option value="010">010</option>
			<option value="016">016</option>
			<option value="017">017</option>
			<option value="018">018</option>
			<option value="019">019</option>
			<option value="011">011</option>	
		</select> -
		<input type="text" name="phone2" size="5" value=<%=phone2 %>> -
		<input type="text" name="phone3" size="5" value=<%=phone3 %>><br>
		<%
			if(gender.equals("man")){
		%>
			성별구분:<input type="radio" name="gender" value="man" checked="checked">남 &nbsp;
					 <input type="radio" name="gender" value="woman">여<br>
		<%
			}else {
		%>	
			성별구분:<input type="radio" name="gender" value="man">남 &nbsp;
					 <input type="radio" name="gender" value="woman" checked="checked">여<br>
		<%
			}
		%>
		<input type="button" value="정보수정" onclick="form_check()"/>
</form>

</body>
</html>