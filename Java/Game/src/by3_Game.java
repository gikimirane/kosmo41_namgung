import java.util.Scanner;

class Move {
	String[][] MoveU(String arr[][]) {	
		String[][] temp = new String[3][3];
		int n=1;
		for(int i=2;i>0;i--) {
			for(int j=0;j<3;j++) {
				if(i==0 && arr[i][j].equals("x")) {
					System.out.println("뭐 출력하게?"+(i-n)+","+j+","+arr[i-n][j]);
					temp[i+n][j] = arr[i+n][j]; //i,j에 x값을 넣음
					arr[i+n][j] = arr[i][j];
					arr[i][j] = temp[i+n][j];	
				}
				else if(arr[i-n][j].equals("x")) {
					temp[i-n][j] = arr[i-n][j]; //i,j에 x값을 넣음
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
				if(i!=0 && arr[i-n][j].equals("x")) {
					System.out.println("뭐 출력하게?"+(i-n)+","+j+","+arr[i-n][j]);
					temp[i-n][j] = arr[i-n][j]; //i,j에 x값을 넣음
					arr[i-n][j] = arr[i][j];
					arr[i][j] = temp[i-n][j];	
				}
				else if(i>2 && arr[i][j].equals("x")) {
					temp[i+n][j] = arr[i+n][j]; //i,j에 x값을 넣음
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
					temp[i][j-n] = arr[i][j-n]; //i,j에 x값을 넣음
					arr[i][j-n] = arr[i][j];
					arr[i][j] = temp[i][j-n];
					break;
				}
				else if(j<2 && arr[i][j+n].equals("x")) {     //내 앞이 비어 있으면
					temp[i][j+n] = arr[i][j+n]; //i,j에 x값을 넣음
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
	
	int Match(String arr[][],String ch[][]) {
		int count=0;
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++) {
				if(arr[i][j].equals(ch[i][j])) {
					count++;
				}
			}
		}
		return count;
	}
}

public class by3_Game {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String Check[][] = {{"1","2","3"},{"4","5","6"},{"7","8","x"}};
		String Board[][] = {{"x","2","3"},{"1","5","6"},{"7","8","4"}};
		Move move = new Move();
		Check check = new Check();
		
		check.Print(Board);
		
		while(check.Match(Board, Check)!=9) {
			System.out.println("[ Move ] a:Left  s:Right  w:Up  z:Down");
			System.out.println("[ Exit ] k:Exit");
			String user = s.next();
			while(true) {
				if(user.equals("k")) {
					System.out.println("종료합니다.");
					break;
				}
				if(check.Match(Board, Check)==9) {
					System.out.println("정답입니다.");
					break;
				}
				else if(user.equals("a")) {
					move.MoveL(Board);
					check.Print(Board);
					check.Match(Board, Check);
					break;
				}else if(user.equals("s")) {
					move.MoveR(Board);
					check.Print(Board);
					check.Match(Board, Check);
					break;
				}else if(user.equals("w")) {
					move.MoveU(Board);
					check.Print(Board);
					check.Match(Board, Check);
					break;
				}else if(user.equals("z")) {
					move.MoveD(Board);
					check.Print(Board);
					check.Match(Board, Check);
					break;
				}
			}
		}
		
		

	}
}
