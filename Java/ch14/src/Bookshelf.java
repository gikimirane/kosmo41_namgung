class Book {   
	//부모 or 슈퍼 클래스
	String title;
	String genre;
	
	void printBook() {
		System.out.println("제  목 : "+title);
		System.out.println("장  르 : "+genre);
	}
}

//클래스 1개로 돌려막기 하고 싶으면 상속받으면 됨
//main 정보들을 담고 extends를 통해 상속받으면 메소드나 변수를
//내맘대로 건들 수 있음

class Novel extends Book {   
	//부모인 Book을 자식인 Novel에게 줌(novel은 Book의 변수, 메소드 다 쓸 수 있음)
	String writer;
	
	void printNov() {
		printBook();
		System.out.println("저  자 : "+writer);
	}
}

class Magazine extends Book {
	//부모인 Book이 자식인 Magazine에게 줌(마찬가지로 변수, 메소드 다 쓸 수 있음)
	int day;
	void printMag () {
		printBook();
		System.out.println("발매일 : "+day+"일");
	}
}
public class Bookshelf {

	public static void main(String[] args) {
		
		Novel nov = new Novel();
		nov.title ="구운몽";    //Book꺼이지만 상속받아서 nov에서 쓸 수 있음 
		nov.genre ="고전문학";  //Book꺼이지만 상속받아서 nov에서 쓸 수 있음 
		nov.writer ="김만중";   //원래 Novel꺼
		
		Magazine mag = new Magazine();
		mag.title="월간 자바 그림책";   //Book꺼이지만 상속받아서 mag에서 쓸 수 있음 
		mag.genre="컴퓨터";             //Book꺼이지만 상속받아서 mag에서 쓸 수 있음 
		mag.day=20;                     //원래 Magazine꺼
		
		nov.printNov();
		System.out.println();
		mag.printMag();
	}
}
//오버로딩 vs 오버라이딩
/*
오버로딩은 println과 같이 메소드는 똑같은데 인자가 다른걸 여러개 만들어 둔 것을 오버로딩이라고 함

오버라이딩은 상속받은 부모클래스에 있는 메소드가 맘에 안들어서 똑같은 이름의 메소드를 수정해서 사용하는 것
동일한 이름의 메소드명을 써야하고 super.메소드명(); 을 쓰지 않고 그냥 구현을 하면 메소드를 아예 바꿔버리는 것이고
super 라는 단어를 사용해서 쓰면 부모꺼 + 자식이 고친거 같이 쓸 수 있게 된다.
 */

