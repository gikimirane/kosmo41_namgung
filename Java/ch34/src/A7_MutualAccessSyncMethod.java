class Counter1 {
	int count =0;
	synchronized public void increment() {
		count++;
	}
	synchronized public void decrement() {
		count--;
	}
	synchronized public int getCount() {
		return count;
	}
}
public class A7_MutualAccessSyncMethod {

	public static Counter1 cnt = new Counter1();   //Counter객체 생성
	
	public static void main(String[] args) throws InterruptedException {
		Runnable task1 = () -> {
			for(int i=0;i<1500;i++) {
				cnt.increment();  //1씩 증가
			}
		};
		
		Runnable task2 = () -> {
			for(int j=0;j<1000;j++) {
				cnt.decrement();  //1씩 감소
			}
		};
		
		Thread t1 = new Thread(task1);
		Thread t2 = new Thread(task2);
		
		t1.start();
		t2.start();
		
		t1.join();  //t1가 참조하는 쓰레드의 종료를 기다림
		t2.join();  //t2가 참조하는 쓰레드의 종료를 기다림
		
		// 쓰레드가 종료되면 출력을 진행함, 위 join 의 영향
		System.out.println(cnt.getCount());
		
	}
}
