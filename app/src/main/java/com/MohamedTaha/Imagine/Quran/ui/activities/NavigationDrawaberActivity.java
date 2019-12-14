package com.MohamedTaha.Imagine.Quran.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.helper.HelperClass;
import com.MohamedTaha.Imagine.Quran.helper.SharedPerefrenceHelper;
import com.MohamedTaha.Imagine.Quran.informationInrto.TapTarget;
import com.MohamedTaha.Imagine.Quran.informationInrto.TapTargetSequence;
import com.MohamedTaha.Imagine.Quran.informationInrto.TapTargetView;
import com.MohamedTaha.Imagine.Quran.mvp.interactor.NavigationDrawarInteractor;
import com.MohamedTaha.Imagine.Quran.notification.NotificationHelper;
import com.MohamedTaha.Imagine.Quran.mvp.presenter.NavigationDrawarPresenter;
import com.MohamedTaha.Imagine.Quran.ui.fragments.AzkarFragment;
import com.MohamedTaha.Imagine.Quran.ui.fragments.FragmentSound;
import com.MohamedTaha.Imagine.Quran.ui.fragments.GridViewFragment;
import com.MohamedTaha.Imagine.Quran.ui.fragments.PartsFragment;
import com.MohamedTaha.Imagine.Quran.mvp.view.NavigationDrawarView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationDrawaberActivity extends AppCompatActivity implements NavigationDrawarView {
    private static final String SAVE_STATE_VIEW_PAGER = "save_state_view_pager";
    public static final String IS_FIRST_TIME_WAY_USING = "way_sueing";

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
    private NavigationDrawarPresenter presenter;
    MenuItem prevMenuItem;
    TapTargetSequence sequence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawaber);
        ButterKnife.bind(this);
        presenter = new NavigationDrawarInteractor(this);
        appPackageName = getPackageName();

        //for show way using
        if (!SharedPerefrenceHelper.getBooleanForWayUsing(getApplicationContext(),IS_FIRST_TIME_WAY_USING,false)){
            showInformation();
            SharedPerefrenceHelper.putBooleanForWayUsing(getApplicationContext(), IS_FIRST_TIME_WAY_USING, true);
        }

        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (savedInstanceState != null) {
            int save = savedInstanceState.getInt(SAVE_STATE_VIEW_PAGER);
            navView.setSelectedItemId(save);
        } else {
            navView.setSelectedItemId(R.id.sound_quran);
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
    protected void onResume() {
        super.onResume();
        //For Settings Notifications
        NotificationHelper.sendNotificationEveryHalfDay(getApplicationContext());
        NotificationHelper.enableBootRecieiver(getApplicationContext());
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
                SharedPerefrenceHelper.removeDataForWayUsing(this);

              //  SharedPerefrenceHelper.putBooleanForWayUsing(this,IS_FIRST_TIME_WAY_USING,false);
                //SharedPerefrenceHelper.putFirstTime(getApplicationContext(), FIRST_TIME, false);
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
        navView.setSelectedItemId(R.id.sound_quran);
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

    public void showInformation() {
        toobar.inflateMenu(R.menu.menu);

        sequence = new TapTargetSequence(this)
                .targets(
                        // This tap target will target the tool bar
                        //This for R.id.action_search
                        TapTarget.forToolbarMenuItem(toobar, R.id.action_search, getString(R.string.spectial_button), getString(R.string.search_string))

                                .transparentTarget(true)
                                .outerCircleColor(R.color.blue)
                                .outerCircleAlpha(0.9f)
                                .textColor(android.R.color.white)
                                .targetCircleColor(R.color.colorAccent)
                                .titleTextSize(18)
                                .tintTarget(false)
                                .id(1),
                        //This for R.id.action_share
                        TapTarget.forToolbarMenuItem(toobar, R.id.action_share, getString(R.string.spectial_button), getString(R.string.share_string))
                                .outerCircleColor(R.color.blue)
                                .outerCircleAlpha(0.9f)
                                .textColor(android.R.color.white)
                                .targetCircleColor(R.color.colorAccent)
                                .transparentTarget(true)
                                .tintTarget(false)
                                .id(2),
                        //This for R.id.spectial_button
                        TapTarget.forToolbarOverflow(toobar,"   هذا الزر خاص", "      ضبط زمن الأشعارات  " +
                                "\n" +
                                "      عرض طريقة الاستخدام مرة أخرى    "
                                +"\n"+
                                "      وتقييم التطبيق     " +
                                        "\n"+
                                "      ومراسلتنا    ")
                                .outerCircleColor(R.color.blue)
                                .outerCircleAlpha(0.9f)
                                .textColor(android.R.color.white)
                                .targetCircleColor(R.color.colorAccent)
                                .transparentTarget(true)
                                .tintTarget(false)
                ).listener(new TapTargetSequence.Listener() {
                    // This listener will tell us when interesting(tm) events happen in regards
                    // to the sequence
                    @Override
                    public void onSequenceFinish() {
                    }

                    @Override
                    public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {
                        Log.d("TapTargetView", "Clicked on " + lastTarget.id());
                    }

                    @Override
                    public void onSequenceCanceled(TapTarget lastTarget) {
                        final AlertDialog dialog = new AlertDialog.Builder(NavigationDrawaberActivity.this)
                                .setPositiveButton(getString(R.string.sorry), null).show();
                        TapTargetView.showFor(dialog,
                                TapTarget.forView(dialog.getButton(DialogInterface.BUTTON_POSITIVE), getString(R.string.end), getString(R.string.description_string))
                                        .cancelable(true)
                                        .transparentTarget(true)
                                        .textColor(android.R.color.white)
                                        .outerCircleColor(R.color.blue)
                                        .outerCircleAlpha(0.9f)
                                        .targetCircleColor(R.color.colorAccent)
                                        .tintTarget(false), new TapTargetView.Listener() {
                                    @Override
                                    public void onTargetClick(TapTargetView view) {
                                        super.onTargetClick(view);
                                        dialog.dismiss();
                                    }

                                    @Override
                                    public void onTargetDismissed(TapTargetView view, boolean userInitiated) {
                                        dialog.dismiss();

                                    }
                                });
                    }
                });

        // You don't always need a sequence, and for that there's a single time tap target
          TapTargetView.showFor(this, TapTarget.forView(findViewById(R.id.sound_quran), getString(R.string.spectial_button),getString(R.string.sound_string) )
                .cancelable(false)
                .drawShadow(true)
                .transparentTarget(true)
                .outerCircleColor(R.color.blue)
                  .outerCircleAlpha(0.9f)
                .textColor(android.R.color.white)
                  .targetCircleColor(R.color.colorAccent)
                .tintTarget(false), new TapTargetView.Listener() {
            @Override
            public void onTargetClick(TapTargetView view) {
                super.onTargetClick(view);
                // .. which evidently starts the sequence we defined earlier
                //  sequence.start();
                navView.setSelectedItemId(R.id.read_quran);
                setTwoShow();
            }

            @Override
            public void onOuterCircleClick(TapTargetView view) {
                super.onOuterCircleClick(view);
            }

            @Override
            public void onTargetDismissed(TapTargetView view, boolean userInitiated) {
                Log.d("TapTargetViewSample", "You dismissed me :(");
            }
        });

    }

    private void setTwoShow() {
        TapTargetView.showFor(this, TapTarget.forView(findViewById(R.id.read_quran), getString(R.string.spectial_button), getString(R.string.read_string))
                .cancelable(false)
                .drawShadow(true)
                .transparentTarget(true)
                .outerCircleColor(R.color.blue)
                .outerCircleAlpha(0.9f)
                .textColor(android.R.color.white)
                .targetCircleColor(R.color.colorAccent)
                .tintTarget(false), new TapTargetView.Listener() {
            @Override
            public void onTargetClick(TapTargetView view) {
                super.onTargetClick(view);
                // .. which evidently starts the sequence we defined earlier
                navView.setSelectedItemId(R.id.read_parts);
                setShowThreeItem();
            }

            @Override
            public void onOuterCircleClick(TapTargetView view) {
                super.onOuterCircleClick(view);
            }

            @Override
            public void onTargetDismissed(TapTargetView view, boolean userInitiated) {
                Log.d("TapTargetViewSample", "You dismissed me :(");

            }
        });
    }

    private void setShowThreeItem() {
        TapTargetView.showFor(this, TapTarget.forView(findViewById(R.id.read_parts), getString(R.string.spectial_button),getString(R.string.read_parts_string)
        )
                .cancelable(false)
                .drawShadow(true)
                .transparentTarget(true)
                .outerCircleColor(R.color.blue)
                .outerCircleAlpha(0.9f)
                .textColor(android.R.color.white)
                .targetCircleColor(R.color.colorAccent)
                .tintTarget(false), new TapTargetView.Listener() {
            @Override
            public void onTargetClick(TapTargetView view) {
                super.onTargetClick(view);
                // .. which evidently starts the sequence we defined earlier
                navView.setSelectedItemId(R.id.azkar);
                setShowFourItem();
            }

            @Override
            public void onOuterCircleClick(TapTargetView view) {
                super.onOuterCircleClick(view);
            }

            @Override
            public void onTargetDismissed(TapTargetView view, boolean userInitiated) {
                Log.d("TapTargetViewSample", "You dismissed me :(");

            }
        });
    }

    private void setShowFourItem() {
        TapTargetView.showFor(this, TapTarget.forView(findViewById(R.id.azkar), getString(R.string.spectial_button), getString(R.string.read_azkar))
                .cancelable(false)
                .drawShadow(true)
                .transparentTarget(true)
                .outerCircleColor(R.color.blue)
                .outerCircleAlpha(0.9f)
                .textColor(android.R.color.white)
                .targetCircleColor(R.color.colorAccent)
                .tintTarget(false), new TapTargetView.Listener() {
            @Override
            public void onTargetClick(TapTargetView view) {
                super.onTargetClick(view);
                // .. which evidently starts the sequence we defined earlier
                sequence.start();
            }

            @Override
            public void onOuterCircleClick(TapTargetView view) {
                super.onOuterCircleClick(view);
            }

            @Override
            public void onTargetDismissed(TapTargetView view, boolean userInitiated) {
                Log.d("TapTargetViewSample", "You dismissed me :(");

            }
        });
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