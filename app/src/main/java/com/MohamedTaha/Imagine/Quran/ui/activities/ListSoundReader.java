package com.MohamedTaha.Imagine.Quran.ui.activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.MohamedTaha.Imagine.Quran.Adapter.RecycleViewReaderAdapter;
import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.helper.HelperClass;
import com.MohamedTaha.Imagine.Quran.helper.Utilities;
import com.MohamedTaha.Imagine.Quran.helper.checkConnection.NetworkConnection;
import com.MohamedTaha.Imagine.Quran.helper.checkConnection.NoInternetConnection;
import com.MohamedTaha.Imagine.Quran.helper.util.PlayerConstants;
import com.MohamedTaha.Imagine.Quran.helper.util.StorageUtil;
import com.MohamedTaha.Imagine.Quran.mvp.model.ImageModel;
import com.MohamedTaha.Imagine.Quran.receiver.ConnectivityReceiver;
import com.MohamedTaha.Imagine.Quran.receiver.DownloadReceiver;
import com.MohamedTaha.Imagine.Quran.receiver.NoInternetReceiver;
import com.MohamedTaha.Imagine.Quran.service.MediaPlayerService;
import com.MohamedTaha.Imagine.Quran.viewmodel.SoundViewHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.MohamedTaha.Imagine.Quran.Adapter.ImageAdapter.SHEKH_ID;
import static com.MohamedTaha.Imagine.Quran.Adapter.ImageAdapter.SHEKH_NAME;
import static com.MohamedTaha.Imagine.Quran.helper.checkConnection.NoInternetConnection.isInternet;
import static com.MohamedTaha.Imagine.Quran.service.MediaPlayerService.BROADCAST_NOT_CONNECTION;
import static com.MohamedTaha.Imagine.Quran.service.MediaPlayerService.activeAudio;
import static com.MohamedTaha.Imagine.Quran.service.MediaPlayerService.mediaPlayer;
import static com.MohamedTaha.Imagine.Quran.service.MediaPlayerService.transportControls;

/**
 * Created by MANASATT on 20/08/17.
 */
