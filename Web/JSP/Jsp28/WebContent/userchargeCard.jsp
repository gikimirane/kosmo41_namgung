<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.study.*" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>

function chargemoney(){
	var client = document.getElementsByName('client');
	var money = document.getElementById("money").value;
	var id="";
	
	
	for(var i=0;i<client.length;i++) {
	    if(client[i].checked) {
        	id = client[i].value;
	    }
	}
	
	var regNumber = /^[0-9]*$/;
	  
	  
	if(!regNumber.test(money)) {
	    alert('숫자만 입력해주세요.');
	    return;
	}
	if(id==""){
		alert("ID를 선택하세요.");
		return;
	}
	if(money==""){
		alert("금액을 입력하세요.");
		return;
	}
	
	var data ="id="+id+"&money="+money;
	
	$.ajax({
		url : './chargemoney.ad',
		type : 'POST',
		data : data,
		dataType : 'json',
		success : function(json){
			var results = eval(json);
			if(results.results=="ok"){
				alert("충전 완료되었습니다.");
				history.go(0);
			}else {
				alert("다시 시도해 주세요.");
				history.go(0);
			}
		}
	}); 
}

</script>
<style>
.align-center { text-align: center; }

* {
	margin:0px;
	padding:0px;
}

</style>
<title>ADMIN PUSH PAGE</title>
 	<meta charset="utf-8">
 	<!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<!--  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>-->
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<body>
	<div class="container">
		<h1>PLACIDO ADMIN</h1>
		<hr>
		<div class="row">
			<div class="col">
				&nbsp;충전금액 : &nbsp;<input type="text" id="money" name="money">
				&nbsp;<a href="#" onclick="chargemoney()" class="btn btn-outline-secondary btn-sm">카드 충전</a>
			</div>
			</div>
  		</div>
  		
	</div>
	<div class="container">
		
		<table class="table table-hover">
			<thead>
			<tr>
				<th scope="col">아이디</th>
				<th scope="col">이름</th>
				<th scope="col">전화번호</th>
				<th scope="col">이메일</th>
				<th scope="col">잔액</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${userlist}" var="userdto">
			<tr>
				<td width="50"><input type="radio" id="client" name="client" value="${userdto.id }">&nbsp;&nbsp;${userdto.id}</td>
				<td width="100">${userdto.name}</td>
				<td width="50">${userdto.phone}</td>
				<td width="50">${userdto.email}</td>
				<td width="50">${userdto.point}</td>	
			</tr>
			</c:forEach>
			</tbody>
		</table>
	
	
</body>
</html>