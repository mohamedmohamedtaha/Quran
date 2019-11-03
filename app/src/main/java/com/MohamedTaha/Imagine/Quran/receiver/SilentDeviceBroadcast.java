package com.MohamedTaha.Imagine.Quran.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.appcompat.app.AlertDialog;

import com.MohamedTaha.Imagine.Quran.service.MediaPlayerService;

public class SilentDeviceBroadcast {
    //Broadcast for notify silent device
    private BroadcastReceiver silentDevice = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
         //   if (checkSilentDevice()) {
               AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("تَنْبِيهٌ");
                builder.setMessage("الجِهَازُ رُبَّمَا يَكُونُ فِي وَضْعٍ الهَزَّازَ أَوْ الكَتْمُ هَلْ تُرِيدُ تَشْغِيلَ الصَّوْتِ؟");
                builder.setCancelable(false);
                builder.setPositiveButton("مُوَافِقٌ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //playMedia();
                        //buildNotification(PlaybackStatus.PLAYING);


                        //page3. play music
                      /*  if (exStore != null && exStore.exists()) {
                            playAyaFromEnternal(exStore);

                        } else {
                            //________________________________________________
                            //Play Sora in the Enternet If was Not There in Internal Storage
                            //  play();
                        }*/
                    }
                });
                builder.setNegativeButton("لَا", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
                builder.show();
            }


    //}
    };
    private void registerSilentDevice(){
        IntentFilter intentFilter = new IntentFilter(MediaPlayerService.Broadcast_SILENT_DEVICE);
      //  registerReceiver(silentDevice,intentFilter);
    }



}
