
public class C2_VarargsBefore {
	public static void showAll(String[] vargs) {
		System.out.println("LEN : "+vargs.length);
		
		for(String s : vargs) {
			System.out.print(s+"\t");
		}System.out.println();
	}
	public static void main(String[] args) {
		showAll(new String[] {"BOX"});
		showAll(new String[] {"BOX","Toy"});
		showAll(new String[] {"BOX","Toy","Apple"});
	}
}
//매개 변수의 가변인자 선언은 자바5에서 추가된 문법
//따라서 그 이전의 코드는 위와같이 작성해야만 했음