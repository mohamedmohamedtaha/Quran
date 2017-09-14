package com.MohamedTaha.Imagine.Quran;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;

import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.MohamedTaha.Imagine.Quran.getData.Utilities;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.io.File;
import java.io.IOException;

import static com.MohamedTaha.Imagine.Quran.Adapter.ImageAdapter.SHEKH_NAME;
import static com.MohamedTaha.Imagine.Quran.Adapter.RecycleViewReaderAdapter.NAME;
import static com.MohamedTaha.Imagine.Quran.Adapter.RecycleViewReaderAdapter.URLLINK;


public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener,View.OnClickListener{


    //Create Folder to save Koran
    private  String FILENAME = null;
    static File mediaStorageDir ;
    File media_path ;
    private SeekBar seekBar;
    ImageButton button_paly;
    ImageView img_equiliser;
    TextView text_status, text_current_time, text_sound_duration;
    AnimationDrawable animationDrawable;
     private Handler handler = new Handler();
    Utilities utilities;
    AlertDialog.Builder builder;
    //Show The progressBar Circle
    View loadingIndicator;
    ///Download Koran
    DownloadManager downloadManager;
    Uri music_uri ;
    private long  Music_DownloadId;
    FloatingActionMenu FABMenu;
    FloatingActionButton FABDownload,FABStatus,FABCancel;

      private  Runnable updateTimeTask = new Runnable() {
        @Override
        public void run() {

            long totalDuration = mPlayer.getDuration();
            long currentDuration = mPlayer.getCurrentPosition();

            text_sound_duration.setText("" + utilities.milliSecondsToTimer(totalDuration));
            text_current_time.setText("" + utilities.milliSecondsToTimer(currentDuration));

            int progress = (utilities.getProgressPercentage(currentDuration, totalDuration));
            seekBar.setProgress(progress);
            handler.postDelayed(this, 100);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Intent i = getIntent();
       FILENAME = "/" + i.getStringExtra(SHEKH_NAME) + "/";

       //Handle AudioFocuesChangeListener
        mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                switch (focusChange){
                    case AudioManager.AUDIOFOCUS_GAIN:
                        // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                        //Resume playback
                            resume();
                        mPlayer.setVolume(1.0f,1.0f);
                        break;
                    //The service lost audio focus, the user probably moved to playing
                    // media on another app, so release the media player.
                    //Lost focus for an unbounded amount of time: stop playback and release media player

                    // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                    // Stop playback and clean up resources
                    case AudioManager.AUDIOFOCUS_LOSS:
                            stop();
                        break;
                    // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                    // short amount of time.
                    //// Lost focus for a short time, but we have to stop
                    // playback. We don't release the media player because playback
                    // is likely to resume
                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                       pause();
                        break;
                    //  The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                    // our app is allowed to continue playing sound but at a lower volume.
                    //Lost focus for a short time, probably a notification arrived on the device, lower the playback volume.
                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                        if (mAudioIsPlaying) mPlayer.setVolume(0.1f,0.1f);
                        break;
                    case AudioManager.AUDIOFOCUS_REQUEST_FAILED:
                        break;
                    default:
                        //
                }
            }
        };
        music_uri = Uri.parse(getIntent().getExtras().getString(URLLINK));

        //FAB Menu
        FABMenu = (FloatingActionMenu) findViewById(R.id.FABMenu);

        //FABDwonload
        FABDownload = (FloatingActionButton)findViewById(R.id.FABDwonload);
        FABDownload.setOnClickListener(this);

        //FAB Status
        FABStatus = (FloatingActionButton)findViewById(R.id.FABStatus);
        FABStatus.setOnClickListener(this);
        FABStatus.setEnabled(false);

        //FAB Cancel
        FABCancel = (FloatingActionButton)findViewById(R.id.FABCancel);
        FABCancel.setOnClickListener(this);
        FABCancel.setEnabled(false);
        mPlayer = new MediaPlayer();
        declrationVariable();
        titleActionBar();

        utilities = new Utilities();
        seekBar.setOnSeekBarChangeListener(this);

        isNetworkConnected();
        button_paly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlayer.isPlaying()) {
                    if (mPlayer != null) {
                        pause();
                        mAudioFocusGranted = false;
                     //   mPlayer.pause();
                        stopAnimation();
                        text_status.setText(R.string.text_pause);
                        button_paly.setImageResource(R.drawable.play);
                    }
                } else {
                    if (mPlayer != null) {
                        mAudioFocusGranted = true;

                        resume();
                      //  mPlayer.start();
                        startAnimation();
                        text_status.setText(R.string.text_play);
                        button_paly.setImageResource(R.drawable.pause);
                    }

                }
            }
        });
    }

    public void declrationVariable() {
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setEnabled(false);
        text_status = (TextView) findViewById(R.id.songCurrentDurationLabel);
        text_current_time = (TextView) findViewById(R.id.songCurrentDurationLabel1);
        text_sound_duration = (TextView) findViewById(R.id.songTotalDurationLabel);
        button_paly = (ImageButton) findViewById(R.id.buttonPlay);
        button_paly.setEnabled(false);
        img_equiliser = (ImageView) findViewById(R.id.img_equiliser);
        img_equiliser.setBackgroundResource(R.drawable.simple_animation);
        animationDrawable = (AnimationDrawable) img_equiliser.getBackground();
        loadingIndicator = findViewById(R.id.loading_indicator);


    }

    public void startAnimation() {
        img_equiliser.post(new Runnable() {
            @Override
            public void run() {
                animationDrawable.start();
            }
        });
    }

    public void stopAnimation() {
        img_equiliser.post(new Runnable() {
            @Override
            public void run() {
                animationDrawable.stop();
            }
        });
    }

    public void playKoran() {
        File media_path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS + FILENAME);
        final File exStore = new File(media_path, getIntent().getExtras().getString(NAME) + ".mp3");
        if (!checkSilentDevice()) {
            //Play Sora in internal Storage If was There
            //Check Is the aya is there or not for the App private Storage

            if (exStore != null && exStore.exists()) {
                playAyaFromEnternal(exStore);

            } else {
                //________________________________________________
                //Play Sora in the Enternet If was Not There in Internal Storage
                play();


            }
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("تَنْبِيهٌ");
            builder.setMessage("الجِهَازُ رُبَّمَا يَكُونُ فِي وَضْعٍ الهَزَّازَ أَوْ الكَتْمُ هَلْ تُرِيدُ تَشْغِيلَ الصَّوْتِ؟");
            builder.setCancelable(false);
            builder.setPositiveButton("مُوَافِقٌ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //4. play music
                    if (exStore != null && exStore.exists()) {
                        playAyaFromEnternal(exStore);

                    } else {
                        //________________________________________________
                        //Play Sora in the Enternet If was Not There in Internal Storage
                        play();


                    }
                }
            });
            builder.setNegativeButton("لَا", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.show();

        }

    }

         public void updateProgressBar() {
        handler.postDelayed(updateTimeTask, 100);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        handler.removeCallbacks(updateTimeTask);
   }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        handler.removeCallbacks(updateTimeTask);
        int totalDuration = mPlayer.getDuration();
        int currentPosition = utilities.progressToTimer(seekBar.getProgress(), totalDuration);
        mPlayer.seekTo(currentPosition);
        updateProgressBar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       try {

            unregisterReceiver(mIntentReceiver);
        }catch (IllegalArgumentException e){
            //Don't any thing
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacks(updateTimeTask);
        seekBar.setProgress(0);
         stop();
    }


    //check Internet
    private void isNetworkConnected() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            falseButton();
            File media_path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS + FILENAME ) ;
           final File exStore = new File(media_path,getIntent().getExtras().getString(NAME)+".mp3");
            if (exStore != null && exStore.exists()){
                if (!checkSilentDevice()) {
                    playAyaFromEnternal(exStore);
                } else {
                    AlertDialog.Builder   builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("تَنْبِيهٌ");
                    builder.setMessage("الجِهَازُ رُبَّمَا يَكُونُ فِي وَضْعٍ الهَزَّازَ أَوْ الكَتْمُ هَلْ تُرِيدُ تَشْغِيلَ الصَّوْتِ؟ ");
                    builder.setCancelable(false);
                    builder.setPositiveButton("مُوَافِقٌ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //4. play music
                            playAyaFromEnternal(exStore);
                        }
                    });
                    builder.setNegativeButton("لَا", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                           finish();
                        }
                    });
                    builder.show();

                }
                        }else {
                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.text_alert);
                builder.setMessage(R.string.text_false);
                builder.setCancelable(false);
                builder.setPositiveButton("مُوَافِقٌ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }
        } else {
           if (URLUtil.isValidUrl(getIntent().getExtras().getString(URLLINK))) {
                playKoran();
            } else {
                Toast.makeText(this, R.string.text_fiald, Toast.LENGTH_LONG).show();
                falseButton();
            }
        }
    }
    public void titleActionBar(){
      //  getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
       // getSupportActionBar().setCustomView(R.layout.custom_actionbar);
      //  getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        TextView titleSora = (TextView) findViewById(R.id.title_Sora);
        titleSora.setText(getIntent().getExtras().getString(NAME));
//          titleSora.setText(ListSoundReader.respones.get(SELECTED_POSITION).getSora_name());

        TextView titleShekh = (TextView) findViewById(R.id.title_Shekh);
        titleShekh.setText(getIntent().getStringExtra(SHEKH_NAME));

    }
    ///Methods Download Koran From Internert
    private long DownloadData(Uri uri, View view){

        long downloadReference = 0;
        //Create Requect for android download manager
        downloadManager = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        //Setting title of request
        request.setTitle(FILENAME + getIntent().getExtras().getString(NAME));
        //Setting description of request
        request.setDescription(getString(R.string.setDescriptionRequet));
        //Setting Show Notification After downloaded
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //Set the local destination for the download file to a path within the application's external files directory
        if (view.getId() == R.id.FABDwonload)
            //check download folder for the App private
             media_path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS + FILENAME) ;
         //check download folder Global
        mediaStorageDir = new File(media_path, getIntent().getExtras().getString(NAME)+".mp3");
        if ( mediaStorageDir != null && mediaStorageDir.exists()){
            Toast.makeText(getApplicationContext(),"السُّورَةُ تَمَّ تَحْمِيلُهَا مِنْ قَبْلُ\n إِذَا وَاجَهْتُكَ أَيُّ مُشْكِلَةٌ (رَاسِلُنَا) نَحْنُ جَاهِزِينَ لِحَلِّهَا",Toast.LENGTH_LONG).show();
        }else {
            request.setDestinationInExternalFilesDir(MainActivity.this, Environment.DIRECTORY_DOWNLOADS + FILENAME
                    , getIntent().getExtras().getString(NAME)+".mp3");
           //Enqueue download and save the referenceId
            downloadReference = downloadManager.enqueue(request);
            FABStatus.setEnabled(true);
            FABCancel.setEnabled(true);
        }
        return downloadReference;
    }
    private void check_Music_status(long music_download_id){
        DownloadManager.Query imageDownloadQuery = new DownloadManager.Query();
        //set the query filter to our previously enqueued download
        imageDownloadQuery.setFilterById(music_download_id);

        //Query the download manager about downloads that have been requested
        Cursor cursor = downloadManager.query(imageDownloadQuery);
        if (cursor.moveToFirst()){
            DownloadStatus(cursor,music_download_id);
        }
    }
    private void DownloadStatus(Cursor cursor,long downloadId){
        //column for download status
        int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
        int status = cursor.getInt(columnIndex);

        //column for reason code if the download failed or paused
        int columnReason = cursor.getColumnIndex(DownloadManager.COLUMN_REASON);
        int reason = cursor.getInt(columnReason);

        //get the download filename
        int fileNameIndex = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME);
        String fileName = cursor.getString(fileNameIndex);
        String statusText = "";
        String reasonText = "";
        switch (status){
            case DownloadManager.STATUS_FAILED :
                statusText = "STATUS_FAILED";
                switch (reason){
                    case DownloadManager.ERROR_CANNOT_RESUME:
                        reasonText = "Error_CANNOUT_RESUME";
                        break;
                    case DownloadManager.ERROR_DEVICE_NOT_FOUND :
                        reasonText = "ERROR_DEVICE_NOT_FOUND";
                        break;
                    case DownloadManager.ERROR_FILE_ALREADY_EXISTS:
                        reasonText = "ERROR_FILE_ALREADY_EXISTS";
                        break;
                    case DownloadManager.ERROR_FILE_ERROR:
                        reasonText = "ERROR_FILE_ERROR";
                        break;
                    case DownloadManager.ERROR_HTTP_DATA_ERROR:
                        reasonText = "ERROR_HTTP_DATA_ERROR";
                        break;
                    case DownloadManager.ERROR_INSUFFICIENT_SPACE :
                        reasonText = "ERROR_INSUFFICIENT_SPACE";
                        break;
                    case DownloadManager.ERROR_TOO_MANY_REDIRECTS:
                        reasonText = "EROR_TOO_MANY_REDIRECTS";
                        break;
                    case DownloadManager.ERROR_UNHANDLED_HTTP_CODE:
                        reasonText = "ERROR_UNHANDLED_HTTP_CODE";
                        break;
                    case DownloadManager.ERROR_UNKNOWN:
                        reasonText = "ERROR_UNKNOWN";
                        break;
                }
                break;
            case DownloadManager.STATUS_PAUSED:
                statusText = "STATUS_PAUSED";
                switch (reason){
                    case DownloadManager.PAUSED_QUEUED_FOR_WIFI:
                        reasonText = "PAUSED_QUEUED_FOR_WIFI";
                        break;
                    case DownloadManager.PAUSED_UNKNOWN:
                        reasonText = "PAUSED_UNKNOWN";
                        break;
                    case DownloadManager.PAUSED_WAITING_FOR_NETWORK:
                        reasonText = "PAUSED_WAITING_FOR_NETWORK";
                        break;
                    case DownloadManager.PAUSED_WAITING_TO_RETRY:
                        reasonText = "PAUSED_WAITING_TO_RETRY";
                        break;
                }
                break;
            case DownloadManager.STATUS_PENDING:
                statusText = "STATUS_PENDING";
                break;
            case DownloadManager.STATUS_RUNNING:
                statusText = "STATUS_RUNNING";
                break;
            case DownloadManager.STATUS_SUCCESSFUL:
                statusText = "STATUS_SUCCESSFUL";
                reasonText = "filename:\n " + fileName;
                break;
        }
        if (downloadId == Music_DownloadId){
            Toast toast = Toast.makeText(MainActivity.this,"حَالَةُ التَّحْمِيلِ: " + "\n" + statusText + "\n" +
                    reasonText,Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP,25,400);
            toast.show();
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //Download KORAN
            case R.id.FABDwonload:
                Music_DownloadId = DownloadData(music_uri,v);
                break;

            //Check the status of all downloads
            case R.id.FABStatus :
                check_Music_status(Music_DownloadId);
                break;
            //Cancel the ongoing download:
            case R.id.FABCancel:
                downloadManager.remove(Music_DownloadId);
                break;

        }

    }

