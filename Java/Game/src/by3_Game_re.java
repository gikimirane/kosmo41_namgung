import java.util.Random;
import java.util.Scanner;

public class by3_Game_re {

	public static int checkMatch(String[][] ar) {
		String ar1[][] = {{"1","2","3"},{"4","5","6"},{"7","8","x"}};
		int count=0;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(ar1[i][j].equals(ar[i][j])) {
					count++;
				}
			}
		}
		return count;
	}
	public static String[][] suffle() {
		String ar[][] = {{"1","2","3"},{"4","5","6"},{"7","x","8"}};
		Random r = new Random();
		
		int count=0;int x=0,y=0;
		while(count<10) {
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					if(ar[i][j].equals("x")) {
						x=i;
						y=j;
					}
				}
			}
			int num =r.nextInt(4);
			if(num==0) moveR(ar,x,y);
			else if(num==1) moveL(ar,x,y);
			else if(num==2) moveU(ar,x,y);
			else if(num==3) moveD(ar,x,y);
			count++;
		}
		return ar;				
	}
	public static String[][] print(String[][] ar) {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				System.out.print(ar[i][j]+"\t");
			}
			System.out.println();
		}
		return ar;
	}
	public static String[][] moveR(String[][] ar,int x, int y) {
		if(y!=0) {
			ar[x][y] = ar[x][y-1];
			ar[x][y-1] = "x";
			return ar;
		}
		return ar;
	}
	public static String[][] moveL(String[][] ar,int x, int y) {
		if(y>=0 && y<2) {
			ar[x][y] = ar[x][y+1];
			ar[x][y+1] = "x";
			return ar;
		}
		return ar;
	}
	public static String[][] moveU(String[][] ar,int x, int y) {
		if(x>=0 && x<2) {
			ar[x][y] = ar[x+1][y];
			ar[x+1][y] = "x";
			return ar;
		}
		return ar;
	}
	public static String[][] moveD(String[][] ar,int x, int y) {
		if(x>0 && x<=2) {
			ar[x][y] = ar[x-1][y];
			ar[x-1][y] = "x";
			return ar;
		}
		return ar;
	}
	public static void main(String[] args) {
		
		String ar[][];
		Scanner s = new Scanner(System.in);
		int count=0;
		ar = suffle();
		print(ar);
		System.out.println(checkMatch(ar));
		
		while(count!=9) {
			int x=0;int y=0;
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					if(ar[i][j].equals("x")) {
						x=i;
						y=j;
					}
				}
			}
			System.out.println(x+","+y);
			System.out.println("입력해 위 : 8, 아래 : 2, 오른쪽 : 6, 왼쪽 : 4, 종료 : 0");
			int uNum = s.nextInt();
			if(uNum == 6) {
				moveR(ar,x,y);
				print(ar);
				count=checkMatch(ar);
			}else if(uNum == 4) {
				moveL(ar,x,y);
				print(ar);
				count=checkMatch(ar);
			}else if(uNum == 8) {
				moveU(ar,x,y);
				print(ar);
				count=checkMatch(ar);
			}else if(uNum == 2) {
				moveD(ar,x,y);
				print(ar);
				count=checkMatch(ar);
			}else if(uNum ==0) {
				s.close();
				break;
			}
			else continue;
		}	
		System.out.println("정답입니다!");
		s.close();
	}
}
