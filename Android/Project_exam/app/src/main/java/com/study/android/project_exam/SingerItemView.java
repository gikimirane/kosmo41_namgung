package com.study.android.project_exam;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class SingerItemView extends LinearLayout {
    private static final String TAG = "lecture";
    TextView textView1;
    TextView textView2;
    Spinner spinner;
    String[] count = {"0","1","2","3","4","5","6"};

    public SingerItemView(final Context context) {
        super(context);

        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_item_view,this,true);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),android.R.layout.simple_spinner_item,count);

        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG","여기서 SingerItem setting하고 싶다.");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });


    }

    public void setDrink(String drink){
        textView1.setText(drink);
    }
    public void setAmount(String amount) {textView2.setText(amount); }
    public void setCount(String count) { spinner.setSelection(Integer.parseInt(count));}

}
