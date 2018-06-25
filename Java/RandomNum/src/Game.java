import java.util.Random;
public class Game {

	public static void main(String[] args) {
		
		while(true) {
			Random ran1 = new Random();
			int num1 = ran1.nextInt(9)+1; 
			int num2 = ran1.nextInt(9)+1;
			int num3 = ran1.nextInt(9)+1;
			
			if (num1 != num2 && num2 != num3 && num1 != num3) {
				System.out.print(num1);
				System.out.print(num2);
				System.out.print(num3);
				break;
			}else continue;
		}
	}
}
