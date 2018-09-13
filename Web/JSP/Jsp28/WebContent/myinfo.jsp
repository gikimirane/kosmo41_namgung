<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.study.jsp.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 	<meta charset="utf-8">
 	<!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<!--  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>-->
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

</head>
<script language="JavaScript" src="member1.js"></script>
<jsp:include page="./home_a.jsp"/>
<%
	request.setCharacterEncoding("UTF-8");
%>

<%
	String id = (String)session.getAttribute("id");
	MemberDao dao = MemberDao.getInstance();
	MemberDto dto = dao.getMember(id);
%>
<body>
	<div class="container">
	<div class="row">
		
		<div class="col">
			<form action="modifyOk.do" method="post" name="reg_frm" >
			
				ID > <input type="text" class="form-control" id="id" name="id" placeholder=<%=id %>><br>
				PASSWORD > <input type="password" class="form-control" id="pw" name="pw" placeholder=""><br>
				PASSWORD CHECK > <input type="password" class="form-control" id="pw_check" name="pw_check" placeholder=""><br>
				NAME > <input type="text" class="form-control" id="name" name="name" placeholder=<%=dto.getName() %>><br>
				EMAIL > <input type="email" class="form-control" id="eMail" name="eMail" placeholder=<%=dto.geteMail() %>><br>
				ADDRESS > <input type="text" class="form-control" id="address" name="address" placeholder=<%=dto.getAddress() %>><br>
				 
				<input type="button" class="btn btn-outline-secondary btn-sm" value="탈퇴" onclick="deleteInfoConfirm()">&nbsp;&nbsp;&nbsp;
				<input type="button" class="btn btn-outline-secondary btn-sm" value="수정" onclick="updateInfoConfirm()">&nbsp;&nbsp;&nbsp;
				<input type="reset" class="btn btn-outline-secondary btn-sm" value="취소" onclick="javascript:window.location='main.jsp'">
				 
			</form>
		</div>
		<div class="col-4"></div>
		</div>
	</div>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	
</body>
<jsp:include page="./footer.jsp" />
</html>
