import java.util.Scanner;

/*
 문제 10
길이가 4 by 4인 int형 배열을 선언하고 순서대로 1, 2, 3 … 정수를 입력하여 초기화하자.
그리고 배열의 요소들을 오른쪽 방향으로 90º씩 이동시켜서 그 결과를 출력하는 프로그램을 작성하라.
출력예)

 */
public class Quiz10 {

	public static void main(String[] args) {
		int ar[][] = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		int[][] ar1 = new int[4][4];
		Scanner s = new Scanner(System.in);
		
		for(int i=0;i<ar.length;i++) {
			for(int j=0;j<ar[j].length;j++) {
				ar1[i][j] = ar[i][j];
			}
		}
		
		
		
		
		
		

	}

}
