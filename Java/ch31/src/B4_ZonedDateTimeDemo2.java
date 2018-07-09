import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class B4_ZonedDateTimeDemo2 {

	public static void main(String[] args) {
		//한국 출발 2017년 12월 9일, 14시 30분 
		ZonedDateTime departure = ZonedDateTime.of(
				LocalDateTime.of(2017,12,9,14,30), ZoneId.of("Asia/Seoul"));
		System.out.println("Departre : "+departure);
		
		//파리 도착 2017년 12월 9일, 17시 25분
		ZonedDateTime arrival = ZonedDateTime.of(
				LocalDateTime.of(2017,12,9,17,25), ZoneId.of("Europe/Paris"));
		System.out.println("Arrival : "+arrival);
		
		//비행 시간
		System.out.println(Duration.between(departure, arrival));
		//왜 서머타임 적용이 안되었을까?
	}
}
