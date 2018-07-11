import java.util.Scanner;

public class ThreadMain {

	public ThreadMain() {
		
	}
	public void init() {
		Scanner s = new Scanner(System.in);
		System.out.println("숫자를 입력해주세요.");
		String s_num = s.nextLine();
		int n_num = Integer.parseInt(s_num);
		
		try {
			Thread tsub = new ThreadSub(n_num);
			tsub.start();
		}catch(Exception e) {
			System.out.println("예외 : "+e);
		}
		System.out.println("이름을 입력해 주세요.");
		String s_name = s.nextLine();
		System.out.println(s_name);
		s.close();
	}
	
	public static void main(String[] args) {
//		Static class 또 안만들라고 자기자신을 객체로 만든 후 init()을 호출하였음
//		좀 더 이해가 필요하다..크흡 
//		이렇게 안하면 init메소드랑, ThreadSub에 다 static 붙혀줘야 해
		ThreadMain tm = new ThreadMain();
		tm.init();
	}
	
// 내부 클래스이다~ 클래스 속에 클래스
	
	class ThreadSub extends Thread {
		int nNum;
		
		public ThreadSub(int num) {
			this.nNum = num;
		}
		
		public void run() {
			int i=0;
			while(i<nNum) {
				try {
					Thread.sleep(1000);
					i=i+1;
					System.out.println("Thread : "+i);
				}catch(Exception e) {
					System.out.println("예외 : "+e);
				}
			}
		}
	}
}
