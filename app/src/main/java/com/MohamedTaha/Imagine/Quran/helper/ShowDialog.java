package com.MohamedTaha.Imagine.Quran.helper;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.MohamedTaha.Imagine.Quran.R;

import static com.MohamedTaha.Imagine.Quran.ui.activities.SwipePagesActivity.IS_TRUE;
import static com.MohamedTaha.Imagine.Quran.ui.activities.SwipePagesActivity.IS_TRUE_AZKAR;
import static com.MohamedTaha.Imagine.Quran.ui.fragments.AzkarFragment.SAVE_AZKAR;
import static com.MohamedTaha.Imagine.Quran.ui.fragments.GridViewFragment.SAVE_IMAGES;

public class ShowDialog {
    public static void showDialog(Activity activity, ViewPager SwipePagesActivityVP, int position) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_show_dialog);
        TextView textView = (TextView) dialog.findViewById(R.id.show_text);
        textView.setText(activity.getResources().getString(R.string.do_want_Save));
        Button yesButton = (Button) dialog.findViewById(R.id.BT_Yes);
        Button noButton = (Button) dialog.findViewById(R.id.BT_No);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = SharedPerefrenceHelper.getInt(activity, SAVE_IMAGES, 0);
                SwipePagesActivityVP.setCurrentItem(position);
                dialog.dismiss();
            }
        });
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPerefrenceHelper.removeData(activity);
                SwipePagesActivityVP.setCurrentItem(position);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void showDialog(Activity activity, int save_position,String text) {
        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_details);
        TextView tv_save_position = (TextView) dialog.findViewById(R.id.TV_Save_Position);
        tv_save_position.setText(text);
        tv_save_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPerefrenceHelper.putBoolean(activity, IS_TRUE, true);
                SharedPerefrenceHelper.putInt(activity, SAVE_IMAGES, save_position);
                HelperClass.customToast(activity, activity.getResources().getString(R.string.save));
                dialog.dismiss();
            }
        });
      //  textView.setText(msg);
       // textView2.setText(number_part);
       // tv_number_page.setText("" + number_page);
        dialog.show();
    }

    public static void showDialogAzkar(Activity activity, int save_position,String text) {
        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_details);
        TextView tv_save_position = (TextView) dialog.findViewById(R.id.TV_Save_Position);
        tv_save_position.setText(text);
        tv_save_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPerefrenceHelper.putBooleanForAzkar(activity, IS_TRUE_AZKAR, true);
                SharedPerefrenceHelper.putIntForAzkar(activity, SAVE_AZKAR, save_position);
                HelperClass.customToast(activity, activity.getResources().getString(R.string.save));
                dialog.dismiss();
            }
        });

        //  textView.setText(msg);
        // textView2.setText(number_part);
        // tv_number_page.setText("" + number_page);
        dialog.show();
    }
    public static void showDialogForAzkar(Activity activity, ViewPager SwipePagesActivityVP, int position) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_show_dialog);
        TextView textView = (TextView) dialog.findViewById(R.id.show_text);
        textView.setText(activity.getResources().getString(R.string.do_want_Save_azkar));
        Button yesButton = (Button) dialog.findViewById(R.id.BT_Yes);
        Button noButton = (Button) dialog.findViewById(R.id.BT_No);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = SharedPerefrenceHelper.getIntForAzkar(activity, SAVE_AZKAR, 0);
                SwipePagesActivityVP.setCurrentItem(position);
                dialog.dismiss();
            }
        });
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPerefrenceHelper.removeDataForAzkar(activity);
                SwipePagesActivityVP.setCurrentItem(position);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}