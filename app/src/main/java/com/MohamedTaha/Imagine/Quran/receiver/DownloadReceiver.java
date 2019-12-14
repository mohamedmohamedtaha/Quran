package com.MohamedTaha.Imagine.Quran.receiver;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.helper.HelperClass;

import static android.content.Context.DOWNLOAD_SERVICE;

public class DownloadReceiver extends BroadcastReceiver {
    DownloadManager downloadManager;
    private  long downloadReference ;

    public DownloadReceiver(long downloadReference, Context context) {
        this.downloadReference = downloadReference;
        downloadManager = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Long  broadCastDownload = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
        if (broadCastDownload == downloadReference){
            if (getDownloadStatus() == DownloadManager.STATUS_SUCCESSFUL){
                HelperClass.customToast((Activity) context, context.getString(R.string.download_successful));
            }else {
                HelperClass.customToast((Activity) context, context.getString(R.string.download_field));
            }
        }
    }

    private int getDownloadStatus(){
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(downloadReference);
        Cursor cursor = downloadManager.query(query);
        if (cursor.moveToFirst()){
            int columIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
            int status = cursor.getInt(columIndex);
            return status;

        }
        return DownloadManager.ERROR_UNKNOWN;
    }

}
