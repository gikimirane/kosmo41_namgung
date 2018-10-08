<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "com.study.android.*"%> 
<%@page import="org.json.simple.JSONArray" %>
<%@page import="org.json.simple.JSONObject" %>   
<%@page import="java.util.*" %>
<%@ page import="java.net.URLEncoder" %> 
<%@ page import="java.net.URLDecoder" %> 
    
<%
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	
	String order = request.getParameter("order");    
	order = URLDecoder.decode(order,"UTF-8");

	ADao db = ADao.getInstance();
	JSONObject obj = new JSONObject();
	System.out.println("order "+order);
	
	
	if(order.equals("menu")){
		obj=db.menulist();
		System.out.println("obj!!"+obj.size());
	}
	else if(order.equals("orderinput")){
		HashMap<String,String> data = new HashMap<>();
		
		String menu = request.getParameter("menu");
		menu = URLDecoder.decode(menu,"UTF-8");
		String code = request.getParameter("code");
		code = URLDecoder.decode(code,"UTF-8");
		String price = request.getParameter("price");
		price = URLDecoder.decode(price,"UTF-8");
		String client = request.getParameter("client");
		client = URLDecoder.decode(client,"UTF-8");
		
		data.put("menu", menu);
		data.put("code",code);
		data.put("price",price);
		data.put("client",client);
		obj = db.inputorder(data);
		System.out.println("obj!!"+obj.size());
		
	}else if(order.equals("myorderlist")){
		
		String client = request.getParameter("client");
		client = URLDecoder.decode(client,"UTF-8");
		obj = db.myorderlist(client);
		
	}else if(order.equals("mysuccess")){
		
		String client = request.getParameter("client");
		client = URLDecoder.decode(client,"UTF-8");
		obj = db.mysuccesslist(client);
	}else if(order.equals("myfinish")){
		
		String client = request.getParameter("client");
		client = URLDecoder.decode(client,"UTF-8");
		obj = db.myfinishlist(client);
		
	}else if(order.equals("sendpush")){
		String client = request.getParameter("client");
		client = URLDecoder.decode(client,"UTF-8");
		String result=db.instancepush(client,"발급된 HAPPYCODE는 오늘이 지나면 사라집니다.");
		obj.put("result",result);
		
	}else if(order.equals("insertuser")){
		String userid = request.getParameter("userid");
		userid = URLDecoder.decode(userid,"UTF-8");
		
		String userpw = request.getParameter("userpw");
		userpw = URLDecoder.decode(userpw,"UTF-8");
		
		String userphone =request.getParameter("userphone");
		userphone = URLDecoder.decode(userphone,"UTF-8");
		
		String point = request.getParameter("point");
		point = URLDecoder.decode(point,"UTF-8");
		
		String clientno = request.getParameter("clientno");
		clientno = URLDecoder.decode(clientno,"UTF-8");
		System.out.println(userid+", "+userpw+", "+userphone+", "+point+", "+clientno);
		int result = db.searchid(userid);
		if(result==0){
			String re = db.insertuser(userid,userpw,userphone,point,clientno);	
			obj.put("result",re);
		}else {
			obj.put("result","중복");
		}
		
	}else if(order.equals("trylogin")){
		String userid = request.getParameter("userid");
		userid = URLDecoder.decode(userid,"UTF-8");
		
		String userpw = request.getParameter("userpw");
		userpw = URLDecoder.decode(userpw,"UTF-8");
		System.out.println(userid+", "+userpw);
		
		int result=0;
		result=db.tryLogin(userid,userpw);
		if(result==1){
			obj.put("result","성공");
		}else if(result==0){
			obj.put("result","실패");
		}
	}
	out.println(obj.toJSONString());
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>