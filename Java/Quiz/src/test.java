import java.util.Scanner;

public class test {
	
	
	
//	public void sendAllMsg (String user, String msg) throws SQLException {
//		
//		if(!blockClient.isEmpty()) {
//			Iterator<String> it = blockClient.keySet().iterator();
//	 		while(it.hasNext()) {
//	 			try {
//	 				PrintWriter it_out = (PrintWriter)blockClient.get(it.next());
//	 				
//	 				if(user.equals("")) {
//	 					it_out.println(URLDecoder.decode(badWordCheck(msg),"UTF-8"));
//	 				}else {
//	 					it_out.println(URLDecoder.decode("["+user+"] "+badWordCheck(msg),"UTF-8"));
//	 				}
//	 			}catch(Exception e) {
//	 				System.out.println("예외 11: "+e);
//	 			}
//	 		}
//		}else {
//			Iterator<String> it = clientMap.keySet().iterator();
//	 		while(it.hasNext()) {
//	 			try {
//	 				PrintWriter it_out = (PrintWriter)clientMap.get(it.next());
//	 				
//	 				if(user.equals("")) {
//	 					it_out.println(URLDecoder.decode(badWordCheck(msg),"UTF-8"));
//	 				}else {
//	 					it_out.println(URLDecoder.decode("["+user+"] "+badWordCheck(msg),"UTF-8"));
//	 				}
//	 				
//	 			}catch(Exception e1) {
//	 				System.out.println("예외 : "+e1);
//	 			}
//	 		}
//		}
//	}
	public static void main(String[] args) {
		String str = "/ownerchange 유니";
		
		String temp = str.substring(str.indexOf(" "));
		String friendName = temp.substring(1,temp.length());
		
		System.out.println(temp);
		System.out.println(friendName);
	}
}
