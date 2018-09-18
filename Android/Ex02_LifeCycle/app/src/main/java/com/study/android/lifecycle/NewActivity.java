package com.study.android.lifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        Toast.makeText(getApplicationContext(),"OnCreate() 호출 됨",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(),"OnStart() 호출 됨",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(),"OnResume() 호출 됨",Toast.LENGTH_LONG).show();

        Intent intent = getIntent();
        String sName = intent.getStringExtra("name");
        Toast.makeText(getApplicationContext(),"내 이름은? : "+sName,Toast.LENGTH_LONG).show();
}

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(),"OnPause() 호출 됨",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(),"onStop() 호출 됨",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"onDestroy() 호출 됨",Toast.LENGTH_LONG).show();
    }

    public void onBtn2Clicked(View v){
        Intent intent = new Intent();
        intent.putExtra("backData","강감찬");
        setResult(10,intent);
        finish();
    }

}
