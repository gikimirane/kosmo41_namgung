/*
Mind Reader
게임설명 :
내가 생각한 숫자를 컴퓨터가 맞춘다.
내가 생각한 숫자보다 크면 h 라고 입력한다.
내가 생각한 숫자보다 작으면 l 이라고 입력한다.
내가 생각한  숫자와 같으면 y 라고 입력한다.
 */
import java.util.Scanner;
public class MindReader {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		String uAnswer;
		int min=0; int max=100;
		
		System.out.println("pick a number between 1 and 100");
		System.out.println("if the number is higher than the guess press h");
		System.out.println("if it is less than the guess press l");
		System.out.println("if my guess is correct y");
		while(true) {
			System.out.println("is it "+((min+max)/2)+"?");
			uAnswer = s.nextLine();   //h,l,y
			
			if(uAnswer.equals("y")) {
				System.out.println("You are not good enough to beat me, human");
				break;
			}else if (uAnswer.equals("h")) {
				min = (min+max)/2;			
			}else if(uAnswer.equals("l")) {
				max = (min+max)/2;		
			}
		}
	}
}