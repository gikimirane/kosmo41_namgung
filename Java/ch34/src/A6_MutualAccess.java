class Counter {
	int count =0;
	public void increment() {
		count++;
	}
	public void decrement() {
		count--;
	}
	public int getCount() {
		return count;
	}
}
// 쓰레드의 메모리 접근 방식과 그에 따른 문제점
public class A6_MutualAccess {
	public static Counter cnt = new Counter();   //Counter객체 생성
	
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
		
		Thread t1 = new Thread(task1);
		Thread t2 = new Thread(task2);
		
		t1.start();
		t2.start();
		
		t1.join();  //t1가 참조하는 쓰레드의 종료를 기다림
		t2.join();  //t2가 참조하는 쓰레드의 종료를 기다림
		
		// 쓰레드가 종료되면 출력을 진행함, 위 join 의 영향
		System.out.println(cnt.getCount());
		
//		만약 join이 없다면 시작과 동시에 println문에서 getcount(0임) 해버림
//		동일한 메모리 공간에 접근하는 것은 문제가 된다고 합니당
//		쓰레드가 값을 가져갔다가 연산해서 돌려주는데 값이 이미 변경되어 있으면 꼬여버림
	}
}
