<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.study.*" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
function paycheck(){
	var client = document.getElementsByName('client');
	var code="";
	
	for(var i=0;i<client.length;i++) {
	    if(client[i].checked) {
        	code = client[i].value;
	    }
	}
	
	if(code==""){
		alert("code를 선택하세요.");
		return;
	}
	
	var data ="code="+code+"&status=결제완료";
	
	$.ajax({
		url : './change.ad',
		type : 'POST',
		data : data,
		dataType : 'json',
		success : function(json){
			var results = eval(json);
			if(results.results == "ok"){
				alert(results.desc);
			}else if(results.results=="fail"){
				alert(results.desc);
			}
		}
	}); 
	
	var data1 ="code="+code;
	$.ajax({
		url : './payclient.ad',
		type : 'POST',
		data : data1,
		dataType : 'json',
		success : function(json){
			var results = eval(json);
			if(results.results == "ok"){
				alert(results.desc);
				location.href="adminlogin.ad";
				return;
			}else if(results.results=="fail"){
				alert(results.desc);
				location.href="adminlogin.ad";
				return;
			}
		}
	}); 
}

function finishcheck(){
	var client = document.getElementsByName('client');
	var code="";
	
	for(var i=0;i<client.length;i++) {
	    if(client[i].checked) {
        	code = client[i].value;
	    }
	}

	if(code==""){
		alert("code를 선택하세요.");
		return;
	}
	
	var data ="code="+code+"&status=주문종료";
	
	$.ajax({
		url : './change.ad',
		type : 'POST',
		data : data,
		dataType : 'json',
		success : function(json){
			var results = eval(json);
			if(results.results == "ok"){
				alert(results.desc);
				location.href="adminlogin.ad";
				return;
			}else if(results.results=="fail"){
				alert(results.desc);
				location.href="adminlogin.ad";
				return;
			}
		}
	}); 
}

function drinkcheck(){
	var client = document.getElementsByName('client');
	var code="";
	
	for(var i=0;i<client.length;i++) {
	    if(client[i].checked) {
        	code = client[i].value;
	    }
	}
	
	if(code==""){
		alert("code를 선택하세요.");
		return;
	}
	
	var data ="code="+code;
	$.ajax({
		url : './pushclient.ad',
		type : 'POST',
		data : data,
		dataType : 'json',
		success : function(json){
			var results = eval(json);
			if(results.results == "ok"){
				alert(results.desc);
				location.href="adminlogin.ad";
				return;
			}else if(results.results=="fail"){
				alert(results.desc);
				location.href="adminlogin.ad";
				return;
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
		<h1>PLACIDO ADMIN</h1><a href="./chargeCard.ad" class="btn btn-outline-secondary btn-sm">카드 충전</a>
		<hr>
		<div class="row">
			<div class="col"><a href="./adminlogin.ad" class="btn btn-outline-secondary btn-sm">주문 목록</a>
			&nbsp;<a href="#" onclick="paycheck()" class="btn btn-outline-secondary btn-sm">결제 완료</a>
			&nbsp;<a href="#" onclick="drinkcheck()" class="btn btn-outline-secondary btn-sm">음료 완료</a>
			&nbsp;<a href="#" onclick="finishcheck()" class="btn btn-outline-secondary btn-sm">주문 종료</a>
			</div>
			
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
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="dto">
			<tr>
				
				<td width="50"><input type="radio" id="client" name="client" value="${dto.code }">&nbsp;&nbsp;${dto.code}</td>
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