import java.util.Random;
import java.util.Scanner;

public class Baseball {
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		Random r = new Random();
		int n1 =r.nextInt(9)+1;
		int n2 = r.nextInt(9)+1;
		int n3 = r.nextInt(9)+1;
		int nUser = 0;
		int u1=0,u2=0,u3=0;
	
		System.out.println("숫자로 하는 야구게임 시작!");
		
		while(true) {
			if(n1 != n2 && n2 != n3 && n1!=n3) {
				break;
			}else {
				n1 =r.nextInt(9)+1;
				n2 = r.nextInt(9)+1;
				n3 = r.nextInt(9)+1;
			}
		}
		//System.out.println(n1+":"+n2+":"+n3);
		int count=1;	
		while(true) {
			
			System.out.println("세자리 숫자를 입력하세요."+count+"회");
			nUser = s.nextInt();
			u1 = nUser/100;
			u2 = (nUser-u1*100)/10;
			u3 = nUser-(u1*100)-(u2*10);	
			System.out.println(u1+" : "+u2+" : "+u3);
			int sCount=0; int bCount=0;
			if(n1==u1)	sCount=sCount+1;
			if(n2==u2)	sCount=sCount+1;
			if(n3==u3)	sCount=sCount+1;
			
			if(n1==u2 || n1==u3)	bCount=bCount+1;
			if(n2==u1 || n2==u3)	bCount=bCount+1;
			if(n3==u1 || n3==u2)	bCount=bCount+1;
			
			if(sCount==3) {
				System.out.println("YOU WIN");
				System.out.println(sCount+"Strike\t"+bCount+"ball");
				System.out.println(n1+""+n2+""+n3);
				break;
			}else {
				System.out.println(sCount+" Strike\t"+bCount+" ball");
				count++;
			}
		}			
	}		
}



