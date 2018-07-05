import java.util.ArrayDeque;
import java.util.Deque;

public class D2_ArrayDequeCollection {

	public static void main(String[] args) {
		Deque<String> deq = new ArrayDeque<>();
//		Deque<String> deq = new LinkedList<>(); 가능
		System.out.println("offerFirst + pollFirst");
		System.out.println();
		
		//앞으로 넣고
		deq.offerFirst("1. Box");
		deq.offerFirst("2. Toy");
		deq.offerFirst("3. Robot");
		//앞부터 출력
		System.out.println(deq.pollFirst());
		System.out.println(deq.pollFirst());
		System.out.println(deq.pollFirst());
		
		System.out.println();
		System.out.println("offerLast + pollLast");
		
		//뒤로 넣고
		deq.offerLast("1.Box");
		deq.offerLast("2.Toy");
		deq.offerLast("3.Robot");
		//뒤에서 부터 출력
		System.out.println(deq.pollLast());
		System.out.println(deq.pollLast());
		System.out.println(deq.pollLast());
		
		System.out.println();
		System.out.println("offerLast + pollFirst");
		
		//뒤로 넣고
		deq.offerLast("1.Box");
		deq.offerLast("2.Toy");
		deq.offerLast("3.Robot");
		//앞부터 출력
		System.out.println(deq.pollFirst());
		System.out.println(deq.pollFirst());
		System.out.println(deq.pollFirst());
	}

}
