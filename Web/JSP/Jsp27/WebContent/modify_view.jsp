<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function form_check(){
		document.modify_form.submit();
		//체크할 내용 확인하고 난 다음에 submit 하기
	}
</script>
</head>
<body>
<table width="500" cellpadding="0" cellspacing="0" border="1">
	<form name="modify_form" action="modify.do" method="post">
	
		<input type="hidden" name="bId" value="${content_view.bId }">
		<tr>
			<td>번호</td>
			<td>${content_view.bId }</td>
		</tr>
		<tr>
			<td>히트</td>
			<td>${content_view.bHit}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="bName" value="${content_view.bName }"></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="bTitle" value="${content_view.bTitle }"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea rows="10" name="bContent">${content_view.bContent}</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<a href="javascript:form_check()">수정완료</a>&nbsp;&nbsp;
				<a href="content_view.do?bId=${content_view.bId}">취소</a>&nbsp;&nbsp;
				<a href="list.do">목록보기</a>&nbsp;&nbsp;
			</td>
		</tr>	
	</form>
</table>
</body>
</html>