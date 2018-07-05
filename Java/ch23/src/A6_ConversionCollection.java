import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class A6_ConversionCollection {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("Toy","Box","Robot","Box");
		//list.add("홍길동"); <- 이거 실행하면 에러남, new를 안해서.. 초기화를 한방에 하고 싶으면 2단계를 거쳐서 해라~
		list = new ArrayList<>(list);
		//list.add("전우치"); <- 에러 안남
		for(Iterator<String> itr = list.iterator();itr.hasNext(); ) {
			System.out.print(itr.next()+"\t");
		}System.out.println();
		
		//Array로 된 애를 Link로 변경한다!
		list = new LinkedList<>(list);
		
		for(Iterator<String> itr = list.iterator();itr.hasNext(); ) {
			System.out.print(itr.next() + "\t");
		}System.out.println();
	}
}