public class ListSoundReader extends AppCompatActivity {
    @BindView(R.id.recycleViewSound)
    RecyclerView recycleViewSound;
    @BindView(R.id.Fragment_ListSound_TV_No_Data)
    TextView FragmentListSoundTVNoData;
    @BindView(R.id.Fragment_List_Sound_TB)
    Toolbar FragmentListSoundTB;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    @BindView(R.id.btnPrevious)
    Button btnPrevious;
    @BindView(R.id.btnStop)
    Button btnStop;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.Fragment_List_Sound_TV_Name_Sora)
    TextView FragmentListSoundTVNameSora;
    @BindView(R.id.progressBar)
    SeekBar seekBar;
    RecycleViewReaderAdapter recycleViewAdaptor;
    public static ProgressBar ListSoundReaderLoadingIndicator;
    public static final String BROADCAST_INVISABLE = "com.example.createmediaplayer.invisable";

    public static boolean isServiceRunning;

    private ArrayList<ImageModel> respones;
    public static TextView textNowPlaying;

    public static Button btnPlay;
    public static Button btnPause;

    public static TextView textBufferDuration;
    public static TextView textDuration;
    public static MaterialCardView FragmentListSoundLLControlMedia;

    private Menu globalMenu;
    Utilities utilities;
    Intent intent;
    private static CircleImageView imageViewAlbumArt;
    Uri music_uri;
    private long Music_DownloadId;
    DownloadManager downloadManager;
    //Create Folder to save Koran
    public static String FILENAME = null;
    private String urlLink;
    private String name_sora;
    File mediaStorageDir;
    File media_path = null;
    private SoundViewHolder nameSoraViewHolder;
    int totalDuration;

    int poisition;
    int position_seekBar;
    String imageModelTest = null;
    public static boolean IsPlay = true;
    //for Service
    public static final String Broadcast_PLAY_NEW_AUDIO = "com.example.createmediaplayer.PlayNewAudio";
    //Create arrayList from Audio class
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    Intent playeIntent;
    private ConnectivityReceiver connectivityReceiver = null;
    private NoInternetReceiver noInternetReceiver = null;
    private DownloadReceiver downloadReceiver = null;
    long downloadReference = 0;

    Dialog dialog;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (connectivityReceiver != null) {
            unregisterReceiver(connectivityReceiver);
        }
        if (noInternetReceiver != null) {
            unregisterReceiver(noInternetReceiver);
        }
        if (downloadReceiver != null) {
            unregisterReceiver(downloadReceiver);
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list_sound);
        ButterKnife.bind(this);
        // Create a network change broadcast receiver.
        connectivityReceiver = new ConnectivityReceiver();
        noInternetReceiver = new NoInternetReceiver();
        utilities = new Utilities();
        isServiceRunning = Utilities.isServiceRunning(MediaPlayerService.class.getName(), getApplicationContext());
        FragmentListSoundLLControlMedia = (MaterialCardView) findViewById(R.id.Fragment_List_Sound_LL_Control_Media);
        ListSoundReaderLoadingIndicator = (ProgressBar) findViewById(R.id.ListSoundReader_loading_indicator);
        imageViewAlbumArt = (CircleImageView) findViewById(R.id.imageViewAlbumArt);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPause = (Button) findViewById(R.id.btnPause);

        dialog = new Dialog(this);


        //   broadCastReceverDownload();

        if (!isServiceRunning) {
            FragmentListSoundLLControlMedia.setVisibility(View.GONE);
        }
        playeIntent = new Intent(ListSoundReader.this, MediaPlayerService.class);
        intent = getIntent();
        textBufferDuration = (TextView) findViewById(R.id.textBufferDuration);
        textDuration = (TextView) findViewById(R.id.textDuration);
        textNowPlaying = (TextView) findViewById(R.id.textNowPlaying);
        seekBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);

        custom_toolbar();
        //For ReDraw the Menu
        invalidateOptionsMenu();
        respones = new ArrayList<>();
        Intent bundle = getIntent();
        if (bundle != null) {
            poisition = new Gson().fromJson(bundle.getStringExtra(SHEKH_ID), Integer.class);
            imageModelTest = new Gson().fromJson(bundle.getStringExtra(SHEKH_NAME), String.class);
        }
        FILENAME = "/" + imageModelTest + "/";
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        FragmentListSoundTVNameSora.setText(imageModelTest);
        //For start position from right don't left
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getApplicationContext(), 2) {
            @Override
            protected boolean isLayoutRTL() {
                return true;
            }
        };
        //For put Space for RecyclerView
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycleViewSound.getContext(),
//                linearLayoutManager.getOrientation());
//        recycleViewSound.addItemDecoration(dividerItemDecoration);
        recycleViewSound.setLayoutManager(linearLayoutManager);

        nameSoraViewHolder = ViewModelProviders.of(this).get(SoundViewHolder.class);
        nameSoraViewHolder.getAllNameSora(poisition).observe(this, new Observer<ArrayList<ImageModel>>() {
            @Override
            public void onChanged(ArrayList<ImageModel> imageModels) {
                respones = imageModels;
                recycleViewAdaptor = new RecycleViewReaderAdapter(getApplicationContext(), respones, new RecycleViewReaderAdapter.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        playAudio(position);
                    }

                }, new RecycleViewReaderAdapter.DownloadMusic() {
                    @Override
                    public void download(int position) {
                        urlLink = respones.get(position).getSora_link();
                        name_sora = respones.get(position).getName_sora();
                        checkPermistion();
                    }
                });
                //For Animation
                LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(getApplicationContext(), R.anim.layout_fall_dwon);
                recycleViewSound.setLayoutAnimation(controller);
                recycleViewSound.setAdapter(recycleViewAdaptor);
                recycleViewSound.scheduleLayoutAnimation();

            }
        });
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                recycleViewAdaptor = new RecycleViewReaderAdapter(getApplicationContext(), respones, new RecycleViewReaderAdapter.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        playAudio(position);
                    }
                }, new RecycleViewReaderAdapter.DownloadMusic() {
                    @Override
                    public void download(int position) {
                        urlLink = respones.get(position).getSora_link();
                        name_sora = respones.get(position).getName_sora();
                        checkPermistion();
                    }
                });
                recycleViewSound.setAdapter(recycleViewAdaptor);
                FragmentListSoundTVNoData.setVisibility(View.GONE);
                recycleViewSound.setVisibility(View.VISIBLE);
            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                    List<ImageModel> stringList = new ArrayList<>();
                    for (ImageModel item : respones) {
                        if (item.getName_sora().contains(newText)) {
                            stringList.add(item);
                        }
                    }
                    if (!stringList.isEmpty()) {
                        recycleViewAdaptor = new RecycleViewReaderAdapter(getApplicationContext(), stringList, new RecycleViewReaderAdapter.ClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                playAudio(stringList.get(position).getPosition());
                            }
                        }, new RecycleViewReaderAdapter.DownloadMusic() {
                            @Override
                            public void download(int position) {
                                urlLink = stringList.get(position).getSora_link();
                                name_sora = stringList.get(position).getName_sora();
                                checkPermistion();
                            }
                        });
                        recycleViewSound.setAdapter(recycleViewAdaptor);
                        FragmentListSoundTVNoData.setVisibility(View.GONE);
                        recycleViewSound.setVisibility(View.VISIBLE);
                    } else {
                        FragmentListSoundTVNoData.setVisibility(View.VISIBLE);
                        recycleViewSound.setVisibility(View.GONE);
                    }
                } else {
                    //If search is Null return default
                    FragmentListSoundTVNoData.setVisibility(View.GONE);
                    recycleViewSound.setVisibility(View.VISIBLE);
                    recycleViewAdaptor = new RecycleViewReaderAdapter(getApplicationContext(), respones, new RecycleViewReaderAdapter.ClickListener() {
                        @Override
                        public void onClick(View view, int position) {
                            playAudio(position);
                        }
                    }, new RecycleViewReaderAdapter.DownloadMusic() {
                        @Override
                        public void download(int position) {
                            urlLink = respones.get(position).getSora_link();
                            name_sora = respones.get(position).getName_sora();
                            checkPermistion();
                        }
                    });
                    recycleViewSound.setAdapter(recycleViewAdaptor);
                }
                return false;
            }
        });
    }

    private void downloadSora() {
        //check Internet
        NoInternetConnection noInternetConnection = new NoInternetConnection();
        noInternetConnection.execute("http://clients3.google.com/generate_204");
        boolean isConnected = NetworkConnection.networkConnectivity(getApplicationContext());
        ListSoundReaderLoadingIndicator.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          if (!isConnected) {
                                              //send BroadcastReceiver to the Service -> Not Connection
                                              Intent broadcastIntent = new Intent(BROADCAST_NOT_CONNECTION);
                                              sendBroadcast(broadcastIntent);
                                              ListSoundReaderLoadingIndicator.setVisibility(View.GONE);
                                          } else {
                                              if (!isInternet()) {
                                                  ListSoundReaderLoadingIndicator.setVisibility(View.GONE);
                                                  //send BroadcastReceiver to the Service -> Not Internet
                                              } else {
                                                  ListSoundReaderLoadingIndicator.setVisibility(View.GONE);
                                                  dialog.setCancelable(false);
                                                  dialog.setContentView(R.layout.custom_show_dialog);
                                                  TextView textView = (TextView) dialog.findViewById(R.id.show_text);
                                                  textView.setText(getResources().getString(R.string.do_want_Save_sound) + " " + name_sora);
                                                  Button yesButton = (Button) dialog.findViewById(R.id.BT_Yes);
                                                  Button noButton = (Button) dialog.findViewById(R.id.BT_No);
                                                  yesButton.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
                                                          music_uri = Uri.parse(urlLink);
                                                          Music_DownloadId = DownloadData(music_uri, name_sora);
                                                          //oast.makeText(ListSoundReader.this, "poisition" + poisition, Toast.LENGTH_SHORT).show();

                                                          dialog.dismiss();
                                                      }
                                                  });
                                                  noButton.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
                                                          dialog.dismiss();
                                                      }
                                                  });
                                                  dialog.show();
                                              }
                                          }
                                      }
                                  }
                , 1000);

    }

    private void download() {
        final ProgressDialog progressBarDialog = new ProgressDialog(this);
        progressBarDialog.setTitle("Download App Data, Please Wait");

        progressBarDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBarDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,
                                int whichButton) {
                // Toast.makeText(getBaseContext(),
                //       "OK clicked!", Toast.LENGTH_SHORT).show();
            }
        });
        progressBarDialog.setProgress(0);

        new Thread(new Runnable() {

            @Override
            public void run() {

                boolean downloading = true;

                DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                while (downloading) {

                    DownloadManager.Query q = new DownloadManager.Query();
                    q.setFilterById(Music_DownloadId); //filter by id which you have receieved when reqesting download from download manager
                    Cursor cursor = manager.query(q);
                    cursor.moveToFirst();
                    int bytes_downloaded = cursor.getInt(cursor
                            .getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                    int bytes_total = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));

                    if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                        downloading = false;
                    }

                    final int dl_progress = (int) ((bytes_downloaded * 100l) / bytes_total);

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            progressBarDialog.setProgress((int) dl_progress);

                        }
                    });

                    // Log.d(Constants.MAIN_VIEW_ACTIVITY, statusMessage(cursor));
                    cursor.close();
                }

            }
        }).start();


        //show the dialog
        progressBarDialog.show();

    }

    private void registerNoConnection() {
        //Register no internet receiver
        IntentFilter filter = new IntentFilter(MediaPlayerService.BROADCAST_NOT_CONNECTION);
        registerReceiver(connectivityReceiver, filter);
    }

    private void registerDownloadSound() {
        //Register no internet receiver
        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(downloadReceiver, intentFilter);
    }

    private void registerNoInternet() {
        //Register no internet receiver
        IntentFilter filter = new IntentFilter(MediaPlayerService.BROADCAST_NOT_INTERNET);
        registerReceiver(noInternetReceiver, filter);
    }

    public void custom_toolbar() {
        setSupportActionBar(FragmentListSoundTB);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //for delete label for Activity
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void playAudio(int audioIndex) {
        //  //Check is service is active
        StorageUtil storageUtil = new StorageUtil(getApplicationContext());
        storageUtil.storeAudio(respones);
        storageUtil.storeAudioIndex(audioIndex);
        if (!isServiceRunning) {
            //Store Serializable audioList to SharedPreferences
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                ContextCompat.startForegroundService(getApplicationContext(), playeIntent);
            } else {
                startService(playeIntent);
            }
            isServiceRunning = true;
        } else {
            //Service is active
            //send BroadcastReceiver to the Service -> PLAY_NEW_AUDIO
            Intent broadcastIntent = new Intent(Broadcast_PLAY_NEW_AUDIO);
            sendBroadcast(broadcastIntent);
        }
    }

    private long DownloadData(Uri uri, String name_sora) {
        downloadReference = 0;
        //Create Requect for android download manager
        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        //Setting title of request
        request.setTitle(FILENAME + name_sora);
        //Setting description of request
        request.setDescription(getString(R.string.setDescriptionRequet));
        //Setting Show Notification After downloaded
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //Set the local destination for the download file to a path within the application's external files directory
        media_path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS + FILENAME);

        //check download folder for the App private
        media_path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS + FILENAME);
        //check download folder Global
        mediaStorageDir = new File(media_path, name_sora + getString(R.string.mp3));
        if (mediaStorageDir != null && mediaStorageDir.exists()) {
            HelperClass.customToast(this, getString(R.string.send_problem_string));
        } else {
            request.setVisibleInDownloadsUi(true);
            request.setDestinationInExternalFilesDir(ListSoundReader.this, Environment.DIRECTORY_DOWNLOADS + FILENAME
                    , name_sora + getString(R.string.mp3));
            //Enqueue download and save the referenceId
            downloadReference = downloadManager.enqueue(request);
            HelperClass.customToast(this, getString(R.string.download_sound));
            //Listen for Download Sound
            downloadReceiver = new DownloadReceiver(downloadReference, this);
            registerDownloadSound();

        }
        return downloadReference;
    }
