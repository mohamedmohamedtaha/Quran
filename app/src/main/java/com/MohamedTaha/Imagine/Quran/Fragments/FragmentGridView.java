package com.MohamedTaha.Imagine.Quran.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.MohamedTaha.Imagine.Quran.Adapter.ImageAdapter;
import com.MohamedTaha.Imagine.Quran.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MANASATT on 20/08/17.
 */

public class FragmentGridView extends Fragment {

    private GridView gridViewImage;
   // ArrayList<Parts> parts = new ArrayList<>();
    ImageAdapter imageAdapter;
    private List<String> nameListReads;

    ArrayList<String> resultEntities;
   public static ArrayList<String> sheks_names;

   // public static String [] nameReaderList = {"محمدصديق المنشاوي","عبدالباسط عبدالصمد","الحصري","ماهر المعيقلي","المغامسي"};
    //Keep all Images in array
    public  static int[] mThumbIds = {R.drawable.elsodes,R.drawable.elklbany,R.drawable.elmenshawy,R.drawable.elmenshawy,
           R.drawable.abdeelbaset, R.drawable.elhosary, R.drawable.elhosary, R.drawable.almaqely,R.drawable.msharyelafasy,
           R.drawable.sherem,R.drawable.eltblawy,R.drawable.eldosary,R.drawable.elgeheny ,R.drawable.mohamedgbrer,
           R.drawable.naserelqatamy,R.drawable.elagamy,R.drawable.elbana,R.drawable.elqarashy, R.drawable.elqasem, R.drawable.bder};
    public  static String[] typeELtelawa = {"حفص عن عاصم","حفص عن عاصم","حفص عن عاصم","المصحف المجود","المصحف المجود","حفص عن عاصم","المصحف المجود","حفص عن عاصم","حفص عن عاصم","حفص عن عاصم",
            "حفص عن عاصم","حفص عن عاصم" ,"حفص عن عاصم" ,"حفص عن عاصم","حفص عن عاصم","حفص عن عاصم","حفص عن عاصم","حفص عن عاصم","حفص عن عاصم","حفص عن عاصم" };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //  return inflater.inflate(R.layout.fragment_list_parts, container, false);
        View rootView = inflater.inflate(R.layout.fragment_grid_view, null);
        getAllWidgets(rootView);
        setAdapter();
        return rootView;
    }

    private void getAllWidgets(View view) {
        sheks_names=new ArrayList<>();
        resultEntities = new ArrayList<>();
        gridViewImage = (GridView) view.findViewById(R.id.gridView);
    }
    private ArrayList<String> getData() {
        sheks_names.add("عبدالرحمن السديس");
        sheks_names.add("عادل الكلباني");
        sheks_names.add("محمد صديق المنشاوي");
        sheks_names.add("محمد صديق المنشاوي");
        sheks_names.add("عبدالباسط عبدالصمد");
        sheks_names.add("محمود خليل الحصري");
        sheks_names.add("محمود خليل الحصري");
        sheks_names.add("ماهر المعيقلي");
        sheks_names.add("مشاري العفاسي");
        sheks_names.add("سعود الشريم");
        sheks_names.add("محمد محمود الطبلاوي");
        sheks_names.add("ياسر الدوسري");
        sheks_names.add("عبدالله الجهني");
        sheks_names.add("محمد جبريل");
        sheks_names.add("ناصر القطامي");
        sheks_names.add("أحمد العجمي");
        sheks_names.add("محمود علي البنا");
        sheks_names.add("ياسر القرشي");
        sheks_names.add("عبدالمحسن القاسم");
        sheks_names.add("صلاح البدري");

        return sheks_names;
    }
    private void setAdapter() {
          resultEntities = getData();
          imageAdapter = new ImageAdapter(getActivity(),resultEntities,mThumbIds,typeELtelawa);
          gridViewImage.setAdapter(imageAdapter);
        }

    }