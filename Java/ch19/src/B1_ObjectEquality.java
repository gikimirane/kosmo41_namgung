class INum {
	private int num;
	public INum(int num) {
		this.num = num;
	}
	@Override
	public boolean equals(Object obj) {      //object 클래스(기본)에 있는 메소드를 오버라이딩
		if(this.num == ((INum)obj).num) {    //this.num = 클래스에서 선언한 변수이고, ((INum)obj).num은 생성자 내의 인자를 의미함
			return true;
		}else return false;
	}
}
public class B1_ObjectEquality {
	public static void main(String[] args) {
		INum num1 = new INum(10);
		INum num2 = new INum(12);
		INum num3 = new INum(10);
		
		if(num1.equals(num2)) System.out.println("num1 num2 내용 동일");
		else System.out.println("num1 num2 내용 다름");
		
		if(num1.equals(num3)) System.out.println("num1 num3 내용 동일");
		else System.out.println("num1 num3 내용 다름");
	}
}
//비교 대상이랑 형태가 다르면 인스턴스를 비교 (int vs String이면 인스턴스를 비교해 버린다
//equals는 object 클래스의 메소드
