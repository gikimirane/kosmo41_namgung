package com.study.android.project_exam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class orderOk extends AppCompatActivity {
    HashMap<String, String> orderlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_ok);

        Intent intent = getIntent();
        orderlist = (HashMap<String, String>) intent.getSerializableExtra("orderlist");
        Iterator<String> keys = orderlist.keySet().iterator();
    }
}
