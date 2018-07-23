import java.sql.*;
public class TransactionEx {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt=null;
		boolean success =false;
		
		try {
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"scott",
					"tiger");
			con.setAutoCommit(false);  
			//auto commit을 하게되면 계속해서 commit이 되기 때문에 꺼두고 마지막에 commit을 시킴
			//--------------------------------------------------
			
			String sql = "insert into test3 values ('홍길동','1111')";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			System.out.println("11111");
			
			sql = "insert into test3 values('전우치','2222')";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			System.out.println("22222");
			sql = "insert into test3 values('손오공','3333'"; //고의로 에러냄, 괄호를 안닫음
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			System.out.println("33333");
			
			success = true;
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			try {
				if(success) {
					System.out.println("44444");
					con.commit();
					//다 성공하고 나면 commit을 시킴
				}else {
					System.out.println("55555");
					con.rollback();
					//중간에 에러가 났으면 commit 하지말고 rollback
					//test3 table 보면은 2개는 정상적으로 insert가 됐었지만 rollback으로 인해
					//아무것도 저장되지 않음
				}
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException sqle) {}
		}
	}
}
