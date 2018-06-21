
public class UnaryAddMin {

	public static void main(String[] args) {
		//단순히 출력이기 때문에 형변환은 필요하지 않음, (물론 변환되지도 않았음)
		short num1 = 5;
		System.out.println(+num1);
		System.out.println(-num1);
		
		
		//연산은 CPU가 한다, CPU가면 숫자는 모두 int가 되어서 강제로 변환해야 함
		short num2 = 7;
		short num3 = (short)(+num2); 
		short num4 = (short)(-num2);
		System.out.println(num3);
		System.out.println(num4);
	}
}
