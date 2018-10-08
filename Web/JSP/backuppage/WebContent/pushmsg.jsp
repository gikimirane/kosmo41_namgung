
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@page import="java.net.URLEncoder"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.google.android.gcm.server.*"%>
 
<%

	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}catch(ClassNotFoundException cnfe) {
		cnfe.printStackTrace();
	}

    ArrayList<String> token = new ArrayList<String>();    //token값을 ArrayList에 저장
    String MESSAGE_ID = String.valueOf(Math.random() % 100 + 1);    //메시지 고유 ID
    boolean SHOW_ON_IDLE = false;    //옙 활성화 상태일때 보여줄것인지
    int LIVE_TIME = 1;    //옙 비활성화 상태일때 FCM가 메시지를 유효화하는 시간
    int RETRY = 2;    //메시지 전송실패시 재시도 횟수
 
    
    String simpleApiKey = "AAAAzuMZ4F8:APA91bFflHUwzs98Hr60iixreoJfvOHTMDeznxKQqA1OlOAGMreOmM_LAf8FL9VYGCFtHSZQtY3wwBd1LRwkRlT783HalGOeyDFMMObeuZXq0EG_px_tKLPxDYF5N3ofiPW1nBw3iLm_";
    String gcmURL = "https://android.googleapis.com/fcm/send";    
    
    Connection conn = null; 
    Statement stmt = null; 
    ResultSet rs = null;
    
    //String msg = request.getParameter("message");
    String msg = "메시지를 보내고 싶어요!!!!!!!!!!";
    System.out.println("올라온 msg " +msg);
    
    if(msg==null || msg.equals("")){
        msg="";
    }
    
    msg = new String(msg.getBytes("UTF-8"), "UTF-8");   //메시지 한글깨짐 처리
    
    try {
    	String user = "scott"; 
	    String pw = "tiger";
	   	//String url = "jdbc:oracle:thin:@ec2-13-209-64-83.ap-northeast-2.compute.amazonaws.com:1521:xe";
	    String url = "jdbc:oracle:thin:@ec2-13-209-64-83.ap-northeast-2.compute.amazonaws.com:1521:xe";
	    
	    conn = DriverManager.getConnection(url,user,pw);
        stmt = conn.createStatement();            
        String sql = "select distinct clientno from orderlist";
        rs = stmt.executeQuery(sql);
        
        System.out.println("Database에 연결되었습니다.\n");
        
        
        String client;
        //모든 등록ID를 리스트로 묶음
        while(rs.next()){
        	client=rs.getString(1);
            token.add(client);
            System.out.println("나의 clientno : "+client);
        }
        
        rs.close();
        stmt.close();
        conn.close();
        
        Sender sender = new Sender(simpleApiKey);
        Message message = new Message.Builder()
        .collapseKey(MESSAGE_ID)
        .delayWhileIdle(SHOW_ON_IDLE)
        .timeToLive(LIVE_TIME)
        .addData("message",msg)
        .build();
        
        System.out.println("여기인가1111");
        
        MulticastResult result1 = sender.send(message,token,RETRY);
        System.out.println("여기인가2222");
        if (result1 != null) {
            List<Result> resultList = result1.getResults();
            for (Result result : resultList) {
            	System.out.println("여기인가 3333");
                System.out.println(result.getErrorCodeName()); 
            }
        }
    }catch (Exception e) {
        e.printStackTrace();
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>