public void playAyaFromEnternal(File media_path ){
    //Play Sora in internal Storage If was There
    //Check Is the aya is there or not for the App private Storage
       if (!mAudioIsPlaying){
        //1.Acquire audio focus
        if (!mAudioFocusGranted && requestAudioFocus()){
            //2. Kill off any other play back sources
            forceMusicStop();
            //3. Register broadcast receiver for player intents
            setupBroadcastReceiver();
        }
        //4. play music
        try {
            mPlayer.reset();
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            Uri uri = Uri.parse(String.valueOf(media_path));
            mPlayer.setDataSource(getApplicationContext(),uri);
            mPlayer.prepare();
            text_status.setText(R.string.text_prepearing);
            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                    startAnimation();
                    updateProgressBar();
                    button_paly.setImageResource(R.drawable.pause);
                    text_status.setText(R.string.text_play);
                    View loadingIndicator = findViewById(R.id.loading_indicator);
                    loadingIndicator.setVisibility(View.GONE);
                    button_paly.setEnabled(true);
                    seekBar.setEnabled(true);
                }
            });
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    text_status.setText(R.string.text_finish);
                    text_current_time.setText("");
                    stopAnimation();
                    button_paly.setImageResource(R.drawable.play);
                 //   mAudioIsPlaying =false;
                    mAudioFocusGranted = false;

                    //  abandonAudioFocus();
                 //   forceMusicStop();
                }
            });
            mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    switch (what){
                        case MediaPlayer.MEDIA_ERROR_TIMED_OUT:
                            Toast.makeText(getApplicationContext(),"MEDIA_ERROR_TIMED_OUT",Toast.LENGTH_LONG).show();
                            break;
                        case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                            Toast.makeText(getApplicationContext(),"MEDIA_ERROR_UNKNOWN",Toast.LENGTH_LONG).show();
                            break;
                        case MediaPlayer.MEDIA_INFO_BAD_INTERLEAVING:
                            Toast.makeText(getApplicationContext(),"MEDIA_INFO_BAD_INTERLEAVING",Toast.LENGTH_LONG).show();
                            break;

                        case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                            Toast.makeText(getApplicationContext(),"MEDIA_INFO_BUFFERING_END",Toast.LENGTH_LONG).show();
                            break;
                        case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                            Toast.makeText(getApplicationContext(),"MEDIA_INFO_BUFFERING_START",Toast.LENGTH_LONG).show();
                            break;
                        case MediaPlayer.MEDIA_INFO_METADATA_UPDATE:
                            Toast.makeText(getApplicationContext(),"MEDIA_INFO_METADATA_UPDATE",Toast.LENGTH_LONG).show();
                            break;
                        case MediaPlayer.MEDIA_INFO_NOT_SEEKABLE:
                            Toast.makeText(getApplicationContext(),"MEDIA_INFO_NOT_SEEKABLE",Toast.LENGTH_LONG).show();
                            break;
                        case MediaPlayer.MEDIA_INFO_SUBTITLE_TIMED_OUT:
                            Toast.makeText(getApplicationContext(),"MEDIA_INFO_SUBTITLE_TIMED_OUT",Toast.LENGTH_LONG).show();
                            break;

                    }
                    stop();

                    return false;
                }
            });
            button_paly.setImageResource(R.drawable.pause);
            seekBar.setProgress(0);
            seekBar.setMax(100);
        } catch (IOException e) {
            //    e.printStackTrace();
            Toast.makeText(getApplicationContext(),"السُّورَةُ غَيْرُ مَوْجُودَةٍ يَجِبُ تَحْمِيلُهَا أَوَّلَا",Toast.LENGTH_LONG).show();
        }
      mAudioIsPlaying =true;
     }
    }

    ///__________________________________________________________________________________________________________
    //Handle AudioManager and AudioFouces

    private static final String CMD_NAME = "command";
    private static final String CMD_PAUSE = "pause";
    private static final String CMD_STOP = "stop";
    private static final String CMD_PLAY = "play";
    //Jellybean
    private static String SERVICE_CMD = "com.sec.android.app.music.musicservicecommand";
    private static String PAUSE_SERVICE_CMD = "com.sec.android.app.music.musicservicecommand.pause";
    private static String PLAY_SERVICE_CMD = "com.sec.android.app.music.musicservicecommand.play";
    //Honeycomb
    {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN){
            SERVICE_CMD = "com.android.music.musicservicecommand";
            PAUSE_SERVICE_CMD = "com.android.music.musicservicecommand.pause";
            PLAY_SERVICE_CMD = "com.android.music.musicservicecommand.play";
        }
    };
    private boolean mAudioFocusGranted = false;
    private boolean mAudioIsPlaying = false;
    private MediaPlayer mPlayer;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener;
    private BroadcastReceiver mIntentReceiver;
    private boolean mReceiverRegistered = false;

    public void play() {
        if (!mAudioIsPlaying){
            //1.Acquire audio focus
            if (!mAudioFocusGranted && requestAudioFocus()){
                //2. Kill off any other play back sources
                forceMusicStop();
                //3. Register broadcast receiver for player intents
                setupBroadcastReceiver();
            }
            //4. play music
            try {
                mPlayer.reset();
                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mPlayer.setDataSource(getIntent().getExtras().getString(URLLINK));
                mPlayer.prepareAsync();
                text_status.setText(R.string.text_prepearing);
                mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                        startAnimation();
                        updateProgressBar();
                        button_paly.setImageResource(R.drawable.pause);
                        text_status.setText(R.string.text_play);
                        View loadingIndicator = findViewById(R.id.loading_indicator);
                        loadingIndicator.setVisibility(View.GONE);
                        button_paly.setEnabled(true);
                        seekBar.setEnabled(true);

                        // FABDownload.setEnabled(true);
                    }
                });
                mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        text_status.setText(R.string.text_finish);
                        text_current_time.setText("");
                        stopAnimation();
                        button_paly.setImageResource(R.drawable.play);
                      //  mAudioIsPlaying =false;
                       mAudioFocusGranted = false;
                        //  abandonAudioFocus();
                     //   forceMusicStop();
                    }
                });
                mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        switch (what){
                            case MediaPlayer.MEDIA_ERROR_TIMED_OUT:
                                Toast.makeText(getApplicationContext(),"MEDIA_ERROR_TIMED_OUT",Toast.LENGTH_LONG).show();
                                break;
                            case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                                Toast.makeText(getApplicationContext(),"MEDIA_ERROR_UNKNOWN",Toast.LENGTH_LONG).show();
                                break;
                            case MediaPlayer.MEDIA_INFO_BAD_INTERLEAVING:
                                Toast.makeText(getApplicationContext(),"MEDIA_INFO_BAD_INTERLEAVING",Toast.LENGTH_LONG).show();
                                break;

                            case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                                Toast.makeText(getApplicationContext(),"MEDIA_INFO_BUFFERING_END",Toast.LENGTH_LONG).show();
                                break;
                            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                                Toast.makeText(getApplicationContext(),"MEDIA_INFO_BUFFERING_START",Toast.LENGTH_LONG).show();
                                break;
                            case MediaPlayer.MEDIA_INFO_METADATA_UPDATE:
                                Toast.makeText(getApplicationContext(),"MEDIA_INFO_METADATA_UPDATE",Toast.LENGTH_LONG).show();
                                break;
                            case MediaPlayer.MEDIA_INFO_NOT_SEEKABLE:
                                Toast.makeText(getApplicationContext(),"MEDIA_INFO_NOT_SEEKABLE",Toast.LENGTH_LONG).show();
                                break;
                            case MediaPlayer.MEDIA_INFO_SUBTITLE_TIMED_OUT:
                                Toast.makeText(getApplicationContext(),"MEDIA_INFO_SUBTITLE_TIMED_OUT",Toast.LENGTH_LONG).show();
                                break;

                        }
                        stop();

                        return false;
                    }
                });
                button_paly.setImageResource(R.drawable.pause);
                seekBar.setProgress(0);
                seekBar.setMax(100);

            } catch (IOException e) {
                e.printStackTrace();
            }
            mAudioIsPlaying =true;
        }

    }
    public void pause(){
        //1. Suspend play back
        if (mAudioFocusGranted && mAudioIsPlaying){
            mPlayer.pause();
            mAudioIsPlaying = false;
            button_paly.setImageResource(R.drawable.play);
            stopAnimation();
            text_status.setText(R.string.text_pause);
        }
    }
    public  void stop(){
        //1. stop play back
        if (mAudioFocusGranted && mAudioIsPlaying){
           // mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
            mAudioIsPlaying = false;
            button_paly.setImageResource(R.drawable.play);
        }
        //2. Give up audio focus
        abandonAudioFocus();
    }
    private void resume(){
        if (!mPlayer.isPlaying()&& mAudioFocusGranted){
            mPlayer.start();
            mAudioIsPlaying =true;
            startAnimation();
            button_paly.setImageResource(R.drawable.pause);
            text_status.setText(R.string.text_play);

        }
    }

    public boolean requestAudioFocus(){
        if (!mAudioFocusGranted){
            AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
            //Request audio focus for play back
            int result = am.requestAudioFocus(mOnAudioFocusChangeListener,
                    //Use the music stream.
                    AudioManager.STREAM_MUSIC,
                    //Request permanent focus
                    AudioManager.AUDIOFOCUS_GAIN);

            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                mAudioFocusGranted = true;
            }else {
                //FAILED
                //  Log.e(TAG,"FAILED TO GET AUDIO FOCUS");
                Toast.makeText(getApplicationContext(),"حدث خطأ في التشغيل ",Toast.LENGTH_LONG).show();
            }
        }
        return mAudioFocusGranted;
    }
    private void abandonAudioFocus(){
        AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        int result = am.abandonAudioFocus(mOnAudioFocusChangeListener);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
            mAudioFocusGranted = false;
        }else {
            //FALID
        }
        mOnAudioFocusChangeListener = null;
    }
   private void setupBroadcastReceiver(){
        mIntentReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                String cmd = intent.getStringExtra(CMD_NAME);

                if (PAUSE_SERVICE_CMD.equals(action) || (SERVICE_CMD.equals(action) && CMD_PAUSE.equals(cmd))){
                    play();
                }
                if (PLAY_SERVICE_CMD.equals(action) || (SERVICE_CMD.equals(action) && CMD_PLAY.equals(cmd))){
                    pause();
                }
            }
        };
        //Do the right thing when something else tries to play
        if (!mReceiverRegistered){
            IntentFilter commandFilter = new IntentFilter();
            commandFilter.addAction(SERVICE_CMD);
            commandFilter.addAction(PAUSE_SERVICE_CMD);
            commandFilter.addAction(PLAY_SERVICE_CMD);
            registerReceiver(mIntentReceiver,commandFilter);
            mReceiverRegistered = true;
        }
    }
    private void forceMusicStop(){
        AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        if (am.isMusicActive()){
            Intent intentStop = new Intent(SERVICE_CMD);
            intentStop.putExtra(CMD_NAME,CMD_STOP);
            sendBroadcast(intentStop);
        }
    }
    //This method fro Disable the Button durtion there is Error in the sound
    private void falseButton(){
        button_paly.setEnabled(false);
        seekBar.setEnabled(false);
        FABDownload.setEnabled(false);
        loadingIndicator.setVisibility(View.GONE);

    }
    private boolean checkSilentDevice(){
        boolean status = false;
        AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        switch (am.getRingerMode()){
            case AudioManager.RINGER_MODE_SILENT:
            case AudioManager.RINGER_MODE_VIBRATE:
                status = true;
                break;
            case AudioManager.RINGER_MODE_NORMAL:
                status = false;
                break;
        }
        return status;
    }
}