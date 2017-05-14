package com.droid.alarmschedule;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by nikhil1804 on 13-05-2017.
 */

public class AlarmReciever extends BroadcastReceiver {

    public static String NOTIFICATION_ID = "notification-id";
    public static String NOTIFICATION = "notification";
    public static String CONTENT = "content";

    @Override
    public void onReceive(Context context, Intent intent) {
        // Show the toast  like in above screen shot
//        Toast.makeText(context, "Alarm Triggered and SMS Sent", Toast.LENGTH_LONG).show();

        int groupId = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
        showNotification(context, intent, groupId, 1);
//        showNotification(context, intent, groupId, 2);
//        showNotification(context, intent, groupId, 3);
    }

    private void showNotification(Context context, Intent intent, int groupId, int itemId) {
        String content = intent.getStringExtra(CONTENT);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("Scheduled Notification");
        builder.setContentText(content + groupId + "." + itemId);
        builder.setSmallIcon(R.drawable.ic_launcher);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            builder.setGroup(groupId + "");
        }
        Notification notification = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notification = builder.build();
        } else {
            notification = builder.getNotification();
        }

        if (notification != null) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context
                    .NOTIFICATION_SERVICE);
            notificationManager.notify(groupId + itemId, notification);
        } else {
            Toast.makeText(context, "Alarm Triggered " + groupId, Toast.LENGTH_LONG).show();
        }
    }
}
