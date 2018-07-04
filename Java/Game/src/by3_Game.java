import java.util.Random;
import java.util.Scanner;

class Move {
	
	String[][] MoveU(String arr[][]) {	
		String[][] temp = new String[3][3];
		int n=1;
		for(int i=2;i>0;i--) {
			for(int j=0;j<3;j++) {
				if(i==0 && arr[i][j].equals("x")) {
					temp[i+n][j] = arr[i+n][j]; 
					arr[i+n][j] = arr[i][j];
					arr[i][j] = temp[i+n][j];	
				}
				else if(arr[i-n][j].equals("x")) {
					temp[i-n][j] = arr[i-n][j]; 
					arr[i-n][j] = arr[i][j];
					arr[i][j] = temp[i-n][j];
				}	
			}
		}
		return arr;
	}
	String[][] MoveD(String arr[][]) {
		
		String[][] temp = new String[3][3];
		int n=1;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(i==2) n=0;
				if(arr[i+n][j].equals("x")) {
					temp[i+n][j] = arr[i+n][j]; 
					arr[i+n][j] = arr[i][j];
					arr[i][j] = temp[i+n][j];	
				}
			}
		}
		return arr;
	}
	String[][] MoveR(String arr[][]) {
		String[][] temp = new String[3][3];
		int n;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				n=1;
				if(j==2 && arr[i][j].equals("x")) {
					temp[i][j-n] = arr[i][j-n]; 
					arr[i][j-n] = arr[i][j];
					arr[i][j] = temp[i][j-n];
					break;
				}
				else if(j<2 && arr[i][j+n].equals("x")) {     
					temp[i][j+n] = arr[i][j+n]; 
					arr[i][j+n] = arr[i][j];
					arr[i][j] = temp[i][j+n];
					break;
				}
			}
		}
		return arr;
	}
	String[][] MoveL(String arr[][]) {          
		String[][] temp = new String[3][3];
		int n=1;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(j==0 && arr[i][j].equals("x")) {
					temp[i][j+n] = arr[i][j+n];
					arr[i][j+n] = arr[i][j];
					arr[i][j] = temp[i][j+n];
					break;
				}else if(j>0 && arr[i][j-n].equals("x")) {
					temp[i][j] = arr[i][j];
					arr[i][j] = arr[i][j-n];
					arr[i][j-n] = temp[i][j];
					break;
				}
			}
		}
		return arr;
	}
}

class Check {
	
	void Print(String arr[][]) {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				System.out.print(arr[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	int Match(String arr[][]) {
		String ch[][] = {{"1","2","3"},{"4","5","6"},{"7","8","x"}}; 
		int count=0;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(arr[i][j].equals(ch[i][j])) {
					count++;
				}
			}
		}
		return count;
	}
}

class Suffle {
	
	String[][] Suf() {
		Random r = new Random();
		Move move = new Move();
		String[][] ar = {{"1","2","3"},{"4","5","6"},{"7","8","x"}};
		int num=0;
		int count =0;
		
		while(count<100) {
			num = r.nextInt(4);
			if(num ==0) {
				move.MoveD(ar);
			}else if(num==1) {
				move.MoveL(ar);
			}else if(num==2) {
				move.MoveR(ar);
			}else if(num==3) {
				move.MoveU(ar);
			}
			count++;			
		}
		return ar;
	}
}


public class by3_Game {
	
	public static void main(String[] args) {
				
		Scanner s = new Scanner(System.in);
		Move move = new Move();
		Check check = new Check();
		Suffle suf = new Suffle();
				
		String Board[][];		
		Board=suf.Suf();  //Suffle 후 Board에 대입

		while(check.Match(Board)!=9) {
			
			check.Print(Board);
			System.out.println("[ Move ] a:Left  s:Right  w:Up  z:Down");
			System.out.println("[ Exit ] k:Exit");
			String user = s.nextLine();
						
			while(true) {
				
				if(user.equals("k")) {
					System.out.println("종료합니다.");
					break;
				}
				else if(user.equals("a")) {
					move.MoveL(Board);
					if(check.Match(Board)==9) {
						System.out.println("정답입니다!");
						break;
					}
					break;
				}else if(user.equals("s")) {
					move.MoveR(Board);
					if(check.Match(Board)==9) {
						System.out.println("정답입니다!");
						break;
					}
					break;
				}else if(user.equals("w")) {
					move.MoveU(Board);
					if(check.Match(Board)==9) {
						System.out.println("정답입니다!");
						break;
					}
					break;
				}else if(user.equals("z")) {
					move.MoveD(Board);
					if(check.Match(Board)==9) {
						System.out.println("정답입니다!");
						s.close();
						break;
					}
					break;
				}else break;   //다른거 누르면 다시 키 누르도록 유도
			}
		}
		check.Print(Board);
	}
}
