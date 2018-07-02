import java.util.StringTokenizer;

public class Add {

	public static void main(String[] args) {
		StringTokenizer v = new StringTokenizer("a:b:c",":");
		StringTokenizer n = new StringTokenizer("1 2 3");
		
		String s = v.nextToken(); //다음 글자(a)를 넣음
		int num = Integer.parseInt(n.nextToken()); 
		
		while(v.hasMoreTokens()) {
			String str = v.nextToken(); //a는 위에서 불렀고 그 다음꺼를 불러옴
			System.out.println(str);
			s = s+" + "+str;
			num = num+Integer.parseInt(n.nextToken());
			//문자열을 int형으로 반환(Integer.parseInt), n.nextToken()꺼를 변환하겠다는 의미
		}
		System.out.println(s+" = "+num);
	}
}
