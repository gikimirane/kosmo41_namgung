package com.study.android.callmetellme;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class myNetworkTask extends AsyncTask<Object,Void,JSONObject> {
    private static final String TAG = "lecture";
    private String surl;
    private HashMap<String,String> values;
    String value;

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
        String type="없음";

        if(s!=null){
            try {
                type = s.getString("type");
                if(type.equals("search")){
                    Log.d(TAG,"search");
                }else if(type.equals("trylogin")){
                    Log.d(TAG,"Trylogin 타기");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            Log.d(TAG,"결과없음!");
        }
    }

}