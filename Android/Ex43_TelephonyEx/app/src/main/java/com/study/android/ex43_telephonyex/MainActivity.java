package com.study.android.ex43_telephonyex;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";
    private TelephonyManager manager;
    String[] permissions = new String[]{
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_PHONE_STATE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!checkPermission()){
            Toast.makeText(getApplicationContext(),"권한 설정을 해야 앱 정상동작 합니다.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        manager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String s = "상태 : "+manager.getCallState()+
                   "\n전화번호 : "+manager.getLine1Number()+
                    "\n데이터 상태 : "+manager.getDataState()+
                    "\n장치 아이디 : "+manager.getDeviceId()+
                    "\n전화기 타입 : "+manager.getPhoneType()+
                    "\n로밍 여부 : "+manager.isNetworkRoaming();
        TextView text = findViewById(R.id.textView);
        text.setText(s);

        manager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE |
                PhoneStateListener.LISTEN_DATA_ACTIVITY);
    }

    private boolean checkPermission() {
        int result;
        List<String> listPermissionNeeded = new ArrayList<>();
        for(String p:permissions){
            result = ContextCompat.checkSelfPermission(this,p);
            if(result!= PackageManager.PERMISSION_GRANTED){
                listPermissionNeeded.add(p);
            }
        }
        if(!listPermissionNeeded.isEmpty()){
            ActivityCompat.requestPermissions(this,
                    listPermissionNeeded.toArray(new String[listPermissionNeeded.size()]),1);
            return false;
        }
        return true;
    }

    public void onBtn1Clicked(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:01090497786"));
        startActivity(intent);
    }
    public void onBtn2Clicked(View v){
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:01090497786"));
        startActivity(intent);
    }
    public void onBtn3Clicked(View v){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01090497786"));
        startActivity(intent);
    }
    public void onBtn4Clicked(View v){
        Intent intent = new Intent(getApplicationContext(),ContactsListActivity.class);
        startActivity(intent);
    }



    PhoneStateListener listener = new PhoneStateListener(){
        public void onCallStateChanged(int state, String incomingNumber){
            Log.d(TAG,"상태 : "+state+" 전화번호 : "+incomingNumber);
        }
        public void onDataActivity(int direction){
            Log.d(TAG,"방향 : "+direction);
        }
    };
}
