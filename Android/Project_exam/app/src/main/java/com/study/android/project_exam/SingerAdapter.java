package com.study.android.project_exam;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;

public class SingerAdapter extends BaseAdapter {
    private static final String TAG = "lecture";

    CheckBox checkbox;
    Spinner spinner;
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
        view.setCount(item.getCount());

        checkbox = (CheckBox) view.findViewById(R.id.checkBox1);
        checkbox.setChecked(((ListView)parent).isItemChecked(position));
        checkbox.setFocusable(false);
        checkbox.setClickable(false);

        return view;
    }
}
