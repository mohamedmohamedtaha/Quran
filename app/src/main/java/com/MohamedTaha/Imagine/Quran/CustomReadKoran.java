package com.MohamedTaha.Imagine.Quran;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;

import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import static com.MohamedTaha.Imagine.Quran.Adapter.ListAdapterIndex.NAMESORA;
import static com.MohamedTaha.Imagine.Quran.Adapter.ListAdapterIndex.TEXTCONTENT;
import static com.MohamedTaha.Imagine.Quran.Adapter.ListAdapterIndex.TEXTSTART;

/**
 * Created by MANASATT on 10/08/17.
 */

public class CustomReadKoran extends AppCompatActivity {
    TextView textNameSor, textStart, textContent,textNameSorActionBar;
    private String colorStart = "<font color='#74d415'>";
    private String colorEnd = "</font>";
    private SeekBar seekBar;
    int MAX = 50;
    int MIN =20;
    int PLUS = 5;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_read_koran);
        //To light Screen during Reading
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        titleActionBar();
        textNameSorActionBar = (TextView) findViewById(R.id.textNameSorActionBar);
        textStart = (TextView) findViewById(R.id.textStart);
        textContent = (TextView) findViewById(R.id.textContent);
        textNameSorActionBar.setText(getIntent().getExtras().getString(NAMESORA));
        textStart.setText(getIntent().getExtras().getString(TEXTSTART));
       // String yourText = getIntent().getExtras().getString(TEXTCONTENT);
       // yourText = yourText.replace("<(>", colorStart);
       // yourText = yourText.replace("<)>", colorEnd);
        //textContent.setText(Html.fromHtml(yourText));
        textContent.post(new Runnable() {
            @Override
            public void run() {
                String yourText = getIntent().getExtras().getString(TEXTCONTENT);
                yourText = yourText.replace("<(>", colorStart);
                yourText = yourText.replace("<)>", colorEnd);
                textContent.setText(Html.fromHtml(yourText));
            }
        });
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax((MAX - MIN )/PLUS);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textContent.setTextSize(MIN + (progress * PLUS));
                textStart.setTextSize(MIN + (progress * PLUS));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public void titleActionBar() {
       ActionBar mActionbar = getSupportActionBar();

        mActionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        mActionbar.setDisplayShowCustomEnabled(true);
        mActionbar.setCustomView(R.layout.custom_actionbar);

        //For a void not show The ActionBar match_parent
        Toolbar toolbar=(Toolbar)mActionbar.getCustomView().getParent();
        toolbar.setContentInsetsAbsolute(0,0);
        toolbar.getContentInsetEnd();
        toolbar.setPadding(0,0,0,0);
    }


}











