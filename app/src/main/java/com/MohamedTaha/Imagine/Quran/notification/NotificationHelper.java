package com.MohamedTaha.Imagine.Quran.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.preference.PreferenceManager;

import com.MohamedTaha.Imagine.Quran.R;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by MANASATT on 04/09/17.
 */

public class NotificationHelper {
    public static int ALARM_TYPE_RTC = 100;
    public static int ALARM_TYPE_ELAPSED = 101;
    private static AlarmManager alarmManager;
    private static PendingIntent alarmPendingIntent;

    public static void sendNotificationEveryHalfDay(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        //getString Retrieve a String value from the Preference
        String repear = sharedPreferences.getString(context.getString(R.string.settings_Notification_key),
                context.getString(R.string.settings_Notification_default));

        //Setting intent to class where notification will be handled
        Intent intent = new Intent(context, AlarmReceiver.class);

        //Setting pending intent to respond to broadcast sent by AlarmManager every day at 8am
        alarmPendingIntent = PendingIntent.getBroadcast(context, ALARM_TYPE_ELAPSED, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //getting instance of AlarmManager service
        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        //Inexact alarm everyday since device is booted up. This is a better choise and
        //scales well when device time preference/locale is changed
        //we are setting alarm to fire notification after 15 minutes , and every 15 minutes there on

        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + setAlarm(repear),
                setAlarm(repear), alarmPendingIntent);
    }

    private static Long setAlarm(String key) {
        Long alarm = 0L ;
        switch (key) {
            case "AlarmManager.INTERVAL_FIFTEEN_MINUTES":
                alarm =  AlarmManager.INTERVAL_FIFTEEN_MINUTES;
            break;
            case "AlarmManager.INTERVAL_HALF_HOUR":
                alarm =  AlarmManager.INTERVAL_HALF_HOUR;
            break;
            case "AlarmManager.INTERVAL_HOUR":
                alarm =  AlarmManager.INTERVAL_HOUR;
            break;
            case "AlarmManager.INTERVAL_HALF_DAY":
                alarm =  AlarmManager.INTERVAL_HALF_DAY;
            break;
            case "AlarmManager.INTERVAL_DAY":
                alarm =  AlarmManager.INTERVAL_DAY;
            break;
            default:
        }
        return alarm;
    }

    //Enable boot receiver to persist alarms set for notification across device reboots
    public static void enableBootRecieiver(Context context) {
        ComponentName receiver = new ComponentName(context, AlarmBootRecevier.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }
}