
public class LocalVariable {

	public static void main(String[] args) {
		boolean ste = true;
		int num1 = 11;
		
		if(ste) {
			// int num1 =22;  <-엄마가 이미 만든거라 자식이 또 만들 수 없음
			num1++;
			System.out.println(num1);
		}
		{
			int num2 =33;
			num2++;
			System.out.println(num2);
		}
		
		 //System.out.println(num2); {}안에서 선언하고 닫혔기 때문이 이후 사용 불가

	}

}
