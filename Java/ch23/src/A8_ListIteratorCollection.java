import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class A8_ListIteratorCollection {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("Toy","Box","Robot","Box");
		list = new ArrayList<>(list);
		
		ListIterator<String> litr = list.listIterator(); //반복자
		String str;
		
		//왼쪽에서 -> 오른쪽으로 이동하면서 출력
		//만약에 현재 가르키고 있는 영역 뒷 문구가 Toy이면 사이에 Toy2를 넣어라
		while(litr.hasNext()) {
			str = litr.next();
			System.out.print(str+"\t");
			if(str.equals("Toy")) {
				litr.add("Toy2");
			}
		}System.out.println();
		
		//litr이 맨뒤에 있으니까 뒤에서 부터 다시 앞으로 오면서 프린트
		//그러므로 초기화 하지 않음(litr = list.listIterator();
		while(litr.hasPrevious()) {
			str = litr.previous();
			System.out.print(str + "\t");
			
			if(str.equals("Robot")) {
				litr.add("Robot2");
			}
		}System.out.println();
		
		for(Iterator<String> itr = list.iterator(); itr.hasNext(); ) {
			System.out.print(itr.next()+"\t");
		}System.out.println();
	}
}
