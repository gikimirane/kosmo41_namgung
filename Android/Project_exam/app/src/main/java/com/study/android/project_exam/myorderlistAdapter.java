package com.study.android.project_exam;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import java.util.ArrayList;

public class myorderlistAdapter extends BaseAdapter {
    private static final String TAG = "lecture";

    Context context;
    ArrayList<myorderlistItem> items = new ArrayList<>();

    public myorderlistAdapter(Context context){
        this.context = context;

    }
    public void addItem(myorderlistItem item){ items.add(item); }


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

        myorderlistItemView view = null;
        if(convertView == null){
            view = new myorderlistItemView(context);
        }else {
            view = (myorderlistItemView)convertView;
        }

        final myorderlistItem item = items.get(position);
        view.setCode(item.getCode());
        view.setPrice(item.getPrice());
        view.setMenu(item.getMenu());

        return view;
    }
}
