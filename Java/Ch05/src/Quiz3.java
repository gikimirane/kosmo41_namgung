/*
문제 05-03 
1 이상 100미만의 정수중에서 7의배수와 9의배수를 출력하는 프로그램을 작성하라.
단, 7의 배수이면서 동시에 9의 배수인 정수는 한번만 출력해야 한다.

 */
public class Quiz3 {

	public static void main(String[] args) {
		
		for (int i=1;i<100;i++) {
			if ((i % 7 == 0) || (i % 9 == 0)) {
				System.out.println("7 또는 9의 배수들 : "+i);
			}
		}
	}
}

/* 7 또는 9의 배수인 애들을 print 하고, 7과 9의 배수를 1회만 찍는 for, if 문의 조합

for(int j=1;j<100;j++) {
	if ((j % 7 == 0) && (j % 9 == 0)) {
		System.out.println("-----------------------");
		System.out.println("7과 9 동시 배수 : "+j);
		System.out.println("-----------------------");
		break;
	}
}

for(int i=1;i<100;i++) {
	if((i % 7 == 0) || (i % 9 == 0)) {
		
		System.out.println("7 또는 9의 배수 : "+i);
		System.out.println("-----------------------");				
	}
}
*/