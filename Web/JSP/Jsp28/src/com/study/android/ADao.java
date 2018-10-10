package com.study.android;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

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
	
	public int searchid(String id) {
		int result=0;
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from members where userid=?";
		try {	
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				result=1;
			}else {
				result=0;
			}
			
		} catch (SQLException sqle) {
		    System.out.println("DB 접속실패 : "+sqle.toString());
		} catch (Exception e) {
		    System.out.println("Unkonwn error");
		    e.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}	
		return result;
	}
	public String deletecode(String code) {
		String result="실패";
		Connection con=null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from orderlist where code =?";
		
		try {	
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, code);
			int upcount = pstmt.executeUpdate();
			if(upcount==1) {
				result="성공";
			}
			
		} catch (SQLException sqle) {
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
		return result;
	}
	public int tryLogin(String id, String pw) {
		int result=0;
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from members where userid=? and userpw=?";
		try {	
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=1;
			}else {
				result=0;
			}
		} catch (SQLException sqle) {
		    System.out.println("DB 접속실패 : "+sqle.toString());
		} catch (Exception e) {
		    System.out.println("Unkonwn error");
		    e.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}	
		return result;
	}
	
	public String insertuser(String id, String pw, String name, String phone, String email, String address, String clientno, String point) {
		String result="";
		Connection con=null;
		PreparedStatement pstmt = null;
		
		int upcount=0;
		int point1 = Integer.parseInt(point);
		String sql = "insert into members values (?,?,?,?,?,?,?,?,sysdate)";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, phone);
			pstmt.setString(5, email);
			pstmt.setString(6, address);
			pstmt.setInt(7, point1);
			pstmt.setString(8,clientno);
			
			upcount=pstmt.executeUpdate();    
			System.out.println("upcount: "+upcount);
			if(upcount==1) {
				result="성공";
			}else {
				result="db입력실패";
			}
		} catch (SQLException sqle) {
		    System.out.println("DB 접속실패 : "+sqle.toString());
		    result = "접속실패111"+sqle.getMessage();
		} catch (Exception e) {
		    System.out.println("Unkonwn error");
		    e.printStackTrace();
		    result = "다른에러111"+e.getMessage();
		}finally {
			try{
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}	
		
		return result;
	}
	
	public int updateClient(String clientno,String userid) {
		Connection con=null;
		PreparedStatement pstmt = null;
		int result=0;
		String sql = "update members set clientno =? where userid =?";
	
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,clientno);
			pstmt.setString(2, userid);
			result = pstmt.executeUpdate();
			System.out.println("update 완료!");
			
		} catch (SQLException sqle) {
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
		return result;
	}
	
	
	public int status(String status,String code) {
		Connection con=null;
		Statement stmt = null;
		int result=0;
		String sql = "update orderlist set status = '"+status+"' where code = '"+code+"'";
	
		try {
			con = dataSource.getConnection();
			stmt = con.createStatement();
			result = stmt.executeUpdate(sql);
			System.out.println("update 완료!");
			
		} catch (SQLException sqle) {
		    System.out.println("DB 접속실패 : "+sqle.toString());
		} catch (Exception e) {
		    System.out.println("Unkonwn error");
		    e.printStackTrace();
		}finally {
			try{
				if(stmt!=null)stmt.close();
				if(con!=null)con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}	
		return result;
	}
	public ArrayList<ADto> adminlist() {
		ArrayList<ADto> list = new ArrayList<>();
		
		Connection con=null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from orderlist where status='결제대기' or status='결제완료' order by status,odata desc";
		String code,menu,price,clientno,status;
		Timestamp date;
		
		try {
			con = dataSource.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				code = rs.getString(1);
				menu = rs.getString(2);
				
				StringTokenizer strtoken = new StringTokenizer(menu,"|");
				String result="";
				int i=0;
				while(strtoken.hasMoreTokens()) {
		            if(i%2==0) {
		                result=result+strtoken.nextToken();
		            }else {
		                result =result+strtoken.nextToken()+"잔 ";
		            }
		            i++;
		        }
				menu= result;
				price = rs.getString(3);
				clientno = rs.getString(4);
				status = rs.getString(5);
				date = rs.getTimestamp(6);
				ADto dto = new ADto(code,menu,price,clientno,status,date);
				list.add(dto);
			}
			
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
		
		return list;
	}
	
	public String sendpush(String code,String msg1) {
		JSONObject obj = new JSONObject();
		String re = "";int re1=0;
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
		String sql = "select distinct clientno from orderlist where code=?";
		try {
		msg = new String(msg.getBytes("UTF-8"), "UTF-8");   
		
		con = dataSource.getConnection();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, code);
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			client=rs.getString("clientno");
			token.add(client);
		}
		//System.out.println("코드 :"+token.get(0));
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
	
	public String payToPoint(String id,int point) {
		
		String result="";
		Connection con=null;
		PreparedStatement pstmt = null;
		int upcount=0;
		String sql="";
		 
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, point);
			pstmt.setString(2, id);
			
			upcount=pstmt.executeUpdate();    
			
			if(upcount==1) {
				result="성공";
			}else {
				result="db입력실패";
			}
		} catch (SQLException sqle) {
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
		return result;
	}
	
	public String instancepush(String client,String msg1) {
		
		String re = "";
		
		ArrayList<String> token = new ArrayList<String>();    //token값을 ArrayList에 저장
	    String MESSAGE_ID = String.valueOf(Math.random() % 100 + 1);    //메시지 고유 ID
	    boolean SHOW_ON_IDLE = false;    //옙 활성화 상태일때 보여줄것인지
	    int LIVE_TIME = 1;    //옙 비활성화 상태일때 FCM가 메시지를 유효화하는 시간
	    int RETRY = 2;    //메시지 전송실패시 재시도 횟수
	 
	    
	    String simpleApiKey = "AAAAzuMZ4F8:APA91bFflHUwzs98Hr60iixreoJfvOHTMDeznxKQqA1OlOAGMreOmM_LAf8FL9VYGCFtHSZQtY3wwBd1LRwkRlT783HalGOeyDFMMObeuZXq0EG_px_tKLPxDYF5N3ofiPW1nBw3iLm_";
	    String gcmURL = "https://android.googleapis.com/fcm/send";    
	    
	    String msg = msg1;
	   
	    if(msg==null || msg.equals("")){
	        msg="";
	    }   
	    
		token.add(client);
		
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
	
	public JSONObject getUserInfo(String userid) {
		JSONObject obj = new JSONObject();
		JSONArray info = new JSONArray();
		
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM members where userid=?";
			
		try {
		    System.out.println("Database에 연결되었습니다.\n");
		    con = dataSource.getConnection();
		    pstmt = con.prepareStatement(sql);
		    pstmt.setString(1, userid);
		    rs = pstmt.executeQuery();
		    
		    if(rs.next()){
		    	info.add(rs.getString("userid"));
		    	info.add(rs.getString("userpw"));
		    	info.add(rs.getString("userphone"));
		    	info.add(rs.getString("userpoint"));
		    	info.add(rs.getString("clientno"));
		    }
		    System.out.println("id : "+info.get(0));
		    System.out.println("pw : "+info.get(1));
		    System.out.println("point"+info.get(2));
		    
		    obj.put("info", info);	 
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
	
	public JSONObject mysuccesslist(String client) {
		JSONObject obj = new JSONObject();
		JSONArray cArray = new JSONArray();
		JSONArray mArray = new JSONArray();
		JSONArray pArray = new JSONArray();
		
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT code,menulist,price FROM orderlist where clientno=? and status = '결제완료' order by status";
			
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
	public JSONObject myfinishlist(String client) {
		
		JSONObject obj = new JSONObject();
		JSONArray cArray = new JSONArray();
		JSONArray mArray = new JSONArray();
		JSONArray pArray = new JSONArray();
		
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT code,menulist,price FROM orderlist where clientno=? and status = '주문종료' order by status";
			
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
