package com.study.android.project_exam;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class SingerAdapter extends BaseAdapter {
    private static final String TAG = "lecture";

    Context context;
    ArrayList<SingerItem> items = new ArrayList<>();

    public SingerAdapter(Context context){
        this.context = context;
    }
    public void addItem(SingerItem item){ items.add(item); }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) { return items.get(position); }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //SingerItemView view = new SingerItemView(getApplicationContext());

        SingerItemView view = null;
        if(convertView == null){
            view = new SingerItemView(context);
        }else {
            view = (SingerItemView)convertView;
        }

        final SingerItem item = items.get(position);
        view.setDrink(item.getDrink());
        view.setAmount(item.getAmount());


        return view;
    }
}
