package com.MohamedTaha.Imagine.Quran.presenter;

import android.os.Bundle;
import android.widget.GridView;

import com.MohamedTaha.Imagine.Quran.model.ModelSora;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

public interface GridViewFragmentPresenter {
    void getPosition(int position, Bundle bundle);
    void getAllNameSour();
    void getAllImages();
    void getDisplayMetrics(GridView GridViewActivityGVShowImages,int imageDiemn);
    void onDestroy();
    void setOnSearchView(MaterialSearchView materialSearchView);
    void setOnQueryText(MaterialSearchView materialSearchView, List<ModelSora> name_swar);

}
