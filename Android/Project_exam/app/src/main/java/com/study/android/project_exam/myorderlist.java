package com.study.android.project_exam;

import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class myorderlist extends AppCompatActivity {
    private static final String TAG = "lecture";
    ViewPager viewPager;
    TabLayout tabLayout;
    myorderlistAdapter adapter;
    ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorderlist);

        viewPager = findViewById(R.id.container);
        tabLayout = findViewById(R.id.tabmenu);


        PageAdapter adapter = new PageAdapter(
                getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        String sUrl ="http://ec2-13-209-64-83.ap-northeast-2.compute.amazonaws.com:8081/menulist/dbController.jsp";
        HashMap<String,String> values = new HashMap<>();
        values.put("order","myorderlist");
        values.put("client",refreshedToken);
        NetworkTask networkTask = new NetworkTask(sUrl,values);
        networkTask.execute();
    }

    public void createlist(JSONObject s){
        adapter = new myorderlistAdapter(this);
        myorderlistItem item;
        String menu;
        String price;
        String code;

        listView1 = findViewById(R.id.waiting);

        try {
            JSONArray mymenu = (JSONArray) s.get("menu");
            JSONArray myprice = (JSONArray) s.get("price");
            JSONArray mycode = (JSONArray) s.get("code");

            for(int i=0;i<mymenu.length();i++){

                menu = mymenu.getString(i);
                price = myprice.getString(i);
                code = mycode.getString(i);

                item = new myorderlistItem(menu,price,code);
                adapter.addItem(item);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        listView1.setAdapter(adapter);

    }

    public class NetworkTask extends AsyncTask<Object,Void,JSONObject> {
        private String surl;
        private HashMap<String,String> values;
        StringBuffer sbParams = new StringBuffer();
        String key;
        String value;
        boolean isAnd = false;

        public NetworkTask(String url, HashMap values) {
            this.surl = url;
            this.values = values;
        }

        @Override
        protected JSONObject doInBackground(Object... params) {
            JSONObject result = null;
            RequestHttpURLConnection request = new RequestHttpURLConnection();
            result=request.jsonReturn(surl,values);
            return result;
        }

        public void onPostExecute(JSONObject s) {
            super.onPostExecute(s);

            if(s!=null){
                Toast.makeText(getApplicationContext(), "onPostExecute", Toast.LENGTH_LONG).show();
                createlist(s);
            }else {
                Log.d(TAG,"결과없음!");
                Toast.makeText(getApplicationContext(), "결과없음!!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
