import java.time.LocalDateTime;

public class A8_LocalDateTimeDemo1 {

	public static void main(String[] args) {
//		현재 날짜와 시각
		LocalDateTime dt = LocalDateTime.now();
		System.out.println(dt);
//		영국 바이어와 22시간 35분 뒤 화상미팅 예정
		LocalDateTime mt = dt.plusHours(22);
		mt = mt.plusMinutes(35);
		
//		영국 바이어와 화상미팅 날짜 및 시각
		System.out.println(mt);
	}
}
// LocalDate는 날짜, LocalTime시간, LocalDateTime은 날짜+시각 동시