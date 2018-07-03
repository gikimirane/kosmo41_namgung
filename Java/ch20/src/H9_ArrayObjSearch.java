import java.util.Arrays;

class Person2 implements Comparable{
	private String name;
	private int age;
	
	public Person2(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public int compareTo(Object o) {
		Person2 p = (Person2)o;
		return this.age - p.age;
	}
	public String toString() {
		return name + ": "+age;
	}
}
public class H9_ArrayObjSearch {

	public static void main(String[] args) {
		Person2[] ar = new Person2[3];
		
		ar[0] = new Person2("Lee",29);
		ar[1] = new Person2("Goo",15);
		ar[2] = new Person2("Soo",37);
		
		Arrays.sort(ar);
		int idx = Arrays.binarySearch(ar, new Person2("Who are you? ",37));
		System.out.println(ar[idx]);

	}

}
