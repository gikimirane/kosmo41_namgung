class SimpleBox {
	private int data;
	
	SimpleBox(int data){
		this.data = data; //this.data는 class내 선언된거, 그냥 data는 메소드호출 시 input 된거
	}
	
	void setData(int data) {
		this.data = data;
	}
	//private로 선언된 data 변수라 직접 변수안에 뭐가 들었는지 볼 수 없고
	//설정할 수 없어서 메소드(getter,setter)를 만들고 그걸로 접근 함
	int getData() {
		return this.data;
	}
}
public class A2_ThisInst {

	public static void main(String[] args) {
		
		SimpleBox box = new SimpleBox(99);
		System.out.println(box.getData());  
		
		box.setData(77);
		System.out.println(box.getData());
	}

}
