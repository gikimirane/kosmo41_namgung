public class D1_StringBuilder {
	public static void main(String[] args) {
		
		StringBuilder stbuf = new StringBuilder("123");
		stbuf.append(45678); //문자열 덧붙히기 (=123+45678)
		System.out.println(stbuf.toString());
		
		stbuf.delete(0, 2);  //문자열 일부삭제 (0번지부터 2-1번지까지 삭제)
		System.out.println(stbuf.toString());
		
		stbuf.replace(0,3,"AB"); // 문자열 일부 교체(0번지부터 3-1번지를 AB로 바꿔라)
		System.out.println(stbuf.toString());
		
		stbuf.reverse(); //문자열 뒤집기 (거꾸로 찍음)
		System.out.println(stbuf.toString());
		String sub = stbuf.substring(2,4);
		//String 형 sub에 stbuf의 일부 글자 (2~4-1번지만큼 뽑아서) 넣음
		System.out.println(sub);
	}
}
