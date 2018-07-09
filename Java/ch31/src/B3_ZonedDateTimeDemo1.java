import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class B3_ZonedDateTimeDemo1 {

	public static void main(String[] args) {
		//지금 현재 시각을 here로 생성
		ZonedDateTime here = ZonedDateTime.now();
		System.out.println(here);
		
		//날짜와 시각 정보만 LocalDateTime 인스턴스에 담아서 반환
		//여기가 몇시인데 Paris는 몇시냐?를 찾는 코드
		//ZonedDateTime.of(현재는 몇시인데, Paris는 몇시니)
		ZonedDateTime paris = ZonedDateTime.of(here.toLocalDateTime(), ZoneId.of("Europe/Paris"));
//		ZonedDateTime paris = ZonedDateTime.of(localDateTime, zone);
		
		System.out.println(paris);
		System.out.println("zoneid출력 :"+ZoneId.of("Europe/Paris"));
		
		Duration diff = Duration.between(here, paris);
		System.out.println(diff);

	}

}
