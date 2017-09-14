package com.MohamedTaha.Imagine.Quran.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.MohamedTaha.Imagine.Quran.ListSoundReader;
import com.MohamedTaha.Imagine.Quran.R;

import java.util.List;

import static com.MohamedTaha.Imagine.Quran.Fragments.FragmentGridView.sheks_names;

/**
 * Created by MANASATT on 20/08/17.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
  //  String [] nameListReads;
    private List<String> nameListReads;
    public static final String SHEKH_ID ="shekh_id";
    public static final String SHEKH_NAME ="shekh_name";
    private String[] typeELtelawa;

    int[] imageId ;
    int []linkses;

    private static LayoutInflater inflater = null;

    //Constructor
    public ImageAdapter(Context context,  List<String> nameListRead, int [] nameImages,String[] typeELtelawa){
        this.nameListReads = nameListRead;
        this.mContext = context;
        this.imageId = nameImages;
        this.typeELtelawa = typeELtelawa;
        inflater = (LayoutInflater)mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return nameListReads.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    //Create a new ImageView for each item referenced by the Adapter
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.custom_name_reader,null);


        holder.tvShowname = (TextView) rowView.findViewById(R.id.textViewShow);
        holder.img = (ImageView) rowView.findViewById(R.id.imageView);
        holder.tvShowType =(TextView)rowView.findViewById(R.id.textTypeTlawa);

        holder.tvShowname.setText(nameListReads.get(position));
        holder.tvShowType.setText(typeELtelawa[position]);

        //setting the bitmap from the drawable folder
        Bitmap bitmap= BitmapFactory.decodeResource(mContext.getResources(), imageId[position]);
        //set the image to the imageView
        holder.img.setImageBitmap(bitmap);
     //   holder.img.setImageResource(imageId[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(mContext,"YOu Clicked " + nameListReads[position] +"\n"+imageId[position],Toast.LENGTH_LONG).show();
               // Toast.makeText(mContext,"YOu Clicked " + sheks_names.get(position),Toast.LENGTH_LONG).show();

                //Send  intent to SingleViewActivity
              //  Intent i = new Intent(mContext,TestShowFullImage.class);
              //  i.putExtra("id",position);
              //  mContext.startActivity(i);
                //__________________
                Intent i = new Intent(mContext,ListSoundReader.class);
                i.putExtra(SHEKH_ID,position);
                i.putExtra(SHEKH_NAME,sheks_names.get(position));
                mContext.startActivity(i);
                // Toast.makeText(mContext,"YOu Clicked " + sheks_names.get(position),Toast.LENGTH_LONG).show();




            }
        });
        return rowView;
    }
    public class Holder{
        TextView tvShowname,tvShowType;
        ImageView img;
    }
}
