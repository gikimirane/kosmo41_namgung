package com.study.android.callhooking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsMessage;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MySMSReceiver extends BroadcastReceiver {
    private static final String TAG = "lecture";
    private static String mLastState;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"수신!!");
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if (state.equals(mLastState)) {
            return;

        } else {
            mLastState = state;

        }
        if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            final String phone_number = PhoneNumberUtils.formatNumber(incomingNumber);
            Log.d(TAG,"발신번호 : "+phone_number);
        }
    }
}
