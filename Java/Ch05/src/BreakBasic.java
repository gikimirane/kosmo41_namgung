
public class BreakBasic {

	public static void main(String[] args) {
		
		int num = 1;
		boolean search = false;
		
		
		while (num < 100) {
			if ((num % 5 == 0) && (num % 7 == 0)) {
				search = true;       //true와 Break 문 같이 쓰는거 좋은듯!
				break;               //while 문을 빠져나감, 반복문을 나가는 것임
			}
			num++;
		}
		
		if (search)                  //Boolean 형이라 if 문 내 조건도 따로 안해도 됨
		{
			System.out.println("찾는 정수 : "+num);
		}
		else System.out.println("5와 7의 공배수 못찾았어요");
		
		
	}
}
/*
범위 내 5의 배수 7의 배수를 모두 print 함

while (num < 100) {
	if ((num % 5 ==0) && (num % 7 == 0)) {
		System.out.println(num);
	}
	num++;
}
*/