package com.MohamedTaha.Imagine.Quran;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.SystemClock;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by MANASATT on 04/09/17.
 */

public class NotificationHelper {
    public static int ALARM_TYPE_RTC = 100;

    public static int ALARM_TYPE_ELAPSED = 101;
    private static AlarmManager alarmManager;
    private static PendingIntent alarmPendingIntent;

    public static void sendNotificationEveryHalfDay(Context context){
        //Setting intent to class where notification will be handled
        Intent intent = new Intent(context, AlarmReceiver.class);

        //Setting pending intent to respond to broadcast sent by AlarmManager every day at 8am
        alarmPendingIntent = PendingIntent.getBroadcast(context,ALARM_TYPE_ELAPSED,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        //getting instance of AlarmManager service
        alarmManager  = (AlarmManager)context.getSystemService(ALARM_SERVICE);

        //Inexact alarm everyday since device is booted up. This is a better choise and
        //scales well when device time settings/locale is changed
        //we are setting alarm tofire notificationafter 15 minutes , and every 15 minutes there on
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_HALF_DAY,
                AlarmManager.INTERVAL_HALF_DAY, alarmPendingIntent);
    }

     //Enable boot receiver to persist alarms set for notification across device reboots
    public static void enableBootRecieiver(Context context){
        ComponentName receiver = new ComponentName(context,AlarmBootRecevier.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }
      }
