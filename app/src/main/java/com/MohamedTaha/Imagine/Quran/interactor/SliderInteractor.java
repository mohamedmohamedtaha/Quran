package com.MohamedTaha.Imagine.Quran.interactor;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.MohamedTaha.Imagine.Quran.helper.SharedPerefrenceHelper;
import com.MohamedTaha.Imagine.Quran.presenter.SliderPresenter;
import com.MohamedTaha.Imagine.Quran.view.SliderView;

import static com.MohamedTaha.Imagine.Quran.interactor.SplashInteractor.FIRST_TIME;

public class SliderInteractor implements SliderPresenter{
    private SliderView sliderView;
    private TextView[] dottv;
    private Context context;

    public SliderInteractor(SliderView sliderView,Context context) {
        this.sliderView = sliderView;
        this.context = context;
    }

    @Override
    public void swipePage(int position, LinearLayout linearLayout, Context context, int[] layout) {
        if (position == layout.length - 1) {
            sliderView.changeTextToNext();
        }else {
            sliderView.changeTextToSkip();
            setDotLayout(position, linearLayout, context, layout);

        }
    }
    public void setDotLayout(int page, LinearLayout linearLayout, Context context, int[] layouts) {
        if (sliderView != null){
            linearLayout.removeAllViews();
            dottv = new TextView[layouts.length];
            for (int i = 0; i < dottv.length; i++) {
                dottv[i] = new TextView(context);
                dottv[i].setText(Html.fromHtml("&#8226"));
                dottv[i].setTextSize(30);
                dottv[i].setTextColor(Color.parseColor("#ff3d00"));
                linearLayout.addView(dottv[i]);
            }

            //set current dot active
            if (dottv.length > 0) {
                dottv[page].setTextColor(Color.parseColor("#f7db07"));
            }
        }


    }
    @Override
    public void onDestroy() {
        sliderView = null;

    }

    @Override
    public void skip() {
        if (sliderView != null) {
            sliderView.skip();
            SharedPerefrenceHelper.putFirstTime(context, FIRST_TIME, true);

        }
    }
}
