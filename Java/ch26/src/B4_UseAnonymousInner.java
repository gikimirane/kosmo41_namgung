interface Printable3
{
	void print();
}
class Papers3 
{
	private String con;
	public Papers3(String s) 
	{
		con = s;
	}
	public Printable3 getPrinter() 
	{
		return new Printable3() 
		{
			public void print() 
			{
				System.out.println(con);
			}
		};
		//원래 대로면 인터페이스는 불완전한 애라서 new가 안되는데
		//쓸 때 바로 완전한 애로 바꿔줘서 사용한 것임
	}
}
public class B4_UseAnonymousInner 
{
	public static void main(String[] args) 
	{
		Papers3 p = new Papers3("서류 내용 : 행복합니다.");
		Printable3 prn = p.getPrinter();
		prn.print();
	}
}
