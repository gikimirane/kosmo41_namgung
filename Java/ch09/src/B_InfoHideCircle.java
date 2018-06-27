class Circle1{
	private double rad = 0;
	final double PI =3.14;
	
	public Circle1 (double r) {  //생성자
		setRad(r);
	}
	public void setRad(double r) {   //Setter
		//리턴타입있으므로 걍 메소드 void (없다)
		if(r<0) {
			rad=0;
			return;
		}
		rad = r;
	}
	public double getRad() {         //Getter
		return rad;
	}
	public double getArea() {        //리턴타입이 있음, double
		return (rad *rad)*PI;
	}
}

public class B_InfoHideCircle {

	public static void main(String[] args) {
		Circle1 c = new Circle1(1.5);
		System.out.println(c.getArea());
		
		c.setRad(2.5);
		System.out.println(c.getArea());
		c.setRad(-3.3);
		System.out.println(c.getArea());
		//c.rad = -4.5;  
		//private 를 Circle 내 Rad를 선언할 때 쓰면 오류가 남
		//c.rad는 이제 private로 묶여있어서 외부에서는 건들 수 없음
		System.out.println(c.getArea());
		System.out.println(c.getRad());
	}

}
