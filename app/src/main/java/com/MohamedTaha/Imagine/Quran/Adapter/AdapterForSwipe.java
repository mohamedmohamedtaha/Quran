package com.MohamedTaha.Imagine.Quran.Adapter;

import android.content.Context;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.MohamedTaha.Imagine.Quran.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class AdapterForSwipe extends PagerAdapter {
    Context context;
    public showDetail lisenter;
    private ArrayList<Integer> GalImages = new ArrayList<>();
    //private Bundle bundle = new Bundle();

    public AdapterForSwipe(Context context, ArrayList<Integer> GalImages, showDetail lisenter) {
        this.context = context;
        this.GalImages = GalImages;
        this.lisenter = lisenter;
       // this.bundle = bundle;
    }

    @Override
    public int getCount() {
        return GalImages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
      //  int pos = bundle.getInt(SAVE_IMAGES);
        ImageView imageView = new ImageView(context);

        Picasso.get()
                .load(GalImages.get(position))
                .fit()
               // .centerCrop()
               //-  .centerInside()
                .into(imageView);
        container.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lisenter != null) lisenter.showDetails(position);
            }
        });

        Animation animation = AnimationUtils.loadAnimation(context,R.anim.fade_in);
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