interface PrintableD{
	void print(String s);
}
public class D4_Lambda4 {

	public static void ShowString(PrintableD p, String s) {
		p.print(s);
	}
	public static void main(String[] args) {
		ShowString((s)->{System.out.println(s);},"What is Lambda?");
	}
}
