import java.util.Scanner;
/*
문제 3 : 03-03
두개의 정수를 입력받아서 다음과 같은 출력결과를 만들어보자.
출력예) 두개의 정수를 입력하세요 .1
25 4
25 나누기 4 의 몫은 6 입니다.
25 나누기 4 의 나머지는 1 입니다.25
 */
public class Quiz3 {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		
		System.out.println("입력1");
		int num1 = s.nextInt();
		
		System.out.println("입력2");
		int num2 = s.nextInt();
		
		System.out.println(num1 + " 와 " + num2 + "나눈값 "+ (num1/num2));
		System.out.println(num1 + " 와 " + num2 + "나눈 나머지 "+ (num1%num2));

	}

}
