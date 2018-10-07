package com.study.android;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class ADao {
	
	DataSource dataSource=null;
	private static ADao instance = new ADao();
	   
	
	public ADao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ADao getInstance() {
		return instance;
	}
	public JSONObject adminlist() {
		JSONObject obj = new JSONObject();
		JSONArray jArray = new JSONArray();
		
		
		
		return obj;
	}
	
	public String sendpush(String msg1) {
		JSONObject obj = new JSONObject();
		String re = "";
		
		ArrayList<String> token = new ArrayList<String>();    //token값을 ArrayList에 저장
	    String MESSAGE_ID = String.valueOf(Math.random() % 100 + 1);    //메시지 고유 ID
	    boolean SHOW_ON_IDLE = false;    //옙 활성화 상태일때 보여줄것인지
	    int LIVE_TIME = 1;    //옙 비활성화 상태일때 FCM가 메시지를 유효화하는 시간
	    int RETRY = 2;    //메시지 전송실패시 재시도 횟수
	 
	    
	    String simpleApiKey = "AAAAzuMZ4F8:APA91bFflHUwzs98Hr60iixreoJfvOHTMDeznxKQqA1OlOAGMreOmM_LAf8FL9VYGCFtHSZQtY3wwBd1LRwkRlT783HalGOeyDFMMObeuZXq0EG_px_tKLPxDYF5N3ofiPW1nBw3iLm_";
	    String gcmURL = "https://android.googleapis.com/fcm/send";    
	    
	    String msg = msg1;
	    String client;
	    if(msg==null || msg.equals("")){
	        msg="";
	    }
	    
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select distinct clientno from orderlist";
		try {
		msg = new String(msg.getBytes("UTF-8"), "UTF-8");   
		
		con = dataSource.getConnection();
		pstmt = con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		
		while(rs.next()) {
			client=rs.getString("clientno");
			token.add(client);
		}
		
		}catch (SQLException sqle) {
		    System.out.println("DB 접속실패 : "+sqle.toString());
		} catch (Exception e) {
		    System.out.println("Unkonwn error");
		    e.printStackTrace();
		}finally {
			try{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		Sender sender = new Sender(simpleApiKey);
        Message message = new Message.Builder()
        .collapseKey(MESSAGE_ID)
        .delayWhileIdle(SHOW_ON_IDLE)
        .timeToLive(LIVE_TIME)
        .addData("message",msg)
        .addData("title", "PLACIDO COFFEE")	
        .build();
        
                
        try {
        	 MulticastResult result1 = sender.send(message,token,RETRY);
             if (result1 != null) {
                 List<Result> resultList = result1.getResults();
                 for (Result result : resultList) {
                    System.out.println(result.getErrorCodeName()); 
                 }
             }
            re= "성공";
        }catch(Exception e1) {
        	e1.printStackTrace();
        	re= "실패";
        }
		return re;
	}
	
	public JSONObject myorderlist(String client) {
		JSONObject obj = new JSONObject();
		JSONArray cArray = new JSONArray();
		JSONArray mArray = new JSONArray();
		JSONArray pArray = new JSONArray();
		
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT code,menulist,price FROM orderlist where clientno=? and status = '결제대기' order by status";
			
		try {
		    System.out.println("Database에 연결되었습니다.\n");
		    con = dataSource.getConnection();
		    pstmt = con.prepareStatement(sql);
		    pstmt.setString(1, client);
		    rs = pstmt.executeQuery();
		    
		    while(rs.next()){
		    	cArray.add(rs.getString("code"));
		    	mArray.add(rs.getString("menulist"));
		    	pArray.add(rs.getString("price"));
		    }
		    System.out.println("메뉴 : "+mArray);
		    System.out.println("금액 : "+pArray);
		    System.out.println("코드 : "+cArray);
		    
		    obj.put("menu",mArray);
		    obj.put("price",pArray);
		    obj.put("code", cArray);
		    
		    sendpush("오늘이 지나면 HAPPYCODE가 사라집니다.");
		 
		} catch (SQLException sqle) {
		    System.out.println("DB 접속실패 : "+sqle.toString());
		} catch (Exception e) {
		    System.out.println("Unkonwn error");
		    e.printStackTrace();
		}finally {
			try{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}		
		
		return obj;
	}
	
	public JSONObject menulist() {
		
		JSONObject obj = new JSONObject();
		JSONArray jArray = new JSONArray();
		JSONArray jArray1 = new JSONArray();
		
		Connection con=null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "select menu,price from menulist order by mro";
		
		try {
		    System.out.println("Database에 연결되었습니다.\n");
		    con = dataSource.getConnection();
		    stmt = con.createStatement();
		    rs = stmt.executeQuery(sql);
		    
		    while(rs.next()){
		    	jArray.add(rs.getString("menu"));
		    	jArray1.add(rs.getString("price"));
		    }
		    System.out.println("메뉴 : "+jArray);
		    System.out.println("금액 : "+jArray1);
		    
		    obj.put("menu",jArray);
		    obj.put("amount",jArray1);
		    //sendpush("메뉴 목록을 제공합니다.");
		    
		} catch (SQLException sqle) {
		    System.out.println("DB 접속실패 : "+sqle.toString());
		} catch (Exception e) {
		    System.out.println("Unkonwn error");
		    e.printStackTrace();
		}finally {
			try{
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				if(con!=null)con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}		
		return obj;
	}
	
	public JSONObject inputorder(HashMap<String,String> data) {
		HashMap<String,String> userdata = data;
		JSONObject obj = new JSONObject();
		JSONArray jArray = new JSONArray();
		
		String menu = data.get("menu");
		String code = data.get("code");
		String price = data.get("price");
		String client = data.get("client");		
		String sql = "insert into orderlist values (?,?,?,?,'결제대기',(SELECT SYSDATE FROM DUAL))";
		Connection con=null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = dataSource.getConnection();
		    System.out.println("Database에 연결되었습니다.\n");
		    
		    pstmt = con.prepareStatement(sql);
		    pstmt.setString(1,code);
			pstmt.setString(2,menu);
			pstmt.setString(3,price);
			pstmt.setString(4,client);
			int update = pstmt.executeUpdate();
		    String push = sendpush("카운터에 HAPPYCODE를 제시하세요!");
		    String sendmsg = "업데이트 : "+update+" , push 여부 : "+push;
			obj.put("result",sendmsg);
		    
		    
		}catch (SQLException sqle) {
		    System.out.println("DB 접속실패 : "+sqle.toString());
		} catch (Exception e) {
		    System.out.println("Unkonwn error");
		    e.printStackTrace();
		}finally {
			try{
			
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return obj;
	}
}
