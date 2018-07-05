import java.util.LinkedList;
import java.util.Queue;

public class D1_LinkedListQueue {

	public static void main(String[] args) {
		Queue<String> que = new LinkedList<>();
		que.offer("Box");
		que.offer("Toy");
		que.offer("Robot");
		
		//다음에 뭐 있는지 확인함
		System.out.println("next : "+que.peek());
		//데이터 하나 데려옴, 데려온 데이터는 이제 queue상에서 사라짐
		System.out.println(que.poll());
		System.out.println(que.poll());
		
		System.out.println("next : "+que.peek());
		System.out.println(que.poll());
		
//		활용 가능 예
//		while(true) {
//			if(que.peek()==null) break;
//			System.out.println(que.poll()+"\t");
//			
//		}
		
	}

}
