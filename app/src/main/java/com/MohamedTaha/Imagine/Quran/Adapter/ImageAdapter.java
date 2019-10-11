package com.MohamedTaha.Imagine.Quran.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.MohamedTaha.Imagine.Quran.ui.activities.ListSoundReader;
import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.model.ImageModel;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MANASATT on 20/08/17.
 */

public class ImageAdapter extends ArrayAdapter<ImageModel> {
    public static final String SHEKH_ID = "shekh_id";
    public static final String SHEKH_NAME = "shekh_name";

    private Activity activity;

    public ImageAdapter(@NonNull Context context, List<ImageModel> imageModels,Activity activity) {
        super(context, 0,imageModels);
        this.activity = activity;
    }

    //Create a new ImageView for each item referenced by the Adapter
    @Override
    public View getView(final int position,View convertView, ViewGroup parent) {
        ViewHolder holder ;
        ImageModel imageModel = getItem(position);
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_name_reader, null);
            holder = new ViewHolder(convertView);
        convertView.setTag(holder);
        }else {
           holder = (ViewHolder) convertView.getTag();
        }



        holder.textViewShow.setText(imageModel.getName_shekh());
        holder.textTypeTlawa.setText(imageModel.getType_sora());

        //setting the bitmap from the drawable folder
        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), imageModel.getUrl_image());
        //set the image to the imageView
        holder.imageView.setImageBitmap(bitmap);
        //   holder.img.setImageResource(imageId[position]);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listSound = new Intent(getContext(), ListSoundReader.class);
                Bundle bundle = new Bundle();
                bundle.putString(SHEKH_ID, new Gson().toJson(imageModel.getPosition()));
                bundle.putString(SHEKH_NAME,new Gson().toJson(imageModel.getName_shekh()));
                listSound.putExtras(bundle);
                getContext().startActivity(listSound);
                activity.overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });
        return convertView;
    }
     static
    class ViewHolder {
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.textViewShow)
        TextView textViewShow;
        @BindView(R.id.textTypeTlawa)
        TextView textTypeTlawa;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
