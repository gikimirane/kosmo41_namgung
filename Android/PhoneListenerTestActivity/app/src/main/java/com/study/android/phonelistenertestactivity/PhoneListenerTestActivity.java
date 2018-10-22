package com.study.android.phonelistenertestactivity;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class PhoneListenerTestActivity extends Activity {

    PhoneStateCheckListener phoneCheckListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.newactivity);
        phoneCheckListener = new PhoneStateCheckListener(this);

        TelephonyManager telephonyManager  =

                (TelephonyManager)getSystemService(TELEPHONY_SERVICE);

        telephonyManager.listen(phoneCheckListener,

                PhoneStateListener.LISTEN_CALL_STATE);

    }



    public class PhoneStateCheckListener extends PhoneStateListener {

        PhoneListenerTestActivity mainActivity;

        PhoneStateCheckListener(PhoneListenerTestActivity _main){

            mainActivity = _main;

        }


        @Override

        public void onCallStateChanged(int state, String incomingNumber) {

            if (state == TelephonyManager.CALL_STATE_IDLE) {

                Toast.makeText(mainActivity,"STATE_IDLE : Incoming number "

                        + incomingNumber,Toast.LENGTH_SHORT).show();

            } else if (state == TelephonyManager.CALL_STATE_RINGING) {

                Toast.makeText(mainActivity,"STATE_RINGING : Incoming number "

                        + incomingNumber,Toast.LENGTH_SHORT).show();


            } else if (state == TelephonyManager.CALL_STATE_OFFHOOK) {

                Toast.makeText(mainActivity,"STATE_OFFHOOK : Incoming number "

                        + incomingNumber,Toast.LENGTH_SHORT).show();

            }

        }

    }

}