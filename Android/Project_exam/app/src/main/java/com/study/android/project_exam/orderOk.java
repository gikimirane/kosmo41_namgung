package com.study.android.project_exam;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
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
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;

public class orderOk extends AppCompatActivity {
    HashMap<String, String> orderlist;

    TextView mymenu,total,tvCode,myname;
    int sum;
    String price,result;
    private static final String TAG = "lecture";
    String menu="",totalsum;
    int subsum,count=0;
    String saveid;
    myNetworkTask networkTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_ok);

        mymenu = findViewById(R.id.mymenu);
        total = findViewById(R.id.total);
        tvCode = findViewById(R.id.tvCode);
        myname = findViewById(R.id.mypoint);
        myname.setText("PLACIDO CARD 잔액 : "+MainActivity.info.getPoint()+"원");

        Intent intent = getIntent();
        orderlist = (HashMap<String, String>) intent.getSerializableExtra("orderlist");

        String count;
        for( String key : orderlist.keySet() ){
            price = orderlist.get(key);
            count = price.substring(0,1);
            price = price.substring(2,price.length());
            price = price.substring(0,price.length()-1);

            subsum = Integer.parseInt(count)*Integer.parseInt(price);
            sum = sum + subsum;

            mymenu.append(key+"  "+count+"잔  " +price+"원  = "+subsum+"원\n");
            //메뉴|갯수|금액|
            menu = menu+key+"|"+count+"|";
        }
        menu = menu.substring(0,menu.length()-1);
        total.setText(price);
        total.setText("\n지불하실 금액은 "+sum+"원 입니다.");
        totalsum = Integer.toString(sum);
        saveid = SaveSharedPreference.getUserName(this);
        randomCode(menu);
    }



    public void paytopoint(){
        String sUrl ="http://ec2-13-209-64-83.ap-northeast-2.compute.amazonaws.com:8081/Jsp28/payclient.ad";
        //String sUrl ="http://192.168.200.131:8081/menulist/payclient.ad";
        HashMap<String,String> values= new HashMap<>();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        values.put("code",result);
        networkTask= new myNetworkTask(sUrl, values);
        networkTask.execute();

        sUrl ="http://ec2-13-209-64-83.ap-northeast-2.compute.amazonaws.com:8081/Jsp28/change.ad";
        //String sUrl ="http://192.168.200.131:8081/menulist/payclient.ad";
        values= new HashMap<>();
        refreshedToken = FirebaseInstanceId.getInstance().getToken();
        values.put("code",result);
        values.put("status","결제완료");
        networkTask = new myNetworkTask(sUrl, values);
        networkTask.execute();

        sUrl ="http://ec2-13-209-64-83.ap-northeast-2.compute.amazonaws.com:8081/Jsp28/dbController.jsp";
        //sUrl ="http://192.168.200.131:8081/menulist/dbController.jsp";
        values= new HashMap<>();
        refreshedToken = FirebaseInstanceId.getInstance().getToken();
        values.put("order","paypoint");
        values.put("userid",MainActivity.info.getId());
        values.put("subsum", String.valueOf(subsum));

        networkTask = new myNetworkTask(sUrl, values);
        networkTask.execute();

        MainActivity.info.setPoint(MainActivity.info.getPoint()-subsum);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("PLACIDO CARD로 결제가 완료되었습니다.\n"+MainActivity.info.getId()+"님의 현재 잔액은 "+MainActivity.info.getPoint()+"원 입니다.")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setCancelable(true)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        Intent intent = new Intent(getApplicationContext(),myorderlist.class);
                        startActivity(intent);

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    public void paybtnClicked(View v){
        if(subsum <= MainActivity.info.getPoint() && count==0){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("PLACIDO CARD로 결제하시겠습니까?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setCancelable(true)
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id){
                            dialog.cancel();
                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            paytopoint();
                            count=1;
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }else if(subsum > MainActivity.info.getPoint()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("잔액이 부족합니다.\nPLACIDO CARD 충전이 필요합니다.")

                    .setCancelable(true)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        }
    }


    private void randomCode(String order){

        Random ran = new Random();
        int code = ran.nextInt(1000);
        result = String.format("%04d",code);
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        String sUrl ="http://ec2-13-209-64-83.ap-northeast-2.compute.amazonaws.com:8081/Jsp28/dbController.jsp";
        //String sUrl ="http://192.168.0.101:8081/Jsp28/dbController.jsp";
        HashMap<String,String> values= new HashMap<>();
        //ContentValues values = new ContentValues();
        values.put("order","orderinput");
        values.put("menu",menu);
        values.put("code",result);
        values.put("price",totalsum);
        values.put("client",refreshedToken);
        values.put("userid",saveid);

        //Log.d(TAG,"client key : "+refreshedToken);
        myNetworkTask networkTask = new myNetworkTask(sUrl, values);
        networkTask.execute();
        tvCode.setText(result);
    }
}
