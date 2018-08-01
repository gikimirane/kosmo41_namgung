import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MultiClient6 {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	public static void main(String[] args) throws UnknownHostException, IOException, SQLException {

		String sql = null;
		Scanner s = new Scanner(System.in);
		String s_name = "";
		boolean sw = true;
		int updateCount=0;
		
		Connection con=ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		while(sw) {
			try {		
				System.out.println("이름을 입력하세요.");
				s_name = s.nextLine();
				
				int blank =0;
				for(int i=0;i<s_name.length();i++) {
					if(s_name.charAt(i) == ' ') {
						blank++;
					}
				}
				if(blank >0) {
					System.out.println("이름에 공백을 허용하지 않습니다. 재입력 하세요.");
					continue;
				}
				
				String cName="";
				int bCount =0;
				sql = "select * from blacklist";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					cName = rs.getString(1);
					if(cName.equals(s_name)) {
						System.out.println(cName);
						bCount++;
					}
				}
				if(bCount>=1){
					System.out.println("Blacklist인 회원이름 입니다.");
					continue;
				}

				sql = "insert into emp values ('"+s_name+"','0','connect','0')";
				pstmt = con.prepareStatement(sql);
				updateCount = pstmt.executeUpdate();
				
				System.out.println("insert Count : "+updateCount);
				if(updateCount>0) {
					sw = false;
					con.commit();
				}
			}catch(SQLException sqle) {
				System.out.println("이름이 중복됩니다.");
				continue;
			}
		}	
		try {
//			String ServerIP = "ec2-13-125-210-91.ap-northeast-2.compute.amazonaws.com";
			String ServerIP = "localhost";
			Socket socket = new Socket(ServerIP,9999);
			System.out.println("서버에 연결되었습니다...");
			
			Thread receiver = new Receiver6(socket);
			receiver.start();
			
			new ChatWin(socket,s_name);
//			Thread.sleep(100);
		}catch(Exception e) {
			System.out.println("예외 [MultiClient class] : "+e);
			s.close();
	
		}finally {
			rs.close();
			pstmt.close();
			con.close();
		}
	}
}
