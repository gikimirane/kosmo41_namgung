<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.study.*" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>

</script>
<title>ADMIN PUSH PAGE</title>
 	<meta charset="utf-8">
 	<!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<!--  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>-->
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>

/* function orderlist(){
	orderlist.innerHTML = "";
	$.ajax({
		url : './adminlogin.ad',
		type : 'POST',
		dataType : 'json',
		error : function(xhr,status,error){
			alert(error);
			alert("error");
		},
		success : function(json){
			var results = eval(json);
			var html = "<tr><td>전체 주문 보기</td></tr>";
				
				for(var i=0;i<results.length;i ++) {
					html += "<tr>";
					html += "<td><input type=\"radio\" id=\"alluserlist\" name=\"alluserlist\" value=\""+results[i].user+"\">"+results[i].user + "</td>";
					html += "</tr>";
				}
			$("#alluser").append(html); 
		}
	});
	
} */
</script>
<body>
	<div class="container">
		<h1>ADMIN PAGE 입니다.</h1>
		<div class="row">
			<a href="./adminlogin.ad" class="btn btn-outline-secondary btn-sm">ORDER LIST</a>&nbsp;
  		</div>
  		<div class="row">
  			<div class="col" id="orderlist" style="overflow:auto;background-color:#F3E9E8"></div>
  		</div>
	</div>
	
	<div class="container">
		<table class="table table-hover">
			<thead>
			<tr>
				<th scope="col">코드</th>
				<th scope="col">메뉴</th>
				<th scope="col">금액</th>
				<th scope="col">상태</th>
				<th scope="col">주문날짜</th>
				<!-- <th scope="col">고객정보</th>
 -->			</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="dto">
			<tr>
				<td width="50">${dto.code}</td>
				<td width="100">${dto.menu}</td>
				<td width="50">${dto.price}</td>
				<td width="50">${dto.status}</td>
				<td width="50">${dto.date}</td>
				<%-- <td width="50">${dto.clientno}</td> --%>
			</tr>
			</c:forEach>
			</tbody>
	</table>
	
	
</body>
</html>