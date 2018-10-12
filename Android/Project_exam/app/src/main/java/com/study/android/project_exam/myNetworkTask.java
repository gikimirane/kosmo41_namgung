package com.study.android.project_exam;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;

public class myNetworkTask extends AsyncTask<Object,Void,JSONObject> {
    private static final String TAG = "lecture";
    private String surl;
    private HashMap<String,String> values;
    //ContentValues values = new ContentValues();
    StringBuffer sbParams = new StringBuffer();
    String key;
    String value;
    boolean isAnd = false;

    public myNetworkTask(String url, HashMap values) {
        this.surl = url;
        this.values = values;
    }

    @Override
    protected JSONObject doInBackground(Object... params) {
        JSONObject result = null;
        RequestHttpURLConnection request = new RequestHttpURLConnection();

        result=request.jsonReturn(surl,values);
        return result;
    }

    public void onPostExecute(JSONObject s) {
        super.onPostExecute(s);
        String result;
        if(s!=null){
            try {
                Log.d(TAG,"결과 : "+s.getString("result"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            Log.d(TAG,"결과없음!");
        }
    }
}