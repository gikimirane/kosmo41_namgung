/*
문제2
char형 1차원 배열을 선언과 동시에 'Good time' 문장으로 초기화하고, 저장된 내용을 출력하라.
*/
public class Quiz2 {
	public static void main(String[] args) {
		char[] ch = new char[] {'G','o','o','d',' ','t','i','m','e'};
		
		for(int i=0;i<ch.length;i++) {
			System.out.print(ch[i]);
		}
	}

}


