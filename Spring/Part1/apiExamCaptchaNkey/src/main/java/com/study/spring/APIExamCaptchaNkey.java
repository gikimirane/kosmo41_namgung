package com.study.spring;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


// 네이버 캡차 API 예제 - 키발급
public class APIExamCaptchaNkey {

    public String getKey() {
        String clientId = "ACGsL06tfm0t8ewUjL_U";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "aQVMfC8xhg";//애플리케이션 클라이언트 시크릿값";
        String key = "";
        try {
            String code = "0"; // 키 발급시 0,  캡차 이미지 비교시 1로 세팅
            String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            JSONParser jsonParser = new JSONParser();
            JSONObject obj = new JSONObject();
            obj = (JSONObject) jsonParser.parse(response.toString());
            key = (String) obj.get("key");
                       
        } catch (Exception e) {
            System.out.println(e);
        }
        return key;
    }
    public void getImage() {
    	String clientId = "ACGsL06tfm0t8ewUjL_U";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "aQVMfC8xhg";//애플리케이션 클라이언트 시크릿값";
         try {
             String key = getKey(); // https://openapi.naver.com/v1/captcha/nkey 호출로 받은 키값
             System.out.println("key : "+key);
             String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;
             URL url = new URL(apiURL);
             HttpURLConnection con = (HttpURLConnection)url.openConnection();
             con.setRequestMethod("GET");
             con.setRequestProperty("X-Naver-Client-Id", clientId);
             con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
             int responseCode = con.getResponseCode();
             BufferedReader br;
             if(responseCode==200) { // 정상 호출
                 
            	 InputStream is = con.getInputStream();
                 int read = 0;
                 byte[] bytes = new byte[1024];
                 // 랜덤한 이름으로  파일 생성
                 String tempname = Long.valueOf(new Date().getTime()).toString();
                 System.out.println("파일명 : "+tempname);
                 File f = new File("tempname" + ".jpg");
                 f.createNewFile();
                 OutputStream outputStream = new FileOutputStream(f);
                 while ((read =is.read(bytes)) != -1) {
                     outputStream.write(bytes, 0, read);
                 }
                 is.close();
                 
             } else {  // 에러 발생
            	 
                 br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                 String inputLine;
                 StringBuffer response = new StringBuffer();
                 while ((inputLine = br.readLine()) != null) {
                     response.append(inputLine);
                 }
                 br.close();
                
                 System.out.println(response.toString());
             }
         } catch (Exception e) {
             System.out.println(e);
         }
         
    }
    public String checkNumber(String cKey,String userInput) {
    	String clientId = "ACGsL06tfm0t8ewUjL_U";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "aQVMfC8xhg";//애플리케이션 클라이언트 시크릿값";
        String result="";
       
        try {
            String code = "1"; // 키 발급시 0,  캡차 이미지 비교시 1로 세팅
            String key = cKey; // 캡차 키 발급시 받은 키값
            String value = userInput; // 사용자가 입력한 캡차 이미지 글자값
            String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code +"&key="+ key + "&value="+ value;

            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            result = response.toString();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}