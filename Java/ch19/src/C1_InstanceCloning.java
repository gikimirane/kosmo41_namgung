class Point implements Cloneable{
	private int xPos;
	private int yPos;
	
	public Point(int x, int y) {
		xPos = x;
		yPos = y;
	}
	public void showPosition() {
		System.out.printf("[%d, %d]", xPos, yPos);
		System.out.println();
	}
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
		//Object 클래스 내 Clone 메소드를 호출함 (기본적으로 상속관계라서 super를 씀)
	}
}
public class C1_InstanceCloning {

	public static void main(String[] args) {
		Point org = new Point(3,5);
		Point cpy;
		try {
			cpy = (Point)org.clone();  //new를 한거랑 동일한 효과를 나타냄
			org.showPosition(); 
			cpy.showPosition();
			//Clone을 통해 new를 한거랑 동일하여 다른 주소를 가지고 있음
			if(org.equals(cpy)) System.out.println("같아");
			else System.out.println("달라"); //주소가 달라서 else문을 타게 됨
		}
		catch (CloneNotSupportedException e){
			e.printStackTrace();
		}
	}
}
