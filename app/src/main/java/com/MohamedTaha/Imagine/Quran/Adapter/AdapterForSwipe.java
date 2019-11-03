package com.MohamedTaha.Imagine.Quran.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.MohamedTaha.Imagine.Quran.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class AdapterForSwipe extends PagerAdapter {
    Context context;
    public showDetail lisenter;
    private ArrayList<Integer> GalImages = new ArrayList<>();

    public AdapterForSwipe(Context context, ArrayList<Integer> GalImages, showDetail lisenter) {
        this.context = context;
        this.GalImages = GalImages;
        this.lisenter = lisenter;
    }

    @Override
    public int getCount() {
        return GalImages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    private int reverse(int position) {
        return getCount() - position - 1;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        ImageView imageView = new ImageView(context);

        Glide.with(context)
                .load(GalImages.get(position))
                .into(imageView);
        container.addView(imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lisenter != null) lisenter.showDetails(position);
            }
        });
    //    imageView.setRotationY(180);

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        imageView.setAnimation(animation);
        animation.start();

        return imageView;
    }

    public interface showDetail {
        void showDetails(int positon);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}