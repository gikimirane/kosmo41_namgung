import java.util.TreeSet;
class Person implements Comparable<Person>{
	private String name;
	private int age;
	
	public Person (String name, int age) {
		this.name = name;
		this.age = age;
	}
	public String toString() {
		return name +" : "+age;
	}
	public int compareTo(Person p) {
		//return p.age - this.age;   // <- 나이 내림차순
		return this.age - p.age;     //<- 이건 나이오름차순
	}
}
public class C2_ComparablePerson {

	public static void main(String[] args) {
		
		TreeSet<Person> tree = new TreeSet<>();
		tree.add(new Person("YOON",37));
		tree.add(new Person("HONG",53));
		tree.add(new Person("PARK",22));
		
		for(Person p : tree) System.out.println(p);
	}
}
