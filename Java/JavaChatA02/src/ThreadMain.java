import java.util.Scanner;

public class ThreadMain {

	public static void main(String[] args) {
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
	}
}
