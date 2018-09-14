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
			
			dao.deleteroom1(user);
			dao.exit(user);
			dao.SQLCall("delete from offen_lang where owner='"+user+"'");
			
			clientMap.remove(user);
			System.out.println("Session "+session.getId()+" has ended");
			
		}
		
		@OnMessage
		public void onMessage(String message, Session session) throws SQLException, IOException {
		//어떤 세션에서 올라오는지, 메시지가 뭔지
			
			message = dao.badWordServer(message);
			user = message.substring(0, message.indexOf("|"));  //메시지 보낸사람 id
			String msg = message.substring(message.indexOf("|"),message.length());		
			String usermsg = message.substring(message.indexOf("@")+1); //!명령어@ 뒤에 오는 msg
			int index = message.indexOf("|")+1;
			String com = message.substring(index,message.indexOf("@")); //!명령어 확보
		
			final Basic basic = session.getBasicRemote();
			
			String type=dao.roomTypeReturn(user);
			
			if(type.equals("게임")) {
				int length = message.length();
				String userinput = message.substring(length-2,length-1);
				String gameuser = message.substring(length-1,length);
				
				System.out.println("게임이름 : "+gameuser);
				System.out.println("사용자입력 : "+userinput);
				
				//message = dao.tictecteo(userinput, gameuser);
				//유저네임
				//message = gameuser+","+userinput;
				
				basic.sendText(message);
				sendRoomSessionToMessage(session,message,user);
				return;
			}
			else {
				
				if(com.equals("!입장")) {
					clientMap.put(user, session);
					message = user+"님 입장했습니다.";
					
				}else if(com.equals("!방입장")){
					
					String room = message.substring(message.indexOf("@")+1);
					int uCount=dao.changeRoom(user,room);
					type=dao.roomTypeReturn(user);
					if(uCount==1) {
						if(type.equals("게임")) {
							message = "!게임입장|X@"+user+"님이 방에 입장했습니다.";
							basic.sendText(message);
							return;
						}else {
							message = user+"님이 방에 입장했습니다.";
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
					message = user+" : "+usermsg;
					
				}else if(com.equals("!방만들기")) {
					String[] split = message.split(",");
					String lock = split[1];
					String limit = split[2];
					String pw = split[3];
					int myroom=0;
					
					if(lock.equals("게임")) {
						myroom=dao.newroom(user,lock,"2",pw);
					}else {
						myroom=dao.newroom(user,lock,limit,pw);
					}
					if(myroom==0) {
						message ="이미 방장인 방이 있습니다.";
						basic.sendText(message);
						return;
					}else {
						if(lock.equals("게임")) {
							message = "!게임입장|O@"+user+"님이 방에 입장했습니다.";
							basic.sendText(message);
							return;
							//message = user+"님이 방장인 "+myroom+"번 게임방이 만들어졌습니다.";
						}else{
							message = user+"님이 방장인 "+myroom+"번방이 만들어졌습니다.";
						}
					}
					
															
					//basic.sendText("> "+message);
					sendAllSessionToMessage(session, message);
					return;
					
								
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
					
				}else if(com.equals("!방나가기")) {
					int count=0;
					int room=dao.myRoomNo(user);
					if(room==0) {
						message = user+"님의 현재 위치는 대기실 입니다.";
						basic.sendText("> "+message);
						return;
					}
					if(dao.roomOwnerReturn(user).equals(user)) {
						basic.sendText("!방장방나가기|방장이 방을 나가면 방이 폭파되어 모두 대기실로 이동됩니다. 그래도 나가시겠습니까?");			
						return;
					}else {
						count=dao.changeRoom(user,"0");
					}
					
					if(count==1) {
						basic.sendText("> "+ "방을 나갔습니다.");
						return;
					}				
				}else if(com.equals("!초대받음")) {
					
					String status = dao.statusInvitation(usermsg);
					
					if(status.equals("invitation")) {
						message = "이미 다른 방에서 초대요청을 보낸 사용자입니다.";
						basic.sendText(message);
						return;
					}
					
					int room = dao.myRoomNo(user);
					Iterator<String> it = clientMap.keySet().iterator();
					while(it.hasNext()) {
						String inviname=it.next();
						if(inviname.equals(usermsg)){
							session=clientMap.get(inviname);
						}
					}
					message = "!초대받음|"+user+","+room;
					session.getBasicRemote().sendText(message);
					System.out.println("초대메시지 : "+message);
					return;
				}else if(com.equals("!초대거절")) {
					String ownername = usermsg;
					
					Iterator<String> it = clientMap.keySet().iterator();
					while(it.hasNext()) {
						String name1=it.next();
						if(name1.equals(ownername)){
							session=clientMap.get(name1);
						}
					}
					
					message = "> "+user+"님이 초대를 거절했습니다.";		
					session.getBasicRemote().sendText(message);
					return;
				}else if(com.equals("!방장방나가기")) {

					message = "> 방장이 방을 나가서 방이 폭파되었습니다. 대기실로 이동되었습니다.";
					basic.sendText(message); 
					sendRoomSessionToMessage(session,message,user);
					
					dao.deleteroom1(user);
					return;
				}
				else if(com.equals("!전체공지")) {
					
					sendAllSessionToMessage(session, usermsg);
					return;
				}else if(com.equals("!강제퇴장")) {
					String outname = usermsg;
					int uCount = dao.changeRoom(usermsg, "0");
					
					if(uCount==1) {
						Iterator<String> it = clientMap.keySet().iterator();
						while(it.hasNext()) {
							String name1=it.next();
							if(name1.equals(outname)){
								session=clientMap.get(name1);
							}
						}
						session.getBasicRemote().sendText("> "+"방장에 의하여 대화방에서 강퇴당했습니다.");
						basic.sendText(outname+"님을 강퇴시켰습니다.");
						return;
					}else {
						basic.sendText("강퇴에 실패했습니다.");
						return;
					}
				}else if(com.equals("!쪽지전송")) {
					String reciver = message.substring(message.indexOf("@")+1,message.indexOf(","));
					String sendmessage = message.substring(message.indexOf(",")+1,message.length());
					
					Iterator<String> it = clientMap.keySet().iterator();
					while(it.hasNext()) {
						String name1=it.next();
						if(name1.equals(reciver)){
							session=clientMap.get(name1);
						}
					}
					session.getBasicRemote().sendText("!쪽지받음@"+user+","+sendmessage);
					return;
					
				}else if(com.equals("!방장승계")) {
					String newowner = usermsg;
					Iterator<String> it = clientMap.keySet().iterator();
					while(it.hasNext()) {
						String name1=it.next();
						if(name1.equals(newowner)){
							session=clientMap.get(name1);
						}
					}
					session.getBasicRemote().sendText("> 이 방에 방장으로 지목되었습니다.");
					return;
				}
				else {
					System.out.println("com : "+com);
					System.out.println("message : "+message);
				}
				
				try {
					//인수로 넘어온 메세지를 각 클라이언트로 보낸다
					String mymsg = dao.badWordCheck(user,message);
					basic.sendText("> "+mymsg);   //내가 보낼 때 to
				}catch(IOException ex) {
					ex.printStackTrace();
				}
				
				sendRoomSessionToMessage(session,message,user);
			}
		
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
							message=dao.badWordCheck(name,message);
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
					message=dao.badWordCheck(name,message);
					session.getBasicRemote().sendText("!공지! : "+message);
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
						message=dao.badWordCheck(user,message);
						session.getBasicRemote().sendText(user+"님의 귓속말 : "+message);
						message=dao.badWordCheck(name,message);
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
