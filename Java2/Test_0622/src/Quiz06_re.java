/*
���� 05-06
����ڷκ��� ���� �ϳ��� �Է¹��� �� �� ���� �ش��ϴ� �������� �������� ����Ͻÿ�.	
*/
import java.util.Scanner;	
public class Quiz06_re {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		//System.out.println("���ϴ� �� �Է�");
		//int nDan = s.nextInt();
		
		//while �� ������ ¥����
		
		int j=9, count=0;
		int i=s.nextInt();
		
		while(j>0) {
			System.out.println(i+"*"+j+"="+i*j);
			j--;
		}
			
		/*
		for(int j=1;j<10;j++) {
			System.out.println(nDan+"*"+j+"="+nDan*j);
		}
		*/

	}

}
