package com.study.jsp1.command;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;
import com.study.jsp1.BDao;

public class BWriteCommand implements BCommand {

	public BWriteCommand() {
		
	}
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
			
		String savePath=request.getRealPath("fileFolder");
		
		int size = 1024 * 1024 * 10;
		String file="";
		String oriFile="";
		String type="";
		String bName="";
		String bTitle="";
		String bContent="";
		String fPath="";
		String fType="";
		String path="";
		String bPass = "none";
		MultipartRequest multi = null;
		try{
			multi = new MultipartRequest(request,savePath,size,"UTF-8",
														new DefaultFileRenamePolicy());
			bName=multi.getParameter("bName");
			bTitle=multi.getParameter("bTitle");
			bContent = multi.getParameter("bContent");
			bPass = multi.getParameter("bPass");
			if(bPass.length()==0) {
				bPass="|none";
			}
						
			Enumeration files = multi.getFileNames();
			String str = (String)files.nextElement();
			
			file=multi.getFilesystemName(str);
			oriFile=multi.getOriginalFileName(str);
			type=multi.getContentType(str);
			type=type.split("/")[0];
			path = savePath+"\\"+file;
		}catch(Exception e){
			type="none";
			file="none";
		}

		BDao dao = BDao.getInstance();
		dao.write(bName,bTitle,bContent,type,file,bPass);
		System.out.println("bwriteCommand 끝!");
	}
}
