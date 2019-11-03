package com.MohamedTaha.Imagine.Quran.view;

import android.content.Intent;

public interface NavigationDrawarView {
    void showMessageExitApp();

    void exitApp();

    void getDefault();

    void getShareApp(Intent intent);

    void getSendUs(Intent intentEmail);

    void getRateApp(Intent rateApp);

}
