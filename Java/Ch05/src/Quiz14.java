/*
문제 05-14
 */
public class Quiz14 {

	public static void main(String[] args) {
		
		for(int a=0;a<10;a++) {
			for(int z=9;z>=0;z--) {
				if(((a*10)+z)+((z*10)+a)==99 && a!=z) 
				{
						System.out.println("---------");
						System.out.println(a+","+z);
						System.out.println(z+","+a);
				}
				
			}
		}
	}
}
