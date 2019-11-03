package com.MohamedTaha.Imagine.Quran.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class AdapterSlider extends PagerAdapter {
    private int[] layouts;
    private Context context;

    public AdapterSlider(Context context, int[] layouts) {
        this.layouts = layouts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return layouts.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //  int pos = bundle.getInt(SAVE_IMAGES);
        ImageView imageView = new ImageView(context);

        Glide.with(context)
                .load(layouts[position])
                .into(imageView);
        container.addView(imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
       /*
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layouts[position], container, false);
        container.addView(view);*/
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
