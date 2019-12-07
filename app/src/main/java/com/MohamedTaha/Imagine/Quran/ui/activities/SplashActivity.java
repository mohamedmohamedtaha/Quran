package com.MohamedTaha.Imagine.Quran.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.helper.HelperClass;
import com.MohamedTaha.Imagine.Quran.mvp.interactor.ExitAppSplashInteractor;
import com.MohamedTaha.Imagine.Quran.mvp.presenter.ExitAppSplashPresenter;
import com.MohamedTaha.Imagine.Quran.ui.fragments.SplashFragment;
import com.MohamedTaha.Imagine.Quran.mvp.view.ExitAppSplashView;

import butterknife.BindString;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements ExitAppSplashView {
    ExitAppSplashPresenter presenter;
    @BindString(R.string.exit_app)
    String exit_app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        presenter = new ExitAppSplashInteractor();
        SplashFragment splashFragment = new SplashFragment();
        HelperClass.replece(splashFragment, getSupportFragmentManager(), R.id.Cycle_Splash_contener);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onBackPressed() {
        presenter.exitApp();
    }

    @Override
    public void showMessageExitApp() {
        HelperClass.customToast(this, exit_app);
    }

    @Override
    public void exitApp() {
        HelperClass.closeApp(getApplicationContext());
    }
}