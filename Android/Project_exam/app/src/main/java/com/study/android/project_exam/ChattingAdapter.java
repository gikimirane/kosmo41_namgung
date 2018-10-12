package com.study.android.project_exam;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class ChattingAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> msgs = new ArrayList<>();

    public ChattingAdapter(Context context){
        this.context = context;

    }
    public void addItem(String name,String msg){ names.add(name); msgs.add(msg); }

    public void removeItem(String name, String msg){ names.remove(name); msgs.remove(msg);}

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) { return names.get(position); }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final String name = names.get(position);
        final String msg = msgs.get(position);

        ChattingItemView view = null;
        view = new ChattingItemView(context,name);
        view.setMyName(name);
        view.setMymsg(msg);


        return view;
    }
}
