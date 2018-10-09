package com.study.android.project_exam;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";
    ViewPager pager1;
    int count;
    int up=0,down=0,btncount=0;
    LinearLayout layout;
    Context context;
    Button btn02;
    String strEmail,strPassword,newPhone,newId,newPw;
    AlertDialog dialog;
    AlertDialog joindialog;
    CheckBox cb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logoutLayout();
        pager1 = findViewById(R.id.ViewPager1);
        pager1.setOffscreenPageLimit(5);

        MyPagerAdapter adapter = new MyPagerAdapter(this);
        pager1.setAdapter(adapter);

        if(!SaveSharedPreference.getUserName(this).equals("")){
            strEmail = SaveSharedPreference.getUserName(this);
            strPassword = SaveSharedPreference.getUserPw(this);
            Log.d(TAG,"PreparedShare id : "+strEmail+" / PW "+strPassword);
            loginCheck();
            loginLayout();
        }

    }
    public boolean onKeyBack(int keyCode,KeyEvent event){
        return super.onKeyDown(keyCode,event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP){
            up++;
            Log.d("TAG","업 :"+up);
        }
        else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
            down++;
            Log.d("TAG","다운 :"+down);
        }
        if(up==2 && down ==2 && btncount==0 && strEmail.equalsIgnoreCase("placido")){
            up=0;
            down=0;
            btncount=1;
            context = this;
            layout = findViewById(R.id.logoutlayout);
            btn02 = new Button(context);
            btn02.setText("ADMIN PAGE");
            layout.addView(btn02);
            Toast.makeText(getApplicationContext(),"ADMIN으로 진입합니다.",Toast.LENGTH_SHORT).show();
            btn02.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    webBtnClicked(v);
                }
            });
         }
        return super.onKeyDown(keyCode, event);
    }
    public void loginClicked(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_login_view, null);
        builder.setView(view);
        //builder.setCancelable(false);
        final Button submit = view.findViewById(R.id.login);
        final EditText email = view.findViewById(R.id.edittextEmailAddress);
        final EditText password = view.findViewById(R.id.edittextPassword);
        final Button cancel = view.findViewById(R.id.cancel);
        cb = view.findViewById(R.id.autologin);

        LinearLayout autolayout=view.findViewById(R.id.autolayout);
        autolayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb.isChecked()){
                    cb.setChecked(false);
                }else if(!cb.isChecked()){
                    cb.setChecked(true);
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dialog.dismiss();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                strEmail = email.getText().toString();
                strPassword = password.getText().toString();
                loginCheck();

            }
        });
        dialog = builder.create();
        dialog.show();
    }
    public void joinClicked(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_join_view, null);
        builder.setView(view);
        //builder.setCancelable(false);
        final Button submit = view.findViewById(R.id.join);
        final EditText email = view.findViewById(R.id.etid);
        final EditText password = view.findViewById(R.id.etpw);
        final EditText phone = view.findViewById(R.id.etphone);
        final Button cancel = view.findViewById(R.id.jcancel);
        joindialog = builder.create();

        cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                joindialog.dismiss();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                newId = email.getText().toString();
                newPw = password.getText().toString();
                newPhone = phone.getText().toString();
                insertDB();
            }
        });
        joindialog.show();
    }


    public void insertDB(){
        String sUrl ="http://ec2-13-209-64-83.ap-northeast-2.compute.amazonaws.com:8081/menulist/dbController.jsp";
        //String sUrl ="http://192.168.0.101:8081/menulist/dbController.jsp";
        Log.d(TAG,"sURL : "+sUrl);

        try{
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            HashMap<String, String> values = new HashMap<>();
            values.put("order","insertuser");
            values.put("userid",newId);
            values.put("userpw",newPw);
            values.put("userphone",newPhone);
            values.put("clientno",refreshedToken);
            values.put("point","5000");
            NetworkTask1 networkTask1 = new NetworkTask1(sUrl, values);
            networkTask1.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void naviClicked(View v){
        Intent intent = new Intent(getApplicationContext(),Navigator.class);
        startActivity(intent);
    }

    public void orderClicked(View v){
        Intent intent = new Intent(getApplicationContext(),order.class);
        startActivity(intent);
    }

    public void orderlistClicked(View v){
        Intent intent = new Intent(getApplicationContext(),myorderlist.class);
        startActivity(intent);
    }
    public void webBtnClicked(View v){
        Intent intent = new Intent(getApplicationContext(),webview.class);
        startActivity(intent);
    }
    public void logoutClicked(View v){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("정말 LOGOUT 하시겠습니까?")
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
                        SaveSharedPreference.clearUserName(getApplicationContext());
                        Toast.makeText(getApplicationContext(),"LOGOUT을 완료 했습니다.",Toast.LENGTH_SHORT).show();
                        logoutLayout();
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void loginCheck(){
        String sUrl ="http://ec2-13-209-64-83.ap-northeast-2.compute.amazonaws.com:8081/menulist/dbController.jsp";
        //String sUrl ="http://192.168.200.131:8081/menulist/dbController.jsp";
        Log.d(TAG,"sURL : "+sUrl);

        try{
            HashMap<String, String> values = new HashMap<>();
            values.put("order","trylogin");
            values.put("userid",strEmail);
            values.put("userpw",strPassword);

            NetworkTask networkTask = new NetworkTask(sUrl, values);
            networkTask.execute();
        }catch (Exception e){
            e.printStackTrace();
        }

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
            result=request.jsonReturn(surl,values);
            return result;
        }

        public void onPostExecute(JSONObject s) {
            super.onPostExecute(s);
            if(s!=null){
                try {
                    if(s.getString("result").equals("성공")){
                        if(SaveSharedPreference.getUserName(getApplicationContext()).length()<=0){
                            dialog.dismiss();
                            if(cb.isChecked()){
                                SaveSharedPreference.setUserName(getApplicationContext(),strEmail,strPassword);
                            }
                        }
                        Toast.makeText(getApplicationContext(), strEmail+"님! 반가워요! ", Toast.LENGTH_LONG).show();
                        loginLayout();

                        if(strEmail.equalsIgnoreCase("placido")){
                            context = getApplicationContext();
                            layout = findViewById(R.id.logoutlayout);
                            btn02 = new Button(context);
                            btn02.setText("ADMIN PAGE");
                            layout.addView(btn02);

                            btn02.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    webBtnClicked(v);
                                }
                            });
                            Toast.makeText(getApplicationContext(),"ADMIN으로 진입합니다.",Toast.LENGTH_SHORT).show();
                        }


                    }else if(s.getString("result").equals("실패")){
                        Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 확인해주세요.", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "잠시 후 다시 시도해 주세요.", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else {
                Log.d(TAG,"결과없음!");
                Toast.makeText(getApplicationContext(), "결과없음!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void loginLayout(){
        LinearLayout emplayout = findViewById(R.id.emplayout);
        emplayout.setVisibility(View.GONE);

        LinearLayout logoutlayout = findViewById(R.id.logoutlayout);
        logoutlayout.setVisibility(View.VISIBLE);
    }
    public void logoutLayout(){
        LinearLayout emplayout = findViewById(R.id.emplayout);
        emplayout.setVisibility(View.VISIBLE);

        LinearLayout logoutlayout = findViewById(R.id.logoutlayout);
        logoutlayout.setVisibility(View.GONE);
    }

    public class NetworkTask1 extends AsyncTask<Object,Void,JSONObject> {
        private String surl;
        private HashMap<String,String> values;
        //ContentValues values = new ContentValues();
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
                    if(s.getString("result").equals("성공")){
                        Toast.makeText(getApplicationContext(), "가입 완료 ! 반갑습니다 !", Toast.LENGTH_LONG).show();
                        joindialog.dismiss();
                    }else if(s.getString("result").equals("중복")){
                        Toast.makeText(getApplicationContext(), "아이디가 중복됩니다. 다시 입력해주세요.", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "잠시 후 다시 시도해 주세요.", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else {
                Log.d(TAG,"결과없음!");
                Toast.makeText(getApplicationContext(), "결과없음!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class MyPagerAdapter extends PagerAdapter {
        private LayoutInflater inflater;
        private Context context;
        public MyPagerAdapter(Context context){
            this.context = context;
        }
        private int[] images = {R.drawable.img1,
                R.drawable.img2,
                R.drawable.img3, R.drawable.img4, R.drawable.img5 };
        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            pager1.removeView((View)object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            inflater = (LayoutInflater)context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.activity_slider, container, false);
            ImageView imageView = v.findViewById(R.id.imageView);
            imageView.setImageResource(images[position]);
            container.addView(v);
            return v;
        }
    }


}
