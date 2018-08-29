<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script>
	function form_check(){
		document.reply_form.submit();
	}
</script>
<body>
<table width="500" cellpadding="0" cellspacing="0" border="1">
	<form name="reply_form" action ="reply.do" method="post">
		<input type="hidden" name="bId" value="${reply_view.bId }">
		<input type="hidden" name="bGroup" value="${reply_view.bGroup }">
		<input type="hidden" name="bStep" value="${reply_view.bStep }">
		<input type="hidden" name="bIndent" value="${reply_view.bIndent }">
		<tr>
			<td>번호</td>
			<td>${reply_view.bId }</td>
		</tr>
		<tr>
			<td>히트</td>
			<td>${reply_view.bHit}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="bName" value="${reply_view.bName}"></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="bTitle" value="${reply_view.bTitle}"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea rows="10" name="bContent">${reply_view.bContent}</textarea></td>
		</tr>
		<tr>
			<td colspan=2>
				<a href="javascript:form_check()">답변</a>
				<a href="list.do">목록</a>
			</td>
		</tr>	
	</form>
</table>
</body>
</html>