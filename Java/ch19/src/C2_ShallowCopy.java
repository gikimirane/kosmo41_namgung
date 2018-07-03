class Point2 implements Cloneable{
	public int xPos;
	public int yPos;
	
	public Point2 (int x, int y) {
		xPos = x;
		yPos = y;
	}
	public void showPosition() {
		System.out.printf("[%d , %d]",xPos,yPos);
		System.out.println();
	}
	public void changePos (int x, int y) {
		xPos = x;
		yPos = y;
	}
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

class Rectangle2 implements Cloneable {
	public Point2 upperLeft;
	public Point2 lowerRight;
	
	public Rectangle2(int x1,int y1, int x2, int y2) {
		upperLeft = new Point2(x1, y1);
		lowerRight = new Point2(x2, y2);
	}
	
	public void changePos(int x1, int y1, int x2, int y2) {
		upperLeft.changePos(x1, y1);
		lowerRight.changePos(x2, y2);
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	public void showPosition() {
		System.out.println("좌측 상단 : ");
		upperLeft.showPosition();
		System.out.println("우측 상단 : ");
		lowerRight.showPosition();
		System.out.println();
	}
}

public class C2_ShallowCopy {
	public static void main(String[] args) {
		Rectangle2 org = new Rectangle2(1,1,9,9);
		Rectangle2 cpy;
		
		try {
			cpy = (Rectangle2)org.clone();
			org.changePos(2, 2, 7, 7);
			
			org.showPosition();
			cpy.showPosition();
			//clone으로 인해 new를 한번 더 한것임, 동일한 인스턴스를 가리키고 있다
			if(org.equals(cpy)) System.out.println("참조하는 주소값이 같다");
			else System.out.println("참조하는 주소값이 다르다");
			//내용은 같다 왜냐면 인스턴스 변수가 같아서
			if(org.lowerRight.equals(cpy.lowerRight)) System.out.println("내용은 같다(내부 복사X)");
			else System.out.println("내용은 다르다(내부 복사O)");
		}catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}
