<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.Enumeration" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%
	String path=request.getRealPath("fileFolder");
	out.println(path);
	int size = 1024 * 1024 * 10;
	String file="";
	String oriFile="";
	System.out.println(path);
	try{
		MultipartRequest multi = new MultipartRequest(request,path,size,"UTF-8",
													new DefaultFileRenamePolicy());
		Enumeration files = multi.getFileNames();
		String str = (String)files.nextElement();
		
		file=multi.getFilesystemName(str);
		oriFile=multi.getOriginalFileName(str);
		
	}catch(Exception e){
		e.printStackTrace();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
file upload success!
</body>
</html>