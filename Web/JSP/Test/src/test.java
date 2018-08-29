
public class test {
	public static void main(String args[]) {
		String message = "남궁윤희|내이름을불러줘~";
		System.out.println(message.indexOf("|"));
		String name = message.substring(0, message.indexOf("|"));
		System.out.println(name);
	}
}
