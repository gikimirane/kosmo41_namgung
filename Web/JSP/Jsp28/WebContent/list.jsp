<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 	<!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

</head>
<style>
	.sub-name1 {
		font-size:30px;
		font-weight:bold;
		
		width:200px;     /* 90% */
		height:100px;
		
		margin:50px;
		
		border-width:5px;
		border-style:dashed;       /* solid */
		border-color:red;
		border-radius:10px 10px 10px 10px;
	}
</style>
<body>

<div class="container">

	<div class="table table-hover">
		<table width="800">
			<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">이름</th>
				<th scope="col">제목</th>
				<th scope="col">날짜</th>
				<th scope="col">조회수</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="dto">
			<tr>
				<th scope="row">${dto.bId}</th>
				<td>${dto.bName}</td>
				<td>
					<c:choose>
						<c:when test ="${dto.bHit>=20}">
							<c:forEach begin="1" end ="${dto.bIndent}">-</c:forEach>
							<a href="content_view.do?bId=${dto.bId}" >${dto.bTitle}<span class="badge badge-danger">hit!</span></a></td>
						</c:when>
		
						<c:otherwise>
							<c:forEach begin="1" end ="${dto.bIndent}">-</c:forEach>
							<a href="content_view.do?bId=${dto.bId}" >${dto.bTitle}</a></td>
						</c:otherwise>
					</c:choose>
					
				<td>${dto.bDate}</td>
				
				<td>${dto.bHit}</td>
			</tr>
			</c:forEach>
			</tbody>
			<thead>
			<tr>
				<td colspan="5" align="right">
					<a href="write_view.do" class="btn btn-outline-secondary btn-sm">글작성</a>
				</td>
			</tr>
			<tr>
				<td colspan="5" align="center">
					<!-- 처음 -->
					<c:choose>
						<c:when test ="${(page.curPage-1)<1}">
							[ &lt;&lt; ]
						</c:when>
						<c:otherwise>
							<a href="list.do?page=1">[ &lt;&lt; ]</a> 
						</c:otherwise>
					</c:choose>
					
					<!-- 이전 -->
					<c:choose>
						<c:when test ="${(page.curPage-1)<1}">
							[ &lt; ] 
						</c:when>
						<c:otherwise>
							<a href="list.do?page=${page.curPage-1}">[ &lt; ]</a> 
						</c:otherwise>
					</c:choose>
		
					<!-- 개별 -->
					<c:forEach var="fEach" begin="${page.startPage }" end="${page.endPage }" step="1">
						<c:choose>
							<c:when test="${page.curPage==fEach}">
								[ ${fEach } ]&nbsp;
							</c:when>
							
							<c:otherwise>
								<a href="list.do?page=${fEach}">[ ${fEach } ]</a> &nbsp;
							</c:otherwise>
						</c:choose>
					</c:forEach>
					
					<!-- 다음 -->
					<c:choose>
						<c:when test ="${(page.curPage+1) > page.totalPage}">
							[ &gt; ] 
						</c:when>
						<c:otherwise>
							<a href="list.do?page=${page.curPage+1}">[ &gt; ]</a>
						</c:otherwise>
					</c:choose>
					
					<!-- 끝 -->
					<c:choose>
						<c:when test ="${page.curPage==page.totalPage}">
							[ &gt;&gt; ]
						</c:when>
						<c:otherwise>
							<a href="list.do?page=${page.totalPage }">[ &gt;&gt; ]</a>  
						</c:otherwise>
					</c:choose>						
				</td>
			</tr>
		</thead>		
	</table>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</body>
</html>