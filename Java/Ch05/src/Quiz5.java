/* 
���� 05-05
�������� ����ϵ� ¦���ܸ� ����ϵ��� ���α׷��� �ۼ��϶�.
��, 2���� 2*2������, 4���� 4*4������....8���� 8*8������ ����ؾ� �Ѵ�.
 */
public class Quiz5 {
	public static void main(String[] args) {
		
		for(int i=2;i<10;i++) {
			for(int j=1;j<=i;j++) {
				if((i % 2==0)) {
					System.out.println(i+" * "+j+" = "+(i*j));
				}
			}
			System.out.println();
		}
	}
}
/* for ������ �ƿ� ¦���� �������� ����
		for (int i=2;i<10;i=i+2) {
			for (int j=1;j<=i;j++) {
				System.out.println(i+" * "+j+" = "+(i*j));
			}
			System.out.println();
		}
*/