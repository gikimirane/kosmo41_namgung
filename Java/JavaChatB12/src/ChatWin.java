	import java.awt.FlowLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.net.Socket;
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
	            out.println(name);

	        } catch(Exception e) {
	            System.out.println("예외S3:"+e);
	        }
		}
		//Inner Class TextHandler
		class TextHandler implements ActionListener {
			boolean fixed=false;
			String iName="";
						
			public void actionPerformed(ActionEvent e) {
				
				String msg = tf.getText();
				String temp = msg.substring(msg.indexOf(" ")+1);

				if ("".equals(msg)) return;
				
				if ( msg.equals("q") || msg.equals("Q") ) {
					try {
						out.close();
						socket.close();
					} catch (IOException e1) {
					}
				}
				
//				/to로 시작하는지 확인
				if(msg.startsWith("/to")) {
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
						out.println("고정귓속말을 시작합니다. 해제하려면 상대방의 이름을 입력하세요.");
					}else {
						out.println(msg);
					}
				}else {
					if(fixed==true) {
						if(msg.equals(iName)) {
							fixed=false;
						}else out.println("/to "+iName+" "+msg);
						
					}else out.println(msg);
				}
				tf.setText("");
			}
		}

	}
