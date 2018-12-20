package com.study.android.callmetellme;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class CallReceiver extends BroadcastReceiver {
    private static final String TAG = "lecture";
    private static String mLastState;


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"수신!!");
        String action= intent.getAction();
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if (state.equals(mLastState)) {
            return;
        } else {
            mLastState = state;
        }
        if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
            Log.d(TAG,"콜 수신!");
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            final String phone_number = PhoneNumberUtils.formatNumber(incomingNumber);

            if(SaveSharedPreference.getStatus(context)){
                MainActivity.RequestHttp(phone_number);
                Log.d(TAG,"HTTP 통신 고고");
            }
        }

        if( action.equals("android.intent.action.BOOT_COMPLETED") ){
            Intent serviceIntent = new Intent(context, CallingService.class);
            context.startService(serviceIntent);
            Log.d(TAG,"service 실행");
        }
    }
}
