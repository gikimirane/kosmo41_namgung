import java.util.HashSet;

class Num1{
	private int num;
	
	public Num1(int n) {
		num = n;
	}
	@Override
	public String toString() {
		return String.valueOf(num);
	}
}
public class B2_HashSetEqualityOne {
	public static void main(String[] args) {
		HashSet<Num1> set = new HashSet<>();
		set.add(new Num1(7799));
		set.add(new Num1(9955));
		set.add(new Num1(7799));
		
		System.out.print("인스턴스 수 : "+set.size());
		for(Num1 n : set) {
			System.out.println(n.toString()+"\t");
		}System.out.println();
	}
}
//set<E>를 쓰면 중복은 포함되지 않는다고 했으나, add 할때 new로 num1 을 각각
//넣었기 때문에 같은게 아니라서 각각 저장되고 인스턴스수가 2가 아닌 3이
//출력되고 있는것임.