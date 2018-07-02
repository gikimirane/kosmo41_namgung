public class C3_NullPointer {

	public static void main(String[] args) {
		String str = null;
		System.out.println(str);
		if(str != null) {
			int len = str.length();
			 //if-else문 안쓰면 str에 비어있는데 길이를 구하라고 해서 에러 발생
		}else System.out.println("비어 있어! str부터 채우렴");
	}
}
