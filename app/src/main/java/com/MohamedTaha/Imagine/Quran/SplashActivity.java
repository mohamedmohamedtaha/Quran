package com.MohamedTaha.Imagine.Quran;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private static final int WAIT_TIME = 2500 ;
    private Timer waitTimer ;
    TextView textShow;
    Typeface nyTypeface ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

         NotificationHelper.sendNotificationEveryHalfDay(getApplicationContext());
           NotificationHelper.enableBootRecieiver(getApplicationContext());
      //  startService(new Intent(SplashActivity.this,ServiceToast.class));

        textShow = (TextView) findViewById(R.id.textShow);
        textShow.setTypeface(nyTypeface);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.aminmation_splash);
        textShow.startAnimation(animation);

        waitTimer = new Timer();
        waitTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                SplashActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startSplashActivity();
                    }
                });
            }
        },WAIT_TIME);
    }
    private void  startSplashActivity(){
        startActivity(new Intent(this,MainFragmentTab.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        //
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        nyTypeface = Typeface.createFromAsset(getAssets(),"fonts/Mirza-SemiBold.ttf");
    }


}

























