import java.time.LocalDate;
import java.time.Period;

public class A5_LocalDateDemo2 {
//		기념일 계산으로 응용가능!
	public static void main(String[] args) {
//		LocalDate today = LocalDate.now();
		LocalDate first = LocalDate.of(2016, 12, 31);
		System.out.println("처음 : "+first);
		
//		LocalDate xmas = LocalDate.of(today.getYear(), 12, 25);
		LocalDate today = LocalDate.now();
		System.out.println("현재까지 : "+today);
		
		Period left = Period.between(first,today);
		System.out.println("현재까지 "+left.getYears()+"년 "+left.getMonths()+"개월 "+left.getDays()+"일째");
	}
}
