/*
���� 05-10
����ڷκ��� 2���� ������ �Է¹޴´�. ���� 2�� 6�� �Է¹޾Ҵٸ�
2+3+4+5+6�� ���ϴ� ���α׷��� �ۼ��϶�.
�� 6�� 2�� �Է¹޴´ٸ� 6+5+4+3+2�� ���ؾ� �Ѵ�.
 */
import java.util.Scanner;
public class Quiz10_re {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("2���� ���� �Է��ض�");
		int num1 = s.nextInt();
		int num2 = s.nextInt();
		int sum = 0;
		
		if(num1 < num2) 
		{
			for(int i=num1;i<=num2;i++) 
			{
				sum= sum+i;
			}
		}
		else 
		{
			for(int i=num1;i>=num2;i--) {
				sum= sum+i;
			}
		}
		System.out.println(sum);

	}

}
