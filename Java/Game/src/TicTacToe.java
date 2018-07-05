import java.util.Scanner;
public class TicTacToe {
	public static void print(String ar[][]) {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				System.out.print(" "+ar[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static String[][] xCheck(String ar[][],String xNum) {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(ar[i][j].equals(xNum)) {
					ar[i][j]="X";
				}
				if(ar[i][j].equals("O")|ar[i][j].equals("X")) {
					System.out.println("재입력 하세요.");
				}
			}
		}
		return ar;
	}
	public static String[][] oCheck(String ar[][],String oNum){
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(ar[i][j].equals(oNum)) {
					ar[i][j]="O";
				}
			}
		}
		return ar;
	}
//현재의 누수 : 이상한 값 입력 시에도 count가 되는거, 입력된 자리에 또 글자 입력해도 count가 되는거! 예외달아주면 됨
	public static void main(String[] args) {
		String ar[][] = {{"1","2","3"},{"4","5","6"},{"7","8","9"}};
		print(ar);
		Scanner s = new Scanner(System.in);
		int count=0; boolean sw = true;
		while(sw | count<=9) {
			if(count%2==1) {
				System.out.println("Player 1, Please enter the number of the square where you want to place your X : ");
				String xNum = s.next();
				xCheck(ar,xNum);
				int num1=0;
				int num2=0;
				for(int i=0;i<3;i++) {
					if(ar[i][0].equals(xNum) && ar[i][1].equals(xNum) && ar[i][2].equals(xNum)){
						System.out.println(xNum+"님 "+"Bingo!!");
						sw=false;
					}
					else if(ar[0][i].equals(xNum) && ar[1][i].equals(xNum) && ar[2][i].equals(xNum)){
						System.out.println(xNum+"님 "+"Bingo!!");
						sw=false;
					}
					if(ar[i][i].equals(xNum)) num1++;
					if(ar[0][2].equals(xNum) && ar[1][1].equals(xNum) && ar[2][0].equals(xNum)) num2++;
				}
				if(num1==3 | num2 ==3) {
					System.out.println(xNum+"님 "+"Bingo!!");
					sw=false;
				}
				print(ar);
			}
			else if(count%2==0)	{
				
				System.out.println("Player 2, Please enter the number of the square where you want to place your O : ");
				String oNum = s.next();
				oCheck(ar,oNum);
				
				int num1=0;
				int num2=0;
				for(int i=0;i<3;i++) {
					if(ar[i][0].equals(oNum) && ar[i][1].equals(oNum) && ar[i][2].equals(oNum)){
						System.out.println(oNum+"님 "+"Bingo!!");
						sw=false;
					}
					else if(ar[0][i].equals(oNum) && ar[1][i].equals(oNum) && ar[2][i].equals(oNum)){
						System.out.println(oNum+"님 "+"Bingo!!");
						sw=false;
					}
					if(ar[i][i].equals(oNum)) num1++;
					if(ar[0][2].equals(oNum) && ar[1][1].equals(oNum) && ar[2][0].equals(oNum)) num2++;
				}
				if(num1==3 | num2 ==3) {
					System.out.println(oNum+"님 "+"Bingo!!");
					sw=false;
				}
				print(ar);
			}			
			count++;
			if(count==9) {
				System.out.println("비겼습니다!");
				break;
			}	
		}
	}
}
