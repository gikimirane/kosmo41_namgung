package com.study.android.project_exam;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import java.util.ArrayList;

public class menulistAdapter extends BaseAdapter {
    private static final String TAG = "lecture";

    CheckBox checkbox;
    Spinner spinner;
    Context context;
    ArrayList<menulistItem> items = new ArrayList<>();

    public menulistAdapter(Context context){
        this.context = context;

    }
    public void addItem(menulistItem item){ items.add(item); }


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


        menulistItemView view = null;
        if(convertView == null){
            view = new menulistItemView(context);
        }else {
            view = (menulistItemView)convertView;
        }

        final menulistItem item = items.get(position);
        view.setDrink(item.getDrink());
        view.setAmount(item.getAmount());
        view.setCount(item.getCount());
        view.setIscheck(item.getIsCheck());

        checkbox = (CheckBox) view.findViewById(R.id.checkBox1);
       // checkbox.setChecked(((ListView)parent).isItemChecked(position));
        checkbox.setFocusable(false);
        checkbox.setClickable(false);

        return view;
    }
}
