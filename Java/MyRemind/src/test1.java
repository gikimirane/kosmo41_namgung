
public class test1 {

	public static void main(String[] args) {
		
		String str = "/setblack 바보";
		String temp = str.substring(str.indexOf(" "));
		String friendName = temp.substring(1,temp.length());
		String txt = temp.substring(temp.indexOf(" ")+1);
		
		System.out.println(temp.length()-1);
		System.out.println(friendName);
		
	}

}
