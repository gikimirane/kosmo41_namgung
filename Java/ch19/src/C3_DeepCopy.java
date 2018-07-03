class Point3 implements Cloneable{
	public int xPos;
	public int yPos;
	
	public Point3 (int x, int y) {
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

class Rectangle3 implements Cloneable {
	public Point3 upperLeft;
	public Point3 lowerRight;
	
	public Rectangle3(int x1,int y1, int x2, int y2) {
		upperLeft = new Point3(x1, y1);
		lowerRight = new Point3(x2, y2);
	}
	
	public void changePos(int x1, int y1, int x2, int y2) {
		upperLeft.changePos(x1, y1);
		lowerRight.changePos(x2, y2);
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		//Object 클래스의 clone 메소드 호출 결과를 얻음
		Rectangle3 copy = (Rectangle3)super.clone();
		//깊은 복사 형태로 복사본을 수정, 직접적으로 개별 카피 시켜줬음, new 처럼..
		copy.upperLeft = (Point3)upperLeft.clone();
		copy.lowerRight = (Point3)lowerRight.clone();
		
		//완성된 복사본의 참조를 반환함
		return copy;
	}
	public void showPosition() {
		System.out.println("좌측 상단 : ");
		upperLeft.showPosition();
		System.out.println("우측 상단 : ");
		lowerRight.showPosition();
		System.out.println();
	}
}

public class C3_DeepCopy {
	public static void main(String[] args) {
		Rectangle3 org = new Rectangle3(1,1,9,9);
		Rectangle3 cpy;
		
		try {
			cpy = (Rectangle3)org.clone();
			org.changePos(2, 2, 7, 7); //clone영역에서 인스턴스 자체를 copy했기 때문에 이제는 각개전투! 여기서 change를 해도 다른 애한테 영향을 미치지않음
			org.showPosition();
			cpy.showPosition();
			//clone으로 인해 new를 한번 더 한것임, 동일한 인스턴스를 가리키고 있다
			if(org.equals(cpy)) System.out.println("참조하는 주소값이 같다");
			else System.out.println("참조하는 주소값이 다르다");
			
			//내용도 다르다. 왜냐면 인스턴스 변수가 달라서 (Clone에서 각각 카피를 했음)
			if(org.lowerRight.equals(cpy.lowerRight)) System.out.println("내용은 같다(내부 복사X)");
			else System.out.println("내용은 다르다(내부 복사O)");
		}catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}
