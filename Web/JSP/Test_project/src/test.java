import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
public class test {

	
	
    public static void main(String[] args) {
    	
    		String output="";
    		String clientId = "j9k9RMAsEz0H3VZ0F7Lg";// 애플리케이션 클라이언트 아이디값";
    		String clientSecret = "DWl2xJYSm2";// 애플리케이션 클라이언트 시크릿값";
    		try {
    			String text = "안녕하세요";
    			String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
    			URL url = new URL(apiURL);
    			HttpURLConnection con = (HttpURLConnection) url.openConnection();
    			con.setRequestMethod("POST");
    			con.setRequestProperty("X-Naver-Client-Id", clientId);
    			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
    			// post request
    			String postParams = "source=ko&target=en&text="+text;
    			con.setDoOutput(true);
    			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    			wr.writeBytes(postParams);
    			wr.flush();
    			wr.close();
    			int responseCode = con.getResponseCode();
    			BufferedReader br;
    			if (responseCode == 200) { // 정상 호출
    				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
    			} else { // 에러 발생
    				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
    			}
    			String inputLine;
    			StringBuffer response = new StringBuffer();
    			while ((inputLine = br.readLine()) != null) {
    				response.append(inputLine);
    			}
    			br.close();
    			
    			String value=response.toString();
    			
    			System.out.println(response.toString());
    			
    			
    		} catch (Exception e) {
    			System.out.println(e);
    		}
    		
    		
    	
    }
}