package animal;

public class WatchZoo {
	public void makeTiger2() {
		//zoo은 public으로 선언되어서 어디든지 인스턴스 생성 가능
		zoo.MyTiger hosun = new zoo.MyTiger();
		
	}
	public void makeTiger3() {
		//zoo.Tiger hosun = new zoo.Tiger();
		//Tiger는 Default로 선언되어 이 위치에서는 인스턴스 생성 불가
		//zoo 패키지 내 MyTiger 클래스에 public 이 없어서 외부에서 불러오지 못함
	}

}
