class MyBook1{
	int price;
	String title;
	
	MyBook1(String t, int p){
		title = t;
		price = p;
	}
	MyBook1(MyBook1 copy){
		title = copy.title;
		price = copy.price;
	}
	void print() {
		System.out.println("제    목 : "+title);
		System.out.println("가    격 : "+price);
	}
}

class Books1 {

	public static void main(String[] args) {
		MyBook1 book1 = new MyBook1("게임스쿨",10000);
		book1.print();
		
		MyBook1 book2 = new MyBook1(book1);
		book2.title="모바일게임";
		book2.print();
	}

}
