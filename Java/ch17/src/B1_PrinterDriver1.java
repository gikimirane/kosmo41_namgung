interface Printable{
	public void print(String doc);
}

class SPrinterDriver implements Printable{
	@Override
	public void print(String doc) {
		System.out.println("From Samsung printer");
		System.out.println(doc);
	}
}

class LPrinterDriver implements Printable{
	@Override
	public void print(String doc) {
		System.out.println("From LG printer");
		System.out.println(doc);
	}
}

public class B1_PrinterDriver1 {

	public static void main(String[] args) {
		
		String myDoc = "This is a report about...";
		//부모(interface)의 메소드를 SprinterDriver꺼로 쓰겠어
		Printable prn = new SPrinterDriver();
		prn.print(myDoc);
		
		prn = new LPrinterDriver();  
		//기존 prn을 참조하는 인스턴스를 L로 바꿔치기 함
		prn.print(myDoc);

	}

}
