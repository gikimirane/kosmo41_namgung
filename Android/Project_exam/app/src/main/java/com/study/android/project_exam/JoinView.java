package com.study.android.project_exam;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class JoinView extends AppCompatActivity {
    private static final String TAG = "lecture";
    EditText etid;
    EditText etpw;
    EditText etphone;
    int point=0;
    String refreshedToken;
    String id,pw,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_view);
        etid = findViewById(R.id.etid);
        etpw = findViewById(R.id.etpw);
        etphone = findViewById(R.id.etphone);
        refreshedToken = FirebaseInstanceId.getInstance().getToken();
    }

    public void joinBtnClicked(View v){
        id = etid.getText().toString();
        pw = etpw.getText().toString();
        phone = etphone.getText().toString();
        if(!id.contains("@")){
            AlertDialog.Builder builder = new AlertDialog.Builder(JoinView.this);
            builder.setMessage("EMAIL 형식이 아닙니다. 다시 입력해 주세요.")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setCancelable(false)
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.cancel();
                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();
        }else if(id.length()!=0 && pw.length()!=0 && phone.length()!=0){
            AlertDialog.Builder builder = new AlertDialog.Builder(JoinView.this);
            builder.setMessage(id+"가 맞습니까?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setCancelable(false)
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            //DBINSERT 시키자
                            insertDB();
                            dialog.cancel();
                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();

        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(JoinView.this);
            builder.setMessage("아이디, 비밀번호, 전화번호를 모두 입력해주세요.")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setCancelable(true)
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    public void insertDB(){

        String sUrl ="http://ec2-13-209-64-83.ap-northeast-2.compute.amazonaws.com:8081/menulist/dbController.jsp";
        //String sUrl ="http://192.168.0.101:8081/menulist/dbController.jsp";
        Log.d(TAG,"sURL : "+sUrl);

        try{
            HashMap<String, String> values = new HashMap<>();
            values.put("order","insertuser");
            values.put("userid",id);
            values.put("userpw",pw);
            values.put("userphone",phone);
            values.put("clientno",refreshedToken);
            values.put("point","5000");

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
                        Toast.makeText(getApplicationContext(), "가입 완료 ! 반갑습니다 !", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);

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
}
