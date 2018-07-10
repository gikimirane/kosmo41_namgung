import java.nio.file.Path;
import java.nio.file.Paths;

public class A2_CurrentDir {

	public static void main(String[] args) {
		Path cur = Paths.get("");

		String cdir;
//		cur가 절대값이니? 맞으면 cdir에 넣어, 아니면 상대값 불러와서 cdir에 넣자!
		if(cur.isAbsolute()) {
			System.out.println("절대주소값이면~ 내가 나오지롱");
			cdir = cur.toString();
		}else {
//			아무것도 안넣었기 때문에 현재 경로를 상대값으로 넣어줌!
			System.out.println("상대주소값이면~ 내가 나오지롱");
			cdir = cur.toAbsolutePath().toString();
		}
		System.out.println("Current dir : "+cdir);
	}
}
