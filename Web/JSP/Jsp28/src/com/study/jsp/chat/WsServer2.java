package com.study.jsp.chat;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		public void onClose(Session session) throws SQLException {
			
			dao.deleteroom(user);
			dao.exit(user);
			clientMap.remove(user);
			System.out.println("Session "+session.getId()+" has ended");
			
		}
		
		@OnMessage
		public void onMessage(String message, Session session) throws SQLException, IOException {
		//어떤 세션에서 올라오는지, 메시지가 뭔지
			System.out.println("최초의 message : "+message);
			user = message.substring(0, message.indexOf("|"));
			String msg = message.substring(message.indexOf("|"),message.length());		
			
			int index = message.indexOf("|")+1;
			String com = message.substring(index,message.indexOf("@"));
			
			System.out.println("com : "+com);
			final Basic basic = session.getBasicRemote();
			if(com.equals("!입장")) {
				
				clientMap.put(user, session);
				message = user+"님 입장했습니다.";
				
			}else if(com.equals("!방목록")) {
				String roomlist = dao.roomlist(user);
				System.out.println(roomlist);
				message = com+" : "+roomlist;
				
			}else if(com.equals("!방입장")){
				
				String room = message.substring(message.indexOf("@")+1);
				
				int uCount=dao.changeRoom(user,room);
				System.out.println("uCount 는? "+uCount);
				if(uCount==1) {
					if(room.equals("0")) {
						message = user+"님의 현재 위치는 대기실 입니다.";
						basic.sendText("> "+message);
						return;
					}else {
						message = user+"님이 방에 입장했습니다.";
						basic.sendText("> "+message);
						return;
					}
				}else if(uCount==0) {
					message = "유효하지 않은 방입니다. 다시 확인해주시기 바랍니다.";
					basic.sendText("> "+message);
					return;
				}else if(uCount==2) {
					message = "입장 가능한 인원이 초과된 방입니다.";
					basic.sendText("> "+message);
					return;
				}
								
			}else if(com.equals("!메세지")){
				String usermsg = message.substring(message.indexOf("@")+1);
				message = user+" : "+usermsg;
				
			}else if(com.equals("!방만들기")) {
				String[] split = message.split(",");
				String lock = split[1];
				String limit = split[2];
				String pw = split[3];
				int myroom=dao.newroom(user,lock,limit,pw);
				message = user+"님이 방장인 "+myroom+"번방이 만들어졌습니다.";
				
			}else if(com.equals("!전체사용자")){
				Iterator<String> it = clientMap.keySet().iterator();
				String list="";
				while(it.hasNext()) {
					list += ","+it.next();
				}
				message=com+" : "+list.substring(list.indexOf(",")+1,list.length());
			}else if(com.equals("!귓속말")) {
				
				message = message.substring(message.indexOf("/")+1);
				message = message.substring(message.indexOf(" ")+1);
				String msg1 = message.substring(message.indexOf(" ")+1);
				String name = message.substring(0, message.indexOf(" "));
				
				int count=whisperToMessage(session,msg1,name);
				
				return;
			
				
			}else if(com.equals("!대기실사용자")) {
				int myroom=dao.myRoomNo(user);
				message = com+" : "+dao.userlist(myroom);
				
			}else if(com.equals("!같은방사용자")) {
				int myroom = dao.myRoomNo(user);
				message = com+" : "+dao.userlist(myroom);
			}
			else {
				System.out.println("com : "+com);
				System.out.println("message : "+message);
			}
			
			try {
				//인수로 넘어온 메세지를 각 클라이언트로 보낸다
				
				basic.sendText("> "+message);   //내가 보낼 때 to
				
			}catch(IOException ex) {
				ex.printStackTrace();
			}
			
			sendRoomSessionToMessage(session,message,user);
			
			//sendAllSessionToMessage(session, message);
		}
		
		
		//같은 방 애들끼리만
		private void sendRoomSessionToMessage(Session self, String message, String user) {
			try {
				//같은방 친구들만 뿌리자
				int myroom = dao.myRoomNo(user);
				
				ArrayList<String> dtos = dao.userlist(myroom);
				Iterator<String> it = clientMap.keySet().iterator();
				Session session;
				
				while(it.hasNext()) {
					String name = it.next();
					session=clientMap.get(name);
					
					for(int i=0;i<dtos.size();i++) {
						if(!session.getId().equals(self.getId()) && name.equals(dtos.get(i))) {
							session.getBasicRemote().sendText("ROOM > "+message);
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
						session.getBasicRemote().sendText("!공지! : "+message);
					}
				}
				
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		//귓속말
		private int whisperToMessage(Session self, String message, String name) {
			int count=0;
		
			try {
				Iterator<String> it = clientMap.keySet().iterator();
				Session session = null;
				
				while(it.hasNext()) {
					if(it.next().equals(name)) {
						session=clientMap.get(name);
						count++;		
					}
				}
				
				if(count==0) {
					self.getBasicRemote().sendText(name+"님은 귓속말이 불가한 상대입니다.");
				}else {
					if(!session.getId().equals(self.getId())) {
						session.getBasicRemote().sendText(user+"님의 귓속말 : "+message);
						self.getBasicRemote().sendText(name+"님께 귓속말 : "+message);
					}
				}
				
			}catch(IOException e) {
				e.printStackTrace();
			}
			return count;
		}
		
		@OnError
		public void OnError( Throwable e, Session session) {
			//에러페이지로 대체할까
		}
		
		
}
