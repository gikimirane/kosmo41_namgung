class MyBook{
	int price;
	int num=0;
	String title;
	
	MyBook(){  //Return 형이 없고 클래스 명과 같으면 생성자이다!
		title = "모바일게임교육";
		price = 5000;
	}
	MyBook(String t, int p){   //동일한 이름의 메소드(생성자)이나 형태가 다른것은 오버로딩
		title = t;
		price = p;
	}
	
	void print() {
		System.out.println("제    목 : "+title);
		System.out.println("가    격 : "+price);
		System.out.println("주문수량 : "+num);
		System.out.println("합계금액 : "+price*num);
	}
}

class Books {

	public static void main(String[] args) {
		MyBook book = new MyBook("게임스쿨",10000);
		MyBook book1 = new MyBook();
		book.num = 10;      //<-멤버변수에 값 대입
		book.print();       //<-메소드 호출
		System.out.println("------------오버로딩된 생성자 확인해보기-----------");
		book1.print();
	}
}
