package com.study.android.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edit;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = findViewById(R.id.editText1);


    }

    public void onBtnClick1 (View v){
        Intent intent = new Intent(getApplicationContext(),NewActivity.class);
        intent.putExtra("name",edit.getText().toString());

        startActivityForResult(intent,1);
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        String str="";
        if(requestCode==1&&resultCode==10){
            String sData = "";
            str = "이름 : "+data.getStringExtra("name");

        }
        Toast.makeText(getApplicationContext(), str ,Toast.LENGTH_LONG).show();

    }

}
