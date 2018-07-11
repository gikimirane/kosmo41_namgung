package com.study.pattern01.singleton;

public class ClassOne {
	//getInstance를 통해 1번만 생성이 됨(null일때만 sc에 만드니까)
	public ClassOne(){
		MySingletonClass single = MySingletonClass.getInstance(); 
		int num = single.getI(); //최초 들어있는애를 불러줌, 최초로 초기화된 애를 불러줌
		
		System.out.println("ClassOne");
		System.out.println("i = "+num);
		
		single.setI(200);
		System.out.println("i = "+single.getI());
	}
}
