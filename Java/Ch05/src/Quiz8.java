/*
���� 05-08
����ڷκ��� 5���� ������ �Է¹޾Ƽ� ���� ���Ͽ� ����Ѵ�.
���� �Է¹޴� ���ڰ� 1�̸��� ���ڶ�� ���Է��� �䱸�ؾ� �Ѵ�. 
�׷��� 1�̻��� ���� 5���� �Է¹޾ƾ� ���α׷��� �ϼ��ȴ�.
 */
import java.util.Scanner;
public class Quiz8 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int sum=0,num;
		for (int i=0;i<5;i++) {
			System.out.println("1�̸� ���� ������ �� 5�� �Է�");
			num = s.nextInt();
			if(num <0) {
				i=i-1;
				System.out.println("1�̸�! �ٽ� �Է�");
			}
			sum = sum+num;
		}
		System.out.println(" �Է��� ���� ���� : "+sum);
	}
}


/*
int nNum;
int nSum = 0;
for(int i=0;i<5;i++) {
	System.out.println("���ڸ� �� 5�� �Է��ϼ���.");
	nNum = s.nextInt();
	if(nNum > 0) {
		nSum = nSum + nNum;
	}
	else if (nNum <= 0){
		System.out.println("�ٽ�");
		i=i-1;
	}
}
System.out.println("5�� ������ ���� : "+nSum);
 */

