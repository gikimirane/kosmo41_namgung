
public class B2_StringEquality {

	public static void main(String[] args) {
		String str1 = new String("So Simple");
		String str2 = new String("So Simple");
		//인스턴스 참조대상을 비교 함
		//참조 대상이 다른 이유는 new로 각각 새로 만들어서 다른 참조를 함
		if(str1 == str2) System.out.println("str1 str2 참조대상 동일");
		else System.out.println("str1 str2 참조대상 다름");
		//인스턴스 내 내용을 비교 함
		//동일한 자료형이라 내용 비교가 바로 가능함
		if(str1.equals(str2)) System.out.println("str1 st2 내용 동일");
		else System.out.println("str1 str2 내용 다름");
	}
}
