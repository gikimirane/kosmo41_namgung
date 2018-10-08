package com.study.android.project_exam;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class myfinishAdapter extends BaseAdapter {
    private static final String TAG = "lecture";

    Context context;
    ArrayList<myfinishlistItem> items = new ArrayList<>();

    public myfinishAdapter(Context context){
        this.context = context;

    }
    public void addItem(myfinishlistItem item){ items.add(item); }


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

        myfinishlistItemView view = null;
        if(convertView == null){
            view = new myfinishlistItemView(context);
        }else {
            view = (myfinishlistItemView)convertView;
        }

        final myfinishlistItem item = items.get(position);
        view.setCode(item.getCode());
        view.setPrice(item.getPrice());
        view.setMenu(item.getMenu());

        return view;
    }
}
