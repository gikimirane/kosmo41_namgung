package com.study.android.smssend;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";
    EditText etTelNum;
    EditText etMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTelNum = findViewById(R.id.etTelNum);
        etMessage = findViewById(R.id.etMessage);

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    this,
                    new String[] { Manifest.permission.SEND_SMS },
                    1
            );
        }
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    this,
                    new String[] { Manifest.permission.READ_PHONE_STATE },
                    1
            );
        }


    }

    public void onBtnClicked(View v){

        if(etTelNum.length() > 0 && etMessage.length() > 0){
            String phoneNumber = etTelNum.getText().toString();
            String message = etMessage.getText().toString();

            Log.d(TAG,phoneNumber);

            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNumber,null,message,null,null);

            Toast.makeText(getApplicationContext(),
                    "메세지를 보냈습니다.",Toast.LENGTH_LONG).show();
        }
    }
}
