class wdata
{
	int month;
	int day;
	String sky;
}
public class Weather {

	public static void main(String[] args) {
		wdata today = new wdata();
		today.month = 10;
		today.day = 5;
		today.sky = "맑음";
		
		System.out.println(today.month+"월 "+today.day+"일 "+today.sky);
	}
}

//데이터와 함수를 정의한 설계도라고 생각하자.
//얘는 여러번 불려다니면서 다른 이름 같은 동작으로 사용이 가능하다.