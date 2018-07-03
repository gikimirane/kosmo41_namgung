public class A_UseWrapperClass {
	
	public static void showData(Object obj) {
		System.out.println(obj);
	}
	
	public static void main(String[] args) {
		Integer iInst = new Integer(3); //박싱 boxing
		showData(iInst);
		showData(new Double(7.15));
	}
}

//showData(인스턴스 내놔)인데 그냥 3은 int형이라서 보내면 안되니까 Integer형으로 바꾼다음에
//showData에 넣어서 보내는거임, wrapper class 안할꺼면 showdata를 각 형에 맞게 오버로드 해줘야 함
