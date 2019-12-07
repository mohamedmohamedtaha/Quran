package com.MohamedTaha.Imagine.Quran.mvp.view;

import com.MohamedTaha.Imagine.Quran.mvp.model.ModelAzkar;

import java.util.List;

public interface AzkarFragmentView {
    void showAfterQueryText(List<ModelAzkar> stringList);
    void isEmpty();
    void thereData();
    void showProgress();
    void hideProgress();
    void showAllINameAzkar(List<ModelAzkar> strings);
    void showAnimation();
    void showAfterSearch();

}
