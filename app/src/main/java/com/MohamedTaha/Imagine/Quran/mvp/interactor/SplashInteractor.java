package com.MohamedTaha.Imagine.Quran.mvp.interactor;

import android.app.Activity;

import com.MohamedTaha.Imagine.Quran.helper.SharedPerefrenceHelper;
import com.MohamedTaha.Imagine.Quran.mvp.presenter.SplashPresenter;
import com.MohamedTaha.Imagine.Quran.mvp.view.SplashView;

import java.util.Timer;
import java.util.TimerTask;

import static com.MohamedTaha.Imagine.Quran.ui.activities.NavigationDrawaberActivity.IS_FIRST_TIME_WAY_USING;

public class SplashInteractor implements SplashPresenter {
    private SplashView splashView;
    private Activity context;
    private static final int WAIT_TIME = 2500;
    private Timer waitTimer;

    public SplashInteractor(SplashView splashView, Activity context) {
        this.splashView = splashView;
        this.context = context;

    }

    @Override
    public void goToSlider() {
        splashView.showAnimation();
        if (!SharedPerefrenceHelper.getBooleanForWayUsing(context, IS_FIRST_TIME_WAY_USING, false)) {
            waitTimer = new Timer();
            waitTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            splashView.goToSlider();
                        }
                    });
                }
            }, WAIT_TIME);
        } else {
            splashView.goToMainActivity();
        }
    }

    @Override
    public void onDestroy() {
        splashView = null;
    }
}
