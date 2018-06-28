
public class C2_SubString {

	public static void main(String[] args) {
		String st1= "일이삼사오육칠";
		// 0부터~ 시작한다고 생각하자
		// 범위만큼만 가져오겠다고 생각하여 , 뒤에 숫자는 미만(-1)까지!
		String st2 = st1.substring(2);
		System.out.println(st2);
		
		String st3 = st1.substring(2,4);
		System.out.println(st3);	
	}
}