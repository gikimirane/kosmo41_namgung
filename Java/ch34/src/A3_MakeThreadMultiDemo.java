public class A3_MakeThreadMultiDemo {
	public static void main(String[] args) {
		Runnable task1 = () -> {
			try {
				for(int i =0;i<20;i++) {
					if(i%2 == 0) {
						System.out.print(i+" ");
					}
					
						Thread.sleep(1000);
				}
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
	
		Runnable task2 = () -> {
			try {
				for(int i=0;i<20;i++) {
					if(i % 2 ==1) {
						System.out.print(i+" ");
					}
					Thread.sleep(1000);
				}
			}catch(InterruptedException e) {
				e.printStackTrace();
			}	
		};
		Thread t1 = new Thread(task1);
		Thread t2 = new Thread(task2);
		t1.start();
		t2.start();
	}
}
//sleep을 상하 동일하게 1초씩 줬고 각개전투로 일을한다는데 왜 
//숫자는 0 -> 1 -> 2 -> 3 -> 으로 찍히죠????????????