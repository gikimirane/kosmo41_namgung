/*
���� 05-10
����ڷκ��� 2���� ������ �Է¹޴´�. 
���� 2�� 6�� �Է¹޾Ҵٸ� 2+3+4+5+6�� ���ϴ� ���α׷��� �ۼ��϶�.
�� 6�� 2�� �Է¹޴´ٸ� 6+5+4+3+2�� ���ؾ� �Ѵ�.
 */
import java.util.Scanner;
public class Quiz10 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("ù ��° �� �Է�");
		int nNum1 = s.nextInt();
		System.out.println("�� ��° �� �Է�");		
		int nNum2 = s.nextInt();
		int nSum = 0;
		
			if(nNum1 > nNum2) {
				for (int i=nNum1;i>=nNum2;) {
				nSum = nSum + i;
				i--;
				}
			}
			else 
			{
				for (int i=nNum1;i<=nNum2;) {
					nSum = nSum + i;
					i++;
			}
		}
		System.out.println("���� : "+nSum);	
	}
}
