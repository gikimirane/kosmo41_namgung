<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="./naver-editor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<title>Insert title here</title>
 	<meta charset="utf-8">
 	<!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

<script>
	function form_check(){
		
		oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
		document.modify_form.submit();
	}
</script>

</head>
<body>
<div class="container">
<table width=800>
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
			<td>
			<% if(session.getAttribute("name")!=null) {%>
				<input type="text" name="bName" class="form-control" value="<%=session.getAttribute("name")%>" readonly size="50">
			<%} else {%>
				<input type="text" name="bName" class="form-control" size="50">
			<%} %>
			</td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" class="form-control" name="bTitle" value="${reply_view.bTitle} - " ></td>
		</tr>
		<tr>
			<td>원문 내용</td>
			<td>${reply_view.bContent}</td>
			
		</tr>
		<tr>
			<td>내용</td>
			<td>		
			<textarea name="bContent" id="ir1" rows="10" cols="80">${content_view.bContent}</textarea>
			<script type="text/javascript">
				var oEditors = [];
				nhn.husky.EZCreator.createInIFrame({
				    oAppRef: oEditors,
				    elPlaceHolder: "ir1",
				    sSkinURI: "./naver-editor/SmartEditor2Skin.html",
				    fCreator: "createSEditor2"
				});
			</script>
			</td>
		</tr>
		<tr>
			<td colspan=2 align="right"><input type="submit" value="답변" class="btn btn-outline-secondary btn-sm">&nbsp;
			<a href="list.do?page=<%=session.getAttribute("cpage")%>&search=<%=session.getAttribute("search")%>&input=<%=session.getAttribute("input")%>" class="btn btn-outline-secondary btn-sm">목록보기</a>
			
			</td>
		</tr>	
	</form>
</table>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</body>
</html>