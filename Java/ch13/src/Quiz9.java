public class Quiz9 {

	public static void main(String[] args) {
		int[] ar = {210,19,72,129,34};
		int n1=0;
		while(true) {
			int j=1;
			for(int i=1;i<ar.length;i++) {
				if(ar[i-1]>ar[i]) {
					n1 = ar[i];
					ar[i] = ar[i-1];
					ar[i-1] = n1;
					
				}else if(ar[i-1]<=ar[i]){
					ar[i]=ar[i];
					ar[i-1]=ar[i-1];
					j++;
				}
			}
			if(j==ar.length) {
				break;
			}	
		}
		for(int i=0;i<ar.length;i++) {
			System.out.println(ar[i]);
		}
	}
}
