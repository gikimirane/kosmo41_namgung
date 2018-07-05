import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class C4_ConvertCollection {

	public static void main(String[] args) {
		List<String> lst = Arrays.asList("Toy","Boy","Boy","Toy");
		ArrayList<String> list = new ArrayList<>(lst);
		
		for(String s : list) System.out.print(s.toString()+"\t");
		System.out.println();
		
		//중복을 걸러내기 위해 hashset에 배열복사
		HashSet<String> set = new HashSet<>(list);
		
		//중복제거되고 알파벳순으로 정렬된 내용을 다시 list에 복사
		list = new ArrayList<>(set);
		
		for(String s : list) System.out.print(s.toString()+"\t");
		System.out.println();

	}

}
