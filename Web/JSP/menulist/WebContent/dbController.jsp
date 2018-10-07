<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "com.study.android.ADao"%> 
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
		
	}else if(order.equals("sendpush")){
		
		//obj=db.sendpush();
	}else if(order.equals("myorderlist")){
		String client = request.getParameter("client");
		client = URLDecoder.decode(client,"UTF-8");
		obj = db.myorderlist(client);
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