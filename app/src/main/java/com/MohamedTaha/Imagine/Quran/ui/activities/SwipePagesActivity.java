package com.MohamedTaha.Imagine.Quran.ui.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.MohamedTaha.Imagine.Quran.Adapter.AdapterForSwipe;
import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.helper.SharedPerefrenceHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.MohamedTaha.Imagine.Quran.interactor.GridViewFragmentInteractor.SAVE_POSITION;
import static com.MohamedTaha.Imagine.Quran.ui.fragments.GridViewFragment.SAVE_IMAGES;

public class SwipePagesActivity extends AppCompatActivity {

    ArrayList<Integer> images = new ArrayList<>();
    private static final String IS_TRUE = "is_true";
    TextView tv_save_position;
    @BindView(R.id.SwipePagesActivity_VP)
    ViewPager SwipePagesActivityVP;
    @BindView(R.id.SwipePagesActivity_PB)
    ProgressBar SwipePagesActivityPB;
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
        AdapterForSwipe adapterForSwipe = new AdapterForSwipe(this, images, new AdapterForSwipe.showDetail() {
            @Override
            public void showDetails(int positon) {
                setNamePart(save_position);

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

            alertDialog(this);

        } else {
            SwipePagesActivityVP.setCurrentItem(position);

        }

    }

    private void getArgemnets() {
        if (bundle != null) {
            images = bundle.getIntegerArrayList(SAVE_IMAGES);
            position = bundle.getInt(SAVE_POSITION);
        }
    }

    private void createImage() {
        for (int i = 0; i < images.size(); i++) {
            bundle.putInt(SAVE_IMAGES, images.get(i));
            bundle.putInt(SAVE_POSITION, position);
        }
        if (position == 2) {
            SwipePagesActivityVP.setCurrentItem(53);
        }
        SwipePagesActivityPB.setVisibility(View.GONE);
    }

    private void setNamePart(int position) {
        if (position == 0) {
            showDialog(SwipePagesActivity.this, getString(R.string.elfatha), getString(R.string.part_one), position);
        } else if (position >= 1 && position <= 21) {
            showDialog(SwipePagesActivity.this, getString(R.string.elbaqara), getString(R.string.part_one), position);
        } else if (position >= 22 && position <= 41) {
            showDialog(SwipePagesActivity.this, getString(R.string.elbaqara), getString(R.string.part_two), position);
        } else if (position >= 42 && position <= 49) {
            showDialog(SwipePagesActivity.this, getString(R.string.elbaqara), getString(R.string.part_three), position);
        } else if (position >= 50 && position <= 61) {
            showDialog(SwipePagesActivity.this, getString(R.string.al_emran), getString(R.string.part_three), position);
        } else if (position >= 62 && position <= 76) {
            showDialog(SwipePagesActivity.this, getString(R.string.al_emran), getString(R.string.part_four), position);
        } else if (position >= 77 && position <= 81) {
            showDialog(SwipePagesActivity.this, getString(R.string.elnsaa), getString(R.string.part_four), position);
        } else if (position >= 82 && position <= 101) {
            showDialog(SwipePagesActivity.this, getString(R.string.elnsaa), getString(R.string.part_five), position);
        } else if (position >= 102 && position <= 105) {
            showDialog(SwipePagesActivity.this, getString(R.string.elnsaa), getString(R.string.part_six), position);
        } else if (position >= 106 && position <= 121) {
            showDialog(SwipePagesActivity.this, getString(R.string.elmaeda), getString(R.string.part_six), position);
        } else if (position >= 122 && position <= 127) {
            showDialog(SwipePagesActivity.this, getString(R.string.elmaeda), getString(R.string.part_seven), position);
        } else if (position >= 128 && position <= 141) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura6), getString(R.string.part_seven), position);
        } else if (position >= 142 && position <= 150) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura6), getString(R.string.part_eight), position);
        } else if (position >= 151 && position <= 161) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura7), getString(R.string.part_eight), position);
        } else if (position >= 162 && position <= 176) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura7), getString(R.string.part_nine), position);
        } else if (position >= 177 && position <= 181) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura8), getString(R.string.part_nine), position);
        } else if (position >= 182 && position <= 186) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura8), getString(R.string.part_ten), position);
        } else if (position >= 187 && position <= 201) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura9), getString(R.string.part_ten), position);
        } else if (position >= 202 && position <= 207) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura9), getString(R.string.part_eleven), position);
        } else if (position >= 208 && position <= 220) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura10), getString(R.string.part_eleven), position);
        } else if (position == 221) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura11), getString(R.string.part_eleven), position);
        } else if (position >= 222 && position <= 235) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura11), getString(R.string.part_twelve), position);
        } else if (position >= 236 && position <= 241) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura12), getString(R.string.part_twelve), position);
        } else if (position >= 242 && position <= 248) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura12), getString(R.string.part_thirteen), position);
        } else if (position >= 249 && position <= 254) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura13), getString(R.string.part_thirteen), position);
        } else if (position >= 255 && position <= 261) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura14), getString(R.string.part_thirteen), position);
        } else if (position >= 262 && position <= 266) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura15), getString(R.string.part_fourteen), position);
        } else if (position >= 267 && position <= 281) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura16), getString(R.string.part_fourteen), position);
        } else if (position >= 282 && position <= 293) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura17), getString(R.string.part_fifteen), position);
        } else if (position >= 294 && position <= 301) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura18), getString(R.string.part_fifteen), position);
        } else if (position >= 302 && position >= 304) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura18), getString(R.string.part_sixteen), position);
        } else if (position >= 305 && position <= 311) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura19), getString(R.string.part_sixteen), position);
        } else if (position >= 312 && position <= 321) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura20), getString(R.string.part_sixteen), position);
        } else if (position >= 322 && position <= 331) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura21), getString(R.string.part_seventeen), position);
        } else if (position >= 332 && position <= 341) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura22), getString(R.string.part_seventeen), position);
        } else if (position >= 342 && position <= 349) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura23), getString(R.string.part_eighteen), position);
        } else if (position >= 350 && position <= 359) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura24), getString(R.string.part_eighteen), position);
        } else if (position >= 360 && position <= 361) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura25), getString(R.string.part_eighteen), position);
        } else if (position >= 362 && position <= 366) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura25), getString(R.string.part_nineteen), position);
        } else if (position >= 366 && position <= 376) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura26), getString(R.string.part_nineteen), position);
        } else if (position >= 377 && position <= 385) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura27), getString(R.string.part_nineteen), position);
        } else if (position >= 386 && position <= 385) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura28), getString(R.string.part_twenty), position);
        } else if (position >= 397 && position <= 401) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura29), getString(R.string.part_twenty), position);
        } else if (position >= 402 && position <= 404) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura29), getString(R.string.part_twenty_one), position);
        } else if (position >= 405 && position <= 410) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura30), getString(R.string.part_twenty_one), position);
        } else if (position >= 411 && position <= 414) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura31), getString(R.string.part_twenty_one), position);
        } else if (position >= 415 && position <= 417) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura32), getString(R.string.part_twenty_one), position);
        } else if (position >= 418 && position <= 421) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura33), getString(R.string.part_twenty_one), position);
        } else if (position >= 422 && position <= 427) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura33), getString(R.string.part_twenty_two), position);
        } else if (position >= 428 && position <= 434) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura34), getString(R.string.part_twenty_two), position);
        } else if (position >= 435 && position <= 439) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura35), getString(R.string.part_twenty_two), position);
        } else if (position >= 440 && position <= 441) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura36), getString(R.string.part_twenty_two), position);
        } else if (position >= 442 && position <= 445) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura36), getString(R.string.part_twenty_three), position);
        } else if (position >= 446 && position <= 452) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura37), getString(R.string.part_twenty_three), position);
        } else if (position >= 453 && position <= 457) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura38), getString(R.string.part_twenty_three), position);
        } else if (position >= 458 && position <= 461) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura39), getString(R.string.part_twenty_three), position);
        } else if (position >= 462 && position <= 466) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura39), getString(R.string.part_twenty_four), position);
        } else if (position >= 467 && position <= 476) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura40), getString(R.string.part_twenty_four), position);
        } else if (position >= 477 && position <= 481) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura41), getString(R.string.part_twenty_four), position);
        } else if (position == 482) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura41), getString(R.string.part_twenty_five), position);
        } else if (position >= 483 && position <= 488) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura42), getString(R.string.part_twenty_five), position);
        } else if (position >= 489 && position <= 495) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura43), getString(R.string.part_twenty_five), position);
        } else if (position >= 496 && position <= 498) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura44), getString(R.string.part_twenty_five), position);
        } else if (position >= 499 && position <= 501) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura45), getString(R.string.part_twenty_five), position);
        } else if (position >= 502 && position <= 506) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura46), getString(R.string.part_twenty_six), position);
        } else if (position >= 507 && position <= 510) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura47), getString(R.string.part_twenty_six), position);
        } else if (position >= 511 && position <= 514) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura48), getString(R.string.part_twenty_six), position);
        } else if (position >= 515 && position <= 517) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura49), getString(R.string.part_twenty_six), position);
        } else if (position >= 518 && position <= 520) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura50), getString(R.string.part_twenty_six), position);
        } else if (position == 521) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura51), getString(R.string.part_twenty_six), position);
        } else if (position >= 522 && position <= 523) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura51), getString(R.string.part_twenty_seven), position);
        } else if (position >= 524 && position <= 525) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura52), getString(R.string.part_twenty_seven), position);
        } else if (position >= 526 && position <= 528) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura53), getString(R.string.part_twenty_seven), position);
        } else if (position >= 529 && position <= 530) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura54), getString(R.string.part_twenty_seven), position);
        } else if (position >= 531 && position <= 533) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura55), getString(R.string.part_twenty_seven), position);
        } else if (position >= 534 && position <= 537) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura56), getString(R.string.part_twenty_seven), position);
        } else if (position >= 538 && position <= 541) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura57), getString(R.string.part_twenty_seven), position);
        } else if (position >= 542 && position <= 544) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura58), getString(R.string.part_twenty_eight), position);
        } else if (position >= 545 && position <= 548) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura59), getString(R.string.part_twenty_eight), position);
        } else if (position >= 549 && position <= 550) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura60), getString(R.string.part_twenty_eight), position);
        } else if (position >= 551 && position <= 552) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura61), getString(R.string.part_twenty_eight), position);
        } else if (position >= 553) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura62), getString(R.string.part_twenty_eight), position);
        } else if (position >= 554 && position <= 555) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura63), getString(R.string.part_twenty_eight), position);
        } else if (position >= 556 && position <= 557) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura64), getString(R.string.part_twenty_eight), position);
        } else if (position >= 558 && position <= 559) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura65), getString(R.string.part_twenty_eight), position);
        } else if (position >= 560 && position <= 561) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura66), getString(R.string.part_twenty_eight), position);
        } else if (position >= 562 && position <= 563) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura67), getString(R.string.part_twenty_nine), position);
        } else if (position >= 564 && position <= 566) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura68), getString(R.string.part_twenty_nine), position);
        } else if (position >= 567 && position <= 568) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura69), getString(R.string.part_twenty_nine), position);
        } else if (position >= 569) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura70), getString(R.string.part_twenty_nine), position);
        } else if (position >= 570 && position <= 571) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura71), getString(R.string.part_twenty_nine), position);
        } else if (position >= 572 && position <= 573) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura72), getString(R.string.part_twenty_nine), position);
        } else if (position >= 574 && position <= 575) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura73), getString(R.string.part_twenty_nine), position);
        } else if (position >= 576) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura74), getString(R.string.part_twenty_nine), position);
        } else if (position >= 577 && position <= 578) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura75), getString(R.string.part_twenty_nine), position);
        } else if (position >= 579) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura76), getString(R.string.part_twenty_nine), position);
        } else if (position >= 580 && position <= 581) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura77), getString(R.string.part_twenty_nine), position);
        } else if (position >= 582 && position <= 583) {
            showDialog(SwipePagesActivity.this, getString(R.string.Sura78), getString(R.string.part_thirty), position);
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }

    }

    private void alertDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle("إشعار");
        builder.setMessage("هل تريد الرجوع إلى الصفحة المحفوظة");
        builder.setIcon(R.mipmap.iconqoran);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int position = SharedPerefrenceHelper.getInt(context, SAVE_IMAGES, 0);
                SwipePagesActivityVP.setCurrentItem(position);

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPerefrenceHelper.removeData(context);
                SwipePagesActivityVP.setCurrentItem(position);

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showDialog(Activity activity, String msg, String number_part, int number_page) {
        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_details);

        TextView textView = (TextView) dialog.findViewById(R.id.TV_NAME_A);
        TextView textView2 = (TextView) dialog.findViewById(R.id.TV_Name_B);
        tv_save_position = (TextView) dialog.findViewById(R.id.TV_Save_Position);
        TextView tv_number_page = (TextView) dialog.findViewById(R.id.TV_Number_Page);


        tv_save_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPerefrenceHelper.putBoolean(SwipePagesActivity.this, IS_TRUE, true);
                SharedPerefrenceHelper.putInt(SwipePagesActivity.this, SAVE_IMAGES, save_position);
                Toast.makeText(SwipePagesActivity.this, getString(R.string.save), Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });
        textView.setText(msg);
        textView2.setText(number_part);
        tv_number_page.setText("" + number_page);
        dialog.show();


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
        overridePendingTransition(R.anim.item_anim_no_thing,R.anim.item_anim_slide_from_bottom);

    }
}
