/*
문제 1 : 03-01
사용자로부터 두개의 정수를 입력받아서 사칙연산에 대한 결과를 출력하는 프로그램을 작성하라
*/

import java.util.Scanner;

public class Quiz1 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int sum=0;
		
		System.out.println("1번 숫자");
		int num1 = s.nextInt();
		
		System.out.println("2번 숫자");
		int num2 = s.nextInt();
		
		System.out.println("합 : " + (num1+num2));
		
		
	
		if (num1 > num2 ){
			System.out.println("빼기 : " + (num1-num2));
		}
		else System.out.println("빼기 : " + (num2-num1));

		//sum = num1 > num2 ? (num1-num2) : (num2 - num1);
		System.out.println("합 : " + sum);
		System.out.println("곱셈 : " + (num1*num2));
		System.out.println("나눗셈 : " + (num1/num2));

	}

}
