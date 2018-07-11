import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class B3_CallableDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Callable<Integer> task = () -> {
			int sum =0;
			for(int i=0;i<10;i++) {
				sum = sum+i;
			}
			return sum;
		};
		
		ExecutorService exr = Executors.newSingleThreadExecutor();
		Future<Integer> fur = exr.submit(task);
		
		Integer r = fur.get();
		System.out.println("Result : "+r);
		exr.shutdown();
	}
}
//Future랑 Callable이랑은 짝꿍, 리턴이 가능한 애다
//Runnable 리턴X