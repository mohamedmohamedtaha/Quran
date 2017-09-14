package com.MohamedTaha.Imagine.Quran;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;

import java.util.Random;

/**
 * Created by MANASATT on 04/09/17.
 */

public class AlarmReceiver extends BroadcastReceiver {
    public int notificationId ;

    @Override
    public void onReceive(Context context, Intent intent) {
        //Get notification Manager to manage/send notification

        //Intent to invoke app when click on notification
        //In the sample, we want to start/launch this sample app when user clicks on notification
        Intent intentToRepeat = new Intent(context,MainFragmentTab.class);
        intentToRepeat.putExtra("notificationOpen",this.notificationId);

        //set flag to restart /relaunch the app
        intentToRepeat.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //Pending intent to handle launch of Activity in intent above
        PendingIntent openIntent = PendingIntent.getActivity(context,NotificationHelper.ALARM_TYPE_RTC,intentToRepeat,PendingIntent.FLAG_UPDATE_CURRENT);

        String[] toastMessages =context.getResources().getStringArray(R.array.notificationAlarm) ;
        int randomIndex = new Random().nextInt(toastMessages.length - 1 );
        //Build notification

        createNotification(context,openIntent,context.getString(R.string.app_name),toastMessages[randomIndex]);
    }
    public  NotificationCompat.Builder createNotification(Context context, PendingIntent openIntent, CharSequence ticker, CharSequence desribe){

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        Intent cancelNotification = new Intent(context,CancelNotification.class);
        cancelNotification.putExtra("notificationId",this.notificationId);
        PendingIntent exitPending = PendingIntent.getBroadcast(context,this.notificationId,cancelNotification,PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap bitmap_icon = BitmapFactory.decodeResource(context.getResources(),R.drawable.iconqoran);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.iconstanderd);
        builder.setLargeIcon(bitmap_icon);
        builder.setTicker(ticker);
        //will make it a Heads Up  Display Style
        builder.addAction(R.drawable.ic_notification_replay,context.getString(R.string.readNow),openIntent);
        builder.addAction(R.drawable.ic_cancel,context.getString(R.string.notNow),exitPending);
        builder.setContentIntent(openIntent);
        builder.setDefaults(Notification.DEFAULT_ALL);//Require VIBREATE permission
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(desribe));
        builder.setAutoCancel(true);

        notificationManager.notify(notificationId,builder.build());

        return builder;
    }
}
