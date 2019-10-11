package com.MohamedTaha.Imagine.Quran.ui.activities;

import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.helper.HelperClass;
import com.MohamedTaha.Imagine.Quran.helper.SharedPerefrenceHelper;
import com.MohamedTaha.Imagine.Quran.notification.NotificationHelper;
import com.MohamedTaha.Imagine.Quran.ui.fragments.FragmentSound;
import com.MohamedTaha.Imagine.Quran.ui.fragments.GridViewFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.MohamedTaha.Imagine.Quran.interactor.SplashInteractor.FIRST_TIME;

public class NavigationDrawaberActivity extends AppCompatActivity {

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

    private int current_fragment;
    public static MaterialSearchView searchView;
    public static final String NOTIFICATION_OPEN = "notificationOpen";
    public static final String GOOGLE_ACCOUNT_ID = "https://play.google.com/store/apps/details?id=";
    public static final String NAME_EMAIL = "mohamed01007919166@gmail.com";
    public static final String MARKET_ID = "market://details?id=";
    String appPackageName;
    int notificationId;
    private Boolean exitApp = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawaber);
        ButterKnife.bind(this);
        appPackageName = getPackageName();
        //current_fragment = R.id.read_quran;
        //For Settings Notifications
        NotificationHelper.sendNotificationEveryHalfDay(getApplicationContext());
        NotificationHelper.enableBootRecieiver(getApplicationContext());
        //for close Notification
        notificationId = getIntent().getIntExtra(NOTIFICATION_OPEN, 1);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(notificationId);

        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setSelectedItemId(R.id.read_quran);
        setSupportActionBar(toobar);
        //getSupportActionBar().setTitle("Material Search");
        toobar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        // toobar.setTitleTextColor(Color.parseColor(getString(R.color.coloroutLine)));
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
                    GridViewFragment gridViewFragment = new GridViewFragment();
                    replaceFragment(gridViewFragment);
                    break;
                //    return true;
                case R.id.sound_quran:
                    FragmentSound fragmentSound = new FragmentSound();
                    replaceFragment(fragmentSound);
                    // return true;
                    break;
            }
            current_fragment = id;
            return false;
        }
    };

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(
                R.id.frameLayout, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else if (navView.getSelectedItemId() == R.id.read_quran) {
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
        } else {
            navView.setSelectedItemId(R.id.read_quran);
        }
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
                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, R.string.nameSora);
                    String about = aboutString + GOOGLE_ACCOUNT_ID + appPackageName;
                    intent.putExtra(Intent.EXTRA_TEXT, about);
                    startActivity(Intent.createChooser(intent, shareApp));
                } catch (Exception e) {
                    e.toString();
                }
                break;
            case R.id.action_send_us:
                Intent intentEmail = new Intent(Intent.ACTION_SEND);
                intentEmail.setData(Uri.parse("mailto:"));
                intentEmail.setType("message/rfc822");
                intentEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{NAME_EMAIL});
                intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                intentEmail.putExtra(Intent.EXTRA_TEXT, "Message Body");
                intentEmail.createChooser(intentEmail, "Send mail...");

                if (intentEmail.resolveActivity(getPackageManager()) != null) {
                    startActivity(intentEmail);
                } else {
                    Toast.makeText(getApplicationContext(), notSupport, Toast.LENGTH_LONG).show();
                }
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
                try {
                    //Open the Store and show the App
                    Intent rateApp = new Intent(Intent.ACTION_VIEW);
                    rateApp.setData(Uri.parse(MARKET_ID + appPackageName));
                    startActivity(rateApp);
                } catch (ActivityNotFoundException e) {
                    //In state store there is not open by The browser
                    Intent webRate = new Intent(Intent.ACTION_VIEW);
                    webRate.setData(Uri.parse(GOOGLE_ACCOUNT_ID + appPackageName));
                    startActivity(webRate);
                }
                break;
            default:

        }
        return super.onOptionsItemSelected(item);
    }

}
