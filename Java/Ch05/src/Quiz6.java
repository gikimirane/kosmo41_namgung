/*
���� 05-06
����ڷκ��� ���� �ϳ��� �Է¹��� �� �� ���� �ش��ϴ� �������� �������� ����Ͻÿ�.	
*/
import java.util.Scanner;
public class Quiz6 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("���ϴ� ���� �Է��ϼ���.");
		int nDan = s.nextInt();
		System.out.println("�Է��� ���� "+nDan+"�Դϴ�. �������� ����մϴ�.");
		for (int i=9;i>1;i=i-1) {
			
			System.out.println(nDan+" * "+i+" = "+(nDan*i));
		}

	}

}
