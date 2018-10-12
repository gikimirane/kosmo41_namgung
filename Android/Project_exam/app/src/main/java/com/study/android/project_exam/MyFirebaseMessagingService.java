package com.study.android.project_exam;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "lecture";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "onMessageReceived() 호출됨.");

        Log.d(TAG, "From: " + remoteMessage.getFrom());
        String from = remoteMessage.getFrom();

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Map<String, String> data = remoteMessage.getData();
            Log.d(TAG, "Message data payload: " + data.toString());

            String contents = data.get("message");
            String title = data.get("title");

            sendNotification(contents,title);
            Log.d(TAG,"message data2 : "+contents);
            //sendToActivity(getApplicationContext(), contents);

        }

        //Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            String notiTitle = remoteMessage.getNotification().getTitle();
            String notiBody = remoteMessage.getNotification().getBody();
            Log.d(TAG, "Message Notification Title: " + notiTitle);
            Log.d(TAG, "Message Notification Body: " + notiBody);

            //sendToActivity(getApplicationContext(), notiString);
            sendNotification(notiBody,notiTitle);
        }
    }

    public void sendNotification(String body,String title){
        String channelId = "channel";
        String channelName = "Channel Name";

        if(body.contains("완료")){
            MainActivity.info.setUsecount(MainActivity.info.getUsecount()+1);
        }

        NotificationManager notifManager
                = (NotificationManager) getSystemService  (Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notifManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext(), channelId);

        Intent notificationIntent = new Intent(getApplicationContext()
                , MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);
        int requestID = (int) System.currentTimeMillis();
        PendingIntent pendingIntent
                = PendingIntent.getActivity(getApplicationContext()
                , requestID
                , notificationIntent
                , PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentTitle(title) // required
                .setContentText(body)  // required
                .setDefaults(Notification.DEFAULT_ALL) // 알림, 사운드 진동 설정
                .setAutoCancel(true) // 알림 터치시 반응 후 삭제
                .setSound(RingtoneManager
                        .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setSmallIcon(android.R.drawable.btn_star)
                .setLargeIcon(BitmapFactory.decodeResource(getResources()
                        , android.R.drawable.btn_star))
                .setBadgeIconType(android.R.drawable.btn_star)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(body))
                .setPriority(Notification.PRIORITY_MAX)
                .setContentIntent(pendingIntent)
                ;

        notifManager.notify(0, builder.build());
        Log.d(TAG,"notification 얍얍");
    }

    private void sendToActivity(Context context, String contents) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("message", contents);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }
/*
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void sendNotification(String messageBody) {

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationManager nm = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);

        Resources res = this.getResources();
        Notification.Builder builder = new Notification.Builder(this);

        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.ic_launcher_foreground_1)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.ic_launcher_background))
                .setTicker("is Ticker")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle("is Title")
                .setContentText("is Body");
        Notification n = builder.build();

        nm.notify(0, n);



        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("!!!!!!!!FCM Message!!!!!!")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());


    }*/
}
