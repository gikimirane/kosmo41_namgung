package com.study.android.service2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    MediaPlayer mp = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        mp = MediaPlayer.create(context, R.raw.sample);
        mp.seekTo(0);
        mp.start();

        Toast.makeText(context,"지정한 시간입니다.",Toast.LENGTH_LONG).show();
    }
}
