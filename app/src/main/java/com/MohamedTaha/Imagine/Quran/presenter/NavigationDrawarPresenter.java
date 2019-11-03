package com.MohamedTaha.Imagine.Quran.presenter;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public interface NavigationDrawarPresenter {
    void onDestroy();

    void exitApp(MaterialSearchView searchView, BottomNavigationView navView);

    void shareApp(String aboutString, String appPackageName);

    void sendUs();

    void actionRate(String appPackageName);

}
