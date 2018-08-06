	import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

//sender 영역
	public class ChatWin extends JFrame {

		private static final long serialVersionUID = 1L;
	    JTextField tf;
	    JPanel p;
	    TextHandler handler = null;

		Socket socket;
		PrintWriter out = null;
	    String name;

		ChatWin(Socket socket, String name) {

			this.setTitle("Chat Window");
		    this.setSize(600, 100);
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		    p = new JPanel();
		    p.setLayout(new FlowLayout());
		    
		    tf = new JTextField(40);
		    p.add(tf);
		    
		    this.setContentPane(p);
		    this.setVisible(true);

			handler = new TextHandler();
			tf.addActionListener(handler);

		    //-------------------------------------------------------------------
	        
		    this.socket = socket;        
	        try {
				out = new PrintWriter(this.socket.getOutputStream(), true);
	            this.name = name;
	            
		        //서버에 입력한 사용자이름을 보내준다.
	            out.println(URLEncoder.encode(name,"UTF-8"));

	        } catch(Exception e) {
	            System.out.println("예외S3:"+e);
	        }
		}
		//Inner Class TextHandler
		class TextHandler implements ActionListener {
			boolean fixed=false;
			String iName;
	
			public void actionPerformed(ActionEvent e) {
				try {
					
					String msg = tf.getText();
					msg = URLDecoder.decode(msg,"UTF-8");	
					String temp = msg.substring(msg.indexOf(" ")+1);
					
					
					if ("".equals(msg)) {
						return;
					}
					
					if ( msg.equals("q") || msg.equals("Q") ) {
						try {
							out.close();
							socket.close();
						} catch (IOException e1) {
						}
					}
									
	//				/to로 시작하는지 확인
					if(msg.startsWith("/귓속말")) {
						int space=0;
	//					공백이 1개면 고정, 2개 이상이면 한번만 하는 귓속말
						for(int i=0;i<msg.length();i++) {
							if(msg.charAt(i)==' ') {
								space++;
							}
						}
	//					한번인지 고정인지 확인하고 계속고정시켜야 함
	//					고정인지 확인하고 계속 고정하기 위해 값을 초기화 시키면 안 됨 그래서 메소드 밖에서 변수 선언함
						if(space==1) {
							fixed = true;
							iName = temp.substring(temp.indexOf(" ")+1);
							System.out.println(iName+"님과 고정 귓속말 시작, 해제 시 /"+iName+"을 입력하세요.");
							tf.setText("");
							return;
						}else {
							out.println(URLEncoder.encode(msg,"UTF-8"));
							tf.setText("");
							return;
						}
						
					} else if(msg.equalsIgnoreCase("/"+iName)&&fixed==true) {
						fixed = false;
						System.out.println(iName+"님과의 고정 귓속말을 해제합니다.");
						iName ="";
						tf.setText("");
						return;						
					}

					if(fixed==true) {
						out.println(URLEncoder.encode("/귓속말 "+iName+" "+msg,"UTF-8"));
					}else out.println(URLEncoder.encode(msg,"UTF-8"));
					tf.setText("");
				}catch(Exception e1) {
					System.out.println("ChatWin Error 발생"+e);
				}
		}
	}
}