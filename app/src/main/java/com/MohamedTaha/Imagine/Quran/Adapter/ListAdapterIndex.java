package com.MohamedTaha.Imagine.Quran.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.getData.Parts;

import java.util.ArrayList;

/**
 * Created by MANASATT on 24/06/17.
 */
public class ListAdapterIndex extends ArrayAdapter<Parts> {
    public static final String NUMBERSORA ="numberSora";


    public static final String NAMESORA ="NAMESORA";
    public static final String TEXTSTART ="TEXTSTART";
    public static final String TEXTCONTENT ="TEXTCONTENT";


    public ListAdapterIndex(Activity context, ArrayList<Parts> arrayListAyat) {
        super(context,0,arrayListAyat);

    }

      @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

          View listItemView = convertView;
          if (listItemView==null){
              listItemView = LayoutInflater.from(getContext()).inflate(R.layout.row_parts_swar,parent,false);
          }
          Parts currentAya = getItem(position);
          TextView nameSora = (TextView)listItemView.findViewById(R.id.nameSora);
          nameSora.setText(currentAya.getNameSora());
          TextView timeNsol = (TextView)listItemView.findViewById(R.id.timeNozol);
          timeNsol.setText(currentAya.getTimeNzol());
          TextView numberSora= (TextView)listItemView.findViewById(R.id.numberSora);
          numberSora.setText((position+1)+"");
          return listItemView;
      }

}