package com.study.jsp.chat;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocketendpoint2")
//서블릿을 상속받은애가 아님 걍 클래스
public class WsServer2 {
		String user;
		DataSource dataSource=null;
		CDao dao = CDao.getInstance();
		
	//해쉬맵 역할을 해주는애
		static final java.util.Map<String, Session> clientMap = 
				java.util.Collections.synchronizedMap(new java.util.HashMap<String, Session>());

		public WsServer2() {
		}
		
		@OnOpen
		public void onOpen(Session session) {
			//사용자가 접속을 하면 open이야
			
			System.out.println("Open session id : "+session.getId());
			
			try {
				final Basic basic = session.getBasicRemote();
				basic.sendText("대기실에 입장했습니다.");
				
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		@OnClose
		public void onClose(Session session) {
			System.out.println("Session "+session.getId()+" has ended");
			//해쉬맵처럼
			clientMap.remove(user);
		}
		
		@OnMessage
		public void onMessage(String message, Session session) {
		//어떤 세션에서 올라오는지, 메시지가 뭔지
			
			user = message.substring(0, message.indexOf("|"));
			clientMap.put(user, session);
			String msg = message.substring(message.indexOf("|"),message.length());		
			System.out.println("Message from : "+session.getId()+" : "+message);
			
			try {
				//인수로 넘어온 메세지를 각 클라이언트로 보낸다
				final Basic basic = session.getBasicRemote();
				basic.sendText("to : "+message);   //내가 보낼 때 to
				
			}catch(IOException ex) {
				ex.printStackTrace();
			}
			//sendAllSessionToMessage(session, message);
			sendRoomSessionToMessage(session,message,user);
		}
		//같은 방 애들끼리만
		private void sendRoomSessionToMessage(Session self, String message, String user) {
			try {
				//같은방 친구들만 뿌리자
				int myroom = dao.myRoomNo(user);
				System.out.println("지금 나의 방번호 : "+myroom);
				ArrayList<String> dtos = dao.userlist(myroom);
				Iterator<String> it = clientMap.keySet().iterator();
				Session session;
				
				while(it.hasNext()) {
					String name = it.next();
					session=clientMap.get(name);
					
					for(int i=0;i<dtos.size();i++) {
						if(!session.getId().equals(self.getId()) && name.equals(dtos.get(i))) {
							session.getBasicRemote().sendText("All : "+message);
						}
					}
				}
				
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		//공지사항
		private void sendAllSessionToMessage(Session self, String message) {
			try {
				
				Iterator<String> it = clientMap.keySet().iterator();
				Session session;
				
				while(it.hasNext()) {
					String name = it.next();
					session=clientMap.get(name);
					if(!session.getId().equals(self.getId())) {
						session.getBasicRemote().sendText("All : "+message);
					}
				}
				
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		@OnError
		public void OnError( Throwable e, Session session) {
			//에러페이지로 대체할까
		}
		
		
}
