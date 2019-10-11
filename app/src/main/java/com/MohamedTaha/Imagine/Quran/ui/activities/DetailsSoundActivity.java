package com.MohamedTaha.Imagine.Quran.ui.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.getData.Utilities;
import com.MohamedTaha.Imagine.Quran.helper.util.PlayerConstants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.MohamedTaha.Imagine.Quran.service.MediaPlayerService.activeAudio;
import static com.MohamedTaha.Imagine.Quran.service.MediaPlayerService.transportControls;
import static com.MohamedTaha.Imagine.Quran.ui.activities.ListSoundReader.IsPlay;

public class DetailsSoundActivity extends AppCompatActivity /*implements SeekBar.OnSeekBarChangeListener*/ {
    @BindView(R.id.btnPrevious)
    Button btnPrevious;
    private static Button btnPlay;
    private static Button btnPause;
    @BindView(R.id.btnNext)
    Button btnNext;
    private static TextView MainActivityNameSora;
    private static TextView MainActivityNameShekh;
    @BindView(R.id.MainActivity_TV_BufferDuration)
    TextView MainActivityTVBufferDuration;
    @BindView(R.id.MainActivity_TV_Duration)
    TextView MainActivityTVDuration;
    @BindView(R.id.MainActivity_SeekBar)
    ProgressBar MainActivitySeekBar;
    Utilities utilities;
    private static ImageView DetailsSoundActivity_IV_Picture_Shekh;
   // private static Animation animationImage;
    public static boolean IS_OPEN = false;
    public static final String BROADCAST_FINISH_ACTIVITY = "com.example.FinishActivityBroadCast.finish.activity";
    private BroadcastReceiver finishActivity = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_sound_activity);
        ButterKnife.bind(this);
        IS_OPEN = true;

        registerReceiver(finishActivity, new IntentFilter(BROADCAST_FINISH_ACTIVITY));
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPause = (Button) findViewById(R.id.btnPause);
        MainActivityNameSora = (TextView) findViewById(R.id.MainActivity_Name_Sora);
        MainActivityNameShekh = (TextView) findViewById(R.id.MainActivity_Name_Shekh);
        DetailsSoundActivity_IV_Picture_Shekh = (ImageView) findViewById(R.id.DetailsSoundActivity_IV_Picture_Shekh);
        MainActivitySeekBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorButton), PorterDuff.Mode.SRC_IN);

        //animationImage = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.infinity_rotate);
        utilities = new Utilities();
    }

    @Override
    protected void onResume() {
        super.onResume();
        PlayerConstants.PROGRESSER_HANDLER = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Integer i[] = (Integer[]) msg.obj;
                MainActivityTVBufferDuration.setText("" + utilities.milliSecondsToTimer(i[0]));
                MainActivityTVDuration.setText("" + utilities.milliSecondsToTimer(i[1]));
                MainActivitySeekBar.setProgress(i[2]);

            }
        };
        updateUI();
        Glide.with(getApplicationContext())
                .load(activeAudio.getUrl_image())
                .apply(new RequestOptions().placeholder(R.drawable.iconqoran).centerCrop())
                .into(DetailsSoundActivity_IV_Picture_Shekh);
       // DetailsSoundActivity_IV_Picture_Shekh.startAnimation(animationImage);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(finishActivity);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.item_anim_no_thing,R.anim.item_anim_slide_from_bottom);

    }

    @OnClick({R.id.btnPrevious, R.id.btnPlay, R.id.btnPause, R.id.btnNext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnPrevious:
                transportControls.skipToPrevious();
                IsPlay = true;
                updateUI();
                break;
            case R.id.btnPlay:
                transportControls.play();
                IsPlay = true;
                updateUI();
                break;
            case R.id.btnPause:
                transportControls.pause();
                IsPlay = false;
                updateUI();
                break;
            case R.id.btnNext:
                transportControls.skipToNext();
                IsPlay = true;
                updateUI();
                break;
        }
    }

    public static void updateUI() {
        if (!IsPlay) {
            btnPlay.setVisibility(View.VISIBLE);
            btnPause.setVisibility(View.GONE);
        } else {
            btnPlay.setVisibility(View.GONE);
            btnPause.setVisibility(View.VISIBLE);

        }

        MainActivityNameShekh.setText("" + activeAudio.getName_shekh());
        MainActivityNameSora.setText("" + activeAudio.getName_sora());
    }
}