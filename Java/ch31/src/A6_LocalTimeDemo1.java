import java.time.LocalTime;
public class A6_LocalTimeDemo1 {

	public static void main(String[] args) {
		LocalTime now = LocalTime.now();
		System.out.println("현재 시각 : "+now);
		
		//현재로 부터 2시간 10분 뒤로 설정
		LocalTime mt = now.plusHours(2);
		mt = mt.plusMinutes(10);
		
		System.out.println("화상 미팅 시각 : "+mt);
	}
}
