/*
문제 05-07
학생의 교내 전체 평균점수에 대한 학점을 출력하는 프로그램을 작성하라.
실행시 국어, 영어, 수학의 점수를 차례로 입력받은 후 평균을 구한 후
90점이상 A, 80점이상 B, 70점이상 C, 50점이상 D, 그 미만이면 F를 출력한다.

 */

import java.util.Scanner;
public class Quiz07_re {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int nJum1 = s.nextInt();
		int nJum2 = s.nextInt();
		int nJum3 = s.nextInt();
		
		int nAvg = (nJum1+nJum2+nJum3)/3;
			
		switch(nAvg/10) {
		case 9: System.out.println("A");
		case 8: System.out.println("B");
		case 7: System.out.println("C");
		case 6: System.out.println("D");
		default : System.out.println("F");
		}

	}

}
