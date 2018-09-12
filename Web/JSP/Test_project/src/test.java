

public class test {

	public static void main(String[] args) {
		
		String message = "!쪽지받음@ㅋㅋㅋ,ㅎ2ㅎ2";
		String reciver = message.substring(message.indexOf("@")+1,message.indexOf(","));
		String sendmessage = message.substring(message.indexOf(",")+1,message.length());
		System.out.println(reciver);
		System.out.println(sendmessage);
		
	}

}
