package com.study.android.httpex1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnGetAct(View v){
        Intent intent = new Intent(MainActivity.this,HttpGetActivity.class);
        startActivity(intent);
    }

    public void onBtnPostAct(View v){
        Intent intent = new Intent(MainActivity.this,HttpPostActivity.class);
        startActivity(intent);
    }
}
