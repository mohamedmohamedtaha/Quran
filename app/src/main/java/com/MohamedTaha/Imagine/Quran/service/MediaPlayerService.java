package com.MohamedTaha.Imagine.Quran.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.session.MediaSessionManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.helper.Utilities;
import com.MohamedTaha.Imagine.Quran.helper.checkConnection.NetworkConnection;
import com.MohamedTaha.Imagine.Quran.helper.checkConnection.NoInternetConnection;
import com.MohamedTaha.Imagine.Quran.helper.util.PlaybackStatus;
import com.MohamedTaha.Imagine.Quran.helper.util.PlayerConstants;
import com.MohamedTaha.Imagine.Quran.helper.util.StorageUtil;
import com.MohamedTaha.Imagine.Quran.mvp.model.ImageModel;
import com.MohamedTaha.Imagine.Quran.receiver.ConnectivityReceiver;
import com.MohamedTaha.Imagine.Quran.receiver.NoInternetReceiver;
import com.MohamedTaha.Imagine.Quran.ui.activities.DetailsSoundActivity;
import com.MohamedTaha.Imagine.Quran.ui.activities.ListSoundReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.MohamedTaha.Imagine.Quran.helper.checkConnection.NoInternetConnection.isInternet;
import static com.MohamedTaha.Imagine.Quran.ui.activities.DetailsSoundActivity.BROADCAST_FINISH_ACTIVITY;
import static com.MohamedTaha.Imagine.Quran.ui.activities.DetailsSoundActivity.DetailsSoundActivity_loading_indicator;
import static com.MohamedTaha.Imagine.Quran.ui.activities.DetailsSoundActivity.IS_OPEN;
import static com.MohamedTaha.Imagine.Quran.ui.activities.DetailsSoundActivity.isDetailsActivityTrue;
import static com.MohamedTaha.Imagine.Quran.ui.activities.ListSoundReader.FragmentListSoundLLControlMedia;
import static com.MohamedTaha.Imagine.Quran.ui.activities.ListSoundReader.ListSoundReaderLoadingIndicator;
import static com.MohamedTaha.Imagine.Quran.ui.activities.ListSoundReader.isServiceRunning;
//import static com.MohamedTaha.Imagine.Quran.ui.activities.ListSoundReader.seekBar;

public class MediaPlayerService extends Service implements MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
        MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener, AudioManager.OnAudioFocusChangeListener {
    public static final String BROADCAST_NOT_CONNECTION = "com.example.createmediaplayer.no.connection";
    public static final String BROADCAST_NOT_INTERNET = "com.example.createmediaplayer.no.internet";
    public static final String Broadcast_SILENT_DEVICE = "com.example.createmediaplayer.silent.device";
    private ConnectivityReceiver connectivityReceiver = null;
    private NoInternetReceiver noInternetReceiver = null;
    NotificationCompat.Builder notificationBuilder;
    private static Timer timer;
    Integer totalDuration ;
    Integer currentDuration ;
    int progress ;
//
//    @Override
//    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//    }
//
//    @Override
//    public void onStartTrackingTouch(SeekBar seekBar) {
//
//    }
//
//    @Override
//    public void onStopTrackingTouch(SeekBar seekBar) {
//        int currentPosition = utilities.progressToTimer(seekBar.getProgress(),totalDuration);
//        mediaPlayer.seekTo(currentPosition);
//
//    }


    private class MainTask extends TimerTask {
        @Override
        public void run() {
            handler1.sendEmptyMessage(0);
        }
    }

    private final Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mediaPlayer != null) {
                 totalDuration = mediaPlayer.getDuration();
                 currentDuration = mediaPlayer.getCurrentPosition();
                 progress = (utilities.getProgressPercentage(currentDuration, totalDuration));
                Integer i[] = new Integer[4];
                i[0] = currentDuration;
                i[1] = totalDuration;
                i[2] = progress;
                try {
                    PlayerConstants.PROGRESSER_HANDLER.sendMessage(PlayerConstants.PROGRESSER_HANDLER.obtainMessage(0, i));
                } catch (Exception e) {

                }
            }
        }
    };
    Utilities utilities;

    //for media controlers
    public static final String ACTION_PLAY = "com.example.createmediaplayer.ACTION_PLAY";
    public static final String ACTION_PAUSE = "com.example.createmediaplayer.ACTION_PAUSE";
    public static final String ACTION_PREVIOUS = "com.example.createmediaplayer.ACTION_PREVIOUS";
    public static final String ACTION_NEXT = "com.example.createmediaplayer.ACTION_NEXT";
    public static final String ACTION_STOP = "com.example.createmediaplayer.ACTION_STOP";
    public static final String SESIION_AUDIO_PLAYER = "AudioPlayer";
    private static final String CHANNEL_ID = "com.MohamedTaha.Imagine.Quran.channelID";
    //MediaSession
    private MediaSessionManager mediaSessionManager;
    private MediaSessionCompat mediaSession;
    public static MediaControllerCompat.TransportControls transportControls;
    private MediaControllerCompat controller;
    private MediaMetadataCompat mediaMetadata;
    private MediaDescriptionCompat description;

    //AudioPlayer notification ID
    private static final int NOTIFICATION_ID_SERVICE = 10;
    private String FILENAME = null;

    public static MediaPlayer mediaPlayer;
    //path to the audio file
    private String mediaFile;

    //Used to ic_pause/resume MediaPlayer
