package com.study.pattern01.singleton;

public class MySingletonClass {
//	딱 하나만 만들어지는 애
//	자기 타입으로 만든 sc
	private static MySingletonClass sc = null;
//	public static MySingletonClass sc = new MySingletonClass();
//	공통된 i가 된다 (데이터 교환을 위해 사용되는 것)
	private int i = 0;
//	생성자 영역	
	private MySingletonClass() {

	}
//	만약에 객체주소가 비어있으면 생성하고 없으면 sc를 Return해줘 
//	(어차피 static이라 같은애가 나오겠고 또 만들수도 없고 private라 다른데서 접근이 불가하기 때문에 생성 메소드를 통해 우회함)
//	public이니까 얘만 접근이 가능함 나머지는 private라서 직접적으로 생성이 불가 함
	public static MySingletonClass getInstance() {
		if(sc == null) {
			sc = new MySingletonClass();
		}
		return sc;
	}
	
//	은닉화(getter,setter), private 형인 int i에 접근하기 위해서 getter와 setter를 통하여 접근한다
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}

}
