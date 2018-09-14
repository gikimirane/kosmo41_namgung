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

function logincheck(){
	var name='<%=session.getAttribute("name") %>';

	if(name!="null"){
		document.location.href="write_view.do";
	}else if(name=="null"){
		alert("로그인 시 글 작성이 가능합니다.");
	}
}

function radio_chk() {
    var size = document.getElementsByName("sear_check").length;
    var input=document.getElementById("input").value;
    var check;
    
    if(input==""){
    	alert("검색 내용을 입력하세요.");
    	return;
    }

    for(var i=0;i<size;i++) {
         if(document.getElementsByName("sear_check")[i].checked) {
             check = document.getElementsByName("sear_check")[i].value;
             break;
         }
    }    

    var search = "./list.do?search="+check+"&input="+input;
    document.location.href=search;
    
}
</script>

</head>

<body>
<jsp:include page="home_a.jsp" />
	<div class="container">
		<table class="table table-hover">
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
							<a href="content_view.do?bId=${dto.bId}" name="title" id="title">${dto.bTitle}<span class="badge badge-danger">hit!</span></a></td>
						</c:when>
						
						<c:otherwise>
							<c:forEach begin="1" end ="${dto.bIndent}">-</c:forEach>
							<a href="content_view.do?bId=${dto.bId}" name="title" id="title">${dto.bTitle}</a></td>
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
					<a href="#" class="btn btn-outline-secondary btn-sm" onclick="logincheck()">글작성</a>
				</td>
			</tr>
			<tr>
				<td colspan="5" align="right">
				<input type="radio" name="sear_check" id="sear_check" value="bTitle" checked="checked"/>제목 &nbsp;
				<input type="radio" name="sear_check" id="sear_check" value="bContent"/>내용
				<input type="text" name="input" id="input">&nbsp;&nbsp; 
			
				<input type="button" value="검색" class="btn btn-outline-secondary btn-sm" onclick="javascript:radio_chk()">
				<% if(session.getAttribute("input1")!=null){ %>
				<input type="button" value="전체보기" class="btn btn-outline-secondary btn-sm" onclick="javascript:document.location.href='s_delete.jsp'">
				<%} %>
		
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
							
							<a href="list.do?page=1&search=<%=session.getAttribute("search1")%>&input=<%=session.getAttribute("input1")%>">[ &lt;&lt; ]</a> 
						</c:otherwise>
					</c:choose>
					
					<!-- 이전 -->
					<c:choose>
						<c:when test ="${(page.curPage-1)<1}">
							[ &lt; ] 
						</c:when>
						<c:otherwise>
							<a href="list.do?page=${page.curPage-1}&search=<%=session.getAttribute("search1")%>&input=<%=session.getAttribute("input1")%>">[ &lt; ]</a> 
						</c:otherwise>
					</c:choose>
		
					<!-- 개별 -->
					<c:forEach var="fEach" begin="${page.startPage}" end="${page.endPage}" step="1">
						<c:choose>
							<c:when test="${page.curPage==fEach}">
								[ ${fEach } ]&nbsp;
							</c:when>
							
							<c:otherwise>
								<a href="list.do?page=${fEach}&search=<%=session.getAttribute("search1")%>&input=<%=session.getAttribute("input1")%>">[ ${fEach } ]</a> &nbsp;
							</c:otherwise>
						</c:choose>
					</c:forEach>
					
					<!-- 다음 -->
									
					<c:choose>
						<c:when test ="${(page.curPage+1) > page.totalPage}">
							[ &gt; ] 
						</c:when>
						<c:otherwise>
							<a href="list.do?page=${page.curPage+1}&search=<%=session.getAttribute("search1")%>&input=<%=session.getAttribute("input1")%>">[ &gt; ]</a>
						</c:otherwise>
					</c:choose>
					
					<!-- 끝 -->
					<c:choose>
						<c:when test ="${page.curPage==page.totalPage}">
							[ &gt;&gt; ]
						</c:when>
						<c:otherwise>
							<a href="list.do?page=${page.totalPage }&search=<%=session.getAttribute("search1")%>&input=<%=session.getAttribute("input1")%>">[ &gt;&gt; ]</a>  
						</c:otherwise>
					</c:choose>						
				</td>
			</tr>
		</thead>		
	</table>
</div>
<jsp:include page="footer.jsp" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</body>
</html>