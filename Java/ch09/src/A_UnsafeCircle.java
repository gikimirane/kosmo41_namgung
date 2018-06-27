class Circle{
	double rad = 0;
	final double PI =3.14;
	
	public Circle (double r) {  //생성자
		setRad(r);
	}
	public void setRad(double r) {  //리턴타입있으므로 걍 메소드 void (없다)
		if(r<0) {
			rad=0;
			return;
		}
		rad = r;
	}
	
	public double getArea() {    //리턴타입이 있음, double
		return (rad *rad)*PI;
	}
}

public class A_UnsafeCircle {
	public static void main(String[] args) {
		Circle c = new Circle(1.5);
		System.out.println(c.getArea());
		
		c.setRad(2.5);
		System.out.println(c.getArea());
		c.setRad(-3.3);
		System.out.println(c.getArea());
		c.rad = -4.5;  //private 를 Circle 내 Rad를 선언할 때 쓰면 오류가 남
		//컴파일 오류는 발생되지 않으나 직접적으로 메소드 내 변수를 바꾸는거라 위험함
		System.out.println(c.getArea());
	}

}
