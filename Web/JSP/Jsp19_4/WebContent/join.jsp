<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP/Servlet19-1</title>
		
	<script src="http://code.jquery.com/jquery.js"></script>	
	<script>
		
		var count =0;	
		function duplication(){
			
			var id = document.getElementById("id");
			$.ajax({
				url : 'DuplicateProcess',
				type : 'POST',
				data : id,
				dataType : 'json',
				success : function(json){
					var results = eval(json);
					count++;
					if(results[0].results == "ok"){
						alert(results[0].desc);
						
					} else {
						alert(results[0].desc);
					}
				}
			});
		}
	
		function form_check() {
			if(count>0){
				submit_ajax();
			}else {
				alert("아이디 중복확인이 필요합니다.");
			}
		}
	
		function submit_ajax(){
			var queryString = $("#JoinProcess").serialize() ;
			$.ajax({
				url : 'JoinProcess',
				type : 'POST',
				data : queryString,
				dataType : 'json',
				success : function(json){
					var results = eval(json);

					if(results[0].results == "ok"){
						alert("정상 가입되었습니다.")
						window.location.replace("login.jsp");
					} else {
						alert(results[0].desc);
					}
					
				}
			});
		}
	</script>
	</head>
	<body>
		<form name="JoinProcess" id="JoinProcess" >
			아&nbsp;이&nbsp;디 : <input type="text" name="id" size="10"><input type="button" value="중복체크" onclick="duplication()"><br/>
			비밀번호 : <input type="text" name="pw" size="10"><br/>
			이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름 : <input type="text" name="name" size="10"><br/>
			전화번호 :  
			<select name="phone1">
			 	<option value="010">010</option>
			 	<option value="016">016</option>
			 	<option value="017">017</option>
			 	<option value="018">018</option>
			 	<option value="019">019</option>
			 	<option value="011">011</option>
			 </select> - 
			 <input type="text" name="phone2" size="5"> -
			 <input type="text" name="phone3" size="5"> <br>
			성별구분 : <input type="radio" name="gender" value="woman"> 여 &nbsp;
					   <input type="radio" name="gender" value="man"> 남 <br>
			 <input type="button" value="회원가입" onclick="form_check()" />
		</form>
		
	</body>
</html>