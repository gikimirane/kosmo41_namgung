<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.Enumeration" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%
	String savePath="D:\\yh9189\\Study\\Web\\JSP\\Jsp28\\WebContent\\fileFolder";
	
	int size = 1024 * 1024 * 10;
	String file="";
	String oriFile="";
	
	try{
		MultipartRequest multi = new MultipartRequest(request,savePath,size,"UTF-8",
													new DefaultFileRenamePolicy());
		Enumeration files = multi.getFileNames();
		String str = (String)files.nextElement();
		
		file=multi.getFilesystemName(str);
		oriFile=multi.getOriginalFileName(str);

		String bName=multi.getParameter("bName");
		String bTitle=multi.getParameter("bTitle");
		String bContent = multi.getParameter("bContent");
			
		System.out.println("fileform"+bName);
		
		session.setAttribute("bName", bName);
		session.setAttribute("bTitle",bTitle);
		session.setAttribute("bContent",bContent);
		response.sendRedirect("./write.do");
		
	}catch(Exception e){
		e.printStackTrace();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<body>

</body>
</head>
<body>



</body>
</html>