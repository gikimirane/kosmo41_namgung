public class A1_CurrentThreadName {
	public static void main(String[] args) {
		Thread ct = Thread.currentThread();
		String name = ct.getName();
		System.out.println(name);
	}
}
//집에서 세탁기 돌린다고 냉장고 안꺼지듯이 한 프로그램 내에서 각개전투하는 최소단위?
//별개의 동작을 하는 애들!