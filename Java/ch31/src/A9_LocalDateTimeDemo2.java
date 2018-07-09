import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

public class A9_LocalDateTimeDemo2 {

	public static void main(String[] args) {
		LocalDateTime today = LocalDateTime.of(2020, 4,25,11,20);
		LocalDateTime flight1 = LocalDateTime.of(2020, 5,14,11,15);
		LocalDateTime flight2 = LocalDateTime.of(2020, 5,13,15,30);
		
		LocalDateTime myFlight;
//		만약에 플라이트1이 플라이트2보다 앞이면 myflight는 flight1로 설정
		if(flight1.isBefore(flight2)) {
			myFlight = flight1;
		}else myFlight = flight2;
		
//		날짜 계산하는 애 Period , 둘 사이의 날짜 계산..
		Period day = Period.between(today.toLocalDate(), myFlight.toLocalDate());
//		시간 계산하는 애 Duration , 둘 사이의 시간 계산
		Duration time = Duration.between(today.toLocalTime(), myFlight.toLocalTime());
		
		System.out.println(day);
		System.out.println(time);
	}
}
