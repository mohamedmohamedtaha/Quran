package com.MohamedTaha.Imagine.Quran.presenter;

import android.content.Context;
import android.widget.LinearLayout;

public interface SliderPresenter {
    void swipePage(int position, LinearLayout linearLayout, Context context, int[] layout);
    void onDestroy();
    void skip();
}
