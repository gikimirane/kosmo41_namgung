import java.util.Comparator;
import java.util.TreeSet;

class StringComparator implements Comparator<String>{
	public int compare(String s1, String s2) {
		return s1.length() - s2.length();
		//길이가 동일한 애는 add되지 않음
	}
}
//길이 순으로 정렬함, 기존에 있는건 안건들고 새로운거 만들어서 씀
public class C4_ComparatorString {

	public static void main(String[] args) {
		TreeSet<String> tree = new TreeSet<>(new StringComparator());
//		TreeSet<String> tree = new TreeSet<>(); //기존꺼 그냥 쓰면 ABC 순으로 정렬됨
		tree.add("Box");
		tree.add("Rabbit");
		tree.add("Robot");
//		tree.add("Pig");  <= 추가되지 않음, 3글자 길이가 같아서
		for(String s : tree) {
			System.out.println(s.toString()+"\t");
		}System.out.println();
	}
}
//