
public class test {
	public static void main(String args[]) {
		String message = "> 내이름을불러줘~^mymymymymyym";
		int index = message.indexOf("^");
		String trantype = message.substring(index+1);
		System.out.println(trantype);
	}
}
