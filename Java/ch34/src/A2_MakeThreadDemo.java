
public class A2_MakeThreadDemo {

	public static void main(String[] args) {

		Runnable task = () -> {
			try {
				Thread.sleep(3000);      //3초후에 시작 됩니다~
			} catch (Exception e) {
			}
			
			int n1 = 10;
			int n2 = 20;
			String name = Thread.currentThread().getName();
			System.out.println(name + " : "+(n1+n2));
		};
		
		Thread t = new Thread(task);  //생성자로 task 를 넣음
//		여기서 시작 됐지만 3초 sleep이라는 구문을 만나서 3초뒤에 출력 됨, 이때 end는 기다려주지 않음, 각개전투라서
		t.start();
//		Thread랑은 별개라서 얘는 바로 출력되고 Thread는 3초뒤에 출력 됨
		System.out.println("End : "+Thread.currentThread().getName());
	}
}

