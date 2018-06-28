

/*
문제8
성적관리 프로그램을 작성한다. 국어, 영어, 수학, 국사 4과목이고 학생은 이순신, 강감찬, 을지문덕, 권율 4명이다. 
4 by 4 배열을  선언하고 사용자로부터 4사람의 4과목 점수를 입력받아 다음의 형태로 값을 저장하고 총점을 구하는 프로그램을 작성하라.
출력예)
*/
//int ar1[][] = { {1,2,3,4},{5,6,7,8} };
import java.util.Scanner;
public class Quiz8 {

	public static void main(String[] args) {
		
		int [][] lJum = new int [4][4];
		Scanner s = new Scanner(System.in);
		String[] ar_name = {"이순신","강감찬","을지문덕","권율"};
		String[] ar_sub= {"국어","영어","수학","국사"};
						
		for(int j=0;j<lJum.length;j++) {
			int count =0;
			
			for(int i=0;i<lJum.length;i++) {
				System.out.println(ar_name[j]+"의 "+ar_sub[count]+"점수를 입력하세요.");
				count++;
				lJum[i][j]=s.nextInt();
			}
		}
		//첫번째 줄 이름 찍기
		System.out.print("구분\t");
		for(int i=0;i<ar_name.length;i++) {
			System.out.print(ar_name[i]+"\t");
		}
		System.out.println("총점");
		//첫번째 줄 이름 및 구분,총점 찍기 완료
		
		//과목과 점수 찍기
		for(int i=0;i<lJum.length;i++) {
			
			int sum=0;
			System.out.print(ar_sub[i]+"\t");  //줄 바뀔 시 다시 과목 찍어줌
			
			for(int j=0;j<lJum.length;j++) {
				System.out.print(lJum[i][j]+"\t");
				sum=sum+lJum[i][j];
			}
			System.out.println(sum);     //sum을 찍고 j값이 증가하면서 int sum=0을 만나서 초기화 됨
		}
	}
}
