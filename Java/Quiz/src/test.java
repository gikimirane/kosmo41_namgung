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
		
		String s_name = "인 덱 스";
		
		System.out.println("select count(*) from block where oname = '"+"현재 이터레이터'"+" and bname = '"+"나"+"'");
			
	}
}
