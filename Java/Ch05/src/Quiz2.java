/*
문제 05A-02
사용자로부터 다음 순서대로 정수를 입력받은 후 평균을 구하여 출력하는 프로그램을 작성하시오.
몇개의 정수를 입력할 지 사용자로부터 입력받는다.
입력받은 숫자만큼 정수를 입력받는다.(1에서 3이라고 입력했다면 3개의 정수를 입력받아야 한다)
입력받은 숫자들의 평균값을 구하여 출력한다. 평균값은 소수점 이하까지 계산해야 한다.
*/

import java.util.Scanner;
public class Quiz2 {
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		//이건 for 문으로 짰어
		int sum= 0; // 합계 변수
		int jum = 0; //점수 변수
		double avg;  //평균을 담을 변수
		
		System.out.println("몇개의 점수를 넣으시겠습니까?");
		int count = s.nextInt();
		
		for (int i=1;i<=count;i++) {
			System.out.println(i+"번째 점수를 넣으세요.");
			jum = s.nextInt();
			sum = sum + jum;
		}
		avg = sum/count;
		System.out.println("입력한 점수의 평균은 : "+avg);
	}

}

/*do while 문으로 짰음
int nsum=0;
int i=1;
double navg;

System.out.println("몇번 입력받을꺼야");
int count = s.nextInt();

do {
	
	System.out.println("점수부터 입력해");
	int njum = s.nextInt();
	nsum = nsum + njum;
	i++;
	
}while(i<=count);

navg = nsum / count;
System.out.println("평균을 내주겠다 : "+ navg);

*/

/* 이건 While 문으로 짰어
int i=1;
int nsum=0;
double davg;

System.out.println("몇번 입력받을꺼야");
int count = s.nextInt();

while (i<=count) {
	System.out.println("몇점이야, 횟수만큼 등장");
	int njum = s.nextInt();
	nsum = nsum + njum;
	i++;
}

davg = nsum / count;
System.out.println("입력한거 평균내줄게 : "+davg);
*/


/* 설계하는 사고 방법을 알아야 함!

3번 가량 반복한다고 가정을 하고 일일이 대충 써보기
3번이 돌아가게끔만 코딩하고
그 다음에 변수를 넣고 더욱 세세하게 가공하기

처음부터 나올리가 없다!
변수 앞에 선언한 변수형태의 앞자리를 붙히면 유용, 회사마다 네이밍에 대한 Rule은 있음
*/