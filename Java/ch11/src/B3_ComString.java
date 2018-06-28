
public class B3_ComString {

	public static void main(String[] args) {
		
		String st1 = "camera";
		String st2 = "apple";
		int cmp;
		
		//인스턴스 내용 비교 (주소X)
		
		if(st1.equals(st2)) {
			System.out.println("두 문자열은 같습니다.");
		}else {
			System.out.println("두 문자열은 다릅니다.");
		}
		//st1을 st2랑 비교하여 맨 앞글자를 사전적인 순서로 비교하여 숫자를 알려줌
		//대소문자를 명확히 구문해서 알려줌
		//st1과 st2를 비교 시 st1이 st2보다 크면 양수, st1이 st2보다 작으면 음수
		//같으면 0을 출력함
		cmp = st1.compareTo(st2);
		if(cmp ==0) {
			System.out.println("두 문자열은 일치합니다.");
		}else if (cmp < 0) {
			System.out.println("사전 앞에 위치하는 문자 : "+st1);
		}else {
			System.out.println("사전 뒤에 위치하는 문자 : "+st2);
		}
		
		//대소문자를 무시하고 비교해라
		if(st1.compareToIgnoreCase(st2)==0) {
			System.out.println("두 문자열은 같습니다.");
		}else {
			System.out.println("두 문자열은 다릅니다.");
		}

	}

}
