package com.droid.alarmschedule;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by nikhil1804 on 14-05-2017.
 */

public class SampleBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        // check if the system finishes booting
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Toast.makeText(context, "SampleBootReceiver : Boot completed", Toast.LENGTH_LONG).show();
            // Set the alarm here again.
            Utils.scheduleAlarmManager(context, "Content body from SampleBootReceiver");
        }
    }
}
