
public class test1 {

	public static void main(String[] args) {
		String str1 = "/joinroom 1";
		String name1 = "유니";
		String temp = str1.substring(str1.indexOf(" "));
		String friendName = temp.substring(1,temp.length());
		System.out.println(temp);
		System.out.println(friendName);
		
	}

}
