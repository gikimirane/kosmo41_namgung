package com.study.android.hello;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {
    String sName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);


        //넘어온 intent를 get한다, 그러면 intent.*을 이용해서 보내진 결과를 가지고 온다.
        Intent intent = getIntent();
        sName = intent.getStringExtra("name");

    }

    //구성이 보통 메소드명(부모,띄울 대상)으로 이루워져 있음
    public void onBtn1Clicked(View v){
        Toast.makeText(getApplicationContext(),"이름 : "+sName,Toast.LENGTH_LONG).show();

    }

    //현재 액티비티를 종료한다 (현재는 main으로 돌아가게 됨)
    public void onBtn2Clicked(View v){
        Intent intent = new Intent();
        intent.putExtra("backData","강감찬");
        setResult(10,intent);
        finish();
    }

}
