import java.util.ArrayList;
import java.util.List;

public class A1_ArrayListCollection {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();  //List인터페이스로 만드는데 Arraylist에 있는 메소드 쓸거야
		list.add("Toy");
		list.add("Box");
		list.add("Box");    // 중복허용
		list.add("Robot");
		
		//list는 순서대로 넣는다.
		
		for(int i =0;i<list.size();i++) {
			System.out.print(list.get(i)+"\t");
		}System.out.println();
		
		list.remove(0);
		//list의 0번째 데이터를 지워라
		
		for(int i=0;i<list.size();i++) {
			System.out.print(list.get(i)+"\t");
		}System.out.println();
	}
}

//배열 기반 자료 구조이지만 공간의 확보 및 확장은 Arraylist 인스턴스가 스스로 처리