/*
문제 05-01
사용자로부터 계속해서 정수를 입력받는다.
단 0을 입력받게되면 기존에 입력받은 모든 정수를 더한후 결과를 출력하는 프로그램을 작성하시오.
 */

import java.util.Scanner;
public class Quiz4 {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("0이 아닌 숫자를 입력 시 계속 더해드립니다.");
		int num1 = s.nextInt();
		int sum = 0;
		
		do {
			num1 = s.nextInt();
			sum = sum + num1;
			
		}while (num1 != 0);
		
		System.out.println("0을 입력하여 합을 반환합니다. 합은 : "+sum);
	
	}
}
