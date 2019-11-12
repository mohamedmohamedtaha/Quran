package com.MohamedTaha.Imagine.Quran.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.TextUtilsCompat;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;

import com.MohamedTaha.Imagine.Quran.Adapter.AdapterForSwipe;
import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.helper.SharedPerefrenceHelper;
import com.MohamedTaha.Imagine.Quran.helper.ShowDialog;
import com.MohamedTaha.Imagine.Quran.model.ModelSora;
import com.booking.rtlviewpager.RtlViewPager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.MohamedTaha.Imagine.Quran.interactor.GridViewFragmentInteractor.SAVE_POSITION;
import static com.MohamedTaha.Imagine.Quran.ui.fragments.GridViewFragment.SAVE_IMAGES;

public class SwipePagesActivity extends AppCompatActivity {

    ArrayList<ModelSora> images = new ArrayList<>();
    public static final String IS_TRUE = "is_true";
    //  @BindView(R.id.SwipePagesActivity_VP)
    // ViewPager SwipePagesActivityVP;
    @BindView(R.id.SwipePagesActivity_PB)
    ProgressBar SwipePagesActivityPB;
    @BindView(R.id.SwipePagesActivity_VP)
    RtlViewPager SwipePagesActivityVP;

    private int save_position;
    private int position = 1;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_pages);
        ButterKnife.bind(this);
        bundle = getIntent().getExtras();
        getArgemnets();
        createImage();
        //For Support RTL and put it in AdapterForSwipe in ImageView also
        //  SwipePagesActivityVP.setRotationY(180);
        //For Support RTL and put it in AdapterForSwipe in ImageView also
        boolean istue= TextUtilsCompat.getLayoutDirectionFromLocale(getResources().getConfiguration().locale) == ViewCompat.LAYOUT_DIRECTION_RTL;
        AdapterForSwipe adapterForSwipe = new AdapterForSwipe(this, images, new AdapterForSwipe.showDetail() {
            @Override
            public void showDetails(int positon) {
                //setNamePart(save_position);
                ShowDialog.showDialog(SwipePagesActivity.this, save_position);


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
            //int position = SharedPerefrenceHelper.getInt(this, SAVE_IMAGES_ONE, 0);
            //1 Toast.makeText(this, "sa", Toast.LENGTH_SHORT).show();
            //viewPager.setCurrentItem(position);
            ShowDialog.showDialog(this, SwipePagesActivityVP, position);

        } else {
            SwipePagesActivityVP.setCurrentItem(position);

        }

    }

    private void getArgemnets() {
        if (bundle != null) {
            Type listType = new TypeToken<List<ModelSora>>() {
            }.getType();
            String st = bundle.getString(SAVE_IMAGES);
            images = new Gson().fromJson(st, listType);
            //images = bundle.getIntegerArrayList(SAVE_IMAGES);
            position = bundle.getInt(SAVE_POSITION);
        }
    }

    private void createImage() {
        for (int i = 0; i < images.size(); i++) {
            bundle.putString(SAVE_IMAGES, images.get(i).getName_sora());

            // bundle.putInt(SAVE_IMAGES, images.get(i));
            bundle.putInt(SAVE_POSITION, position);
        }
        if (position == 2) {
            SwipePagesActivityVP.setCurrentItem(53);
        }
        SwipePagesActivityPB.setVisibility(View.GONE);
    }

    private void setNamePart(int position) {
        if (position == 0) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 1 && position <= 21) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 22 && position <= 41) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 42 && position <= 49) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 50 && position <= 61) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 62 && position <= 76) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 77 && position <= 81) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 82 && position <= 101) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 102 && position <= 105) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 106 && position <= 121) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 122 && position <= 127) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 128 && position <= 141) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 142 && position <= 150) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 151 && position <= 161) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 162 && position <= 176) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 177 && position <= 181) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 182 && position <= 186) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 187 && position <= 201) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 202 && position <= 207) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 208 && position <= 220) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position == 221) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 222 && position <= 233) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 234 && position <= 241) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 242 && position <= 248) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 249 && position <= 254) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 255 && position <= 261) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 262 && position <= 266) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 267 && position <= 281) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 282 && position <= 293) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 294 && position <= 301) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 302 && position >= 304) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 305 && position <= 311) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 312 && position <= 321) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 322 && position <= 331) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 332 && position <= 341) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 342 && position <= 349) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 350 && position <= 359) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 360 && position <= 361) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 362 && position <= 366) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 366 && position <= 376) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 377 && position <= 385) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 386 && position <= 385) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 397 && position <= 401) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 402 && position <= 404) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 405 && position <= 410) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 411 && position <= 414) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 415 && position <= 417) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 418 && position <= 421) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 422 && position <= 427) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 428 && position <= 434) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 435 && position <= 439) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 440 && position <= 441) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 442 && position <= 445) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 446 && position <= 452) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 453 && position <= 457) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 458 && position <= 461) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 462 && position <= 466) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 467 && position <= 476) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 477 && position <= 481) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position == 482) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 483 && position <= 488) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 489 && position <= 495) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 496 && position <= 498) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 499 && position <= 501) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 502 && position <= 506) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 507 && position <= 510) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 511 && position <= 514) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 515 && position <= 517) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 518 && position <= 520) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position == 521) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 522 && position <= 523) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 524 && position <= 525) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 526 && position <= 528) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 529 && position <= 530) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 531 && position <= 533) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 534 && position <= 537) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 538 && position <= 541) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 542 && position <= 544) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 545 && position <= 548) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 549 && position <= 550) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 551 && position <= 552) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 553) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 554 && position <= 555) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 556 && position <= 557) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 558 && position <= 559) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 560 && position <= 561) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 562 && position <= 563) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 564 && position <= 566) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 567 && position <= 568) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 569) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 570 && position <= 571) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 572 && position <= 573) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 574 && position <= 575) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 576) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 577 && position <= 578) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 579) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 580 && position <= 581) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else if (position >= 582 && position <= 583) {
            ShowDialog.showDialog(SwipePagesActivity.this, save_position);
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //   outState.putInt(SAVE_POSITION, save_position);
        // SharedPerefrenceHelper.putBoolean(this, IS_TRUE, true);
        SharedPerefrenceHelper.putInt(this, SAVE_IMAGES, save_position);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        save_position = savedInstanceState.getInt(SAVE_IMAGES);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.item_anim_no_thing, R.anim.item_anim_slide_from_bottom);
    }
}