package com.droid.alarmschedule;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import java.util.Calendar;

/**
 * Created by nikhil1804 on 14-05-2017.
 */

public class Utils {

    /**
     * Enable Boot receiver and Schedule alarm manager.
     * @param context
     * @param content
     */
    public static void scheduleNotification(Context context, String content) {
        Utils.enableBootReceiver(context);
        scheduleAlarmManager(context, content);
    }

    /**
     * Schedule alarm manager.
     * @param context
     * @param content
     */
    public static void scheduleAlarmManager(Context context, String content) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis() + (10 * 1000)); // start alarm just after 10 sec
        //calendar.set(Calendar.HOUR_OF_DAY, 1); // to set 1:00 am
        //calendar.set(Calendar.MINUTE, 3);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_HOUR, getPendingIntent(context, content)); //AlarmManager.INTERVAL_DAY
    }

    private static PendingIntent getPendingIntent(Context context, String content) {
        Intent notificationIntent = new Intent(context, AlarmReciever.class);
        notificationIntent.putExtra(AlarmReciever.CONTENT, content);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        return pendingIntent;
    }

    /**
     * Enable Boot receiver
     * @param context
     */
    private static void enableBootReceiver(Context context) {
        ComponentName receiver = new ComponentName(context, SampleBootReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }
}
