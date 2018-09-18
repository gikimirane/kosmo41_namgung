package com.study.android.lifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onBtnClicked(View v){

        Intent intent = new Intent(getApplicationContext(),NewActivity.class);
        //put이 반복될 경우 객체를 보내줘도 됨
        intent.putExtra("name","홍길동");
        startActivityForResult(intent,1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode,resultCode,data);

        Log.d(TAG,"콜백 함수 호출 됨");

        //요청한 애는 1번, 돌려받은 애는 10번
        if(requestCode==1 && resultCode==10){
            String sData= "";
            String str = "OnActivityResult() Called : "+
                    resultCode+" : "+ resultCode;

            sData = data.getStringExtra("backData");
            str = str + " : "+sData;

            Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
        }
    }
}
