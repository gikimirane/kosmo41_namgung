
public class B2_ImmutableString {

	public static void main(String[] args) {
		String str1 = "Simple String";
		String str2 = "Simple String";
		
		String str3 = new String("Simple String");
		String str4 = new String("Simple String");
		
		//String을 == 로 비교하면 참조주소를 비교 하게 됨
		//new로 생성한 애들은 각각 새로운 주소를 파서 만든거라 다른 인스턴스를 참조
		//str1에서 먼저 만들고 str2가 새로운 것을 만드려고 할 때 아까 만든거라고 스스로 생각하고
		//str1과 2는 동일한 주소를 참조하게 됨
		
		if(str1==str2) {
			System.out.println("str1과 str2는 동일 인스턴스 참조");
		}
		else {
			System.out.println("str1과 str2는 다른 인스턴스 참조");
		}
		
		if(str3==str4) {
			System.out.println("str3과 str4는 동일 인스턴스 참조");
		}else {
			System.out.println("str3과 str4는 다른 인스턴스 참조");
		}

	}

}
