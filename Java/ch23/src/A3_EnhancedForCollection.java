import java.util.LinkedList;
import java.util.List;

public class A3_EnhancedForCollection {

	public static void main(String[] args) {
		//Arraylist랑 Linkedlist랑 다 똑같은데 이 부분만 다름
		List<String> list = new LinkedList<>();  //List인터페이스로 만드는데 Arraylist에 있는 메소드 쓸거야
		//컬렉션 인스턴스를 문자열 인스턴스에 저장
		list.add("Toy");
		list.add("Box");
		list.add("Box");    // 중복허용
		list.add("Robot");
		
		//list는 순서대로 넣는다.
		
		//저장된 문자열 인스턴스의 참조
		for(String s : list) {
			System.out.print(s +"\t");
		}System.out.println();
		
		list.remove(0);
		//list의 0번째 데이터를 지워라
		
		//삭제 후 저장된 문자열 인스턴스의 참조
		
		//enhanced for문을 사용하여 단순하게 기재!
		//list를 순서대로 s에 하나씩 보내라~ = for(int i=0;i<list.size;i++)
		for(String s : list) {
			System.out.print(s+"\t");
		}System.out.println();
	}
}


