package com.MohamedTaha.Imagine.Quran.ui.activities;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.helper.HelperClass;
import com.MohamedTaha.Imagine.Quran.helper.SharedPerefrenceHelper;
import com.MohamedTaha.Imagine.Quran.interactor.NavigationDrawarInteractor;
import com.MohamedTaha.Imagine.Quran.notification.AlarmReceiver;
import com.MohamedTaha.Imagine.Quran.notification.NotificationHelper;
import com.MohamedTaha.Imagine.Quran.presenter.NavigationDrawarPresenter;
import com.MohamedTaha.Imagine.Quran.ui.fragments.AzkarFragment;
import com.MohamedTaha.Imagine.Quran.ui.fragments.FragmentSound;
import com.MohamedTaha.Imagine.Quran.ui.fragments.GridViewFragment;
import com.MohamedTaha.Imagine.Quran.ui.fragments.PartsFragment;
import com.MohamedTaha.Imagine.Quran.view.NavigationDrawarView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.MohamedTaha.Imagine.Quran.interactor.SplashInteractor.FIRST_TIME;

public class NavigationDrawaberActivity extends AppCompatActivity implements NavigationDrawarView {
    private static final String SAVE_STATE_VIEW_PAGER = "save_state_view_pager";
    @BindView(R.id.nav_view)
    BottomNavigationView navView;
    @BindView(R.id.toobar)
    Toolbar toobar;
    @BindString(R.string.about)
    String aboutString;
    @BindString(R.string.shareApp)
    String shareApp;
    @BindString(R.string.notSupport)
    String notSupport;
    @BindString(R.string.exit_app)
    String exit_app;
    @BindView(R.id.NavigationDrawaberActivity_VPager)
    ViewPager NavigationDrawaberActivityVPager;
    private int current_fragment;
    public static MaterialSearchView searchView;
    String appPackageName;
    int notificationId;
    private NavigationDrawarPresenter presenter;
    MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawaber);
        ButterKnife.bind(this);
        presenter = new NavigationDrawarInteractor(this);
        appPackageName = getPackageName();
        //For Settings Notifications
        NotificationHelper.sendNotificationEveryHalfDay(getApplicationContext());
        NotificationHelper.enableBootRecieiver(getApplicationContext());

//        //for close Notification
//        notificationId = getIntent().getIntExtra(NOTIFICATION_ID, 1);
//        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        notificationManager.cancel(notificationId);

//
//        String[] toastMessages = getResources().getStringArray(R.array.notificationAlarm);
//        int randomIndex = new Random().nextInt(toastMessages.length - 1);
//
//        notificationId = setNotificationForShow(randomIndex);
//        addImages(notificationId);
//
//        //Get notification Manager to manage/send notification
//        //Intent to invoke app when click on notification
//        //In the sample, we want to start/launch this sample app when user clicks on notification
//        intentToRepeat = new Intent(getApplicationContext(), SwipePagesActivity.class);
//        intentToRepeat.putExtra(NOTIFICATION_ID, notificationId);
//        intentToRepeat.putIntegerArrayListExtra(SAVE_Position_Notification, (ArrayList<Integer>) images);
//
//        //set flag to restart /relaunch the app
//        intentToRepeat.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        //Pending intent to handle launch of Activity in intent above
//        PendingIntent openIntent = PendingIntent.getActivity(getApplicationContext(), NotificationHelper.ALARM_TYPE_RTC,
//                intentToRepeat, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        //Build notification
//        AlarmReceiver.createNotification(getApplicationContext(), openIntent, getString(R.string.app_name), toastMessages[randomIndex]);
//

        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (savedInstanceState != null) {
            int save = savedInstanceState.getInt(SAVE_STATE_VIEW_PAGER);
            navView.setSelectedItemId(save);
        } else {
            navView.setSelectedItemId(R.id.read_quran);
        }
        setSupportActionBar(toobar);
        //for change color text toolbar
        toobar.setTitleTextColor(Color.parseColor("#FFFFFF"));

//        NavigationDrawaberActivityVPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//                if (prevMenuItem != null){
//                    prevMenuItem.setChecked(false);
//                }else {
//                    navView.getMenu().getItem(0).setChecked(false);
//                }
//                navView.getMenu().getItem(position).setChecked(true);
//                prevMenuItem = navView.getMenu().getItem(position);
//                if (position != current_fragment) {
//                    current_fragment = position;
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//        setupViewPager(NavigationDrawaberActivityVPager);
//
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();
            if (id == current_fragment) {
                return false;
            }
            switch (id) {
                case R.id.read_quran:
                    //NavigationDrawaberActivityVPager.setCurrentItem(0);
                    GridViewFragment gridViewFragment = new GridViewFragment();
                    HelperClass.replece(gridViewFragment, getSupportFragmentManager(), R.id.frameLayout);
                    break;
                case R.id.read_parts:
                    //  NavigationDrawaberActivityVPager.setCurrentItem(1);
                    PartsFragment partsFragment = new PartsFragment();
                    HelperClass.replece(partsFragment, getSupportFragmentManager(), R.id.frameLayout);
                    break;
                case R.id.sound_quran:
                    //    NavigationDrawaberActivityVPager.setCurrentItem(2);
                    FragmentSound fragmentSound = new FragmentSound();
                    HelperClass.replece(fragmentSound, getSupportFragmentManager(), R.id.frameLayout);
                    break;
                case R.id.azkar:
                    AzkarFragment azkarFragment = new AzkarFragment();
                    HelperClass.replece(azkarFragment, getSupportFragmentManager(), R.id.frameLayout);
                    break;
//                case R.id.elslah:
//                    AzanFragment azanFragment = new AzanFragment();
//                HelperClass.replece(azanFragment, getSupportFragmentManager(), R.id.frameLayout);
//                break;
            }
            current_fragment = id;
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        presenter.exitApp(searchView, navView);
    }

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
        switch (item.getItemId()) {
            case R.id.action_share:
                presenter.shareApp(aboutString, appPackageName);
                break;
            case R.id.action_send_us:
                presenter.sendUs();
                break;
            case R.id.action_use_way:
                SharedPerefrenceHelper.putFirstTime(getApplicationContext(), FIRST_TIME, false);
                HelperClass.startActivity(getApplicationContext(), SplashActivity.class);
                break;
            case R.id.action_settings:
                HelperClass.startActivity(getApplicationContext(), SettingsActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                }
                break;
            case R.id.action_rate:
                presenter.actionRate(appPackageName);
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showMessageExitApp() {
        HelperClass.customToast(this, exit_app);
    }

    @Override
    public void exitApp() {
        HelperClass.closeApp(getApplicationContext());
    }

    @Override
    public void getDefault() {
        navView.setSelectedItemId(R.id.read_quran);
    }

    @Override
    public void getShareApp(Intent intent) {
        startActivity(Intent.createChooser(intent, shareApp));
    }

    @Override
    public void getSendUs(Intent intentEmail) {
        if (intentEmail.resolveActivity(getPackageManager()) != null) {
            startActivity(intentEmail);
        } else {
            HelperClass.customToast(this, notSupport);
        }
    }

    @Override
    public void getRateApp(Intent rateApp) {
        startActivity(rateApp);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_STATE_VIEW_PAGER, current_fragment);
    }
    //    private void setupViewPager(ViewPager viewPager){
//        AdapterForNavigation adapterForNavigation = new AdapterForNavigation(getSupportFragmentManager());
//        GridViewFragment gridViewFragment = new GridViewFragment();
//          PartsFragment partsFragment = new PartsFragment();
//           FragmentSound fragmentSound = new FragmentSound();
//           adapterForNavigation.addFragment(gridViewFragment);
//        adapterForNavigation.addFragment(partsFragment);
//        adapterForNavigation.addFragment(fragmentSound);
//        viewPager.setAdapter(adapterForNavigation);
//    }
}