//    private int resumePosition;

    private AudioManager audioManager;

    //Handle incoming phone calls
    private boolean ongoingCall = false;
    private PhoneStateListener phoneStateListener;
    private TelephonyManager telephonyManager;
    private boolean isPlaying = false;

    //List of available Audio files
    private ArrayList<ImageModel> audioArrayList;
    private int audioIndex = -1;
    public static ImageModel activeAudio;// an object of the currently playing audio

    private Intent playbackAction;

    @Override
    public void onCreate() {
        super.onCreate();
        //For don't throw exception when there is not Internet
        //  int NOTIFICATION_ID = (int) (System.currentTimeMillis()%10000);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            startForeground(NOTIFICATION_ID_SERVICE, new Notification.Builder(this,CHANNEL_ID).build());
//        }
        utilities = new Utilities();
//        //For avoid throw exception with don't find internet
//        if (Build.VERSION.SDK_INT >= 26) {
//            startForeground(NOTIFICATION_ID_SERVICE, new Notification());
//        }
//        seekBar.setOnSeekBarChangeListener(this);


        // Create a network change broadcast receiver.
        connectivityReceiver = new ConnectivityReceiver();
        noInternetReceiver = new NoInternetReceiver();

        //Perform one-time setup procedures
        //Manage incoming phone calls during playback.
        //Pause MediaPlayer on incoming call, Resume on hangup.
        callStateListener();
        //ACTION_AUDIO_BECOMING_NOISY -- change in audio outputs -- BroadcastReceiver
        registerBecomingNoisyReceiver();
        //Listen for new Audio to play -- BroadcastReceiver
        register_playNewAudio();
        //Listen For not Connection
        registerNoConnection();
        //Listen for not Internet
        registerNoInternet();
        //Listen for Silent Device
        //registerSilentDevice();

    }


    //The system calls this method when an activity, requests the service be started
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // MediaButtonReceiver.handleIntent(mediaSession, intent);

        //for start service when don't find the internet and don't throw Exception.
        //you only need to create  the cnannel on API 26+ devices
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
        notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);

        try {
            //Load data from SharedPreferences
            StorageUtil storage = new StorageUtil(getApplicationContext());
            audioArrayList = storage.loadAudio();
            audioIndex = storage.loadAudioIndex();
            if (audioIndex != -1 && audioIndex < audioArrayList.size()) {
                //index is in a valid range
                activeAudio = audioArrayList.get(audioIndex);
            } else {
                stopSelf();
            }
        } catch (NullPointerException e) {
            stopSelf();
        }
        //Request audio focus
        if (requestAduioFocus() == false) {
            //Could not gain focus
            stopSelf();
        }
        if (mediaSessionManager == null) {
            try {
                initMediaSession();
                initMediaPlayer();
            } catch (RemoteException e) {
                e.printStackTrace();
                stopSelf();
            }
            //  buildNotification(PlaybackStatus.PLAYING);
        }

        //Handle Intent action from MediaSession.TransportControls
        handleIncomingActions(intent);
    //    return super.onStartCommand(intent, flags, startId);
        return  START_NOT_STICKY;

    }

    public void playAyaFromEnternal(File media_path) {
        //Play Sora in internal Storage If was There
        //Check Is the aya is there or not for the App private Storage
        //page_slider3. play music
        Uri uri = Uri.parse(String.valueOf(media_path));
        try {
            // mediaPlayer.reset();
            mediaPlayer.setDataSource(getApplicationContext(), uri);
            mediaPlayer.prepareAsync();

            buildNotification(PlaybackStatus.PLAYING);
            FragmentListSoundLLControlMedia.setVisibility(View.VISIBLE);

        } catch (Exception e) {
            //   e.printStackTrace();
            //Toast.makeText(getApplicationContext(), "السُّورَةُ غَيْرُ مَوْجُودَةٍ يَجِبُ تَحْمِيلُهَا أَوَّلَا", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mediaSession.release();
        //    removeNotification();
        return super.onUnbind(intent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            stopMedia();

        }
        removeAudioFocus();
        //Disable the PhoneStateListener
        if (phoneStateListener != null) {
            telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_NONE);
        }
        removeNotification();
        //unregister BroadcastReceivers
        unregisterReceiver(becomingNoisyReceiver);
        unregisterReceiver(playNewAudio);
        if (connectivityReceiver != null) {
            unregisterReceiver(connectivityReceiver);
        }
        if (noInternetReceiver != null) {
            unregisterReceiver(noInternetReceiver);
        }

        //Clear cached playlist
        new StorageUtil(getApplicationContext()).clearCachedAudioPlaylist();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    //Handle incoming phone calls
    private void callStateListener() {
        //Get the telephony manager
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        //Starting listening for PhoneState changes
        phoneStateListener = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String phoneNumber) {
                switch (state) {
                    //if at least one call exists or the phone is ringing
                    //ic_pause the MediaPlayer
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                    case TelephonyManager.CALL_STATE_RINGING:
                        if (mediaPlayer != null && isPlaying) {
                            pauseMedia();
                            ongoingCall = true;
                        }
                        break;
                    case TelephonyManager.CALL_STATE_IDLE:
                        //Phone idle. Start playing
                        if (mediaPlayer != null) {
                            if (ongoingCall) {
                                ongoingCall = false;
                                resumeMedia();
                            }
                        }
                        break;
                }
            }
        };
        //Register the listener with the telephony manager
        //Listen for changes to the device call state.
        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
    }


    private void initMediaPlayer() {
        if (mediaPlayer == null)
            mediaPlayer = new MediaPlayer(); // new MediaPlayer instance
        //Set up MediaPlayer event listeners
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnInfoListener(this);
        //Reset so that the MediaPlayer is not pointing to anther data source
        mediaPlayer.reset();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build());
        } else {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        }
        try {
            //Set the data source to the mediaFile location
            //  mediaPlayer.setDataSource("http://server11.mp3quran.net/minsh_mjwd/044.mp3");
            FILENAME = "/" + activeAudio.getName_shekh() + "/";
            isNetworkConnected();
        } catch (Exception e) {
            e.printStackTrace();
            stopSelf();
        }
    }


    @Override
    public void onAudioFocusChange(int focusChange) {
        //Invoked when the audio focus fo the system is updated.
        switch (focusChange) {
            case AudioManager.AUDIOFOCUS_GAIN:
                //resume playback
                if (mediaPlayer == null) initMediaPlayer();
                if (mediaPlayer != null && isPlaying) {
                    playMedia();
                    mediaPlayer.setVolume(1.0f, 1.0f);
                }

                break;
            case AudioManager.AUDIOFOCUS_LOSS:
                //Lost focus for an unbounded amount of time : stop playback and relase media player
                if (mediaPlayer.isPlaying()) pauseMedia();
                buildNotification(PlaybackStatus.PAUSED);
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                //Lost focus for a short time, but we have to stop
                //playback.we don't release the media player because playback is likely to resume
                if (mediaPlayer.isPlaying()) {
                    pauseMedia();
                    buildNotification(PlaybackStatus.PAUSED);

                }

                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                //Lost focus for a short time,but it's ok to keep playing at an attenuated level
                if (mediaPlayer.isPlaying() && isPlaying) mediaPlayer.setVolume(0.1f, 0.1f);
                break;
        }
    }

    private boolean requestAduioFocus() {
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int result = audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            //Focus gained
            return true;
        }
        //Could not gain focus
        return false;
    }

    private boolean removeAudioFocus() {
        return AudioManager.AUDIOFOCUS_REQUEST_GRANTED == audioManager.abandonAudioFocus(this);
    }


    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        //Invoked when playback of a media source has complete
        timer.cancel();
        //For play Next Audio after finish one
        skipToNext();
        updateMetaData();
        buildNotification(PlaybackStatus.PLAYING);
    }


    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        //Invoked when there has been an error during an asynchronous operation
        switch (what) {
            case MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK:
                Toast.makeText(getApplicationContext(), "MEDIA ERROR NOT VALIED FOR PROGRESSIVE PLAYBACK " + extra, Toast.LENGTH_SHORT).show();
                break;
            case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                Toast.makeText(getApplicationContext(), "MEDIA ERROR SERVER DIED " + extra, Toast.LENGTH_SHORT).show();
                //   break;
                // case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                //       Toast.makeText(getApplicationContext(), "MEDIA ERROR UNKNOWN" + extra, Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        //Invoked when the media source is ready for playback
        timer = new Timer();
        playMedia();
        timer.scheduleAtFixedRate(new MainTask(), 0, 100);
        ListSoundReader.updateUI(getApplicationContext());
        if (IS_OPEN) {
            DetailsSoundActivity.updateUI();
        }

    }
    public void seekTo(int posiiotn) {
        if (mediaPlayer != null){
            mediaPlayer.seekTo(posiiotn);
        }

    }

    private void playMedia() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            isPlaying = true;
            ListSoundReader.IsPlay = true;
        }
    }

    private void stopMedia() {
        if (mediaPlayer == null) return;
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
          //  mediaPlayer.release();
            isPlaying = false;
            ListSoundReader.IsPlay = false;
        }
        if (timer != null) {
            timer.cancel();
        }
        isServiceRunning = false;

    }

    private void pauseMedia() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            //updateProgressBar();
         //   resumePosition = mediaPlayer.getCurrentPosition();
            isPlaying = false;
            buildNotification(PlaybackStatus.PAUSED);
            ListSoundReader.IsPlay = false;
            ListSoundReader.updateUI(getApplicationContext());
            if (IS_OPEN) {
                DetailsSoundActivity.updateUI();
            }
        }
    }

    private void resumeMedia() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(currentDuration);
            mediaPlayer.start();
            isPlaying = true;
            buildNotification(PlaybackStatus.PLAYING);
            ListSoundReader.IsPlay = true;
            ListSoundReader.updateUI(getApplicationContext());
            if (IS_OPEN) {
                DetailsSoundActivity.updateUI();
            }

        }

    }

    //Becoming noisy
    private BroadcastReceiver becomingNoisyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //ic_pause audio on ACTION_AUDIO_BECOMING_NOISY
            if (AudioManager.ACTION_AUDIO_BECOMING_NOISY.equals(intent.getAction())) {
                pauseMedia();
                buildNotification(PlaybackStatus.PAUSED);
            }
        }
    };

    private void registerBecomingNoisyReceiver() {
        //register after getting audio focus
        IntentFilter intentFilter = new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY);
        registerReceiver(becomingNoisyReceiver, intentFilter);
    }

    //Broadcast for notify a new play song
    private BroadcastReceiver playNewAudio = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Get the new media index fromSharedPreferences
            //For change list of Shakh
            StorageUtil storage = new StorageUtil(getApplicationContext());
            audioArrayList = storage.loadAudio();
            audioIndex = storage.loadAudioIndex();
            if (audioIndex != -1 && audioIndex < audioArrayList.size()) {
                //indexis in a valid range
                activeAudio = audioArrayList.get(audioIndex);
            } else {
                stopSelf();
            }
            //A Play_NEW_AUDIO action received
            //reset mediaPlayer to play the new Audio
            stopMedia();
            //   mediaPlayer.reset();
            initMediaPlayer();
            updateMetaData();

        }
    };

    private void register_playNewAudio() {
        //Register playNewMedia receiver
        IntentFilter filter = new IntentFilter(ListSoundReader.Broadcast_PLAY_NEW_AUDIO);
        registerReceiver(playNewAudio, filter);
    }

    private void registerNoConnection() {
        //Register no internet receiver
        IntentFilter filter = new IntentFilter(MediaPlayerService.BROADCAST_NOT_CONNECTION);
        registerReceiver(connectivityReceiver, filter);
    }

    private void registerNoInternet() {
        //Register no internet receiver
        IntentFilter filter = new IntentFilter(MediaPlayerService.BROADCAST_NOT_INTERNET);
        registerReceiver(noInternetReceiver, filter);
    }

    public class DeleteNotification extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("tt")) {
                Toast.makeText(context, "Delete action", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initMediaSession() throws RemoteException {

        if (mediaSessionManager != null) return; //mediaSessionManager exists

        mediaSessionManager = (MediaSessionManager) getSystemService(Context.MEDIA_SESSION_SERVICE);
        //Create a new MediaSession
        mediaSession = new MediaSessionCompat(getApplicationContext(), SESIION_AUDIO_PLAYER);
        //Get MediaSessions transport controls
        transportControls = mediaSession.getController().getTransportControls();
        //set MediaSession - > ready to receive media commands
        mediaSession.setActive(true);
        //indicate that the MediaSession handles transport control commands
        //through its MediaSessionCompat.Callback.
        mediaSession.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS | MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

        //Set mediaSession'sMetaData
        updateMetaData();
        //Attach Callbackto receive MediaSession updates
        mediaSession.setCallback(new MediaSessionCompat.Callback() {
            @Override
            public void onPlay() {
                super.onPlay();
                resumeMedia();

                //ACTION_AUDIO_BECOMING_NOISY -- change in audio outputs -- BroadcastReceiver
                registerBecomingNoisyReceiver();
                //Listen for new Audio to play -- BroadcastReceiver
                register_playNewAudio();

                //Listen for Connection
                registerNoConnection();

                //Listen for Internet
                registerNoInternet();

            }

            @Override
            public void onPause() {
                super.onPause();
                pauseMedia();
                buildNotification(PlaybackStatus.PAUSED);
                ListSoundReader.IsPlay = false;
                ListSoundReader.updateUI(getApplicationContext());
                if (IS_OPEN) {
                    DetailsSoundActivity.updateUI();
                }
            }

            @Override
            public void onSkipToNext() {
                super.onSkipToNext();
                skipToNext();
                updateMetaData();
                buildNotification(PlaybackStatus.PLAYING);
                //ListSoundReader.IsPlay = true;
            }

            @Override
            public void onSkipToPrevious() {
                super.onSkipToPrevious();
                skipToPrevious();
                updateMetaData();
                buildNotification(PlaybackStatus.PLAYING);
                ListSoundReader.IsPlay = true;
            }

            @Override
            public void onStop() {
                super.onStop();
                removeNotification();
                stopMedia();
                //Stop the service
                stopSelf();
                if (IS_OPEN) {
                    //For close Activity after Remove Notification
                    Intent intent = new Intent(BROADCAST_FINISH_ACTIVITY);
                    sendBroadcast(intent);
                }
            }

            @Override
            public void onSeekTo(long pos) {
                super.onSeekTo(pos);
            }
        });


    }

    //check Internet
    private void isNetworkConnected() {
        NoInternetConnection noInternetConnection = new NoInternetConnection();
        noInternetConnection.execute("http://clients3.google.com/generate_204");
        boolean isConnected = NetworkConnection.networkConnectivity(getApplicationContext());
        File media_path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS + FILENAME);
        final File exStore = new File(media_path, activeAudio.getName_sora() + ".mp3");
        ListSoundReaderLoadingIndicator.setVisibility(View.VISIBLE);
        if (isDetailsActivityTrue) {
            DetailsSoundActivity_loading_indicator.setVisibility(View.VISIBLE);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ListSoundReaderLoadingIndicator.setVisibility(View.GONE);
                if (isDetailsActivityTrue) {
                    DetailsSoundActivity_loading_indicator.setVisibility(View.GONE);
                }
                if (exStore != null && exStore.exists()) {
                    playAyaFromEnternal(exStore);
                } else {
                    if (!isConnected) {
                        //send BroadcastReceiver to the Service -> Not Connection
                        Intent broadcastIntent = new Intent(BROADCAST_NOT_CONNECTION);
                        sendBroadcast(broadcastIntent);
                        startForeground(NOTIFICATION_ID_SERVICE, notificationBuilder.build());
                        stopMedia();
                        removeNotification();
                        stopSelf();
                        FragmentListSoundLLControlMedia.setVisibility(View.GONE);

                    } else {
                        if (!isInternet()) {
                            //send BroadcastReceiver to the Service -> Not Internet
                            Intent broadcastIntent = new Intent(BROADCAST_NOT_INTERNET);
                            sendBroadcast(broadcastIntent);
                            startForeground(NOTIFICATION_ID_SERVICE, notificationBuilder.build());
                            stopMedia();
                            stopSelf();
                            removeNotification();
                            FragmentListSoundLLControlMedia.setVisibility(View.GONE);
                        } else {
                            if (URLUtil.isValidUrl(activeAudio.getSora_link())) {
                                try {
                                    mediaPlayer.setDataSource(activeAudio.getSora_link());
                                    mediaPlayer.prepareAsync();
                                    buildNotification(PlaybackStatus.PLAYING);
                                    FragmentListSoundLLControlMedia.setVisibility(View.VISIBLE);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            } else {
                                Toast.makeText(getApplicationContext(), R.string.text_fiald, Toast.LENGTH_LONG).show();
                                startForeground(NOTIFICATION_ID_SERVICE, notificationBuilder.build());
                                stopMedia();
                                stopSelf();
                                removeNotification();
                                FragmentListSoundLLControlMedia.setVisibility(View.GONE);
                            }
                        }
                    }
                }
            }
        }, 1000);

    }

    private void updateMetaData() {
        Bitmap albumArt = BitmapFactory.decodeResource(getResources(), R.mipmap.logo);//replace with medias albumArt
        //update the current metadata
        mediaSession.setMetadata(new MediaMetadataCompat.Builder()
                .putBitmap(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON, BitmapFactory.decodeResource(getResources(), R.mipmap.logo))
                .putBitmap(MediaMetadataCompat.METADATA_KEY_ART, BitmapFactory.decodeResource(getResources(), R.mipmap.logo))
                .putBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART, albumArt)
                .putString(MediaMetadataCompat.METADATA_KEY_TITLE, activeAudio.getName_sora())
                .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, activeAudio.getName_shekh())
                .putString(MediaMetadataCompat.METADATA_KEY_ALBUM, activeAudio.getName_shekh())
                .build());
    }

    private void skipToNext() {
        if (audioIndex == audioArrayList.size() - 1) {
            //if last in playlist
            audioIndex = 0;
            activeAudio = audioArrayList.get(audioIndex);
        } else {
            //get changeTextToNext in playlist
            activeAudio = audioArrayList.get(++audioIndex);
        }
        //Update stored index
        new StorageUtil(getApplicationContext()).storeAudioIndex(audioIndex);
        stopMedia();
        //reset mediaPlayer
        mediaPlayer.reset();
        initMediaPlayer();
    }

    private void skipToPrevious() {
        if (audioIndex == 0) {
            //if first in playlist
            //set index to the last of audio list
            audioIndex = audioArrayList.size() - 1;
            activeAudio = audioArrayList.get(audioIndex);
        } else {
            //get previous in playList
            activeAudio = audioArrayList.get(--audioIndex);
        }
        //update stored index
        new StorageUtil(getApplicationContext()).storeAudioIndex(audioIndex);
        stopMedia();
        //reset mediaPlayer
        mediaPlayer.reset();
        initMediaPlayer();
    }

    private void buildNotification(PlaybackStatus playbackStatus) {

        int notificationAction = android.R.drawable.ic_media_pause; // needs to be initialized
        PendingIntent play_pauseAction = null;

        //Build a new notification according to the current state of the MediaPlayer
        if (playbackStatus == PlaybackStatus.PLAYING) {
            notificationAction = android.R.drawable.ic_media_pause;
            //Create the ic_pause action
            play_pauseAction = playbackAction(1);
        } else if (playbackStatus == PlaybackStatus.PAUSED) {
            notificationAction = android.R.drawable.ic_media_play;
            //Create the play action
            play_pauseAction = playbackAction(0);
        }

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), activeAudio.getUrl_image());//Replace with your own image

