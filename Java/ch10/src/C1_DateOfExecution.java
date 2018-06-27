import java.time.LocalDate;

public class C1_DateOfExecution {
	static String date;   //static 변수인데 메소드에서 쓰려면 걔도 static 이여야 해
	
	static {              //그럼 얘는 메소드인가? date라는 전역 변수를 사용하기 위해서 메소드도 static인가?
		LocalDate nDate = LocalDate.now();
		date = nDate.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(date);
	}

}
