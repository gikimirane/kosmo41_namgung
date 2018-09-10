package com.study.jsp.chat;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp1.BDto;
import com.study.jsp1.BPageInfo;

public class CRoomListCommand implements CCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
				
		CDao dao = CDao.getInstance();
	
		ArrayList<RoomDto> dtos = dao.roomlist();
		request.setAttribute("roomlist", dtos);
		String viewPage="roomlist.jsp";
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		
		try {
			dispatcher.forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		}

	}

}
