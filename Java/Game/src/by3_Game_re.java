import java.util.Scanner;

class Find{
	String arr[][];
	static int x;
	static int y;
	
	Find(String ar[][]){
		arr = ar;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(arr[i][j].equals("x")) {
					x=i;
					y=j;
				}
			}
		}
	}
	
}

public class by3_Game_re {

	public static void clearScreen() {
		
	}
	public static void suffle() {
		String ar[][] = new String[3][3]; 
	}
	public static void print(String[][] ar) {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				System.out.print(ar[i][j]+"\t");
			}
			System.out.println();
		}
	}
	public static String[][] moveR(String[][] ar,int x, int y) {
		ar[x][y] = ar[x][y-1];
		ar[x][y-1] = "x";
		return ar;
	}
	public static String[][] moveL(String[][] ar,int x, int y) {
		ar[x][y] = ar[x][y+1];
		ar[x][y+1] = "x";
		return ar;
	}
	
	public static void main(String[] args) {
		String ar[][] = {{"1","2","3"},{"4","5","6"},{"7","x","8"}};
		Scanner s = new Scanner(System.in);
		Find find = new Find(ar);
		
		print(ar);
		System.out.println(find.x+","+find.y);
		
		System.out.println("입력해 위 : 8, 아래 : 2, 오른쪽 : 6, 왼쪽 : 4");
		int uNum = s.nextInt();
		if(uNum == 6) {
			
			moveR(ar,find.x,find.y);
			System.out.println(find.x+","+find.y);
			print(ar);
		}
	}
}
