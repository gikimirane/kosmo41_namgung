package com.study.android.project_exam;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class mysuccesslistItemView extends LinearLayout {
    private static final String TAG = "lecture";
    TextView mycode;
    TextView myprice;
    TextView mymenu;

    public mysuccesslistItemView(final Context context) {
        super(context);

        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.mysuccesslist_item_view,this,true);

        mycode = findViewById(R.id.mycode);
        myprice = findViewById(R.id.myprice);
        mymenu = findViewById(R.id.mymenu);
    }

    public void setCode(String code){
        mycode.setText(code);
    }
    public void setPrice(String price) { myprice.setText(price); }
    public void setMenu(String menu) { mymenu.setText(menu); }

}
