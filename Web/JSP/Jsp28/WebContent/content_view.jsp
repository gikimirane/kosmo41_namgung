<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

<script>

function button_event(){
	if (confirm("정말 삭제하시겠습니까?") == true){    //확인
		document.location.href="./delete.do?bId=${content_view.bId}";
	}else{   //취소
	    return;
	}
}
	
</script>

</head>
<body>
<div class=container>
<div class="table-responsive-sm">
	<table class="table" width="300" cellpadding="0" cellspacing="0" border-bottom="1">
			<input type="hidden" name="bId" value="${content_view.bId }">
			<thead>
			    <tr>
			      <th scope="col">No. ${content_view.bId }</th>
			      <th scope="col">Title. ${content_view.bTitle}</th>
			    </tr>
			 </thead>
			
			<tr>
				<th scope="row">조회수</th>
				<td>${content_view.bHit}</td>
			</tr>
			<tr>
				<th scope="row">작성자</th>
				<td>${content_view.bName}</td>
			</tr>
			<tr>
				<th scope="row">내용</th>
				<td>${content_view.bContent}</td>
			</tr>
			<tr>
				<td colspan=2 align="right">
					<a href="modify_view.do?bId=${content_view.bId }" class="btn btn-outline-secondary btn-sm">수정</a>&nbsp;&nbsp;
					<a href="list.do?page=<%=session.getAttribute("cpage")%>" class="btn btn-outline-secondary btn-sm">목록보기</a>&nbsp;&nbsp;
					<input type=button class="btn btn-outline-secondary btn-sm" value="삭제" onclick="javascript:button_event()">&nbsp;&nbsp;
					<a href="reply_view.do?bId=${content_view.bId}" class="btn btn-outline-secondary btn-sm">답변</a>&nbsp;&nbsp;
				</td>
			</tr>	
		</table>
	</div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>