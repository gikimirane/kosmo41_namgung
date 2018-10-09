package com.study.android.project_exam;

import android.content.ContentValues;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RequestHttpURLConnection {
    private static final String TAG = "lecture";

    String key;
    String value;
    boolean isAnd = false;
    StringBuffer sbParams = new StringBuffer();

    public JSONObject jsonReturn(String surl, HashMap values){
        JSONObject result = null;
        try {
            URL url = new URL(surl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            Log.d(TAG,"sUrl:"+surl);

            if(conn != null){
                Set<String> set = values.keySet();
                Iterator<String> it = set.iterator();
                while(it.hasNext())
                {
                    key = it.next();
                    value = (String) values.get(key);

                    if(isAnd)
                    {
                        sbParams.append("&");
                    }

                    sbParams.append(key).append("=").append(value);

                    if (!isAnd)
                        if (set.size() >= 2)
                            isAnd = true;
                }

                Log.d(TAG,"파라미터 : "+sbParams);

                String strParams = sbParams.toString();
               /* strParams = URLEncoder.encode(strParams, "UTF-8")
                                  .replace("+", "%20")
                                  .replace("*", "%2A")
                                  .replace("%7E", "~")
                                  .replace("%3D", "=");*/



                conn.setConnectTimeout(10000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                Log.d(TAG, "strParams : " + strParams);
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                pw.write(strParams);
                pw.flush();
                pw.close();

               /* conn.setConnectTimeout(10000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;charset=UTF-8");
                //conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "text/html"); //서버에 response 데이터를 html로 받음
                conn.setRequestProperty("Accept-Charset", "UTF-8"); // Accept-Charset 설정.
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                pw.write(strParams);
                pw.flush();
                pw.close();*/


                int resCode = conn.getResponseCode();
                if(resCode != HttpURLConnection.HTTP_OK){
                    Log.d(TAG, "RequestHttpURLConnection : " + conn.getResponseCode());
                    return null;
                }

                StringBuilder sb = new StringBuilder();
                int status = conn.getResponseCode();
                if (status == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"), 8);
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    reader.close();
                    conn.disconnect();
                } else {
                    throw new IOException("Server returned non-OK status: " +
                            status + " " +
                            conn.getResponseMessage());
                }
                try {
                    result = new JSONObject(sb.toString());
                } catch (JSONException | NullPointerException e) {
                    e.printStackTrace();
                }
            }
            else {
                Log.d(TAG, "CCC");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.d(TAG, "result = " + result);
        return result;
    }
/*

    public String request(String _url, ContentValues _params){

        // HttpURLConnection 참조 변수.
        HttpURLConnection urlConn = null;
        // URL 뒤에 붙여서 보낼 파라미터.
        StringBuffer sbParams = new StringBuffer();

        */
/**
         * 1. StringBuffer에 파라미터 연결
         **//*

        if (_params == null) {
            // 보낼 데이터가 없으면 파라미터를 비운다.
            sbParams.append("");
        } else {
            // 보낼 데이터가 있으면 파라미터를 채운다.
            // 파라미터가 2개 이상이면 파라미터 연결에 &가 필요하므로 스위칭할 변수 생성.
            boolean isAnd = false;
            // 파라미터 키와 값.
            String key;
            String value;

            for (Map.Entry<String, Object> parameter : _params.valueSet()) {
                key = parameter.getKey();
                value = parameter.getValue().toString();

                // 파라미터가 두개 이상일때, 파라미터 사이에 &를 붙인다.
                if (isAnd)
                    sbParams.append("&");

                sbParams.append(key).append("=").append(value);

                // 파라미터가 2개 이상이면 isAnd를 true로 바꾸고 다음 루프부터 &를 붙인다.
                if (!isAnd)
                    if (_params.size() >= 2)
                        isAnd = true;
            }
        }

        */
/**
         * 2. HttpURLConnection을 통해 web의 데이터를 가져온다.
         **//*

        try {
            URL url = new URL(_url);
            urlConn = (HttpURLConnection) url.openConnection();

            // [2-1]. urlConn 설정.
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setReadTimeout(10000);
            urlConn.setConnectTimeout(15000);
            urlConn.setDefaultUseCaches(false);
            urlConn.setRequestMethod("POST"); // URL 요청에 대한 메소드 설정 : POST.
            urlConn.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;charset=UTF-8");
            //urlConn.setRequestProperty("Content-Type", "application/json"); //application JSON 형식으로 전송
            urlConn.setRequestProperty("Accept", "text/html"); //서버에 response 데이터를 html로 받음
            urlConn.setRequestProperty("Accept-Charset", "UTF-8"); // Accept-Charset 설정.

            // [2-2]. parameter 전달 및 데이터 읽어오기.
            // sbParams에 정리한 파라미터들을 스트링으로 저장. 예)id=id1&pw=123;
            String strParams = sbParams.toString();
//            strParams = URLEncoder.encode(strParams, "UTF-8");
//                                  .replace("+", "%20")
//                                  .replace("*", "%2A")
//                                  .replace("%7E", "~")
//                                  .replace("%3D", "=");
           */
/* PrintWriter pw=null;
            try{
                pw = new PrintWriter(new OutputStreamWriter(urlConn.getOutputStream(), "UTF-8"));
            }catch(Exception e){
                Log.d(TAG,e.getMessage());
            }

            pw.write(strParams);
            pw.flush();
            pw.close();*//*


//            OutputStream os = urlConn.getOutputStream();
//            os.write(strParams.getBytes("UTF-8")); // 출력 스트림에 출력.
//            os.flush(); // 출력 스트림을 플러시(비운다)하고 버퍼링 된 모든 출력 바이트를 강제 실행.
//            os.close(); // 출력 스트림을 닫고 모든 시스템 자원을 해제.

            // [2-3]. 연결 요청 확인.
            // 실패 시 null을 리턴하고 메서드를 종료.
            if (urlConn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Log.d(TAG, "RequestHttpURLConnection : " + urlConn.getResponseCode());
                return null;
            }

            // [2-4]. 읽어온 결과물 리턴.
            // 요청한 URL의 출력물을 BufferedReader로 받는다.
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));

            // 출력물의 라인과 그 합에 대한 변수.
            String line;
            String page = "";

            // 라인을 받아와 합친다.
            while ((line = reader.readLine()) != null){
                page += line;
            }

            Log.d(TAG, page);
            return page;

        } catch (MalformedURLException e) { // for URL.
            e.printStackTrace();
        } catch (IOException e) { // for openConnection().
            e.printStackTrace();
        } finally {
            if (urlConn != null)
                urlConn.disconnect();
        }

        return null;
    }
*/

}
