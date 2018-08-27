<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.study.jsp1.MemberDao" %>
<jsp:useBean id="dto" class="com.study.jsp1.MemberDto" scope="page"/>
<jsp:setProperty name="dto" property="*"/>
<%-- 이전페이지에서 올라온 값들만 저장됨! id는 session에서 꺼내서 저장해줌 --%>

<%
	request.setCharacterEncoding("UTF-8");
	String id = (String)session.getAttribute("id");
	dto.setId(id);
	MemberDao dao = MemberDao.getInstance();
	int check = dao.deleteMember(dto);
	
	System.out.println(dto.getId());
	System.out.println(dto.getName());
	System.out.println(dto.getPw());
	System.out.println(dto.geteMail());
	System.out.println(dto.getrDate());
	
	
	if(check==1){
		session.invalidate();
%>		<script language="javascript">
		alert("회원 탈퇴가 완료되었습니다.");
		document.location.href="login.jsp";
		</script>
<%	}else {		
%>
		<script language="javascript">
		alert("회원 탈퇴에 실패했습니다.");
		history.go(-1);
		</script>
<%
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>