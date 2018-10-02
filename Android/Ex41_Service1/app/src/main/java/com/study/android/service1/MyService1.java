package com.study.android.service1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService1 extends Service {
    private static final String TAG = "lecture";

    public MyService1() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void onCreate(){
        super.onCreate();
        Log.d(TAG,"Service start");

        MyThread1 thread = new MyThread1();
        thread.start();
    }

    class MyThread1 extends Thread {

        public void run(){
            for(int i=0;i<10;i++){
                Log.d(TAG,"Service Called .. "+i);

                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            //내가 넣어둠..for문 다 돌리고 service 죽도록
            onDestroy();
        }

    }
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"Service end");
    }
}
