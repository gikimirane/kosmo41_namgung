
public class B1_BoxingUnboxing {

	public static void main(String[] args) {
		//Boxing, 인스턴스의 값을 감싸는 박싱
		Integer iObj = new Integer(10);
		Double dObj = new Double(3.14);
		//Integer, Double 클래스의 객체를 만듬, 
		
		System.out.println(iObj);
		System.out.println(dObj);
		System.out.println();
		
		//unBoxing , 메서드 호출을 통한 언박싱
		//intValue, DoubleValue를 통해서 접근, num1,2에 각각의 값을 메소드 호출을 통해 넣을 수 있음
		int num1 = iObj.intValue();
		double num2 = dObj.doubleValue();
				
		System.out.println(iObj);
		System.out.println(dObj);
		System.out.println();
		
		//래퍼 인스턴스의 값 증가 방법, 직접적인 대입이 불가하여 메소드 호출한 값에 연산을 한 후 다시 감싸야 함
		iObj = new Integer(iObj.intValue()+10);
		dObj = new Double(dObj.doubleValue()+1.2);
		System.out.println(iObj);
		System.out.println(dObj);
	}

}
