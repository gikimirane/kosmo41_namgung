package com.study.android.list6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SingerAdapter adapter;
    EditText editText1;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new SingerAdapter(this);
        SingerItem item1 = new SingerItem("홍길동","010-1234-5678",R.drawable.face1);
        adapter.addItem(item1);

        SingerItem item2 = new SingerItem("강감찬","010-4321-9876",R.drawable.face1);
        adapter.addItem(item2);

        SingerItem item3 = new SingerItem("길동이","010-5678-4321",R.drawable.face1);
        adapter.addItem(item3);

        ListView listView1 = findViewById(R.id.listView1);
        listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                SingerItem item = (SingerItem)adapter.getItem(position);
                Toast.makeText(getApplicationContext(),"selected : "+item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        //editText1 = findViewById(R.id.editText1);
        //editText2 = findViewById(R.id.editText2);
    }
/*
    public void onBtn1Clicked(View v){
        String inputName = editText1.getText().toString();
        String inputAge = editText2.getText().toString();

        SingerItem item = new SingerItem(inputName,inputAge,R.drawable.face1);
        adapter.addItem(item);
        adapter.notifyDataSetChanged();
    }*/


}
