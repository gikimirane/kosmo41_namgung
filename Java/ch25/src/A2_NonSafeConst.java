interface Animal1{
	int DOG=1;
	int CAT=2;
}
interface Person1{
	int MAN=1;
	int WOMAN=2;
}
public class A2_NonSafeConst {

	public static void main(String[] args) {
		//정상적인 메소드 호출
		who(Person1.MAN);
		//비 정상적인 메소드 호출
		//에러는 안나는데 who 메소드를 가면 개도 사람이 되버림
		//컴파일 오류시 찾을 수 없는 오류임
		who(Animal1.DOG);
	}
	public static void who(int man) {
		switch(man) {
		case Person1.MAN:
			System.out.println("남성 손님입니다.");
			break;
		case Person1.WOMAN:
			System.out.println("여성 손님 입니다.");
			break;
		}
	}
}
//값을 숫자로 받다보니 개던 사람이던 1,2 둘중 하나만 받아도 걍 실행이 됨