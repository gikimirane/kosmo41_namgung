package com.study.android.hello;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";                    //코드 합칠 때 좋으라고 암묵적으로 이렇게 쓴다고 함
    EditText edittext;
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);                     //메모리에 있는거 화면에 띄워주고~
        edittext = findViewById(R.id.editText1);
        textview = findViewById(R.id.textView1);

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

    //버튼4 :
    //버튼 누르면 입력받은 글자 긁어서 띄워줌
    public void onBtn4Clicked(View v){
        textview.setText(edittext.getText());
    }

    //버튼5 :
    //파라미터는 어떤버튼이 눌렸는지로 들어옴, View는 최상위 부모이기때문에 누구든 들어올 수 있어
    //다른 액티비티를 부르는 동작
    //Intent ( 부모, 열릴대상 )
    public void onBtn5Clicked(View v){
        Intent intent = new Intent(getApplicationContext(),NewActivity.class);
        //put이 반복될 경우 객체를 보내줘도 됨
        intent.putExtra("name","홍길동");
        startActivityForResult(intent,1);
    }

    @Override
    //리스너 같음. ActivityResult가 들어오면 호출 됨.. (알아서) 오버라이드 됨
    public void onActivityResult(int requestCode, int resultCode, Intent data){

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
