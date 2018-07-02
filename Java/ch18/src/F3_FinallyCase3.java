import java.util.Scanner;

public class F3_FinallyCase3 {
	public static void main(String[] args) {
	
		int num=0;
		Scanner s = new Scanner(System.in);
		int a = s.nextInt();
		int b = s.nextInt();
		
		try {
			num = a/b;
		}catch(Exception e) {           //에러를 볼수도 있고 처리도 할 수 있어
			//e.printStackTrace();
			num=0; 
		}finally {                      //에러가 발생하고 난뒤에도 무조건적으로 실행하는 영역
			//데이터 베이스 접속 종료.. 등 무조건 해야할 일
			//num = num + 1;
		}
		System.out.println(num);
	}
}
