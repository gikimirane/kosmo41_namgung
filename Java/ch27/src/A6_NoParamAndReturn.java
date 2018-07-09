import java.util.Random;

interface Generator {
	int rand();
}
public class A6_NoParamAndReturn {
	public static void main(String[] args) {
//		파라미터가 없으면 () 로 처리
		Generator gen = () -> {
			Random rand = new Random();
			return rand.nextInt(50);
		};
		System.out.println(gen.rand());
	}
}
