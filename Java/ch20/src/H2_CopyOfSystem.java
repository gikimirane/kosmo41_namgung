
public class H2_CopyOfSystem {

	public static void main(String[] args) {
		
		double[] org = {1.1,2.2,3.3,4.4,5.5};
		double[] cpy = new double[3];
		//미리 만들어져 있는 (cpy)에 org배열을 복사하는데, 1번지부터~3개 복사
		System.arraycopy(org,1,cpy,0,3);
		
		for(double d:cpy) {
			System.out.println(d+"\t");
		}System.out.println();
	}
}
