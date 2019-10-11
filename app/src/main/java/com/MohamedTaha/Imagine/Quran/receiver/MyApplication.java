package com.MohamedTaha.Imagine.Quran.receiver;

import android.app.Application;

public class MyApplication extends Application {
    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }
    public static synchronized MyApplication getInstance(){
        return myApplication;
    }
    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener){
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
