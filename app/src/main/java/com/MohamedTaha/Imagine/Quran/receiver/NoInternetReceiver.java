package com.MohamedTaha.Imagine.Quran.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.MohamedTaha.Imagine.Quran.R;

import static com.MohamedTaha.Imagine.Quran.helper.checkConnection.NoInternetConnection.isInternet;

public class NoInternetReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (isInternet()) {
        } else {
            Toast.makeText(context, context.getString(R.string.no_internet_network), Toast.LENGTH_SHORT).show();
        }
    }
}
