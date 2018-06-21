
public class SCE {

	public static void main(String[] args) {
		int num1 = 0;
		int num2 = 0;
		boolean result;
		
		result = ((num1 +=10) < 0) && ((num2 += 10) > 0);
		System.out.println("result = " + result);
		System.out.println("num1 = " + num1);
		System.out.println("num2 = " + num2);
		
		result = ((num1 += 10) > 0) || ((num2 += 10) > 0);
		System.out.println("result = " + result);
		System.out.println("num1 = " + num1);
		System.out.println("num2 = " + num2);
	}

}

/* 포인트, AND 연산의 경우 앞쪽이 false면 계산할 의미가 없으므로 뒷쪽은 연산되지 않음,
만약 뒤쪽에 계산이 되어야 하는 식이 있다면 주의 필요 함 (순서를 바꾸다던지.)

먼저 계산되어야 할 것이 있다면 앞쪽으로 배치하여 시간을 단축, 큰것부터 검사될 수 있도록..
*/
