import java.time.Duration;
import java.time.Instant;

public class A1_InstantDemo {

	public static void main(String[] args) {
		Instant start = Instant.now();
		System.out.println("시작 : "+start.getEpochSecond());
		
		System.out.println("Time files like an arrow.");
		
		for(int i=0;i<10000000;i++) {
			//차이를 내기위해 기재한 영역임!! 
		}
		
		Instant end = Instant.now();
		System.out.println("끝 : "+end.getEpochSecond());
		
		Duration between = Duration.between(start, end);
		System.out.println("밀리 초 단위 차 : "+between.toMillis());

	}

}
