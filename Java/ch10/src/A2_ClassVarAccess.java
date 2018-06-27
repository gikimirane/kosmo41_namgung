class AAA{
	static int num =0; //static메소드를 부르려면 동일하게 메소드 메모리에 있는 static 변수가 와야 함
	static void addNum(int n) {
		num = num+n;
	}
}

class AccessWay{
	static int num=0;
	
	AccessWay(){
		incrCnt();
	}
	void incrCnt() {
		num++;
	}
}
public class A2_ClassVarAccess {
//static은 이미 메소드 메모리 영역에 있기 때문에 new를 할 필요가 없음(변수, 메소드)
	public static void main(String[] args) {
		AccessWay way  = new AccessWay(); //선언하면서 생성자에 의해 1추가
		System.out.println("num = "+AccessWay.num);
		way.num++; // 전역변수 num에 1추가 (기존 1+1)
		System.out.println("num = "+AccessWay.num);
		AccessWay.num++; //[표준 기술은 이렇게..]클래스 이름을 대놓고 쓰고 기존에 2에 +1을 함
		System.out.println("num = "+AccessWay.num);

	}

}
