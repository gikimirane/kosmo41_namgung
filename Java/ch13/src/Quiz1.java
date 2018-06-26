import java.util.Scanner;
/*
문제1
길이가 5인 int형 배열을 선언해서 사용자로부터 5개의 정수를 입력받는다.	
그리고 최대값, 최소값, 모든 수의 합을 구하라. 함수(최대,최소,합)를 정의하여 구현하시오.
(배열을 인자로 넘기지 마시오.)  하다 안되면 무시!!
 */
public class Quiz1 {

	public static void main(String[] args) {
		
		int arr[] = new int[5];
		int max=0;	
		int min=2147483647;
		int sum=0;
		Scanner s = new Scanner(System.in);
		System.out.println("정수 5개 입력하시오.");
		
		for(int i=0;i<arr.length;i++) {
			System.out.println("입력 하세요.");
			arr[i] = s.nextInt();
		}
		// 최대값
		for(int i=0;i<arr.length;i++) {	
			if(max < arr[i]) {
				max = arr[i];
			}else {
				max = max;
			}
		}
		//최소값
		for(int i=0;i<arr.length;i++) {
			if(min > arr[i]) {
				min = arr[i];
			}else {
				min = min;
			}
		}
		//합계
		for(int i=0;i<arr.length;i++) {
			sum = sum + arr[i];
		}
		System.out.println("최대값 : "+max);
		System.out.println("최소값 : "+min);
		System.out.println("합계 : "+sum);
	}
}
