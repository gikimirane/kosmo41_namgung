import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class Counter3 {
	int count =0;
	ReentrantLock criticObj = new ReentrantLock();
	
	public void increment() {
		criticObj.lock();
		
		try {
			count++;
		}finally {
			criticObj.unlock();
		}
	}
	public void decrement() {
//		일단 잠구고 count--를 시도해 그리고 다시 잠금 해제해 만약에 에러나도 잠금해제 할 수 있도록 finally 기재 한거야
		criticObj.lock();
		try {
			count--;
		}finally {
			criticObj.unlock();
		}
	}
	public int getCount() {
		return count;
	}
}
public class B4_MutualAccessReentrantLock {

public static Counter3 cnt = new Counter3();   //Counter객체 생성
	
	public static void main(String[] args) throws InterruptedException {
		Runnable task1 = () -> {
			for(int i=0;i<1000;i++) {
				cnt.increment();  //1씩 증가
			}
		};
		
		Runnable task2 = () -> {
			for(int j=0;j<1000;j++) {
				cnt.decrement();  //1씩 감소
			}
		};
		
//		최대 2개까지 pool에 쓰레드를 만들꺼얌
		ExecutorService exr = Executors.newFixedThreadPool(2);
		exr.submit(task1);
		exr.submit(task2);
		
		exr.shutdown();
		exr.awaitTermination(100, TimeUnit.SECONDS);
//		최대 100초까지 기다릴꺼얌
//		셧다운을 실행한 뒤, 지정한 시간 동안 모든 작업이 종료될 때 까지 대기한다. 
//		지정한 시간 이내에서 실행중인 모든 작업이 종료되면 true를 리턴하고, 여전히 실행중인 작업이 남아 있다면 false를 리턴한다.
		
		System.out.println(cnt.getCount());
	}
}
