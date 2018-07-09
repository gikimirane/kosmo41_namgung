interface Calculate2{
	int cal(int a, int b);
}
public class A4_TwoParamAndReturn {
	public static void main(String[] args) {
		Calculate2 c;
		
		c = (a,b) -> {return a+b; };  //return 문의 중괄호는 생략이 불가함
//		int num = c.cal(4, 3); 이와 같이 연산결과가 남는다
		System.out.println(c.cal(4, 3));
		
		c = (a,b) -> a+b; //연산 결과가 남으면 별도로 명시하지 않아도 반환 대상이 됨!
		System.out.println(c.cal(4,3));
	}
}

//메서드 몸체에 해당하는 내용이 return 문이면 그 문장이 하나이더라도 
//중괄호의 생략이 불가능하다!