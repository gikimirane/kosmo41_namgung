import java.util.Scanner;

public class UserIO {
	
	int UserIn() {   //오버로딩 (같은 이름의 메소드이고 매개변수나 리턴타입이 각각 다르게 해야 가능)
		Scanner s = new Scanner(System.in);
		System.out.println("사용자 숫자 입력");
		int a = s.nextInt();
				
		return a;
	}	
	int UserIn(int a) {
		Scanner s = new Scanner(System.in);
		System.out.println(a+"번째 사용자 숫자 입력 : ");
		a = s.nextInt();
				
		return a;
	}	
}
