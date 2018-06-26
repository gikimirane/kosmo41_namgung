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
		
		int[] sr = new int[10];
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
		
		System.out.println(hCount);
		System.out.println(sCount);
		
		System.out.println("짝수 출력 : ");
		for(int i=0;i<10;i++) {
			if((i+1)<sCount) {
				System.out.print(s_sr[i]+", ");
				
			}else if((i+1)==sCount) {
				System.out.print(s_sr[i]);
			}
		}
		System.out.println();
		System.out.println("홀수 출력 : ");
		for(int i=0;i<10;i++) {
			if((i+1)<(hCount)) {
				System.out.print(h_sr[i]+", ");
				
			}else if ((i+1)==(hCount)){
				System.out.print(h_sr[i]);
			}
		}		
	}
}
