
public class SwitchBasic {

	public static void main(String[] args) {
		int n = 3;
		
		switch(n) {
		case 1:
			System.out.println("simple java");
			//break;
		case 2:
			System.out.println("Funny java");
			//break;
		case 3:
			System.out.println("fantastic java");
			//break;
		default:
			System.out.println("The best programming language");
		}
		
		System.out.println("Do you like java?");

	}

}


/* 각 케이스 하단에 Break가 없을 시 3번 케이스와 Default, 마지막 do you ~ 영역까지 
보여주게 된다. */