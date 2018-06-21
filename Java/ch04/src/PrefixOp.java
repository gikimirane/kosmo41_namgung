
public class PrefixOp {

	public static void main(String[] args) {
		int num1 = 7;
		System.out.println(++num1);  //print 전 1을 더해
		System.out.println(++num1);
		System.out.println(num1);
		
		System.out.println("--------------------------");

		int num2 = 7;
		System.out.println(num2++);   //print 후 1을 더해
		System.out.println(num2++);
		System.out.println(num2);
	}
}
