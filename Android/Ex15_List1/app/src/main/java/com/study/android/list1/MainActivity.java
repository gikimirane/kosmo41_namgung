package com.study.android.list1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";
    String [] names = {"홍길동","강감찬","을지문덕","양만춘","유관순"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, names);

        ListView listView1 = findViewById(R.id.listView1);
        listView1.setAdapter(adapter);

        //바꾸면 안돼 이상해지니까, 바꿀 영역은 onItemClick 안에만 바꾸면 됨
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3){
                //이 부분 바꾸면 됨
                Toast.makeText(getApplicationContext(),"selected : "+names[position],Toast.LENGTH_SHORT).show();
            }

        });

    }
}
