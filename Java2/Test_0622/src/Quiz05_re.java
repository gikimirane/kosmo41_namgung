/*
���� 05-05
�������� ����ϵ� ¦���ܸ� ����ϵ��� ���α׷��� �ۼ��϶�.
��, 2���� 2*2������, 4���� 4*4������....8���� 8*8������ ����ؾ� �Ѵ�.
( ��, for������ 2�� �����ϴ� �� �ȵ� !!!!)
*/
public class Quiz05_re {

	public static void main(String[] args) {
		
		for(int i=2;i<10;i++) {
			for(int j=1;j<=i;j++) {
				System.out.println(i+"*"+j+"="+(i*j));
			}
			System.out.println();
		}
		
		
		/*for������ 2�� ������Ų�ٸ�?
		for(int i=2;i<10;i=i+2) {
			for(int j=1;j<=i;j++) {
				System.out.println(i+"*"+j+"="+(i*j));
			}
			System.out.println();
		}
		*/
		
	}

}
