package com.study.jsp.chat;
import java.io.IOException;

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
	
	//해쉬맵 역할을 해주는애
		static final java.util.Set<Session> sessions = 
				java.util.Collections.synchronizedSet(new java.util.HashSet<Session>());
		
		@OnOpen
		public void onOpen(Session session) {
			//사용자가 접속을 하면 open이야
			
			System.out.println("Open session id : "+session.getId());
			
			try {
				final Basic basic = session.getBasicRemote();
				basic.sendText("Connection Established");
			
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			//세션에다가 add해준거래
			sessions.add(session);
		}
		
		@OnClose
		public void onClose(Session session) {
			System.out.println("Session "+session.getId()+" has ended");
			//해쉬맵처럼
			sessions.remove(session);
		}
		
		@OnMessage
		public void onMessage(String message, Session session) {
			//어떤 세션에서 올라오는지, 메시지가 뭔지
			System.out.println("Message from : "+session.getId()+" : "+message);
			try {
				//인수로 넘어온 메세지를 각 클라이언트로 보낸다
				final Basic basic = session.getBasicRemote();
				basic.sendText("to : "+message);   //내가 보낼 때 to
			}catch(IOException ex) {
				ex.printStackTrace();
			}
			sendAllSessionToMessage(session, message);
		}
		
		private void sendAllSessionToMessage(Session self, String message) {
			try {
				for(Session session : WsServer2.sessions) {
					if(! self.getId().equals(session.getId())) {
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
