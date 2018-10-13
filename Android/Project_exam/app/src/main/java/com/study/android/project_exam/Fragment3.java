package com.study.android.project_exam;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Fragment3 extends Fragment {

    private static final String TAG = "lecture";
    ListView listview1;
    myfinishAdapter adapter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView =
                (ViewGroup)inflater.inflate(R.layout.fragment3,container,false);
        listview1 = rootView.findViewById(R.id.finish);
        adapter = new myfinishAdapter(getContext());
        requestdata();

        return rootView;
    }


    public void requestdata(){
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        String sUrl ="http://ec2-13-209-64-83.ap-northeast-2.compute.amazonaws.com:8081/Jsp28/dbController.jsp";
        HashMap<String,String> values = new HashMap<>();
        values.put("order","myfinish");
        values.put("userid",MainActivity.info.getId());
        NetworkTask networkTask = new NetworkTask(sUrl,values);
        networkTask.execute();
    }


    public void createlist(JSONObject s){

        myfinishlistItem item;
        String menu;
        String price;
        String code;

        try {
            JSONArray mymenu = (JSONArray) s.get("menu");
            JSONArray myprice = (JSONArray) s.get("price");
            JSONArray mycode = (JSONArray) s.get("code");

            for(int i=0;i<mymenu.length();i++){

                menu = mymenu.getString(i);
                price = myprice.getString(i);
                code = mycode.getString(i);

                item = new myfinishlistItem(menu,price,code);
                adapter.addItem(item);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        listview1.setAdapter(adapter);
        listview1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                myfinishlistItem item = (myfinishlistItem) adapter.getItem(position);
                final String mycode = item.getCode();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setMessage("내역을 삭제하시겠습니까?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setCancelable(true)
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id){
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                                deleteorder(mycode);
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            }
        });

    }
    public void deleteorder(String mycode){
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        String sUrl ="http://ec2-13-209-64-83.ap-northeast-2.compute.amazonaws.com:8081/Jsp28/dbController.jsp";
        HashMap<String,String> values = new HashMap<>();
        values.put("order","deleteorder");
        values.put("code",mycode);
        NetworkTask1 NetworkTask1 = new NetworkTask1(sUrl,values);
        NetworkTask1.execute();
    }

    private void refresh(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }

    public class NetworkTask1 extends AsyncTask<Object,Void,JSONObject> {
        private String surl;
        private HashMap<String,String> values;
        StringBuffer sbParams = new StringBuffer();
        String key;
        String value;
        boolean isAnd = false;

        public NetworkTask1(String url, HashMap values) {
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
                    String result=s.getString("result");
                    if(result.equals("성공")){
                        refresh();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else {
                Log.d(TAG,"결과없음!");

            }
        }
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
                //Toast.makeText(getApplicationContext(), "onPostExecute", Toast.LENGTH_LONG).show();
                Log.d(TAG,"fragment : onPostExecute");
                createlist(s);
            }else {
                Log.d(TAG,"결과없음!");
                //Toast.makeText(getApplicationContext(), "결과없음!!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
