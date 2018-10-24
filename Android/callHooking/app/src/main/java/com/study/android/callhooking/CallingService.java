package com.study.android.callhooking;

import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class CallingService extends Service {
    public static final String EXTRA_CALL_NUMBER = "call_number";

    @Override
    public IBinder onBind(Intent intent) {
        // Service 객체와 (화면단 Activity 사이에서)
        // 통신(데이터를 주고받을) 때 사용하는 메서드
        // 데이터를 전달할 필요가 없으면 return null;
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        // 서비스에서 가장 먼저 호출됨(최초에 한번만)
        Log.d("test", "서비스의 onCreate");
        Toast.makeText(getApplicationContext(),"서비스 Create",Toast.LENGTH_SHORT).show();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String phone_number = intent.getStringExtra(EXTRA_CALL_NUMBER);
        Log.d("test", "서비스의 onStartCommand");
        Toast.makeText(getApplicationContext(),"전화번호 : "+phone_number,Toast.LENGTH_SHORT).show();

        return START_REDELIVER_INTENT ;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        // 서비스가 종료될 때 실행
        //mp.stop(); // 음악 종료
        Toast.makeText(getApplicationContext(),"종료!!!",Toast.LENGTH_SHORT).show();
        Log.d("test", "서비스의 onDestroy");
    }
}
