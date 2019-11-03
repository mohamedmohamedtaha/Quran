package com.MohamedTaha.Imagine.Quran.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.model.ModelSora;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterGridView extends ArrayAdapter<ModelSora> {
  //  private int imageDimension;
    private boolean isParts;

    public AdapterGridView(@NonNull Context context, List<ModelSora> listName_sora, boolean isParts/*, int imageDimension*/) {
        super(context, 0, listName_sora);
        this.isParts = isParts;
     //   this.imageDimension = imageDimension;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        ModelSora modelSora = getItem(i);
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.custom_layout_grid_view, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (isParts){
            viewHolder.TVNameSora.setText(modelSora.getName_part());
            viewHolder.TVNzolelsora.setVisibility(View.GONE);
                    }else {
            viewHolder.TVNameSora.setText(modelSora.getName_sora());
            viewHolder.TVNzolelsora.setVisibility(View.VISIBLE);
            viewHolder.TVNzolelsora.setText(modelSora.getNzol_elsora());

        }

//        final float scale = getContext().getResources().getDisplayMetrics().density;
//        int pixels = (int) (imageDimension * scale + 0.5f);
//        viewHolder.RelativeLayoutBackground.setLayoutParams(new RelativeLayout.LayoutParams(pixels, pixels));
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.TV_Name_Sora)
        TextView TVNameSora;
        @BindView(R.id.TV_Nzol_elsora)
        TextView TVNzolelsora;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}