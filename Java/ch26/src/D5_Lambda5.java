interface PrintableE{
	void print(String s , int n);
}
public class D5_Lambda5 {

	public static void main(String[] args) {
		PrintableE prn = (s,n) -> {System.out.println(s+" : "+n);};
		prn.print("What is Lambda?", 5);
	}
}
