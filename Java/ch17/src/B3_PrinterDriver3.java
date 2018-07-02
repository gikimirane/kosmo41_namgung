interface Printable3{
	void print(String doc);
}
interface ColorPrintable3 extends Printable3{
	void printCMYK(String doc);
}

class Prn909Drv3 implements ColorPrintable3 {
	//interface 를 상속받고 +@하여 구현하도록 함
	@Override
	public void print(String doc) {
		System.out.println("From MD-909 Black & White ver");
		System.out.println(doc);
	}
	@Override
	public void printCMYK (String doc) {
		System.out.println("From MD-909 CMYK ver");
		System.out.println(doc);
	}
}
public class B3_PrinterDriver3 {

	public static void main(String[] args) {
		String myDoc = "This is report about...";
		ColorPrintable3 prn = new Prn909Drv3();
		prn.print(myDoc);
		System.out.println();
		prn.printCMYK(myDoc);
	}

}
