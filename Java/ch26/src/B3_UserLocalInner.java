interface Printable2{
	void print();
}
class Papers2 {
	private String con;
	//생성자 영역
	public Papers2(String s) {
		con = s;
	}
	//메소드인데 리턴타입이 Printable임 
	public Printable2 getPrinter() {
		class Printer2 implements Printable2 {
			public void print() {
				System.out.println(con);
			}
		}
		return new Printer2();
	}
}
public class B3_UserLocalInner {

	public static void main(String[] args) {
		Papers2 p = new Papers2("서류내용 : 행복합니다.");
		Printable2 prn = p.getPrinter();
		prn.print();
	}
}
