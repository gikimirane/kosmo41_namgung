/*문제 05-01
사용자로부터 계속해서 정수를 입력받는다.
단 0을 입력받게되면 기존에 입력받은 모든 정수를 더한후 결과를 출력하는 프로그램을 작성하시오.
*/
public class Quiz4_re {

	public static void main(String[] args) {
		UserIO in = new UserIO();
		int sum=0,num;
		for(int i=1;;i++) {
			num = in.UserIn(i);
			sum = sum+num;
			if(num==0) break;
		}
		System.out.println("현재까지 더한 값은 : "+sum);
	}
}
