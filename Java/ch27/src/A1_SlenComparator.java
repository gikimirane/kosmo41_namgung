import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class SLenComp implements Comparator<String>{
	public int compare(String s1, String s2) {
//		s1의 길이 - s2의 길이를 리턴한다?
		return s1.length()-s2.length();
	}
}
public class A1_SlenComparator {
	public static void main(String[] args) {
//		list 부모의 인스턴스 list는 arraylist의 주소값을 참조한다
		List<String> list = new ArrayList<>();
		
		list.add("Robot");
		list.add("Lambda");
		list.add("Box");
		
		Collections.sort(list, new SLenComp());
		for(String s : list) {
			System.out.println(s);
		}
	}
}
