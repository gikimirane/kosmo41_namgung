import java.time.ZoneId;

public class B1_ZoneIdDemo1 {

	public static void main(String[] args) {
		ZoneId paris = ZoneId.of("Europe/Paris");
		System.out.println(paris);
	}
}
//파리의 시간대 정보를 반영한 ZoneId 생성