class Person {
	String name;
	public Person(String name) {
		this.name = name;
	}
	@Override
	protected void finalize() throws Throwable{
		super.finalize();
		System.out.println("destroyed : "+name);
	}
}
public class A_ObjectFinalize {

	public static void main(String[] args) {
		
		Person p1 = new Person("Yoon");
		Person p2 = new Person("Park");
		p1 = null;  //참조대상을 가비지 컬렉션 대상으로 만듬, 참조와 인스턴스 관계를 끊음
		p2 = null;
		
		System.gc();    //기본 시스템 메소드래!!
		System.runFinalization();
		
		System.out.println("end of program");
	}
}
