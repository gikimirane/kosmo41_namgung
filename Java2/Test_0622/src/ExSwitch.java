/*

 */
import java.util.Scanner;
public class ExSwitch {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		System.out.println("태어난 월 입력 시 해당 계절을 알려줌");
		int nMon=s.nextInt();
		
		switch(nMon) {
		case 12:case 1:case 2: System.out.println("겨울"); break;
		case 3:case 4:case 5: System.out.println("봄"); break;
		case 6:case 7:case 8: System.out.println("여름"); break;
		case 9:case 10:case 11: System.out.println("가을"); break;
		default: System.out.println("이상한거 넣지마"); break;
		}

	}

}
