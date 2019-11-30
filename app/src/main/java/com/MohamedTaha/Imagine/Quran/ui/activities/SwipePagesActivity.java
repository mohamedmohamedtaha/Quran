package com.MohamedTaha.Imagine.Quran.ui.activities;

import android.app.NotificationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.MohamedTaha.Imagine.Quran.Adapter.AdapterForAzkarSwipe;
import com.MohamedTaha.Imagine.Quran.Adapter.AdapterForSwipe;
import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.helper.HelperClass;
import com.MohamedTaha.Imagine.Quran.helper.SharedPerefrenceHelper;
import com.MohamedTaha.Imagine.Quran.helper.ShowDialog;
import com.MohamedTaha.Imagine.Quran.model.ModelAzkar;
import com.booking.rtlviewpager.RtlViewPager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.MohamedTaha.Imagine.Quran.interactor.GridViewFragmentInteractor.SAVE_POSITION;
import static com.MohamedTaha.Imagine.Quran.notification.AlarmReceiver.NOTIFICATION_ID;
import static com.MohamedTaha.Imagine.Quran.ui.fragments.AzkarFragment.SAVE_AZKAR;
import static com.MohamedTaha.Imagine.Quran.ui.fragments.AzkarFragment.SAVE_POTION_AZKAR;
import static com.MohamedTaha.Imagine.Quran.ui.fragments.GridViewFragment.SAVE_IMAGES;
import static com.MohamedTaha.Imagine.Quran.ui.fragments.GridViewFragment.SAVE_Position_Notification;
import static com.MohamedTaha.Imagine.Quran.ui.fragments.GridViewFragment.SAVE_STATE;

public class SwipePagesActivity extends AppCompatActivity {
    ArrayList<Integer> images = new ArrayList<>();
    ArrayList<Integer> imagesNotification = new ArrayList<>();

    public static final String IS_TRUE = "is_true";
    public static final String IS_TRUE_AZKAR = "is_true_azkar";
    @BindView(R.id.SwipePagesActivity_PB)
    ProgressBar SwipePagesActivityPB;
    @BindView(R.id.SwipePagesActivity_VP)
    RtlViewPager SwipePagesActivityVP;
    List<ModelAzkar> modelAzkarList = new ArrayList<>();
    private int save_position;
    private int save_position_azkar;
    private int position = 1;
    private int position_azkar = 0;
    Bundle bundle;
    int notificationId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_pages);
        ButterKnife.bind(this);
        bundle = getIntent().getExtras();
        //to Check before change Language
        String language_name = Locale.getDefault().getLanguage();
        if (language_name != "ar") {
            HelperClass.change_language("ar", this);
        }
        //for close Notification
        notificationId = getIntent().getIntExtra(NOTIFICATION_ID, -1);
        if (notificationId >= 0) {
            getArgemnetsNotification();
            createImageNotification();
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.cancel(notificationId);
            AdapterForSwipe adapterForSwipe = new AdapterForSwipe(this, imagesNotification);

            SwipePagesActivityVP.setAdapter(adapterForSwipe);
            SwipePagesActivityVP.setCurrentItem(notificationId);
            Log.i("Number :", String.valueOf(notificationId));

        } else if (bundle.getBoolean(SAVE_STATE) == true) {
            getArgemnets();
            createImage();
            AdapterForSwipe adapterForSwipe = new AdapterForSwipe(this, images, new AdapterForSwipe.showDetail() {
                @Override
                public void showDetails(int positon) {
                    ShowDialog.showDialog(SwipePagesActivity.this, save_position, getString(R.string.save_position));
                }
            });
            SwipePagesActivityVP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    save_position = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
            SwipePagesActivityVP.setAdapter(adapterForSwipe);

            if (SharedPerefrenceHelper.getBoolean(this, IS_TRUE, false)) {
                ShowDialog.showDialog(this, SwipePagesActivityVP, position);
            } else {
                SwipePagesActivityVP.setCurrentItem(position);
            }

        } else {
            getArgemnetsForAzkar();
            AdapterForAzkarSwipe adapterForAzkarSwipe = new AdapterForAzkarSwipe(SwipePagesActivity.this, modelAzkarList, new AdapterForAzkarSwipe.showDetail() {
                @Override
                public void showDetails(int positon) {
                    ShowDialog.showDialogAzkar(SwipePagesActivity.this, save_position_azkar, getString(R.string.save_position_alzekr));
                }
            });
            SwipePagesActivityVP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    save_position_azkar = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
            SwipePagesActivityVP.setAdapter(adapterForAzkarSwipe);

            if (SharedPerefrenceHelper.getBooleanForAzkar(this, IS_TRUE_AZKAR, false)) {
                ShowDialog.showDialogForAzkar(this, SwipePagesActivityVP, position_azkar);
            } else {
                SwipePagesActivityVP.setCurrentItem(position_azkar);
            }
        }
    }

    private void getArgemnets() {
        if (bundle != null) {
            images = bundle.getIntegerArrayList(SAVE_IMAGES);
            position = bundle.getInt(SAVE_POSITION);
        }
    }
    private void getArgemnetsNotification() {
        if (bundle != null) {
            imagesNotification = bundle.getIntegerArrayList(SAVE_Position_Notification);
         //   position = bundle.getInt(SAVE_POSITION);
        }
    }

    private void getArgemnetsForAzkar() {
        if (bundle != null) {
            Type listType = new TypeToken<List<ModelAzkar>>() {
            }.getType();
            String st = bundle.getString(SAVE_AZKAR);
            modelAzkarList = new Gson().fromJson(st, listType);
            position_azkar = bundle.getInt(SAVE_POTION_AZKAR);
        }
        SwipePagesActivityPB.setVisibility(View.GONE);
    }
    private void createImageNotification() {
        if (imagesNotification != null){
            for (int i = 0; i < imagesNotification.size(); i++) {
                bundle.putInt(SAVE_Position_Notification, imagesNotification.get(i));
                //    bundle.putInt(SAVE_POSITION, position);
            }
            SwipePagesActivityPB.setVisibility(View.GONE);
            Log.i("Arrayy: ", "2");

        }else {
            Log.i("Arrayy: ", "1");
        }


    }
    private void createImage() {
        for (int i = 0; i < images.size(); i++) {
            bundle.putInt(SAVE_IMAGES, images.get(i));
            bundle.putInt(SAVE_POSITION, position);
        }
        SwipePagesActivityPB.setVisibility(View.GONE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        SharedPerefrenceHelper.putInt(this, SAVE_IMAGES, save_position);
        SharedPerefrenceHelper.putInt(this, SAVE_AZKAR, save_position_azkar);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        save_position = savedInstanceState.getInt(SAVE_IMAGES);
        save_position_azkar = savedInstanceState.getInt(SAVE_AZKAR);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imagesNotification.clear();
        images.clear();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.item_anim_no_thing, R.anim.item_anim_slide_from_bottom);
    }
}