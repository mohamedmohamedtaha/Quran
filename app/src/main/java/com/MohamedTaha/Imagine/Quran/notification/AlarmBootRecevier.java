package com.MohamedTaha.Imagine.Quran.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by MANASATT on 04/09/17.
 */

public class AlarmBootRecevier extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            //only enabling one type of notification
            NotificationHelper.sendNotificationEveryHalfDay(context);
        }
    }
}