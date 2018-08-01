import java.util.Scanner;

public class test {
	
	
	public static void main(String[] args) {
		String str = "/joinroom 123 111";
		String temp = str.substring(str.indexOf(" "));
		String pwd = temp.substring(temp.indexOf(" ")+1);
		
		String temp1 = str.substring(str.indexOf(" ")+1);
		String friendName = temp1.substring(0,temp1.indexOf(" "));
		String txt = temp1.substring(temp1.indexOf(" ")+1);
		
		System.out.println(temp);
		System.out.println(pwd);
		
		System.out.println(temp1);
		System.out.println(txt);
		
	}
}
