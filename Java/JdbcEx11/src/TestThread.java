import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestThread extends Thread {
	private String noThread ="00";
	
	TestThread(int n){
		if(n<10)
			noThread = "0"+n;
		else
			noThread =""+n;
	}
	
	public void run() {
		try {
			Connection con = ConnectionPool.getConnection("성공");
			con.setAutoCommit(false);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select name from emp");
			while(rs.next()) {
				System.out.println("eno : "+rs.getString("name"));
			}
			stmt.close();
			//ConnectionPool.listCacheInfos();
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			//System.out.println("Sleep ... "+noThread);
			try {
				Thread.sleep(100);
			}catch(Exception e) {}
		}
	}
}
