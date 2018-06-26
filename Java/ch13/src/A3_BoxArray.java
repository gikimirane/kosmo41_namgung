//값의 저장과 참조의 예
class Box1 {
	private String conts;
	
	Box1(String cont) 
	{
		this.conts = cont;
	}
	public String toString() {
		return conts;
	}
}
public class A3_BoxArray {

	public static void main(String[] args) {
		Box1[] ar = new Box1[3];
		//배열에 인스턴스에 저장
		ar[0] = new Box1("First");
		ar[1] = new Box1("Second");
		ar[2] = new Box1("Third");
		//저장된 인스턴스를 참조
		System.out.println(ar[0]);
		System.out.println(ar[1]);
		System.out.println(ar[2]);
	}

}
