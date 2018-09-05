<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

function onDownload(idx,fPath) {
	var o = document.getElementById("ifrm_filedown");	
	o.src = "download.do?bId="+idx+"&fPath="+fPath;
}
</script>
<style>
	.sub-name1{
		border-width:1px;
		border-style:solid;       /* solid */
		border-color:black;
	}

</style>

</head>
<body>
<c:choose>
	<c:when test ="${content_view.bPass!='|none'}">
		<jsp:forward page="passwordCheck.jsp"/>
		<!-- 여기에 bPass 가져가고 싶다!! -->
	</c:when>
</c:choose>	

<div class=container>
<iframe id="ifrm_filedown" style="position:absolute;z-index:1;visibility:hidden;"></iframe>
<div class="table-responsive-sm">
	<table class="table" width="800" cellpadding="0" cellspacing="0" border-bottom="1">
			<input type="hidden" name="bId" value="${content_view.bId }">
			<thead>
			    <tr>
			      <th scope="col" class="sub-name1">No. ${content_view.bId }</th>
			      <th scope="col" class="sub-name1">Title. ${content_view.bTitle}</th>
			    </tr>
			 </thead>
			
			<tr>
				<th scope="row" class="sub-name1">조회수</th>
				<td colspan="4" class="sub-name1">${content_view.bHit}</td>
			</tr>
			<tr>
				<th scope="row" class="sub-name1">작성자</th>
				<td colspan="4" class="sub-name1">${content_view.bName}</td>
			</tr>
			<tr>
				<th scope="row" class="sub-name1">내용</th>
				<td colspan="4" class="sub-name1">${content_view.bContent}</td>
			</tr>
			<tr>
			
				<th scope="row" class="sub-name1">첨부</th>
				<c:choose>
						<c:when test ="${content_view.fType=='none'}">
							<td colspan="4" width=500px></td>
						</c:when>
						
						<c:when test="${content_view.fType=='image'}">
							<td colspan="4" align="left" width=500px class="sub-name1"><img src="./fileFolder/${content_view.fPath}" width=500px;/><br>
							Download : <a href="#" onclick="onDownload('${content_view.bId}','${content_view.fPath}')">${content_view.fPath}</a>
							</td>
						</c:when>
						
						<c:when test="${content_view.fType!='image'}">
							
							<td>Download : <a href="#" onclick="onDownload('${content_view.bId}','${content_view.fPath}')">${content_view.fPath}</a></td>
						</c:when>
				</c:choose>	
				
			</tr>
			<tr>
				<td colspan=4 align="right">
					<a href="modify_view.do?bId=${content_view.bId }" class="btn btn-outline-secondary btn-sm">수정</a>&nbsp;&nbsp;
					<a href="list.do?page=<%=session.getAttribute("cpage")%>&search=<%=session.getAttribute("search")%>&input=<%=session.getAttribute("input")%>" class="btn btn-outline-secondary btn-sm">목록보기</a>&nbsp;&nbsp;
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