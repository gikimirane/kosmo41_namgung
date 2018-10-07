package com.study.android.project_exam;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.RemoteMessage;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class orderOk extends AppCompatActivity {
    HashMap<String, String> orderlist;

    TextView mymenu,total,tvCode;
    int sum;
    String price;
    private static final String TAG = "lecture";
    String menu="",totalsum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_ok);
        mymenu = findViewById(R.id.mymenu);
        total = findViewById(R.id.total);
        tvCode = findViewById(R.id.tvCode);
        //String count;

        Intent intent = getIntent();
        orderlist = (HashMap<String, String>) intent.getSerializableExtra("orderlist");

        String count;
        for( String key : orderlist.keySet() ){

            //String co = key.substring(key.length()-2,key.length()-1);
            //int count = Integer.parseInt(co);

            price = orderlist.get(key);
            count = price.substring(0,1);
            price = price.substring(2,price.length());
            price = price.substring(0,price.length()-1);

            int subsum = Integer.parseInt(count)*Integer.parseInt(price);
            sum = sum + subsum;

            mymenu.append(key+"  "+count+"잔  " +price+"원  = "+subsum+"원\n");
            //메뉴|갯수|금액|
            menu = menu+key+"|"+count+"|";
        }
        menu = menu.substring(0,menu.length()-1);
        total.setText(price);
        total.setText("\n지불하실 금액은 "+sum+"원 입니다.");
        totalsum = Integer.toString(sum);
        randomCode(menu);
    }

    private void randomCode(String order){

        Random ran = new Random();
        int code = ran.nextInt(1000);
        String result = String.format("%04d",code);
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        String sUrl ="http://ec2-13-209-64-83.ap-northeast-2.compute.amazonaws.com:8081/menulist/dbController.jsp";
        //String sUrl ="http://192.168.200.131:8081/menulist/dbController.jsp";
        HashMap<String,String> values= new HashMap<>();
      //  ContentValues values = new ContentValues();
        values.put("order","orderinput");
        values.put("menu",order);
        values.put("code",result);
        values.put("price",totalsum);
        values.put("client",refreshedToken);

        Log.d(TAG,"client key : "+refreshedToken);


        NetworkTask networkTask = new NetworkTask(sUrl, values);
        networkTask.execute();

        //이 난수가 db에 있으면 다시 실행시켜야 함
        //json 1 or 1로 다시 받자
        //data 접근 깔끔하게 하는 메소드 만들기
        //코드 발생시키고, db에 넣고

        tvCode.setText(result);
    }

    public class NetworkTask extends AsyncTask<Object,Void,JSONObject> {
        private String surl;
        private HashMap<String,String> values;
        //ContentValues values = new ContentValues();
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
                try {
                    Log.d(TAG, "s : " + s.getString("result"));
                    Toast.makeText(getApplicationContext(), "db 입력 결과는! "+s.getString("result"), Toast.LENGTH_LONG).show();

                    //Toast.makeText(getApplicationContext(), "push 결과는! "+s.getString("result"), Toast.LENGTH_LONG).show();
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
