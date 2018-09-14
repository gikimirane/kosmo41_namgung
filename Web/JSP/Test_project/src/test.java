

public class test {

	public static void main(String[] args) {
		
		String message = "ㅇㅋㅇ|!게임중@1O";
		//int gamename=message.length();
		int length = message.length();
		String userinput = message.substring(length-2,length-1);
		String gameuser = message.substring(length-1,length);
		System.out.println(userinput);
		System.out.println(gameuser);
		
	}

}
