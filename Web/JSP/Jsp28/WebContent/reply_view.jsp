<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://cdn.ckeditor.com/4.10.0/standard/ckeditor.js"></script>
<title>Insert title here</title>
 	<meta charset="utf-8">
 	<!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>


</head>
<body>
<div class="container">
<table>
	<form action ="reply.do" method="post">
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
			<td><input type="text" name="bName"></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="bTitle" value="${reply_view.bTitle} - "  ></td>
		</tr>
		<tr>
			<td>원문 내용</td>
			<td>${reply_view.bContent}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
			
			<textarea name="bContent" id="editor1" rows="10" cols="80">${content_view.bContent}</textarea>
			<script> CKEDITOR.replace( 'editor1' ); </script>
			</td>
		</tr>
		<tr>
			<td colspan=2><input type="submit" value="답변"><a href="list.do?page=<%=session.getAttribute("cpage")%>">목록</a></td>
		</tr>	
	</form>
</table>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</body>
</html>