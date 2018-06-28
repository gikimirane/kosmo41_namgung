
/*
하이로우 게임 만들기

게임설명 :
컴퓨터가 낸 숫자를 맞춘다.
기회는 6회 주어진다. (5회로 하면 난이도 상승)
숫자를 맞추거나 기회를 다 소진하면 다시 할  것인가를 물어본다.
 */
import java.util.Random;
import java.util.Scanner;

public class Highlow {

	public static void main(String[] args) {
		Random r = new Random();
		Scanner s = new Scanner(System.in);
		
		int cNum = r.nextInt(101);
		System.out.println("나는 지금 0~100사이의 값 중 하나를 생각하겠습니다.");
		System.out.println("당신은 6회 안에 맞추시면 됩니다.");
		
		while(true) {
			for(int i=6;i>=1;i--) {
				System.out.print("몇이라고 생각합니까? <0 to 100>\t");
				int uNum = s.nextInt();	
				
				if(cNum == uNum) {
					System.out.println(uNum+"은 정답입니다. 축하합니다!");
					break;
				}else if (cNum < uNum) {
					System.out.println(uNum+"은 제가 정한 숫자보다 큽니다.");
					System.out.println("["+(i-1)+"]"+"의 기회가 남았습니다.");
				}else if (cNum > uNum) {
					System.out.println(uNum+"은 제가 정한 숫자보다 작습니다.");
					System.out.println("["+(i-1)+"]"+"의 기회가 남았습니다.");
				}
			}
			System.out.println("끝났습니다. 종료하시겠습니까? y or n");
	
			String count = s.nextLine();
			if(count.equals("y")) break;
			else if(count.equals("n")) continue;
		}
	}
}
