package com.study.android.list_homework;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.android.list_homework.R;

public class ItemView extends LinearLayout {
    private static final String TAG = "lecture";
    TextView textView1;
    TextView textView2;
    TextView textView3;
    ImageView imageView1;

    public ItemView(Context context,String gender) {
        super(context);


        if(gender.equals("남")){
            LayoutInflater inflater =
                    (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.item_man,this,true);

        }else if(gender.equals("여")) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.item_woman, this, true);
        }

        textView1=findViewById(R.id.textView1);
        textView2=findViewById(R.id.textView2);
        textView3=findViewById(R.id.textView3);
        imageView1=findViewById(R.id.imageView1);
    }

    public void setName(String name){
        textView1.setText(name);
    }
    public void setAge(String Age) {
        textView2.setText(Age);
    }
    public void setAddress(String address){
        textView3.setText(address);
    }
    public void setImage(int imgNum){
        imageView1.setImageResource(imgNum);
    }
}
