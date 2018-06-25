
public class ForInFor {

	public static void main(String[] args) {
		
		//For 문
		/*
		for (int i = 2; i < 10 ; i++) {
			for (int j = 1 ; j < 10; j++) {
				System.out.println(i+" * "+j+" = "+(i*j));
			}
			System.out.println();
		}
		*/
		

		
		int i = 2;
		int j = 1;
		
		while (i < 10) {
			while (j < 10) {
			
				System.out.println(i +" * "+j+" = "+(i*j));
				j++;
			}
			System.out.println();
			i++;
			j=1;           
		}
		
		
		
		/*
		for (int i = 0; i < 3 ; i++) {
			
			System.out.println("------------------------");
			
			for (int j = 0; j < 3; j++) {
				System.out.print(" ["+i+", "+j+"] ");
			}
			System.out.print('\n'); 
		}
		*/
	}

}
