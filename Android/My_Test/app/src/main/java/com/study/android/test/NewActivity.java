package com.study.android.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {
    String sName;
    EditText edit1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        edit1 = findViewById(R.id.editText2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        sName = intent.getStringExtra("name");

        Toast.makeText(getApplicationContext(),"넘어온 이름 : "+sName,Toast.LENGTH_LONG).show();
    }

    public void onBtnClick2 (View v){
        String name2 = edit1.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("name",name2);
        setResult(10,intent);

        finish();
    }

}
