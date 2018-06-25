/*
사용자로부터 다음 순서대로 정수를 입력받은 후 평균을 구하여 출력하는 프로그램을 작성하시오.
몇개의 정수를 입력할 지 사용자로부터 입력받는다
입력받은 숫자만큼 정수를 입력받는다.(조건1에서 3이라고 입력했다면 3개의 정수를 입력받아야 한다)
입력받은 숫자들의 평균값을 구하여 출력한다. 평균값은 소수점 이하까지 계산해야 한다.

*/

import java.util.Scanner;
public class Quiz2 {
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		
		int sum= 0;
		int jum = 0;
		double avg;  
		
		System.out.println("몇개 입력할래?");
		int count = s.nextInt();
		
		for (int i=1;i<=count;i++) {
			System.out.println(i+"번째 값 입력");
			jum = s.nextInt();
			sum = sum + jum;
		}
		avg = sum/count;
		System.out.println("평균: "+avg);
	}

}