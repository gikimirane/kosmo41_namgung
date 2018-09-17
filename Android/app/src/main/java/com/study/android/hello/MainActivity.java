package com.study.android.hello;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";                    //코드 합칠 때 좋으라고 암묵적으로 이렇게 쓴다고 함

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);                     //메모리에 있는거 화면에 띄워주고~

        //버튼1 :
        //클릭 이벤트 추가
        //로그 출력 추가
        //토스트 생성 추가

        //전통적인 방식
        Button button1 = findViewById(R.id.button1);                //HTML getElementById 와 같은..
        button1.setOnClickListener(new View.OnClickListener(){      //Listener 만들어주고~
            public void onClick(View v){                            //클릭할 때마다 이런 동작을 하라
                Log.d(TAG,"로그출력");
                Toast.makeText(getApplicationContext(),"긴 토스트",Toast.LENGTH_LONG).show();

            }
        });
    }
    //버튼2 :
    // 인텐트 만들어서 웹브라우저 띄우기
    public void onBtn2Clicked(View v){
       Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
       startActivity(intent);
    }
    //버튼3 :
    //전화 거는 걸로..
    public void onBtn3Clicked(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:01090497786"));
        startActivity(intent);
    }
}
