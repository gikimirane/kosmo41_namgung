
public class B3_AutoBoxingUnboxing2 {

	public static void main(String[] args) {
		Integer num=10;
		num++; //new Integer(num.intValue()+1); <-오토 박싱/언박싱 동시진행
		System.out.println(num);
		
		num+=3; //new Integer(num.intValue()+3);
		System.out.println(num);
		
		int r= num+5;         //num에 대한 오토 언박싱 진행
		Integer rObj = num-5; //num에 대한 오토 언박싱 진행 후 wrapping 
		System.out.println(r);
		System.out.println(rObj);
	}
}
