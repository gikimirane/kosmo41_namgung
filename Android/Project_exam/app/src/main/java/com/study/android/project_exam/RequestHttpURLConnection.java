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
}
