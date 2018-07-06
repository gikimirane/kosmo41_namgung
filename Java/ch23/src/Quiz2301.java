//농락당했어 다시 공부하기 !!
import java.util.ArrayList;
import java.util.List;

public class Quiz2301 {

	public static void main(String[] args) {
		List<Integer> list1 = new ArrayList<>();
		List<Integer> list2 = new ArrayList<>();
		List<Integer> kyo = new ArrayList<>();
		List<Integer> cha = new ArrayList<>();
		List<Integer> hap = new ArrayList<>();
		
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.add(4);
		
		list2.add(3);
		list2.add(4);
		list2.add(5);
		list2.add(6);
		
		//쓰는순간 바로 들어가는군!!
		
		//교집합!
		kyo.addAll(list1); //list1의 모든 요소를 kyo에 저장한다.
		kyo.retainAll(list2); //list2와 kyo의 공통 요소만 남기고 삭제한다.
		
		//원래 있던애하고 비교해서 공통된 애만 남기고 나머지는 다 지워줌
		
		//차집합!!
		cha.addAll(list1); 
		cha.removeAll(list2);  //char에서 list2와 공통된 요소들을 모두 삭제한다.
		//겹친걸 지워준다
		
		//합집합!!
		hap.addAll(list1);
		hap.removeAll(list2);  //hap에서 kyo와 공통된 모든 요소를 삭제한다.
		hap.addAll(list2);     //list2의 모든 요소를 hap에 저장한다.
	
		System.out.println("list1 = "+list1);
		System.out.println("list2 = "+list2);
		System.out.println("kyo = "+kyo);
		System.out.println("cha = "+cha);
		System.out.println("hap = "+hap);
	}
}