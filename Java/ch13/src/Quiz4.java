import java.util.Scanner;

/*
문제4
길이가 10인 배열을 선언한후 총 10개의 정수를 입력받는다.
단 홀수는 배열의 앞에서 부터 채워나가고 짝수는 배열의 끝에서부터 채워나간다.
출력예시) 총 10개의 정수를 입력하시오.
1 2 3 4 5 6 7 8 9 10결과 : 1 3 5 7 9 10 8 6 4 2
 */
public class Quiz4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int sr[] = new int[10];
		int total[] = new int[10];
		
		//값을 10개 받기
		for(int i=0;i<sr.length;i++) {
			System.out.println("정수를 입력하세요. 총 10개");
			sr[i] = sc.nextInt();
		}
		int s=0;
		int h=9;
		for(int i=0;i<10;i++) {
			if(sr[i]%2==0) {
				total[s]=sr[i]; //짝수
				s++;
			}
			else if(sr[i]%2==1) {
				total[h]=sr[i]; //홀수
				h--;
			}
		}
		
		for(int i=0;i<10;i++) {
			System.out.print(total[i]+" ");
		}
	}	
}