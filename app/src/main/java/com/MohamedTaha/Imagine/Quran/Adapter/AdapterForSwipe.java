package com.MohamedTaha.Imagine.Quran.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.MohamedTaha.Imagine.Quran.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterForSwipe extends PagerAdapter {
    Context context;
    public showDetail lisenter;

    private ArrayList<Integer> GalImages = new ArrayList<>();
    private static final String FONT_TYPE = "fonts/Aayat-Quraan3.ttf";
    Typeface nyTypeface;
    private  int position;

    public AdapterForSwipe(Context context, ArrayList<Integer> GalImages, showDetail lisenter) {
        this.context = context;
        this.GalImages = GalImages;
        this.lisenter = lisenter;
    }

    public AdapterForSwipe(Context context, ArrayList<Integer> GalImages) {
        this.context = context;
        this.GalImages = GalImages;
    }
    public AdapterForSwipe(Context context, int GalImages) {
        this.context = context;
        this.position = GalImages;
    }

    @Override
    public int getCount() {
        if (GalImages != null && GalImages.size() >=0){
            return GalImages.size();
        }
        else {
            return 0;
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        //For change font type
//        nyTypeface = Typeface.createFromAsset(context.getAssets(), FONT_TYPE);
//
//        View view = LayoutInflater.from(context).inflate(R.layout.custom_background_for_ayat, null);
//        viewHolder = new ViewHolder(view);
        ImageView imageView = new ImageView(context);
        //  TextView textView = new TextView(context);

//        String colorStart = "<font color='#F05A28'>";
//        String colorEnd = "</font>";
//        //   ModelSora modelSora = GalImages.get(position);
//        String yourText = GalImages.get(position).getName_sora();
//         yourText = yourText.replace("<(>", colorStart);
//        yourText = yourText.replace("<)>", colorEnd);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            viewHolder.tvShowText.setText(Html.fromHtml(yourText));
//        }else {
//            viewHolder.tvShowText.setText(Html.fromHtml(yourText),true);
//
//        }

        //    viewHolder.tvShowText.setText(Html.fromHtml(yourText));


      //  viewHolder.tvShowText
        //      .setTypeface(nyTypeface);
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

//        Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
//        viewHolder.tvShowText.setAnimation(animation);
//        animation.start();

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