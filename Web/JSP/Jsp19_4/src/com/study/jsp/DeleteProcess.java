package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/DeleteProcess")
public class DeleteProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection con;
	private PreparedStatement pstmt;
		
	String driver="oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String uid = "scott";
	String upw = "tiger";
	
	HttpSession session;
	String pw,sql,id;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		deleteEMP(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		deleteEMP(request,response);
	}
	private void deleteEMP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		session = request.getSession();
		PrintWriter writer = response.getWriter();
		
		
		id = (String)session.getAttribute("id");
		pw = request.getParameter("pw");
		sql="delete from member where id=?";
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, uid, upw);

			if(pwConfirm()) {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				int updateCount = pstmt.executeUpdate();
				if(updateCount==1) {
					System.out.println("삭제완료");
					writer.println( "[{\"results\":\"ok\",\"desc\":\"비밀번호가 확인되어 탈퇴\"}]" );
					response.sendRedirect("deleteResult.jsp");
				}
			}else {
				System.out.println("비밀번호가 맞지 않습니다.");
				writer.println( "[{\"results\":\"fail\",\"desc\":\"비밀번호 맞지 않음\"}]" );
			}

		}catch(Exception e) {
			
		}finally {
			writer.close();
			try {
				if(pstmt!=null)	pstmt.close();
				if(con!=null) con.close();
			}catch(Exception e1) {}
		}
	}
	
	private boolean pwConfirm() {
		boolean rs = false;
		String sessionPw=(String)session.getAttribute("pw");
				
		if(sessionPw.equals(pw)){
			rs=true;
		}else {
			rs=false;
		}		
		return rs;
	}
}
