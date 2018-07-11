package com.study.pattern02.strategy1;

public class BObj {
	
	AInterface ainter;
	
	public BObj() {
		ainter = new AImplements();
		//자식을 부모한테 대입하는 형태
	}
	
	public void SomeFunc() {
//		다음 기능이 필요합니다. 
//		A님 만들어주세요 (델리게이트했다(Delegate, 기능구성을 위임했다)
//		System.out.println("AAA");
//		System.out.println("AAA");
//		System.out.println("AAA");
		
//		Delegate한 기능을 사용하는 것..
		ainter.funcA();
		ainter.funcA();
		ainter.funcA();
	}
}