//    private int getDownloadStatus(){
//        DownloadManager.Query query = new DownloadManager.Query();
//        query.setFilterById(downloadReference);
//
//        Cursor cursor = downloadManager.query(query);
//        if (cursor.moveToFirst()){
//            int columIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
//            int status = cursor.getInt(columIndex);
//            return status;
//
//        }
//        return DownloadManager.ERROR_UNKNOWN;
//    }
//    private void broadCastReceverDownload(){
//        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
//        registerReceiver(new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                Long  broadCastDownload = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
//                if (broadCastDownload == downloadReference){
//                    if (getDownloadStatus() == DownloadManager.STATUS_SUCCESSFUL){
//                        HelperClass.customToast((Activity) context, getString(R.string.download_successful));
//                    }else {
//                        HelperClass.customToast((Activity) context, getString(R.string.download_field));
//
//                    }
//
//                }
//
//            }
//        },intentFilter);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem SearchItem = menu.findItem(R.id.action_search);
        searchView.setMenuItem(SearchItem);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    private void checkPermistion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}
                        , MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            } else {
                downloadSora();
            }
        } else {
            downloadSora();

        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Download Sora
                    downloadSora();
                } else {
                    if (shouldShowRequestPermissionRationale(permissions[0])) {
                        new AlertDialog.Builder(this)
                                .setMessage(getString(R.string.something_error))
                                .setPositiveButton(getString(R.string.allow), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        checkPermistion();
                                    }
                                })
                                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).create().show();
                    } else {
                        new AlertDialog.Builder(this)
                                .setTitle(getString(R.string.error))
                                .setMessage(getString(R.string.grand_permission))
                                .setPositiveButton(getString(R.string.settings), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent();
                                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        Uri uri = Uri.fromParts(getString(R.string.package_string), getPackageName(), null);
                                        intent.setData(uri);
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton(getString(R.string.error), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).create().show();
                    }
                }
                return;
            }
        }
    }

    //For Delete those menus from that page
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        globalMenu = menu;
        globalMenu.findItem(R.id.action_send_us).setVisible(false);
        globalMenu.findItem(R.id.action_share).setVisible(false);
        globalMenu.findItem(R.id.action_use_way).setVisible(false);
        globalMenu.findItem(R.id.action_settings).setVisible(false);
        globalMenu.findItem(R.id.action_rate).setVisible(false);
        return super.onPrepareOptionsMenu(globalMenu);
    }

    public static void updateUI(Context context) {
        if (!IsPlay) {
            btnPlay.setVisibility(View.VISIBLE);
            btnPause.setVisibility(View.GONE);
        } else {
            btnPlay.setVisibility(View.GONE);
            btnPause.setVisibility(View.VISIBLE);
        }
        textNowPlaying.setText(activeAudio.getName_sora() + " / " + activeAudio.getName_shekh());
        Glide.with(context)
                .load(activeAudio.getUrl_image())
                .apply(new RequestOptions().placeholder(R.mipmap.logo).centerCrop())
                .into(imageViewAlbumArt);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Listen For not Connection
        registerNoConnection();
        //Listen for not Internet
        registerNoInternet();

        PlayerConstants.PROGRESSER_HANDLER = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Integer i[] = (Integer[]) msg.obj;
                totalDuration = i[1];
                textBufferDuration.setText("" + utilities.milliSecondsToTimer(i[0]));
                textDuration.setText("" + utilities.milliSecondsToTimer(i[1]));
                seekBar.setProgress(i[2]);
            }
        };
        //   boolean isServiceRunning = Utilities.isServiceRunning(MediaPlayerService.class.getName(), getApplicationContext());
        if (isServiceRunning) {
            updateUI(getApplicationContext());
        }


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            //    position_seekBar  = utilities.progressToTimer(seekBar.getProgress(),totalDuration);
                           }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                    position_seekBar  = utilities.progressToTimer(seekBar.getProgress(),totalDuration);
                mediaPlayer.seekTo(position_seekBar);
            }
        });
    }

    @OnClick({R.id.imageViewAlbumArt, R.id.btnPrevious, R.id.btnPlay, R.id.btnPause, R.id.btnStop, R.id.btnNext, R.id.Fragment_List_Sound_LL_Control_Media})
    public void onViewClicked(View view) {
        Intent openDetails = new Intent(ListSoundReader.this, DetailsSoundActivity.class);
        switch (view.getId()) {
            case R.id.imageViewAlbumArt:
                startActivity(openDetails);
                overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing);
                break;
            case R.id.btnPrevious:
                transportControls.skipToPrevious();
                IsPlay = true;
                updateUI(getApplicationContext());
                break;
            case R.id.btnPlay:
                transportControls.play();
                IsPlay = true;
                updateUI(getApplicationContext());
                break;
            case R.id.btnPause:
                transportControls.pause();
                IsPlay = false;
                updateUI(getApplicationContext());
                break;
            case R.id.btnStop:
                //service is active
                stopService(playeIntent);
                FragmentListSoundLLControlMedia.setVisibility(View.GONE);
                isServiceRunning = false;

                break;
            case R.id.btnNext:
                transportControls.skipToNext();
                IsPlay = true;
                updateUI(getApplicationContext());
                break;
            case R.id.Fragment_List_Sound_LL_Control_Media:
                Bundle bundle = new Bundle();
                bundle.putString(SHEKH_ID, new Gson().toJson(poisition));
                bundle.putString(SHEKH_NAME, new Gson().toJson(activeAudio.getName_shekh()));
                openDetails.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                openDetails.putExtras(bundle);
                startActivity(openDetails);
                overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing);
                break;
        }
    }
}