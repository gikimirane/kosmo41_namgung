import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

//이터레이터
public class A4_IteratorCollection {

	public static void main(String[] args) {
		List<String> list = new LinkedList<>();
		list.add("Toy");
		list.add("Box");
		list.add("Robot");
		list.add("Box");
		
		//반복자 획득
		Iterator<String> itr = list.iterator();
		
		while(itr.hasNext()) {
			System.out.print(itr.next()+'\t');
		}System.out.println();
		
		//반복자 다시 획득, 가장 좋은 case는 이터레이터를 쓸때마다 다시 구하기
		itr= list.iterator();
		
		//box 삭제
		String str;
		while(itr.hasNext()) {       //itr의 다음에 뭐가 있으면
			str = itr.next();        //다음꺼를 str에 넣어
			if(str.equals("Box")){   //만약에 str이 box이면 이터레이터가 가르키고 있는 애를 지워
				itr.remove();
			}
		}
		itr = list.iterator();
		while(itr.hasNext()) {
			System.out.print(itr.next()+'\t');
		}System.out.println();
	}

}
