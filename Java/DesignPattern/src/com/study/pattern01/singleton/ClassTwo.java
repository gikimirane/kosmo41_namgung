package com.study.pattern01.singleton;

public class ClassTwo {
	public ClassTwo(){
//		MysingletonClass를 사용하려면 getInstance를 통해 우회해서 접근해야 함
		MySingletonClass single = MySingletonClass.getInstance(); 
		System.out.println("ClassTwo");
		System.out.println("i = "+single.getI());
	}
}
