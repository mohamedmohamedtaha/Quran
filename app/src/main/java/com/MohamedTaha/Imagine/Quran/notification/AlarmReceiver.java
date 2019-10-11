package com.MohamedTaha.Imagine.Quran.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.ui.activities.NavigationDrawaberActivity;

import java.util.Random;

/**
 * Created by MANASATT on 04/09/17.
 */

public class AlarmReceiver extends BroadcastReceiver  {
    public int notificationId;
    private static final String CHANNEL_ID = "com.MohamedTaha.Imagine.Quran.notification";
    public static final String NOTIFICATION_ID= "notificationOpen";

    @Override
    public void onReceive(Context context, Intent intent) {
        //Get notification Manager to manage/send notification

        //Intent to invoke app when click on notification
        //In the sample, we want to start/launch this sample app when user clicks on notification
        Intent intentToRepeat = new Intent(context, NavigationDrawaberActivity.class);
        intentToRepeat.putExtra(NOTIFICATION_ID, this.notificationId);

        //set flag to restart /relaunch the app
        intentToRepeat.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //Pending intent to handle launch of Activity in intent above
        PendingIntent openIntent = PendingIntent.getActivity(context, NotificationHelper.ALARM_TYPE_RTC,
                intentToRepeat, PendingIntent.FLAG_UPDATE_CURRENT);

        String[] toastMessages = context.getResources().getStringArray(R.array.notificationAlarm);
        int randomIndex = new Random().nextInt(toastMessages.length - 1);
        //Build notification

        createNotification(context, openIntent, context.getString(R.string.app_name), toastMessages[randomIndex]);
    }

    public NotificationCompat.Builder createNotification(Context context, PendingIntent openIntent, CharSequence ticker, CharSequence desribe) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //you only need to create  the cnannel on API 26+ devices
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel(context);
        }
        Intent cancelNotification = new Intent(context, CancelNotification.class);
        cancelNotification.putExtra(NOTIFICATION_ID, this.notificationId);
        PendingIntent exitPending = PendingIntent.getBroadcast(context, this.notificationId, cancelNotification, PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap bitmap_icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.iconqoran);
        //Create a new Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.iconstanderd);
        builder.setLargeIcon(bitmap_icon);
        builder.setTicker(ticker);
        builder.setContentText(desribe);
        //For sound
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(alarmSound);

        builder.setWhen(System.currentTimeMillis());  // the time stamp
        builder.setChannelId(CHANNEL_ID);
        // Make the transport controls visible on the lock screen
        builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        //Set the notification color
        builder.setColor(ContextCompat.getColor(context.getApplicationContext(), R.color.colorPrimaryDark));

        //will make it a Heads Up  Display Style
        builder.addAction(R.drawable.ic_notification_replay, context.getString(R.string.readNow), openIntent);
        builder.addAction(R.drawable.ic_cancel, context.getString(R.string.notNow), exitPending);
        builder.setContentIntent(openIntent);
        builder.setDefaults(Notification.DEFAULT_ALL);//Require VIBREATE permission
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(desribe));
        builder.setAutoCancel(true);
        notificationManager.notify(notificationId, builder.build());

        return builder;
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private void createChannel(Context context) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //The id of the channel
        //for create Channel for notification for Android O
        //The user visible name of the channel
        CharSequence name = "Quran Notification";
        //The user visible description of the channel
        String description = "Quran Notification controls";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel notificationChannel2 = new NotificationChannel(CHANNEL_ID, name, importance);
        //Configure the channel's intial preference
        notificationChannel2.setLightColor(Color.GREEN);
        //Configure the notification channel.
        notificationChannel2.setDescription(description);
        notificationChannel2.setShowBadge(false);
        notificationChannel2.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        manager.createNotificationChannel(notificationChannel2);

    }
}
