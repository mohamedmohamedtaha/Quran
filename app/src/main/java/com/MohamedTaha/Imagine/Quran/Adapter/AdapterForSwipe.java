package com.MohamedTaha.Imagine.Quran.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.model.ModelSora;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterForSwipe extends PagerAdapter {
    Context context;
    public showDetail lisenter;
    private ArrayList<ModelSora> GalImages = new ArrayList<>();
    private static final String FONT_TYPE = "fonts/Aayat-Quraan3.ttf";
    Typeface nyTypeface;

    public AdapterForSwipe(Context context, ArrayList<ModelSora> GalImages, showDetail lisenter) {
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
        ViewHolder viewHolder;
        //For change font type
        nyTypeface = Typeface.createFromAsset(context.getAssets(), FONT_TYPE);

        View view = LayoutInflater.from(context).inflate(R.layout.custom_background_for_ayat, null);
        viewHolder = new ViewHolder(view);
        //ImageView imageView = new ImageView(context);
      //  TextView textView = new TextView(context);
        String colorStart = "<font color='#10618D'>";
        String colorEnd = "</font>";
        //   ModelSora modelSora = GalImages.get(position);
        String yourText = GalImages.get(position).getName_sora();
        yourText = yourText.replace("<(>", colorStart);
        yourText = yourText.replace("<)>", colorEnd);
        viewHolder.tvShowText.setText(Html.fromHtml(yourText));
        viewHolder.tvShowText
                .setTypeface(nyTypeface);
//        Glide.with(context)
//                .load(GalImages.get(position))
//                .into(imageView);
        container.addView(view);
//        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lisenter != null) lisenter.showDetails(position);
            }
        });
        //    imageView.setRotationY(180);

//        Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
//        imageView.setAnimation(animation);
//        animation.start();

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        viewHolder.tvShowText.setAnimation(animation);
        animation.start();

        return view;
    }

    public interface showDetail {
        void showDetails(int positon);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    static
    class ViewHolder {
        @BindView(R.id.tv_Show_Text)
        TextView tvShowText;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}