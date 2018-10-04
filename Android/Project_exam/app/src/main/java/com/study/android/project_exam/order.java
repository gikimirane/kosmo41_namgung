package com.study.android.project_exam;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class order extends AppCompatActivity {
    private static final String TAG = "lecture";
    SingerAdapter adapter;
    TextView textView1;
    ListView listView1;

    HashMap<String,String> cb = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        listView1 = findViewById(R.id.listview1);


        String sUrl ="http://ec2-13-209-64-83.ap-northeast-2.compute.amazonaws.com:8081/menulist/menulist.jsp";
        Log.d(TAG,"sURL : "+sUrl);

        try{
            HashMap<String, String> values = new HashMap<>();
            values.put("userid","admin");
            values.put("userpwd","1234");

            NetworkTask networkTask = new NetworkTask(sUrl, values);
            networkTask.execute();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void codeBtnClicked(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(order.this);
        builder.setMessage("주문할 메뉴를 모두 체크하셨습니까?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("알림!")
                .setCancelable(true)

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id){
                        Toast.makeText(getApplicationContext(),"미구현~! nono",Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getApplicationContext(),orderOk.class);
                        intent.putExtra("orderlist",cb);
                        startActivity(intent);
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }
    public void menulist(JSONObject s){
        adapter = new SingerAdapter(this);
        String menulist;
        String price;
        try {
            JSONArray menu = (JSONArray) s.get("menu");
            JSONArray amount = (JSONArray) s.get("amount");

            for(int i=0;i<menu.length();i++){
                menulist = menu.getString(i);
                price = amount.getString(i);
                adapter.addItem(new SingerItem(menulist,price));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listView1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                SingerItem item = (SingerItem)adapter.getItem(position);
                //Toast.makeText(getApplicationContext(),item.getDrink(), Toast.LENGTH_SHORT).show();

                CheckBox checkBox = arg1.findViewById(R.id.checkBox1);

                if(checkBox.isChecked()){
                    checkBox.setChecked(false);
                    cb.remove(item.getDrink());
                }else {
                    checkBox.setChecked(true);
                    cb.put(item.getDrink(),item.getAmount());
                }
            }
        });
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
                        value = values.get(key);
                        if(isAnd)
                        {
                            sbParams.append("&");
                        }

                        sbParams.append(key).append("=").append(value);

                        if (!isAnd)
                            if (set.size() >= 2)
                                isAnd = true;
                    }
                    String strParams = sbParams.toString();
                    conn.setConnectTimeout(10000);
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
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

        public void onPostExecute(JSONObject s) {
            super.onPostExecute(s);

            if(s!=null){
                try {
                    Log.d(TAG, "s : " + s.getString("menu"));
                    Toast.makeText(getApplicationContext(), "주문 메뉴를 선택 후 HAPPY CODE를 누르세요. ", Toast.LENGTH_LONG).show();
                    menulist(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else {
                Log.d(TAG,"결과없음!");
                Toast.makeText(getApplicationContext(), "결과없음!!", Toast.LENGTH_SHORT).show();
            }

        }
    }


}
