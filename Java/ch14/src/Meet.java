//interface는 구현해야 하지만 아직 구현되지 않은 애들의 묶음
//=> 나같으면 만들고 싶은것들을 구상할 때 interface로 만들고 까먹지 않고 메소드를 구현할 수 있도록 할거 같아.
//개를 만든다고 가정하면 개한테 필요한 동작들은 적어놨지만 실제로 동작시키지는 못하게 모아둔 것들(왜냐? 동작을 안적어서)

//상속은 내가 닮고 싶은 부분들의 집합이라고 생각하자. 
//    그리고 닮고 싶으면 extends로 불러와서 똑같이 사용 혹은 수정(오버라이딩+super)해서 사용이 가능
//추상클래스 abstract는 전체적으로 대충은 구현해놨는데 특정 n개의 동작은 구현하지 않은 상태. 
//           꼭 있어야 하는 동작이지만 너 입맛에 맞게 구현해서 써라 라는 의미

interface Greet {
	void greet();
	//외국인이 인사할수도 있고 한국인이 인사할수도 있으니까 나를 쓰려고 하는 사람은 너 알아서 만드세용
}
interface Talk {
	void talk();
}

class Morning implements Greet, Talk {
	public void greet() {    //여기서 오버라이딩 (개념이 동일함, 내가 데려온 인터페이스에 있는 메소드를 구현해서 씀)
		System.out.println("안녕하세요!");
	}
	public void talk() {
		System.out.println("날씨 좋네요.");
	}
}

public class Meet {

	public static void main(String[] args) {
		Morning morning = new Morning();
		morning.greet();
		morning.talk();
	}

}
