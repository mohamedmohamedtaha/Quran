package com.MohamedTaha.Imagine.Quran.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.helper.checkConnection.NetworkConnection;
import com.MohamedTaha.Imagine.Quran.service.MediaPlayerService;

import static com.MohamedTaha.Imagine.Quran.ui.activities.ListSoundReader.FragmentListSoundLLControlMedia;

public class ConnectivityReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isConnected = NetworkConnection.networkConnectivity(context);
        if (isConnected) {
            //For check from Status Network
        } else {
            Toast.makeText(context, context.getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
               /* boolean isServiceRunning = Utilities.isServiceRunning(MediaPlayerService.class.getName(), getApplicationContext());
                if (isServiceRunning) {
                    stopSelf();

                }*/
    }
}

}
