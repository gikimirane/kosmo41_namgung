enum Scale1{
	DO,RE,MI,FA,SO,RA,TI;
}//0부터 쭈욱 들어감~
public class A3_SimpleEnum {

	public static void main(String[] args) {
		Scale1 sc = Scale1.DO;
		//Scale1클래스의 참조변수 sc = Scale1.DO를 가르키고 있음
		
		System.out.println(sc);
		
		switch(sc) {
		case DO : 
			System.out.println("도 ~");
			break;
		case RE : 
			System.out.println("레 ~");
			break;
		case MI:
			System.out.println("미 ~");
			break;
		case FA:
			System.out.println("파 ~");
			break;
		default: System.out.println("솔 ~ 라 ~ 시 ~");
		}
	}
}
//case 문에서 표현의 간결함을 위해 DO와 같이 열거형 값의 이름만 명시하기