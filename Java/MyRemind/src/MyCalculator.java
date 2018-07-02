
public class MyCalculator {
	int Add(int a, int b) {
		return a+b;
	}
	int Sub(int a, int b) {
		if(a>b) return a-b;
		else return b-a;
	}
	int Multi(int a,int b) {
		return a*b;
	}
	int Divi(int a, int b) {
		if(a>b) return a/b;
		else return 0;
	}
	int Squ (int a) {
		return a*a;
	}
	int Remain(int a,int b) {
		return a%b;
	}
	
	void Multiple(int a,int b) {
		
		if(a % b==0) {
			System.out.println(a+"는 "+b+"의 배수 입니다.\t");
		}
	}
	void Multiple(int a,int b,int c) {
		
		if(a % b==0 && a % c ==0) {
			System.out.println(a+"는 "+b+"과 "+c+"의 배수 입니다.\t");
		}
	}
	
}
