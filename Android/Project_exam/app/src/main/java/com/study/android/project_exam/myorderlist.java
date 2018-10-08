package com.study.android.project_exam;

import android.app.FragmentTransaction;
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
    PageAdapter adapter;
    ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorderlist);
        requestdata();

        viewPager = findViewById(R.id.container);
        tabLayout = findViewById(R.id.tabmenu);

        adapter = new PageAdapter(
                getSupportFragmentManager(), tabLayout.getTabCount());

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



        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                adapter.notifyDataSetChanged();
                Log.d(TAG,"페이지 변경11!");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                adapter.notifyDataSetChanged();
                Log.d(TAG,"페이지 변경!");
            }
        });
    }


    public void requestdata() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        String sUrl ="http://ec2-13-209-64-83.ap-northeast-2.compute.amazonaws.com:8081/menulist/dbController.jsp";
        HashMap<String,String> values = new HashMap<>();
        values.put("order","sendpush");
        values.put("client",refreshedToken);
        NetworkTask networkTask = new NetworkTask(sUrl,values);
        networkTask.execute();
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
                try {
                    Log.d(TAG,"결과있음");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else {
                Log.d(TAG,"결과없음!");
                //Toast.makeText(getApplicationContext(), "결과없음!!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
