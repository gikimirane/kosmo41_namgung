public class test {
	public static void main(String[] args) {
		
		String msg = "msg:p1244|조윤희|ff";
		String[] arr = msg.split("\\|");
		for(int i=0;i<arr.length;i++) {
			System.out.println(arr[i]);
		}
	}
}
