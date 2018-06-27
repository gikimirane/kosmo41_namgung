class InstCnt{
	static int instNum=0; 
	//int instNum=0;
	
//Static은 전역변수로 Class를 만들 때마다 초기화를 하지 않고 최초 1회만 한다
//Static은 스택이나 힙에 들어가는게 아니고 메소드 영역에 들어가기 때문에 휘발하지 않는다
	
	InstCnt(){
		instNum++;
		System.out.println("인스턴스 생성 : "+instNum);
	}
}
public class A1_ClassVar {
	public static void main(String[] args) {
		InstCnt cnt1 = new InstCnt();
		InstCnt cnt2 = new InstCnt();
		InstCnt cnt3 = new InstCnt();
	}

}
