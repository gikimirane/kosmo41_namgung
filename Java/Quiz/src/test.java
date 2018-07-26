import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class test {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	public static void main(String[] args) throws SQLException {
		String name = "1111";

		Map<String, String> clientMap;
		clientMap = new HashMap<String, String>();
		Collections.synchronizedMap(clientMap);
		
		clientMap.put("유니", "1234");
		clientMap.put("하이", "1255");
		
		
		Connection con;
		ResultSet rs=null;
		con = DriverManager.getConnection(
				"jdbc:oracle:thin:@ec2-13-125-210-91.ap-northeast-2.compute.amazonaws.com:1521:xe",
				"scott",
				"tiger");
		PreparedStatement pstmt=null;
		String sql = "select * from block where bname = '"+name+"'";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery(sql);
//		int updateCount = pstmt.executeUpdate();
//		System.out.println(rs.getString("ONAME"));
		
		
		ArrayList<String> arr = new ArrayList<String>();
		
		while(rs.next()) {
			 arr.add(clientMap.get(rs.getString(1)));
			 System.out.println(rs.getString("oname"));
		}
		
		Iterator<String> it = clientMap.keySet().iterator();
		int count=0;
		while(it.hasNext()) {
			
				String it_out = (String)clientMap.get(it.next());
				if(arr.contains(it_out)) {
					System.out.println("차단!");
				}
				count++;
		}
		 System.out.println("select count(*) from block where bname = '"+name+"';");

		
	}
}
