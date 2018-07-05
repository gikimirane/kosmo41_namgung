import java.util.HashSet;
class Num2 {
	private int num;
	
	public Num2(int n) {
		num = n;
	}
	@Override
	public String toString() {
		return String.valueOf(num);
	}
	@Override
	public int hashCode() {
		return num %3;
		//나머지가 0,1,2 로 나와서 분류하도록
	}
	@Override
	public boolean equals(Object obj) {
		if(num==((Num2)obj).num) return true;
		else return false;
	}
//	equals를 오버라이딩해서 주소값 비교가 아닌 내용 비교로 변경 함
//	클래스를 비교해서 new했기때문에 그전에는 3개 찍어줌, 지금은 2개 찍어줌
}
public class B3_HashSetEqualityTwo {

	public static void main(String[] args) {
		HashSet<Num2> set = new HashSet<>();
		set.add(new Num2(7799));
		set.add(new Num2(9955));
		set.add(new Num2(7799));
		
		System.out.println("인스턴스 수 : "+set.size());
		for(Num2 n : set) {
			System.out.println(n.toString()+"\t");
		}System.out.println();
	}
}
