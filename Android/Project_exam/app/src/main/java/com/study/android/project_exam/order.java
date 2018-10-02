package com.study.android.project_exam;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class order extends AppCompatActivity {
    private static final String TAG = "lecture";
    SingerAdapter adapter;
    EditText editText1;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        adapter = new SingerAdapter(this);
        ListView listView1 = findViewById(R.id.mlist1);

/*

        SingerItem item1 = new SingerItem("홍길동");
        adapter.addItem(item1);

        SingerItem item2 = new SingerItem("강감찬");
        adapter.addItem(item2);

        SingerItem item3 = new SingerItem("길동이");
        adapter.addItem(item3);
*/


        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                SingerItem item = (SingerItem)adapter.getItem(position);
                Toast.makeText(getApplicationContext(),"selected : "+item.getDrink(), Toast.LENGTH_SHORT).show();
            }
        });

        //editText1 = findViewById(R.id.editText1);
        //editText2 = findViewById(R.id.editText2);
    }

    public void menuBtnClicked(View v){
        String sUrl ="http://192.168.200.175:8081/menulist/menulist.jsp";
        Log.d(TAG,"sURL : "+sUrl);
        try{
            ContentValues values = new ContentValues();
            values.put("userid","abcde");
            values.put("userpwd","1234");

            NetworkTask networkTask = new NetworkTask(sUrl,values);
            networkTask.execute();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public class NetworkTask extends AsyncTask<Object, Integer, JSONObject> {
        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values){
            this.url = url;
            this.values = values;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(Object... params) {
            JSONObject rtn = null;

            values.put("id","abcde");

            String result;
            RequestHttpURLConnection requestHttpURLConnection =
                    new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url,values);

            return rtn;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);

            if (result != null) {
                try {
                    if (!result.getString("menu").isEmpty()) {
                        Toast.makeText(getApplicationContext(),"DB 성공!", Toast.LENGTH_SHORT).show();
                        SingerItem item1 = new SingerItem(result.getString("menu"));
                        adapter.addItem(item1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getApplicationContext(), "DB 실패!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
