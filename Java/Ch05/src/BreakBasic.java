
public class BreakBasic {

	public static void main(String[] args) {
		
		int num = 1;
		boolean search = false;
		
		
		while (num < 100) {
			if ((num % 5 == 0) && (num % 7 == 0)) {
				search = true;       //true�� Break �� ���� ���°� ������!
				break;               //while ���� ��������, �ݺ����� ������ ����
			}
			num++;
		}
		
		if (search)                  //Boolean ���̶� if �� �� ���ǵ� ���� ���ص� ��
		{
			System.out.println("ã�� ���� : "+num);
		}
		else System.out.println("5�� 7�� ����� ��ã�Ҿ��");
		
		
	}
}
/*
���� �� 5�� ��� 7�� ����� ��� print ��

while (num < 100) {
	if ((num % 5 ==0) && (num % 7 == 0)) {
		System.out.println(num);
	}
	num++;
}
*/