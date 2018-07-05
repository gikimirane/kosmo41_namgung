import java.util.Comparator;
import java.util.TreeSet;

class Person2 implements Comparable<Person2>{
	String name;
	int age;
	public Person2(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public String toString() {
		return name + " : " +age;
	}
	@Override
	public int compareTo(Person2 p) {
		return this.age-p.age;
		//나이가 동일한 데이터는 추가되지 않는다
	}
}
//나 혼자만 다른순서로 출력하고 싶을때 써먹기
class PersonComparator implements Comparator<Person2> {
	public int compare(Person2 p1, Person2 p2) {
		return p2.age - p1.age;
	}
}
public class C3_ComparatorPerson {

	public static void main(String[] args) {
		TreeSet<Person2> tree1 = new TreeSet<>();
		TreeSet<Person2> tree = new TreeSet<>(new PersonComparator());
		
		tree1.add(new Person2("YOON",37));
		tree1.add(new Person2("HONG",53));
		tree1.add(new Person2("PARK",22));
		
		for(Person2 p : tree1) System.out.println(p);
		System.out.println("----------------------------------");
		tree.add(new Person2("YOON",37));
		tree.add(new Person2("HONG",53));
		tree.add(new Person2("PARK",22));
		
		for(Person2 p : tree) System.out.println(p);
	}
}
