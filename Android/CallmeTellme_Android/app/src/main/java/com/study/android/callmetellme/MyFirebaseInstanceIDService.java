package com.study.android.callmetellme;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "lecture";

    @Override
    public void onTokenRefresh() {
        Log.d(TAG, "onTokenRefresh() 호출됨.");
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed Token : " + refreshedToken);

        try {
            sendRegistrationToServer(refreshedToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendRegistrationToServer(String token) throws JSONException, ExecutionException, InterruptedException {
        Log.d(TAG,"저장된 ID : "+SaveSharedPreference.getMyId(this));
        if(SaveSharedPreference.getMyId(this).equals("")){
            String sUrl ="http://192.168.0.101:8081/CallmeTellme/android/updateToken";
            HashMap<String,String> values = new HashMap<>();
            values.put("userid",SaveSharedPreference.getMyId(this));
            values.put("usertoken",token);

            myNetworkTask networkTask = new myNetworkTask(sUrl,values);
            JSONObject s = networkTask.execute().get();
            String result=s.getString("result");
            Log.d(TAG,"update 완료 : "+result);
        }
    }
}
