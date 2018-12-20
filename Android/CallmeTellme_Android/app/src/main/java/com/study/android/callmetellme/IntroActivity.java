package com.study.android.callmetellme;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class IntroActivity extends AppCompatActivity {

    private static final String TAG="lecture";

    private static final int STOPSPLASH=0;
    private static final long SPLASHTIME=500;

    private Handler splashHandler = new Handler(){
        public void handleMessage(Message msg){
            Intent intent;

            switch(msg.what){
                case STOPSPLASH:
                    intent = new Intent(IntroActivity.this,companyLogin.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in,R.anim.hold);
                    finish();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

    }

    protected void onResume(){
        super.onResume();

        Message msg = new Message();
        msg.what = STOPSPLASH;
        splashHandler.sendMessageDelayed(msg,SPLASHTIME);
    }
}
