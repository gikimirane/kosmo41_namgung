package com.study.jsp.chat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.chat")
public class CFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request,response);
	}
	public void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com=uri.substring(conPath.length());
		System.out.println("com은?"+com);
		CCommand command;
		
		if(com.equals("/join.chat")) {
			command = new CJoinCommand();
			command.execute(request, response);
			
		}else if(com.equals("/exit.chat")) {
			command = new CExitCommand();
			command.execute(request, response);
			
		}else if(com.equals("/newroom.chat")) {
			command = new CNewRoomCommand();
			command.execute(request, response);
			
		}else if(com.equals("/deleteroom.chat")) {
			command = new CDeleteRoomCommand();
			command.execute(request, response);
			
		}else if(com.equals("/roomlist.chat")) {
			command = new CRoomListCommand();
			command.execute(request, response);
			
		}else if(com.equals("/Entrance.chat")) {
			command = new CEntranceCommand();
			command.execute(request, response);
		}else if(com.equals("/waitinglist.chat")) {
			command = new CWaitinglistCommand();
			command.execute(request, response);
		}else if(com.equals("/roomUserlist.chat")) {
			command = new CRoomUserListCommand();
			command.execute(request, response);
		}else if(com.equals("/invitation.chat")) {
			command = new CInvitationCommand();
			command.execute(request, response);
		}else if(com.equals("/reject.chat")) {
			command = new CRejectCommand();
			command.execute(request, response);
		}else if(com.equals("/allroomlist.chat")) {
			command = new CAllRoomListCommand();
			command.execute(request, response);
		}else if(com.equals("/passwordcheck.chat")) {
			command = new CPassWordCommand();
			command.execute(request, response);
		}else if(com.equals("/checkmyroom.chat")) {
			command = new CMyRoomCommand();
			command.execute(request, response);
		}else if(com.equals("/alluserlist.chat")) {
			command = new CAllUserCommand();
			command.execute(request, response);
		}else if(com.equals("/blockword.chat")) {
			command = new CBlockWordCommand();
			command.execute(request, response);
		}else if(com.equals("/ownerchange.chat")) {
			command = new COwnerChange();
			command.execute(request, response);
		}
	}
}
