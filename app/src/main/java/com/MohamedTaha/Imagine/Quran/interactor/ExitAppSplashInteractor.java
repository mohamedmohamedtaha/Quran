package com.MohamedTaha.Imagine.Quran.interactor;

import android.content.Context;
import android.os.Handler;

import com.MohamedTaha.Imagine.Quran.presenter.ExitAppSplashPresenter;
import com.MohamedTaha.Imagine.Quran.view.ExitAppSplashView;
public class ExitAppSplashInteractor implements ExitAppSplashPresenter {
    private ExitAppSplashView exitAppView;
    private Boolean exitApp = false;

    public ExitAppSplashInteractor() {
    }

    @Override
    public void onDestroy() {
        this.exitAppView = null;
    }

    @Override
    public void setView(ExitAppSplashView exitAppView) {
        this.exitAppView = exitAppView;
    }

    @Override
    public void exitApp() {


        if (exitApp) {
            exitAppView.exitApp();
            return;
        }
        exitApp = true;
        exitAppView.showMessageExitApp();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                exitApp = false;
            }
        }, 2000);
    }
}