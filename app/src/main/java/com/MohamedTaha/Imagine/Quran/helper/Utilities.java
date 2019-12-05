package com.MohamedTaha.Imagine.Quran.helper;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;

/**
 * Created by MANASATT on 15/06/17.
 */

public class Utilities {
    public String milliSecondsToTimer(long milliSeconds) {
        String finalTimerString = "";
        String secondsString = "";

        int hours = (int) (milliSeconds / (1000 * 60 * 60));
        int minutes = (int) (milliSeconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliSeconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        if (hours > 0) {
            finalTimerString = hours + ":";
        }
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }
        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        return finalTimerString;

    }

    public int getProgressPercentage(long currentDuration, long totalDuration) {
        Double percentage = (double) 0;
        long currentSeconds = (int) (currentDuration / 1000);
        long totalSeconds = (int) (totalDuration / 1000);
        percentage = (((double) currentSeconds) / totalSeconds) * 100;
        return percentage.intValue();
    }

    public int progressToTimer(int progress, int totalDuration) {
        int currentDuration = 0;
        totalDuration = (int) (totalDuration / 1000);
        currentDuration = (int) ((((double) progress) / 100) * totalDuration);

        return currentDuration * 1000;

    }

    //Check if Service is running or not
    public static boolean isServiceRunning(String serviceName, Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo serviceInfo : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceName.equals(serviceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean currentVersionSupportBigNotification() {
        int sdkVersion = Build.VERSION.SDK_INT;
        if (sdkVersion >= Build.VERSION_CODES.JELLY_BEAN) {
            return true;
        }
        return false;
    }

    public static boolean currentVersionSupportLockScreenControls() {
        int sdkVersion = Build.VERSION.SDK_INT;
        if (sdkVersion >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return true;
        }
        return false;
    }
}


