//you only need to create  the cnannel on API 26+ devices
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }

//For Delete Notification
        Intent cancelNotification = new Intent(this, DeleteNotification.class);
        cancelNotification.setAction("tt");
        PendingIntent exitPending = PendingIntent.getBroadcast(this, 0, cancelNotification, PendingIntent.FLAG_CANCEL_CURRENT);
        // The PendingIntent to launch our activity if the user selects this notification
        //Create an explicit intent for an Activity in your app
        Intent intent = new Intent(getApplicationContext(), DetailsSoundActivity.class);
        //  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);
        // Given a media session and its context (usually the component containing the session)
        // Create a NotificationCompat.Builder

        // Get the session's metadata
        controller = mediaSession.getController();
        mediaMetadata = controller.getMetadata();
        description = mediaMetadata.getDescription();
        //Create a new Notification
        //Hide the timestamp
        //.setShowWhen(false)
        notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);

        notificationBuilder.setWhen(System.currentTimeMillis())  // the time stamp
                //Set notification content information
                .setSmallIcon(activeAudio.getUrl_image())
                //.setContentText(activeAudio.getSora_name())
                //.setContentTitle(activeAudio.getAlbum())
                //.setContentInfo(activeAudio.getTitle())
                .setContentText(description.getDescription())
                .setContentTitle(description.getTitle())
                .setSubText(description.getDescription())
                .setLargeIcon(description.getIconBitmap())
                .setAutoCancel(false)
                .setOngoing(true)// Cant cancel your notification (except NotificationManger.cancel();
                //Enable launching the player by clicking the notification
                .setContentIntent(contentIntent)
                //Stop the service when the notification is swiped away
                .setDeleteIntent(exitPending)
                .setChannelId(CHANNEL_ID)
                // Make the transport controls visible on the lock screen
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                //Set the notification color
                .setColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
                .setLargeIcon(largeIcon)
                //.setPriority(NotificationCompat.PRIORITY_DEFAULT)
                //Add media control buttons that invoke intents in your media service
                .addAction(android.R.drawable.ic_media_previous, "previous", playbackAction(3))
                .addAction(notificationAction, "ic_pause", play_pauseAction)
                .addAction(android.R.drawable.ic_media_next, "changeTextToNext", playbackAction(2))
                .addAction(R.drawable.ic_exit, "close", playbackAction(4))
                //Set the Notification Media Style
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        //Attach our MediaSession token
                        .setMediaSession(mediaSession.getSessionToken())
                        //Show our playback controls in the compact notification view.
                        .setShowActionsInCompactView(0, 1, 2, 3)
                );
        //Display the notification and place the service in the foreground
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(NOTIFICATION_ID_SERVICE, notificationBuilder.build());
        } else {
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(NOTIFICATION_ID_SERVICE, notificationBuilder.build());
        }
    }

    private void removeNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(NOTIFICATION_ID_SERVICE);
    }

    private PendingIntent playbackAction(int actionNumber) {
        playbackAction = new Intent(this, MediaPlayerService.class);
        switch (actionNumber) {
            case 0:
                // Play
                playbackAction.setAction(ACTION_PLAY);
                return PendingIntent.getService(this, actionNumber, playbackAction, 0);
            case 1:
                // Pause
                playbackAction.setAction(ACTION_PAUSE);
                return PendingIntent.getService(this, actionNumber, playbackAction, 0);
            case 2:
                // Next track
                playbackAction.setAction(ACTION_NEXT);
                return PendingIntent.getService(this, actionNumber, playbackAction, 0);
            case 3:
                // Previous track
                playbackAction.setAction(ACTION_PREVIOUS);
                return PendingIntent.getService(this, actionNumber, playbackAction, 0);
            case 4:
                // stop track
                playbackAction.setAction(ACTION_STOP);
                // Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
                // stopSelf();
                return PendingIntent.getService(this, actionNumber, playbackAction, 0);
            default:
                break;
        }
        return null;
    }

    //Now that the service generates actions when the user clicks
