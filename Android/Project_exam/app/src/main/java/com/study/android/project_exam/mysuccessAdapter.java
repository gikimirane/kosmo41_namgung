package com.study.android.project_exam;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class mysuccessAdapter extends BaseAdapter {
    private static final String TAG = "lecture";

    Context context;
    ArrayList<mysuccesslistItem> items = new ArrayList<>();

    public mysuccessAdapter(Context context){
        this.context = context;

    }
    public void addItem(mysuccesslistItem item){ items.add(item); }


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

        mysuccesslistItemView view = null;
        if(convertView == null){
            view = new mysuccesslistItemView(context);
        }else {
            view = (mysuccesslistItemView)convertView;
        }

        final mysuccesslistItem item = items.get(position);
        view.setCode(item.getCode());
        view.setPrice(item.getPrice());
        view.setMenu(item.getMenu());

        return view;
    }
}
