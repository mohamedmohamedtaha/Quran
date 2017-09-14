package com.MohamedTaha.Imagine.Quran.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.MohamedTaha.Imagine.Quran.Fragments.FragmentGridView;
import com.MohamedTaha.Imagine.Quran.Fragments.FragmentListSwar;
import com.MohamedTaha.Imagine.Quran.R;

/**
 * Created by MANASATT on 15/07/17.
 */

public class TabAdapter extends FragmentPagerAdapter {
    /*
        Context of the App
         */
    private Context mContext;
    private String[] tabText;
    public TabAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext = context;
        this.tabText = new String[]{mContext.getResources().getString(R.string.readswar),mContext.getResources().getString(R.string.listen_swar) };
        //  "القِرَاءَةُ بِالأَجْزَاء",
    }


    @Override
    public Fragment getItem(int position) {
      //  if (position == 0){
        //    return new FragmentListParts();
        //}
         if (position == 0){
           return new FragmentListSwar();
        }
      // else if (position == 2){
       //     return new FragmentListSound();
       // }
        else
         {
             return new FragmentGridView();
         }
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return tabText[position];

    }
}

