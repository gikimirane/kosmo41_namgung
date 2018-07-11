import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkEx1 {

	public static void main(String[] args) {
		
		InetAddress ip = null;
		InetAddress[] ipArr = null;
		
		try {
			ip = InetAddress.getByName("www.naver.com");
			System.out.println("getHostHome() : "+ip.getHostName());
			System.out.println("ip.getHostAddress() : "+ip.getHostAddress());
			System.out.println("ip.toString() : "+ip.toString());
			
			byte[] ipAddr = ip.getAddress();
			System.out.println("ip.getAddress() : "+ip.getAddress());
			
			String result = " ";
			for(int i=0;i<ipAddr.length;i++) {
				result += (ipAddr[i]<0) ? ipAddr[i]+256 : ipAddr[i];
				result += ".";
			}
			System.out.println("getAddress()+256 : "+result);
		}catch(UnknownHostException e) {
			e.printStackTrace();
		}
		try {
			ip = InetAddress.getLocalHost();
			System.out.println("ip.getHostName() : "+ip.getHostName());
			System.out.println("ip.getHostAddress() : "+ip.getHostAddress());
			
		}catch(UnknownHostException e) {
			e.printStackTrace();
		}
		try {
			ipArr = InetAddress.getAllByName("www.naver.com");
			
			for(int i=0;i<ipArr.length;i++) {
				System.out.println("ipArr[ "+i+" ] : "+ipArr[i]);
			}
		}catch(UnknownHostException e) {
			e.printStackTrace();
		} 
	}
}
