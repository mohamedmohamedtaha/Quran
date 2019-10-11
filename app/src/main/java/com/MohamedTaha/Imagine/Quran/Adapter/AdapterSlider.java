package com.MohamedTaha.Imagine.Quran.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

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

        Picasso.get()
                .load(layouts[position])
                .fit()
                // .centerCrop()
                //-  .centerInside()
                .into(imageView);
        container.addView(imageView);
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
