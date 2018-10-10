package com.study.android.project_exam;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class order extends AppCompatActivity {
    private static final String TAG = "lecture";
    menulistAdapter adapter;
    TextView textView1;
    ListView listView1;
    Spinner spinner;
    HashMap<String,String> cb = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        listView1 = findViewById(R.id.listview1);

        String sUrl ="http://ec2-13-209-64-83.ap-northeast-2.compute.amazonaws.com:8081/Jsp28/dbController.jsp";
        //String sUrl ="http://192.168.200.131:8081/menulist/dbController.jsp";
        Log.d(TAG,"sURL : "+sUrl);

        try{
            HashMap<String, String> values = new HashMap<>();
            values.put("order","menu");

            NetworkTask networkTask = new NetworkTask(sUrl, values);
            networkTask.execute();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void codeBtnClicked(View v){

        if(cb.size()>0){
            AlertDialog.Builder builder = new AlertDialog.Builder(order.this);
            builder.setMessage("주문할 메뉴를 모두 체크하셨습니까?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setCancelable(true)
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id){
                            Toast.makeText(getApplicationContext(),"미구현~! nono",Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(getApplicationContext(),orderOk.class);
                            intent.putExtra("orderlist",cb);
                            startActivity(intent);
                            dialog.cancel();
                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(order.this);
            builder.setMessage("주문할 메뉴를 체크하세요.")

                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();
        }
    }
    public void menulist(JSONObject s){
        adapter = new menulistAdapter(this);
        menulistItem item;
        String menulist;
        String price;

        try {
            JSONArray menu = (JSONArray) s.get("menu");
            JSONArray amount = (JSONArray) s.get("amount");

            for(int i=0;i<menu.length();i++){

                menulist = menu.getString(i);
                price = amount.getString(i);

                item = new menulistItem(menulist,price);
                adapter.addItem(item);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        listView1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                final menulistItem item = (menulistItem) adapter.getItem(position);
                final CheckBox checkBox = arg1.findViewById(R.id.checkBox1);
                final Spinner spinner = arg1.findViewById(R.id.spinner);

                //클릭 이벤트가 있을 때의 스피너 값을 다시 재 셋팅
                item.setCount(spinner.getSelectedItem().toString());
                adapter.notifyDataSetChanged();

                //체크가 트루상태이면 클릭 들어왔을때 off 해야 하니까..
                if (checkBox.isChecked()) {
                    cb.remove(item.getDrink());
                    item.setCount("0");
                    spinner.setSelection(0);
                    item.setIscheck(false);
                    checkBox.setChecked(false);
                    Log.d(TAG,"아이템.getCount : "+item.getCount());

                    //체크가 해제되어 있어야 체크로 시켜주기 위해 체크
                } else if(!checkBox.isChecked()){
                    if(spinner.getSelectedItem().toString().equals("0")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(order.this);
                        builder.setMessage("수량을 선택하세요.")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        checkBox.setChecked(false);
                                    }
                                });

                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                    else if(!spinner.getSelectedItem().toString().equals("0")){
                        cb.remove(item.getDrink()); //지우고
                        checkBox.setChecked(true);
                        item.setIscheck(true);
                        cb.put(item.getDrink(), spinner.getSelectedItem().toString()+"|"+item.getAmount()); //다시넣고
                    }
                }
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        final menulistItem item = (menulistItem) adapter.getItem(position);
                        Log.d(TAG,"찍혀라 얍얍");
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });
    }


    public class NetworkTask extends AsyncTask<Object,Void,JSONObject> {

        private String surl;
        private HashMap<String,String> values;
        //ContentValues values = new ContentValues();
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
            result = request.jsonReturn(surl,values);
            return result;
        }

        public void onPostExecute(JSONObject s) {
            super.onPostExecute(s);
            if(s!=null){
                try {
                    Log.d(TAG, "s : " + s.getString("menu"));
                    Toast.makeText(getApplicationContext(), "주문 메뉴를 선택 후 HAPPY CODE를 누르세요. ", Toast.LENGTH_LONG).show();
                    menulist(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else {
                Log.d(TAG,"결과없음!");
                Toast.makeText(getApplicationContext(), "결과없음!!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
