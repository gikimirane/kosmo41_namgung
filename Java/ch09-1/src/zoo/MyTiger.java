package zoo;
//Tiger는 Default로 선언되었으므로 동일 패키지 내에서만 가능

class Tiger{
	//빈 클래스
}
public class MyTiger {
	public void makeTiger() {
		//Tiger 와 같은 패키지로 묶여있으니 Tiger 인스턴스 생성 가능
		Tiger hodol = new Tiger();
	}
}
