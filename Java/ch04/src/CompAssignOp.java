
public class CompAssignOp {

	public static void main(String[] args) {
		short num = 10;
		num = (short)(num+77L);   //�� ��ȯ���� ������ ����, 77L�� LongŸ���̶�..
		int rate = 3;
		rate = (int)(rate*3.5);   //�� ��ȯ���� ������ ����, 3.5�� �Ǽ���
		System.out.println(num);
		System.out.println(rate);
		
/*		
		���� ������ ����� ������ �ϰ� ����  num = num + 11L �� ���� ������ ����� �� ���� 
		=> ��Ű���� ������� ��..
		num += 11L �� ��� ��..
*/
		num = 10;
		num += 77L;    //�� ��ȯ �ʿ� ����
		rate *= 3;     //�� ��ȯ �ʿ� ����
		rate *= 3.5;
		System.out.println(num);
		System.out.println(rate);
				
	}

}
