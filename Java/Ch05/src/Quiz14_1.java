

//세자리 숫자를 더해서 999
// a * 100 + b *10 + c
import java.util.Random;
class MyRandom{
	Random rand = new Random();
	
	void randPrint1() {
		int num1 = 0;
		int num2 = 0;
		int num3 = 0;
		
		while (true) 
		{
			num1 = rand.nextInt(9)+1;
			num2 = rand.nextInt(9)+1;
			num3 = rand.nextInt(9)+1;
			
			if ((num1 !=0 && (num1 != num2) && (num2 != num3) && (num1 != num3))) 
			{
				break;
			}
		}
		System.out.println((num1*100)+(num2*10)+num3);
		//아예 1개씩 찍는 방법
		System.out.println(num1+""+num2+""+num3);
	}
	void randPrint2() {

		while(true) {
			int nRan = rand.nextInt(900)+101; //0~899 + 100  -----> 100~999 까지 수가 나옴
			int num1 = nRan / 100;  //8이 나옴
			int num2 = (nRan - num1*100)/10; //7이 나옴
			int num3 = nRan - num1*100 - num2*10; //6이 나옴
			if (num1 !=0 && (num1 != num2) && (num2 != num3) && (num1 != num3))
			{
					System.out.println(num1+""+num2+""+num3);				
					break;
			}
		}
	}
}
public class Quiz14_1 {

	public static void main(String[] args) {
		MyRandom rand = new MyRandom();
		rand.randPrint1();
		rand.randPrint2();
		
	}
}
