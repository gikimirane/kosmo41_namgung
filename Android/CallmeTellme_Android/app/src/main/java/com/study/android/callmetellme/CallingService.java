package com.study.android.callmetellme;



import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

public class CallingService extends Service {
    public static final String EXTRA_CALL_NUMBER = "call_number";
    private static final String TAG = "lecture";

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "서비스의 onCreate");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "서비스의 onStartCommand");

        return START_REDELIVER_INTENT ;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "서비스의 onDestroy");
    }
/*  public void RequestHttp(String phone){

        String sUrl ="http://192.168.0.101:8081/CallmeTellme/android/comingcall";
        HashMap<String,String> values = new HashMap<>();
        values.put("phone",phone);
        myNetworkTask networkTask = new myNetworkTask(sUrl,values);
        networkTask.execute();
    }*/
}
