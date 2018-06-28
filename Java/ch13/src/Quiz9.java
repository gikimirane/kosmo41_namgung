/*
가위바위보 게임 만들기
게임설명 :
1,2,3중 하나의 숫자를 난수로 생성한다.
사용자가 가위(1),바위(2),보(3) 중 하나를 낸다.
승부를 판단하여 출력한다.
1,2,3 이외의 숫자를 입력하면 잘못된 입력을 알려준다.
*/
import java.util.Random;
import java.util.Scanner;
public class Quiz9 {

	public static void main(String[] args) {
		Random r = new Random();
		Scanner s = new Scanner(System.in);
		
		while(true) {
			System.out.println("가위 (1), 바위 (2), 보(3) 중 1개 입력하세요.");
			int nUser = s.nextInt();     //입력받음
			int nRan = r.nextInt(3)+1;   //랜덤발생 (1~3)
			
			if(nUser > 3 || nUser < 0) {              // 3 초과 입력 시 재입력 경고 후 continue
				System.out.println("가위 (1), 바위 (2), 보(3)로 입력해주세요.");
				continue;
			}
			if(nRan==nUser) {               //비겼을 시 비겼다고 하고 재입력 유도
				System.out.println("사용자 : "+nUser+" vs 컴퓨터 : "+nRan);
				System.out.println("비겼습니다.");
				continue;
			}
			if(nUser==1 && nRan == 3) {    // 유저 입력이 이길 경우만 비교하여 이겼다고 출력해주고 그 외는 다 진거로 처리
				System.out.println("사용자 : "+nUser+" vs 컴퓨터 : "+nRan);
				System.out.println("이겼습니다!");
				
			}else if(nUser == 2 && nRan == 1) {
				System.out.println("사용자 : "+nUser+" vs 컴퓨터 : "+nRan);
				System.out.println("이겼습니다!");
				
			}else if(nUser==3 && nRan == 2) {
				System.out.println("사용자 : "+nUser+" vs 컴퓨터 : "+nRan);
				System.out.println("이겼습니다!");
				
			}else {
				System.out.println("사용자 : "+nUser+" vs 컴퓨터 : "+nRan);
				System.out.println("졌습니다.흑흑");
			}
		}
	}
}