// on the notification buttons it needs a way to handle these actions. Add the following action to the service.
    private void handleIncomingActions(Intent playbackAction) {
        if (playbackAction == null || playbackAction.getAction() == null) return;
        String actionString = playbackAction.getAction();
        if (actionString.equalsIgnoreCase(ACTION_PLAY)) {
            transportControls.play();
        } else if (actionString.equalsIgnoreCase(ACTION_PAUSE)) {
            transportControls.pause();
        } else if (actionString.equalsIgnoreCase(ACTION_NEXT)) {
            transportControls.skipToNext();
        } else if (actionString.equalsIgnoreCase(ACTION_PREVIOUS)) {
            transportControls.skipToPrevious();
        } else if (actionString.equalsIgnoreCase(ACTION_STOP)) {
            transportControls.stop();
            FragmentListSoundLLControlMedia.setVisibility(View.GONE);
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //The id of the channel
        //for create Channel for notification for Android O
        //The user visible name of the channel
        CharSequence name = "Quran playback";
        //The user visible description of the channel
        String description = "Quran Media playback controls";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
        //Configure the channel's intial preference
        notificationChannel.setLightColor(Color.GREEN);
        //Configure the notification channel.
        notificationChannel.setDescription(description);
        notificationChannel.setShowBadge(false);
        //For mute sound
        notificationChannel.setSound(null, null);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        manager.createNotificationChannel(notificationChannel);
    }
}