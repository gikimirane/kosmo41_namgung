import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class ConnTest1 {
	ConnectionPool cp = null;
	Connection con =null;
	PreparedStatement pstmt =null;
	ResultSet rs = null;
	
	public void select () { 
		try {
			cp = ConnectionPool.getInstance();
			con = cp.getConnection();
			pstmt = con.prepareStatement("select count(*) from department");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println("Count : "+rs.getInt(1)+", ");
			}
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con!=null) cp.releaseConnection(con);
			}catch (SQLException _sqle) {
				_sqle.printStackTrace();
			}
		}
		//cp.closeAll();
	}
}
class ConnTest2 extends Thread {
	ConnectionPool cp = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//run() 메소드의 재 정의, 얘네 엄마는 Thread 클래스
	public void run () {
		try { 
			String name = Thread.currentThread().getName();
			cp = ConnectionPool.getInstance();
			con = cp.getConnection();
			pstmt = con.prepareStatement("select count(*) from department");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println(name + " : "+rs.getInt(1)+" : "+con);
			}
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}finally { 
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con !=null) cp.releaseConnection(con);
			}catch(SQLException _sqle) {
				_sqle.printStackTrace();
			}
		}
	}
}
public class Main {

	public static void main(String[] args) throws InterruptedException {
		ConnTest1 ct = new ConnTest1();
		ct.select();
		for(int i=0;i<80;i++) {
			Thread test = new ConnTest2();
			test.start();
			
			Thread.sleep(10);
		}
	}
}
