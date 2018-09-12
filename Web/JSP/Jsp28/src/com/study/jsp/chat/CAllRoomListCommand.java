package com.study.jsp.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CAllRoomListCommand implements CCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();	
		CDao dao = CDao.getInstance();
		
		ArrayList<RoomDto> list=dao.roomlist();
			
		String data="[";
		for(int i=0;i<list.size();i++) {
			if(list.get(i).rno.equals("0")){
				data += "{\"room\":\"대기실\",\"lock\":\"공개\",\"limit\":\"무제한\"},";
			}else {
				data += "{\"room\":\""+list.get(i).rno+"\",\"lock\":\""+list.get(i).open_type+"\",\"limit\":\""+list.get(i).user_limit+"\"},";
			}
			
		}
		
		data = data.substring(0,data.length()-1)+"]";
		System.out.println(data);
		writer.println(data);
	}

}
