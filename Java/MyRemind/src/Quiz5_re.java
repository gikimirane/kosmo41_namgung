/*
문제 05-02
사용자로부터 다음 순서대로 정수를 입력받은 후 평균을 d구하여 출력하는 프로그램을 작성하시오.
1.	몇개의 정수를 입력할 지 사용자로부터 입력받는다
2.	입력받은 숫자만큼 정수를 입력받는다.(조건1에서 3이라고 입력했다면 3개의 정수를 입력받아야 한다)
3.	입력받은 숫자들의 평균값을 구하여 출력한다. 평균값은 소수점 이하까지 계산해야 한다.

*/
public class Quiz5_re {

	public static void main(String[] args) {
		UserIO in = new UserIO();
		MyCalculator calc = new MyCalculator();
		
		System.out.println("몇개 입력할껀데");
		int[] ar = new int [in.UserIn()];
		int sum=0;
		for(int i=0;i<ar.length;i++) {
			ar[i] = in.UserIn(i+1);
			sum = sum + ar[i];
		}
		
		System.out.println("평균은 : "+(double)calc.Divi(sum, ar.length));

	}

}
