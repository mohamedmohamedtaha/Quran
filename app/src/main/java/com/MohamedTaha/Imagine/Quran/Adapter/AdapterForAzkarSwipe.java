package com.MohamedTaha.Imagine.Quran.Adapter;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.libraryTextView.TextViewEx;
import com.MohamedTaha.Imagine.Quran.mvp.model.ModelAzkar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterForAzkarSwipe extends PagerAdapter {
    Context context;
    List<ModelAzkar> modelAzkarList = new ArrayList<>();
    public showDetail lisenter;


    public AdapterForAzkarSwipe(Context context, List<ModelAzkar> modelAzkarList, showDetail lisenter) {
        this.context = context;
        this.modelAzkarList = modelAzkarList;
        this.lisenter = lisenter;
    }

    @Override
    public int getCount() {
        if (modelAzkarList != null && modelAzkarList.size() >= 0) {
            return modelAzkarList.size();
        } else {
            return 0;
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //For change font type
//        nyTypeface = Typeface.createFromAsset(context.getAssets(), FONT_TYPE);
//
        View view = LayoutInflater.from(context).inflate(R.layout.custom_azkar_swipe, null);
        ViewHolder viewHolder = new ViewHolder(view);

        ModelAzkar modelSora = modelAzkarList.get(position);
        viewHolder.TVNameDescribe.setText(modelSora.getDescribe_azkar());
        //For Scrolling for textView
        viewHolder.TVNameDescribe.setMovementMethod(new ScrollingMovementMethod());
        viewHolder.TVNameAzkar.setText(modelSora.getName_azkar());
//        viewHolder.TVNameAzkar
//                .setTypeface(nyTypeface);
        //  Toast.makeText(context, modelSora.getName_azkar() , Toast.LENGTH_SHORT).show();
        //   Toast.makeText(context, "Size: " + modelAzkarList.get(position).getName_azkar() , Toast.LENGTH_SHORT).show();
        viewHolder.TVNameDescribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lisenter != null) lisenter.showDetails(position);
            }
        });
        container.addView(view);

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        view.setAnimation(animation);
        animation.start();
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
        @BindView(R.id.TV_Name_Azkar)
        TextView TVNameAzkar;
        @BindView(R.id.TV_Name_Describe)
        TextViewEx TVNameDescribe;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
