import java.util.StringTokenizer;

public class test {
	public static void main(String args[]) {
		String message = "따듯한카페라떼|1|아이스아메리카노|3";
		StringTokenizer strtoken = new StringTokenizer(message,"|");
		
		String result="";
		int i=0;
		while(strtoken.hasMoreTokens()) {
			if(i%2==0) {
				result=result+strtoken.nextToken();
			}else {
				result =result+strtoken.nextToken()+"잔 ";
			}
			i++;
		}
		
		System.out.println(result);
	}
}
