class Board{}
class PBoard extends Board{}

public class C2_ClassCast {
	public static void main(String[] args) {
		
		Board pbd1 = new PBoard();
		PBoard pbd2 =(PBoard)pbd1;    //(PBoard)는 부모에서 자식으로 형변환
		
		System.out.println("..intermediate location..");
		Board ebd1 = new Board();  
		PBoard ebd2 = (PBoard)ebd1;  //원래 부모꺼였는데 강제로 자식으로 형변환 (안돼!)
	}
}

//Board가 부모, PBoard는 자식 관계 상태
//ebd1은 부모로 만든 인스턴스 / 부모의 인스턴스를 자식(PBoard)으로 만든 인스턴스에 넣으려고 하니까 
//에러가 발생 됨 (ClassCastException)
//부모가 만든 자식을 참조하는 인스턴스는 자식에게 대입이 가능 함