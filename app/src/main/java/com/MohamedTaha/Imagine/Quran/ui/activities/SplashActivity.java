package com.MohamedTaha.Imagine.Quran.ui.activities;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.helper.HelperClass;
import com.MohamedTaha.Imagine.Quran.ui.fragments.SplashFragment;

import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {
    private Boolean exitApp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        SplashFragment splashFragment = new SplashFragment();
        HelperClass.replece(splashFragment, getSupportFragmentManager(), R.id.Cycle_Splash_contener, null, null);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    public void onBackPressed() {
        if (exitApp) {
            HelperClass.closeApp(getApplicationContext());
            return;
        }
        exitApp = true;
        Toast.makeText(this, getString(R.string.exit_app), Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                exitApp = false;
            }
        }, 2000);
    }
}
