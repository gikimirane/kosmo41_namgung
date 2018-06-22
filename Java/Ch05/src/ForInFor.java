
public class ForInFor {

	public static void main(String[] args) {
		
		//구구단 출력
		/*
		for (int i = 2; i < 10 ; i++) {
			for (int j = 1 ; j < 10; j++) {
				System.out.println(i+" * "+j+" = "+(i*j));
			}
			System.out.println();
		}
		*/
		
		//구구단 while 문으로..
		
		int i = 2;
		int j = 1;
		
		while (i < 10) {
			while (j < 10) {
			
				System.out.println(i +" * "+j+" = "+(i*j));
				j++;
			}
			System.out.println();
			i++;
			j=1;           // j가 while 문을 나오고 나면 변수가 9가 되서 1로 바꿔야 함
		}
		
		
		
		/*
		for (int i = 0; i < 3 ; i++) {
			
			System.out.println("------------------------");
			
			for (int j = 0; j < 3; j++) {
				System.out.print(" ["+i+", "+j+"] ");
			}
			System.out.print('\n'); //println 을 써도 됨, n 자체가 개행임
		}
		*/
	}

}
