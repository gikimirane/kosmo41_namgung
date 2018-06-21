/*
문제 1 : 03-01
사용자로부터 두개의 정수를 입력받아서 사칙연산에 대한 결과를 출력하는 프로그램을 작성하라.
*/

import java.util.Scanner;

public class Quiz1 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("첫 번째 숫자를 입력하세요.");
		int num1 = s.nextInt();
		
		System.out.println("두 번째 숫자를 입력하세요.");
		int num2 = s.nextInt();
		
		System.out.println("덧셈의 결과 : " + (num1+num2));
		System.out.println("뺄셈의 결과 : " + (num1-num2));
		System.out.println("곱셈의 결과 : " + (num1*num2));
		System.out.println("나눗셈의 결과 : " + (num1/num2));

	}

}
