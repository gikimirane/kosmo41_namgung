import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class B2_ExecutorsDemo2 {

	public static void main(String[] args) {
		Runnable task1 = () -> {
			String name = Thread.currentThread().getName();
			System.out.println(name + " : "+(5+7));
		};
		Runnable task2 = () -> {
			String name = Thread.currentThread().getName();
			System.out.println(name + " : "+(7-5));
		};
		//나는 쓰레드 2개만 만들꼬야(풀 안에 인자로 전달된 수 만큼 생성,유지
		ExecutorService exr = Executors.newFixedThreadPool(2);
		exr.submit(task1);
		exr.submit(task2);
		exr.submit(() -> {
			String name = Thread.currentThread().getName();
			System.out.println(name + " : "+(7*5));
		});
		exr.shutdown();
	}
}
