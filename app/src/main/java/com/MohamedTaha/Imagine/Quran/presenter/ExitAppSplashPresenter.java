package com.MohamedTaha.Imagine.Quran.presenter;

import com.MohamedTaha.Imagine.Quran.view.ExitAppSplashView;

public interface ExitAppSplashPresenter {
    void onDestroy();

    void setView(ExitAppSplashView exitAppView);

    void exitApp();

}
