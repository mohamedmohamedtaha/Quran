package com.MohamedTaha.Imagine.Quran.mvp.presenter;

import com.MohamedTaha.Imagine.Quran.mvp.view.ExitAppSplashView;

public interface ExitAppSplashPresenter {
    void onDestroy();

    void setView(ExitAppSplashView exitAppView);

    void exitApp();

}
