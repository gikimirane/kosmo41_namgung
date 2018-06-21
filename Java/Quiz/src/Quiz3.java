import java.util.Scanner;

/*
문제 3 : 03-03
두개의 정수를 입력받아서 다음과 같은 출력결과를 만들어보자.
출력예) 두개의 정수를 입력하세요.
25 4
25 나누기 4 의 몫은 6 입니다.
25 나누기 4 의 나머지는 1 입니다.

 */
public class Quiz3 {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		
		System.out.println("첫 번째 숫자를 입력하세요.");
		int num1 = s.nextInt();
		
		System.out.println("두 번째 숫자를 입력하세요.");
		int num2 = s.nextInt();
		
		System.out.println(num1 + " 나누기 " + num2 + "의 몫은 "+ (num1/num2)+ " 입니다.");
		System.out.println(num1 + " 나누기 " + num2 + "의 나머지는 "+ (num1%num2)+ " 입니다.");

	}

}
