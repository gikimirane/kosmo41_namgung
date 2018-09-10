
public class test {
	public static void main(String args[]) {
		String message = "남궁윤희|내이름을불러줘~";
		String msg = message.substring(message.indexOf("|"),message.length());
		System.out.println(msg);
	}
}
