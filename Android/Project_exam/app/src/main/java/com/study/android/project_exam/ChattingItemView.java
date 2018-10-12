package com.study.android.project_exam;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChattingItemView extends LinearLayout {
    private static final String TAG = "lecture";
    TextView myname;
    TextView mymsg;


    public ChattingItemView(final Context context,String name) {
        super(context);


        if(name.equals(MainActivity.info.getName())){
            LayoutInflater inflater =
                    (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.mychatting_item_view,this,true);
        }else {
            LayoutInflater inflater =
                    (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.chatting_item_view,this,true);
        }

        myname = findViewById(R.id.myname);
        mymsg = findViewById(R.id.mymsg);
    }

    public void setMyName(String name){
        myname.setText(name);
    }
    public void setMymsg(String msg) { mymsg.setText(msg); }

}
