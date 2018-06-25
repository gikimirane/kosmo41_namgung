/*
문제 05-07
학생의 교내 전체 평균점수에 대한 학점을 출력하는 프로그램을 작성하라.
실행시 국어, 영어, 수학의 점수를 차례로 입력받은 후 평균을 구한 후
90점이상 A, 80점이상 B, 70점이상 C, 50점이상 D, 그 미만이면 F를 출력한다.

*/
import java.util.Scanner;
public class Quiz7 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
	
		int nAvg = 0;
		
		System.out.println("국어");
		int nLan = s.nextInt();
		System.out.println("영어");
		int nEng = s.nextInt();
		System.out.println("수학");
		int nMat = s.nextInt();
		
		nAvg = (nLan+nEng+nMat)/3;
		
		if (nAvg >= 90) {
			System.out.println("A");
		}else if (nAvg >= 80) {
			System.out.println("B");
		}else if (nAvg >= 70) {
			System.out.println("C");
		}else if (nAvg >= 50) {
			System.out.println("D");
		}else System.out.println("F");
		
	}

}
