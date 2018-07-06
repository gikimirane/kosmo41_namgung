enum Animal2 {
	DOG,CAT;
}
enum Person2{
	MAN,WOMAN;
}
public class A4_SafeEnum {

	public static void main(String[] args) {
		System.out.println(Animal2.DOG);
		//정상적인 메소드 호출
		who(Person2.MAN);
		//비정상적인 메소드 호출, 컴파일 시 에러확인이 가능
//		who(Animal2.DOG);
//		참고 : C처럼 숫자로 비교하면 에러가 남, 그냥 man이라는 존재 그대로 써야 함
//		if(Person2.MAN==0) {
//			
//		}
	}
	public static void who(Person2 man) {
		switch(man) {
		case MAN :
			System.out.println("남성 손님입니다~");
			break;
		case WOMAN :
			System.out.println("여성 손님입니다~");
			break;
		}
	}
}
