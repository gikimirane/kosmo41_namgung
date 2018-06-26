class calc
{
	int x1=0;
	int x2=0;
	
	calc (int n1,int n2)
	{
		x1 = x1 + n1;
		x2 = x2 + n2;
	}
	calc ()
	{		
	}
		
	int Add (int n1, int n2) {
		int nResult = (n1) + (n2);
		//System.out.println(nResult);        //n1,n2,nResult,상수까지 총 4개였다가 nResult에 들어가면서 3개가 됨
		return nResult;
	}
}
public class Exam01 {

	public static void main(String[] args) {
		int num1 = 5;
		int num2 = 7;
		
		calc myCalc = new calc(num1,num2);
		int num3 = myCalc.Add(num1, num2);
		
		int num4 = num3 * 10 - 20;
		System.out.println(num4);
		
		
		System.out.println(num1+"+"+num2+"="+(num1+num2));
		System.out.println(num1+"-"+num2+"="+(num1-num2));
		System.out.println(num1+"*"+num2+"="+(num1*num2));
		System.out.println(num1+"/"+num2+"="+(num1/num2));
		System.out.println(num1+"%"+num2+"="+(num1%num2));
		
	}

}
