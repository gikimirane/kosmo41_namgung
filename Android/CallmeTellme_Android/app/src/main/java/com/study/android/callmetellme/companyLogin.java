package com.study.android.callmetellme;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class companyLogin extends AppCompatActivity {
    private static final String TAG = "lecture";
    EditText id;
    EditText pw;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_login);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    this,
                    new String[] { Manifest.permission.READ_PHONE_STATE },
                    1);
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    this,
                    new String[] { Manifest.permission.SEND_SMS },
                    1);
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    this,
                    new String[] { Manifest.permission.INTERNET },
                    1);
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    this,
                    new String[] { Manifest.permission.CALL_PHONE },
                    1);
        }



        id = findViewById(R.id.etid);
        pw = findViewById(R.id.etpw);
        checkBox = findViewById(R.id.autologin);
        if(!SaveSharedPreference.getMyId(this).equals("")){
            String strID = SaveSharedPreference.getMyId(this);
            String strPW  = SaveSharedPreference.getMypw(this);
            Log.d(TAG,"PreparedShare id : "+strID+" / PW "+strPW);
            try {
                trylogin(strID,strPW);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"나의 TOKEN : "+refreshedToken);
    }

    public void LoginBtnClicked(View v) throws InterruptedException, ExecutionException, JSONException {
        if(checkBox.isChecked()){
            SaveSharedPreference.setMyId(getApplicationContext(),id.getText().toString());
            SaveSharedPreference.setMypw(getApplicationContext(),pw.getText().toString());
        }
        String userid = id.getText().toString();
        String userpw = pw.getText().toString();

        trylogin(userid,userpw);
    }

    private void trylogin(String userid, String userpw) throws ExecutionException, InterruptedException, JSONException{
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        String sUrl ="http://192.168.0.101:8081/CallmeTellme/android/login";
        HashMap<String,String> values = new HashMap<>();
        values.put("userid",userid);
        values.put("userpw",userpw);
        values.put("usertoken",refreshedToken);

        myNetworkTask networkTask = new myNetworkTask(sUrl,values);
        JSONObject s = networkTask.execute().get();
        String result=s.getString("result");

        if(result.equals("success")){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(getApplicationContext(),"아이디와 비밀번호를 다시 확인해주세요.",Toast.LENGTH_SHORT).show();
        }
    }
}
