package com.study.android.asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    ProgressBar mProgress1;
    int mProgressStatus=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgress1 = findViewById(R.id.progressBar1);
    }

    public void onBtnClicked(View v){
        new CounterTask().execute(0);

    }
    //<doinbackground에서 사용되는애,onProgress에서 필요한애, onpostExecute에서 필요한애> 순서
    private class CounterTask extends AsyncTask<Integer,Integer,Integer> {
        protected void onPreExecute(){
            //제일 먼저 호출되는 애
        }
        @Override
        protected Integer doInBackground(Integer... value) {
            while(mProgressStatus<100){
                try{
                    Thread.sleep(100);
                }catch(InterruptedException e){
                }
                mProgressStatus++;


                publishProgress(mProgressStatus);
            }

            return mProgressStatus;
        }

        protected void onProgressUpdate(Integer...value){
            mProgress1.setProgress(mProgressStatus);
        }
        protected void onPostExecute(Integer result){
            mProgress1.setProgress(mProgressStatus);
        }
    }
}
