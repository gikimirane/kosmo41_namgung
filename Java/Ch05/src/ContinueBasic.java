public class ContinueBasic {

	public static void main(String[] args) {
		
		int num = 0;
		int count = 0;
		
		while (num < 100) {
			num++;
			if ((num % 5 != 0) || (num % 7 != 0)) 
			{
				continue;
			}
			count++;
			System.out.println("5 또는 7의 배수 : "+num);
		}
		System.out.println("몇개냐면 : "+count);
	}
}

/*
		while ((num++) < 175) {
			if ((num % 5 != 0) || (num % 7 != 0)) 
			{
				continue;
			}
			count++;
			System.out.println(num);
		}

		System.out.println("count : "+count);
*/