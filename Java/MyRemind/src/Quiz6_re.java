/*
문제 05-03 
1이상 100미만의 정수중에서 7의배수와 9의배수를 출력하는 프로그램을 작성하라.
단, 7의 배수이면서 동시에 9의 배수인 정수는 한번만 출력해야 한다.
*/
public class Quiz6_re {
	public static void main(String[] args) {
		MyCalculator calc = new MyCalculator();
				
		for(int i=1;i<=99;i++) {
			calc.Multiple(i,7);
			calc.Multiple(i,9);
			calc.Multiple(i, 7, 9);
		}
	}
}
