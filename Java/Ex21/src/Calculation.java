class Calc2
{
	int add(int a, int b){
		return a+b;
	}
}

class Calculation {

	static void disp() {
		int nRtn;
		Calc2 calc = new Calc2();
		nRtn = calc.add(3, 9);
		System.out.println("3+9="+nRtn);
	}
	
	public static void main(String[] args) {
		disp();
	}
}
