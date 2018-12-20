package com.study.android.callmetellme;

import android.Manifest;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";
    public static boolean status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    1);
        }


        Switch sw = findViewById(R.id.switch1);
        sw.setChecked(SaveSharedPreference.getStatus(this));
        Log.d(TAG, "Shared : " + SaveSharedPreference.getStatus(getApplicationContext()));
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                status = isChecked;
                SaveSharedPreference.setStatus(getApplicationContext(), status);

                Log.d(TAG, "상태 : " + status);
                Log.d(TAG, "Shared : " + SaveSharedPreference.getStatus(getApplicationContext()));
            }
        });

    }

    public void logoutBtnClicked(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("정말 LOGOUT 하시겠습니까?")
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
                        Log.d(TAG, "로그아웃영역");
                        SaveSharedPreference.clearLogin(getApplicationContext());
                        dialog.cancel();
                        Intent intent = new Intent(getApplicationContext(), companyLogin.class);
                        startActivity(intent);
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void RequestHttp(String phone) {
        Log.d(TAG, "main안에 RequestHTTP");
        String sUrl = "http://192.168.0.101:8081/CallmeTellme/android/comingcall";
        HashMap<String, String> values = new HashMap<>();
        values.put("phone", phone);
        myNetworkTask networkTask = new myNetworkTask(sUrl, values);
        networkTask.execute();
    }
}
