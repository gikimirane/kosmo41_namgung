interface Calculate { 
	void cal (int a, int b);
}
public class A3_TwoParamNoReturn {

	public static void main(String[] args) {
		Calculate c;
		
		//연산결과가 안남는다! 찍고 끝!
		c = (a,b) -> System.out.println(a+b);
		c.cal(4, 3);
		
		c = (a,b) -> System.out.println(a-b);
//			응용 가능! {}를 하면 1줄 이상으로 기재 가능함!
//			if(a>b) {
//				System.out.println(a-b);
//			}else System.out.println(b-a);
//		};
		c.cal(4, 3);
		
		c = (a,b) -> System.out.println(a*b);
		c.cal(4, 3);
	}

}
