import java.util.Iterator;
import java.util.TreeSet;

public class C1_SortedTreeSet {
	public static void main(String[] args) {
		TreeSet<Integer> tree = new TreeSet<Integer>();
		tree.add(3); tree.add(1);
		tree.add(2); tree.add(4);
		
		System.out.println("인스턴스 수 : "+tree.size());
		
		//for-each문에 의한 출력
		for(Integer n : tree) System.out.print(n.toString()+"\t");
		System.out.println();
		
		//반복자에 의한 출력
		for(Iterator<Integer> itr = tree.iterator(); itr.hasNext(); ) {
			System.out.print(itr.next().toString()+"\t");
		}System.out.println();
	}
}
//tree구조는 오름차순정렬해준다!
//중복된 데이터 안넣으려고 hash, set, tree 를 쓴거다
//중복된거 넣으려면 linked랑 array 쓰면 된다