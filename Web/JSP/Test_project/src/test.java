
public class test {

	public static void main(String[] args) {
		
		String message = "ㅇㅇㅇㅇㅋ|!귓속말@/to 연주 123123 dddd";
		
		message = message.substring(message.indexOf("/")+1);
		message = message.substring(message.indexOf(" ")+1);
		String msg = message.substring(message.indexOf(" ")+1);
		String name = message.substring(0, message.indexOf(" "));
		
		System.out.println(message);
		System.out.println(name);
		System.out.println(msg);
		/*String text = message.substring(index);
		String name = text.substring(0, text.indexOf(" "));
			
		index = text.indexOf(" ");
		String msg = text.substring(index+1);
		System.out.println(msg);
		*/
		//sString name = message.substring(index, message.indexOf(" "));
		//System.out.println(name);
		//System.out.println(message);
		//message.in
	}

}
