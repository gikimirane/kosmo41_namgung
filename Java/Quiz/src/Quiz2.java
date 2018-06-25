/*
문제 2 : 03-02
하나의 정수를 입력받아서 그 수의 제곱을 출력하는 프로그램을 작성하라. 
가령 5를 입력받았다면 25가 출력되어야 한다.

 */

import java.util.Scanner;

public class Quiz2 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("입력");
		int num1 = s.nextInt();
		
		System.out.println("제곱 : " + (num1*num1));

	}

}
