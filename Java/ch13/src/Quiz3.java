import java.util.Scanner;

/*
문제3
길이가 10인 배열을 선언하고 총 10개의 정수를 입력받아서 홀수와 짝수를
구분해서 출력하라.
출력예시) 총 10개의 숫자를 입력하시오.
1 2 3 ....... 10개입력
홀수출력 : 1, 3, 5, 7
짝수출력 : 2, 4, 6, 8, 10
마지막 따옴표를 떼는 방법!!!!!
 */
public class Quiz3 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int sr[] = new int[10];
		int[] sA = new int [10];
		int[] hA = new int [10];
		//값을 10개 받기
		for(int i=0;i<sr.length;i++) {
			System.out.println("정수를 입력하세요. 총 10개");
			sr[i] = sc.nextInt();
		}
		int s=0;
		int h=0;
		for(int i=0;i<10;i++) {
			if(sr[i]%2==0) {
				sA[s]=sr[i]; //기존 배열을 짝수배열로 배치
				s++; 	     //홀수의 개수
			}
			else if(sr[i]%2==1) {
				hA[h]=sr[i]; //기존 배열을 홀수배열로 배치
				h++; 	     //홀수 배열 위치 이동
			}
		}
		System.out.println("확인용 : "+s+" / "+h);
		
		System.out.println("짝수 출력 : ");
		for(int i=0;i<10;i++) {
			if(i<(s-1)) {      // -1의 이유는 배열 주소는 0부터 시작하지만, length는 1부터 n개까지 n개로 표현하기 때문
				System.out.print(sA[i]+", ");
				
			}else if(i==(s-1)) {
				System.out.print(sA[i]);
			}
		}
		System.out.println();
		System.out.println("홀수 출력 : ");
		for(int i=0;i<10;i++) {
			if(i<(h-1)) {
				System.out.print(hA[i]+", ");				
			}
			else if (i==h-1)
			{
				System.out.print(hA[i]);
			}
		}		
	}
}

/*
 		/*int[] sr = new int[10];
		Scanner s = new Scanner(System.in);
		
		for(int i=0;i<10;i++) {
			System.out.println("총 10개의 값 입력하세요.");
			sr[i] = s.nextInt();
		}
		
		int[] s_sr = new int[10]; // 짝
		int[] h_sr = new int[10]; // 홀
		int sCount = 0;
		int hCount = 0;
		for(int i=0;i<sr.length;i++) {
			if(sr[i]%2 ==0) {
				s_sr[sCount]=sr[i];
				sCount++;
			}else {
				h_sr[hCount]=sr[i];
				hCount++;
			}
		}
		*/

