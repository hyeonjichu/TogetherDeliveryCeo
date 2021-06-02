package com.example.togetherdeliveryceo;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    protected static final String FCMTAG = "[FCM]";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(FCMTAG, "is called111111111111111111111111111111111111111111111111");

        String msg, title;
        msg = remoteMessage.getNotification().getBody();
        title = remoteMessage.getNotification().getTitle();

        Notification.Builder noti = new Notification.Builder(this)
                .setContentTitle("111111111111111111111111111111111111111new push from :"+title)
                .setContentText(msg)
                .setSmallIcon(R.mipmap.ic_launcher);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,noti.build());
    }
}
