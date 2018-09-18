package com.study.android.text1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText1;
    EditText editText2;
    TextView textView;
    String cal;
    String cal1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1 = findViewById(R.id.num1);
        editText2 = findViewById(R.id.num2);
        textView = findViewById(R.id.textView1);
    }

    public void onBtn1Click(View v){
        cal1 = "+";
    }
    public void onBtn2Click(View v){
        cal1 = "-";
    }
    public void onBtn3Click(View v){
        cal1 = "*";
    }
    public void onBtn4Click(View v){
        cal1 = "/";
    }

    public void onBtnEqualClick(View v){
        String n1 = editText1.getText().toString();
        String n2 = editText2.getText().toString();
        int num1 = Integer.parseInt(n1);
        int num2 = Integer.parseInt(n2);
        int sum=0;
        if(cal1.equals("+")){
            sum = num1+num2;
        }else if(cal1.equals("-")){
            sum = num1-num2;
        }else if(cal1.equals("*")){
            sum = num1*num2;
        }else if(cal1.equals("/")){
            sum = num1/num2;
        }
        textView.setText("연산결과 = "+sum);

    }

}
