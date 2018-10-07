package com.study.android.project_exam;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class Fragment1 extends Fragment {
    private static final String TAG = "lecture";
    ListView listview1;
    myorderlistAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView =
                (ViewGroup)inflater.inflate(R.layout.fragment1,container,false);
        listview1 = rootView.findViewById(R.id.waiting);
        adapter = new myorderlistAdapter(getContext());
        requestdata();
        return rootView;
    }

    public void requestdata(){
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        String sUrl ="http://ec2-13-209-64-83.ap-northeast-2.compute.amazonaws.com:8081/menulist/dbController.jsp";
        HashMap<String,String> values = new HashMap<>();
        values.put("order","myorderlist");
        values.put("client",refreshedToken);
        NetworkTask networkTask = new NetworkTask(sUrl,values);
        networkTask.execute();
    }


    public void createlist(JSONObject s){

        myorderlistItem item;
        String menu;
        String price;
        String code;

        try {
            JSONArray mymenu = (JSONArray) s.get("menu");
            JSONArray myprice = (JSONArray) s.get("price");
            JSONArray mycode = (JSONArray) s.get("code");

            for(int i=0;i<mymenu.length();i++){

                menu = mymenu.getString(i);
                price = myprice.getString(i);
                code = mycode.getString(i);

                item = new myorderlistItem(menu,price,code);
                adapter.addItem(item);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        listview1.setAdapter(adapter);

    }


    public class NetworkTask extends AsyncTask<Object,Void,JSONObject> {
        private String surl;
        private HashMap<String,String> values;
        StringBuffer sbParams = new StringBuffer();
        String key;
        String value;
        boolean isAnd = false;

        public NetworkTask(String url, HashMap values) {
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

            if(s!=null){
                //Toast.makeText(getApplicationContext(), "onPostExecute", Toast.LENGTH_LONG).show();
                Log.d(TAG,"fragment : onPostExecute");
                createlist(s);
            }else {
                Log.d(TAG,"결과없음!");
                //Toast.makeText(getApplicationContext(), "결과없음!!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
