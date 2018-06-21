
public class CompAssignOp {

	public static void main(String[] args) {
		short num = 10;
		num = (short)(num+77L);   //형 변환하지 않으면 에러, 77L이 Long타입이라..
		int rate = 3;
		rate = (int)(rate*3.5);   //형 변환하지 않으면 에러, 3.5가 실수라서
		System.out.println(num);
		System.out.println(rate);
		
/*		
		위에 변수에 명시적 선언을 하고 나면  num = num + 11L 과 같은 형식은 사용할 수 없음 
		=> 아키텍쳐 문제라고 함..
		num += 11L 로 써야 함..
*/
		num = 10;
		num += 77L;    //형 변환 필요 없음
		rate *= 3;     //형 변환 필요 없음
		rate *= 3.5;
		System.out.println(num);
		System.out.println(rate);
				
	}

}
